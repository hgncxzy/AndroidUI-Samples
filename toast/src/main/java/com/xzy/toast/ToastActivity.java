package com.xzy.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.widget.jcdialog.DialogUtils;
import com.widget.jcdialog.utils.ToastUitl;

/**
 * toast 项目的用法。
 *
 * @author xzy
 */
@SuppressWarnings("unused")
public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DialogUtils.init(this);
        handle();
    }

    /**
     * 依赖库 com.liujc.util:jcdialog:1.0.1
     * 依赖库地址 https://github.com/liujinchao/DialogUi
     */
    public void handle() {
        findViewById(R.id.topToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUitl.showToastTop("顶部的Toast");
            }
        });
        findViewById(R.id.bottomToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUitl.showToast("默认的Toast");
            }
        });
        findViewById(R.id.centerToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUitl.showToastCenter("中部的Toast");
            }
        });
        findViewById(R.id.imgToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUitl.showToastWithImg("带图片的Toast", R.drawable.ic_success);
            }
        });
    }

    /**
     * toast 封装
     * 依赖库 com.github.the-pig-of-jungle.smart-show:all:2.7.8
     * 依赖库地址 https://github.com/the-pig-of-jungle/smart-show
     */
    private void handle2() {

    }

    /**
     * Toast 封装
     * 依赖库  com.github.goweii:AnyLayer:3.0.0
     * 依赖库地址 https://github.com/goweii/AnyLayer
     */
    private void handle3() {

    }

}
