package com.github.danielfelgar.drawreceiptlib;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 12/08/2016.
 */
public class DrawText implements IDrawItem {
    private Paint paint = new Paint();
    private String text;
    private int tmpWidth;
    private boolean newLine;
    private float rowHeight;

    public DrawText(String text, int tmpWidth) {

        this.text = text;
        this.tmpWidth = tmpWidth;
    }

    @Override
    public void drawOnCanvas(Canvas canvas, float x, float y) {
        //canvas.drawText(text, getX(canvas, x), getY(y), paint);
        drawMultiLineText(this.text, x, y, paint, canvas);
    }

    private void drawMultiLineText(String str, float x, float y, Paint paint, Canvas canvas) {
        String[] lines = str.split("\n");
        float xx = getX(canvas, x);
        float yy = getY(y);
        rowHeight = 0;

        for (String line : lines) {

            final List<String> subLines = new ArrayList<>();
            makeSecureLines(line, paint, canvas.getWidth(), subLines);

            for (String subTxt : subLines) {
                canvas.drawText(subTxt, xx, yy + rowHeight, paint);
                rowHeight += getHeight();
                //xx = 0;
            }
        }
    }

    private int makeSecureLines(String text, Paint mPaint, float secureLineWidth, List<String> lines) {

        if (TextUtils.isEmpty(text))
            return 0;

        int measuredNum = mPaint.breakText(text, true, secureLineWidth, null);
        lines.add(text.substring(0, measuredNum));
        String leftStr = text.substring(measuredNum);

        if(leftStr.length() > 0){
            makeSecureLines(leftStr, mPaint, secureLineWidth, lines);
        }

        return lines.size();
    }

    private int computeLineAmount() {
        String[] lines = text.split("\n");
        int inc = 0;
        ArrayList<String> arr = new ArrayList<>();
        for (String line : lines) {
            arr.clear();
            inc += makeSecureLines(line, paint, tmpWidth, arr);
        }
        return inc;
    }

    private float getY(float y) {
        float baseline = -paint.ascent();
        return baseline + y;
    }

    private float getX(Canvas canvas, float x) {
        float xPos = x;
        if (paint.getTextAlign().equals(Paint.Align.CENTER)) {
            xPos = (canvas.getWidth() / 2);
        } else if (paint.getTextAlign().equals(Paint.Align.RIGHT)) {
            xPos = canvas.getWidth();
        }
        return xPos;
    }

    @Override
    public int getHeight() {
        return newLine ? (int) getTextSize() : 0;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return paint.getColor();
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public float getTextSize() {
        return paint.getTextSize();
    }

    public void setTextSize(float textSize) {
        paint.setTextSize(textSize);
    }

    public void getTypeface() {
        paint.getTypeface();
    }

    public void setTypeface(Typeface typeface) {
        paint.setTypeface(typeface);
    }

    public void setAlign(Paint.Align align) {
        paint.setTextAlign(align);
    }

    public Paint.Align getAlign() {
        return paint.getTextAlign();
    }

    public void setNewLine(boolean newLine) {
        this.newLine = newLine;
    }

    public boolean getNewLine() {
        return newLine;
    }

    public float getRowHeight() {
        if (rowHeight == 0) {
            return computeLineAmount() * getHeight();
        }
        return rowHeight;
    }

    public void setRowHeight(float rowHeight) {
        this.rowHeight = rowHeight;
    }
}
