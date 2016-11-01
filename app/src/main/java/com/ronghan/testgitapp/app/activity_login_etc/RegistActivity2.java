package com.ronghan.testgitapp.app.activity_login_etc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.constant.H;
import com.ronghan.testgitapp.http.HttpMethods;
import com.ronghan.testgitapp.tools.MSPUtils;
import com.ronghan.testgitapp.tools.TextTools;
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
public class RegistActivity2 extends BaseActivity {

    private SubscriberOnNextListener registerListener;
    private String strPhone;
    private AppUserEntity registerEntity;

    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.et_pwd_confirm)
    EditText etPwdConfirm;
    @Bind(R.id.et_extension)
    EditText etExtension;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_register_2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        strPhone = getIntent().getStringExtra(H.TAG_INTENT_PHONE);
        //FIXME debug
        etExtension.setText("486747");
        registerListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) {
                registerEntity = JSON.parseObject(TextTools.DataDecode(o.toString()), AppUserEntity.class);
                MSPUtils.getInstance(RegistActivity2.this).setAppUserSp(registerEntity);
                startActivity(new Intent(RegistActivity2.this, RegistActivity3.class).putExtra(H.TAG_INTENT_PHONE, strPhone));
            }
        };
    }

    @OnClick(R.id.btn_register)
    public void onClick() {
        if (TextUtils.isEmpty(etPwd.getText())) {
            showToast("请输入密码！");
        } else if (TextUtils.isEmpty(etPwdConfirm.getText())) {
            showToast("请输入邀请码！");
        } else if (TextUtils.isEmpty(etExtension.getText())) {
            showToast("请确认密码！");
        } else if (!TextUtils.equals(etPwd.getText(), etPwdConfirm.getText())) {
            showToast("两次输入密码不一致，请重新输入！");
        } else {
            JSONObject jO = new JSONObject();
            jO.put("userName", strPhone);
            jO.put("password", etPwd.getText());
            jO.put("extensionCode", etExtension.getText());
            httpRegister(jO);
        }
    }


    /**
     * http注册
     **/
    private void httpRegister(JSONObject jO) {
        HttpMethods.getInstance().register(new ProgressSubscriber(registerListener, RegistActivity2.this, "正在注册…", true), jO);
    }
}
