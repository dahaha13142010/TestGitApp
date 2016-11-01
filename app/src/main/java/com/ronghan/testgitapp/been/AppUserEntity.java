package com.ronghan.testgitapp.been;

import com.admin.control.quickRv.inter.RvQuickInterface;

import java.io.Serializable;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/6 0006 18:17
 * 修改人：Michael
 * 修改时间：2016/5/6 0006 18:17
 * 修改备注：
 */
public class AppUserEntity implements Serializable, RvQuickInterface {


    @Override
    public int getRvType() {
        return 0;
    }

    /**
     * createdDate : 1462522747000
     * customerId : tc20160506249570
     * extensionCode : 579732
     * id : 8a9817535484e701015485261bdd0002
     * isActivation : NO
     * isValidate : NO
     * nowRole : CLERK
     * parentId : {"accountName":"测试人","bankAccount":"1111111111111111","bankName":"招商","createdDate":1462519166000,"customerId":"tc20160506133259","extensionCode":"855637","isActivation":"YES","isValidate":"YES","mobilePhone":"18875888888","nowRole":"AGENT","status":"ENABLE","updatedDate":1462519166000,"userId":"8a98175354849b7b015484ef73d80004","userName":"A0000001"}
     * status : ENABLE
     * updatedDate : 1462522747000
     * userId : 8a98175354849b7b015485261a620007
     * userName : 18716325424
     * version : 0
     */
    /**
     * id : ID
     * updatedDate : 修改时间
     * status : 用户状态[ENABLE:启用，DISABLE:禁用，PENDING:待审核]
     * isActivation : 是否激活[YES:激活，NO:未激活]
     * parentId : 上级用户信息
     * idCard : 身份证
     */

    private String id;
    private String accountName;
    private String bankAccount;
    private String bankName;
    private long createdDate;
    private String customerId;
    private String extensionCode;
    private String isActivation;
    private String isValidate;
    private String mobilePhone;
    private String nowRole;

    private String idCard;

    /**
     * accountName : 测试人
     * bankAccount : 1111111111111111
     * bankName : 招商
     * createdDate : 1462519166000
     * customerId : tc20160506133259
     * extensionCode : 855637
     * isActivation : YES
     * isValidate : YES
     * mobilePhone : 18875888888
     * nowRole : AGENT
     * status : ENABLE
     * updatedDate : 1462519166000
     * userId : 8a98175354849b7b015484ef73d80004
     * userName : A0000001
     */

    private AppUserEntity parentId;
    private String status;
    private long updatedDate;
    private String userId;
    private String userName;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getExtensionCode() {
        return extensionCode;
    }

    public void setExtensionCode(String extensionCode) {
        this.extensionCode = extensionCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsActivation() {
        return isActivation;
    }

    public void setIsActivation(String isActivation) {
        this.isActivation = isActivation;
    }

    public String getIsValidate() {
        return isValidate;
    }

    public void setIsValidate(String isValidate) {
        this.isValidate = isValidate;
    }

    public String getNowRole() {
        return nowRole;
    }

    public void setNowRole(String nowRole) {
        this.nowRole = nowRole;
    }

    public AppUserEntity getParentId() {
        return parentId;
    }

    public void setParentId(AppUserEntity parentId) {
        this.parentId = parentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String toString() {
        return "AppUserEntity{" +
                "id='" + id + '\'' +
                ", accountName='" + accountName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", bankName='" + bankName + '\'' +
                ", createdDate=" + createdDate +
                ", customerId='" + customerId + '\'' +
                ", extensionCode='" + extensionCode + '\'' +
                ", isActivation='" + isActivation + '\'' +
                ", isValidate='" + isValidate + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", nowRole='" + nowRole + '\'' +
                ", parentId=" + parentId +
                ", status='" + status + '\'' +
                ", updatedDate=" + updatedDate +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
