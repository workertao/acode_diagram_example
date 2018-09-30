package com.acode.diagram;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.acode.diagram.bean.DiagramBean;
import com.acode.diagram.data.Config;
import com.acode.diagram.listener.OnItemClickListener;
import com.acode.diagram.listener.OnSizeChangeListener;
import com.acode.diagram.utils.DimenUtils;
import com.acode.diagram.utils.DisplayUtils;

import java.util.ArrayList;

/**
 * user:yangtao
 * date:2018/8/241358
 * email:yangtao@bjxmail.com
 * introduce:折线图
 */
public class DiagramLineView extends View {
    private final static String TAG = "Diagram";

    private Context context;

    //paint
    private Paint xPaint, yPaint, linePaint, effectPaint, pointPaint;

    //path
    private Path path;

    //X轴展示几个
    private int xLength = 7;

    //y轴展示几个
    private int yLength = 4;

    //x平均一个代表多少
    private int xAverWidth;

    //y平均一个代表多少
    private int yAverWidth;

    //设置点点颜色 stokeColor外圈颜色
    private int stokeColor;

    //设置点点颜色 fillColor填充颜色
    private int fillColor;

    //实际宽高
    private int thisWidth;

    //实际宽高
    private int thisHeight;

    //是否展示加载动画
    private boolean isAnim = true;

    //记录点点
    private float[] segent;

    //动画执行比例
    private float lineFraction;

    //PathMeasure 将折线图路径添加到PathMeasure，进行动画展示
    private PathMeasure pathMeasure;

    //数据源
    private ArrayList<DiagramBean> diagramBeans;

    //Y轴每个点的间隔
    private int yInterval;

    //是否可以滑动
    private boolean isScroll;

    //监听控件的宽高变化
    private OnSizeChangeListener onSizeChangeListener;

    public DiagramLineView(Context context) {
        this(context, null);
    }

    public DiagramLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiagramLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public DiagramLineView setOnSizeChangeListener(OnSizeChangeListener onSizeChangeListener) {
        this.onSizeChangeListener = onSizeChangeListener;
        return this;
    }

    //初始化
    private void init() {
        initPaint();
        initPath();
        initPathMeasure();
    }

    //初始化paint
    private void initPaint() {
        //x轴
        xPaint = new Paint();
        xPaint.setAntiAlias(true);
        xPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        xPaint.setColor(Color.parseColor("#1e8ada"));
        xPaint.setStrokeWidth(5);
        xPaint.setPathEffect(null);
        xPaint.setTextSize(DimenUtils.dip2px(context, 14));

        //y轴
        yPaint = new Paint();
        yPaint.setAntiAlias(true);
        yPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        yPaint.setColor(Color.parseColor("#333333"));
        yPaint.setStyle(Paint.Style.FILL);
        yPaint.setStrokeWidth(2);
        yPaint.setPathEffect(null);
        yPaint.setTextSize(DimenUtils.dip2px(context, 14));

        //线条
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        linePaint.setPathEffect(null);
        linePaint.setColor(Color.parseColor("#ff4400"));
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(5);

        //虚线
        effectPaint = new Paint();
        effectPaint.setAntiAlias(true);
        effectPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        effectPaint.setStyle(Paint.Style.STROKE);
        effectPaint.setColor(Color.parseColor("#999999"));
        effectPaint.setStrokeWidth(3);
        effectPaint.setPathEffect(new DashPathEffect(new float[]{10f, 5f}, 0));

        //点
        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setStrokeWidth(5);
    }

    //初始化path
    private void initPath() {
        path = new Path();
    }

    //初始化PathMeasure
    private void initPathMeasure() {
        pathMeasure = new PathMeasure();
    }

    //初始化pathMeasure的path路径
    private void initLineData() {
        int length = diagramBeans.size();
        segent = new float[length - 1];
        drawLinePath(null);
    }

