package com.ronghan.testgitapp.app.main.bank;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.app.activity_login_etc.LoginActivity;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.http.HttpMethods;
import com.ronghan.testgitapp.tools.MSPUtils;
import com.ronghan.testgitapp.tools.TextTools;
import com.ronghan.testgitapp.tools.subscribers.ProgressSubscriber;
import com.ronghan.testgitapp.tools.subscribers.SubscriberOnNextListener;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

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
public class BindBankActivity extends BaseActivity {

//    private String strPhone;
    private AppUserEntity appUserEntity;
    private SubscriberOnNextListener createBankListener;

    @Bind(R.id.tv_account)
    TextView tvAccount;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_id_card_number)
    EditText etIdCardNumber;
    @Bind(R.id.et_bank_card_number)
    EditText etBankCardNumber;
    @Bind(R.id.et_bank_number_repeat)
    EditText etBankNumberRepeat;
    @Bind(R.id.et_bank)
    EditText etBank;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_binding_card;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appUserEntity = MSPUtils.getInstance(BindBankActivity.this).getAppUserSp();
        tvAccount.setText(TextTools.hidePhone(appUserEntity.getUserName()));
        createBankListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                startActivity(new Intent(BindBankActivity.this, BankCommitSucceedActivity.class)
                        /*.putExtra(H.TAG_INTENT_PHONE, appUserEntity.getUserName())*/);
            }
        };
    }

    @OnClick({R.id.iv_delete, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_delete:
                startActivity(new Intent(BindBankActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.btn_next:
                if (TextUtils.isEmpty(etName.getText())) {
                    showToast("请输入持卡人姓名！");
                } else if (TextUtils.isEmpty(etIdCardNumber.getText())) {
                    showToast("请输入身份证号码！");
                } else {
                    /** * 判断身份证有效性**/
                    String strIDError = null;
                    try {
                        strIDError = TextTools.IDCardValidate(etIdCardNumber.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if ("" != strIDError) {
                        showToast(strIDError);
                    } else if (TextUtils.isEmpty(etBankCardNumber.getText())) {
                        showToast("请输入银行卡卡号！");
                    } else if (!TextTools.checkBankCard(etBankCardNumber.getText().toString())) {
                        showToast("请输入正确的银行卡卡号！");
                    } else if (TextUtils.isEmpty(etBankNumberRepeat.getText())) {
                        showToast("请确认银行卡卡号！");
                    } else if (!TextUtils.equals(etBankNumberRepeat.getText(), etBankCardNumber.getText())) {
                        showToast("两次输入银行卡号不一致，请重新输入！");
                    } else if (TextUtils.isEmpty(etBank.getText())) {
                        showToast("请输入开户行名称！");
                    } else {
                        JSONObject jO = new JSONObject();
                        jO.put("customerId", appUserEntity.getCustomerId());
                        jO.put("bankAccount", etBankCardNumber.getText().toString());
                        jO.put("accountName", etName.getText().toString());
                        jO.put("bankName", etBank.getText().toString());
                        jO.put("mobilePhone", appUserEntity.getUserName());
                        jO.put("idCard", etIdCardNumber.getText().toString());
                        httpCreateBank(jO);
                    }
                }
                break;
        }
    }

    /**
     * http添加银行信息
     *
     * @param jO customerId : appUser编号
     *           bankAccount : 银行账号
     *           accountName :持卡人名称
     *           bankName : 银行名称
     *           mobilePhone : 预留电话
     *           idCard : 身份证
     */
    private void httpCreateBank(JSONObject jO) {
        HttpMethods.getInstance().createBank(new ProgressSubscriber(createBankListener, BindBankActivity.this, "正在保存信息…", true), jO);
    }
}
