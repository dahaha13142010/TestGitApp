package com.ronghan.testgitapp.app.activity_login_etc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.constant.H;
import com.ronghan.testgitapp.http.HttpMethods;
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
public class ForgetPwdActivity extends BaseActivity {

    private String strPhone, strYzm;
    @Bind(R.id.rbtn_login_pwd)
    RadioButton rbtnLoginPwd;
    private SubscriberOnNextListener sendMessageListener, checkVerificationCodeListener;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_yzm)
    EditText etYzm;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendMessageListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                showToast("验证码发送成功！");
            }
        };
        checkVerificationCodeListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                startActivity(new Intent(ForgetPwdActivity.this, SetPwdActivity.class)
                        .putExtra(H.TAG_INTENT_PHONE, strPhone)
                        .putExtra(H.TAG_INTENT, rbtnLoginPwd.isChecked() ? H.TAG_INTENT_LOGIN_PWD : H.TAG_INTENT_PAY_PWD));
                finish();
            }
        };
    }

    @OnClick({R.id.tv_get_yzm, R.id.btn_confirm})
    public void onClick(View view) {
        strPhone = etPhone.getText().toString();
        strYzm = etYzm.getText().toString();
        switch (view.getId()) {
            case R.id.tv_get_yzm:
                if (TextUtils.isEmpty(strPhone)) {
                    showToast("请输入手机号码！");
                } else {
                    JSONObject jO = new JSONObject();
                    jO.put("userName", strPhone);
                    httpSendMessage(jO);
                    break;
                }
            case R.id.btn_confirm:
                if (TextUtils.isEmpty(strPhone)) {
                    showToast("请输入手机号码！");
                } else if (TextUtils.isEmpty(strYzm)) {
                    showToast("请输入验证码！");
                } else {
                    JSONObject jO = new JSONObject();
                    jO.put("userName", strPhone);
                    jO.put("verificationCode", strYzm);
                    httpCheckVerificationCode(jO);
                    break;
                }
                break;
        }
    }

    /**
     * http获取验证码
     *
     * @param jO userName ： 账号（手机号码）
     */
    private void httpSendMessage(JSONObject jO) {
        HttpMethods.getInstance().sendMessage(new ProgressSubscriber(sendMessageListener, ForgetPwdActivity.this, "正在获取验证码…", true), jO);
    }

    /**
     * http核对短信码
     *
     * @param jO userName ： 账号（手机号码）
     *           verificationCode ： 验证码
     */
    private void httpCheckVerificationCode(JSONObject jO) {
        HttpMethods.getInstance().checkVerificationCode(new ProgressSubscriber(checkVerificationCodeListener, ForgetPwdActivity.this, "正在获取验证码…", true), jO);
    }
}
