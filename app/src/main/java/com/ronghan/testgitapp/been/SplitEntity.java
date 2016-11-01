package com.ronghan.testgitapp.been;

import com.admin.control.quickRv.inter.RvQuickInterface;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/12 0012 14:22
 * 修改人：Michael
 * 修改时间：2016/5/12 0012 14:22
 * 修改备注：
 */
public class SplitEntity implements Serializable {

    /**
     * count : 1
     * data : [{"amount":10,"createdDate":1463034003000,"customerId":"0","customerName":"18716325424","fundFlow":"INCOME","id":"1","orderId":"123","orderType":"PRODUCT","rate":0.02,"remark":"产品分润","transactAmount":500,"type":"PRODUCT_COLLECTION","version":0,"walletChangeAmount":10,"walletRemainingAmount":60}]
     * page : 1
     * pageSize : 10
     */

    private int count;
    private int page;
    private int pageSize;
    /**
     * amount : 10
     * createdDate : 1463034003000
     * customerId : 0
     * customerName : 18716325424
     * fundFlow : INCOME
     * id : 1
     * orderId : 123
     * orderType : PRODUCT
     * rate : 0.02
     * remark : 产品分润
     * transactAmount : 500
     * type : PRODUCT_COLLECTION
     * version : 0
     * walletChangeAmount : 10
     * walletRemainingAmount : 60
     */

    private List<DataBean> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements RvQuickInterface {

        @Override
        public int getRvType() {
            return 0;
        }

        private int amount;
        private String createdDate;
        private String customerId;
        private String customerName;
        private String fundFlow;
        private String id;
        private String orderId;
        private String orderType;
        private double rate;
        private String remark;
        private int transactAmount;
        private String type;
        private int version;
        private int walletChangeAmount;
        private int walletRemainingAmount;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
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

        public String getFundFlow() {
            return fundFlow;
        }

        public void setFundFlow(String fundFlow) {
            this.fundFlow = fundFlow;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getTransactAmount() {
            return transactAmount;
        }

        public void setTransactAmount(int transactAmount) {
            this.transactAmount = transactAmount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getWalletChangeAmount() {
            return walletChangeAmount;
        }

        public void setWalletChangeAmount(int walletChangeAmount) {
            this.walletChangeAmount = walletChangeAmount;
        }

        public int getWalletRemainingAmount() {
            return walletRemainingAmount;
        }

        public void setWalletRemainingAmount(int walletRemainingAmount) {
            this.walletRemainingAmount = walletRemainingAmount;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "amount=" + amount +
                    ", createdDate=" + createdDate +
                    ", customerId='" + customerId + '\'' +
                    ", customerName='" + customerName + '\'' +
                    ", fundFlow='" + fundFlow + '\'' +
                    ", id='" + id + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", orderType='" + orderType + '\'' +
                    ", rate=" + rate +
                    ", remark='" + remark + '\'' +
                    ", transactAmount=" + transactAmount +
                    ", type='" + type + '\'' +
                    ", version=" + version +
                    ", walletChangeAmount=" + walletChangeAmount +
                    ", walletRemainingAmount=" + walletRemainingAmount +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SplitEntity{" +
                "count=" + count +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", data=" + data.toString() +
                '}';
    }
}
