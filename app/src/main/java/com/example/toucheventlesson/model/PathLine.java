package com.example.toucheventlesson.model;

import android.graphics.Path;

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


}
