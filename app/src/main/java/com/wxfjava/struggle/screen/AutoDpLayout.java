package com.wxfjava.struggle.screen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class AutoDpLayout extends RelativeLayout {

    private static final float BASE_DENSITY = 2.6f;

    boolean isAutoSet = false;

    public AutoDpLayout(Context context) {
        super(context);
    }

    public AutoDpLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoDpLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AutoDpLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isAutoSet) {

            double scale = BASE_DENSITY / getContext().getResources().getDisplayMetrics().density;

            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                params.width = (int) (params.width * scale);
                params.height = (int) (params.height * scale);
                params.leftMargin = (int) (params.leftMargin * scale);
                params.rightMargin = (int) (params.rightMargin * scale);
                params.topMargin = (int) (params.topMargin * scale);
                params.bottomMargin = (int) (params.bottomMargin * scale);
            }
            isAutoSet = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
