package com.admin.control.MListView;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：JNIDemo
 * 类描述：
 * 创建人：Michael_hj
 * 创建时间：2015/12/7 0007 15:24
 * 修改人：Michael_hj
 * 修改时间：2015/12/7 0007 15:24
 * 修改备注：
 */
public abstract class MAdapter<T> extends BaseAdapter {
    private Activity activityContext;
    private ArrayList<T> list = new ArrayList<>();
    protected int mItemLayoutId;

    protected MAdapter(Activity activity, int itemLayoutId) {
        init(activity, new ArrayList<T>(), itemLayoutId);
    }

    protected MAdapter(Activity activity, ArrayList<T> mDatas, int itemLayoutId) {
        init(activity, mDatas, itemLayoutId);
    }

    /**
     * ================================================初始化数据================================================================
     */
    protected void init(Activity activity, ArrayList<T> mDatas, int itemLayoutId) {
        if (activity == null)
            throw new NullPointerException("上下文传入为空");
        else
            this.activityContext = activity;
        if (mDatas != null)
            this.list = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    /**
     * ==========================================================================================================================
     */
    public void setData(ArrayList<T> list) {
        initData(list);
    }

    public void setDataNotify(ArrayList<T> list) {
        initData(list);
        notifyDataSetChanged();
    }

    private void initData(ArrayList<T> list) {
        if (list != null && list.size() > 0)
            this.list = list;
        else
            throw new NullPointerException("传入数据有误");
    }

    /**
     * =============================================================================================================
     */
    @Override
    public int getCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * =============================================================================================================
     */
    public void insert(T data) {
        list.add(0, data);
        this.notifyDataSetChanged();
    }

    public void append(T data) {
        list.add(data);
        this.notifyDataSetChanged();
    }

    public void replace(T data) {
        int idx = this.list.indexOf(data);
        this.replace(idx, data);
    }

    public void replace(int index, T data) {
        if (index < 0) return;
        if (index > list.size() - 1) return;
        list.set(index, data);
        this.notifyDataSetChanged();
    }

    public List getItems() {
        return list;
    }

    public void removeItem(int position) {
        if (list.size() <= 0) return;
        if (position < 0) return;
        if (position > list.size() - 1) return;
        list.remove(position);
        this.notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        this.notifyDataSetChanged();
    }

    /**
     * =============================================================================================================
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MViewHolder viewHolder = MViewHolder.get(activityContext, convertView, parent, mItemLayoutId, position);
        ItemConvert(viewHolder, position, getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void ItemConvert(MViewHolder helper, int position, T item);
}
