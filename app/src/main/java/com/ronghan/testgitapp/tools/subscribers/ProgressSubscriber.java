package com.ronghan.testgitapp.tools.subscribers;

import android.content.Context;
import android.widget.Toast;

import com.admin.utils.ABLogUtil;
import com.ronghan.testgitapp.tools.progress.ProgressCancelListener;
import com.ronghan.testgitapp.tools.progress.ProgressDialogHandler;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/4/27 0027 17:27
 * 修改人：Michael
 * 修改时间：2016/4/27 0027 17:27
 * 修改备注：
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;
    private boolean isDialog;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context, String message, boolean isDialog) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
        this.isDialog = isDialog;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true, message);
    }

    private void showProgressDialog() {
        if (isDialog){
            if (null != mProgressDialogHandler) {
                mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
            }
        }
    }

    private void dismissProgressDialog() {
        if (isDialog){
            if (null != mProgressDialogHandler) {
                mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
                mProgressDialogHandler = null;
            }
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络太慢啦，再试一次吧！", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网断了，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            ABLogUtil.e(e.getMessage());
        }
        dismissProgressDialog();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (null != mSubscriberOnNextListener) {
            try {
                mSubscriberOnNextListener.onNext(t);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
