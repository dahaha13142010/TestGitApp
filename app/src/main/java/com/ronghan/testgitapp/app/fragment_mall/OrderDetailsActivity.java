package com.ronghan.testgitapp.app.fragment_mall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.admin.control.pop.AbListPopupWindow;
import com.admin.control.quickRv.RvHFAdapter;
import com.admin.control.quickRv.RvLoadMoreAdapter;
import com.admin.control.quickRv.RvQuickAdapter;
import com.admin.control.quickRv.RvViewHolder;
import com.admin.control.quickRv.inter.OnRecyclerItemClickListener;
import com.admin.mine.BaseActivity;
import com.admin.utils.ABLogUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.been.AddressEntity;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.been.GoodsEntities;
import com.ronghan.testgitapp.constant.H;
import com.ronghan.testgitapp.http.HttpMethods;
import com.ronghan.testgitapp.tools.MSPUtils;
import com.ronghan.testgitapp.tools.TextTools;
import com.ronghan.testgitapp.tools.subscribers.ProgressSubscriber;
import com.ronghan.testgitapp.tools.subscribers.SubscriberOnNextListener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/6/2 0002 10:10
 * 修改人：Michael
 * 修改时间：2016/6/2 0002 10:10
 * 修改备注：
 */
public class OrderDetailsActivity extends BaseActivity {
    @Bind(R.id.tv_recipients)
    TextView tvRecipients;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_price_total)
    TextView tvPriceTotal;
    @Bind(R.id.tv_freight_total)
    TextView tvFreightTotal;
    @Bind(R.id.tv_real_price_total)
    TextView tvRealPriceTotal;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.tv_pay_way_choose)
    TextView tvPayWayChoose;

    private Context mContext;
    private AppUserEntity appUserEntity;
    private SubscriberOnNextListener orderCreateListener, deliveryFindListener;
    private List<GoodsEntities> goodsEntities;
    private List<AddressEntity> addressEntities;

    private RvLoadMoreAdapter rvLoadMoreAdapter;
    private RvQuickAdapter rvQuickAdapter;


    List<String> stringList = new ArrayList<>();

    String strPayWay;

    @Override

    protected int getLayoutResID() {
        return R.layout.activity_order_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        goodsEntities = (List<GoodsEntities>) getIntent().getSerializableExtra(H.TAG_INTENT);

        initRv(goodsEntities);
        appUserEntity = MSPUtils.getInstance(mContext).getAppUserSp();

        stringList.add("在线支付");
        stringList.add("钱包支付");

        orderCreateListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                TextTools.DataDecode(o.toString());
            }
        };
        deliveryFindListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                addressEntities = JSONArray.parseArray(TextTools.DataDecode(o.toString()), AddressEntity.class);
                for (AddressEntity addressEntity : addressEntities) {
                    if (TextUtils.equals("YES", addressEntity.getStatus())) {
                        tvAddress.setText(addressEntity.getAddress());
                        tvRecipients.setText(addressEntity.getName());
                    }
                }
            }
        };
//        JSONObject jO = new JSONObject();
//        jO.put("userId", appUserEntity.getUserId());
        httpDeliveryFind(appUserEntity.getUserId());
        ABLogUtil.i(appUserEntity.getUserId());
    }


    private void initRv(final List<GoodsEntities> goodsEntities) {// 单类型适配
        rvQuickAdapter = new RvQuickAdapter<GoodsEntities>(mContext, goodsEntities, R.layout.item_order_fill) {

            @Override
            public void bindData4View(final RvViewHolder holder, final GoodsEntities data, int pos, int type) {
                holder.setImg(mContext, R.id.iv_icon, data.getProductPics().get(0).getUrl());
                holder.setText(R.id.tv_name, data.getName());
                holder.setText(R.id.tv_details, data.getRemark());
                holder.setText(R.id.tv_price, data.getSalePrice() + "");
                holder.setText(R.id.tv_price_total, data.getSalePrice() + "");
                final TextView tvNumber = holder.getView(R.id.tv_number);
                final TextView tvPriceTotal = holder.getView(R.id.tv_price_total);

                holder.setLis(R.id.tv_sub, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.setText(R.id.tv_number, (Double.valueOf(tvNumber.getText().toString()) + 1) + "");
                    }
                });
                holder.setLis(R.id.tv_sub, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Double.valueOf(tvNumber.getText().toString()) - 1 > 0) {
                            holder.setText(R.id.tv_number, (Double.valueOf(tvNumber.getText().toString()) - 1) + "");
                            holder.setText(R.id.tv_price_total, (Double.valueOf(tvNumber.getText().toString()) * Double.valueOf(tvPriceTotal.getText().toString())) + "");
                        } else {
                            holder.setText(R.id.tv_number, "1");
                            holder.setText(R.id.tv_price_total, (Double.valueOf(tvNumber.getText().toString()) * Double.valueOf(tvPriceTotal.getText().toString())) + "");
                        }
                    }
                });
            }

        };
        //设置item监听
        rvQuickAdapter.setClickListener(new OnRecyclerItemClickListener<RvViewHolder>() {
            @Override
            public void onItemClick(int pos, RvViewHolder holder) {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(H.TAG_INTENT_SERIALIZABLE, goodsEntities.get(pos));
//                setResult(H.INTENT_SUCCESS, new Intent(mContext, OrderDetailsActivity.class).putExtra(H.TAG_INTENT, bundle));
//                finish();
            }
        });


        //使用RvHFAdapter包装,实现添加Header和Footer
        final RvHFAdapter rvHFQuickAdapter = new RvHFAdapter(rvQuickAdapter) {
            @Override
            public void bindLisAndData4Footer(RvViewHolder footer) {
                super.bindLisAndData4Footer(footer);
            }

            @Override
            public void bindLisAndData4Header(RvViewHolder header) {
                super.bindLisAndData4Header(header);
                //可以不实现
            }
        };

        //TODO
