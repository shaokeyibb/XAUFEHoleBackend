package cn.xctra.xaufeholebackend.database.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPreviewDto implements Serializable {
    /**
     * id : 1
     * post_time : 1664551473
     * preview : # 欢迎来到西财树洞！
     * reply_count : Math.floor((Math.random() * 10) + 1)
     * star_count : Math.floor((Math.random() * 10) + 1)
     * attributes : ["置顶"]
     * tags : ["新人报到"]
     * comments : [{"id":1,"poster_index":0,"post_time":1664553033,"content":"火钳刘明"}]
     */

    private long id;
    private long post_time;
    private String preview;
    private int reply_count;
    private int star_count;
    private List<String> attributes;
    private List<String> tags;
    private List<CommentPreview> comments;

    @Data
    @AllArgsConstructor
    @Builder
    public static class CommentPreview implements Serializable {
        /**
         * id : 1
         * poster_index : 0
         * post_time : 1664553033
         * content : 火钳刘明
         */

        private long id;
        private int poster_index;
        private long post_time;
        private String content;
    }
}
