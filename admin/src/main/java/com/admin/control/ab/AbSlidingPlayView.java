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
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.admin.utils.ABFileUtil;

import java.util.ArrayList;
import java.util.List;
// TODO: Auto-generated Javadoc

/**
 * 名称：AbPlayView
 * 描述：可播放显示的View.
 *
 * @date 2011-11-28
 */

public class AbSlidingPlayView extends LinearLayout {

    /**
     * 上下文.
     */
    private Context context;

    /**
     * 内部的ViewPager.
     */
    private AbInnerViewPager mViewPager;

    /**
     * 导航的布局.
     */
    private LinearLayout navLinearLayout;

    /**
     * 导航布局参数.
     */
    public LayoutParams navLayoutParams = null;

    /**
     * 计数.
     */
    private int count, position;

    /**
     * 导航图片.
     */
    private Bitmap displayImage, hideImage;

    /**
     * 点击.
     */
    private AbOnItemClickListener mOnItemClickListener;

    /**
     * 改变.
     */
    private AbOnChangeListener mAbChangeListener;

    /**
     * 滚动.
     */
    private AbOnScrollListener mAbScrolledListener;

    /**
     * 触摸.
     */
    private AbOnTouchListener mAbOnTouchListener;

    //TODO >>
    /** The layout params ff. */
    public LinearLayout.LayoutParams layoutParamsFF = null;

    /** The layout params fw. */
    public LinearLayout.LayoutParams layoutParamsFW = null;

    /** The layout params wf. */
    public LinearLayout.LayoutParams layoutParamsWF = null;
    // TODO  <<

    /**
     * List views.
     */
    private ArrayList<View> mListViews = null;

    /**
     * 适配器.
     */
    private AbViewPagerAdapter mAbViewPagerAdapter = null;

    /**
     * 导航的点父View.
     */
    private LinearLayout mNavLayoutParent = null;

    /**
     * 导航内容的对齐方式.
     */
    private int navHorizontalGravity = Gravity.RIGHT;

    /**
     * 播放的方向.
     */
    private int playingDirection = 0;

    /**
     * 播放的开关.
     */
    private boolean play = false;

    //TODO >>
    /** 播放的间隔时间 */
    private int sleepTime = 5000;
    /** 播放方向方式（1顺序播放和0来回播放） */
    private int playType = 1;
    //TODO <<

    /**
     * 当前页面
     */
    private int nowPlayIndex = 0;

    public int getNowPlayIndex() {
        return nowPlayIndex;
    }

    public void setNowPlayIndex(int nowPlayIndex) {
        this.nowPlayIndex = nowPlayIndex;
    }

    /**
     * 创建一个AbSlidingPlayView.
     *
     * @param context the context
     */
    public AbSlidingPlayView(Context context) {
        super(context);
        initView(context);
    }

