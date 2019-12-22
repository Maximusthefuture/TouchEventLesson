package com.example.toucheventlesson;

import android.graphics.PointF;
import android.os.Parcel;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
