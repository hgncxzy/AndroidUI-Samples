package com.xzy.statusbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 状态栏用法。
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
     * android 4.4以上沉浸式状态栏和沉浸式导航栏管理，适配横竖屏切换、刘海屏、软键盘弹出等问题，
     * 可以修改状态栏字体颜色和导航栏图标颜色，以及不可修改字体颜色手机的适配，
     * 适用于Activity、Fragment、DialogFragment、Dialog，PopupWindow，
     * 一句代码轻松实现，以及对bar的其他设置
     * <p>
     * 依赖库地址 https://github.com/gyf-dev/ImmersionBar
     */
    private void handle() {

    }

    /**
     * Android沉浸式状态栏，支持状态栏渐变色，纯色， 全屏，亮光、暗色模式，适配android 4.4 -10.0机型
     * 依赖库地址 https://github.com/Ye-Miao/StatusBarUtil
     */
    private void handle2() {

    }
}