    /**
     * 从xml初始化的AbSlidingPlayView.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public AbSlidingPlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    /**
     * 描述：初始化这个View.
     *
     * @param context the context
     */
    public void initView(Context context) {
        this.context = context;
        //TODO >>
        layoutParamsFF = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParamsFW = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParamsWF = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        //TODO <<
        navLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        this.setOrientation(LinearLayout.VERTICAL);

        RelativeLayout mRelativeLayout = new RelativeLayout(context);
        mViewPager = new AbInnerViewPager(context);
        //手动创建的ViewPager,如果用fragment必须调用setId()方法设置一个id
//        mViewPager.setId(1985);
        //导航的点
        mNavLayoutParent = new LinearLayout(context);
        mNavLayoutParent.setPadding(0, 5, 0, 5);
        navLinearLayout = new LinearLayout(context);
        navLinearLayout.setPadding(15, 1, 15, 1);
        navLinearLayout.setVisibility(View.INVISIBLE);
        mNavLayoutParent.addView(navLinearLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        lp1.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        lp1.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        mRelativeLayout.addView(mViewPager, lp1);

        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        mRelativeLayout.addView(mNavLayoutParent, lp2);
        addView(mRelativeLayout, layoutParamsFW);
//        addView(mRelativeLayout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

        //得到导航点的图片资源文件
        displayImage = ABFileUtil.getBitmapFromSrc("play_display.png");
        hideImage = ABFileUtil.getBitmapFromSrc("play_hide.png");

        mListViews = new ArrayList<>();
        mAbViewPagerAdapter = new AbViewPagerAdapter(context, mListViews);
        mViewPager.setAdapter(mAbViewPagerAdapter);
        mViewPager.setFadingEdgeLength(0);
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (mAbScrolledListener != null) {
                    if (position == 0) mAbScrolledListener.onScrollToLeft();
                    if (position == mListViews.size() - 1) mAbScrolledListener.onScrollToRight();
                }
                setNowPlayIndex(position);
                makesurePosition();
                onPageSelectedCallBack(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                onPageScrolledCallBack(position);
            }

        });

    }

    /**
     * 拦截父类的滑动事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 创建导航点.
     */
    public void creatIndex() {
        //显示下面的点
        navLinearLayout.removeAllViews();
        mNavLayoutParent.setHorizontalGravity(navHorizontalGravity);
        navLinearLayout.setGravity(Gravity.CENTER);
        navLinearLayout.setVisibility(View.VISIBLE);
        count = mListViews.size();
        navLayoutParams.setMargins(5, 5, 5, 5);
        for (int j = 0; j < count; j++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(navLayoutParams);
            if (j == 0) {
                imageView.setImageBitmap(displayImage);
            } else {
                imageView.setImageBitmap(hideImage);
            }
            navLinearLayout.addView(imageView, j);
        }
    }


    /**
     * 定位点的位置.
     */
    public void makesurePosition() {
        position = mViewPager.getCurrentItem();
        for (int j = 0; j < count; j++) {
            if (position == j) {
                ((ImageView) navLinearLayout.getChildAt(position)).setImageBitmap(displayImage);
            } else {
                ((ImageView) navLinearLayout.getChildAt(j)).setImageBitmap(hideImage);
            }
        }
    }

    /**
     * 描述：添加可播放视图.
     *
     * @param view the view
     */
    public void addView(View view) {
        mListViews.add(view);
        if (view instanceof AbsListView) {
        } else {
            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClick(position);
                    }
                }
            });
            view.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    if (mAbOnTouchListener != null) {
                        mAbOnTouchListener.onTouch(event);
                    }
                    return false;
                }
            });
        }
        mAbViewPagerAdapter.notifyDataSetChanged();
        creatIndex();
    }

    /**
     * 描述：添加可播放视图列表.
     *
     * @param views the views
     */
    public void addViews(List<View> views) {
        mListViews.addAll(views);
        for (View view : views) {
            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClick(position);
                    }
                }
            });
            view.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    if (mAbOnTouchListener != null) {
                        mAbOnTouchListener.onTouch(event);
                    }
                    return false;
                }
            });
        }
        mAbViewPagerAdapter.notifyDataSetChanged();
        creatIndex();
    }

    /**
     * 描述：删除可播放视图.
     */
    @Override
    public void removeAllViews() {
        mListViews.clear();
        mAbViewPagerAdapter.notifyDataSetChanged();
        creatIndex();
    }


    /**
     * 描述：设置页面切换事件.
     *
     * @param position the position
     */
    private void onPageScrolledCallBack(int position) {
        if (mAbScrolledListener != null) {
            mAbScrolledListener.onScroll(mListViews == null ? 0 : mListViews.size(), position);
        }

    }

    /**
     * 描述：设置页面切换事件.
     *
     * @param position the position
     */
    private void onPageSelectedCallBack(int position) {
        if (mAbChangeListener != null) {
            mAbChangeListener.onChange(position);
        }

    }


    /**
     * 用与轮换的 handler.
     */
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                ShowPlay();
                if (play) {
                    handler.postDelayed(runnable, sleepTime);
                }
            }
        }

    };

    /**
     * 用于轮播的线程.
     */
    private Runnable runnable = new Runnable() {
        public void run() {
            if (mViewPager != null) {
                handler.sendEmptyMessage(0);
            }
        }
    };

    /**
     * 上一页
     */
    public void setPreviousPage() {
        if (getNowPlayIndex() - 1 >= 0)
            mViewPager.setCurrentItem(getNowPlayIndex() - 1, true);
    }

    /**
     * 上一页
     */
    public void setNextPage() {
        if (mListViews.size() > getNowPlayIndex() + 1)
            mViewPager.setCurrentItem(getNowPlayIndex() + 1, true);
    }

    /**
     * 描述：自动轮播.
     */
    public void startPlay() {
        if (handler != null) {
            play = true;
            handler.postDelayed(runnable, 5000);
        }
    }

    /**
     * 描述：自动轮播.
     */
    public void stopPlay() {
        if (handler != null) {
            play = false;
            handler.removeCallbacks(runnable);
        }
    }

    /**
     * 设置点击事件监听.
     *
     * @param onItemClickListener the new on item click listener
     */
    public void setOnItemClickListener(AbOnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    /**
     * 描述：设置页面切换的监听器.
     *
     * @param abChangeListener the new on page change listener
     */
    public void setOnPageChangeListener(AbOnChangeListener abChangeListener) {
        mAbChangeListener = abChangeListener;
    }

    /**
     * 描述：设置页面滑动的监听器.
     *
     * @param abScrolledListener the new on page scrolled listener
     */
    public void setOnPageScrolledListener(AbOnScrollListener abScrolledListener) {
        mAbScrolledListener = abScrolledListener;
    }

    /**
     * 描述：设置页面Touch的监听器.
     *
     * @param abOnTouchListener the new on touch listener
     */
    public void setOnTouchListener(AbOnTouchListener abOnTouchListener) {
        mAbOnTouchListener = abOnTouchListener;
    }


    /**
     * Sets the page line image.
     *
     * @param displayImage the display image
     * @param hideImage    the hide image
     */
    public void setPageLineImage(Bitmap displayImage, Bitmap hideImage) {
        this.displayImage = displayImage;
        this.hideImage = hideImage;
        creatIndex();

    }

    /**
     * 描述：获取这个滑动的ViewPager类.
     *
     * @return the view pager
     */
    public ViewPager getViewPager() {
        return mViewPager;
    }

    /**
     * 描述：获取当前的View的数量.
     *
     * @return the count
     */
    public int getCount() {
        return mListViews.size();
    }

    /**
     * 描述：设置页显示条的位置,在AddView前设置.
     *
     * @param horizontalGravity the nav horizontal gravity
     */
    public void setNavHorizontalGravity(int horizontalGravity) {
        navHorizontalGravity = horizontalGravity;
    }

    /**
     * 如果外层有ScrollView需要设置.
     *
     * @param parentScrollView the new parent scroll view
     */
    public void setParentScrollView(ScrollView parentScrollView) {
        this.mViewPager.setParentScrollView(parentScrollView);
    }

    /**
     * 如果外层有ListView需要设置.
     *
     * @param parentListView the new parent list view
     */
    public void setParentListView(ListView parentListView) {
        this.mViewPager.setParentListView(parentListView);
    }

    /**
     * 描述：设置导航点的背景.
     *
     * @param resid the new nav layout background
     */
    public void setNavLayoutBackground(int resid) {
        navLinearLayout.setBackgroundResource(resid);
    }

    // TODO: 2016/5/18 0018 >>
    /**
     * 描述：设置播放的间隔时间
     * @param sleepTime  间隔时间单位是毫秒
     */
    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     *  描述：设置播放方向的方式（1顺序播放和0来回播放） playType
     * @param playType    为0表示来回播放，为1表示顺序播放
     */
    public void setPlayType(int playType) {
        this.playType = playType;
    }


    /**
     * 描述：播放显示界面（1顺序播放和0来回播放） playType 为0表示来回播放，为1表示顺序播放
     */
    public void ShowPlay() {
        //总页数
        int count = mListViews.size();
        // 当前显示的页数
        int i = mViewPager.getCurrentItem();
        switch (playType) {
            case 0:
                // 来回播放
                if (playingDirection == 0) {
                    if (i == count - 1) {
                        playingDirection = -1;
                        i--;
                    } else {
                        i++;
                    }
                } else {
                    if (i == 0) {
                        playingDirection = 0;
                        i++;
                    } else {
                        i--;
                    }
                }
                break;
            case 1:
                // 顺序播放
                if (i == count - 1) {
                    i = 0;
                } else {
                    i++;
                }

                break;

            default:
                break;
        }
        // 设置显示第几页
        mViewPager.setCurrentItem(i, true);
    }
// TODO: 2016/5/18 0018 <<
    /**
     * 监听器.
     */
    public interface AbOnChangeListener {

        /**
         * 改变.
         *
         * @param position the position
         */
        void onChange(int position);

    }

    /**
     * 条目点击接口.
     */
    public interface AbOnItemClickListener {

        /**
         * 描述：点击事件.
         *
         * @param position 索引
         */
        void onClick(int position);
    }

    /**
     * 滚动.
     */
    public interface AbOnScrollListener {

        /**
         * 滚动.
         *
         * @param arg1 返回参数
         */
        void onScroll(int total, int arg1);

        /**
         * 滚动停止.
         */
        void onScrollStoped();

        /**
         * 滚到了最左边.
         */
        void onScrollToLeft();

        /**
         * 滚到了最右边.
         */
        void onScrollToRight();

    }

    /**
     * 触摸屏幕接口.
     */
    public interface AbOnTouchListener {
        /**
         * 描述：Touch事件.
         *
         * @param event 触摸手势
         */
        void onTouch(MotionEvent event);
    }

}
