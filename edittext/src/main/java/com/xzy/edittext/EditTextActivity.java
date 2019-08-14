package com.xzy.edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * EditText 控件的常见用法。
 *
 * @author xzy
 */
@SuppressWarnings("unused")
public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
    }

    /**
     * 一个可以用代码处理控件的阴影效果，及用代码在TextView、EditText、Button等控件设置selector背景
     * （触摸反馈，样式变化、文字颜色变化、hint文字颜色变化等效果）的组件
     * 依赖库地址 https://github.com/liujinchao/XSelectorUtil
     */
    private void handle() {

    }

    /**
     * 一款简单、好用的自定义EditText（含一键删除&自定义样式）
     * 依赖库地址 https://github.com/Carson-Ho/SuperEditText
     */
    private void handle2() {

    }

    /**
     * TextInputLayout+TextInputEditText 打造精美的登录界面
     *  参考 https://blog.csdn.net/smile_Running/article/details/97260032
     */
    private void handle3() {

    }
}
