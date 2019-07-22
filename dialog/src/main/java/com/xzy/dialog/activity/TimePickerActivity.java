package com.xzy.dialog.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.widget.jcdialog.DialogUtils;
import com.widget.jcdialog.listener.DialogUIDateTimeSaveListener;
import com.widget.jcdialog.utils.ToastUitl;
import com.widget.jcdialog.widget.DateSelectorWheelView;
import com.widget.jcdialog.widget.pickerview.TimePickerView;
import com.xzy.dialog.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.widget.jcdialog.utils.ToastUitl.showToast;

/**
 * 时间选择器的用法。
 *
 * @author xzy
 */
public class TimePickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        handle();
    }

    private void handle() {
        findViewById(R.id.yymmdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showDatePick(TimePickerActivity.this,
                        Gravity.CENTER,
                        "选择日期",
                        System.currentTimeMillis() + 60000,
                        DateSelectorWheelView.TYPE_YYYYMMDD,
                        0,
                        new DialogUIDateTimeSaveListener() {
                            @Override
                            public void onSaveSelectedDate(int tag, String selectedDate) {
                                showToast(selectedDate);
                            }
                        }).show();
            }
        });
        findViewById(R.id.yymmddhhmm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showDatePick(TimePickerActivity.this,
                        Gravity.CENTER,
                        "选择日期",
                        System.currentTimeMillis() + 60000,
                        DateSelectorWheelView.TYPE_YYYYMMDDHHMM,
                        0,
                        new DialogUIDateTimeSaveListener() {
                            @Override
                            public void onSaveSelectedDate(int tag, String selectedDate) {
                                showToast(selectedDate);
                            }
                        }).show();
            }
        });
        findViewById(R.id.yymmddhhmmss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showDatePick(TimePickerActivity.this,
                        Gravity.BOTTOM,
                        "选择日期",
                        System.currentTimeMillis() + 60000,
                        DateSelectorWheelView.TYPE_YYYYMMDDHHMMSS,
                        0,
                        new DialogUIDateTimeSaveListener() {
                            @Override
                            public void onSaveSelectedDate(int tag, String selectedDate) {
                                showToast(selectedDate);
                            }
                        }).show();
            }
        });
        findViewById(R.id.dateyymmddhhmm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showTimePickView(TimePickerActivity.this,
                        "选择日期",
                        TimePickerView.Type.ALL,
                        new TimePickerView.OnTimeSelectListener() {
                            @Override
                            public void onTimeSelect(Date date) {
                                ToastUitl.showToast(getTime(date));
                            }
                        });
            }
        });
    }


    public static String getTime(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format =
                new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
    
}