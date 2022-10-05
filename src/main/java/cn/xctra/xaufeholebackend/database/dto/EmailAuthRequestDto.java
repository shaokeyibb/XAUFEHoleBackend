package cn.xctra.xaufeholebackend.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailAuthRequestDto {

    private String username;
    private String password;
    private String captcha;
    private boolean registerMode;
    private String registrationCode;

}
