package com.ronghan.testgitapp.app.activity_login_etc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.constant.H;
import com.ronghan.testgitapp.http.HttpMethods;
import com.ronghan.testgitapp.tools.subscribers.ProgressSubscriber;
import com.ronghan.testgitapp.tools.subscribers.SubscriberOnNextListener;

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
public class RegistActivity1 extends BaseActivity {

    private SubscriberOnNextListener registerSendSMSListener, registerCheckSMSListener;

    @Bind(R.id.et_account)
    EditText etAccount;
    @Bind(R.id.et_yzm)
    EditText etYzm;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_register_1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerSendSMSListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) {
                showToast("短信发送成功！");
            }
        };
        registerCheckSMSListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) {
                showToast("验证成功！");
                startActivity(new Intent(RegistActivity1.this, RegistActivity2.class)
                        .putExtra(H.TAG_INTENT_PHONE, etAccount.getText().toString()));
                finish();
            }
        };
//        etAccount.setText("18716325424");
    }

    @OnClick({R.id.tv_get_yzm, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_get_yzm:
                if (TextUtils.isEmpty(etAccount.getText())) {
                    showToast("请输入手机号码！");
                } else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("userName", etAccount.getText());
                    httpSendSMS(jsonObject);
                }
                break;
            case R.id.btn_next:
                if (TextUtils.isEmpty(etAccount.getText())) {
                    showToast("请输入手机号码！");
                } else if (TextUtils.isEmpty(etYzm.getText())) {
                    showToast("请输入验证码！");
                } else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("userName", etAccount.getText());
                    jsonObject.put("verificationCode", etYzm.getText());
                    httpCheckSMS(jsonObject);
                }
                break;
        }
    }

    /**
     * http获取短信
     **/
    private void httpSendSMS(JSONObject jO) {
        HttpMethods.getInstance().sendVerificationCode(new ProgressSubscriber(registerSendSMSListener, RegistActivity1.this, "正在获取…", true), jO);
    }

    /**
     * http验证验证码
     **/
    private void httpCheckSMS(JSONObject jO) {
        HttpMethods.getInstance().checkVerificationCode(new ProgressSubscriber(registerCheckSMSListener, RegistActivity1.this, "正在验证…", true), jO);
    }
}
