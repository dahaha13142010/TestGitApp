package com.ronghan.testgitapp.app.main.my_wallet;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.admin.control.topTitleBackBar.TopTitleBackBar;
import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.been.AppUserEntity;
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
 * 创建时间：2016/5/12 0012 10:24
 * 修改人：Michael
 * 修改时间：2016/5/12 0012 10:24
 * 修改备注：
 */
public class WithdrawActivity extends BaseActivity {

    @Bind(R.id.tv_transfer_money)
    TextView tvTransferMoney;
    @Bind(R.id.tv_transfer_account)
    TextView tvTransferAccount;
    @Bind(R.id.title)
    TopTitleBackBar title;
    @Bind(R.id.et_transfer_account)
    EditText etTransferAccount;
    @Bind(R.id.et_transfer_money)
    EditText etTransferMoney;
    @Bind(R.id.tv_balance)
    TextView tvBalance;
    private SubscriberOnNextListener withdrawCreateListener;
    private double douBalance;
    private String strUserId;
    private AppUserEntity appUserEntity;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_my_wallet_change;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appUserEntity = MSPUtils.getInstance(WithdrawActivity.this).getAppUserSp();

        title.setTitle(R.string.my_wallet_withdraw);
        title.setRightTitle(R.string.withdraw_record);
        tvTransferAccount.setText(appUserEntity.getBankName() + ":（" + TextTools.hideBank(appUserEntity.getBankAccount()) + "）");
        etTransferAccount.setVisibility(View.GONE);
        tvTransferMoney.setText(R.string.withdraw_money);
        etTransferMoney.setHint(R.string.withdraw_money_hint);
        title.setOnClickListenerRightTv(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WithdrawActivity.this, WithdrawRecordActivity.class));
            }
        });
        douBalance = MSPUtils.getInstance(WithdrawActivity.this).getMyWalletSp().getBalance();
        strUserId = MSPUtils.getInstance(WithdrawActivity.this).getLoginSp().id;
        tvBalance.setText("账户余额：" + douBalance + "元\n周一至周五（节假日除外）\n15点之前提现，当前到账\n5点之后提现，第二工作日到账");
        tvBalance.setGravity(Gravity.LEFT);
        withdrawCreateListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                TextTools.DataDecode(o.toString());
                showToast("提现成功！");
                finish();
            }
        };
    }

    @OnClick(R.id.btn_next)
    public void onClick() {
        JSONObject jO = new JSONObject();
        if (TextUtils.isEmpty(etTransferMoney.getText())) {
            showToast("请输入转账金额！");
        } else {
            jO.put("userId", strUserId);
            jO.put("amount", etTransferMoney.getText());
            httpWithdrawCreate(jO);
        }
    }

    /**
     * 提现
     *
     * @param jO userId : 当前登录者（ID）
     *           amount : 提现金额
     */
    private void httpWithdrawCreate(JSONObject jO) {
        HttpMethods.getInstance().withdrawCreate(new ProgressSubscriber(withdrawCreateListener, WithdrawActivity.this, "正在转账…", true), jO);
    }
}
