package com.example.toucheventlesson;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {

    public static final float STROKE = 10f;

    private Path mPath = new Path();
    private Paint mPaint = new Paint();


    public DrawView(Context context) {
        super(context, null);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
            mPaint.setColor(Color.RED);
            mPaint.setAntiAlias(true);
            mPaint.setStrokeWidth(STROKE);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeJoin(Paint.Join.MITER);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(eventX, eventY);
                invalidate();
                return true;

            default:
                return super.onTouchEvent(event);

        }


    }

    public void clear() {
        mPath.reset();
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);

    }
}
