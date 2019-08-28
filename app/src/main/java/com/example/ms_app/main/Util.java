package com.example.ms_app.main;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Util {
    // 模擬器連Tomcat
//    public static String URL = "http://10.0.2.2:8081/DA102G4_APP/";
    public static String URL = "http://192.168.0.3:8081/DA102G4_APP/";


    // 偏好設定檔案名稱
        public final static String PREF_FILE = "preference";

        public static boolean networkConnected(Activity activity) {
            ConnectivityManager conManager =
                    (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = conManager != null ? conManager.getActiveNetworkInfo() : null;
            return networkInfo != null && networkInfo.isConnected();
        }

        public static void showToast(Context context, int messageResId) {
            Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
