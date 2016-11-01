package com.ronghan.testgitapp.app.main.my_promotion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.been.AppUserEntity;
import com.ronghan.testgitapp.been.PromotionStatisticEntity;
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
 * 创建时间：2016/5/16 0016 14:24
 * 修改人：Michael
 * 修改时间：2016/5/16 0016 14:24
 * 修改备注：
 */
public class MyPromotionActivity extends BaseActivity {
    @Bind(R.id.tv_clerk_direct)
    TextView tvClerkDirect;
    @Bind(R.id.tv_clerk_indirect)
    TextView tvClerkIndirect;
    @Bind(R.id.tv_shopowner_direct)
    TextView tvShopownerDirect;
    @Bind(R.id.tv_shopowner_indirect)
    TextView tvShopownerIndirect;
    @Bind(R.id.tv_boss_direct)
    TextView tvBossDirect;
    @Bind(R.id.tv_boss_indirect)
    TextView tvBossIndirect;
    @Bind(R.id.tv_hint)
    TextView tvTotalNumber;

    private SubscriberOnNextListener promotionStatisticListener;
    private AppUserEntity appUserEntity;
    private List<PromotionStatisticEntity> promotionStatisticEntities;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_my_promotion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appUserEntity = MSPUtils.getInstance(MyPromotionActivity.this).getAppUserSp();
        promotionStatisticListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                promotionStatisticEntities = JSON.parseArray(TextTools.DataDecode(o.toString()), PromotionStatisticEntity.class);
                tvClerkDirect.setText("直接" + promotionStatisticEntities.get(0).getClerkDirect() + "人");
                tvShopownerDirect.setText("直接" + promotionStatisticEntities.get(0).getShopOwnerDirect() + "人");
                tvBossDirect.setText("直接" + promotionStatisticEntities.get(0).getBossDirect() + "人");
                tvClerkIndirect.setText("间接" + promotionStatisticEntities.get(0).getClerkIndirect() + "人");
                tvShopownerIndirect.setText("间接" + promotionStatisticEntities.get(0).getShopOwnerIndirect() + "人");
                tvBossIndirect.setText("间接" + promotionStatisticEntities.get(0).getBossIndirect() + "人");
                tvTotalNumber.setText("全部" + (promotionStatisticEntities.get(0).getClerkDirect()
                        + promotionStatisticEntities.get(0).getShopOwnerDirect()
                        + promotionStatisticEntities.get(0).getBossDirect()
                        + promotionStatisticEntities.get(0).getClerkIndirect()
                        + promotionStatisticEntities.get(0).getShopOwnerIndirect()
                        + promotionStatisticEntities.get(0).getBossIndirect()) + "人");
            }
        };
        JSONObject jO = new JSONObject();
        jO.put("userId", appUserEntity.getUserId());
        httpPromotionStatistic(jO);
    }

    @OnClick({R.id.rl_clerk, R.id.rl_shopowner, R.id.rl_boss})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_clerk:
                startActivity(new Intent(MyPromotionActivity.this, MyPromotionDetailsActivity.class).putExtra(H.TAG_INTENT, H.CLERK));
                break;
            case R.id.rl_shopowner:
                startActivity(new Intent(MyPromotionActivity.this, MyPromotionDetailsActivity.class).putExtra(H.TAG_INTENT, H.SHOPOWNER));
                break;
            case R.id.rl_boss:
                startActivity(new Intent(MyPromotionActivity.this, MyPromotionDetailsActivity.class).putExtra(H.TAG_INTENT, H.BOSS));
                break;
        }
    }

    /**
     * http推广数据查询
     *
     * @param jO userId : 当前登录者（id）
     */
    private void httpPromotionStatistic(JSONObject jO) {
        HttpMethods.getInstance().promotionStatistic(new ProgressSubscriber(promotionStatisticListener, MyPromotionActivity.this, "正在查询钱包信息…", false), jO);
    }
}
