package com.ronghan.testgitapp.app.fragment_mall;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.admin.control.ab.AbSlidingPlayView;
import com.admin.control.quickRv.RvHFAdapter;
import com.admin.control.quickRv.RvLoadMoreAdapter;
import com.admin.control.quickRv.RvQuickAdapter;
import com.admin.control.quickRv.RvViewHolder;
import com.admin.control.quickRv.inter.OnRecyclerItemClickListener;
import com.admin.control.topTitleBackBar.TopTitleBackBar;
import com.admin.mine.BaseFragment;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.app.activity_login_etc.LoginActivity;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.been.BannerEntity;
import com.ronghan.testgitapp.been.ProductionEntity;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/4/26 0026 9:51
 * 修改人：Michael
 * 修改时间：2016/4/26 0026 9:51
 * 修改备注：
 */
public class FragMall extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {


    @Bind(R.id.title)
    TopTitleBackBar title;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    //    @Bind(R.id.viewpager)
    AbSlidingPlayView viewPager;
    Button btnGoodsCategory, btnSplitRule, btnSplitRecord;

    private Context mContext;
    private AppUserEntity appUserEntity;
    private ProductionEntity productionEntity;

    private RvLoadMoreAdapter rvLoadMoreAdapter;
    private RvQuickAdapter rvQuickAdapter;
    private int page = 1;
    private boolean isMore = false;

    private SubscriberOnNextListener bannerFindListener, productPaginationListener, shopCarCreateListener;
    private List<BannerEntity> bannerEntities;
    /**
     * 存储轮播的界面
     */
    private ArrayList<View> allListView;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_mall;
    }

    @Override
    protected void onBaseCreate(View view) {
        mContext = this.getContext();
        if (MSPUtils.getInstance(mContext).isLogin()) {
            appUserEntity = MSPUtils.getInstance(mContext).getAppUserSp();
        }

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        shopCarCreateListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                showToast("添加成功！");
            }
        };
        bannerFindListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                bannerEntities = JSON.parseArray(TextTools.DataDecode(o.toString()), BannerEntity.class);
                initViewPager(bannerEntities.get(0));
            }
        };
        productPaginationListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object s) throws UnsupportedEncodingException {
                productionEntity = JSON.parseObject(TextTools.DataDecode(s.toString()), ProductionEntity.class);
                isMore = productionEntity.getCount() > productionEntity.getPageSize() * productionEntity.getPage();
                if (null != mSwipeRefreshLayout && mSwipeRefreshLayout.isRefreshing())
                    mSwipeRefreshLayout.setRefreshing(false);
                initRv(productionEntity);
                JSONObject jO = new JSONObject();
                jO.put("bannerType", H.MALL);
                httpBannerFind(jO);
            }
        };

        title.setOnClickListenerRightRl(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), OrderListActivity.class));
            }
        });
        JSONObject jO2 = new JSONObject();
