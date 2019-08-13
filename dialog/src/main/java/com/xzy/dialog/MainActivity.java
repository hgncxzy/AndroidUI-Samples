package com.xzy.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.widget.jcdialog.DialogUtils;
import com.xzy.dialog.activity.AddressActivity;
import com.xzy.dialog.activity.DialogActivity;
import com.xzy.dialog.activity.LoadingDialogActivity;
import com.xzy.dialog.activity.PopupWindowActivity;
import com.xzy.dialog.activity.SelectDialogActivity;
import com.xzy.dialog.activity.TimePickerActivity;

/**
 * dialog 使用 demo。
 *
 * @author xzy
 */
@SuppressWarnings("unused")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handle();
    }

    /**
     * 关于toast、等待框、对话框、选择框、地址选择框、软键盘等工具的封装
     * 依赖库 com.liujc.util:jcdialog:1.0.1
     * 依赖库地址 https://github.com/liujinchao/DialogUi
     */
    private void handle() {

        DialogUtils.init(this);

        findViewById(R.id.popupwindow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PopupWindowActivity.class));
            }
        });

        findViewById(R.id.loadingDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoadingDialogActivity.class));
            }
        });

        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
            }
        });

        findViewById(R.id.selectDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SelectDialogActivity.class));
            }
        });

        findViewById(R.id.timepicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TimePickerActivity.class));
            }
        });
        findViewById(R.id.addressPicker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddressActivity.class));
            }
        });
    }


    /**
     * DialogFragment 封装,高效实现各种弹窗效果
     * 依赖库 com.timmy.tdialog:tdialog:2.1.1
     * 依赖库地址 https://github.com/Timmy-zzh/TDialog
     */
    private void handle2() {

    }

    /**
     * Dialog 封装
     * 依赖库 com.github.the-pig-of-jungle.smart-show:all:2.7.8
     * 依赖库地址 https://github.com/the-pig-of-jungle/smart-show
     */
    private void handle3() {

    }
}
