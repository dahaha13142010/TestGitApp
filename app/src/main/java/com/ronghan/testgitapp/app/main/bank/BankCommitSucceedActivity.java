package com.ronghan.testgitapp.app.main.bank;

import android.content.Intent;
import android.os.Bundle;

import com.admin.mine.BaseActivity;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.app.activity_login_etc.SetPwdActivity;
import com.ronghan.testgitapp.constant.H;

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
public class BankCommitSucceedActivity extends BaseActivity {

//    String strPhone;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_bank_binding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        strPhone = getIntent().getStringExtra(H.TAG_INTENT_PHONE);
    }

    @OnClick(R.id.btn_known)
    public void onClick() {
        startActivity(new Intent(BankCommitSucceedActivity.this, SetPwdActivity.class)
                .putExtra(H.TAG_INTENT, H.TAG_INTENT_REGISTER)
                /*.putExtra(H.TAG_INTENT_PHONE, strPhone)*/);
    }
}
