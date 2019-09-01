package com.example.ms_app.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;

import com.example.ms_app.R;
import com.example.ms_app.home.Home;
import com.example.ms_app.task.CommonTask;
import com.google.gson.JsonObject;


public class MainActivity extends AppCompatActivity {
    public static final int STARTUP_DELAY = 200;
    public static final int ANIM_ITEM_DURATION = 1500;
    public static final int EDITTEXT_DELAY = 300;
    public static final int BUTTON_DELAY = 500;
    public static final int VIEW_DELAY = 400;
    public static final String TAG = "MainActivity";
    public CommonTask isMemberTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView logoImageView = findViewById(R.id.meisin_Logo);
        ViewGroup container = findViewById(R.id.container);
        setResult(RESULT_CANCELED);

        ViewCompat.animate(logoImageView)
                .translationY(-330)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION)
                .setInterpolator(new DecelerateInterpolator(1.2f))
                .start();

        for (int i = 0; i < container.getChildCount(); i++) {
            View view = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator;

            if (view instanceof EditText) {
                viewAnimator = ViewCompat.animate(view)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((EDITTEXT_DELAY * i) + 500)
                        .setDuration(500);
            } else if (view instanceof Button) {
                viewAnimator = ViewCompat.animate(view)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((BUTTON_DELAY * i) + 500)
                        .setDuration(500);
            } else {
                viewAnimator = ViewCompat.animate(view)
                        .translationY(100).alpha(1)
                        .setStartDelay((VIEW_DELAY * i) + 500)
                        .setDuration(1000);
            }

            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
        }

    }
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences(Util.PREF_FILE,
                MODE_PRIVATE);
        boolean login = preferences.getBoolean("login", false);
        if (login) {
            String st_num = preferences.getString("st_num", "");
            String st_password = preferences.getString("st_password", "");
            if (isMember(st_num,st_password)) {
                setResult(RESULT_OK);

            }
        }
    }
    private boolean isMember(final String st_num, final String st_password) {
        boolean isMember = false;
        if (Util.networkConnected(this)) {
            String url = Util.URL + "MemberServlet";
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action", "isMember");
            jsonObject.addProperty("st_num", st_num);
            jsonObject.addProperty("st_password", st_password);
            String jsonOut = jsonObject.toString();
            isMemberTask = new CommonTask(url, jsonOut);
            Log.e(TAG,"將字串包成json送出");
            try {
                String result = isMemberTask.execute().get();
                isMember = Boolean.valueOf(result);
                Log.e(TAG,"進入try");
            } catch (Exception e) {
                Log.e(TAG, e.toString());
                isMember = false;
                Log.e(TAG,"進入Exception");
            }
        } else {
            Util.showToast(this, R.string.msg_NoNetwork);
            Log.e(TAG,"沒有連線");
        }
        return isMember;
    }


    public void onLogin(View view) {
        EditText etname = findViewById(R.id.etname);
        EditText etpsd = findViewById(R.id.etpsd);
        String st_num = etname.getText().toString().trim();
        String st_password = etpsd.getText().toString().trim();
        if(st_num.length() <= 0 || st_password.length() <= 0){
            Toast.makeText(this,"請輸入帳號和密碼",Toast.LENGTH_LONG).show();
            return;
        }
        if (isMember(st_num, st_password)) {
            SharedPreferences preferences = getSharedPreferences(
                    Util.PREF_FILE, MODE_PRIVATE);
            preferences.edit().putBoolean("login", true)
                    .putString("st_num", st_num)
                    .putString("st_password", st_password).apply();
            setResult(RESULT_OK);
            Intent intent = new Intent(this, Home.class);
            Bundle bundle = new Bundle();
            bundle.putString("st_num",st_num);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this,"帳號或密碼錯誤，請重新輸入",Toast.LENGTH_LONG).show();

        }


    }

    protected void onStop() {
        super.onStop();
        if (isMemberTask != null) {
            isMemberTask.cancel(true);
        }
    }
}
