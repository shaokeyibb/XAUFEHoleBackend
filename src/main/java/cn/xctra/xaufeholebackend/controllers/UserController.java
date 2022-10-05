package cn.xctra.xaufeholebackend.controllers;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.xctra.xaufeholebackend.database.dto.UserProfileDto;
import cn.xctra.xaufeholebackend.database.entities.UserEntity;
import cn.xctra.xaufeholebackend.database.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin //TODO: remove this
    @SaCheckLogin
    @GetMapping("info")
    public ResponseEntity<UserProfileDto> info() {
        UserEntity entity = userService.getUser(StpUtil.getLoginIdAsLong());
        if (entity != null) {
            return ResponseEntity.ok(new UserProfileDto(entity));
        }
        return ResponseEntity.notFound().build();
    }

}
