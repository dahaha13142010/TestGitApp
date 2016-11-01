package com.ronghan.testgitapp.app.main.my_wallet;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.admin.control.topTitleBackBar.TopTitleBackBar;
import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
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
public class TransferActivity extends BaseActivity {

    private SubscriberOnNextListener transferRecordCreateListener;
    private double douBalance;
    private String strUserId;
    @Bind(R.id.title)
    TopTitleBackBar title;
    @Bind(R.id.et_transfer_account)
    EditText etTransferAccount;
    @Bind(R.id.et_transfer_money)
    EditText etTransferMoney;
    @Bind(R.id.tv_balance)
    TextView tvBalance;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_my_wallet_change;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title.setOnClickListenerRightTv(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransferActivity.this, TransferRecordActivity.class));
            }
        });
        douBalance = MSPUtils.getInstance(TransferActivity.this).getMyWalletSp().getBalance();
        strUserId = MSPUtils.getInstance(TransferActivity.this).getLoginSp().id;
        tvBalance.setText("账户余额：" + douBalance + "元\t转账为及时到账,无手续费");
        transferRecordCreateListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                TextTools.DataDecode(o.toString());
                showToast("转账成功！");
                finish();
            }
        };
    }

    @OnClick(R.id.btn_next)
    public void onClick() {
        JSONObject jO = new JSONObject();
        if (TextUtils.isEmpty(etTransferAccount.getText())) {
            showToast("请输入对方账户号码！");
        } else if (TextUtils.isEmpty(etTransferMoney.getText())) {
            showToast("请输入转账金额！");
        } else {
            jO.put("userId", strUserId);
            jO.put("userName", etTransferAccount.getText());
            jO.put("amount", etTransferMoney.getText());
            httpTransferRecordCreate(jO);
        }
    }

    /**
     * 转账
     *
     * @param jO userId : 转账者
     *           userName : 转账账户
     *           amount : 转账金额
     */
    private void httpTransferRecordCreate(JSONObject jO) {
        HttpMethods.getInstance().transferRecordCreate(new ProgressSubscriber(transferRecordCreateListener, TransferActivity.this, "正在转账…", true), jO);
    }
}
