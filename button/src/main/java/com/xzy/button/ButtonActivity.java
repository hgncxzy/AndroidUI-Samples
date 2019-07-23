package com.xzy.button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * Button 控件的常见用法。
 *
 * @author xzy
 */
public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        handle();
    }

    /**
     * 依赖包 com.android.util:xselector:1.0.2
     * 依赖库地址 https://github.com/liujinchao/XSelectorUtil
     * 用法参考 https://github.com/liujinchao/XSelectorUtil
     */
    private void handle() {

    }
}
