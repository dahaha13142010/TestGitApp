package com.admin.control.quickRv.inter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.admin.control.quickRv.RvViewHolder;


/**
 * QuickRv     com.march.quickrvlibs.inter
 * Created by 陈栋 on 16/4/12.
 * 功能:
 */
public interface BaseRvAdapter {

    void onAttachedToRecyclerView(RecyclerView recyclerView);

    int getItemCount();

    int getItemViewType(int position);

    RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(RvViewHolder holder, int pos);

    <T extends BaseRvAdapter> T getInAdapter();
}
