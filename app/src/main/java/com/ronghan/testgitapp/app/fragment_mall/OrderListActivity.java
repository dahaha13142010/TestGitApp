package com.ronghan.testgitapp.app.fragment_mall;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.http.HttpMethods;
import com.ronghan.testgitapp.tools.subscribers.ProgressSubscriber;
import com.ronghan.testgitapp.tools.subscribers.SubscriberOnNextListener;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/26 0026 9:37
 * 修改人：Michael
 * 修改时间：2016/5/26 0026 9:37
 * 修改备注：
 */
public class OrderListActivity extends BaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private Context mContext;
    private SubscriberOnNextListener orderListListener;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_mall_order_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @OnClick({R.id.rbtn_order_all, R.id.rbtn_order_wait_delivery, R.id.rbtn_order_wait_receipt, R.id.rbtn_order_wait_evaluate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbtn_order_all:
                break;
            case R.id.rbtn_order_wait_delivery:
                break;
            case R.id.rbtn_order_wait_receipt:
                break;
            case R.id.rbtn_order_wait_evaluate:
                break;
        }
    }


    /**
     * http Banner查询
     *
     * @param jO bannerType : MALL或者TITLE    //MALL为请求商城banner，TITLE为请求首页banner
     */
    private void httpBannerFind(JSONObject jO) {
        HttpMethods.getInstance().bannerFind(new ProgressSubscriber(orderListListener, mContext, "正在查询钱包信息…", false), jO);
    }
}
