package com.ronghan.testgitapp.app.fragment_mall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.admin.control.quickRv.RvHFAdapter;
import com.admin.control.quickRv.RvLoadMoreAdapter;
import com.admin.control.quickRv.RvQuickAdapter;
import com.admin.control.quickRv.RvViewHolder;
import com.admin.control.quickRv.inter.OnRecyclerItemClickListener;
import com.admin.control.topTitleBackBar.TopTitleBackBar;
import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.app.activity_login_etc.LoginActivity;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.been.ProductionEntity;
import com.ronghan.testgitapp.been.ProductionTypeEntity;
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
 * 创建时间：2016/5/31 0031 16:35
 * 修改人：Michael
 * 修改时间：2016/5/31 0031 16:35
 * 修改备注：
 */
public class GoodsCategoryActivity extends BaseActivity {

    @Bind(R.id.title)
    TopTitleBackBar title;
    @Bind(R.id.rv_left)
    RecyclerView rvLeft;
    @Bind(R.id.rv_right)
    RecyclerView rvRight;

    private Context mContext;
    private AppUserEntity appUserEntity;
    private ProductionEntity productionEntity;
    private List<ProductionTypeEntity> productionTypeEntities;
    private String productionType;

    private SubscriberOnNextListener productTypeListListener, productPaginationListener, shopCarCreateListener;

    private RvLoadMoreAdapter rvLoadMoreAdapterLeft;
    private RvQuickAdapter rvQuickAdapterLeft;
    private RvLoadMoreAdapter rvLoadMoreAdapterRight;
    private RvQuickAdapter rvQuickAdapterRight;
    private int page = 1;
    private boolean isMore = false;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_goods_category;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (MSPUtils.getInstance(mContext).isLogin()) {
            appUserEntity = MSPUtils.getInstance(mContext).getAppUserSp();
        }
        title.setOnClickListenerRightIm(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, OrderListActivity.class));
            }
        });

        productTypeListListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                productionTypeEntities = JSON.parseArray(TextTools.DataDecode(o.toString()), ProductionTypeEntity.class);
                initRvLeft(productionTypeEntities);
            }
        };
        productPaginationListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object s) throws UnsupportedEncodingException {
                productionEntity = JSON.parseObject(TextTools.DataDecode(s.toString()), ProductionEntity.class);
                isMore = productionEntity.getCount() > productionEntity.getPageSize() * productionEntity.getPage();
                initRvRight(productionEntity);
            }
        };

        shopCarCreateListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                showToast("添加成功！");
            }
        };
        httpProductTypeList();
        JSONObject jO = new JSONObject();
        jO.put("page", page);
        jO.put("pageSize", H.PAGE_SIZE);
        httpProductPagination(jO);
    }

    private void initRvRight(final ProductionEntity productionEntity) {// 单类型适配
        rvQuickAdapterRight = new RvQuickAdapter<ProductionEntity.DataBean>(mContext, productionEntity.getData(), R.layout.item_products_type_list_right) {
            @Override
            public void bindData4View(RvViewHolder holder, final ProductionEntity.DataBean data, int pos, int type) {
                holder.setImg(mContext, R.id.iv_production, data.getProductPics().get(0).getUrl());
                holder.setText(R.id.tv_production_name, data.getName());
                holder.setText(R.id.tv_production_description, data.getRemark());
                holder.setText(R.id.tv_production_price, "￥: " + data.getSalePrice());
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
                holder.setLis(R.id.btn_buy_now, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (MSPUtils.getInstance(mContext).isLogin()) {
                            showToast("购买：" + data.getName() + ",id:" + data.getId());
                        } else {
                            startActivity(new Intent(mContext, LoginActivity.class));
                        }
                    }
                });
            }

        };
        //   设置item监听
        rvQuickAdapterRight.setClickListener(new OnRecyclerItemClickListener<RvViewHolder>() {
            @Override
            public void onItemClick(int pos, RvViewHolder holder) {
                startActivity(new Intent(mContext, ProductionDetailsActivity.class).putExtra(H.TAG_INTENT, productionEntity.getData().get(pos).getId()));
            }
        });


        //使用RvHFAdapter包装,实现添加Header和Footer
        final RvHFAdapter rvHFQuickAdapter = new RvHFAdapter(rvQuickAdapterRight) {
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

//        rvHFQuickAdapter.addFooter(R.activity_goods_category.rvquick_footer);

        //上拉加载更多
        rvLoadMoreAdapterRight = new RvLoadMoreAdapter(rvHFQuickAdapter, new RvLoadMoreAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (isMore) {
                    page++;
                    JSONObject jO = new JSONObject();
                    if (!TextUtils.isEmpty(productionType))
                        jO.put("productTypeId", productionType);
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
//        rvRight.addItemDecoration(new GridDividerDecoration(SplitDetailsActivity.this, line));
        rvRight.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvRight.setAdapter(rvLoadMoreAdapterRight);
    }

    private void initRvLeft(final List<ProductionTypeEntity> productionTypeEntities) {// 单类型适配
        rvQuickAdapterLeft = new RvQuickAdapter<ProductionTypeEntity>(mContext, productionTypeEntities, R.layout.item_production_type_list) {
            @Override
            public void bindData4View(RvViewHolder holder, final ProductionTypeEntity data, int pos, int type) {
                holder.setText(R.id.tv_type, data.getName());
            }

        };
        //设置item监听
        rvQuickAdapterLeft.setClickListener(new OnRecyclerItemClickListener<RvViewHolder>() {
            @Override
            public void onItemClick(int pos, RvViewHolder holder) {
                productionType = productionTypeEntities.get(pos).getId();
                JSONObject jO = new JSONObject();
                jO.put("productTypeId", productionType);
                jO.put("page", page);
                jO.put("pageSize", H.PAGE_SIZE);
                httpProductPagination(jO);
            }
        });


        //使用RvHFAdapter包装,实现添加Header和Footer
        final RvHFAdapter rvHFQuickAdapter = new RvHFAdapter(rvQuickAdapterLeft) {
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

        //上拉加载更多
        rvLoadMoreAdapterLeft = new RvLoadMoreAdapter(rvHFQuickAdapter, new RvLoadMoreAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
            }
        });
        //你可以使用shape自定义分割线样式
//        Drawable line = ContextCompat.getDrawable(SplitDetailsActivity.this, R.drawable.shape_line);
//        rvLeft.addItemDecoration(new GridDividerDecoration(SplitDetailsActivity.this, line));
//        rvLeft.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        rvLeft.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rvLeft.setAdapter(rvLoadMoreAdapterLeft);
    }

    /**
     * http 产品类型
     */
    private void httpProductTypeList() {
        HttpMethods.getInstance().productTypeList(new ProgressSubscriber(productTypeListListener, mContext, "正在查询钱包信息…", false));
    }

    /**
     * http 产品分页
     *
     * @param jO productTypeId ： 产品类型id
     *           page : 页数
     *           pageSize : 每页数量
     */
    private void httpProductPagination(JSONObject jO) {
        HttpMethods.getInstance().productPagination(new ProgressSubscriber(productPaginationListener, mContext, "正在查询钱包信息…", false), jO);
    }

    /**
     * http 添加购物车
     *
     * @param jO productId : 产品id
     *           userId : 用户当前登录id
     */
    private void httpShopCarCreate(JSONObject jO) {
        HttpMethods.getInstance().shopCarCreate(new ProgressSubscriber(shopCarCreateListener, mContext, "正在查询钱包信息…", false), jO);
    }
}
