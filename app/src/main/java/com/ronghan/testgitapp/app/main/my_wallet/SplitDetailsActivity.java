package com.ronghan.testgitapp.app.main.my_wallet;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.admin.control.quickRv.RvHFAdapter;
import com.admin.control.quickRv.RvLoadMoreAdapter;
import com.admin.control.quickRv.RvQuickAdapter;
import com.admin.control.quickRv.RvViewHolder;
import com.admin.control.quickRv.inter.OnRecyclerItemClickListener;
import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.been.SplitEntity;
import com.ronghan.testgitapp.constant.H;
import com.ronghan.testgitapp.http.HttpMethods;
import com.ronghan.testgitapp.tools.MSPUtils;
import com.ronghan.testgitapp.tools.TextTools;
import com.ronghan.testgitapp.tools.subscribers.ProgressSubscriber;
import com.ronghan.testgitapp.tools.subscribers.SubscriberOnNextListener;

import java.io.UnsupportedEncodingException;

import butterknife.Bind;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/12 0012 10:24
 * 修改人：Michael
 * 修改时间：2016/5/12 0012 10:24
 * 修改备注：
 */
public class SplitDetailsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private SplitEntity splitEntity;
    private SubscriberOnNextListener billingPaginationListener;

    private RvLoadMoreAdapter rvLoadMoreAdapter;
    private RvQuickAdapter rvQuickAdapter;
    private int page = 1;
    private boolean isMore = false;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_my_wallet_record;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        billingPaginationListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object s) throws UnsupportedEncodingException {
                splitEntity = JSON.parseObject(TextTools.DataDecode(s.toString()), SplitEntity.class);
                isMore = splitEntity.getCount() > splitEntity.getPageSize() * splitEntity.getPage();
                mSwipeRefreshLayout.setRefreshing(false);
                initRv(splitEntity);
            }
        };
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        //TODO recyclerView 添加 foot 和 head
//        recyclerView.addHeaderView();
//        recyclerView.
    }

    @Override
    protected void onResume() {
        super.onResume();
        JSONObject jO = new JSONObject();
        jO.put("userId", MSPUtils.getInstance(SplitDetailsActivity.this).getLoginSp().id);
        jO.put("status", "ALL");
        jO.put("page", page);
        jO.put("pageSize", H.PAGE_SIZE);
        httpBillingPagination(jO);
    }

    private void initRv(final SplitEntity splitEntity) {

        // 单类型适配
        rvQuickAdapter = new RvQuickAdapter<SplitEntity.DataBean>(SplitDetailsActivity.this, splitEntity.getData(), R.layout.item_transfer_record) {

            @Override
            public void bindData4View(RvViewHolder holder, SplitEntity.DataBean data, int pos, int type) {
                holder.setText(R.id.tv_order_id, "订单号：" + data.getOrderId());
                holder.setText(R.id.tv_order_time, data.getCreatedDate());
                holder.setText(R.id.iv_icon, "￥" + data.getAmount());
                holder.setText(R.id.tv_order_statue, "已入账");
                holder.setText(R.id.tv_order_from, "来源：" + TextTools.getType(data.getType()) + "（分润率：" + (data.getRate() * 100) + "%）");
                holder.setText(R.id.tv_order_total, "交易金额：" + data.getTransactAmount());
            }

        };
        //设置item监听
        rvQuickAdapter.setClickListener(new OnRecyclerItemClickListener<RvViewHolder>() {
            @Override
            public void onItemClick(int pos, RvViewHolder holder) {
//                startActivity(new Intent(SplitDetailsActivity.this, ChildActivity.class)/*.putExtra("skip", homeMenuEntities.get(pos).strIntent)*/);
            }
        });


        //使用RvHFAdapter包装,实现添加Header和Footer
        final RvHFAdapter rvHFQuickAdapter = new RvHFAdapter(rvQuickAdapter) {
            @Override
            public void bindLisAndData4Footer(RvViewHolder footer) {
                super.bindLisAndData4Footer(footer);
                //可以不实现
            }

            @Override
            public void bindLisAndData4Header(RvViewHolder header) {
                super.bindLisAndData4Header(header);
                //可以不实现
            }
        };

        //TODO
//        rvHFQuickAdapter.addHeader(R.activity_goods_category.rvquick_header);
//        rvHFQuickAdapter.addFooter(R.activity_goods_category.rvquick_footer);

        //上拉加载更多
        rvLoadMoreAdapter = new RvLoadMoreAdapter(rvHFQuickAdapter, new RvLoadMoreAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (isMore) {
                    page++;
                    JSONObject jO = new JSONObject();
                    jO.put("userId", MSPUtils.getInstance(SplitDetailsActivity.this).getLoginSp().id);
                    jO.put("status", "ALL");
                    jO.put("page", page);
                    jO.put("pageSize", H.PAGE_SIZE);
                    httpBillingPagination(jO);
                } else {
                    rvHFQuickAdapter.addFooter(R.layout.not_loading);
                }
            }
        });
        //你可以使用shape自定义分割线样式
//        Drawable line = ContextCompat.getDrawable(SplitDetailsActivity.this, R.drawable.shape_line);
//        recyclerView.addItemDecoration(new GridDividerDecoration(SplitDetailsActivity.this, line));
        recyclerView.setLayoutManager(new LinearLayoutManager(SplitDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(rvLoadMoreAdapter);
    }


    @Override
    public void onRefresh() {
        page = 1;
        JSONObject jO = new JSONObject();
        jO.put("userId", MSPUtils.getInstance(SplitDetailsActivity.this).getLoginSp().id);
        jO.put("status", "ALL");
        jO.put("page", page);
        jO.put("pageSize", H.PAGE_SIZE);
        httpBillingPagination(jO);
    }

    /**
     * 分润查询
     *
     * @param jO userId : 当前登录用户ID
     *           status : 类型[ALL:全部，COLLECTION:收款分润，PRODUCT:产品分润]
     *           page : 页数
     *           pageSize : 每页数量
     */
    private void httpBillingPagination(JSONObject jO) {
        HttpMethods.getInstance().billingPagination(new ProgressSubscriber(billingPaginationListener, SplitDetailsActivity.this, "正在查询…", false), jO);
    }
}
