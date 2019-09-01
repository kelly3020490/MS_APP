package com.example.ms_app.schoolbus;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ms_app.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashSet;
import java.util.Set;

public class SchoolBus extends AppCompatActivity implements OnMapReadyCallback {
    private static final int MY_REQUEST_CODE = 1;

    // 存地圖資訊
    private GoogleMap map;
    //美心幼兒園標記
    private Marker marker_meiSin;
    //美心幼兒園經緯度
    private LatLng meiSin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_bus);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initPoints();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fmMap);
        mapFragment.getMapAsync(this);
    }



    @Override
    protected void onStart() {
        super.onStart();
        askPermissions();
    }


    private void askPermissions() {
        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        };

        Set<String> permissionsRequest = new HashSet<>();
        for (String permission : permissions) {
            int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissionsRequest.add(permission);
            }
        }

        if (!permissionsRequest.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    permissionsRequest.toArray(new String[permissionsRequest.size()]),
                    MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_REQUEST_CODE:
                String text = "";
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        text += permissions[i] + "\n";
                    }
                }
                if (!text.isEmpty()) {
                    text += getString(R.string.text_NotGranted);
                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    // 初始化所有地點的緯經度
    private void initPoints() {
        meiSin = new LatLng(24.967759, 121.191695);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        setupMap();
    }

    @SuppressLint("MissingPermission")
    private void setupMap() {
//        map.setMyLocationEnabled(true);


        //移動地圖
        //鏡頭的位置 target(鏡頭的位置)
        CameraPosition cameraPosition = new CameraPosition.Builder()
                // 鏡頭焦點在中大（以中大為中心點）
                .target(meiSin)
                // 地圖縮放層級定為7
                .zoom(15)
                .build();

        // 改變鏡頭焦點到指定的新地點
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        //開始做鏡頭的動畫移動
        map.animateCamera(cameraUpdate);



        addMarkersToMap();
        // 如果不套用自訂InfoWindowAdapter會自動套用預設訊息視窗
        map.setInfoWindowAdapter(new MyInfoWindowAdapter());

        MyMarkerListener listener = new MyMarkerListener();

        // 註冊OnMarkerClickListener，當標記被點擊時會自動呼叫該Listener的方法
        map.setOnMarkerClickListener(listener);
        // 註冊OnInfoWindowClickListener，當標記訊息視窗被點擊時會自動呼叫該Listener的方法
        map.setOnInfoWindowClickListener(listener);

    }


    // 在地圖上加入多個標記
    private void addMarkersToMap() {
        marker_meiSin = map.addMarker(new MarkerOptions()
                // 設定標記位置
                .position(meiSin)  //必要
                // 設定標記標題
                .title(getString(R.string.marker_title_meiSin))
                // 設定標記描述
                .snippet(getString(R.string.marker_snippet_meiSin)));



    }


    // 自訂InfoWindowAdapter，當點擊標記時會跳出自訂風格的訊息視窗
    private class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View infoWindow;

        MyInfoWindowAdapter() {
            infoWindow = LayoutInflater.from(SchoolBus.this)
                    .inflate(R.layout.custom_infowindow, null);
        }

        @Override
        // 回傳設計好的訊息視窗樣式
        // 回傳null會自動呼叫getInfoContents(Marker)
        public View getInfoWindow(Marker marker) {
            int logoId;
            // 使用equals()方法檢查2個標記是否相同，千萬別用「==」檢查
            if (marker.equals(marker_meiSin)) {
                logoId = R.drawable.meisin2;

            } else {
                // 呼叫setImageResource(int)傳遞0則不會顯示任何圖形
                logoId = 0;
            }

            // 顯示圖示
            ImageView ivLogo = infoWindow.findViewById(R.id.ivLogo);
            ivLogo.setImageResource(logoId);

            // 顯示標題
            String title = marker.getTitle();
            TextView tvTitle = infoWindow.findViewById(R.id.tvTitle);
            tvTitle.setText(title);

            // 顯示描述
            String snippet = marker.getSnippet();
            TextView tvSnippet = infoWindow.findViewById(R.id.tvSnippet);
            tvSnippet.setText(snippet);

            return infoWindow;
        }

        @Override
        // 當getInfoWindow(Marker)回傳null時才會呼叫此方法
        // 此方法如果再回傳null，代表套用預設視窗樣式
        public View getInfoContents(Marker marker) {
            return null;
        }
    }

    private class MyMarkerListener implements GoogleMap.OnMarkerClickListener,
            GoogleMap.OnInfoWindowClickListener {

        @Override
        // 點擊地圖上的標記
        public boolean onMarkerClick(Marker marker) {
            Toast.makeText(SchoolBus.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        // 點擊標記的訊息視窗
        public void onInfoWindowClick(Marker marker) {
            Toast.makeText(SchoolBus.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
        }

//        @Override
        // 開始拖曳標記
//        public void onMarkerDragStart(Marker marker) {
//            tvMarkerDrag.setText("onMarkerDragStart");
//        }

//        @Override
        // 拖曳標記過程中會不斷呼叫此方法
//        public void onMarkerDrag(Marker marker) {
//            // 以TextView顯示標記的緯經度
//            tvMarkerDrag.setText("onMarkerDrag.  Current Position: " + marker.getPosition());
//        }

//        @Override
//        // 結束拖曳標記
//        public void onMarkerDragEnd(Marker marker) {
//            tvMarkerDrag.setText("onMarkerDragEnd");
//        }
    }
}
