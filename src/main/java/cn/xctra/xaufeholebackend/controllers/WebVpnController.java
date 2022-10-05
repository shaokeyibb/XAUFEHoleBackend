package cn.xctra.xaufeholebackend.controllers;

import cn.hutool.core.io.IoUtil;
import cn.xctra.xaufeholebackend.model.WebVpnAvailableModel;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/webvpn/")
public class WebVpnController {

    @Value("${xaufe-hole.webvpn.backend-api-hash}")
    private String webVpnBackendAPIHash;

    private final OkHttpClient okHttpClient;

    @Autowired
    public WebVpnController(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    /**
     * 检测 WebVPN 服务是否可用
     * <br>
     * 该端点的检测逻辑是：向 _available 路由发送一个 get 请求（该路由永远返回 202 accept），
     * 如果正常得到返回，则证明 WebVPN 有效，
     * 否则，即使得到一个非 4xx 或 5xx 返回，也代表请求无效（因为可能返回的是 webvpn 的其他界面或 CAS 登录跳转）
     *
     * @return WebVPN 服务是否可用
     */
    @CrossOrigin //TODO: remove this
    @GetMapping("available")
    public ResponseEntity<WebVpnAvailableModel> available(@RequestParam(required = false, defaultValue = "#{webVpnService.cookie}") String cookie) {
        try (Response response = okHttpClient.newCall(
                new Request.Builder()
                        .get()
                        .url("https://webvpn.xaufe.edu.cn/http/" + webVpnBackendAPIHash + "/api/webvpn/_available")
                        .header("Cookie", "show_vpn=0; show_faq=0; refresh=1; wengine_vpn_ticketwebvpn_xaufe_edu_cn=" + cookie)
                        .build()
        ).execute()) {
            return ResponseEntity.ok(new WebVpnAvailableModel(response.code() == 202));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("_available")
    public ResponseEntity<JsonObject> _available() {
        return ResponseEntity.accepted().build();
    }
}
