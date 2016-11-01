package com.ronghan.testgitapp.app.fragment_mall;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;

import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.been.AddressEntity;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.constant.H;
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
 * 创建时间：2016/6/3 0003 10:46
 * 修改人：Michael
 * 修改时间：2016/6/3 0003 10:46
 * 修改备注：
 */
public class AddressCreateAndEditActivity extends BaseActivity {
    @Bind(R.id.et_shr)
    EditText etShr;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_address_add_details)
    EditText etAddressAddDetails;
    @Bind(R.id.cb_set_default)
    CheckBox cbSetDefault;

    private Context mContext;
    private SubscriberOnNextListener deliveryUpdateListener, deliveryCreateListener;

    private AppUserEntity appUserEntity;

    private boolean isCreate = false;
    private AddressEntity addressEntity;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_address_edit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        isCreate = getIntent().getBooleanExtra(H.TAG_INTENT_BOOLEAN, false);
        if (!isCreate) {
            addressEntity = (AddressEntity) getIntent().getSerializableExtra(H.TAG_INTENT);
            etShr.setText(addressEntity.getName());
            etAddressAddDetails.setText(addressEntity.getAddress());
            etPhone.setText(addressEntity.getTelephone());
            cbSetDefault.setChecked(TextUtils.equals("YES",addressEntity.getStatus()));
        }
        appUserEntity = MSPUtils.getInstance(mContext).getAppUserSp();
        deliveryUpdateListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                showToast("修改成功！");
                finish();
            }
        };
        deliveryCreateListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                TextTools.DataDecode(o.toString());
                showToast("保存成功！");
                finish();
            }
        };
    }

    @OnClick(R.id.btn_save_use)
    public void onClick() {
        if (TextUtils.isEmpty(etShr.getText())) {
            showToast("请填写收货人！");
        } else if (TextUtils.isEmpty(etPhone.getText())) {
            showToast("请填写联系方式！");
        } else if (TextUtils.isEmpty(etAddressAddDetails.getText())) {
            showToast("请填写详细地址！");
        } else {
            JSONObject jO = new JSONObject();
            jO.put("name", etShr.getText());
            jO.put("address", etAddressAddDetails.getText());
            jO.put("telephone", etPhone.getText());
            jO.put("status", cbSetDefault.isChecked() ? "YES" : "NO");

            if (isCreate) {
                jO.put("userId", appUserEntity.getUserId());
                httpDeliveryCreate(jO);
            } else {
                jO.put("id", addressEntity.getId());
                httpDeliveryUpdate(jO);
            }
        }
    }


    /**
     * http 更新收货地址信息
     *
     * @param jO id:             唯一标识
     *           name:           收货人姓名
     *           address:        收货地址
     *           telephone:      电话号码
     *           status:         是否为默认收货地址的状态(YES:是, NO:否)
     */
    private void httpDeliveryUpdate(JSONObject jO) {
        HttpMethods.getInstance().deliveryUpdate(new ProgressSubscriber(deliveryUpdateListener, mContext, "正在查询钱包信息…", false), jO);
    }

    /**
     * http 创建收货地址信息
     *
     * @param jO userId:         用户账号
     *           name:           收货人姓名
     *           address:        收货地址
     *           telephone:      电话号码
     *           status:         是否为默认收货地址(YES :是, NO :否)
     */
    private void httpDeliveryCreate(JSONObject jO) {
        HttpMethods.getInstance().deliveryCreate(new ProgressSubscriber(deliveryCreateListener, mContext, "正在查询钱包信息…", false), jO);
    }
}
