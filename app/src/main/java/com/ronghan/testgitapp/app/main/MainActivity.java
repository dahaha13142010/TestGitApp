package com.ronghan.testgitapp.app.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.app.fragment_home.FragHome;
import com.ronghan.testgitapp.app.fragment_mall.FragMall;
import com.ronghan.testgitapp.app.fragment_phone.FragPhone;
import com.ronghan.testgitapp.app.fragment_promotion.FragPromotion;
import com.ronghan.testgitapp.app.fragment_promotion.ShareEwmActivity;
import com.ronghan.testgitapp.app.main.activate.ActivateActivity;
import com.ronghan.testgitapp.app.main.bank.BindBankActivity;
import com.ronghan.testgitapp.app.main.bank.BindSucceedActivity;
import com.ronghan.testgitapp.app.main.bank.BindingActivity;
import com.ronghan.testgitapp.app.main.message.MessageActivity;
import com.ronghan.testgitapp.app.main.my_promotion.MyPromotionActivity;
import com.ronghan.testgitapp.app.main.my_wallet.MyWalletActivity;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.been.LoginEntity;
import com.ronghan.testgitapp.been.MyWalletEntity;
import com.ronghan.testgitapp.constant.H;
import com.ronghan.testgitapp.http.HttpMethods;
import com.ronghan.testgitapp.tools.MSPUtils;
import com.ronghan.testgitapp.tools.TextTools;
import com.ronghan.testgitapp.tools.subscribers.ProgressSubscriber;
import com.ronghan.testgitapp.tools.subscribers.SubscriberOnNextListener;

