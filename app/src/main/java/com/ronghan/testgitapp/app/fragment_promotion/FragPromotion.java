package com.ronghan.testgitapp.app.fragment_promotion;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.admin.mine.BaseFragment;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.been.PromotionEntity;
import com.ronghan.testgitapp.been.PromotionSearchEntity;
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
 * 创建时间：2016/4/26 0026 9:51
 * 修改人：Michael
 * 修改时间：2016/4/26 0026 9:51
 * 修改备注：
 */
public class FragPromotion extends BaseFragment {
    @Bind(R.id.tv_level)
    TextView tvLevel;
    @Bind(R.id.tv_statue)
    TextView tvStatue;
    @Bind(R.id.tv_boss_number)
    TextView tvBossNumber;
    @Bind(R.id.tv_shopowner_number)
    TextView tvShopownerNumber;

    private SubscriberOnNextListener parameterFindAllListener, promotionSearchListener;
    private List<PromotionEntity> promotionEntities;
    private List<PromotionSearchEntity> promotionSearchEntities;
    private Context context;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_promotion;
    }

    @Override
    protected void onBaseCreate(View view) {
        context = getContext();
        parameterFindAllListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                promotionEntities = JSON.parseArray(TextTools.DataDecode(o.toString()), PromotionEntity.class);
                for (PromotionEntity promotionEntity : promotionEntities) {
                    if (TextUtils.equals("shopManagerNumber", promotionEntity.getParamKey())) {
                        tvBossNumber.setText("推广" + promotionEntity.getParamValue() + "人");
                    }
                }
                for (PromotionEntity promotionEntity : promotionEntities) {
                    if (TextUtils.equals("clerkNumber", promotionEntity.getParamKey())) {
                        tvShopownerNumber.setText("推广" + promotionEntity.getParamValue() + "人");
                    }
                }
            }
        };
        promotionSearchListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                promotionSearchEntities = JSON.parseArray(TextTools.DataDecode(o.toString()), PromotionSearchEntity.class);
                tvStatue.setText(promotionSearchEntities.get(0).getIndirectValid());
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MSPUtils.getInstance(context).isLogin()) {
            AppUserEntity appUserEntity = MSPUtils.getInstance(context).getAppUserSp();
            JSONObject jO = new JSONObject();
            jO.put("userId", appUserEntity.getUserId());
            httpPromotionSearch(jO);
            tvLevel.setText(TextTools.getLevel(appUserEntity.getNowRole()));
        }
        httpParameter();
    }

    @OnClick({R.id.btn_income, R.id.btn_partner})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_income:
//                startActivity(new Intent(getContext(), ChildActivity.class)/*.putExtra("skip", homeMenuEntities.get(pos).strIntent)*/);
                break;
            case R.id.btn_partner:
//                startActivity(new Intent(getContext(), ChildActivity.class)/*.putExtra("skip", homeMenuEntities.get(pos).strIntent)*/);
                break;
        }
    }

    /**
     * parameter推广参数信息
     */
    public void httpParameter() {
        HttpMethods.getInstance().parameterFindAll(new ProgressSubscriber(parameterFindAllListener, getContext(), "...", true));
    }

    /**
     * 推广数据查询
     *
     * @param jO userId : 登录者账号id
     */
    public void httpPromotionSearch(JSONObject jO) {
        HttpMethods.getInstance().promotionSearch(new ProgressSubscriber(promotionSearchListener, getContext(), "...", true), jO);
    }
}
