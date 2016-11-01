package com.ronghan.testgitapp.been;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/4/27 0027 15:27
 * 修改人：Michael
 * 修改时间：2016/4/27 0027 15:27
 * 修改备注：
 */
public class HttpResult {
    private int code;
    private String errmsg;

    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", errmsg='" + errmsg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
