package com.xzy.study.recyclerview.test005;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xzy.study.recyclerview.R;

/**
 * 参考 https://blog.csdn.net/chehec2010/article/details/83902263
 * github https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 *
 * @author xzy
 */
public class RecyclerViewActivity05 extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main05);
        TextView viewById = findViewById(R.id.tv_show);
        viewById.setText("参考: https://blog.csdn.net/chehec2010/article/details/83902263\n" +
                "  github: https://github.com/CymChad/BaseRecyclerViewAdapterHelper");
    }
}