//        jO.put("productTypeId", 0);
        jO2.put("page", page);
        jO2.put("pageSize", 8);
        httpProductPagination(jO2);
    }

    private void initRv(final ProductionEntity productionEntity) {// 单类型适配
        rvQuickAdapter = new RvQuickAdapter<ProductionEntity.DataBean>(mContext, productionEntity.getData(), R.layout.item_ma_products) {
            @Override
            public void bindData4View(RvViewHolder holder, final ProductionEntity.DataBean data, int pos, int type) {
                holder.setImg(mContext, R.id.iv_production, data.getProductPics().get(0).getUrl());
                holder.setText(R.id.tv_description, data.getRemark());
                holder.setText(R.id.tv_price, "￥ " + data.getSalePrice());
                holder.setLis(R.id.btn_add_cart, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (MSPUtils.getInstance(mContext).isLogin()) {
                            JSONObject jO = new JSONObject();
                            jO.put("productId", data.getId());
                            jO.put("userId", appUserEntity.getUserId());
                            httpShopCarCreate(jO);
                        } else {
                            startActivity(new Intent(mContext, LoginActivity.class));
                        }
                    }
                });
            }

        };
        //设置item监听
        rvQuickAdapter.setClickListener(new OnRecyclerItemClickListener<RvViewHolder>() {
            @Override
            public void onItemClick(int pos, RvViewHolder holder) {
                startActivity(new Intent(mContext, ProductionDetailsActivity.class).putExtra(H.TAG_INTENT, productionEntity.getData().get(pos - 1).getId()));
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
        rvHFQuickAdapter.addHeader(R.layout.header_mall);
//        rvHFQuickAdapter.addFooter(R.activity_goods_category.rvquick_footer);

        //上拉加载更多
        rvLoadMoreAdapter = new RvLoadMoreAdapter(rvHFQuickAdapter, new RvLoadMoreAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (isMore) {
                    page++;
                    JSONObject jO = new JSONObject();
//                    jO.put("productTypeId", 0);
                    jO.put("page", page);
                    jO.put("pageSize", 8);
                    httpProductPagination(jO);
                } else {
                    rvHFQuickAdapter.addFooter(R.layout.not_loading);
                }
            }
        });
        //你可以使用shape自定义分割线样式
//        Drawable line = ContextCompat.getDrawable(SplitDetailsActivity.this, R.drawable.shape_line);
//        recyclerView.addItemDecoration(new GridDividerDecoration(SplitDetailsActivity.this, line));
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(rvLoadMoreAdapter);

        View v = rvHFQuickAdapter.getHeader();
        viewPager = (AbSlidingPlayView) v.findViewById(R.id.viewpager);
        btnGoodsCategory = (Button) v.findViewById(R.id.btn_goods_category);
        btnSplitRule = (Button) v.findViewById(R.id.btn_split_rule);
        btnSplitRecord = (Button) v.findViewById(R.id.btn_split_record);
        btnGoodsCategory.setOnClickListener(this);
        btnSplitRule.setOnClickListener(this);
        btnSplitRecord.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initViewPager(BannerEntity bannerEntity) {
        //设置播放方式为顺序播放
        viewPager.setPlayType(1);
        //设置播放间隔时间
        viewPager.setSleepTime(3000);
        viewPager.setNavHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        Bitmap bitmapPointY = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.point_y);
        Bitmap bitmapPointN = BitmapFactory.decodeResource(this.getContext().getResources(), R.mipmap.point_n);
        viewPager.setPageLineImage(bitmapPointY, bitmapPointN);
        if (allListView != null) {
            allListView.clear();
            allListView = null;
        }
        allListView = new ArrayList<>();

        for (int i = 0; i < bannerEntity.getBannerPics().size(); i++) {
            //导入ViewPager的布局
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_banner, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
            Glide.with(getContext()).load(bannerEntity.getBannerPics().get(i).getPicUrl()).into(imageView);
//            Picasso.with(getContext()).load(bannerEntity.getBannerPics().get(i).getPicUrl()).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            allListView.add(view);
        }
        viewPager.addViews(allListView);
        //开始轮播
        viewPager.startPlay();
        viewPager.setOnItemClickListener(new AbSlidingPlayView.AbOnItemClickListener() {
            @Override
            public void onClick(int position) {
                showToast("VIEW_PAGER!");
                //跳转到详情界面
//                Intent intent = new Intent(getActivity(), BabyActivity.class);
//                startActivity(intent);
            }
        });
    }

    //    @OnClick({R.id.btn_goods_category, R.id.btn_split_rule, R.id.btn_split_record})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_goods_category:
                startActivity(new Intent(mContext, GoodsCategoryActivity.class)/*.putExtra("skip", homeMenuEntities.get(pos).strIntent)*/);
                break;
            case R.id.btn_split_rule:
                startActivity(new Intent(mContext, SplitRuleActivity.class)/*.putExtra("skip", homeMenuEntities.get(pos).strIntent)*/);
//                startActivity(new Intent(getContext(), ChildActivity.class)/*.putExtra("skip", homeMenuEntities.get(pos).strIntent)*/);
                break;
            case R.id.btn_split_record:
                startActivity(new Intent(mContext, SplitRecordActivity.class)/*.putExtra("skip", homeMenuEntities.get(pos).strIntent)*/);
//                startActivity(new Intent(getContext(), ChildActivity.class)/*.putExtra("skip", homeMenuEntities.get(pos).strIntent)*/);
                break;
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        JSONObject jO = new JSONObject();
//        jO.put("productTypeId", 0);
        jO.put("page", page);
        jO.put("pageSize", 8);
        httpProductPagination(jO);
    }

    /**
     * http Banner查询
     *
     * @param jO bannerType : MALL或者TITLE    //MALL为请求商城banner，TITLE为请求首页banner
     */
    private void httpBannerFind(JSONObject jO) {
        HttpMethods.getInstance().bannerFind(new ProgressSubscriber(bannerFindListener, getContext(), "正在查询钱包信息…", false), jO);
    }


    /**
     * http 产品分页
     *
     * @param jO productTypeId ： 产品类型id
     *           page : 页数
     *           pageSize : 每页数量
     */
    private void httpProductPagination(JSONObject jO) {
        HttpMethods.getInstance().productPagination(new ProgressSubscriber(productPaginationListener, getContext(), "正在查询钱包信息…", false), jO);
    }

    /**
     * http 添加购物车
     *
     * @param jO productId : 产品id
     *           userId : 用户当前登录id
     */
    private void httpShopCarCreate(JSONObject jO) {
        HttpMethods.getInstance().shopCarCreate(new ProgressSubscriber(shopCarCreateListener, getContext(), "正在查询钱包信息…", false), jO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.iv_shop_car)
    public void onClick() {
        startActivity(new Intent(mContext, ShopCarActivity.class));
    }
}
