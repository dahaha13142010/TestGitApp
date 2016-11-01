package com.admin.control.topTitleBackBar;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.admin.R;


/**
 * 项目名称：JKP
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/2/2 15:37
 * 修改人：Michael
 * 修改时间：2016/2/2 15:37
 * 修改备注：
 */
public class TopTitleBackBar extends BaseTopBackCenterHeader {
    private RelativeLayout topLay;
    private LinearLayout closeIv;
    private LinearLayout rightIv;
    private TextView titleTv;
    private TextView leftTitleTv;
    private TextView rightTitleTv;
    private ImageView leftTitleIm;
    private ImageView rightTitleIm;
    private int iWidth,iLeftWidth,iRightWidth;

    public TopTitleBackBar(Context context) {
        this(context, null);
    }

    public TopTitleBackBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setRightVisibility(int rightVisibility) {rightIv.setVisibility(rightVisibility);}

    public void setRightImageVisibility(int rightImageVisibility) {rightTitleIm.setVisibility(rightImageVisibility);}

    public void setRightImage(int rightImage) {rightTitleIm.setImageResource(rightImage);}

    public void setRightTitleVisibility(int rightTitleVisibility) {rightTitleTv.setVisibility(rightTitleVisibility);}

    public void setRightTitleColor(int rightColor) {rightTitleTv.setTextColor(rightColor);}

    public void setRightTitleTv(String text) {rightTitleTv.setText(text); }

    public void setLeftVisibility(int leftVisibility) {closeIv.setVisibility(leftVisibility);}

    public void setLeftImageVisibility(int leftImageVisibility) {leftTitleIm.setVisibility(leftImageVisibility);}

    public void setLeftImage(int leftImage) { leftTitleIm.setImageResource(leftImage);  }

    public void setRightTitle(String rightTitle) {rightTitleTv.setText(rightTitle); }

    public void setLeftTitleColor(int leftColor) {leftTitleTv.setTextColor(leftColor);}

    public void setLeftClickListener(View.OnClickListener onClickListener) {  closeIv.setOnClickListener(onClickListener);}

    public void setTitle(String text) {
        titleTv.setText(text);
    }

    public void setTitle(int resid) {
        titleTv.setText(getContext().getResources().getText(resid));
    }
    public void setRightTitle(int resid) {
        rightTitleTv.setText(getContext().getResources().getText(resid));
    }

    public void setTitleColor(int color) {
        titleTv.setTextColor(color);
    }

    @Override
    public void setTitleBackgroundColor(int color) { topLay.setBackgroundColor(color);}

    public void setLeftTitle(String text) {
        leftTitleTv.setText(text);
    }

    public void setLeftTitleVisibility(int shown) { leftTitleTv.setVisibility(shown); }

    void initViews() {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.top_title_back, this);
        topLay = (RelativeLayout) findViewById(R.id.top_lay);
        closeIv = (LinearLayout) findViewById(R.id.back_lay);
        rightIv = (LinearLayout) findViewById(R.id.right_layout);
        titleTv = (TextView) findViewById(R.id.tV_top_title);
        leftTitleTv = (TextView) findViewById(R.id.leftTitleTv);
        rightTitleTv = (TextView) findViewById(R.id.rightTitleTv);
        leftTitleIm = (ImageView) findViewById(R.id.iV_top_back);
        rightTitleIm = (ImageView) findViewById(R.id.iv_top_right);
        closeIv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                }

            }
        });




    }

    public void setOnClickListenerRightIm(View.OnClickListener onClickListener) {
        rightTitleIm.setOnClickListener(onClickListener);
    }

    public void setOnClickListenerRightTv(View.OnClickListener onClickListener) {
        rightTitleTv.setOnClickListener(onClickListener);
    }

    public void setOnClickListenerRightRl(View.OnClickListener onClickListener) {
        rightTitleIm.setOnClickListener(onClickListener);
        rightTitleTv.setOnClickListener(onClickListener);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        iWidth = widthMeasureSpec;
//        iLeftWidth = closeIv.getWidth();
//        iRightWidth= rightIv.getWidth();
//        ABLogUtil.i("widthMeasureSpec:"+widthMeasureSpec+",iLeftWidth:"+iLeftWidth+",iRightWidth:"+iRightWidth);
//        titleTv.setWidth(iLeftWidth>iRightWidth? iWidth-iLeftWidth*2 : iWidth-iRightWidth*2);
//        ABLogUtil.i("titleTv.setWidth():"+(iLeftWidth>iRightWidth? iWidth-iLeftWidth*2 : iWidth-iRightWidth*2));
//    }
}
