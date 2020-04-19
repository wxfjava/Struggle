package com.wxfjava.struggle.screen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.wxfjava.struggle.utils.DeviceUtils;

public class AutoPxLayout extends RelativeLayout {

    private static final float BASE_WIDTH = 1080;
    private static final float BASE_HEIGHT = 1920;

    boolean isAutoSet = false;

    public AutoPxLayout(Context context) {
        super(context);
    }

    public AutoPxLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoPxLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AutoPxLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isAutoSet) {
            float scaleWidth = DeviceUtils.getDisplayWidth(getContext()) / BASE_WIDTH;
            float scaleHeight = DeviceUtils.getDisplayHeight(getContext()) / BASE_HEIGHT;

            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                params.width = (int) (params.width * scaleWidth);
                params.height = (int) (params.height * scaleHeight);
                params.leftMargin = (int) (params.leftMargin * scaleWidth);
                params.rightMargin = (int) (params.rightMargin * scaleWidth);
                params.topMargin = (int) (params.topMargin * scaleHeight);
                params.bottomMargin = (int) (params.bottomMargin * scaleHeight);
            }
            isAutoSet = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
