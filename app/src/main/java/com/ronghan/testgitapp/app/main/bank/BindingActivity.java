package com.ronghan.testgitapp.app.main.bank;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.admin.mine.BaseActivity;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.tools.MSPUtils;
import com.ronghan.testgitapp.tools.TextTools;

import butterknife.Bind;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/4/25 0025 11:26
 * 修改人：Michael
 * 修改时间：2016/4/25 0025 11:26
 * 修改备注：
 */
public class BindingActivity extends BaseActivity {

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
    @Bind(R.id.btn_modify)
    Button btnModify;
    @Bind(R.id.tv_bind_hint)
    TextView tvBindHint;
    private AppUserEntity appUserEntity;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_binded_card;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appUserEntity = MSPUtils.getInstance(BindingActivity.this).getAppUserSp();
        tvAccount.setText(TextTools.hidePhone(appUserEntity.getUserName()));
        tvName.setText(TextTools.hideName(appUserEntity.getAccountName()));
        tvIdCardNumber.setText(TextTools.hideIdCard(appUserEntity.getIdCard()));
        tvBankCardNumber.setText(TextTools.hideBank(appUserEntity.getBankAccount()));
        tvBank.setText(appUserEntity.getBankName());
        btnModify.setVisibility(View.INVISIBLE);
        tvBindHint.setVisibility(View.INVISIBLE);
    }
}
