package com.ronghan.testgitapp.app.main.my_wallet;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.admin.control.topTitleBackBar.TopTitleBackBar;
import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSONObject;
import com.ronghan.testgitapp.R;
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
public class TransferRecordActivity extends BaseActivity {

    private SubscriberOnNextListener transferRecordPaginationListener;
    @Bind(R.id.title)
    TopTitleBackBar title;
    @Bind(R.id.tv_numbers)
    TextView tvHint;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_my_wallet_record;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title.setTitle(R.string.transfer_record);
        tvHint.setVisibility(View.GONE);
        transferRecordPaginationListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                TextTools.DataDecode(o.toString());
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        JSONObject jO = new JSONObject();
        jO.put("userId", MSPUtils.getInstance(TransferRecordActivity.this).getLoginSp().id);
        jO.put("page", 1);
        jO.put("pageSize", 10);
        httpTransferRecordPagination(jO);
    }

    /**
     * 转账查询
     *
     * @param jO userId : 当前登录者（id）
     *           page : 页数
     *           pageSize : 每页数量
     */
    private void httpTransferRecordPagination(JSONObject jO) {
        HttpMethods.getInstance().transferRecordPagination(new ProgressSubscriber(transferRecordPaginationListener, TransferRecordActivity.this, "正在查询…", true), jO);
    }
}
