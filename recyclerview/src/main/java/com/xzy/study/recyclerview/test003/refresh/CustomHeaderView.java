package com.xzy.study.recyclerview.test003.refresh;

import android.content.Context;
import android.util.AttributeSet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.xzy.study.recyclerview.R;
import com.xzy.study.recyclerview.test003.view.LoadingView;

/**
 * @author xzy
 */
public class CustomHeaderView extends IHeaderView {

    private TextView mTipTv;
    private LoadingView mImageView;
    private RotateAnimation rotateAnimation;

    public CustomHeaderView(Context context) {
        super(context);
    }

    public CustomHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
    }

    @Override
    public void onPullToRefresh(int pullheight) {
      mImageView.updateView((float)pullheight/getHeight());
        mTipTv.setText("下拉刷新");
        mImageView.rotateAnimation(false);
    }

    @Override
    public void onReleaseToRefresh(int pullheight) {
        mImageView.updateView((float)pullheight/getHeight());
        mTipTv.setText("松开刷新");
        mImageView.rotateAnimation(false);
    }

    @Override
    public void onRefreshing() {
        mTipTv.setText("正在刷新");
        mImageView.updateView(1);
        mImageView.rotateAnimation(true);
    }

    @Override
    public void onNormal() {
        mTipTv.setText("下拉刷新");
        mImageView.rotateAnimation(false);
    }

    @Override
    public View inflateView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_header_view, null);
        mTipTv = (TextView) view.findViewById(R.id.text);
        mImageView = (LoadingView) view.findViewById(R.id.imageView);

        return view;
    }

}
