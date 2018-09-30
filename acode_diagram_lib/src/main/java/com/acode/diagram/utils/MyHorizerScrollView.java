package com.acode.diagram.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.acode.diagram.listener.OnScrollViewListener;

/**
 * user:yangtao
 * date:2018/9/271140
 * email:yangtao@bjxmail.com
 * introduce:功能
 */
public class MyHorizerScrollView extends HorizontalScrollView {

    private OnScrollViewListener scrollViewListener = null;

    public MyHorizerScrollView(Context context) {
        super(context);
    }

    public MyHorizerScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyHorizerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(OnScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
