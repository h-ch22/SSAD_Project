package kr.ac.jbnu.se.ssad_group6.mainPage;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.util.FusedLocationSource;

import kr.ac.jbnu.se.ssad_group6.R;

public class activity_main extends Activity implements OnMapReadyCallback {
    private MapView mapView;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;

    protected void onStart(){
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop(){
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)){
            if (!locationSource.isActivated()){
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }

            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
