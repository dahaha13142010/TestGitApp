package com.ronghan.testgitapp.been;

import com.admin.control.quickRv.inter.RvQuickInterface;

import java.io.Serializable;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/6/3 0003 14:14
 * 修改人：Michael
 * 修改时间：2016/6/3 0003 14:14
 * 修改备注：
 */
public class AddressEntity implements Serializable, RvQuickInterface{

    @Override
    public int getRvType() {
        return 0;
    }

    /**
     * address : 4546464646466464
     * createDate : 1464934232000
     * customerId : 189702
     * id : 40281d81551420e0015514e28bad002a
     * name : 移民
     * status : NO
     * telephone : 18716325424
     * updateDate : 1464934270000
     * userId : 40281d8155142026015514dfe1dc0002
     * userName : 18716325424
     * version : 1
     */

    private String address;
    private long createDate;
    private String customerId;
    private String id;
    private String name;
    private String status;
    private String telephone;
    private long updateDate;
    private String userId;
    private String userName;
    private int version;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        return "AddressEntity{" +
                "address='" + address + '\'' +
                ", createDate=" + createDate +
                ", customerId='" + customerId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", telephone='" + telephone + '\'' +
                ", updateDate=" + updateDate +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", version=" + version +
                '}';
    }

}
