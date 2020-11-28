package com.xzy.study.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.xzy.study.recyclerview.test001.RecyclerViewActivity01;
import com.xzy.study.recyclerview.test002.RecyclerViewActivity02;
import com.xzy.study.recyclerview.test003.RecyclerViewActivity03;
import com.xzy.study.recyclerview.test004.demo.RecyclerViewActivity04;

/**
 * @author xzy
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // test001 rv 加载多布局，实现了 item 点击事件、item 内部控件点击事件
        findViewById(R.id.btn1).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            Intent intent = new Intent(this, RecyclerViewActivity01.class);
            // todo 调整 type 类型分别实现 ListView，GridView，瀑布流 效果
            bundle.putInt("type", 1);
            intent.putExtras(bundle);
            startActivity(intent);
        });
        // test002 rv 入场/添加/删除/更新 动效
        findViewById(R.id.btn2).setOnClickListener(v -> {
            startActivity(new Intent(this, RecyclerViewActivity02.class));
        });
        // test003 下拉刷新/上拉加载
        findViewById(R.id.btn3).setOnClickListener(v -> {
            startActivity(new Intent(this, RecyclerViewActivity03.class));
        });

        // test004 下拉刷新/上拉加载
        findViewById(R.id.btn4).setOnClickListener(v -> {
            startActivity(new Intent(this, RecyclerViewActivity04.class));
        });

    }
}