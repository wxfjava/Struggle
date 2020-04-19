package com.wxfjava.struggle.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DeviceUtils {

    private static int mDisplayWidth = -1;
    private static int mDisplayHeight = -1;

    private static void initDisplay(Context context) {
        if (mDisplayWidth < 0 || mDisplayHeight < 0) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                //横屏
                mDisplayWidth = displayMetrics.heightPixels;
                mDisplayHeight = displayMetrics.widthPixels;
            } else {
                mDisplayWidth = displayMetrics.widthPixels;
                mDisplayHeight = displayMetrics.heightPixels - getStatusBarHeight(context);
            }

            L.i(px2dip(context, 1080));
            L.i(px2dip(context, 1920));
            L.i(displayMetrics.toString());
        }
    }

    private static int getStatusBarHeight(Context context) {
        int resID = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resID > 0) {
            return context.getResources().getDimensionPixelSize(resID);
        }
        return 0;
    }

    public static int getDisplayWidth(Context context) {
        initDisplay(context);
        return mDisplayWidth;
    }

    public static int getDisplayHeight(Context context) {
        initDisplay(context);
        return mDisplayHeight;
    }

    public static double dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);
    }

    public static double px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxValue / scale + 0.5f);
    }
}
