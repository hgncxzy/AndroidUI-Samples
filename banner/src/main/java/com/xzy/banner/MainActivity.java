package com.xzy.banner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 轮播图相关库收集。
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
     *轮播图，支持多种自定义属性，可以设置轮播红点或者轮播数字，支持设置引导页。可以根据不同使用场景，
     * 可以选择无限循环，静态管理或者动态管理adapter，还可以设置暂停和开始轮播。后期添加了RecyclerView轮播图，
     * 同时自定义多种类型SnapHelper，卡片滑动流畅，目前已经用于多个正式项目中！！
     * 依赖库地址 https://github.com/yangchong211/YCBanner
     */
    private void handle(){

    }
}
