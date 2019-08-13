package com.xzy.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 菜单 menu 的用法。
 * @author xzy
 */
@SuppressWarnings("unused")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 一个零耦合的侧滑菜单，支持RecyclerView、ListView、GridView等不同条目布局，
     * 支持菜单在左或在右，可选滑动阻塞，是否禁用等功能
     * 参考项目 https://github.com/ljphawk/SwipeMenuLayout
     */
    private void handle(){

    }
}
