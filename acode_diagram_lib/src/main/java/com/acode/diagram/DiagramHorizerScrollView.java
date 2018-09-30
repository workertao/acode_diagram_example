package com.acode.diagram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.acode.diagram.bean.DiagramBean;
import com.acode.diagram.data.Config;
import com.acode.diagram.listener.OnItemClickListener;
import com.acode.diagram.listener.OnScrollViewListener;
import com.acode.diagram.listener.OnSizeChangeListener;
import com.acode.diagram.utils.DimenUtils;
import com.acode.diagram.utils.LeftYView;
import com.acode.diagram.utils.MyHorizerScrollView;

import java.util.ArrayList;

/**
 * user:yangtao
 * date:2018/9/261420
 * email:yangtao@bjxmail.com
 * introduce:功能
 */
public class DiagramHorizerScrollView extends RelativeLayout {
    private Context context;

    //折线图
    private DiagramLineView diagramLineView;

    //左侧Y轴view
    private LeftYView leftYView;

    public DiagramHorizerScrollView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public DiagramHorizerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public DiagramHorizerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        diagramLineView = new DiagramLineView(context);
        leftYView = new LeftYView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width = DimenUtils.dip2px(context, Config.margin + 10);
        params.height = DimenUtils.dip2px(context, 211);
        leftYView.setLayoutParams(params);
        leftYView.setX(-DimenUtils.dip2px(context, Config.margin + 10));
        diagramLineView.setIsScroll(true);
        RelativeLayout linearLayout = new RelativeLayout(context);
        linearLayout.addView(diagramLineView);
        MyHorizerScrollView myHorizerScrollView = new MyHorizerScrollView(context);
        myHorizerScrollView.addView(linearLayout);
        myHorizerScrollView.setHorizontalScrollBarEnabled(false);
        addView(myHorizerScrollView);
        addView(leftYView);
        myHorizerScrollView.setScrollViewListener(new OnScrollViewListener() {
            @Override
            public void onScrollChanged(MyHorizerScrollView scrollView, int x, int y, int oldx, int oldy) {
                //可滑动距离
                int offsetX = DimenUtils.dip2px(context, Config.margin+10);
                if (x <= 0) {
                    leftYView.setX(-offsetX);
                } else if (x > 0 && x < offsetX) {
                    leftYView.setX(-offsetX + x);
                } else if (x > offsetX) {
                    //滑动完成直接展示
                    leftYView.setX(0);
                }
                Log.d("滑动", "滑动X：" + x + "     offsetX：" + offsetX);
            }
        });
    }

    //设置数据源
    public void setData(ArrayList<DiagramBean> diagramBeans) {
        diagramLineView.setData(diagramBeans);
        diagramLineView.setOnSizeChangeListener(new OnSizeChangeListener() {
            @Override
            public void onSize() {
                leftYView.setYInterval(diagramLineView.getyInterval());
                leftYView.setAverHeight(diagramLineView.getAverHeight());
                leftYView.setHeight(diagramLineView.getThisHeight());
                leftYView.invalidate();
            }
        });
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

    //刷新数据
    public void notifyData() {
        diagramLineView.notifyData();
    }

    //设置Y轴的每个点的间隔
    public void setYInterval(int yInterval) {
        diagramLineView.setYInterval(yInterval);
    }

    //设置点击事件
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        diagramLineView.setOnItemClickListener(onItemClickListener);
    }
}
