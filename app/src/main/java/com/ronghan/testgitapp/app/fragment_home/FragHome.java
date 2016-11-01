package com.ronghan.testgitapp.app.fragment_home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.admin.control.ab.AbSlidingPlayView;
import com.admin.control.quickRv.RvQuickAdapter;
import com.admin.control.quickRv.RvViewHolder;
import com.admin.control.quickRv.inter.OnRecyclerItemClickListener;
import com.admin.mine.BaseFragment;
import com.admin.utils.ABLogUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.app.activity_login_etc.LoginActivity;
import com.ronghan.testgitapp.app.main.MainActivity;
import com.ronghan.testgitapp.app.main.message.MessageActivity;
import com.ronghan.testgitapp.been.BannerEntity;
import com.ronghan.testgitapp.been.IcoEntities;
import com.ronghan.testgitapp.been.IcoEntity;
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
 * 创建时间：2016/4/20 0020 11:00
 * 修改人：Michael
 * 修改时间：2016/4/20 0020 11:00
 * 修改备注：
 */
public class FragHome extends BaseFragment {

    @Bind(R.id.viewpager)
    AbSlidingPlayView viewPager;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private MainActivity activity;

    private RvQuickAdapter rvQuickAdapter;
    private SubscriberOnNextListener bannerFindListener, icoFindAllListener;
    private List<BannerEntity> bannerEntities;
    private IcoEntities icoEntities;
    /**
     * 存储轮播的界面
     */
    private ArrayList<View> allListView;


    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onBaseCreate(View view) {
        icoEntities = new IcoEntities();
        activity = (MainActivity) this.getActivity();
        bannerFindListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                bannerEntities = JSON.parseArray(TextTools.DataDecode(o.toString()), BannerEntity.class);
                initViewPager(bannerEntities.get(0));
            }
        };
        icoFindAllListener = new SubscriberOnNextListener() {

            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                icoEntities.setIcoEntities(JSON.parseArray(TextTools.DataDecode(o.toString()), IcoEntity.class));
                initRv(icoEntities);
            }
        };
        JSONObject jO = new JSONObject();
        jO.put("bannerType", H.TITLE);
        httpBannerFind(jO);
        httpIcoFindAll();
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
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_banner, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
            Glide.with(getContext()).load(bannerEntity.getBannerPics().get(i).getPicUrl()).error(R.mipmap.ic_launcher).into(imageView);
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
                //跳转到详情界面
//                Intent intent = new Intent(getActivity(), BabyActivity.class);
//                startActivity(intent);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        activity.http();
    }

    @OnClick({R.id.iv_icon, R.id.iv_message, R.id.btn_fkm, R.id.btn_scan, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_icon:
                if (MSPUtils.getInstance(getContext()).isLogin()) {
                    activity.openDrawerAtLogin();
                } else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.iv_message:
                startActivity(new Intent(getContext(), MessageActivity.class));
                break;
            case R.id.btn_fkm:
                startActivity(new Intent(getContext(), FkmActivity.class));
                break;
            case R.id.btn_scan:
//                startActivity(new Intent(getContext(),QRActivity.class));
                break;
            case R.id.btn_search:
                break;
        }
    }


    private void initRv(IcoEntities icoEntities) {
        List<IcoEntities> icos = new ArrayList<>();
        IcoEntities icoEntities1 = new IcoEntities();
        List<IcoEntity> icoes = new ArrayList<>();
        for (int i = 0; i < (icoEntities.getIcoEntities().size() / 6); i++) {
            icoes.clear();
            icoes.add(icoEntities.getIcoEntities().get(6 * i));
            icoes.add(icoEntities.getIcoEntities().get(6 * i + 1));
            icoes.add(icoEntities.getIcoEntities().get(6 * i + 2));
            icoes.add(icoEntities.getIcoEntities().get(6 * i + 3));
            icoes.add(icoEntities.getIcoEntities().get(6 * i + 4));
            icoes.add(icoEntities.getIcoEntities().get(6 * i + 5));
            icoEntities1.setIcoEntities(icoes);
            icos.add(icoEntities1);
            ABLogUtil.i("icos:" + icos.get(i).getIcoEntities().get(0).toString());
        }
        icoes.clear();
        for (int i = 0; i < (icoEntities.getIcoEntities().size() % 6); i++) {
            icoes.add(icoEntities.getIcoEntities().get(i));
        }
        icoEntities1.setIcoEntities(icoes);
        icos.add(icoEntities1);
        ABLogUtil.i("icos.size():" + icos.size());

        // 单类型适配
        rvQuickAdapter = new RvQuickAdapter<IcoEntities>(getContext(), icos, R.layout.item_home_menu) {
            @Override
            public void bindData4View(RvViewHolder holder, IcoEntities data, int pos, int type) {
                holder.setText(R.id.tv_menu_1, data.getIcoEntities().size() > 0 ? data.getIcoEntities().get(0).getName() : "");
                holder.setText(R.id.tv_menu_2, data.getIcoEntities().size() > 1 ? data.getIcoEntities().get(1).getName() : "");
                holder.setText(R.id.tv_menu_3, data.getIcoEntities().size() > 2 ? data.getIcoEntities().get(2).getName() : "");
                holder.setText(R.id.tv_menu_4, data.getIcoEntities().size() > 3 ? data.getIcoEntities().get(3).getName() : "");
                holder.setText(R.id.tv_menu_5, data.getIcoEntities().size() > 4 ? data.getIcoEntities().get(4).getName() : "");
                holder.setText(R.id.tv_menu_6, data.getIcoEntities().size() > 5 ? data.getIcoEntities().get(5).getName() : "");
                holder.setImg(getContext(), R.id.iv_menu_1, data.getIcoEntities().size() > 0 ? data.getIcoEntities().get(0).getIcoPics().get(0).getPicUrl() : "");
                holder.setImg(getContext(), R.id.iv_menu_2, data.getIcoEntities().size() > 1 ? data.getIcoEntities().get(1).getIcoPics().get(0).getPicUrl() : "");
                holder.setImg(getContext(), R.id.iv_menu_3, data.getIcoEntities().size() > 2 ? data.getIcoEntities().get(2).getIcoPics().get(0).getPicUrl() : "");
                holder.setImg(getContext(), R.id.iv_menu_4, data.getIcoEntities().size() > 3 ? data.getIcoEntities().get(3).getIcoPics().get(0).getPicUrl() : "");
                holder.setImg(getContext(), R.id.iv_menu_5, data.getIcoEntities().size() > 4 ? data.getIcoEntities().get(4).getIcoPics().get(0).getPicUrl() : "");
                holder.setImg(getContext(), R.id.iv_menu_6, data.getIcoEntities().size() > 5 ? data.getIcoEntities().get(5).getIcoPics().get(0).getPicUrl() : "");
            }
        };
        //设置item监听
        rvQuickAdapter.setClickListener(new OnRecyclerItemClickListener<RvViewHolder>() {
            @Override
            public void onItemClick(int pos, RvViewHolder holder) {
//                startActivity(new Intent(getContext(), ChildActivity.class)/*.putExtra("skip", homeMenuEntities.get(pos).strIntent)*/);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(rvQuickAdapter);
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
     * http ico信息查询
     */
    private void httpIcoFindAll() {
        HttpMethods.getInstance().icoFindAll(new ProgressSubscriber(icoFindAllListener, getContext(), "正在查询钱包信息…", false));
    }
}
