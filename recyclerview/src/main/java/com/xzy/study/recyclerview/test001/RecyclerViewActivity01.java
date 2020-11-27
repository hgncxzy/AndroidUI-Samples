package com.xzy.study.recyclerview.test001;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.xzy.study.recyclerview.R;
import com.xzy.study.recyclerview.decoration.DividerGridItemDecoration;
import com.xzy.study.recyclerview.decoration.DividerListItemDecoration;

/**
 * 利用 RecyclerView 实现多 Item 布局的加载，
 * 多 Item 布局的加载的意思就是在开发过程中 List 的每一项可能根据需求的不同会加载不同的 Layout
 * 参考 https://www.jb51.net/article/80676.htm
 *
 * @author xzy
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "AlibabaRemoveCommentedCode"})
public class RecyclerViewActivity01 extends AppCompatActivity implements RecyclerViewAdapter.OnChildListener {
    private RecyclerView mRecyclerView;
    /**
     * 数据源
     */
    private String[] title = {
            "Blog : http://blog.csdn.net/Leejizhou.",
            "A good laugh and a long sleep are the best cures in the doctor's book.",
            "all or nothing, now or never ",
            "Be nice to people on the way up, because you'll need them on your way down.",
            "Be confident with yourself and stop worrying what other people think. Do what's best for your future happiness!",
            "Blessed is he whose fame does not outshine his truth.",
            "Create good memories today, so that you can have a good past"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main01);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);

        // 这里根据上一个页面的传入值来加载 LIST 或 GRID 或 FLOW
        int viewType = getIntent().getIntExtra("type", 0);
        if (viewType == 1) {
            // List
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            DividerListItemDecoration dividerListItemDecoration = new DividerListItemDecoration(this, DividerListItemDecoration.VERTICAL_LIST);
            mRecyclerView.addItemDecoration(dividerListItemDecoration);
            mRecyclerView.setLayoutManager(layoutManager);
        } else if (viewType == 2) {
            // Grid
            // mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
            // 设置跨行或者跨列的数目
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0) {
                        return 3;
                    } else if (position == 1) {
                        return 2;
                    }
                    return 1;
                }
            });
            DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(this);
            mRecyclerView.addItemDecoration(dividerGridItemDecoration);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else if (viewType == 3) {
            // 瀑布流 Flow
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        }

        // RecyclerView 设置 Adapter
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, title);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        // 设置监听事件
        recyclerViewAdapter.setListener(this);

    }

    @Override
    public void onChildClick(RecyclerView parent, View view, int position, String data) {
        Toast.makeText(this, "你点击了第" + position + "个 item", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageClick(int position) {
        Toast.makeText(this, "你点击了第" + position + "个 item 的图片", Toast.LENGTH_SHORT).show();
    }
}