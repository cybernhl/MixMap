package com.example.mapdemo;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Neo on 2018/4/22.
 */

public class SimpleFragmentModeActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_mode);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        double lat = 0.0;
        double lng = 0.0;
        LatLng appointLoc = new LatLng(lat, lng);

        // 移动地图到指定经度的位置
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(appointLoc));

        //添加标记到指定经纬度

        GoogleMapOptions options = new GoogleMapOptions().liteMode(true);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Marker")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.chat_loc_point)));

        googleMap.getUiSettings().setMapToolbarEnabled(false); //禁用精简模式下右下角的工具栏

        // 设置缩放级别
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(3);
        googleMap.animateCamera(zoom);

        /*// 点击标记点，默认点击弹出跳转google地图或导航选择，返回true则不弹出
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return true;
            }
        });

        // 单击
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

            }
        });

        // 不允许手势缩放
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        //googleMap.getUiSettings().setMapToolbarEnabled(false); 禁用精简模式下右下角的工具栏

        // 不允许拖动地图
        googleMap.getUiSettings().setScrollGesturesEnabled(false);

       */
    }
}

