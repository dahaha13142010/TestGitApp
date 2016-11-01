package com.admin.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.admin.control.quickRv.RvQuick.RvQuick;
import com.admin.utils.ABLogUtil;
import com.bumptech.glide.Glide;

import butterknife.ButterKnife;


/**
 * 类描述：BaseFragment Fragment父类
 * 创建人：Michael
 * 创建时间：2015/12/15 9:40
 * 修改人：Michael
 * 修改时间：2015/12/15 9:40
 * 修改备注：
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 设置是否退出APP
     *
     * @param exit
     */
    private boolean isNewFragment = false;

    public void setNewFragment(boolean newFragment) {
        isNewFragment = newFragment;
    }

    private boolean injected = false;

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        if (view == null || isNewFragment) {
            view = inflater.inflate(getLayoutResID(), container, false);
            if (injected)
                ButterKnife.bind(this, view);
            onBaseCreate(view);
        } else if (view.getParent() != null) {
            ViewGroup vp = (ViewGroup) view.getParent();
            vp.removeAllViews();
        }
        RvQuick.init(new RvQuick.QuickLoad() {
            @Override
            public void load(Context context, String url, ImageView view) {
                Glide.with(context).load(url).into(view);
//                view.setScaleType(ImageView.ScaleType.FIT_XY);
//                Picasso.with(context).load(url).into(view);
            }
        });
        return view;
    }

    /**
     * 获取布局文件id
     *
     * @return
     */
    protected abstract int getLayoutResID();

    /**
     * 自定义Fragment初始化方法
     *
     * @param view
     * @return View
     */
    protected abstract void onBaseCreate(View view);

    /**
     * 在view被创建后调用
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 显示短提示
     *
     * @param msg
     */
    protected void showToast(String msg) {
        MApplication.showToast(getActivity(), msg + "");
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

    protected void showProgress(Context context) {
        ABLogUtil.w("context:" + context);
        MApplication.openProgressDialog(getActivity(), null, null);
    }

    protected void disProgress(BaseFragment frg) {
        MApplication.closeProgressDialog();
    }

    /**
     * 监听activity销毁（释放资源）
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);//解除绑定，官方文档只对fragment做了解绑
    }

}
