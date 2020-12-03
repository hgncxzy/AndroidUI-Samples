package com.xzy.refreshlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 上拉加载，下拉刷新。
 *
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
     * 下拉刷新，上拉加载，真实的回弹(overscroll)效果(媲美qq)，
     * 且大小只有37KB(是其他主流刷新库或回弹库的1/2,1/3,甚至是1/4)，同时，自定义header和footer，
     * 可以实现任何你想的到的功能(例如:自动触发加载更多、二级刷新等)
     * 依赖库地址 https://github.com/genius158/PullRefreshLayout
     */
    private void handle1() {

    }

    /**
     * 参考项目 recyclerview ，里面包含 rv 动画，多 item ，手动实现 rv 的加载/刷新功能。
     */
    private void handle2() {

    }
}
