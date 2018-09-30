package com.acode.diagram.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.acode.diagram.data.Config;

/**
 * user:yangtao
 * date:2018/9/271010
 * email:yangtao@bjxmail.com
 * introduce:左侧Y轴的view
 */
public class LeftYView extends View {
    private Paint yPaint;

    private Context context;

    public LeftYView(Context context) {
        this(context, null);
    }

    public LeftYView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeftYView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        //y轴
        yPaint = new Paint();
        yPaint.setAntiAlias(true);
        yPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        yPaint.setColor(Color.parseColor("#333333"));
        yPaint.setStyle(Paint.Style.FILL);
        yPaint.setStrokeWidth(2);
        yPaint.setPathEffect(null);
        yPaint.setTextSize(DimenUtils.dip2px(context, 14));
    }

    private int yInterval;
    private float averHeight;
    private int height;

    //设置Y轴的每个点的间隔
    public void setYInterval(int yInterval) {
        this.yInterval = yInterval;
    }

    public void setAverHeight(float averHeight) {
        this.averHeight = averHeight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
        Log.d("leftview", "width:" + width + "      height:" + height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(DimenUtils.dip2px(context, Config.margin), height - DimenUtils.dip2px(context, Config.margin));
        canvas.drawColor(Color.WHITE);
        //y轴文字
        for (int i = 0; i < 5; i++) {
            canvas.drawText(i * yInterval + "", -DimenUtils.dip2px(context, 22), -averHeight * i + 10, yPaint);
        }
        Log.d("leftview", "yInterval:" + yInterval + "      averHeight:" + averHeight + "     height" + height);
    }
}
