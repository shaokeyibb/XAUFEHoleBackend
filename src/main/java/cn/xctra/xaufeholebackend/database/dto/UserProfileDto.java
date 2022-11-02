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
    private long studentID;
    private String email;
    private List<String> roles;
    private List<String> permissions;
    // starred posts and others should not be included in this dto, and they should get from other side

    public UserProfileDto(UserEntity user) {
        this(user.getId(), user.getStudentID(), user.getEmail(), user.getRoles(), user.getPermissions());
    }

}
