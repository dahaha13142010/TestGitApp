package com.ronghan.testgitapp.been;

import java.io.Serializable;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/17 0017 17:24
 * 修改人：Michael
 * 修改时间：2016/5/17 0017 17:24
 * 修改备注：
 */
public class PromotionSearchEntity implements Serializable{

    /**
     * direct : 1
     * directValid : 0
     * id : 4028813454a920290154a98bb1380005
     * indirect : 0
     * indirectValid : 0
     * userName : 18716325424
     * version : 1
     */

    private int direct;
    private int directValid;
    private String id;
    private int indirect;
    private int indirectValid;
    private String userName;
    private int version;

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getDirectValid() {
        return directValid;
    }

    public void setDirectValid(int directValid) {
        this.directValid = directValid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndirect() {
        return indirect;
    }

    public void setIndirect(int indirect) {
        this.indirect = indirect;
    }

    public int getIndirectValid() {
        return indirectValid;
    }

    public void setIndirectValid(int indirectValid) {
        this.indirectValid = indirectValid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "PromotionSearchEntity{" +
                "direct=" + direct +
                ", directValid=" + directValid +
                ", id='" + id + '\'' +
                ", indirect=" + indirect +
                ", indirectValid=" + indirectValid +
                ", userName='" + userName + '\'' +
                ", version=" + version +
                '}';
    }
}
