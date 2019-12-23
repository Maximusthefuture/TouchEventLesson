package com.example.toucheventlesson;

import android.graphics.Path;
import android.os.Parcel;

public class PathLine extends Figure {

    private Path mPath;
    private int mColor;

    public PathLine(Path path, int color) {
        mPath = path;
        mColor = color;
    }

    @Override
    public Path getPath() {
        return mPath;
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
