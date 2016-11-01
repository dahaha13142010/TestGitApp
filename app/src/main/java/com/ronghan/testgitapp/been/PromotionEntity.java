package com.ronghan.testgitapp.been;

import com.admin.control.quickRv.inter.RvQuickInterface;

import java.io.Serializable;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/17 0017 16:31
 * 修改人：Michael
 * 修改时间：2016/5/17 0017 16:31
 * 修改备注：
 */
public class PromotionEntity implements Serializable, RvQuickInterface {
    /**
     * id : 40281d8154a7c5430154a7d482490005
     * paramKey : clerkNumber
     * paramValue : 3
     * version : 0
     */

    private String id;
    private String paramKey;
    private String paramValue;
    private int version;

    @Override
    public int getRvType() {
        return 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "PromotionEntity{" +
                "id='" + id + '\'' +
                ", paramKey='" + paramKey + '\'' +
                ", paramValue='" + paramValue + '\'' +
                ", version=" + version +
                '}';
    }
}
