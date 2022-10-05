package cn.xctra.xaufeholebackend.database.dto;

import cn.xctra.xaufeholebackend.database.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private long id;

    public UserProfileDto(UserEntity user) {
        this(user.getId());
    }

}
