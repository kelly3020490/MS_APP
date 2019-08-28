package com.example.ms_app.schoolrecord;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ms_app.R;

import java.util.ArrayList;
import java.util.List;

public class schoolRecord extends AppCompatActivity {
    private CalendarView calendarview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_record);
        Toolbar toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        calendarview = findViewById(R.id.recordcalendar);
        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(schoolRecord.this, "你選擇的時間是：：" + year + "年" + (month + 1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final List<Record> recordList = new ArrayList<>();
        recordList.add(new Record(823, "到校時間", "08:00", "離校時間", "16:00"));


        recyclerView.setAdapter(new RecordAdapter(recordList));


    }

    private class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
        private List<Record> recordList;

        private RecordAdapter(List<Record> recordList) {
            this.recordList = recordList;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView date;
            private TextView arrivals;
            private TextView arr_time;
            private TextView leave;
            private TextView lea_time;


            private ViewHolder(View view) {
                super(view);
                date = view.findViewById(R.id.date);
                arrivals = view.findViewById(R.id.arrivals);
                arr_time = view.findViewById(R.id.arr_time);
                leave = view.findViewById(R.id.leave);
                lea_time = view.findViewById(R.id.lea_time);
            }
        }
        @Override
        public int getItemCount() {
            return recordList.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_school_record, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //將資料注入到View裡
            final Record record = recordList.get(position);
            holder.date.setText(record.getDate().toString());
            holder.arrivals.setText(record.getArrivals());
            holder.arr_time.setText(record.getArr_time());
            holder.leave.setText(record.getLeave());
            holder.lea_time.setText(record.getLea_time());

        }
    }

}
