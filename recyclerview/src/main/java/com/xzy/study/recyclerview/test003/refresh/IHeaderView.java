package com.xzy.study.recyclerview.test003.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by mjc on 2015/12/26.
 */
public abstract class IHeaderView extends FrameLayout {
    public IHeaderView(Context context) {
        super(context);
        init(context);
    }

    public IHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    protected void init(Context context) {
        addView(inflateView(context));
    }

    public abstract void onPullToRefresh(int pullheight);//下拉刷新

    public abstract void onReleaseToRefresh(int pullheight);//松开可刷新

    public abstract void onRefreshing();//正在刷新
    public abstract void onNormal();//正常状态

    public abstract View inflateView(Context context);
}
