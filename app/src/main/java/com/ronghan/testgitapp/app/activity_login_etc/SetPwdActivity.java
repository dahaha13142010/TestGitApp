package com.ronghan.testgitapp.app.activity_login_etc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.admin.control.topTitleBackBar.TopTitleBackBar;
import com.admin.mine.BaseActivity;
import com.admin.utils.ABLogUtil;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.app.main.MainActivity;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.constant.H;
import com.ronghan.testgitapp.http.HttpMethods;
import com.ronghan.testgitapp.tools.MSPUtils;
import com.ronghan.testgitapp.tools.subscribers.ProgressSubscriber;
import com.ronghan.testgitapp.tools.subscribers.SubscriberOnNextListener;

import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/4/25 0025 11:26
 * 修改人：Michael
 * 修改时间：2016/4/25 0025 11:26
 * 修改备注：
 */
public class SetPwdActivity extends BaseActivity {

    private SubscriberOnNextListener resetLoginPwdListener, resetPayPwdListener;
//    private String strPhone, strSkip;
    private String strSkip;
    private AppUserEntity appUserEntity;
    @Bind(R.id.title)
    TopTitleBackBar title;
    @Bind(R.id.tv_phone_2)
    TextView tvPhone2;
    @Bind(R.id.btn_next)
    Button btnNext;
    @Bind(R.id.et_pad_pwd)
    EditText etPadPwd;
    @Bind(R.id.et_pay_pwd_repeat)
    EditText etPayPwdRepeat;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_set_pwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appUserEntity = MSPUtils.getInstance(SetPwdActivity.this).getAppUserSp();
//        strPhone = getIntent().getStringExtra(H.TAG_INTENT_PHONE);
        strSkip = getIntent().getStringExtra(H.TAG_INTENT);
        ABLogUtil.e("appUserEntity.getUserName: " + appUserEntity.getUserName() + "----strShip: " + strSkip);
        switch (strSkip) {
            case H.TAG_INTENT_LOGIN_PWD:
                title.setTitle(R.string.login_pwd);
                btnNext.setText(R.string.reset_confirm);
                resetLoginPwdListener = new SubscriberOnNextListener() {
                    @Override
                    public void onNext(Object o) throws UnsupportedEncodingException {
                        showToast("设置成功，请重新登陆！");
                        startActivity(new Intent(SetPwdActivity.this, LoginActivity.class));
                        finish();
                    }
                };
                break;
            case H.TAG_INTENT_PAY_PWD:
                btnNext.setText(R.string.reset_confirm);
            case H.TAG_INTENT_REGISTER:
                resetPayPwdListener = new SubscriberOnNextListener() {
                    @Override
                    public void onNext(Object o) throws UnsupportedEncodingException {
                        showToast("设置成功！");
                        startActivity(new Intent(SetPwdActivity.this, MainActivity.class));
                        finish();
                    }
                };
                break;
        }
    }

    @OnClick(R.id.btn_next)
    public void onClick() {
        JSONObject jO = new JSONObject();
        if (TextUtils.isEmpty(etPadPwd.getText())) {
            showToast("请输入密码！");
        } else if (TextUtils.isEmpty(etPayPwdRepeat.getText())) {
            showToast("请确认密码！");
        } else if (!TextUtils.equals(etPadPwd.getText(), etPayPwdRepeat.getText())) {
            showToast("两次输入密码不一致，请重新输入！");
        } else {
            if (TextUtils.equals(H.TAG_INTENT_LOGIN_PWD, strSkip)) {
                jO.put("userName", appUserEntity.getUserName());
                jO.put("password", etPadPwd.getText());
                httpResetLoginPwd(jO);
            } else {
                jO.put("userName", appUserEntity.getUserName());
                jO.put("payPassword", etPadPwd.getText());
                httpResetPayPwd(jO);
            }
        }
    }

    /**
     * 重置登陆密码
     *
     * @param jO userName ： 账号（手机号码）
     *           password ： 密码
     */
    private void httpResetLoginPwd(JSONObject jO) {
        HttpMethods.getInstance().resetPassword(new ProgressSubscriber(resetLoginPwdListener, SetPwdActivity.this, "正在设置密码…", true), jO);
    }

    /**
     * 设置和重置支付密码
     *
     * @param jO userId : 当前登录用户ID
     *           payPassword : 支付密码
     */
    private void httpResetPayPwd(JSONObject jO) {
        HttpMethods.getInstance().editPayPassword(new ProgressSubscriber(resetPayPwdListener, SetPwdActivity.this, "正在设置密码…", true), jO);
    }
}
