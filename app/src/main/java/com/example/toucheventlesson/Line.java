package com.example.toucheventlesson;

import android.graphics.PointF;
import android.os.Parcel;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
