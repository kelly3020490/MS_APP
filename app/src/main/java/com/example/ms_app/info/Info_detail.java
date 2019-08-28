package com.example.ms_app.info;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ms_app.R;
import com.example.ms_app.main.Util;

public class Info_detail extends AppCompatActivity {
    private final static String TAG = "InfoDetail";
    java.text.SimpleDateFormat date = new java.text.SimpleDateFormat("yyyy/MM/dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);
        InfoVO anno = (InfoVO) this.getIntent().getSerializableExtra("info");
        if (anno == null) {
            Util.showToast(this, "找不到該則園所資訊");
        } else {
            showDetail(anno);
        }
    }

    public void showDetail(InfoVO anno) {
        TextView tvTitle, tvText;
        tvTitle = findViewById(R.id.tvTitle);
        tvText = findViewById(R.id.tvText);

        String title = anno.getAnno_title();
        tvTitle.setText(title);

        String text = "類別：" + anno.getAnno_cla() + "\n"
                + "發布時間：" + date.format(anno.getAnno_date()) + "\n"
                + "內容：" + "\n"
                + anno.getAnno_text();
        tvText.setText(text);


    }

}
