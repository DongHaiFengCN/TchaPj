package com.application.tchapj.widiget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.application.tchapj.R;

/**
 * Create by zyy on 2019/6/14
 * Description: 文字上画斜线
 */
public class ObliqueLineTextView extends android.support.v7.widget.AppCompatTextView {

    private final Paint mLinePaint;
    private final int mLineColor;
    private double mAngle = 45;

    public ObliqueLineTextView(Context context) {
        this(context, null);
    }

    public ObliqueLineTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ObliqueLineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLineColor = Color.parseColor("#9296AB");
        mLinePaint.setColor(mLineColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //斜线坐标
        int startX, startY, endX, endY;

        //视图宽高
        int width = getWidth();
        int height = getHeight();

        //计算坐标
        startX = 0;
        startY = 0;
        endX = (int) ((width) * (1 + Math.sin(mAngle)));;
        endY = (int) ((height) * (1 + Math.sin(mAngle)));
        //画斜线
        canvas.drawLine(startX, startY, endX, endY, mLinePaint);
    }
}
