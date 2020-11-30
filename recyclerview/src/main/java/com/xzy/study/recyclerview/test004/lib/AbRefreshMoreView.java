package com.xzy.study.recyclerview.test004.lib;

import android.content.Context;

/**
 * 上拉更多基类
 * @author xzy
 */

public abstract class AbRefreshMoreView extends AbRefreshView {

    public AbRefreshMoreView(Context context) {
        super(context);
        mMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
    }

    //默认状态
    protected abstract void onNormalState();

    //正在刷新
    protected abstract void onLoadingMore();

    //刷新成功
    protected abstract void onResultSuccess();

    //刷新失败
    protected abstract void onResultFail();

}
