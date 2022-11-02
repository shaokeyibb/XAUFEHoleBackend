package cn.xctra.xaufeholebackend.configurations;

import cn.dev33.satoken.stp.StpInterface;
import cn.xctra.xaufeholebackend.database.dto.UserProfileDto;
import cn.xctra.xaufeholebackend.database.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class StpInterfaceImpl implements StpInterface {

    private final UserService userService;

    @Autowired
    public StpInterfaceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 此处 new 了一个 UserProfileDto 是出于希望和 Controller 返回值同步以保证封装性的想法
        return new UserProfileDto(Objects.requireNonNull(userService.getUser(Long.parseLong(String.valueOf(loginId))))).getPermissions();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 此处 new 了一个 UserProfileDto 是出于希望和 Controller 返回值同步以保证封装性的想法
        return new UserProfileDto(Objects.requireNonNull(userService.getUser(Long.parseLong(String.valueOf(loginId))))).getRoles();
    }
}
