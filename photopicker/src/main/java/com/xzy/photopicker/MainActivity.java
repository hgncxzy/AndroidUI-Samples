package com.xzy.photopicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 图片选择器的用法。
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
     * 一款图片选择器，支持直接拍照并裁剪，单选裁剪，图片多选，裁剪比例设置 等
     * 依赖库地址 https://github.com/q805699513/PhotoPicker
     */
    private void handle1() {

    }
}
