package com.example.toucheventlesson.model;

import android.graphics.Path;
import android.graphics.PointF;
import android.os.Parcel;

public abstract class Figure{

    private PointF mOrigin;
    private PointF mEnd;
    private int mColor;
    private PointF mCurrent;
    private Path mPath;

    public Figure(Parcel in) {
        mColor = in.readInt();
        mCurrent = in.readParcelable((ClassLoader) PointF.CREATOR);
    }

    protected Figure() {
    }

    public PointF getOrigin() {
        return mOrigin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF mCurrent) {
        this.mCurrent = mCurrent;
    }

    public PointF getEnd() {
        return mEnd;
    }

    public void setEnd(PointF end) {
        mEnd = end;
    }

    public int getColor() {
        return mColor;
    }

    public Path getPath() {
        return mPath;
    }

}


