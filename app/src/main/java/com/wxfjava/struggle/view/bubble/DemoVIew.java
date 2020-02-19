package com.wxfjava.struggle.view.bubble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DemoVIew extends View {


    /**
     * 固定圆圆心，小圆圆心
     */
    private final PointF fixedP = new PointF(300f, 700f);

    /**
     * 拖拽圆圆心，大圆圆心
     */
    private final PointF dragP = new PointF(600f, 400f);

    /**
     * 固定圆半径
     */
    private final float fixedR = 100f;

    /**
     * 拖拽圆半径
     */
    private final float dragR = 150f;

    /**
     * 画笔
     */
    private Paint mPaint = null;

    /**
     * 固定圆左侧点
     */
    private PointF fixedP1 = null;

    /**
     * 固定圆右侧侧点
     */
    private PointF fixedP2 = null;

    /**
     * 拖拽圆左侧点
     */
    private PointF dragP1 = null;

    /**
     * 拖拽圆右侧侧点
     */
    private PointF dragP2 = null;

    /**
     * 两圆心中点，贝塞尔曲线控制点
     */
    private PointF controlP = null;

    /**
     * 关键角角度
     */
    private double mAngle;


    public DemoVIew(Context context) {
        super(context);
        init();
    }

    public DemoVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DemoVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DemoVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(3f);

        double tan = Math.abs(fixedP.y - dragP.y) / Math.abs(fixedP.x - dragP.x);
        mAngle = Math.atan(tan);
        float fixedXOffset = (float) (fixedR * Math.sin(mAngle));
        float fixedYOffset = (float) (fixedR * Math.cos(mAngle));
        float dragXOffset = (float) (dragR * Math.sin(mAngle));
        float fdragYOffset = (float) (dragR * Math.cos(mAngle));

        fixedP1 = new PointF(fixedP.x - fixedXOffset, fixedP.y - fixedYOffset);
        fixedP2 = new PointF(fixedP.x + fixedXOffset, fixedP.y + fixedYOffset);
        dragP1 = new PointF(dragP.x - dragXOffset, dragP.y - fdragYOffset);
        dragP2 = new PointF(dragP.x + dragXOffset, dragP.y + fdragYOffset);
        controlP = new PointF((fixedP.x + dragP.x) / 2, (fixedP.y + dragP.y) / 2);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

//        drawPaths(canvas);

        drawShapes(canvas);
        drawArc(canvas);
//        drawPaths(canvas);

    }

    private void drawArc(Canvas canvas) {
        RectF fixedRectF = new RectF(fixedP.x - fixedR, fixedP.y - fixedR, fixedP.x + fixedR, fixedP.y + fixedR);
        canvas.drawArc(fixedRectF, (float) Math.toDegrees(mAngle), 180f, true, mPaint);

        RectF dragRectF = new RectF(dragP.x - dragR, dragP.y - dragR, dragP.x + dragR, dragP.y + dragR);
        canvas.drawArc(dragRectF, (float) Math.toDegrees(mAngle) + 180f, 180f, true, mPaint);
    }

    private void drawShapes(Canvas canvas) {

        canvas.drawCircle(fixedP.x, fixedP.y, fixedR, mPaint);
        canvas.drawCircle(dragP.x, dragP.y, dragR, mPaint);

        Path path = new Path();
        path.moveTo(fixedP1.x, fixedP1.y);
        path.quadTo(controlP.x, controlP.y, dragP1.x, dragP1.y);
        path.lineTo(dragP2.x, dragP2.y);
        path.quadTo(controlP.x, controlP.y, fixedP2.x, fixedP2.y);
        path.lineTo(fixedP1.x, fixedP1.y);
        canvas.drawPath(path, mPaint);
    }

    private void drawPaths(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(fixedP.x, fixedP.y, fixedR, mPaint);
        canvas.drawCircle(dragP.x, dragP.y, dragR, mPaint);

        canvas.drawLine(fixedP.x, fixedP.y, dragP.x, dragP.y, mPaint);

        Path path = new Path();
        path.moveTo(fixedP1.x, fixedP1.y);
        path.quadTo(controlP.x, controlP.y, dragP1.x, dragP1.y);
        path.lineTo(dragP2.x, dragP2.y);
        path.quadTo(controlP.x, controlP.y, fixedP2.x, fixedP2.y);
        path.lineTo(fixedP1.x, fixedP1.y);
        canvas.drawPath(path, mPaint);
    }
}
