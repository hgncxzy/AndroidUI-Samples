package com.xzy.textview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * TextView 控件常见用法。
 *
 * @author xzy
 */
@SuppressWarnings("unused")
public class TextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        handle();
    }

    /**
     * 一个可以用代码处理控件的阴影效果，及用代码在TextView、EditText、Button等控件设置selector背景
     * （触摸反馈，样式变化、文字颜色变化、hint文字颜色变化等效果）的组件
     * 依赖库地址 https://github.com/liujinchao/XSelectorUtil
     */
    private void handle() {

    }

    /**
     * 一个增强的TextView库。可以实现文字两端对齐，文字竖排，以及自定义选择文字后的弹出菜单。
     * 依赖库地址 https://github.com/devilist/AdvancedTextView
     */
    private void handle2() {

    }

}
