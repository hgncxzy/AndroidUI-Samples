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
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handle();
    }

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
}
