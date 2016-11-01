package com.ronghan.testgitapp.been;

import com.admin.control.quickRv.inter.RvQuickInterface;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/16 0016 18:04
 * 修改人：Michael
 * 修改时间：2016/5/16 0016 18:04
 * 修改备注：
 */
public class IcoEntity implements Serializable, RvQuickInterface {

    @Override
    public int getRvType() {
        return 0;
    }
    /**
     * icoPics : [{"id":"40281d8154a7c5430154a7da64830015","originalName":"1463104985271.jpg","picName":"300.500.1463104985271.jpg","picUrl":"http://192.168.199.133:8888/img/300.500.1463104985271.jpg","version":0}]
     * id : 40281d8154a7c5430154a7da64820014
     * name : 收款
     * priority : 2
     * status : YES
     * url : http://www.sougou.com
     * version : 2
     */

    private String id;
    private String name;
    private int priority;
    private String status;
    private String url;
    private int version;
    /**
     * id : 40281d8154a7c5430154a7da64830015
     * originalName : 1463104985271.jpg
     * picName : 300.500.1463104985271.jpg
     * picUrl : http://192.168.199.133:8888/img/300.500.1463104985271.jpg
     * version : 0
     */

    private List<IcoPicsBean> icoPics;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<IcoPicsBean> getIcoPics() {
        return icoPics;
    }

    public void setIcoPics(List<IcoPicsBean> icoPics) {
        this.icoPics = icoPics;
    }

    public static class IcoPicsBean {
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
            return "IcoPicsBean{" +
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
        return "IcoEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", status='" + status + '\'' +
                ", url='" + url + '\'' +
                ", version=" + version +
                ", icoPics=" + icoPics.toString() +
                '}';
    }
}
