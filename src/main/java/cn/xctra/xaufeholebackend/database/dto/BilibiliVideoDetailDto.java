package cn.xctra.xaufeholebackend.database.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BilibiliVideoDetailDto implements Serializable {


    /**
     * code : 0
     * message : 0
     * ttl : 1
     * data : {"bvid":"BV1gg41187XR","aid":516722056,"videos":1,"tid":95,"tname":"数码","copyright":1,"pic":"http://i2.hdslb.com/bfs/archive/9a3aff3acf5a23bb58affeaade5855a1a373ebf9.jpg","title":"USB：我又改名了","pubdate":1666196657,"ctime":1666196657,"desc":"好起来了","desc_v2":[{"raw_text":"好起来了","type":1,"biz_id":0}],"state":0,"duration":162,"mission_id":1013247,"rights":{"bp":0,"elec":0,"download":1,"movie":0,"pay":0,"hd5":1,"no_reprint":1,"autoplay":1,"ugc_pay":0,"is_cooperation":0,"ugc_pay_preview":0,"no_background":0,"clean_mode":0,"is_stein_gate":0,"is_360":0,"no_share":0,"arc_pay":0,"free_watch":0},"owner":{"mid":39180492,"name":"蒸気火鸡","face":"https://i0.hdslb.com/bfs/face/60ec600cc9d84c74c8220ab41ce49bacfdc0895c.jpg"},"stat":{"aid":516722056,"view":303943,"danmaku":460,"reply":1100,"favorite":1656,"coin":2526,"share":1954,"now_rank":0,"his_rank":0,"like":21710,"dislike":0,"evaluation":"","argue_msg":""},"dynamic":"好起来了","cid":866408795,"dimension":{"width":1920,"height":1080,"rotate":0},"premiere":null,"teenage_mode":0,"is_chargeable_season":false,"is_story":false,"no_cache":false,"pages":[{"cid":866408795,"page":1,"from":"vupload","part":"USB：我又改名了","duration":162,"vid":"","weblink":"","dimension":{"width":1920,"height":1080,"rotate":0},"first_frame":"http://i1.hdslb.com/bfs/storyff/n221020qn1fuq35cdx4et32cdgpwbr2o_firsti.jpg"}],"subtitle":{"allow_submit":false,"list":[]},"label":{"type":1},"is_season_display":false,"user_garb":{"url_image_ani_cut":""},"honor_reply":{"honor":[{"aid":516722056,"type":4,"desc":"热门","weekly_recommend_num":0}]},"like_icon":""}
     */

    private int code;
    private String message;
    private int ttl;
    private DataBean data;

    @Data
    public static class DataBean implements Serializable {
        /**
         * bvid : BV1gg41187XR
         * aid : 516722056
         * videos : 1
         * tid : 95
         * tname : 数码
         * copyright : 1
         * pic : http://i2.hdslb.com/bfs/archive/9a3aff3acf5a23bb58affeaade5855a1a373ebf9.jpg
         * title : USB：我又改名了
         * pubdate : 1666196657
         * ctime : 1666196657
         * desc : 好起来了
         * desc_v2 : [{"raw_text":"好起来了","type":1,"biz_id":0}]
         * state : 0
         * duration : 162
         * mission_id : 1013247
         * rights : {"bp":0,"elec":0,"download":1,"movie":0,"pay":0,"hd5":1,"no_reprint":1,"autoplay":1,"ugc_pay":0,"is_cooperation":0,"ugc_pay_preview":0,"no_background":0,"clean_mode":0,"is_stein_gate":0,"is_360":0,"no_share":0,"arc_pay":0,"free_watch":0}
         * owner : {"mid":39180492,"name":"蒸気火鸡","face":"https://i0.hdslb.com/bfs/face/60ec600cc9d84c74c8220ab41ce49bacfdc0895c.jpg"}
         * stat : {"aid":516722056,"view":303943,"danmaku":460,"reply":1100,"favorite":1656,"coin":2526,"share":1954,"now_rank":0,"his_rank":0,"like":21710,"dislike":0,"evaluation":"","argue_msg":""}
         * dynamic : 好起来了
         * cid : 866408795
         * dimension : {"width":1920,"height":1080,"rotate":0}
         * premiere : null
         * teenage_mode : 0
         * is_chargeable_season : false
         * is_story : false
         * no_cache : false
         * pages : [{"cid":866408795,"page":1,"from":"vupload","part":"USB：我又改名了","duration":162,"vid":"","weblink":"","dimension":{"width":1920,"height":1080,"rotate":0},"first_frame":"http://i1.hdslb.com/bfs/storyff/n221020qn1fuq35cdx4et32cdgpwbr2o_firsti.jpg"}]
         * subtitle : {"allow_submit":false,"list":[]}
         * label : {"type":1}
         * is_season_display : false
         * user_garb : {"url_image_ani_cut":""}
         * honor_reply : {"honor":[{"aid":516722056,"type":4,"desc":"热门","weekly_recommend_num":0}]}
         * like_icon :
         */

        private String bvid;
        private int aid;
        private int videos;
        private int tid;
        private String tname;
        private int copyright;
        private String pic;
        private String title;
        private int pubdate;
        private int ctime;
        private String desc;
        private int state;
        private int duration;
        private int mission_id;
        private RightsBean rights;
        private OwnerBean owner;
        private StatBean stat;
        private String dynamic;
        private int cid;
        private DimensionBean dimension;
        private Object premiere;
        private int teenage_mode;
        private boolean is_chargeable_season;
        private boolean is_story;
        private boolean no_cache;
        private SubtitleBean subtitle;
        private LabelBean label;
        private boolean is_season_display;
        private UserGarbBean user_garb;
        private HonorReplyBean honor_reply;
        private String like_icon;
        private List<DescV2Bean> desc_v2;
        private List<PagesBean> pages;

        @Data
        public static class RightsBean implements Serializable {
            /**
             * bp : 0
             * elec : 0
             * download : 1
             * movie : 0
             * pay : 0
             * hd5 : 1
             * no_reprint : 1
             * autoplay : 1
             * ugc_pay : 0
             * is_cooperation : 0
             * ugc_pay_preview : 0
             * no_background : 0
             * clean_mode : 0
             * is_stein_gate : 0
             * is_360 : 0
             * no_share : 0
             * arc_pay : 0
             * free_watch : 0
             */

            private int bp;
            private int elec;
            private int download;
            private int movie;
            private int pay;
            private int hd5;
            private int no_reprint;
            private int autoplay;
            private int ugc_pay;
            private int is_cooperation;
            private int ugc_pay_preview;
            private int no_background;
            private int clean_mode;
            private int is_stein_gate;
            private int is_360;
            private int no_share;
            private int arc_pay;
            private int free_watch;
        }

        @Data
        public static class OwnerBean implements Serializable {
            /**
             * mid : 39180492
             * name : 蒸気火鸡
             * face : https://i0.hdslb.com/bfs/face/60ec600cc9d84c74c8220ab41ce49bacfdc0895c.jpg
             */

            private int mid;
            private String name;
            private String face;
        }

        @Data
        public static class StatBean implements Serializable {
            /**
             * aid : 516722056
             * view : 303943
             * danmaku : 460
             * reply : 1100
             * favorite : 1656
             * coin : 2526
             * share : 1954
             * now_rank : 0
             * his_rank : 0
             * like : 21710
             * dislike : 0
             * evaluation :
             * argue_msg :
             */

            private int aid;
            private int view;
            private int danmaku;
            private int reply;
            private int favorite;
            private int coin;
            private int share;
            private int now_rank;
            private int his_rank;
            private int like;
            private int dislike;
            private String evaluation;
            private String argue_msg;
        }

        @Data
        public static class DimensionBean implements Serializable {
            /**
             * width : 1920
             * height : 1080
             * rotate : 0
             */

            private int width;
            private int height;
            private int rotate;
        }

        @Data
        public static class SubtitleBean implements Serializable {
            /**
             * allow_submit : false
             * list : []
             */

            private boolean allow_submit;
            private List<?> list;
        }

        @Data
        public static class LabelBean implements Serializable {
            /**
             * type : 1
             */

            private int type;
        }

        @Data
        public static class UserGarbBean implements Serializable {
            /**
             * url_image_ani_cut :
             */

            private String url_image_ani_cut;
        }

        @Data
        public static class HonorReplyBean implements Serializable {
            private List<HonorBean> honor;

            @Data
            public static class HonorBean implements Serializable {
                /**
                 * aid : 516722056
                 * type : 4
                 * desc : 热门
                 * weekly_recommend_num : 0
                 */

                private int aid;
                private int type;
                private String desc;
                private int weekly_recommend_num;
            }
        }

        @Data
        public static class DescV2Bean implements Serializable {
            /**
             * raw_text : 好起来了
             * type : 1
             * biz_id : 0
             */

            private String raw_text;
            private int type;
            private int biz_id;
        }

        @Data
        public static class PagesBean implements Serializable {
            /**
             * cid : 866408795
             * page : 1
             * from : vupload
             * part : USB：我又改名了
             * duration : 162
             * vid :
             * weblink :
             * dimension : {"width":1920,"height":1080,"rotate":0}
             * first_frame : http://i1.hdslb.com/bfs/storyff/n221020qn1fuq35cdx4et32cdgpwbr2o_firsti.jpg
             */

            private int cid;
            private int page;
            private String from;
            private String part;
            private int duration;
            private String vid;
            private String weblink;
            private DimensionBeanX dimension;
            private String first_frame;

            @Data
            public static class DimensionBeanX implements Serializable {
                /**
                 * width : 1920
                 * height : 1080
                 * rotate : 0
                 */

                private int width;
                private int height;
                private int rotate;
            }
        }
    }
}
