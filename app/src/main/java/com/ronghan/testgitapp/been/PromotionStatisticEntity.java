package com.ronghan.testgitapp.been;

import java.io.Serializable;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/16 0016 15:29
 * 修改人：Michael
 * 修改时间：2016/5/16 0016 15:29
 * 修改备注：
 */
public class PromotionStatisticEntity implements Serializable {

    /**
     * bossDirect : 0
     * bossIndirect : 0
     * clerkDirect : 1
     * clerkIndirect : 0
     * customerId : 486747
     * shopOwnerDirect : 0
     * shopOwnerIndirect : 0
     */

    private int bossDirect;
    private int bossIndirect;
    private int clerkDirect;
    private int clerkIndirect;
    private String customerId;
    private int shopOwnerDirect;
    private int shopOwnerIndirect;

    public int getBossDirect() {
        return bossDirect;
    }

    public void setBossDirect(int bossDirect) {
        this.bossDirect = bossDirect;
    }

    public int getBossIndirect() {
        return bossIndirect;
    }

    public void setBossIndirect(int bossIndirect) {
        this.bossIndirect = bossIndirect;
    }

    public int getClerkDirect() {
        return clerkDirect;
    }

    public void setClerkDirect(int clerkDirect) {
        this.clerkDirect = clerkDirect;
    }

    public int getClerkIndirect() {
        return clerkIndirect;
    }

    public void setClerkIndirect(int clerkIndirect) {
        this.clerkIndirect = clerkIndirect;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getShopOwnerDirect() {
        return shopOwnerDirect;
    }

    public void setShopOwnerDirect(int shopOwnerDirect) {
        this.shopOwnerDirect = shopOwnerDirect;
    }

    public int getShopOwnerIndirect() {
        return shopOwnerIndirect;
    }

    public void setShopOwnerIndirect(int shopOwnerIndirect) {
        this.shopOwnerIndirect = shopOwnerIndirect;
    }
}
