package com.acode.diagram.listener;

import com.acode.diagram.utils.MyHorizerScrollView;

/**
 * user:yangtao
 * date:2018/9/271141
 * email:yangtao@bjxmail.com
 * introduce:横向的scrollview滑动监听
 */
public interface OnScrollViewListener {
    void onScrollChanged(MyHorizerScrollView scrollView, int x, int y, int oldx, int oldy);
}
