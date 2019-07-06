package com.example.mapdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * User: LJM
 * Date&Time: 2017-07-19 & 20:31
 * Describe: Describe Text
 */
public class LvMapAdapter extends BaseAdapter {

    private ArrayList<NamedLocation> mDatas;
    private Context mContext;


    public LvMapAdapter(Context mContext,ArrayList<NamedLocation> mDatas){
        this.mDatas = mDatas;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {


        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder= new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_map, null);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.lite_listrow_text);
            viewHolder.mapView = (MapView) convertView.findViewById(R.id.lite_listrow_map);

            // Initialise the MapView
            viewHolder.initializeMapView(); // 第一次加载时先初始化地图
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        // Get the NamedLocation for this item and attach it to the MapView
        NamedLocation item = mDatas.get(position);
        viewHolder.mapView.setTag(item);  // 给view在单独打一个tag

        // Ensure the map has been initialised by the on map ready callback in ViewHolder.
        // If it is not ready yet, it will be initialised with the NamedLocation set as its tag
        // when the callback is received.
        // 这里是复用时的确保地址整错，防止错位
        if (viewHolder.map != null) {
            // The map is already ready to be used
            setMapLocation(viewHolder.map, item);
        }
        // Set the text label for this item
        viewHolder.tv_title.setText(item.name);

        return convertView;
    }

    class ViewHolder implements OnMapReadyCallback {
        TextView tv_title;
        MapView mapView;

        /**
         * ViewHolder多增了一个变量，取自onMapReady的参数，作用是复用的时候有一个已经准备就绪的GoogleMap对象
         */
        GoogleMap map;

        public void initializeMapView() {
            if (mapView != null) {
                mapView.onCreate(null); // MapView对象使用之前调用onCreate必不可少，作用是初始化 MapView
                mapView.getMapAsync(this);
            }
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsInitializer.initialize(mContext);
            map = googleMap;
            NamedLocation data = (NamedLocation) mapView.getTag();
            if (data != null) {
                setMapLocation(map, data);
            }
        }
    }

    private static void setMapLocation(GoogleMap map, NamedLocation data) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(data.location, 13f));
        map.addMarker(new MarkerOptions().position(data.location));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    /**
     * RecycleListener that completely clears the {@link com.google.android.gms.maps.GoogleMap}
     * attached to a row in the ListView.
     * Sets the map type to {@link com.google.android.gms.maps.GoogleMap#MAP_TYPE_NONE} and clears
     * the map.
     */
    public AbsListView.RecyclerListener mRecycleListener = new AbsListView.RecyclerListener() {

        @Override
        public void onMovedToScrapHeap(View view) {
            ViewHolder holder = (ViewHolder) view.getTag();
            if (holder != null && holder.map != null) {
                // Clear the map and free up resources by changing the map type to none
                holder.map.clear();
                holder.map.setMapType(GoogleMap.MAP_TYPE_NONE);
            }
        }
    };
}
