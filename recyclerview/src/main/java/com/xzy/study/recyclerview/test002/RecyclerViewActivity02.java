package com.xzy.study.recyclerview.test002;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xzy.study.recyclerview.R;
import com.xzy.study.recyclerview.decoration.DividerListItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * recyclerView 实现添加（添加动画）、删除（删除动画）、进场动画、
 * <p>
 * 进场动画 https://blog.csdn.net/C_biubiubiu/article/details/109852543
 * 自定义 itemAnimator参考：
 * https://www.jianshu.com/p/2a82b0341138
 * https://www.jianshu.com/p/b9aef3597f2d
 *
 * @author xzy
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "AlibabaRemoveCommentedCode"})
public class RecyclerViewActivity02 extends AppCompatActivity implements RecyclerViewAdapter.OnChildListener {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<String> list;
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
        setContentView(R.layout.activity_main02);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        // 数据源
        initData();
        // List
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerListItemDecoration dividerListItemDecoration = new DividerListItemDecoration(this, DividerListItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(dividerListItemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);
        // RecyclerView 设置 Adapter
        recyclerViewAdapter = new RecyclerViewAdapter(this, list);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        // 创建布局动画控制类 LayoutAnimationController ，待会给 RecyclerView 设置动画
        LayoutAnimationController lacRightSlide = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_anim_item_right_slipe);
        // 设置进场动画
        mRecyclerView.setLayoutAnimation(lacRightSlide);
        // 设置 item 操作动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // mRecyclerView.setItemAnimator(new ScaleItemAnimator());
        // 设置监听事件
        recyclerViewAdapter.setListener(this);
    }

    private void initData() {
        list = new ArrayList<>();
        Collections.addAll(list, title);
    }

    @Override
    public void onChildClick(RecyclerView parent, View view, int position, String data) {
        Toast.makeText(this, "你点击了第" + position + "个 item", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageClick(int position, View view) {
        if (view.getId() == R.id.iv_add_item) {
            Toast.makeText(this, "你点击了第" + position + "个 item 的添加图片", Toast.LENGTH_LONG).show();
            recyclerViewAdapter.add(0, "我是新增的内容" + System.currentTimeMillis());
        }
        if (view.getId() == R.id.iv_delete_item) {
            Toast.makeText(this, "你点击了第" + position + "个 item 的删除图片", Toast.LENGTH_LONG).show();
            recyclerViewAdapter.remove(position);
        }
        if (view.getId() == R.id.iv_change_item) {
            Toast.makeText(this, "你点击了第" + position + "个 item 的更新图片", Toast.LENGTH_LONG).show();
            recyclerViewAdapter.change(position, "你刚刚更新了这一条内容");
        }
    }
}