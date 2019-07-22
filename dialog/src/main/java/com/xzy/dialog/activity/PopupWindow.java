package com.xzy.dialog.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import com.widget.jcdialog.bean.PopuBean;
import com.widget.jcdialog.listener.TdataListener;
import com.widget.jcdialog.widget.PopuWindowView;
import com.xzy.dialog.R;

import java.util.List;

import static com.widget.jcdialog.utils.ToastUitl.showToast;

/**
 * popupWindow 的用法。
 * @author xzy
 */
public class PopupWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        Button mButton = findViewById(R.id.popupwindow_2);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopupWindow(view);
            }
        });
    }

    private void initPopupWindow(View view){
        // 初始化 PopuWindowView
        final PopuWindowView popuWindowView = new PopuWindowView(this,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 绑定数据和点击事件
        popuWindowView.initPupoData(new TdataListener() {
            @Override
            public void initPupoData(List<PopuBean> lists) {
                for (int i = 0; i < 5; i++) {
                    PopuBean popu = new PopuBean();
                    popu.setTitle("item"+i);
                    popu.setId(i);
                    lists.add(popu);
                }
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position) {
                showToast(popuWindowView.getTitle(position));
                popuWindowView.dismiss();
            }
        });
        // 展示 PopuWindowView
        popuWindowView.showing(view);
    }
}
