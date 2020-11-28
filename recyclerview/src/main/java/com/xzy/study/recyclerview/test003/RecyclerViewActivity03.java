
package com.xzy.study.recyclerview.test003;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.xzy.study.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考
 * https://github.com/ryanlijianchang/PullToLoadData-RecyclerView
 *
 * @author xzy
 */
public class RecyclerViewActivity03 extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<String> list;

    private int lastVisibleItem = 0;
    private final int PAGE_COUNT = 20;
    private final int TOTAL_COUNT = 60;
    private LinearLayoutManager mLayoutManager;
    private RecyclerViewAdapter adapter;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main03);
        initData();
        findView();
        initRefreshLayout();
        initRecyclerView();
    }

    /**
     * 数据源，模拟数据总量
     */
    private void initData() {
        list = new ArrayList<>();
        for (int i = 1; i <= TOTAL_COUNT; i++) {
            list.add("条目" + i);
        }
    }

    private void findView() {
        refreshLayout = findViewById(R.id.refreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initRefreshLayout() {
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        // 首次模拟加载数据
        mHandler.postDelayed(() -> {
            adapter = new RecyclerViewAdapter(getData(0, PAGE_COUNT), RecyclerViewActivity03.this, getData(0, PAGE_COUNT).size() > 0);
            recyclerView.setAdapter(adapter);
            refreshLayout.setRefreshing(false);
        }, 2000);
        loadMore();
    }


    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        adapter.resetDatas();
        updateRecyclerView(0, PAGE_COUNT);
        mHandler.postDelayed(() -> refreshLayout.setRefreshing(false), 1000);
    }

    /**
     * 上拉加载更多
     */
    private void loadMore() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!adapter.isFadeTips() && lastVisibleItem + 1 == adapter.getItemCount()) {
                        mHandler.postDelayed(() -> updateRecyclerView(adapter.getRealLastPosition(), adapter.getRealLastPosition() + PAGE_COUNT), 500);
                    }
                    if (adapter.isFadeTips() && lastVisibleItem + 2 == adapter.getItemCount()) {
                        mHandler.postDelayed(() -> updateRecyclerView(adapter.getRealLastPosition(), adapter.getRealLastPosition() + PAGE_COUNT), 500);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }


    /**
     * 更新 rv
     *
     * @param fromIndex 起始位置
     * @param toIndex   结束位置
     */
    private void updateRecyclerView(int fromIndex, int toIndex) {
        // 获取新的数据
        List<String> newData = getData(fromIndex, toIndex);
        if (newData.size() > 0) {
            adapter.updateList(newData, true);
        } else {
            adapter.updateList(null, false);
        }
    }

    /**
     * 获取新的数据
     *
     * @param firstIndex 起始位置
     * @param lastIndex  结束为止
     * @return 获取到的新的 list 数据
     */
    private List<String> getData(final int firstIndex, final int lastIndex) {
        List<String> resList = new ArrayList<>();
        for (int i = firstIndex; i < lastIndex; i++) {
            if (i < list.size()) {
                resList.add(list.get(i));
            }
        }
        return resList;
    }
}