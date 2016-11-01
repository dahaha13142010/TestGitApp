/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.admin.control.ab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

import com.nineoldandroids.view.ViewHelper;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc

/**
 * 名称：AbInnerViewPager.java
 * 描述：这个ViewPager解决了外部是可滚动View（List或者scrollView）
 * 与内部可滑动View的事件冲突问题
 *
 * @version v1.0
 */
public class AbInnerViewPager extends ViewPager {

    private View mLeft;
    private View mRight;

    private float mTrans;
    private float mScale;

    private static final float MIN_SCALE = 0.6f;
    private Map<Integer, View> mChildren = new HashMap<Integer, View>();
    /**
     * The parent scroll view.
     */
    private ScrollView parentScrollView;

    /**
     * The parent list view.
     */
    private ListView parentListView;

    /**
     * The m gesture detector.
     */
    private GestureDetector mGestureDetector;

    /**
     * 初始化这个内部的ViewPager.
     *
     * @param context the context
     */
    public AbInnerViewPager(Context context) {
        super(context);
        mGestureDetector = new GestureDetector(new YScrollDetector());
        setFadingEdgeLength(0);
    }

    /**
     * 初始化这个内部的ViewPager.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public AbInnerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(new YScrollDetector());
        setFadingEdgeLength(0);
    }

    /**
     * 设置put的方法
     */
    public void setViewForPosition(View view, int position) {
        mChildren.put(position, view);
    }

    /**
     * remove的方法
     */
    public void removeViewFromPosition(Integer position) {
        mChildren.remove(position);
    }

    /**
     * 描述：拦截事件.
     *
     * @param ev the ev
     * @return true, if successful
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev)
                && mGestureDetector.onTouchEvent(ev);
    }

    /**
     * 设置父级的View.
     *
     * @param flag 父是否滚动开关
     */
    private void setParentScrollAble(boolean flag) {
        if (parentScrollView != null) {
            parentScrollView.requestDisallowInterceptTouchEvent(!flag);
        }

        if (parentListView != null) {
            parentListView.requestDisallowInterceptTouchEvent(!flag);
        }

    }

    /**
     * 如果外层有ScrollView需要设置.
     *
     * @param parentScrollView the new parent scroll view
     */
    public void setParentScrollView(ScrollView parentScrollView) {
        this.parentScrollView = parentScrollView;
    }

    /**
     * 如果外层有ListView需要设置.
     *
     * @param parentListView the new parent scroll view
     */
    public void setParentListView(ListView parentListView) {
        this.parentListView = parentListView;
    }


    /**
     * 重写的方法
     */
    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {

        // Log.e("TAG", "position =" + position + ",offset = " + offset);
        mLeft = mChildren.get(position);
        mRight = mChildren.get(position + 1);

        animStack(mLeft, mRight, offset, offsetPixels);// 创建动画效果

        super.onPageScrolled(position, offset, offsetPixels);
    }

    private void animStack(View left, View right, float offset, int offsetPixels) {
        if (right != null) {

            // 从0-1页，offset:0`1
            mScale = (1 - MIN_SCALE) * offset + MIN_SCALE;

            mTrans = -getWidth() - getPageMargin() + offsetPixels;

            ViewHelper.setScaleX(right, mScale);
            ViewHelper.setScaleY(right, mScale);

            ViewHelper.setTranslationX(right, mTrans);
        }
        if (left != null) {
            left.bringToFront();
        }
    }

    /**
     * The Class YScrollDetector.
     */
    class YScrollDetector extends SimpleOnGestureListener {

        /* (non-Javadoc)
         * @see android.view.GestureDetector.SimpleOnGestureListener#onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float)
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {

            if (Math.abs(distanceX) >= Math.abs(distanceY)) {
                //父亲不滑动
                setParentScrollAble(false);
                return true;
            } else {
                setParentScrollAble(true);
            }
            return false;
        }
    }


}
