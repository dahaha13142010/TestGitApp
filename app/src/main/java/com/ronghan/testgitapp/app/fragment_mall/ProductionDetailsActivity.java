package com.ronghan.testgitapp.app.fragment_mall;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.admin.control.ab.AbSlidingPlayView;
import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.been.GoodsEntities;
import com.ronghan.testgitapp.constant.H;
import com.ronghan.testgitapp.http.HttpMethods;
import com.ronghan.testgitapp.tools.MSPUtils;
import com.ronghan.testgitapp.tools.TextTools;
import com.ronghan.testgitapp.tools.subscribers.ProgressSubscriber;
import com.ronghan.testgitapp.tools.subscribers.SubscriberOnNextListener;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
public class ProductionDetailsActivity extends BaseActivity {

    @Bind(R.id.viewpager)
    AbSlidingPlayView viewPager;
    @Bind(R.id.shop_title)
    TextView shop_title;//标题介绍
    @Bind(R.id.shop_introduce)
    TextView shop_introduce;//详情介绍
    @Bind(R.id.shop_price)
    TextView shop_price;//价格

    SubscriberOnNextListener bannerFindListener,shopCarCreateListener;
    AppUserEntity appUserEntity;

    String goods_id = null;
    List<GoodsEntities> goodsEntities;
    private ArrayList<View> allListView = null;//轮播界面

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_productionetails_xml;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goods_id = getIntent().getExtras().getString(H.TAG_INTENT);
        if (MSPUtils.getInstance(this).isLogin()) {
            appUserEntity = MSPUtils.getInstance(this).getAppUserSp();
        }
        bannerFindListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                String result = TextTools.DataDecode(o.toString());
                goodsEntities = JSON.parseArray(result, GoodsEntities.class);
                setData(goodsEntities.get(0));
            }
        };
        shopCarCreateListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                showToast("添加成功！");
            }
        };
        List<String> ids = new ArrayList<>();
        ids.add(goods_id);
        JSONObject ob = new JSONObject();
        ob.put("ids", ids);
        httpBannerFind(ob);
    }

    private void setData(GoodsEntities ge) {
        setImage(ge.getProductPics());

        shop_title.setText(ge.getName());
        shop_introduce.setText(ge.getRemark());
        shop_price.setText("¥ " + ge.getSalePrice());
    }

    //设置图片
    private void setImage(List<GoodsEntities.ProductPicsEntity> productPics) {
        //设置播放方式为顺序播放
        viewPager.setPlayType(1);
        //设置播放间隔时间
        viewPager.setSleepTime(3000);
        viewPager.setNavHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        Bitmap bitmapPointY = BitmapFactory.decodeResource(this.getResources(), R.mipmap.point_y);
        Bitmap bitmapPointN = BitmapFactory.decodeResource(this.getResources(), R.mipmap.point_n);
        viewPager.setPageLineImage(bitmapPointY, bitmapPointN);
        allListView = new ArrayList<>();
        for (GoodsEntities.ProductPicsEntity image_data : productPics) {
            //导入ViewPager的布局
            View view = LayoutInflater.from(this).inflate(R.layout.item_banner, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
            Glide.with(this).load(image_data.getUrl()).error(R.mipmap.ic_launcher).into(imageView);
//            Picasso.with(getContext()).load(bannerEntity.getBannerPics().get(i).getPicUrl()).error(R.mipmap.ic_launcher).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            allListView.add(view);
        }
        viewPager.addViews(allListView);
        //开始轮播
        viewPager.startPlay();
        viewPager.setOnItemClickListener(new AbSlidingPlayView.AbOnItemClickListener() {
            @Override
            public void onClick(int position) {
//                Intent intent = new Intent(ProductionDetailsActivity.this, BabyActivity.class);
//                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.add_shoppingCart, R.id.buy_shop})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_shoppingCart:
                JSONObject jO = new JSONObject();
                jO.put("productId", goods_id);
                jO.put("userId", appUserEntity.getUserId());
                httpShopCarCreate(jO);
                break;
            case R.id.buy_shop:
                startActivity(new Intent(ProductionDetailsActivity.this, OrderDetailsActivity.class).putExtra(H.TAG_INTENT, (Serializable) goodsEntities));
                break;
        }
    }

    /**
     * http 添加购物车
     *
     * @param jO productId : 产品id
     *           userId : 用户当前登录id
     */
    private void httpShopCarCreate(JSONObject jO) {
        HttpMethods.getInstance().shopCarCreate(new ProgressSubscriber(shopCarCreateListener, this, "正在查询钱包信息…", false), jO);
    }



    private void httpBannerFind(JSONObject ob) {
        HttpMethods.getInstance().productSearch(new ProgressSubscriber(bannerFindListener, this, "正在查询商品详情...", false), ob);
    }
}
