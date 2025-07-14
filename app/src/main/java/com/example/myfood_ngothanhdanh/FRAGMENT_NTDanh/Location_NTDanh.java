package com.example.myfood_ngothanhdanh.FRAGMENT_NTDanh;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_sort_res_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.restaurant_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.restaurantwdistance;
import com.example.myfood_ngothanhdanh.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Location_NTDanh extends Fragment {
    private double cusLat = 0.0, cusLong= 0.0;
    private String cusAddress;
    private RecyclerView recycler_food_NTDanh;
    private TextView customer_location_NTDanh;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<restaurantwdistance> restaurantwdistanceList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location__n_t_danh, container, false);
        recycler_food_NTDanh = view.findViewById(R.id.recycler_food_NTDanh);
        customer_location_NTDanh = view.findViewById(R.id.customer_location_NTDanh);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCustomerLocation_NTDanh();
    }

    private void requestPermission_NTDanh(){
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
            }, 1001); // Fragment có sẵn requestPermissions()
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getContext(), "Đã cấp quyền vị trí", Toast.LENGTH_SHORT).show();
                getCustomerLocation_NTDanh();
            }else {
                Toast.makeText(getContext(), "Từ chối quyền vị trí", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCustomerLocation_NTDanh() {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission_NTDanh();
            return;
        }

        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        cusLat = location.getLatitude();
                        cusLong = location.getLongitude();
                        Log.d("Location", "Lat: " + cusLat + ", Long: " + cusLong);
                        resverseLocationFromGeocoding_NTDanh();
                        compareDistance_NTDanh();
                    } else {
                        Toast.makeText(requireContext(), "Không thể lấy vị trí", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void resverseLocationFromGeocoding_NTDanh(){

        if (!isAdded()) return;
        if (cusLat != 0.0 && cusLong != 0.0){
            Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocation(cusLat, cusLong, 1);
                if (addresses != null && !addresses.isEmpty()){
                    Address address = addresses.get(0);
                    cusAddress = address.getAddressLine(0);
                    customer_location_NTDanh.setText(cusAddress);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(requireContext(), "Lỗi khi lấy địa chỉ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void compareDistance_NTDanh(){
        db.collection("Restaurants").get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (!isAdded()) return;
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                Double resLat = documentSnapshot.getDouble("res_Lat");
                Double resLong = documentSnapshot.getDouble("res_Long");

                float[] result = new float[1];
                Location.distanceBetween(cusLat, cusLong, resLat, resLong, result);
                float distance = result[0];


                Log.d("Location", "Resnmame:" + documentSnapshot.getString("res_name") + " Distance:" + String.valueOf(distance));

                restaurant_NTDanh res = new restaurant_NTDanh();
                res.setRes_id(documentSnapshot.getId());
                res.setRes_name(documentSnapshot.getString("res_name"));
                res.setRes_img(documentSnapshot.getString("res_img"));

                restaurantwdistanceList.add(new restaurantwdistance(res, distance));
            }

            Collections.sort(restaurantwdistanceList, Comparator.comparingDouble(r -> r.distance));
            adapter_sort_res_NTDanh adapterSortResNtDanh = new adapter_sort_res_NTDanh(restaurantwdistanceList);
            recycler_food_NTDanh.setLayoutManager(new LinearLayoutManager(getContext()));
            recycler_food_NTDanh.setAdapter(adapterSortResNtDanh);
        });
    }
}