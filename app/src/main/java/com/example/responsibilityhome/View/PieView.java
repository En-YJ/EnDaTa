package com.example.responsibilityhome.View;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.responsibilityhome.Entity.PieEntry;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PieView extends View {
    private final int[] colors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private boolean mShowHole = true;
    private int holeColor = Color.WHITE;
    private float holeRadiusProportion = 59;
    private float startAngle = -90;
    private float distance = 14F;
    private float smallCircleRadius = 3F;
    private float bigCircleRadius = 7F;
    private float xOffset = 7F;
    private float yOffset = 14F;
    private float extend = 180F;


    private Paint mPaint;
    private RectF mRectF;
    private float mTotalWidth;
    private float mTotalHeight;
    private float mScale;
    private float mRadius;

    private ArrayList<Integer> mColorLists;
    private ArrayList<PieEntry> mPieLists;


    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mScale != 0 && MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            int height = (int) (mTotalWidth / mScale);
            setMeasuredDimension(widthMeasureSpec, height);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w - getPaddingStart() - getPaddingEnd();
        mTotalHeight = h - getPaddingTop() - getPaddingBottom();
        initRectF();

    }

    private void initRectF() {

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float textHeight = fontMetrics.bottom - fontMetrics.top + fontMetrics.leading;
        float lineHeight = distance + bigCircleRadius + yOffset;
        float lineWidth = distance + bigCircleRadius + xOffset + extend;
        mScale = mTotalWidth / (mTotalWidth + lineHeight * 2 + textHeight * 2 - lineWidth * 2);


        float shortSideLength;
        if (mTotalWidth / mTotalHeight >= mScale) {
            shortSideLength = mTotalHeight;
        } else {
            shortSideLength = mTotalWidth / mScale;
        }

        mRadius = shortSideLength / 2 - lineHeight - textHeight;
        mRectF = new RectF(-mRadius, -mRadius, mRadius, mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mTotalWidth / 2, mTotalHeight / 2);

        if (isPieListsNull()) {
            mPaint.setColor(Color.BLACK);
            canvas.drawText("setData", -120, 0, mPaint);
        } else {
            drawPie(canvas);
            drawHole(canvas);
            drawLineAndText(canvas);
        }

    }

    private void drawPie(Canvas canvas) {
        for (PieEntry pie : mPieLists) {
            mPaint.setColor(pie.getColor());
            canvas.drawArc(mRectF,
                    pie.getCurrentStartAngle(),
                    pie.getSweepAngle(),
                    true, mPaint);
        }
    }

    private void drawHole(Canvas canvas) {
        if (mShowHole) {
            mPaint.setColor(holeColor);
            canvas.drawCircle(0, 0, mRadius * holeRadiusProportion / 100, mPaint);
        }
    }

    private void drawLineAndText(Canvas canvas) {

        double offsetRadians = Math.atan(yOffset / xOffset);
        float cosOffset = (float) Math.cos(offsetRadians);
        float sinOffset = (float) Math.sin(offsetRadians);

        for (PieEntry pie : mPieLists) {

            float halfAngle = pie.getCurrentStartAngle() + pie.getSweepAngle() / 2;
            float cos = (float) Math.cos(Math.toRadians(halfAngle));
            float sin = (float) Math.sin(Math.toRadians(halfAngle));
            float xCirclePoint = (mRadius + distance) * cos;
            float yCirclePoint = (mRadius + distance) * sin;

            mPaint.setColor(pie.getColor());
            if (pie.getLabel() != "")
            canvas.drawCircle(xCirclePoint, yCirclePoint, smallCircleRadius, mPaint);
            mPaint.setStyle(Paint.Style.STROKE);
            if (pie.getLabel() != "")
            canvas.drawCircle(xCirclePoint, yCirclePoint, bigCircleRadius, mPaint);
            mPaint.setStyle(Paint.Style.FILL);

            int quadrant = (int) (halfAngle + 90) / 90;
            float xLineStartPoint = 0;
            float yLineStartPoint = 0;
            float xLineTurningPoint = 0;
            float yLineTurningPoint = 0;
            float xLineEndPoint = 0;
            float yLineEndPoint = 0;

            String text = pie.getLabel() + " " +
                    new DecimalFormat("#.#").format(pie.getPercentage()) + "%";

            if (pie.getLabel() != "") {
                float cosLength = bigCircleRadius * cosOffset;
                float sinLength = bigCircleRadius * sinOffset;
                switch (quadrant) {
                    case 0:
                        xLineStartPoint = xCirclePoint + cosLength;
                        yLineStartPoint = yCirclePoint - sinLength;
                        xLineTurningPoint = xLineStartPoint + xOffset;
                        yLineTurningPoint = yLineStartPoint - yOffset;
                        xLineEndPoint = xLineTurningPoint + extend;
                        yLineEndPoint = yLineTurningPoint;
                        mPaint.setTextAlign(Paint.Align.RIGHT);
                        canvas.drawText(text, xLineEndPoint, yLineEndPoint - 5, mPaint);
                        break;
                    case 1:
                        xLineStartPoint = xCirclePoint + cosLength;
                        yLineStartPoint = yCirclePoint + sinLength;
                        xLineTurningPoint = xLineStartPoint + xOffset;
                        yLineTurningPoint = yLineStartPoint + yOffset;
                        xLineEndPoint = xLineTurningPoint + extend;
                        yLineEndPoint = yLineTurningPoint;
                        mPaint.setTextAlign(Paint.Align.RIGHT);
                        canvas.drawText(text, xLineEndPoint, yLineEndPoint - 5, mPaint);
                        break;
                    case 2:
                        xLineStartPoint = xCirclePoint - cosLength;
                        yLineStartPoint = yCirclePoint + sinLength;
                        xLineTurningPoint = xLineStartPoint - xOffset;
                        yLineTurningPoint = yLineStartPoint + yOffset;
                        xLineEndPoint = xLineTurningPoint - extend;
                        yLineEndPoint = yLineTurningPoint;
                        mPaint.setTextAlign(Paint.Align.LEFT);
                        canvas.drawText(text, xLineEndPoint, yLineEndPoint - 5, mPaint);
                        break;
                    case 3:
                        xLineStartPoint = xCirclePoint - cosLength;
                        yLineStartPoint = yCirclePoint - sinLength;
                        xLineTurningPoint = xLineStartPoint - xOffset;
                        yLineTurningPoint = yLineStartPoint - yOffset;
                        xLineEndPoint = xLineTurningPoint - extend;
                        yLineEndPoint = yLineTurningPoint;
                        mPaint.setTextAlign(Paint.Align.LEFT);
                        canvas.drawText(text, xLineEndPoint, yLineEndPoint - 5, mPaint);
                        break;
                    default:
                }
                canvas.drawLine(xLineStartPoint, yLineStartPoint, xLineTurningPoint, yLineTurningPoint, mPaint);
                canvas.drawLine(xLineTurningPoint, yLineTurningPoint, xLineEndPoint, yLineEndPoint, mPaint);
            }
        }
    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(sp2px(12));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1f);
    }

    private void initData() {
        if (isPieListsNull()) {
            return;
        }
        float currentStartAngle = startAngle;
        for (int i = 0; i < mPieLists.size(); i++) {
            PieEntry pie = mPieLists.get(i);
            pie.setCurrentStartAngle(currentStartAngle);
            float sweepAngle = pie.getPercentage() / 100 * 360;
            pie.setSweepAngle(sweepAngle);
            currentStartAngle += sweepAngle;


            if (mColorLists == null || mColorLists.size() == 0) {
                int j = i % colors.length;
                pie.setColor(colors[j]);
            } else {
                pie.setColor(mColorLists.get(i));
            }
        }
    }

    private void initColors() {
        if (isPieListsNull()) {
            return;
        }
        for (int i = 0; i < mPieLists.size(); i++) {
            mPieLists.get(i).setColor(mColorLists.get(i));
        }
    }

    private boolean isPieListsNull() {
        return mPieLists == null || mPieLists.size() == 0;
    }

    public void setData(ArrayList<PieEntry> pieLists) {
        this.mPieLists = pieLists;
        initData();
        invalidate();
    }

    public void setColors(ArrayList<Integer> colorLists) {
        this.mColorLists = colorLists;
        initColors();
        invalidate();
    }

    public void setShowHole(boolean isShowHole) {
        this.mShowHole = isShowHole;
        invalidate();
    }

    /**
     * Value of sp to value of px.
     *
     * @param spValue The value of sp.
     * @return value of px
     */
    public static int sp2px(final float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
