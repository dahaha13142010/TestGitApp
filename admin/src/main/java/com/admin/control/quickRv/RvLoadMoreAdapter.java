package com.admin.control.quickRv;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.admin.control.quickRv.inter.BaseRvAdapter;


/**
 * QuickRv     com.march.quickrvlibs
 * Created by 陈栋 on 16/4/12.
 * 功能:
 */
public class RvLoadMoreAdapter extends RecyclerView.Adapter<RvViewHolder> implements BaseRvAdapter {

    private BaseRvAdapter inAdapter;
    private OnLoadMoreListener mLoadMoreListener;
    private RecyclerView mRecyclerView;
    private boolean mIsLoadingMore;
    private int preLoadNum = 0;
    private boolean isEnding = false;
    public RvLoadMoreAdapter(BaseRvAdapter inAdapter, OnLoadMoreListener loadMoreListener) {
        this.inAdapter = inAdapter;
        this.mLoadMoreListener = loadMoreListener;
    }

    public RvLoadMoreAdapter(BaseRvAdapter inAdapter) {
        this(inAdapter, null);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public <T extends BaseRvAdapter> T getInAdapter() {
        return (T) inAdapter;
    }


    public void setPreLoadNum(int preLoadNum) {
        this.preLoadNum = preLoadNum;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        inAdapter.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && isEnding) {
                    if (null != mLoadMoreListener && !mIsLoadingMore) {
                        mLoadMoreListener.onLoadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (null != mLoadMoreListener && dy > 0) {
                    int lastVisiblePosition = getLastVisiblePosition();
                    isEnding = lastVisiblePosition + 1 + preLoadNum == inAdapter.getItemCount();
                }
            }
        });
    }

    /**
     * 获取最后一条展示的位置
     *
     * @return pos
     */
    private int getLastVisiblePosition() {
        int position;
        RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            position = ((GridLayoutManager) manager).findLastVisibleItemPosition();
        } else if (manager instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
        } else if (manager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) manager;
            int[] lastPositions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMaxPosition(lastPositions);
        } else {
            position = manager.getItemCount() - 1;
        }
        return position;
    }

    /**
     * 获得最大的位置
     *
     * @param positions 位置
     * @return pos
     */
    private int getMaxPosition(int[] positions) {
        int maxPosition = Integer.MIN_VALUE;
        for (int position : positions) {
            maxPosition = Math.max(maxPosition, position);
        }
        return maxPosition;
    }

    public void finishLoad() {
        this.mIsLoadingMore = false;
    }

    public void setLoadMoreListener(OnLoadMoreListener mLoadMoreListener) {
        this.mLoadMoreListener = mLoadMoreListener;
    }


    @Override
    public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return inAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RvViewHolder holder, int position) {
        inAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return inAdapter.getItemCount();
    }


    @Override
    public int getItemViewType(int position) {
        return inAdapter.getItemViewType(position);
    }
}
