package com.ronghan.testgitapp.app.fragment_mall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import com.admin.control.quickRv.RvHFAdapter;
import com.admin.control.quickRv.RvLoadMoreAdapter;
import com.admin.control.quickRv.RvQuickAdapter;
import com.admin.control.quickRv.RvViewHolder;
import com.admin.control.quickRv.inter.OnRecyclerItemClickListener;
import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSON;
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
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/6/3 0003 9:55
 * 修改人：Michael
 * 修改时间：2016/6/3 0003 9:55
 * 修改备注：
 */
public class AddressActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private Context mContext;
    private AppUserEntity appUserEntity;

    private SubscriberOnNextListener deliveryFindListener, deliveryDeleteListener;
    private RvLoadMoreAdapter rvLoadMoreAdapter;

    private RvQuickAdapter rvQuickAdapter;

    private List<AddressEntity> addressEntities;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_address;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        appUserEntity = MSPUtils.getInstance(mContext).getAppUserSp();

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        deliveryFindListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                if (null != mSwipeRefreshLayout && mSwipeRefreshLayout.isRefreshing())
                    mSwipeRefreshLayout.setRefreshing(false);
                addressEntities = JSON.parseArray(TextTools.DataDecode(o.toString()), AddressEntity.class);
                initRv(addressEntities);
            }
        };
        deliveryDeleteListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                showToast("删除成功！");
                httpDeliveryFind(appUserEntity.getUserId());
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        httpDeliveryFind(appUserEntity.getUserId());
    }

    private void initRv(final List<AddressEntity> addressEntities) {// 单类型适配
        rvQuickAdapter = new RvQuickAdapter<AddressEntity>(mContext, addressEntities, R.layout.item_address) {

            @Override
            public void bindData4View(RvViewHolder holder, final AddressEntity data, int pos, int type) {
                holder.setText(R.id.tv_name, data.getName());
                holder.setText(R.id.tv_phone, data.getTelephone());
                holder.setText(R.id.tv_address, data.getAddress());
                holder.setVisibility(R.id.tv_address_default, TextUtils.equals("YES", data.getStatus()) ? View.VISIBLE : View.INVISIBLE);
                holder.setLis(R.id.tv_delete_address, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        httpDeliveryDelete(data.getId());
                    }
                });
                holder.setLis(R.id.tv_edit_address, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(mContext, AddressCreateAndEditActivity.class).putExtra(H.TAG_INTENT_BOOLEAN, false).putExtra(H.TAG_INTENT, data));
                    }
                });
            }

        };
        //设置item监听
        rvQuickAdapter.setClickListener(new OnRecyclerItemClickListener<RvViewHolder>() {
            @Override
            public void onItemClick(int pos, RvViewHolder holder) {
                setResult(H.INTENT_SUCCESS, new Intent().putExtra(H.TAG_INTENT, addressEntities.get(pos)));
                finish();
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

    /**
     * http 查询所有收货地址信息
     *
     * @param string userId:         账户信息ID
     */
    private void httpDeliveryFind(String string) {
        HttpMethods.getInstance().deliveryFind(new ProgressSubscriber(deliveryFindListener, mContext, "正在查询钱包信息…", false), string);
    }

    @OnClick(R.id.tv_footer)
    public void onClick() {
        startActivity(new Intent(mContext, AddressCreateAndEditActivity.class).putExtra(H.TAG_INTENT_BOOLEAN, true));
    }

    /**
     * http 删除收货地址信息
     *
     * @param string id : 收货地址信息的id,传参方式见 请求地址
     */
    private void httpDeliveryDelete(String string) {
        HttpMethods.getInstance().deliveryDelete(new ProgressSubscriber(deliveryDeleteListener, mContext, "正在查询钱包信息…", false), string);
    }

    @Override
    public void onRefresh() {
        httpDeliveryFind(appUserEntity.getUserId());
    }
}