    //画线
    private void drawLinePath(Canvas canvas) {
//        path.moveTo(0, diagramBeans.get(0).getDy());
        path.moveTo(0, getRelY(0));
        // 连接线
        for (int i = 1; i < diagramBeans.size(); i++) {
//            path.lineTo(getAverWidth() * i, diagramBeans.get(i).getDy());
            path.lineTo(getAverWidth() * i, getRelY(i));
            pathMeasure.setPath(path, false);
            segent[i - 1] = pathMeasure.getLength();
        }
        // 画线
        if (canvas == null) {
            return;
        }
        canvas.drawPath(path, linePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec == 0) {
            thisWidth = getSAWidth() * (xLength) + ((DimenUtils.dip2px(context, Config.margin) * 2));
            setMeasuredDimension(thisWidth, getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
            return;
        }
        setMeasuredDimension(
                getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //宽度减去两边的边距
        thisWidth = w - (DimenUtils.dip2px(context, Config.margin) * 2);
        Log.d(TAG, "w:" + w + "   thisWidth:" + thisWidth);
        thisHeight = h;
        notifyData();
        if (onSizeChangeListener==null){
            return;
        }
        onSizeChangeListener.onSize();
    }

    //reset画笔
    private void resetPaint() {
        xPaint.reset();
        yPaint.reset();
        linePaint.reset();
        effectPaint.reset();
        pointPaint.reset();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(DimenUtils.dip2px(context, Config.margin + 10), thisHeight - DimenUtils.dip2px(context, Config.margin));
        resetPaint();
        path.reset();
        initPaint();
        initPath();
        //坐标系
        drawXY(canvas);
        if (diagramBeans == null) {
            return;
        }
        //动画
        if (isAnim) {
            drawLinePathForAnim(canvas);
        } else {
            drawLinePath(canvas);
            // 画点
            for (int i = 0; i < diagramBeans.size(); i++) {
                drawPoint(canvas, i);
                drawText(canvas, i);
            }
        }
    }

    //折线动画
    private void startAnim() {
        ValueAnimator mAnimator = ValueAnimator.ofFloat(0, 1.0f);//绘制范围0到100
        mAnimator.setRepeatCount(0);
        mAnimator.setDuration(5000);//设置动画执行完需要的时间
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lineFraction = animation.getAnimatedFraction();
                invalidate();
            }
        });
        mAnimator.start();
    }

    //画点
    private void drawPoint(Canvas canvas, int i) {
        //设置外圈颜色，默认实心颜色为白色
        if (stokeColor != 0 && fillColor == 0) {
            pointPaint.setColor(stokeColor);
            pointPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(getAverWidth() * i, getRelY(i), 7, pointPaint);

            pointPaint.setColor(Color.WHITE);
            pointPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getAverWidth() * i, getRelY(i), 5, pointPaint);
        }
        //设置实心颜色
        if (stokeColor == 0 && fillColor != 0) {
            pointPaint.setColor(fillColor);
            pointPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getAverWidth() * i, getRelY(i), 12, pointPaint);
        }
        //设置渐变色
        if (stokeColor != 0 && fillColor != 0) {
            pointPaint.setColor(stokeColor);
            pointPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(getAverWidth() * i, getRelY(i), 7, pointPaint);

            pointPaint.setColor(fillColor);
            pointPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(getAverWidth() * i, getRelY(i), 5, pointPaint);
        }
    }

    //画字
    private void drawText(Canvas canvas, int i) {
        //画字还用真是的数据
        String text = -((int) diagramBeans.get(i).getDy()) + "";
        Rect rect = new Rect();
        yPaint.getTextBounds(text, 0, text.length(), rect);
        //字的位置使用计算的比例来画
        canvas.drawText(text, getAverWidth() * i, getRelY(i) - rect.height(), yPaint);
    }

    //坐标系
    private void drawXY(Canvas canvas) {
        // x轴
        //记录drawLines的参数,第一个数组的含义，两两组成一个坐标，前两个是起始点，再之后两个是结束点，后边的是之间的坐标
        ArrayList<Float> floats = new ArrayList<>();
        floats.add(0.0f);
        floats.add(0.0f);
        floats.add((float) thisWidth);
        floats.add(0.0f);
        for (int i = 1; i < xLength; i++) {
            float x = getAverWidth() * i;
            float y = 0;
            floats.add(x);
            floats.add(y);
        }
        Float[] xLine = floats.toArray(new Float[floats.size()]);
        float[] xValue = new float[floats.size()];
        for (int i = 0; i < xLine.length; i++) {
            xValue[i] = xLine[i].floatValue();
        }
        canvas.drawLines(xValue, xPaint);
        if (diagramBeans != null) {
            //x轴文字
            for (int i = 0; i < xLength; i++) {
                canvas.drawText(diagramBeans.get(i).getStrdx(), getAverWidth() * i - 30, 50, yPaint);
            }
        }
        //y轴文字
        for (int i = 0; i < yLength; i++) {
            canvas.drawText(i * yInterval + "", -DimenUtils.dip2px(context, 32), -getAverHeight() * i + 10, yPaint);
        }
        //虚线
        for (int i = 1; i < yLength; i++) {
            //虚线
            Path path = new Path();
            path.moveTo(0, -getAverHeight() * i);
            path.lineTo(thisWidth, -getAverHeight() * i);
            canvas.drawPath(path, effectPaint);
        }
    }

    //计算Y轴的点的比例，因为像素太高
    private float getRelY(int i) {
        float a = getAverHeight() / yInterval;
        float b = diagramBeans.get(i).getDy() * a;
        return b;
    }

    //线条动画
    private void drawLinePathForAnim(Canvas canvas) {
        if (lineFraction == 0.0f) {
            return;
        }
        Path path = new Path();
        float currentLength = pathMeasure.getLength() * lineFraction;
        pathMeasure.getSegment(0, currentLength, path, true);
        canvas.drawPath(path, linePaint);
        int index = -1;
        for (int j = 0; j < segent.length; j++) {
            if (currentLength < segent[j]) {
                index = j;
                break;
            }
        }
        if (index == -1) {
            index = diagramBeans.size() - 1;
        }
        // 画点
        for (int i = 0; i < diagramBeans.size() && i < (index + 1); i++) {
            drawPoint(canvas, i);
            drawText(canvas, i);
        }
    }


    //宽度平均值
    public float getAverWidth() {
        if (isScroll) {
            return thisWidth / xLength;
        }
        return thisWidth / (xLength - 1);
    }

    //高度平均值
    public float getAverHeight() {
        return thisHeight / yLength;
    }

    //获取折线图的宽度
    public int getThisWidth() {
        return thisWidth;
    }

    //获取折线图的高度
    public int getThisHeight() {
        return thisHeight;
    }

    //设置X轴坐标数量
    public void setXLength(int xLength) {
        this.xLength = xLength;
    }

    //设置Y轴坐标数量
    public void setYLength(int yLength) {
        this.yLength = yLength;
    }

    //是否执行动画
    public void setIsAnim(boolean isAnim) {
        //用户传的true，并且当前版本>5.0的时候
        if (isAnim && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.isAnim = true;
        } else {
            this.isAnim = false;
        }
    }

    //设置数据源
    public void setData(ArrayList<DiagramBean> diagramBeans) {
        //处理下数据，因为坐标系在左上角，往下是正坐标，上边是负坐标，做一个取相反数的操作
        if (diagramBeans == null || diagramBeans.size() == 0) {
            return;
        }
        for (int i = 0; i < diagramBeans.size(); i++) {
            diagramBeans.get(i).setDy(-diagramBeans.get(i).getDy());
        }
        this.diagramBeans = diagramBeans;
    }

    //获取数据源
    public ArrayList<DiagramBean> getData() {
        return diagramBeans;
    }


    //设置点点颜色 stokeColor外圈颜色
    public void setStokeColor(int stokeColor) {
        this.stokeColor = context.getResources().getColor(stokeColor);
    }

    //设置点点颜色 fillColor填充颜色
    public void setFillColor(int fillColor) {
        this.fillColor = context.getResources().getColor(fillColor);
    }

    //更新数据
    public void notifyData() {
        if (diagramBeans == null) {
            return;
        }
        initLineData();
        if (isAnim) {
            startAnim();
        } else {
            invalidate();
        }
    }

    //设置Y轴的每个点的间隔
    public void setYInterval(int yInterval) {
        this.yInterval = yInterval;
    }

    public int getyInterval() {
        return yInterval;
    }

    //获取将屏幕分成7分后的X轴宽度
    private int getSAWidth() {
        int width = DisplayUtils.getDisplayWidth(context);
        int relWidth = width - (DimenUtils.dip2px(context, Config.margin)) - (DimenUtils.dip2px(context, 14));
        int sdWidth = relWidth / 7;
        return sdWidth;
    }

    //设置是否可以滑动
    public void setIsScroll(boolean isScroll) {
        this.isScroll = isScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (onItemClickListener == null) {
            return false;
        }
        if (getData() == null || getData().size() == 0) {
            return false;
        }
        int index = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() > (getAverWidth() / 2)) {
                    index = (int) (event.getX() / getAverWidth());
                }
                if (index>=xLength){
                    return false;
                }
                onItemClickListener.onItemClick(index);
                break;
        }
        return false;
    }

    public OnItemClickListener onItemClickListener;

    public DiagramLineView setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }
}
