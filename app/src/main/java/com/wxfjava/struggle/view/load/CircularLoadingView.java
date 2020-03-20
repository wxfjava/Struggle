package com.wxfjava.struggle.view.load;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 加载动画
 */
public class CircularLoadingView extends View {

    private final int MIN_WIDTH = 100;
    private final int MIN_HEIGHT = 50;

    private int mCenterX;
    private int mCenterY;
    private int mRadius;
    private Paint mPaint;
    private int mColor1 = Color.RED;
    private int mColor2 = Color.BLUE;
    private PointF mCircle1;
    private PointF mCircle2;
    private float mAnimatorValue = 0;

    public CircularLoadingView(Context context) {
        this(context, null);
    }

    public CircularLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CircularLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (modeWidth == MeasureSpec.AT_MOST) { // wrap_content
            sizeWidth = Math.min(MIN_WIDTH, sizeWidth);
            modeWidth = MeasureSpec.EXACTLY;
        }

        if (modeHeight == MeasureSpec.AT_MOST) { // wrap_content
            sizeHeight = Math.min(MIN_HEIGHT, sizeHeight);
            modeHeight = MeasureSpec.EXACTLY;
        }

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(sizeWidth, modeWidth);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(sizeHeight, modeHeight);
//
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mCenterX = width / 2;
        mCenterY = height / 2;
        mRadius = Math.min(width / 2, height) / 2;

        mCircle1 = new PointF(mCenterX - mRadius, mCenterY);
        mCircle2 = new PointF(mCenterX + mRadius, mCenterY);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 2, 4);
        animator.setDuration(2000);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
            }
        });
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);
        boolean isBack = false;
        float tmpValue = mAnimatorValue;
        if (tmpValue > 2) {
            tmpValue = 4 - mAnimatorValue;
            isBack = true;
        }

        float value = tmpValue;
        if (tmpValue > 1) {
            value = 2 - tmpValue;
        }

        int color1 = mColor1;
        int color2 = mColor2;
        float radius = mRadius - (mRadius / 3 * value);
        mPaint.setColor(color1);
        canvas.drawCircle(mCircle1.x + (mRadius * tmpValue), mCircle1.y, radius, mPaint);
        if (isBack) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        } else {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        }

        mPaint.setColor(color2);
        canvas.drawCircle(mCircle2.x - (mRadius * tmpValue), mCircle2.y, radius, mPaint);
        mPaint.setXfermode(null);
        postInvalidateDelayed(50);
    }
}
