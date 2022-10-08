package cn.xctra.xaufeholebackend.database.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SMMSUploadImageResponseDto implements Serializable {


    /**
     * success : true
     * code : success
     * message : Upload success.
     * data : {"file_id":0,"width":4677,"height":3307,"filename":"luo.jpg","storename":"D5VpWCKFElUsPcR.jpg","size":801933,"path":"/2019/12/16/D5VpWCKFElUsPcR.jpg","hash":"Q6vLIbCGZojrMhO2e7BmgFuXRV","url":"https://vip1.loli.net/2019/12/16/D5VpWCKFElUsPcR.jpg","delete":"https://sm.ms/delete/Q6vLIbCGZojrMhO2e7BmgFuXRV","page":"https://sm.ms/image/D5VpWCKFElUsPcR"}
     * RequestId : 8A84DDCA-96B3-4363-B5DF-524E95A5201A
     */

    private boolean success;
    private String code;
    private String message;
    private DataBean data;
    private String RequestId;

    @Data
    public static class DataBean implements Serializable {
        /**
         * file_id : 0
         * width : 4677
         * height : 3307
         * filename : luo.jpg
         * storename : D5VpWCKFElUsPcR.jpg
         * size : 801933
         * path : /2019/12/16/D5VpWCKFElUsPcR.jpg
         * hash : Q6vLIbCGZojrMhO2e7BmgFuXRV
         * url : https://vip1.loli.net/2019/12/16/D5VpWCKFElUsPcR.jpg
         * delete : https://sm.ms/delete/Q6vLIbCGZojrMhO2e7BmgFuXRV
         * page : https://sm.ms/image/D5VpWCKFElUsPcR
         */

        private int file_id;
        private int width;
        private int height;
        private String filename;
        private String storename;
        private int size;
        private String path;
        private String hash;
        private String url;
        private String delete;
        private String page;
    }
}
