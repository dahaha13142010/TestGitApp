package com.ronghan.testgitapp.app.main.my_wallet;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
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
public class MyWalletActivity extends BaseActivity {

    private SubscriberOnNextListener walletSearchListener;
    private MyWalletEntity myWalletEntity;
    private LoginEntity loginEntity;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_split_total)
    TextView tvSplitTotal;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginEntity = MSPUtils.getInstance(MyWalletActivity.this).getLoginSp();
        walletSearchListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                myWalletEntity = JSON.parseObject(TextTools.DataDecode(o.toString()), MyWalletEntity.class);
                MSPUtils.getInstance(MyWalletActivity.this).setMyWalletSp(myWalletEntity);
                tvMoney.setText(String.valueOf(myWalletEntity.getBalance()));
                TextPaint tp = tvMoney.getPaint();
                tp.setFakeBoldText(true);
                tvSplitTotal.setText("钱包累计分润：" + (myWalletEntity.getCollectionBalance() + myWalletEntity.getProductBalance()) + "元");
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        JSONObject jO = new JSONObject();
        jO.put("userId", loginEntity.id);
        httpWalletSearch(jO);
    }

    @OnClick({R.id.tv_split_details, R.id.rbtn_login_pwd, R.id.rbtn_pay_pwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_split_details:
                startActivity(new Intent(MyWalletActivity.this, SplitDetailsActivity.class));
                break;
            case R.id.rbtn_login_pwd:
                startActivity(new Intent(MyWalletActivity.this, TransferActivity.class));
                break;
            case R.id.rbtn_pay_pwd:
                startActivity(new Intent(MyWalletActivity.this, WithdrawActivity.class));
                break;
        }
    }

    /**
     * http钱包查询
     *
     * @param jO userId : 当前登录者（id）
     */
    private void httpWalletSearch(JSONObject jO) {
        HttpMethods.getInstance().walletSearch(new ProgressSubscriber(walletSearchListener, MyWalletActivity.this, "正在查询钱包信息…", false), jO);
    }
}
