package com.example.mapdemo;

import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Neo on 2018/4/22.
 */

public class RecyclerViewMapActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private ArrayList<NamedLocation> mDatas;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_map);

        mRv = (RecyclerView) findViewById(R.id.mRv);
        mRv.setLayoutManager(new LinearLayoutManager(this));

        mDatas = new ArrayList<>();
        fillData();
        mRv.setAdapter(new RvLocAdapter(RecyclerViewMapActivity.this, mDatas));
    }

    private void fillData() {
        mDatas.add(new NamedLocation("Cape Town", new LatLng(-33.920455, 18.466941)));
        mDatas.add(new NamedLocation("Beijing", new LatLng(39.937795, 116.387224)));
        mDatas.add(new NamedLocation("Bern", new LatLng(46.948020, 7.448206)));
        mDatas.add(new NamedLocation("Breda", new LatLng(51.589256, 4.774396)));
        mDatas.add(new NamedLocation("Brussels", new LatLng(50.854509, 4.376678)));
        mDatas.add(new NamedLocation("Copenhagen", new LatLng(55.679423, 12.577114)));
        mDatas.add(new NamedLocation("Hannover", new LatLng(52.372026, 9.735672)));
        mDatas.add(new NamedLocation("Helsinki", new LatLng(60.169653, 24.939480)));
        mDatas.add(new NamedLocation("Hong Kong", new LatLng(22.325862, 114.165532)));
        mDatas.add(new NamedLocation("Istanbul", new LatLng(41.034435, 28.977556)));
        mDatas.add(new NamedLocation("Johannesburg", new LatLng(-26.202886, 28.039753)));
        mDatas.add(new NamedLocation("Lisbon", new LatLng(38.707163, -9.135517)));
        mDatas.add(new NamedLocation("London", new LatLng(51.500208, -0.126729)));
        mDatas.add(new NamedLocation("Madrid", new LatLng(40.420006, -3.709924)));
        mDatas.add(new NamedLocation("Mexico City", new LatLng(19.427050, -99.127571)));
        mDatas.add(new NamedLocation("Moscow", new LatLng(55.750449, 37.621136)));
        mDatas.add(new NamedLocation("New York", new LatLng(40.750580, -73.993584)));
        mDatas.add(new NamedLocation("Oslo", new LatLng(59.910761, 10.749092)));
        mDatas.add(new NamedLocation("Paris", new LatLng(48.859972, 2.340260)));
        mDatas.add(new NamedLocation("Prague", new LatLng(50.087811, 14.420460)));
        mDatas.add(new NamedLocation("Rio de Janeiro", new LatLng(-22.90187, -43.232437)));
        mDatas.add(new NamedLocation("Rome", new LatLng(41.889998, 12.500162)));
        mDatas.add(new NamedLocation("Sao Paolo", new LatLng(-22.863878, -43.244097)));
        mDatas.add(new NamedLocation("Seoul", new LatLng(37.560908, 126.987705)));
        mDatas.add(new NamedLocation("Stockholm", new LatLng(59.330650, 18.067360)));
        mDatas.add(new NamedLocation("Sydney", new LatLng(-33.873651, 151.2068896)));
        mDatas.add(new NamedLocation("Taipei", new LatLng(25.022112, 121.478019)));
        mDatas.add(new NamedLocation("Tokyo", new LatLng(35.670267, 139.769955)));
        mDatas.add(new NamedLocation("Tulsa Oklahoma", new LatLng(36.149777, -95.993398)));
        mDatas.add(new NamedLocation("Vaduz", new LatLng(47.141076, 9.521482)));
        mDatas.add(new NamedLocation("Vienna", new LatLng(48.209206, 16.372778)));
        mDatas.add(new NamedLocation("Warsaw", new LatLng(52.235474, 21.004057)));
        mDatas.add(new NamedLocation("Wellington", new LatLng(-41.286480, 174.776217)));
        mDatas.add(new NamedLocation("Winnipeg", new LatLng(49.875832, -97.150726)));
    }
}
