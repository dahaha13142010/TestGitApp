package com.ronghan.testgitapp.app.activity_login_etc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.admin.mine.BaseActivity;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.app.main.bank.BindBankActivity;

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
public class RegistActivity3 extends BaseActivity {

//    private String strPhone;
//    private AppUserEntity appUserEntity;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_register_3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        appUserEntity = MSPUtils.getInstance(RegistActivity3.this).getAppUserSp();
//        strPhone = getIntent().getStringExtra(H.TAG_INTENT_PHONE);
//        ABLogUtil.i("strPhone(Intent):" + strPhone);
//        strPhone = appUserEntity.getUserName();
//        ABLogUtil.i("strPhone(appUserEntity):" + strPhone);
    }

    @OnClick({R.id.btn_cancel, R.id.btn_continue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                startActivity(new Intent(RegistActivity3.this, LoginActivity.class));
                break;
            case R.id.btn_continue:
                startActivity(new Intent(RegistActivity3.this, BindBankActivity.class)/*.putExtra(H.TAG_INTENT_PHONE, strPhone)*/);
                break;
        }
    }
}
