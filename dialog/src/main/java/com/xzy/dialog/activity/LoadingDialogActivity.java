package com.xzy.dialog.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.widget.jcdialog.DialogUtils;
import com.xzy.dialog.R;

/**
 * 加载框的用法。
 *
 * @author xzy
 */
public class LoadingDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_dialog);
        handle();
    }

    private void handle() {
        findViewById(R.id.horizontalLoading)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogUtils.showLoadingHorizontal(LoadingDialogActivity.this,
                                "加载中...").show();
                    }
                });
        findViewById(R.id.verticalLoading)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogUtils.showLoadingVertical(LoadingDialogActivity.this,
                                "加载中...").show();
                    }
                });
        findViewById(R.id.horizontalLoadingGray)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogUtils.showLoadingHorizontal(LoadingDialogActivity.this,
                                "加载中...", false).show();
                    }
                });
        findViewById(R.id.verticalLoadingGray)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogUtils.showLoadingVertical(LoadingDialogActivity.this,
                                "加载中...", false).show();
                    }
                });
        findViewById(R.id.mdStyleHorizontalLoading)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogUtils.showMdLoadingHorizontal(LoadingDialogActivity.this,
                                "加载中...").show();
                    }
                });
        findViewById(R.id.mdStyleVerticalLoading)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogUtils.showMdLoadingVertical(LoadingDialogActivity.this,
                                "加载中...").show();
                    }
                });
    }
}
