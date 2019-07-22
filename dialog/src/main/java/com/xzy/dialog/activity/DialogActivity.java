package com.xzy.dialog.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.widget.jcdialog.DialogUtils;
import com.widget.jcdialog.listener.DialogUIListener;
import com.xzy.dialog.R;

import static com.widget.jcdialog.utils.ToastUitl.showToast;

/**
 * 对话框使用 demo。
 *
 * @author xzy
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        handle();
    }

    private void handle() {
        findViewById(R.id.normalDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View rootView = View.inflate(DialogActivity.this,
                        R.layout.custom_dialog_layout, null);
                final Dialog dialog = DialogUtils
                        .showCustomAlert(DialogActivity.this,
                                rootView,
                                Gravity.CENTER,
                                true,
                                false)
                        .show();
                rootView
                        .findViewById(R.id.btn_ok)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DialogUtils.dismiss(dialog);
                            }
                        });
            }
        });
        findViewById(R.id.bottomDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View rootViewB = View.inflate(DialogActivity.this,
                        R.layout.custom_dialog_layout,
                        null);
                DialogUtils.showCustomBottomAlert(DialogActivity.this,
                        rootViewB).show();
            }
        });
        findViewById(R.id.mdStyleDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showMdAlert(DialogActivity.this,
                        "标题",
                        "内容",
                        new DialogUIListener() {
                            @Override
                            public void onPositive() {
                                showToast("onPositive");
                            }

                            @Override
                            public void onNegative() {
                                showToast("onNegative");
                            }

                        }).show();
            }
        });
        findViewById(R.id.textDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showDialogTie(DialogActivity.this,
                        "纯文本 Dialog").show();
            }
        });
        findViewById(R.id.tipsDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showAlert(DialogActivity.this,
                        "标题",
                        "提示框",
                        "aaa",
                        "bbb",
                        "确定",
                        "",
                        true,
                        new DialogUIListener() {
                            @Override
                            public void onPositive() {
                                showToast("onPositive");
                            }

                            @Override
                            public void onNegative() {
                                showToast("onNegative");
                            }

                        }).show();
            }
        });
        findViewById(R.id.horizontalDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showAlert(DialogActivity.this,
                        "标题",
                        "内容",
                        "aaa",
                        "bbb",
                        "确定",
                        "取消",
                        false, new DialogUIListener() {
                            @Override
                            public void onPositive() {
                                showToast("onPositive");
                            }

                            @Override
                            public void onNegative() {
                                showToast("onNegative");
                            }

                        }).show();
            }
        });
        findViewById(R.id.verticalDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showAlert(DialogActivity.this,
                        "标题",
                        "内容",
                        "aaa",
                        "bbb",
                        "确定",
                        "取消",
                        true,
                        new DialogUIListener() {
                            @Override
                            public void onPositive() {
                                showToast("onPositive");
                            }

                            @Override
                            public void onNegative() {
                                showToast("onNegative");
                            }

                        }).show();
            }
        });
        findViewById(R.id.inputDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showAlert(DialogActivity.this,
                        "登录",
                        "",
                        "请输入用户名",
                        "请输入密码",
                        "登录",
                        "取消",
                        false, new DialogUIListener() {
                            @Override
                            public void onPositive() {

                            }

                            @Override
                            public void onNegative() {

                            }

                            @Override
                            public void onGetInput(CharSequence input1, CharSequence input2) {
                                super.onGetInput(input1, input2);
                                showToast("input1:" + input1 + "--input2:" + input2);
                            }
                        }).show();
            }
        });
    }
}
