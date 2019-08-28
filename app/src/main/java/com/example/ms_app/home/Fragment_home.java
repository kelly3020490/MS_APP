package com.example.ms_app.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ms_app.leave.Leave;
import com.example.ms_app.medicine.Medicine;
import com.example.ms_app.menu.Menu;
import com.example.ms_app.QRcode.QRcode;
import com.example.ms_app.R;
import com.example.ms_app.schedule.Schedule;
import com.example.ms_app.schoolbook.SchoolBook;
import com.example.ms_app.schoolbus.SchoolBus;
import com.example.ms_app.schoolrecord.schoolRecord;

import java.util.Calendar;

public class Fragment_home extends Fragment {

    private static int year, month, day;
    private static TextView tvDateTime;
    private ImageView calendarView;
    private ImageView scheduleView;
    private ImageView schoolBookView;
    private ImageView medicineView;
    private ImageView leaveView;
    private ImageView qrcodeView;
    private ImageView schoolcarView;
    private ImageView menuView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_home, container, false);


        tvDateTime = view.findViewById(R.id.tvDateTime);
        calendarView = view.findViewById(R.id.calendarView);
        schoolBookView = view.findViewById(R.id.schoolBookView);
        medicineView = view.findViewById(R.id.medicineView);
        leaveView = view.findViewById(R.id.leaveView);
        qrcodeView = view.findViewById(R.id.qrcodeView);
        schoolcarView = view.findViewById(R.id.schoolcarView);
        menuView = view.findViewById(R.id.menuView);
        scheduleView = view.findViewById(R.id.scheduleView);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        //顯示時間
        showRightNow();
        //設定圖片按鈕的點擊事件
        //到離校紀錄
        calendarView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), schoolRecord.class);
                startActivity(intent);
            }
        });
        //親子聯絡簿
        schoolBookView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SchoolBook.class);
                startActivity(intent);
            }
        });
        //託藥
        medicineView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Medicine.class);
                startActivity(intent);
            }
        });
        //請假
        leaveView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Leave.class);
                startActivity(intent);
            }
        });
        //QRcode掃描
        qrcodeView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QRcode.class);
                startActivity(intent);
            }
        });
        //交通車即時位置
        schoolcarView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SchoolBus.class);
                startActivity(intent);
            }
        });
        //美味菜單
        menuView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Menu.class);
                startActivity(intent);
            }
        });
        //作息表
        scheduleView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Schedule.class);
                startActivity(intent);
            }
        });


    }


    private static void showRightNow() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        updateInfo();
    }

    private static void updateInfo() {
        tvDateTime.setText(new StringBuilder().append(year)
                .append("-").append(parseNum(month + 1)).append("-").append(parseNum(day)));
    }

    private static String parseNum(int day) {
        if (day >= 10) {
            return String.valueOf(day);
        } else {
            return "0" + String.valueOf(day);
        }
    }


}
