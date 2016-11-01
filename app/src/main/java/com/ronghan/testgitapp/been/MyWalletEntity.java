package com.ronghan.testgitapp.been;

import java.io.Serializable;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/10 0010 15:05
 * 修改人：Michael
 * 修改时间：2016/5/10 0010 15:05
 * 修改备注：
 */
public class MyWalletEntity implements Serializable{

    /**
     * balance : 0.0
     * changedInBalance : 0.0
     * changedOutBalance : 0.0
     * collectionBalance : 0.0
     * consumedBalance : 0.0
     * customerId : 0
     * customerName : 18716325424
     * frozeBalance : 0.0
     * id : 1
     * productBalance : 0.0
     * status : ENABLE
     * version : 0
     * withdrawBalance : 0.0
     */

    private double balance;
    private double changedInBalance;
    private double changedOutBalance;
    private double collectionBalance;
    private double consumedBalance;
    private String customerId;
    private String customerName;
    private double frozeBalance;
    private String id;
    private double productBalance;
    private String status;
    private double withdrawBalance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getChangedInBalance() {
        return changedInBalance;
    }

    public void setChangedInBalance(double changedInBalance) {
        this.changedInBalance = changedInBalance;
    }

    public double getChangedOutBalance() {
        return changedOutBalance;
    }

    public void setChangedOutBalance(double changedOutBalance) {
        this.changedOutBalance = changedOutBalance;
    }

    public double getCollectionBalance() {
        return collectionBalance;
    }

    public void setCollectionBalance(double collectionBalance) {
        this.collectionBalance = collectionBalance;
    }

    public double getConsumedBalance() {
        return consumedBalance;
    }

    public void setConsumedBalance(double consumedBalance) {
        this.consumedBalance = consumedBalance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getFrozeBalance() {
        return frozeBalance;
    }

    public void setFrozeBalance(double frozeBalance) {
        this.frozeBalance = frozeBalance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getProductBalance() {
        return productBalance;
    }

    public void setProductBalance(double productBalance) {
        this.productBalance = productBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getWithdrawBalance() {
        return withdrawBalance;
    }

    public void setWithdrawBalance(double withdrawBalance) {
        this.withdrawBalance = withdrawBalance;
    }

    @Override
    public String toString() {
        return "MyWalletEntity{" +
                "balance=" + balance +
                ", changedInBalance=" + changedInBalance +
                ", changedOutBalance=" + changedOutBalance +
                ", collectionBalance=" + collectionBalance +
                ", consumedBalance=" + consumedBalance +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", frozeBalance=" + frozeBalance +
                ", id='" + id + '\'' +
                ", productBalance=" + productBalance +
                ", status='" + status + '\'' +
                ", withdrawBalance=" + withdrawBalance +
                '}';
    }
}
