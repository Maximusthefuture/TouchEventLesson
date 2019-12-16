package com.example.toucheventlesson;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoxDrawView extends View {

    private Paint mPaint = new Paint();
    private List<Box> boxes = new ArrayList<>();
    private Box mCurrentBox;


    public BoxDrawView(Context context) {
        this(context, null);
    }

    public BoxDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mCurrentBox = new Box(current);
                boxes.add(mCurrentBox);
                break;

            case MotionEvent.ACTION_MOVE:
                mCurrentBox.setCurrent(current);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                return true;

            case MotionEvent.ACTION_CANCEL:
                mCurrentBox = null;
                break;

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Box box : boxes) {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }


    public void clear() {
        boxes.clear();
        invalidate();
    }

    // линии drawLine();
}
