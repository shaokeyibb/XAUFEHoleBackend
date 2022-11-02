package cn.xctra.xaufeholebackend.database.dto;

import cn.xctra.xaufeholebackend.database.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private long id;
    private List<String> roles;
    private List<String> permissions;

    public UserProfileDto(UserEntity user) {
        this(user.getId(), user.getRoles(), user.getPermissions());
    }

}
