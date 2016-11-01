package com.admin.control.quickRv.inter;


import com.admin.control.quickRv.RvViewHolder;

/**
 * CdLibsTest     com.march.libs.recyclerlibs
 * Created by 陈栋 on 16/2/4.
 * 功能:
 */
public interface OnRecyclerItemLongClickListener<T extends RvViewHolder>{
    void onItemLongClick(int pos, T holder);
}
