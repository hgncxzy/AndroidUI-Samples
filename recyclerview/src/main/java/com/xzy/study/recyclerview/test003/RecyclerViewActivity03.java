package com.xzy.study.recyclerview.test003;

import android.os.Handler;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.xzy.study.recyclerview.R;
import com.xzy.study.recyclerview.test003.refresh.PullToRefreshRecycleView;

import java.util.ArrayList;

/**
 * 自定义实现上拉刷新+下拉加载
 * 参考 https://blog.csdn.net/meijian531161724/article/details/50887391?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2.control
 *
 * @author xzy
 */
public class RecyclerViewActivity03 extends AppCompatActivity {

    private RecyclerView mRv;
    private CustomAdapter mAdapter;
    private ArrayList<String> datas;
    private PullToRefreshRecycleView mPRRV;

    private Handler mHandler = new Handler();

    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main03);
        mPRRV = findViewById(R.id.pull_to_refresh_rv);
        mPRRV.isFirst = true;
        initData();

        mPRRV.setOnRefreshListener(new PullToRefreshRecycleView.OnRefreshListener() {
            @Override
            public void onPullDownRefresh() {
                mHandler.postDelayed(() -> {
                    datas.add(0, "add" + System.currentTimeMillis());
                    mAdapter.notifyDataSetChanged();
                    mPRRV.completeRefresh();
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        if (page == 3) {
                            // 模拟无数据
                            mPRRV.setFooterViewState(PullToRefreshRecycleView.TYPE_NO_MORE_DATA);

                        } else {
                            datas.add("Create good memories today, so that you can have a good past");
                            datas.add("Blog : http://blog.csdn.net/Leejizhou.");
                            datas.add("A good laugh and a long sleep are the best cures in the doctor's book.");
                            datas.add("Be nice to people on the way up, because you'll need them on your way down.");
                            datas.add("all or nothing, now or never");
                            datas.add("Blessed is he whose fame does not outshine his truth.");

                            mAdapter.notifyDataSetChanged();
                            mPRRV.completeLoadMore();
                        }

                    }
                }, 1000);

            }
        });
    }

    private void initData() {
        datas = new ArrayList<>();
        mAdapter = new CustomAdapter(datas);
        mPRRV.setAdapter(mAdapter);


        datas.add("Create good memories today, so that you can have a good past");
        datas.add("Blog : http://blog.csdn.net/Leejizhou.");
        datas.add("A good laugh and a long sleep are the best cures in the doctor's book.");
        datas.add("Be nice to people on the way up, because you'll need them on your way down.");
        datas.add("all or nothing, now or never");
        datas.add("Blessed is he whose fame does not outshine his truth.");
        datas.add("Create good memories today, so that you can have a good past");
        datas.add("Blog : http://blog.csdn.net/Leejizhou.");
        datas.add("A good laugh and a long sleep are the best cures in the doctor's book.");
        datas.add("Be nice to people on the way up, because you'll need them on your way down.");
        datas.add("all or nothing, now or never");
        datas.add("Blessed is he whose fame does not outshine his truth.");
        datas.add("Create good memories today, so that you can have a good past");
        datas.add("Blog : http://blog.csdn.net/Leejizhou.");
        datas.add("A good laugh and a long sleep are the best cures in the doctor's book.");
        datas.add("Be nice to people on the way up, because you'll need them on your way down.");
        datas.add("all or nothing, now or never");
        datas.add("Blessed is he whose fame does not outshine his truth.");


        mHandler.postDelayed(() -> {

            mAdapter.notifyDataSetChanged();
            mPRRV.completeRefresh();
        }, 50);
    }
}
