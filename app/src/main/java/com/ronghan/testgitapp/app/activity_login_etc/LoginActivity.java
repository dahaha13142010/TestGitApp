package com.ronghan.testgitapp.app.activity_login_etc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.app.main.MainActivity;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.been.LoginEntity;
import com.ronghan.testgitapp.been.MyWalletEntity;
import com.ronghan.testgitapp.http.HttpMethods;
import com.ronghan.testgitapp.tools.MSPUtils;
import com.ronghan.testgitapp.tools.TextTools;
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
public class LoginActivity extends BaseActivity {

    private SubscriberOnNextListener loginOnNestListener,queryAppUserListener,walletSearchListener;
    private LoginEntity loginEntity;
    private AppUserEntity appUser;
    private MyWalletEntity myWalletEntity;
    @Bind(R.id.et_account)
    EditText etAccount;
    @Bind(R.id.et_pwd_confirm)
    EditText etPwd;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FIXME debug用
        etAccount.setText("18716325424");
        etPwd.setText("123456");

        loginOnNestListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object s) {
                loginEntity = JSON.parseObject(TextTools.DataDecode(s.toString()), LoginEntity.class);
                MSPUtils.getInstance(LoginActivity.this).setLoginSp(loginEntity);
                JSONObject jO = new JSONObject();
                jO.put("userId", loginEntity.id);
                httpQueryUser(jO);
            }

        };
        queryAppUserListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object s) {
                appUser = JSON.parseObject(TextTools.DataDecode(s.toString()), AppUserEntity.class);
                MSPUtils.getInstance(LoginActivity.this).setAppUserSp(appUser);
                JSONObject jO = new JSONObject();
                jO.put("userId", loginEntity.id);
                httpWalletSearch(jO);
            }
        };
        walletSearchListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                myWalletEntity = JSON.parseObject(TextTools.DataDecode(o.toString()), MyWalletEntity.class);
                MSPUtils.getInstance(LoginActivity.this).setMyWalletSp(myWalletEntity);
                showToast("登陆成功");
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        };

    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.t_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (TextUtils.isEmpty(etAccount.getText())) {
                    showToast("请输入用户名！");
                } else if (TextUtils.isEmpty(etPwd.getText())) {
                    showToast("请输入密码！");
                } else {
//                    C.openProgressDialog(this, null, "正在登录");
                    JSONObject jO = new JSONObject();
                    jO.put("username", etAccount.getText().toString());
                    jO.put("password", etPwd.getText().toString());
                    httpLogin(jO);
                }
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegistActivity1.class));
                finish();
                break;
            case R.id.t_forget:
                startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
                finish();
                break;
        }
    }

    /**
     * 登录
     *
     * @param jO 用户名：username 密码：password
     */
    private void httpLogin(JSONObject jO) {
        HttpMethods.getInstance().login(new ProgressSubscriber(loginOnNestListener, LoginActivity.this, "正在登录…", true), jO);
    }


    /**
     * 查询appUser
     *
     * @param jO userId : 登录成功后的（id）
     */
    private void httpQueryUser(JSONObject jO) {
        HttpMethods.getInstance().appUserShow(new ProgressSubscriber(queryAppUserListener, LoginActivity.this, "正在查询用户信息…", false), jO);
    }


    /**
     * http钱包查询
     *
     * @param jO userId : 当前登录者（id）
     */
    private void httpWalletSearch(JSONObject jO) {
        HttpMethods.getInstance().walletSearch(new ProgressSubscriber(walletSearchListener, LoginActivity.this, "正在查询钱包信息…", false), jO);
    }
}
