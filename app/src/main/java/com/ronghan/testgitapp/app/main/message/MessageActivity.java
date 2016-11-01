package com.ronghan.testgitapp.app.main.message;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.admin.control.quickRv.RvHFAdapter;
import com.admin.control.quickRv.RvLoadMoreAdapter;
import com.admin.control.quickRv.RvQuickAdapter;
import com.admin.control.quickRv.RvViewHolder;
import com.admin.control.quickRv.inter.OnRecyclerItemClickListener;
import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSON;
import com.ronghan.testgitapp.R;
import com.ronghan.testgitapp.been.MessageEntity;
import com.ronghan.testgitapp.http.HttpMethods;
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
public class MessageActivity extends BaseActivity {

    @Bind(R.id.rv)
    RecyclerView recyclerView;

    private List<MessageEntity> messageEntities;
    private SubscriberOnNextListener notificationFindAllListener;
    private RvLoadMoreAdapter rvLoadMoreAdapter;
    private RvQuickAdapter rvQuickAdapter;
    private int i = 0;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationFindAllListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object s) throws UnsupportedEncodingException {
                messageEntities = JSON.parseArray(TextTools.DataDecode(s.toString()), MessageEntity.class);
                initRv(messageEntities);
            }
        };
        httpNotificationFindAll();
    }

    private void initRv(final List<MessageEntity> messageEntities) {

        // 单类型适配
        rvQuickAdapter = new RvQuickAdapter<MessageEntity>(MessageActivity.this, messageEntities, R.layout.item_message) {

            @Override
            public void bindData4View(RvViewHolder holder, MessageEntity messageEntity, int pos, int type) {
                holder.setText(R.id.tv_message_time, String.valueOf(messageEntity.getStartDate()));
                holder.setText(R.id.tv_message, messageEntity.getContent());
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
//        Drawable line = ContextCompat.getDrawable(MessageActivity.this, R.drawable.shape_line);
//        recyclerView.addItemDecoration(new GridDividerDecoration(MessageActivity.this, line));
        recyclerView.setLayoutManager(new LinearLayoutManager(MessageActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(rvLoadMoreAdapter);
    }

    /**
     * 查询所有推送消息
     */
    private void httpNotificationFindAll() {
        HttpMethods.getInstance().notificationFindAll(new ProgressSubscriber(notificationFindAllListener, MessageActivity.this, "正在查询…", true));
    }
}
