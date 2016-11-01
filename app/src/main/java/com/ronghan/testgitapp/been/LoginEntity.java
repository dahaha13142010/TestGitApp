package com.ronghan.testgitapp.been;

import java.io.Serializable;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/4/27 0027 15:02
 * 修改人：Michael
 * 修改时间：2016/4/27 0027 15:02
 * 修改备注：
 */
public class LoginEntity implements Serializable{
    /**
     * 用户标识:id
     * 用户版本号:version
     * 用户名:username
     */
    public String id;
    public String username;

    public LoginEntity() {
    }

    public LoginEntity(String id,  String username) {
        this.id = id;
        this.username = username;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
