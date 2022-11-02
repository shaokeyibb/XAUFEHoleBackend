package cn.xctra.xaufeholebackend.controllers;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.crypto.digest.BCrypt;
import cn.xctra.xaufeholebackend.database.dto.EmailAuthRequestDto;
import cn.xctra.xaufeholebackend.database.entities.UserEntity;
import cn.xctra.xaufeholebackend.database.services.UserService;
import cn.xctra.xaufeholebackend.model.WebVpnAvailableModel;
import cn.xctra.xaufeholebackend.services.MailService;
import cn.xctra.xaufeholebackend.services.RegistrationService;
import cn.xctra.xaufeholebackend.services.WebVpnService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramostear.captcha.HappyCaptcha;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    private final OkHttpClient okHttpClient;
    private final WebVpnService webVpnService;
    private final WebVpnController webVpnController;
    private final UserService userService;
    private final ObjectMapper xmlMapper;

    private final MailService mailService;

    private final RegistrationService registrationService;

    @Autowired
    public AuthController(OkHttpClient okHttpClient, WebVpnService webVpnService, WebVpnController webVpnController, UserService userService, @Qualifier("xmlMapper") ObjectMapper xmlMapper, MailService mailService, RegistrationService registrationService) {
        this.okHttpClient = okHttpClient;
        this.webVpnService = webVpnService;
        this.webVpnController = webVpnController;
        this.userService = userService;
        this.xmlMapper = xmlMapper;
        this.mailService = mailService;
        this.registrationService = registrationService;
    }

    @GetMapping("casAuth")
    public void casAuth(@RequestParam String ticket,
                        @RequestParam(required = false, defaultValue = "/") String redirectUrl,
                        @RequestParam(required = false, defaultValue = "#{webVpnService.cookie}") String webVpnCookie,
                        @RequestParam(required = false, defaultValue = "false") boolean doShareWebVpnCookie,
                        HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse) throws IOException {
        WebVpnAvailableModel model = webVpnController.available(webVpnCookie).getBody();
        if (model == null) {
            httpServletResponse.sendError(500, "Model is null");
            return;
        }
        if (!model.isAvailable()) {
            httpServletResponse.sendError(400, "Cookie not available");
            return;
        }

        if (doShareWebVpnCookie) {
            webVpnService.setCookie(webVpnCookie);
        }

        String queryString = httpServletRequest.getQueryString().replaceAll("&?ticket=[^&]*", "");

        try (Response response = okHttpClient.newCall(
                new Request.Builder()
                        .get()
                        .url("https://webvpn.xaufe.edu.cn/https/77726476706e69737468656265737421f3f652d23f317d567b468ca88d1b203b/p3/serviceValidate" + "?ticket=" + ticket + "&service=" + httpServletRequest.getRequestURL().toString() + URLEncoder.encode("?" + queryString, "UTF-8"))
                        .header("Cookie", "show_vpn=0; show_faq=0; refresh=1; wengine_vpn_ticketwebvpn_xaufe_edu_cn=" + webVpnCookie)
                        .build()
        ).execute()) {
            Map<?, ?> result = xmlMapper.readValue(Objects.requireNonNull(response.body()).byteStream(), Map.class);

            if (result.containsKey("authenticationFailure")) {
                Map<?, ?> authenticationFailure = (Map<?, ?>) result.get("authenticationFailure");
                String code = (String) authenticationFailure.get("code");
                switch (code) {
                    case "INVALID_TICKET":
                        httpServletResponse.sendError(403, "invalid ticket");
                        break;
                    case "INVALID_SERVICE":
                        httpServletResponse.sendError(500, "invalid service");
                        break;
                    case "INVALID_REQUEST":
                        httpServletResponse.sendError(500, "invalid request");
                        break;
                    default:
                        httpServletResponse.sendError(500, "invalid something");
                        break;
                }
                return;
            }
            long username = Long.parseLong((String) ((Map<?, ?>) result.get("authenticationSuccess")).get("user"));

            if (!userService.hasUser(username)) {
                userService.saveUser(username);
            }

            StpUtil.login(username);

        } catch (IOException e) {
            httpServletResponse.sendError(500, e.getMessage());
            return;
        }

        httpServletResponse.sendRedirect(Base64Decoder.decodeStr(redirectUrl));
    }

    @PostMapping("emailAuth")
    public ResponseEntity<String> emailAuth(@RequestBody EmailAuthRequestDto body,
                                            HttpServletRequest httpServletRequest) throws IOException {
//        if (!body.getUsername().endsWith("@xaufe.edu.cn")) {
//            return ResponseEntity.badRequest().body("账号不符合格式");
//        }

        if (body.isRegisterMode()) {

            boolean verified = HappyCaptcha.verification(httpServletRequest, body.getCaptcha(), true);

            if (!verified) {
                return ResponseEntity.badRequest().body("验证码错误");
            }

            if (userService.hasUser(body.getUsername().hashCode())) {
                return ResponseEntity.badRequest().body("该账号已被注册");
            }

            if (body.getRegistrationCode() == null) {
                mailService.sendMail(body.getUsername(), "About your registration in XAUFEHole", "Your registration code is " + registrationService.requireRegistrationCode(body.getUsername()));
                return ResponseEntity.accepted().build();
            }

            if (!registrationService.verifyRegistrationCode(body.getUsername(), body.getRegistrationCode())) {
                return ResponseEntity.badRequest().body("无效的注册验证码");
            }

            userService.saveUser(body.getUsername(), body.getPassword());
            StpUtil.login(body.getUsername().hashCode());
            return ResponseEntity.ok().build();
        }

        if (!userService.hasUser(body.getUsername().hashCode())) {
            return ResponseEntity.badRequest().body("账号或密码错误");
        }

        UserEntity user = Objects.requireNonNull(userService.getUser(body.getUsername().hashCode()));

        if (user.getPassword() == null) {
            return ResponseEntity.badRequest().body("账号或密码错误");
        }

        if (!BCrypt.checkpw(body.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("账号或密码错误");
        }

        StpUtil.login(body.getUsername().hashCode());

        return ResponseEntity.ok().build();
    }

    @SaCheckLogin
    @GetMapping("logout")
    public void logout() {
        StpUtil.logout();
    }

}