package com.example.ms_app.schoolcalendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ms_app.R;

import java.util.ArrayList;
import java.util.List;

public class School_Calendar extends Fragment {
    private Toolbar toolbar;
    private CalendarView calendarview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_school__calendar, container, false);
//       在畫面上顯示自訂的toolbar
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("行事曆");

        calendarview = view.findViewById(R.id.schoolCalendar);
        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getActivity(), "你選擇的時間是：：" + year + "年" + (month+1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
            }
        });


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        final List<School_calendarVO> school_calendarList = new ArrayList<>();
        school_calendarList.add(new School_calendarVO(2019, "防災演練活動", "11:00","12:00"));

        recyclerView.setAdapter(new School_calendarAdapter( school_calendarList));

        return view;
    }

    private class School_calendarAdapter extends RecyclerView.Adapter<School_calendarAdapter.ViewHolder> {
        private List<School_calendarVO> school_calendarList;

        private School_calendarAdapter(List<School_calendarVO> school_calendarList) {
            this.school_calendarList = school_calendarList;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView date;
            private TextView activity;
            private TextView startTime;
            private TextView endTime;


            private ViewHolder(View view) {
                super(view);
                date = view.findViewById(R.id.date);
                activity = view.findViewById(R.id.activity);
                startTime = view.findViewById(R.id.startTime);
                endTime = view.findViewById(R.id.endTime);
            }
        }
        @Override
        public int getItemCount() {
            return school_calendarList.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_school_calendar, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //將資料注入到View裡
            final School_calendarVO school_calendar = school_calendarList.get(position);
            holder.date.setText(school_calendar.getDate().toString());
            holder. activity.setText(school_calendar.getActivity());
            holder.startTime.setText(school_calendar.getStartTime());
            holder.endTime.setText(school_calendar.getEndTime());


        }
    }

}
