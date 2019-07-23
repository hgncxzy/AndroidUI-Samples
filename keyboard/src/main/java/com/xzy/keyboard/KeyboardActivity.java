package com.xzy.keyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.widget.jcdialog.DialogUtils;
import com.widget.jcdialog.widget.pswKeyBoard.NormalKeyBoardActivity;
import com.widget.jcdialog.widget.pswKeyBoard.OnPasswordInputFinish;
import com.widget.jcdialog.widget.pswKeyBoard.widget.PopEnterPassword;

/**
 * 键盘的用法
 *
 * @author xzy
 */
public class KeyboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handle();
    }

    private void handle() {
        findViewById(R.id.normalKeyboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KeyboardActivity.this,
                        NormalKeyBoardActivity.class));
            }
        });
        findViewById(R.id.payKeyboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopEnterPassword popEnterPassword = DialogUtils
                        .showPayKeyBoard(KeyboardActivity.this, view);
                popEnterPassword.getPwdView().setOnFinishInput(new OnPasswordInputFinish() {
                    @Override
                    public void inputFinish(String password) {
                        popEnterPassword.dismiss();
                        Toast.makeText(KeyboardActivity.this,
                                "支付成功，密码为：" + password, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.passwordKeyboard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopEnterPassword popEnterPassword2 = new
                        PopEnterPassword(KeyboardActivity.this);
                // 显示窗口
                // 设置layout在PopupWindow中显示的位置
                popEnterPassword2.showAtLocation(view,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }
}