//        rvHFQuickAdapter.addHeader(R.layout.header_mall);
//        rvHFQuickAdapter.addFooter(R.layout.footer_address);

        //上拉加载更多
        rvLoadMoreAdapter = new RvLoadMoreAdapter(rvHFQuickAdapter, new RvLoadMoreAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
            }
        });
        //你可以使用shape自定义分割线样式
//        Drawable line = ContextCompat.getDrawable(SplitDetailsActivity.this, R.drawable.shape_line);
//        recyclerView.addItemDecoration(new GridDividerDecoration(SplitDetailsActivity.this, line));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(rvLoadMoreAdapter);


    }

    @OnClick({R.id.tv_pay_way_choose, R.id.btn_order_commit, R.id.rl_top})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pay_way_choose:
                AbListPopupWindow.getInstance(mContext).openPopupWindow(false, tvPayWayChoose, 15, stringList, 0, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        strPayWay = position == 0 ? "WALLET" : "WALLET_OTHER";
                    }
                });
                break;
            case R.id.btn_order_commit:
                JSONObject jO = new JSONObject();
                JSONArray jA = new JSONArray();
                JSONObject jOProductInfo = new JSONObject();
                jO.put("customerId", appUserEntity.getCustomerId());
                jO.put("paymentType", strPayWay);
                if (TextUtils.equals("WALLET_OTHER", strPayWay)) jO.put("walletAmount", 0);
                jO.put("deliveryAddressId", "");
                jO.put("productInfo", jA);
                httpOrderCreate(jO);
                break;
            case R.id.rl_top:
                startActivityForResult(new Intent(mContext, AddressActivity.class), H.ADDRESS_ACTIVITY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (H.INTENT_SUCCESS == resultCode) {
            switch (requestCode) {
                case H.ADDRESS_ACTIVITY:
                    AddressEntity addressEntity = (AddressEntity) data.getSerializableExtra(H.TAG_INTENT);
                    ABLogUtil.i("addressEntity==null" + (null == addressEntity));
                    tvRecipients.setText(addressEntity.getName());
                    tvAddress.setText(addressEntity.getAddress());
                    break;
            }
        }

    }

    /**
     * http 添加订单
     *
     * @param jO customerId : 用户customerId
     *           productInfo : 多个购买产品信息
     *           [
     *           productId : 可多个产品id
     *           count : 购买数量
     *           ]
     *           paymentType : 支付类型[WALLET:钱包支付,WALLET_OTHER:钱包和在线支付,OTHER:在线支付]
     *           walletAmount : 钱包支付金额(当paymentType为（WALLET_OTHER）有值)
     *           deliveryAddressId : 收货地址Id
     */
    private void httpOrderCreate(JSONObject jO) {
        HttpMethods.getInstance().orderCreate(new ProgressSubscriber(orderCreateListener, mContext, "正在查询钱包信息…", false), jO);
    }

    /**
     * http 查询所有收货地址信息
     *
     * @param string userId:         账户信息ID
     */
    private void httpDeliveryFind(String string) {
        HttpMethods.getInstance().deliveryFind(new ProgressSubscriber(deliveryFindListener, mContext, "正在查询钱包信息…", false), string);
    }

}
