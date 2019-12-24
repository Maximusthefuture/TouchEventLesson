package com.example.toucheventlesson.model;

import android.graphics.PointF;

public class Line extends Figure {

    private PointF mOrigin;
    private PointF mEnd;
    private int mColor;

    public Line(PointF origin, PointF end, int color) {
        mOrigin = origin;
        mEnd = end;
        mColor = color;
    }

    @Override
    public PointF getOrigin() {
        return mOrigin;
    }

    @Override
    public PointF getEnd() {
        return mEnd;
    }

    @Override
    public void setEnd(PointF end) {
        mEnd = end;
    }

    @Override
    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

}
