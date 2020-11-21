package kr.ac.jbnu.se.ssad_group6.mainPage;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import kr.ac.jbnu.se.ssad_group6.R;

public class activity_main extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(35.84693240272961, 127.12932428270338), 9,true);
        mapView.zoomIn(true);
        mapView.zoomOut(true);
    }
}
