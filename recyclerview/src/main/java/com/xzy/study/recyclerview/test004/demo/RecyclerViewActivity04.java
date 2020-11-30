package com.xzy.study.recyclerview.test004.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xzy.study.recyclerview.R;
import com.xzy.study.recyclerview.test004.lib.PullListener;
import com.xzy.study.recyclerview.test004.lib.PullRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 参考 https://blog.csdn.net/a807891033/article/details/79523427
 * 自定义 header 和 footer 的 rv
 *
 * @author xzy
 */
public class RecyclerViewActivity04 extends Activity implements PullListener {
    private Context mContext;
    private PullRecyclerView mPull;
    private List<String> mData;
    private TestAdapter mAdapter;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main04);
        init();
    }

    private void init() {
        mData = new ArrayList<>();
        mPull = findViewById(R.id.pull);
        mAdapter = new TestAdapter();
        mPull.setHeadRefreshView(new SimpleRefreshHeadView(mContext))
                .setMoreRefreshView(new SimpleRefreshMoreView(mContext))
                .setUseLoadMore(true)
                .setUseRefresh(true)
                .setPullLayoutManager(new LinearLayoutManager(this))
                .setPullListener(this)
                .setPullItemAnimator(null)
                .build(mAdapter);
        loadData(100L);
    }

    @Override
    public void onRefresh() {
        loadData(1000L);
    }

    @Override
    public void onLoadMore() {
        loadData(1000L);
    }

    private void loadData(final long sleep) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String[] arr = new String[new Random().nextInt(10)];
                mData.addAll(Arrays.asList(arr));
                mAdapter.notifyItemChanged(mData.size() - arr.length, arr.length);
                mPull.onComplete(arr.length > 0);
            }
        }, sleep);
    }

    private class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TestViewHolder(mContext);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof TestViewHolder) {
                ((TestViewHolder) holder).setTip(position);
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        private class TestViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public TestViewHolder(Context context) {
                super(LayoutInflater.from(context).inflate(R.layout.item_view, null));
                tv = itemView.findViewById(R.id.tv);
            }

            private void setTip(int index) {
                tv.setText(String.valueOf(index));
            }
        }
    }
}
