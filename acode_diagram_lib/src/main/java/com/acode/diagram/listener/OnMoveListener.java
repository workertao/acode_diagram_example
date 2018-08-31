package com.acode.diagram.listener;

/**
 * user:yangtao
 * date:2018/8/291429
 * email:yangtao@bjxmail.com
 * introduce:折线图滑动监听
 */
public interface OnMoveListener {
    /**
     * 滑动监听
     *
     * @param index 滑动第几个点
     */
    void onMoved(int index);
}
