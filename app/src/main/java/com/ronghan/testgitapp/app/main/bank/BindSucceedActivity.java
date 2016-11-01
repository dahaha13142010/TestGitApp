package com.ronghan.testgitapp.app.main.bank;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.admin.mine.BaseActivity;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.tools.MSPUtils;
import com.ronghan.testgitapp.tools.TextTools;

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
public class BindSucceedActivity extends BaseActivity {

    private AppUserEntity appUserEntity;
    @Bind(R.id.tv_account)
    TextView tvAccount;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_id_card_number)
    TextView tvIdCardNumber;
    @Bind(R.id.tv_bank_card_number)
    TextView tvBankCardNumber;
    @Bind(R.id.tv_bank)
    TextView tvBank;
    @Bind(R.id.tv_audit)
    TextView tvAudit;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_binded_card;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appUserEntity = MSPUtils.getInstance(BindSucceedActivity.this).getAppUserSp();
        tvAccount.setText(TextTools.hidePhone(appUserEntity.getUserName()));
        tvName.setText(TextTools.hideName(appUserEntity.getAccountName()));
        tvIdCardNumber.setText(TextTools.hideIdCard(appUserEntity.getIdCard()));
        tvBankCardNumber.setText(TextTools.hideBank(appUserEntity.getBankAccount()));
        tvBank.setText(appUserEntity.getBankName());
        tvAudit.setText(R.string.bd_audit_yes);
    }

    @OnClick(R.id.btn_modify)
    public void onClick() {
        startActivity(new Intent(BindSucceedActivity.this, BindBankActivity.class));
    }
}
