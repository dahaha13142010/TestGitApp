package com.ronghan.testgitapp.been;

import com.admin.control.quickRv.inter.RvQuickInterface;

import java.io.Serializable;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/17 0017 15:28
 * 修改人：Michael
 * 修改时间：2016/5/17 0017 15:28
 * 修改备注：
 */
public class MessageEntity implements Serializable ,RvQuickInterface{

    @Override
    public int getRvType() {
        return 0;
    }
    /**
     * content : 这是一个推送消息，你只需要认真看
     * endDate : 2016-05-27 00:00:00
     * id : 4028813454b84f620154b89165bf000f
     * startDate : 2016-05-05 00:00:00
     * version : 0
     */

    private String content;
    private String endDate;
    private String id;
    private String startDate;
    private int version;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "content='" + content + '\'' +
                ", endDate='" + endDate + '\'' +
                ", id='" + id + '\'' +
                ", startDate='" + startDate + '\'' +
                ", version=" + version +
                '}';
    }
}
