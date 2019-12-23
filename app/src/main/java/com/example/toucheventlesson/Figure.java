package com.example.toucheventlesson;

import android.graphics.Path;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;

public abstract class Figure implements Parcelable {

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


    public static final Creator<Figure> CREATOR = new Creator<Figure>() {
        @Override
        public Figure createFromParcel(Parcel in) {
            return new Figure(in) {
                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {

                }
            };
        }

        @Override
        public Figure[] newArray(int size) {
            return new Figure[size];
        }
    };
}


