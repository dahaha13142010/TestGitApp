package com.admin.mine;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.admin.R;
import com.admin.control.dialog.CustomProgressDialog;
import com.admin.utils.ABFileUtil;


/**
 * 项目名称：dyb-app-android
 * 类描述：主Application
 * 创建人：Michael
 * 创建时间：2015/10/27 16:42
 * 修改人：Michael
 * 修改时间：2015/10/27 16:42
 * 修改备注：
 */
public class MApplication extends Application {
    private static boolean isGetLog = true;

    public static boolean getIsGetLog() {
        return isGetLog;
    }

    public void setIsGetLog(boolean isGetLog) {
        MApplication.isGetLog = isGetLog;
    }

    public void onCreate() {
        super.onCreate();
        initAll(this);
    }

    /**
     * ========================================================================================================================================
     */
    public static void initAll(MApplication application) {
        ABFileUtil.initFileDir(application);//初始化缓存文件夹
        if (getIsGetLog())
            CrashHandler.getInstance().initCrash(application);// 自定义捕获程序崩溃日志
    }

    /**
     * ===============================================================================================================================================
     */
    /**
     * 自定义等待提示弹出框
     */
    public static class MyProgressDialog extends ProgressDialog {
        public MyProgressDialog(Context context) {
            super(context);
        }

        @Override
        public boolean onKeyUp(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                this.dismiss();
                return true;
            }
            return super.onKeyUp(keyCode, event);
        }

        @Override
        public void setView(View view) {
            super.setView(view);
        }
    }

    /**
     * 自定义Loading
     */
    public static CustomProgressDialog myProgerssDialog = null;

    /**
     * 打开Loading
     */
    public static void openProgressDialog(Activity activity, DialogInterface.OnKeyListener onKeyListener, String message) {
        if (myProgerssDialog != null) {
            closeProgressDialog();
        }
        myProgerssDialog = new CustomProgressDialog(activity, R.style.dialog).createDialog(activity);
        myProgerssDialog.setCanceledOnTouchOutside(false);
        myProgerssDialog.setCancelable(true);
        if (message != null && !message.equals("")) {
            myProgerssDialog.setMessage(message);
        }
        if (onKeyListener != null)
            myProgerssDialog.setOnKeyListener(onKeyListener);
        if (!activity.isFinishing())
            myProgerssDialog.show();
    }

    /**
     * 关闭Loading
     */
    public static void closeProgressDialog() {
        if (myProgerssDialog != null && myProgerssDialog.isShowing()) {
            myProgerssDialog.dismiss();
            myProgerssDialog = null;
        }
    }

    /**
     * 自定义Toast
     */
    private static Toast toast = null;

    public static void showToast(Context context, String msg) {
        showToast(context, msg, false);
    }

    public static void showToast(Context context, String msg, boolean isShowLongTime) {
        if (toast == null) {
                toast = Toast.makeText(context.getApplicationContext(), msg, isShowLongTime? Toast.LENGTH_LONG:Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
