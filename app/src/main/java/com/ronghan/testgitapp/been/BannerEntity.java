package com.ronghan.testgitapp.been;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/4/21 0021 14:57
 * 修改人：Michael
 * 修改时间：2016/4/21 0021 14:57
 * 修改备注：
 */
public class BannerEntity implements Serializable{

    /**
     * bannerPics : [{"id":"40281d8154a7c5430154a7d5331a0009","originalName":"1463104644106.jpg","picName":"300.500.1463104644106.jpg","picUrl":"http://192.168.1.180:8888/img/300.500.1463104644106.jpg","version":0},{"id":"40281d8154a7c5430154a7d5331b000a","originalName":"1463104644757.jpg","picName":"300.500.1463104644757.jpg","picUrl":"http://192.168.1.180:8888/img/300.500.1463104644757.jpg","version":0},{"id":"40281d8154a7c5430154a7d5750e000c","originalName":"1463104662017.jpg","picName":"300.500.1463104662017.jpg","picUrl":"http://192.168.1.180:8888/img/300.500.1463104662017.jpg","version":0}]
     * bannerType : TITLE
     * id : 40281d8154a7c5430154a7d4cafc0008
     * version : 3
     */

    private String bannerType;
    private String id;
    private int version;
    /**
     * id : 40281d8154a7c5430154a7d5331a0009
     * originalName : 1463104644106.jpg
     * picName : 300.500.1463104644106.jpg
     * picUrl : http://192.168.1.180:8888/img/300.500.1463104644106.jpg
     * version : 0
     */

    private List<BannerPicsBean> bannerPics;

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<BannerPicsBean> getBannerPics() {
        return bannerPics;
    }

    public void setBannerPics(List<BannerPicsBean> bannerPics) {
        this.bannerPics = bannerPics;
    }

    public static class BannerPicsBean {
        private String id;
        private String originalName;
        private String picName;
        private String picUrl;
        private int version;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOriginalName() {
            return originalName;
        }

        public void setOriginalName(String originalName) {
            this.originalName = originalName;
        }

        public String getPicName() {
            return picName;
        }

        public void setPicName(String picName) {
            this.picName = picName;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return "BannerPicsBean{" +
                    "id='" + id + '\'' +
                    ", originalName='" + originalName + '\'' +
                    ", picName='" + picName + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ", version=" + version +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BannerEntity{" +
                "bannerType='" + bannerType + '\'' +
                ", id='" + id + '\'' +
                ", version=" + version +
                ", bannerPics=" + bannerPics +
                '}';
    }
}
