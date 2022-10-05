package cn.xctra.xaufeholebackend.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyPostDto {

    private long id;
    private String content;

}
