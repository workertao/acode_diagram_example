package com.acode.diagram;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.acode.diagram.bean.DiagramBean;
import com.acode.diagram.data.Config;
import com.acode.diagram.listener.OnMoveListener;
import com.acode.diagram.utils.DimenUtils;

import java.util.ArrayList;

/**
 * user:yangtao
 * date:2018/8/271434
 * email:yangtao@bjxmail.com
 * introduce:自定义折线图
 */
public class DiagramView extends RelativeLayout {
    private final static String TAG = "Diagram";

    private Context context;

    //折线图
    private DiagramLineView diagramLineView;

    //光标
    private View progressLayout;

    //光标
    private int progressLayoutId = R.layout.diagram_progress;


    //最后一次触摸的坐标
    private float lastTouchX;

    //当前的X坐标
    private float currentX;

    //滑动监听，到达某一节点回调
    private OnMoveListener onMoveListener;

    //光标其实位置
    private float startX;

    //光标下边的小圆圈
    private View view_point;

    public DiagramView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public DiagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public DiagramView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        progressLayout = View.inflate(context, progressLayoutId, null);
        view_point = progressLayout.findViewById(R.id.view_point);
        diagramLineView = new DiagramLineView(context);
        addView(diagramLineView);
        addView(progressLayout);
        setPosition();
        startX = -DimenUtils.dip2px(context, 50) + DimenUtils.dip2px(context, Config.margin);
        progressLayout.setX(startX);
        setBackgroundColor(Color.parseColor("#ffffff"));
    }

    //设置光标的位置
    private void setPosition() {
        ViewTreeObserver obPoint = view_point.getViewTreeObserver();
        obPoint.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.addRule(ALIGN_PARENT_LEFT);
                params.bottomMargin = DimenUtils.dip2px(context, Config.margin) - (view_point.getHeight() / 2);
                progressLayout.setLayoutParams(params);
                startX = DimenUtils.dip2px(context, Config.margin) - (view_point.getHeight() / 2);
                progressLayout.setX(startX);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastTouchX = event.getX();
                Log.d(TAG, "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                float offsetX = lastTouchX - event.getX();
                currentX = currentX + opposite(offsetX);
                if (currentX <= startX) {
                    currentX = startX;
                }
                if (currentX >= diagramLineView.getThisWidth() + startX) {
                    currentX = diagramLineView.getThisWidth() + startX;
                }
                progressLayout.setX(currentX);
                int index = (int) ((currentX - startX) / diagramLineView.getAverWidth());
                Log.d(TAG, "index:" + index);
                if (index < diagramLineView.getData().size() && onMoveListener != null) {
                    onMoveListener.onMoved(index);
                }
                lastTouchX = event.getX();
                Log.d(TAG, "ACTION_MOVE   currentX " + currentX + "   平均宽：" + diagramLineView.getAverWidth());
                break;
        }
        return true;
    }

    //获得坐标的相反数
    private float opposite(float number) {
        return number * -1;
    }

    //设置数据源
    public void setData(ArrayList<DiagramBean> diagramBeans) {
        diagramLineView.setData(diagramBeans);
    }

    //设置X轴坐标数量
    public void setXLength(int xLength) {
        diagramLineView.setXLength(xLength);
    }

    //设置Y轴坐标数量
    public void setYLength(int yLength) {
        diagramLineView.setYLength(yLength);
    }

    //是否执行动画
    public void setIsAnim(boolean isAnim) {
        diagramLineView.setIsAnim(isAnim);
    }

    //设置点点颜色 stokeColor外圈颜色
    public void setStokeColor(int stokeColor) {
        diagramLineView.setStokeColor(stokeColor);
    }

    //设置点点颜色 fillColor填充颜色
    public void setFillColor(int fillColor) {
        diagramLineView.setFillColor(fillColor);
    }

    //滑动监听
    public DiagramView setOnMoveListener(OnMoveListener onMoveListener) {
        this.onMoveListener = onMoveListener;
        return this;
    }
}
