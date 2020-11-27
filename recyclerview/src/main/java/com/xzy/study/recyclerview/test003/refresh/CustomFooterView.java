package com.xzy.study.recyclerview.test003.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xzy.study.recyclerview.R;

/**
 * @author xzy
 */
public class CustomFooterView extends LinearLayout implements IFooterView{
    private ProgressBar mPb;
    private TextView mTv;

    public CustomFooterView(Context context) {
        super(context);
        init(context);
    }

    public CustomFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_footer_view, null);
        LayoutParams listParams = new LayoutParams(-1, -1);
        view.setLayoutParams(listParams);
        addView(view);

        mPb = (ProgressBar)view.findViewById(R.id.pb);
        mTv = (TextView) view.findViewById(R.id.text);
    }



    @Override
    public void onNoData() {
        mPb.setVisibility(View.GONE);
        mTv.setText("没有更多数据");
    }

    @Override
    public void onRefreshing() {
        mPb.setVisibility(View.VISIBLE);
        mTv.setText("正在加载...");
    }
}
