package com.admin.control.topTitleBackBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.admin.R;


/**
 * 项目名称：JKP
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/2/2 15:38
 * 修改人：Sun
 * 修改时间：2016/3/23 15:03
 * 修改备注：
 */
public abstract class BaseTopBackCenterHeader extends RelativeLayout {
    public BaseTopBackCenterHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public BaseTopBackCenterHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initViews();
        if (null != attrs) {
            TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.BaseTopBackCenterHeader);

            try {
                String title = arr.getString(R.styleable.BaseTopBackCenterHeader_headerTitle);
                this.setTitle(title);

                int titleColor = arr.getColor(R.styleable.BaseTopBackCenterHeader_headerTitleColor, getResources().getColor(R.color.cl_Grid));
                this.setTitleColor(titleColor);

                int titleBg = arr.getColor(R.styleable.BaseTopBackCenterHeader_headerBackground, getResources().getColor(R.color.cl_Grid));
                this.setTitleBackgroundColor(titleBg);

                String leftTitle = arr.getString(R.styleable.BaseTopBackCenterHeader_headerLeftTitle);
                this.setLeftTitle(leftTitle);

                int leftColor = arr.getColor(R.styleable.BaseTopBackCenterHeader_headerLeftTitleColor,getResources().getColor(R.color.white));
                this.setLeftTitleColor(leftColor);

                int leftTitleVisibility = arr.getInt(R.styleable.BaseTopBackCenterHeader_headerLeftTitleVisibility, 0);
                this.setLeftTitleVisibility(leftTitleVisibility);

                int leftImage = arr.getResourceId(R.styleable.BaseTopBackCenterHeader_headerLeftImage,R.mipmap.top_back);
                this.setLeftImage(leftImage);

                int leftImageVisibility = arr.getInt(R.styleable.BaseTopBackCenterHeader_headerLeftImageVisibility,0);
                this.setLeftImageVisibility(leftImageVisibility);

                int leftVisibility = arr.getInt(R.styleable.BaseTopBackCenterHeader_headerLeftVisibility,0);
                this.setLeftVisibility(leftVisibility);

                String rightTitle = arr.getString(R.styleable.BaseTopBackCenterHeader_headerRightTitle);
                this.setRightTitle(rightTitle);

                int rightColor = arr.getColor(R.styleable.BaseTopBackCenterHeader_headerRightTitleColor,getResources().getColor(R.color.white));
                this.setRightTitleColor(rightColor);

                int rightTitleVisibility = arr.getInt(R.styleable.BaseTopBackCenterHeader_headerRightTitleVisibility, 0);
                this.setRightTitleVisibility(rightTitleVisibility);

                int rightImage = arr.getResourceId(R.styleable.BaseTopBackCenterHeader_headerRightImage,R.mipmap.ic_launcher);
                this.setRightImage(rightImage);

                int rightImageVisibility = arr.getInt(R.styleable.BaseTopBackCenterHeader_headerRightImageVisibility,0);
                this.setRightImageVisibility(rightImageVisibility);

                int rightVisibility = arr.getInt(R.styleable.BaseTopBackCenterHeader_headerRightVisibility,0);
                this.setRightVisibility(rightVisibility);

            } finally {
                arr.recycle();
            }

        }
    }

    public abstract void setRightVisibility(int rightVisibility);

    public abstract void setRightImageVisibility(int rightImageVisibility);


    public abstract void setRightImage(int rightImage);

    public abstract void setRightTitleVisibility(int rightTitleVisibility);

    public abstract void setRightTitleColor(int rightColor);

    public abstract void setRightTitle(String rightTitle);

    public abstract void setLeftVisibility(int leftVisibility);

    public abstract void setLeftImageVisibility(int leftImageVisibility);

    public abstract void setLeftImage(int leftImage);

    public abstract void setLeftTitleColor(int leftColor);

    public abstract void setLeftClickListener(OnClickListener var1);

    public abstract void setTitle(String var1);

    public abstract void setTitle(int var1);

    public abstract void setTitleColor(int color);

    public abstract void setTitleBackgroundColor(int color);

    public abstract void setLeftTitle(String var1);

    public abstract void setLeftTitleVisibility(int var1);


    abstract void initViews();
}
