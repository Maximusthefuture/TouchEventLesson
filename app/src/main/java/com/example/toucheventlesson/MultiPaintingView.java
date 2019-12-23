package com.example.toucheventlesson;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MultiPaintingView implements Parcelable {


    private Paint mPaint;
    private Paint mComplexPaint;

    private Path mComplexPath;
    private int mColor;
    private float mLineWidth = 8f;
    private List<PointF> mPoints = new ArrayList<>(16);

    public MultiPaintingView(int color) {
        mColor = color;
        initPaint();
    }

    public MultiPaintingView(Parcel item) {
        mColor = item.readInt();
        mPoints = item.createTypedArrayList(PointF.CREATOR);
    }


    public static final Creator<MultiPaintingView> CREATOR = new Creator<MultiPaintingView>() {
        @Override
        public MultiPaintingView createFromParcel(Parcel in) {
            return new MultiPaintingView(in);
        }

        @Override
        public MultiPaintingView[] newArray(int size) {
            return new MultiPaintingView[size];
        }
    };

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(mLineWidth);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));

        mComplexPaint = new Paint(mPaint);
        mComplexPaint.setStyle(Paint.Style.FILL);

    }

    public PointF getPoint(int index) {
        while (index >= mPoints.size()) {
            mPoints.add(new PointF());
        }

        return mPoints.get(index);
    }


    public void drawMulti(@NonNull Canvas canvas) {
        if (mComplexPath == null) {
            mComplexPath = new Path();
        }

        mComplexPath.reset();

        for (PointF point : mPoints) {
            if (mComplexPath.isEmpty()) {
                mComplexPath.moveTo(point.x, point.y);
            } else {
                mComplexPath.lineTo(point.x, point.y);
            }
        }
        mComplexPath.close();
        canvas.drawPath(mComplexPath, mComplexPaint);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mColor);
        dest.writeFloat(mLineWidth);
        dest.writeTypedList(mPoints);
    }
}
