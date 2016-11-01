package com.ronghan.testgitapp.app.main.my_promotion;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.admin.control.quickRv.RvHFAdapter;
import com.admin.control.quickRv.RvLoadMoreAdapter;
import com.admin.control.quickRv.RvQuickAdapter;
import com.admin.control.quickRv.RvViewHolder;
import com.admin.control.quickRv.helper.GridDividerDecoration;
import com.admin.control.quickRv.inter.OnRecyclerItemClickListener;
import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
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

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/5/16 0016 14:24
 * 修改人：Michael
 * 修改时间：2016/5/16 0016 14:24
 * 修改备注：
 */
public class MyPromotionDetailsActivity extends BaseActivity {

    @Bind(R.id.tv_numbers)
    TextView tvNumbers;
    @Bind(R.id.rv)
    RecyclerView recyclerView;

    private List<AppUserEntity> appUserEntities;
    private SubscriberOnNextListener appUserPromotionListener;
    private RvLoadMoreAdapter rvLoadMoreAdapter;
    private RvQuickAdapter rvQuickAdapter;
    private int i = 0;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_my_promotion_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appUserPromotionListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object s) throws UnsupportedEncodingException {
                appUserEntities = JSON.parseArray(TextTools.DataDecode(s.toString()), AppUserEntity.class);
                initRv(appUserEntities);
                for (AppUserEntity app : appUserEntities) {
                    i = TextUtils.equals("YES", app.getIsActivation()) ? i + 1 : i;
                }
                tvNumbers.setText("店员直接推广" + appUserEntities.size() + "人，其中有效推广" + i + "人");
            }
        };

        JSONObject jO = new JSONObject();
        jO.put("userId", MSPUtils.getInstance(MyPromotionDetailsActivity.this).getLoginSp().id);
        jO.put("nowRole", getIntent().getStringExtra(H.TAG_INTENT));
        httpAppUserPromotion(jO);
    }

    private void initRv(final List<AppUserEntity> appUserEntities) {

        // 单类型适配
        rvQuickAdapter = new RvQuickAdapter<AppUserEntity>(MyPromotionDetailsActivity.this, appUserEntities, R.layout.item_promotion_details) {

            @Override
            public void bindData4View(RvViewHolder holder, AppUserEntity appUserEntity, int pos, int type) {
                holder.setText(R.id.tv_time, String.valueOf(appUserEntity.getCreatedDate()));
                holder.setText(R.id.tv_level, TextTools.hidePhone(appUserEntity.getUserName()) + "(" + appUserEntity.getAccountName() + ")");
                holder.setText(R.id.tv_statue, TextUtils.equals("YES", appUserEntity.getIsActivation()) ? "已激活" : "未激活");
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
        RvHFAdapter rvHFQuickAdapter = new RvHFAdapter(rvQuickAdapter) {
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

            }
        });
        //你可以使用shape自定义分割线样式
        Drawable line = ContextCompat.getDrawable(MyPromotionDetailsActivity.this, R.drawable.shape_line);
        recyclerView.addItemDecoration(new GridDividerDecoration(MyPromotionDetailsActivity.this, line));
        recyclerView.setLayoutManager(new LinearLayoutManager(MyPromotionDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(rvLoadMoreAdapter);
    }

    /**
     * 推广详情
     * @param jO userId : 登录者账号ID
     *           nowRole : 查询角色[CLERK：店员，SHOPOWNER：店长，BOSS：老板]
     */
    private void httpAppUserPromotion(JSONObject jO) {
        HttpMethods.getInstance().appUserPromotion(new ProgressSubscriber(appUserPromotionListener, MyPromotionDetailsActivity.this, "正在查询…", true), jO);
    }
}
