package com.example.ms_app.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ms_app.QRcode.QRcode;
import com.example.ms_app.R;
import com.example.ms_app.leave.Leave;
import com.example.ms_app.main.Member;
import com.example.ms_app.main.Util;
import com.example.ms_app.medicine.Medicine;
import com.example.ms_app.menu.Menu;
import com.example.ms_app.schedule.Schedule;
import com.example.ms_app.schoolbook.SchoolBook;
import com.example.ms_app.schoolbus.SchoolBus;
import com.example.ms_app.schoolrecord.schoolRecord;
import com.example.ms_app.task.CommonTask;
import com.example.ms_app.task.ImageTask;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Calendar;

public class Fragment_home extends Fragment {
    public static final String TAG = "Fragment_home";

    private static int year, month, day;
    private static TextView tvDateTime,st_name,st_number;
    private ImageView st_pic;
    private ImageView calendarView;
    private ImageView scheduleView;
    private ImageView schoolBookView;
    private ImageView medicineView;
    private ImageView leaveView;
    private ImageView qrcodeView;
    private ImageView schoolcarView;
    private ImageView menuView;
    private CommonTask showStInfoTask;
    private ImageTask studentImageTask;
    String st_num;
    Member member;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_home, container, false);


        tvDateTime = view.findViewById(R.id.tvDateTime);
        st_name = view.findViewById(R.id.st_name);
        st_number = view.findViewById(R.id.st_number);
        st_pic = view.findViewById(R.id.st_pic);
        calendarView = view.findViewById(R.id.calendarView);
        schoolBookView = view.findViewById(R.id.schoolBookView);
        medicineView = view.findViewById(R.id.medicineView);
        leaveView = view.findViewById(R.id.leaveView);
        qrcodeView = view.findViewById(R.id.qrcodeView);
        schoolcarView = view.findViewById(R.id.schoolcarView);
        menuView = view.findViewById(R.id.menuView);
        scheduleView = view.findViewById(R.id.scheduleView);

        //取得登入頁面傳送過來的學生編號
        st_num = getActivity().getIntent().getExtras().getString("st_num");
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        //顯示時間
        showRightNow();

        //顯示學生姓名，職稱，照片
        showStInfo(st_num);


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


    Member showStInfo(String st_num){
        int imageSize = getResources().getDisplayMetrics().widthPixels * 2;

        if (Util.networkConnected(getActivity())) {
            String url = Util.URL + "MemberServlet";
            //new一個JsonObject物件
            JsonObject jsonObject = new JsonObject();
            //將屬性和值放入jsonObject物件內
            jsonObject.addProperty("action", "findById");
            jsonObject.addProperty("st_num", st_num);
            //將jsonObject物件轉成字串
            String jsonOut = jsonObject.toString();
            showStInfoTask = new CommonTask(url, jsonOut);
            Log.e(TAG, "將字串包成json送出");


            try {
                String result = showStInfoTask.execute().get();
//
                Log.e(TAG, "result" + result);

                Gson gson = new Gson();
                JsonObject jsonObject2 = gson.fromJson(result, JsonObject.class);
                member = gson.fromJson(jsonObject2.get("member").getAsString(), Member.class);

                st_name.setText(member.getSt_name());
                Log.e(TAG, "成功傳入老師姓名:" + member.getSt_num());

                st_number.setText("學號：" + member.getSt_num());
                Log.e(TAG, "成功傳入老師職稱:" + member.getSt_num());

                studentImageTask = new ImageTask(url, st_num, imageSize, st_pic);
                studentImageTask.execute();
                Log.e(TAG, "成功傳入照片");

            } catch (Exception e) {
                Log.e(TAG, e.toString());
//                showTrInfo = false;
                Log.e(TAG, "進入Exception");
            }
        } else {
            Util.showToast(getActivity(), R.string.msg_NoNetwork);
            Log.e(TAG, "沒有連線");
        }
        return member;
    }


}
