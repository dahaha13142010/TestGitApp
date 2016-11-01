package com.ronghan.testgitapp.been;

import com.admin.control.quickRv.inter.RvQuickInterface;

import java.io.Serializable;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/6/1 0001 11:40
 * 修改人：Michael
 * 修改时间：2016/6/1 0001 11:40
 * 修改备注：
 */
public class ProductionTypeEntity implements Serializable, RvQuickInterface {
    @Override
    public int getRvType() {
        return 0;
    }
    /**
     * createdDate : 1463383852000
     * id : 4028813454b84f620154b879847d000a
     * name : 测试产品类型
     * version : 0
     */

    private long createdDate;
    private String id;
    private String name;
    private int version;

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ProductionTypeEntity{" +
                "createdDate=" + createdDate +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", version=" + version +
                '}';
    }
}
