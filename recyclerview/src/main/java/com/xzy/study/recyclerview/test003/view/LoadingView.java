package com.xzy.study.recyclerview.test003.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 *
 * @author xzy
 */
public class LoadingView extends View {
    private Context mContext;
    private Paint mPaint;


    private double mRate;
    private int mRadius;

    private int mArcWidth = 4;
    private float startRate = 0.5f;

    private boolean mIsRotate;

    private int mRotateArge = 0;
    private Thread mThread;
    private MyHandler mHandler;
    private Runnable mRunable;

    public LoadingView(Context context) {
        super(context);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mRadius = 14;
        mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mRadius, context.getResources().getDisplayMetrics());

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mArcWidth);
        mHandler = new MyHandler();
        mRunable = new Runnable() {
            @Override
            public void run() {
                while (mIsRotate) {
                    mHandler.sendEmptyMessage(0x11);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mRadius * 2 + getPaddingLeft() + getPaddingRight(), mRadius * 2 + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        drawArc(canvas, mRate);
        if (!mIsRotate) {
            drawArrow(canvas, mRate);
        } else {
            //绘制一个指针
            drawPointer(canvas);
        }
    }

    private void drawPointer(Canvas canvas) {
        canvas.save();
        canvas.rotate(mRotateArge + 90, getPaddingLeft() + mRadius, getPaddingTop() + mRadius);
        canvas.drawLine(getPaddingLeft() + mRadius, getPaddingTop(), getPaddingLeft() + mRadius, getPaddingTop() + mRadius, mPaint);
//        canvas.rotate(mRotateArge + 60, getPaddingLeft() + mRadius, getPaddingTop() + mRadius);
//        canvas.drawLine(getPaddingLeft() + mRadius, getPaddingTop() + 10, getPaddingLeft() + mRadius, getPaddingTop() + mRadius, mPaint);
//        canvas.rotate(mRotateArge, getPaddingLeft() + mRadius, getPaddingTop() + mRadius);
//        canvas.drawLine(getPaddingLeft() + mRadius, getPaddingTop() + 20, getPaddingLeft() + mRadius, getPaddingTop() + mRadius, mPaint);
        canvas.restore();
    }

    private void drawArrow(Canvas canvas, double mRate) {
        canvas.save();

        if (mRate < 1) {
            canvas.rotate(0, getPaddingLeft() + mRadius, getPaddingTop() + mRadius);
        } else {
            canvas.rotate(180, getPaddingLeft() + mRadius, getPaddingTop() + mRadius);
        }
        drawActureArrow(canvas);
        canvas.restore();
    }

    private void drawActureArrow(Canvas canvas) {
        int startX = getPaddingLeft() + mRadius;
        int endX = getPaddingLeft() + mRadius;
        int startY = getPaddingTop() + mRadius * 2 / 5;
        int endY = startY + mRadius * 6 / 5;
        canvas.drawLine(startX, startY, endX, endY, mPaint);
        Path path = new Path();
        path.moveTo(startX - mRadius / 3, endY - mRadius / 3);
        path.lineTo(startX, endY);
        path.lineTo(startX + mRadius / 3, endY - mRadius / 3);
        canvas.drawPath(path, mPaint);

    }

    //绘制弧形，非实心
    private void drawArc(Canvas canvas, double rate) {
        canvas.drawArc(new RectF(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + 2 * mRadius, getPaddingTop() + 2 * mRadius), -90, (float) (360 * rate), false, mPaint);

    }


    public void updateView(double rate) {
        this.mRate = rate;
        invalidate();
    }

    public void rotateAnimation(boolean isRotate) {
        //如果本身已经在转动
        if (isRotate && mIsRotate) {
            return;
        }
        mIsRotate = isRotate;
        if (isRotate) {
            mThread = new Thread(mRunable);
            mThread.start();
        }
    }


    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mRotateArge += 2;
            invalidate();
        }
    }
}
