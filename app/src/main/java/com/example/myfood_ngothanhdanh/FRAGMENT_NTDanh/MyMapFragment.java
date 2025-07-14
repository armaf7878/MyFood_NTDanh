package com.example.myfood_ngothanhdanh.FRAGMENT_NTDanh;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfood_ngothanhdanh.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMapFragment extends Fragment implements OnMapReadyCallback {
    GoogleMap mMap;
    double cus_Lat = 10.800036036774685;
    double cus_Long = 106.65441624938295;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_map, container, false);
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.map_container, mapFragment).commit();
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        Bundle bundle = getArguments();
        if (bundle != null){
            cus_Lat = bundle.getDouble("cus_Lat");
            cus_Long = bundle.getDouble("cus_Long");
        }

        // Ví dụ: Đặt marker tại vị trí hiện tại
        LatLng user_location = new LatLng(cus_Lat, cus_Long);
        mMap.addMarker(new MarkerOptions().position(user_location).title("User Here").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user_location, 20));

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(@NonNull Marker marker) {
                LatLng newPosition = marker.getPosition();
                double newLat = newPosition.latitude;
                double newLong = newPosition.longitude;
                Bundle result = new Bundle();
                result.putDouble("newLat", newLat);
                result.putDouble("newLong", newLong);
                getParentFragmentManager().setFragmentResult("location_result", result);
            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {

            }
        });

        // Bật hiển thị vị trí người dùng (nếu có permission)
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
    }
}