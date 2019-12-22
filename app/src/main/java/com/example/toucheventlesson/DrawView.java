package com.example.toucheventlesson;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DrawView extends View {

    public static final float STROKE = 10f;

    private Path mPath = new Path();
    private Paint mLinePaint = new Paint();
    private Line mLine;
    private List<Line> linesList = new ArrayList<>();
    private Paint mPathLinePaint = new Paint();
    private PathLine mPathLines;
    private List<PathLine> pathLinesList = new ArrayList<>();
    private Paint mRectanglePaint = new Paint();
    private Figure mRectangles;
    private List<Figure> rectanglesList = new ArrayList<>();
    private int mCurrentColor;
    private EnumFigure mEnumFigure = EnumFigure.LINE;
    private List<MultiPaintingView> mMultiPaintingViewList = new ArrayList<>();
    private MultiPaintingView mMultiPaintingView;



    public DrawView(Context context) {
        super(context, null);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }



    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initPaint() {
        defautColor();
//        mLinePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
//        mLinePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        mLinePaint.setColor(mCurrentColor);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStrokeWidth(STROKE);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeJoin(Paint.Join.MITER);


//        mRectanglePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        mRectanglePaint.setColor(mCurrentColor);
        mRectanglePaint.setAntiAlias(true);
        mRectanglePaint.setStrokeWidth(STROKE);
        mRectanglePaint.setStyle(Paint.Style.STROKE);


//        mPathLinePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        mPathLinePaint.setColor(mCurrentColor);
        mPathLinePaint.setAntiAlias(true);
        mPathLinePaint.setStrokeWidth(STROKE);
        mPathLinePaint.setStyle(Paint.Style.STROKE);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (mEnumFigure) {
            case LINE:
                lineMotionEvent(event);
                break;
            case RECTANGLE:
                rectanglesMotionEvent(event);
                break;
            case PATH_LINE:
                pathLinesMotionEvent(event);
                break;
            case MULTI_PAINTING:
                multiPaintingMotionEvent(event);
                break;
            default:
                return super.onTouchEvent(event);
        }
        invalidate();
        return true;

    }

    public void pathLinesMotionEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(current.x, current.y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(current.x, current.y);
                mPathLines = new PathLine(mPath, mCurrentColor);
                pathLinesList.add(mPathLines);
                break;
            case MotionEvent.ACTION_CANCEL:
                mPath = null;
                break;
        }
    }


    public void rectanglesMotionEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mRectangles = new Box(current, mCurrentColor);
                rectanglesList.add(mRectangles);
                break;
            case MotionEvent.ACTION_MOVE:
                if (mRectangles != null) {
                    mRectangles.setCurrent(current);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mRectangles = null;
                break;

        }
    }

    public void lineMotionEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(), event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLine = new Line(current,current, mCurrentColor);
                linesList.add(mLine);
                break;
            case MotionEvent.ACTION_MOVE:
                mLine.setEnd(current);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mLine = null;
                break;
        }
    }

    public void multiPaintingMotionEvent(MotionEvent event) {
        PointF currentPoint = new PointF(event.getX(), event.getY());
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mMultiPaintingView = new MultiPaintingView(mCurrentColor);
                currentPoint = mMultiPaintingView.getPoint(0);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int pointerId = event.getPointerId(event.getActionIndex());
                currentPoint = mMultiPaintingView.getPoint(pointerId);
                break;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int pId = event.getPointerId(i);
                    mMultiPaintingView.getPoint(pId).x = event.getX(i);
                    mMultiPaintingView.getPoint(pId).y = event.getY(i);
                }
                break;
            case MotionEvent.ACTION_UP:
                mMultiPaintingViewList.add(mMultiPaintingView);
                mMultiPaintingView =  null;
                break;
        }

            currentPoint.x = event.getX(event.getActionIndex());
            currentPoint.y = event.getY(event.getActionIndex());


    }

    public void setType(EnumFigure enumFigure) {
        mEnumFigure = enumFigure;
    }

    public void clear() {
        mPath.reset();
        rectanglesList.clear();
        linesList.clear();
        pathLinesList.clear();
        mMultiPaintingViewList.clear();
        invalidate();
    }

    public void drawLine(Canvas canvas) {
        for (Figure figure : linesList) {
            float startX = figure.getOrigin().x;
            float startY = figure.getOrigin().y;
            float endX = figure.getEnd().x;
            float endY = figure.getEnd().y;
            mLinePaint.setColor(figure.getColor());
            canvas.drawLine(startX, startY, endX, endY, mLinePaint);
        }
    }

    private void rectangleDraw(Canvas canvas) {
        for (Figure figure : rectanglesList) {
            float left = Math.min(figure.getCurrent().x, figure.getOrigin().x);
            float right = Math.max(figure.getCurrent().x, figure.getOrigin().x);
            float top = Math.min(figure.getCurrent().y, figure.getOrigin().y);
            float bottom = Math.max(figure.getCurrent().y, figure.getOrigin().y);
            mRectanglePaint.setColor(figure.getColor());
            canvas.drawRect(left, top, right, bottom, mRectanglePaint);
        }
    }

    public void pathLineDraw(Canvas canvas) {
        for (Figure pathLine : pathLinesList) {
            mPathLinePaint.setColor(pathLine.getColor());
            canvas.drawPath(pathLine.getPath(), mPathLinePaint);
        }
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState state = new SavedState(superState);
        state.mLinesList = linesList;
        state.mPathLinesList = pathLinesList;
        state.color = mCurrentColor;
        state.rectagleList = rectanglesList;
        state.mMultiPaintingViews = mMultiPaintingViewList;
        state.figure = mEnumFigure;
        return state;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        SavedState ourState = (SavedState) state;
        linesList = ourState.mLinesList;
        pathLinesList = ourState.mPathLinesList;
        mCurrentColor = ourState.color;
        rectanglesList = ourState.rectagleList;
        mMultiPaintingViewList = ourState.mMultiPaintingViews;
        mEnumFigure = ourState.figure;
        invalidate();
    }

    public void defautColor() {
        mCurrentColor = Color.RED;
    }



    public void changeColor(int color) {
        mCurrentColor = color;

    }

    public void drawMultiPainting(Canvas canvas) {
        for (MultiPaintingView multiPaintingView : mMultiPaintingViewList) {
            multiPaintingView.drawMulti(canvas);
        }

        if (mMultiPaintingView != null) {
            mMultiPaintingView.drawMulti(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawLine(canvas);
        rectangleDraw(canvas);
        pathLineDraw(canvas);
        drawMultiPainting(canvas);

    }




    private static class SavedState extends BaseSavedState {
        int color;
        private List<Line> mLinesList;
        private List<PathLine> mPathLinesList;
        private List<Figure> rectagleList;
        private List<MultiPaintingView> mMultiPaintingViews;
        private EnumFigure figure;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            color = in.readInt();
            mLinesList = in.readParcelable((ClassLoader) Figure.CREATOR);
            mPathLinesList = in.readParcelable((ClassLoader) Figure.CREATOR);
            rectagleList = in.readParcelable((ClassLoader) Figure.CREATOR);
            mMultiPaintingViews = in.createTypedArrayList(MultiPaintingView.CREATOR);
            figure = (EnumFigure) in.readSerializable();
//            mPathLinesList = in.createTypedArrayList(PathLine.CREATOR)
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(color);
            out.writeTypedList(mLinesList);
            out.writeTypedList(mPathLinesList);
            out.writeTypedList(rectagleList);
            out.writeTypedList(mMultiPaintingViews);
            out.writeSerializable(figure);


        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

}







