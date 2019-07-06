package com.example.mapdemo;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Neo on 2018/4/22.
 */

public class NamedLocation {

    public final String name;

    public final LatLng location;

    NamedLocation(String name, LatLng location) {
        this.name = name;
        this.location = location;
    }
}
