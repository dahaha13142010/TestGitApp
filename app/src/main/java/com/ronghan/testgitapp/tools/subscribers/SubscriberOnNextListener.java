package com.ronghan.testgitapp.tools.subscribers;

import java.io.UnsupportedEncodingException;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/4/27 0027 17:29
 * 修改人：Michael
 * 修改时间：2016/4/27 0027 17:29
 * 修改备注：
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t) throws UnsupportedEncodingException;
}
