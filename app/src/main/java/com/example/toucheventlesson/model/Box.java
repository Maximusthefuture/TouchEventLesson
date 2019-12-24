package com.example.toucheventlesson.model;

import android.graphics.PointF;

public class Box extends Figure{

    private PointF mOrigin;
    private PointF mCurrent;
    private int mColor;

    public Box(PointF origin, int color) {
        mOrigin = origin;
        mCurrent = origin;
        mColor = color;
    }
    @Override
    public PointF getOrigin() {
        return mOrigin;
    }
    @Override
    public PointF getCurrent() {
        return mCurrent;
    }
    @Override
    public void setCurrent(PointF mCurrent) {
        this.mCurrent = mCurrent;
    }

    @Override
    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

}
