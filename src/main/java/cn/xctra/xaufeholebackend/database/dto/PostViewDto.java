package cn.xctra.xaufeholebackend.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostViewDto implements Serializable {
    /**
     * id : 1
     * posts : [{"id":0,"poster_index":-1,"post_time":1664551473,"content":"# 欢迎来到西财树洞！","attributes":["置顶"]},{"id":1,"poster_index":0,"post_time":1664553033,"content":"火钳刘明"},{"id":2,"poster_index":1,"post_time":1664553033,"content":"火钳刘明"},{"id":3,"poster_index":2,"post_time":1664553033,"content":"火钳刘明"}]
     */

    private long id;
    private List<PostsBean> posts;
    private boolean star;

    @Data
    @AllArgsConstructor
    @Builder
    public static class PostsBean implements Serializable {
        /**
         * id : 0
         * poster_index : -1
         * post_time : 1664551473
         * content : # 欢迎来到西财树洞！
         * attributes : ["置顶"]
         */

        private long id;
        private int poster_index;
        private long post_time;
        private String content;
        private List<String> attributes;
        private List<String> tags;
        // For admin only
        private UserProfileDto profile;

        // For Post but comments
        public PostsBean(long post_time, String content, List<String> attributes, List<String> tags, UserProfileDto profile) {
            this(0, -1, post_time, content, attributes, tags, profile);
        }
    }
}
