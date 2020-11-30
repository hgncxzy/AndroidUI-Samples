package com.xzy.study.recyclerview.test004.lib;

import android.content.Context;
import android.view.Gravity;

/**
 * 下拉刷新基类
 * @author xzy
 */

public abstract class AbRefreshHeadView extends AbRefreshView {
    private int mRefreshHeight;

    public AbRefreshHeadView(Context context) {
        super(context);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        mRefreshHeight = onCreateRefreshLimitHeight();
        mMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0));
        setGravity(Gravity.BOTTOM);
    }

    protected int getRefreshHeight() {
        return mRefreshHeight;
    }

    //触发刷新的最小高度
    protected abstract int onCreateRefreshLimitHeight();

    //正在下拉
    protected abstract void onPullingDown();

    //已经达到可以刷新的状态
    protected abstract void onReleaseState();

    //执行刷新
    protected abstract void onRefreshing();

    //刷新成功
    protected abstract void onResultSuccess();

    //刷新失败
    protected abstract void onResultFail();
}