import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private FragHome fragHome;
    private FragPhone fragPhone;
    private FragMall fragMall;
    private FragPromotion fragPromotion;
    private Fragment mContent;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private SubscriberOnNextListener logoutListener, queryAppUserListener, walletSearchListener;


    private MyWalletEntity myWalletEntity;
    private AppUserEntity appUser;
    private LoginEntity loginEntity;

    @Bind(R.id.tv_level)
    TextView tvLevel;
    @Bind(R.id.tv_phone_2)
    TextView tvPhone;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.tv_cumulative_collection)
    TextView tvCumulativeCollection;
    @Bind(R.id.tv_my_wallet)
    TextView tvMyWallet;
    @Bind(R.id.tv_verified)
    TextView tvVerified;
    @Bind(R.id.tv_shop_activate)
    TextView tvShopActivate;
    @Bind(R.id.iv_new)
    ImageView ivNew;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setExit(true);//是否需要直接退出程序
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mFragmentManager = this.getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        fragHome = new FragHome();
        fragPhone = new FragPhone();
        fragMall = new FragMall();
        fragPromotion = new FragPromotion();
        mFragmentTransaction.add(R.id.fragment, fragHome).commit();
        mContent = fragHome;

        logoutListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object s) {
                MSPUtils.getInstance(MainActivity.this).setLogout();
                showToast("已注销登录！");
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MSPUtils.getInstance(MainActivity.this).isLogin()) {
            loginEntity = MSPUtils.getInstance(MainActivity.this).getLoginSp();
            queryAppUserListener = new SubscriberOnNextListener() {
                @Override
                public void onNext(Object s) {
                    appUser = JSON.parseObject(TextTools.DataDecode(s.toString()), AppUserEntity.class);
                    MSPUtils.getInstance(MainActivity.this).setAppUserSp(appUser);
                    tvLevel.setText(TextTools.getLevel(appUser.getNowRole()));
                    tvPhone.setText(appUser.getUserName());
                    tvShopActivate.setVisibility(TextUtils.equals("YES", appUser.getIsActivation()) ? View.INVISIBLE : View.VISIBLE);
                    tvVerified.setText(TextTools.getVerify(appUser.getIsValidate()) );
                }
            };

            walletSearchListener = new SubscriberOnNextListener() {
                @Override
                public void onNext(Object o) throws UnsupportedEncodingException {
                    myWalletEntity = JSON.parseObject(TextTools.DataDecode(o.toString()), MyWalletEntity.class);
                    MSPUtils.getInstance(MainActivity.this).setMyWalletSp(myWalletEntity);
                    tvMyWallet.setText("￥：" + String.valueOf(myWalletEntity.getBalance()));
                }
            };
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MSPUtils.getInstance(MainActivity.this).setLogout();
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.rl_bank_account, R.id.rl_activate, R.id.rl_promotion, R.id.rl_message,
            R.id.rl_guide, R.id.rl_customer_services, R.id.rl_feedback, R.id.btn_logout,
            R.id.rbtn_home, R.id.rbtn_phone, R.id.rbtn_mall, R.id.rbtn_promotion,
            R.id.iv_user_ewm, R.id.ll_cumulative_collection, R.id.ll_my_wallet})
    public void onClick(View view) {
        drawerLayout.closeDrawer(Gravity.LEFT);
        switch (view.getId()) {
            /** 抽屉title **/
            case R.id.iv_user_ewm:
                startActivity(new Intent(MainActivity.this, ShareEwmActivity.class));
                break;
            case R.id.ll_cumulative_collection:
                //FIXME
                showToast("尽情期待");
//                startActivity(new Intent(MainActivity.this, BindBankActivity.class));
                break;
            case R.id.ll_my_wallet:
                startActivity(new Intent(MainActivity.this, MyWalletActivity.class));
                break;
            /** 抽屉item **/
            case R.id.rl_bank_account:
                switch (appUser.getIsValidate()){
                    case H.VERIFIED:
                        startActivity(new Intent(MainActivity.this, BindSucceedActivity.class));
                        break;
                    case H.VERIFICATION_IN:
                        startActivity(new Intent(MainActivity.this, BindingActivity.class));
                        break;
                    case H.NOT_VERIFIED:
                        startActivity(new Intent(MainActivity.this, BindBankActivity.class));
                        break;
                    case H.VALIDATION_FAILURE:
                        startActivity(new Intent(MainActivity.this, BindBankActivity.class));
                        break;
                }
                break;
            case R.id.rl_activate:
                startActivity(new Intent(MainActivity.this, ActivateActivity.class));
                break;
            case R.id.rl_promotion:
                startActivity(new Intent(MainActivity.this, MyPromotionActivity.class));
                break;
            case R.id.rl_message:
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
                break;
            case R.id.rl_guide:
                break;
            case R.id.rl_customer_services:
                break;
            case R.id.rl_feedback:
                break;
            case R.id.btn_logout:
                MSPUtils.getInstance(MainActivity.this).setLogout();
                httpLogout();
                break;
            /** 导航栏 **/
            case R.id.rbtn_home:
                switchContent(mContent, fragHome);
                break;
            case R.id.rbtn_phone:
                switchContent(mContent, fragPhone);
                break;
            case R.id.rbtn_mall:
                switchContent(mContent, fragMall);
                break;
            case R.id.rbtn_promotion:
                switchContent(mContent, fragPromotion);
                break;
        }
    }

    /**
     * 切换fragment
     *
     * @param from
     * @param to
     */
    public void switchContent(Fragment from, Fragment to) {
        if (mContent != to) {
            mContent = to;
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.setCustomAnimations(R.anim.right_in,
                    R.anim.left_out);
            mFragmentTransaction.replace(R.id.fragment, to)
                    .commit();
        }
    }

    public void openDrawerAtLogin() {
        http();
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    public void http(){
        if (MSPUtils.getInstance(this).isLogin()) {
            JSONObject jO = new JSONObject();
            jO.put("userId", loginEntity.id);
            httpQueryUser(jO);
            httpWalletSearch(jO);
        }
    }

    public void closeDrawer() {
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    /**
     * 登出
     **/
    private void httpLogout() {
        HttpMethods.getInstance().logout(new ProgressSubscriber(logoutListener, MainActivity.this, "正在注销登录…", true));
    }



    /**
     * 查询appUser
     *
     * @param jO userId : 登录成功后的（id）
     */
    private void httpQueryUser(JSONObject jO) {
        HttpMethods.getInstance().appUserShow(new ProgressSubscriber(queryAppUserListener, MainActivity.this, "正在查询用户信息…", false), jO);
    }

    /**
     * http钱包查询
     *
     * @param jO userId : 当前登录者（id）
     */
    private void httpWalletSearch(JSONObject jO) {
        HttpMethods.getInstance().walletSearch(new ProgressSubscriber(walletSearchListener, MainActivity.this, "正在查询钱包信息…", false), jO);
    }
}
