package com.example.ms_app.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ms_app.R;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {
    CalendarView calendarview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calendarview = findViewById(R.id.menucalendar);
        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(Menu.this, "你選擇的時間是：：" + year + "年" + (month+1) + "月" + dayOfMonth + "日", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final List<MenuVO> menuList = new ArrayList<>();
        menuList.add(new MenuVO("牛奶＋法國吐司", "豆漿＋炒麵", "布丁"));

        recyclerView.setAdapter(new MenuAdapter(menuList));
    }

    private class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
        private List<MenuVO> menuList;

        private MenuAdapter(List<MenuVO> menuList) {
            this.menuList = menuList;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView breakfastName;
            private TextView lunchName;
            private TextView snackName;


            private ViewHolder(View view) {
                super(view);
                breakfastName = view.findViewById(R.id.breakfastName);
                lunchName = view.findViewById(R.id.lunchName);
                snackName = view.findViewById(R.id.snackName);
            }
        }
        @Override
        public int getItemCount() {
            return menuList.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_menu, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //將資料注入到View裡
            final MenuVO menu = menuList.get(position);
            holder.breakfastName.setText(menu.getBreakfast());
            holder. lunchName.setText(menu.getLunch());
            holder.snackName.setText(menu.getSnack());


        }
    }
}
