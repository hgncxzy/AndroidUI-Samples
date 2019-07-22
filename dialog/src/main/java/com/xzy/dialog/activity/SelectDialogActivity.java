package com.xzy.dialog.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.widget.jcdialog.DialogUtils;
import com.widget.jcdialog.adapter.TieAdapter;
import com.widget.jcdialog.bean.BuildBean;
import com.widget.jcdialog.bean.TieBean;
import com.widget.jcdialog.listener.DialogUIItemListener;
import com.widget.jcdialog.listener.DialogUIListener;
import com.xzy.dialog.R;

import java.util.ArrayList;
import java.util.List;

import static com.widget.jcdialog.utils.ToastUitl.showToast;

/**
 * 选择框使用 demo。
 *
 * @author xzy
 */
public class SelectDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dialog);
        handle();
    }

    private void handle() {
        findViewById(R.id.mdStyleSelectDialog)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String[] words = new String[]{"1", "2", "3"};
                        boolean[] choseDefault = new boolean[]{false, false, false};
                        DialogUtils.showMdMultiChoose(SelectDialogActivity.this,
                                "标题",
                                words,
                                choseDefault,
                                new DialogUIListener() {
                                    @Override
                                    public void onPositive() {

                                    }

                                    @Override
                                    public void onNegative() {

                                    }
                                }).show();
                    }
                });
        findViewById(R.id.mdStyleVerticalBottomSelectDialog)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<TieBean> datas3 = new ArrayList<>();
                        datas3.add(new TieBean("1"));
                        datas3.add(new TieBean("2"));
                        datas3.add(new TieBean("3"));
                        datas3.add(new TieBean("4"));
                        datas3.add(new TieBean("5"));
                        datas3.add(new TieBean("6"));
                        DialogUtils.showMdBottomSheet(SelectDialogActivity.this,
                                true,
                                "标题",
                                datas3,
                                "",
                                0,
                                new DialogUIItemListener() {
                                    @Override
                                    public void onItemClick(CharSequence text, int position) {
                                        showToast(text + "---" + position);
                                    }
                                }).show();
                    }
                });
        findViewById(R.id.mdStyleHorizontalBottomSelectDialog)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<TieBean> datas2 = new ArrayList<>();
                        datas2.add(new TieBean("1"));
                        datas2.add(new TieBean("2"));
                        datas2.add(new TieBean("3"));
                        datas2.add(new TieBean("4"));
                        datas2.add(new TieBean("5"));
                        datas2.add(new TieBean("6"));
                        TieAdapter adapter = new TieAdapter(SelectDialogActivity.this,
                                datas2);
                        BuildBean buildBean = DialogUtils.showMdBottomSheet(
                                SelectDialogActivity.this,
                                false,
                                "",
                                datas2,
                                "",
                                4,
                                new DialogUIItemListener() {
                                    @Override
                                    public void onItemClick(CharSequence text, int position) {
                                        showToast(text + "---" + position);
                                    }
                                });
                        buildBean.mAdapter = adapter;
                        buildBean.show();
                    }
                });
    }
}
