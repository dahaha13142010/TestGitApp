package com.admin.mine;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.admin.control.quickRv.RvQuick.RvQuick;
import com.bumptech.glide.Glide;

import butterknife.ButterKnife;


/**
 * 类描述：BaseActivity Activity父类
 * 创建人：Michael
 * 创建时间：2015/12/15 9:40
 * 修改人：Michael
 * 修改时间：2015/12/15 9:40
 * 修改备注：
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 设置是否退出APP
     *
     * @param exit
     */
    private boolean isExit = false;

    public void setExit(boolean exit) {
        isExit = exit;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        if (getLayoutResID() != -1) {
            setContentView(getLayoutResID());
            ButterKnife.bind(this);
        }
        RvQuick.init(new RvQuick.QuickLoad() {
            @Override
            public void load(Context context, String url, ImageView view) {
                Glide.with(context).load(url).into(view);
//                view.setScaleType(ImageView.ScaleType.FIT_XY);
//                Picasso.with(context).load(url).into(view);
            }
        });
    }

    /**
     * 获取布局文件id
     *
     * @return
     */
    protected abstract int getLayoutResID();

    /**
     * 显示短提示
     *
     * @param msg
     */
    protected void showToast(String msg) {
        MApplication.showToast(this, msg + "");
    }

    /**
     * 获取资源字符串
     *
     * @param resId
     * @return
     */
    protected String getResString(int resId) {
        return getResources().getString(resId) + "";
    }

    /**
     * 获取资源字符串
     *
     * @param resId
     * @return
     */
    protected int getResColor(int resId) {
        return getResources().getColor(resId);
    }

    private long exitTime = 2000;

    /**
     * 监听回退键并处理（isExit 是否直接退出APP）
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MApplication.closeProgressDialog();
        if (isExit && keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                int currentVersion = android.os.Build.VERSION.SDK_INT;
                if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startMain);
                    System.exit(0);
                } else {// android2.1
                    ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                    am.restartPackage(getPackageName());
                }
            }
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }

    /**
     * @param layoutResID
     * @return
     */
    protected View getViewByLayoutId(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null, false);
        return view;
    }

    protected void showProgress(BaseActivity activity) {
        MApplication.openProgressDialog(activity, null, null);
    }

    private void disProgress(BaseActivity activity) {
        MApplication.closeProgressDialog();
    }

    /**
     * 监听activity销毁（释放资源）
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);//解除绑定，官方文档只对fragment做了解绑
    }
}
