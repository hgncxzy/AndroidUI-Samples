package com.xzy.study.recyclerview.test004.lib;

/**
 * @author xzy
 */
public interface PullListener {
    /**
     *下拉刷新
     */
    void onRefresh();

    /**
     * 上拉加载
     */
    void onLoadMore();
}
