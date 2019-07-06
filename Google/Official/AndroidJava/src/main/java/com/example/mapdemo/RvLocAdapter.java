package com.example.mapdemo;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Neo on 2018/4/22.
 */

public class RvLocAdapter extends RecyclerView.Adapter<RvLocAdapter.ViewHolder> {
    public ArrayList<NamedLocation> mDatas = null;
    public static Context mContext;

    // 点击回调
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);  // 点击
        void onItemLongClick(View view, int position); // 长按
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public RvLocAdapter(Context context, ArrayList<NamedLocation> datas) {
        this.mDatas = datas;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lv_map,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final NamedLocation namedLocation = mDatas.get(position);

        viewHolder.tv_location.setText(namedLocation.name);

        NamedLocation item = mDatas.get(position);
        viewHolder.mapView.setTag(item);

        if (viewHolder.map != null) {
            // The map is already ready to be used
            setMapLocation(viewHolder.map, item);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
        private TextView tv_location;
        private MapView mapView;
        GoogleMap map;

        public ViewHolder(View view){
            super(view);
            tv_location = (TextView) view.findViewById(R.id.lite_listrow_text);
            mapView = (MapView) view.findViewById(R.id.lite_listrow_map);
            initializeMapView();
        }

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
}
