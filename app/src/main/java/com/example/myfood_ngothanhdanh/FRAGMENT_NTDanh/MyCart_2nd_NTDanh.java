package com.example.myfood_ngothanhdanh.FRAGMENT_NTDanh;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfood_ngothanhdanh.API_INTERFACE.provinceAPI_NTDanh;
import com.example.myfood_ngothanhdanh.API_INTERFACE.retrofitClient_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.district_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.orders_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.province_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.provincewdistrict_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.user_NTDanh;
import com.example.myfood_ngothanhdanh.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCart_2nd_NTDanh extends Fragment {
    private TextView txt_Coordinate_NTDanh;
    private EditText edtUserName_NTDanh, edtUserPhone_NTDanh, edtDetailAddress_NTDanh;
    private Spinner spinner_City_NTDanh, spinner_District_NTDanh;
    private ImageButton btn_Location_NTDanh;
    private Button btn_NextStep_NTDanh;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private Bundle bundle;
    private user_NTDanh user_data  = new user_NTDanh();
    private retrofitClient_NTDanh retrofit;
    private Double cus_Lat = 0.0, cus_Long = 0.0;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private List<String> checkListFoodID_NTDanh = new ArrayList<>();
    private orders_NTDanh order_info = new orders_NTDanh();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_my_cart_2nd__n_t_danh, container, false);
        txt_Coordinate_NTDanh = view.findViewById(R.id.txt_Coordinate_NTDanh);
        edtUserName_NTDanh = view.findViewById(R.id.edtUserName_NTDanh);
        edtUserPhone_NTDanh = view.findViewById(R.id.edtUserPhone_NTDanh);
        edtDetailAddress_NTDanh = view.findViewById(R.id.edtDetailAddress_NTDanh);
        spinner_City_NTDanh = view.findViewById(R.id.spinner_City_NTDanh);
        spinner_District_NTDanh = view.findViewById(R.id.spinner_District_NTDanh);
        btn_Location_NTDanh = view.findViewById(R.id.btn_Location_NTDanh);
        btn_NextStep_NTDanh = view.findViewById(R.id.btn_NextStep_NTDanh);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataFromBundle();
        loadDataUser();
        btn_Location_NTDanh.setOnClickListener(view1 -> {getLocation();});

        getChildFragmentManager().setFragmentResultListener("location_result", getViewLifecycleOwner(), ((requestKey, result) -> {
            double lat = result.getDouble("newLat");
            double lng = result.getDouble("newLong");
            txt_Coordinate_NTDanh.setText(lat + ", " + lng);
            cus_Lat = lat;
            cus_Long = lng;
        }));

        btn_NextStep_NTDanh.setOnClickListener(view1 -> {nextStep_NTDanh();});
    }
    private void getDataFromBundle(){
        bundle = getArguments();
        if (bundle != null){
            checkListFoodID_NTDanh = bundle.getStringArrayList("CartID");
            for (String i: checkListFoodID_NTDanh){
                Log.d("CartID", i);
            }
        }
    }
    private void loadDataUser(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Users").document(uid).get().addOnSuccessListener(documentSnapshot -> {
            user_data.setUser_fullname(documentSnapshot.getString("fullname"));
            user_data.setUser_phone(documentSnapshot.getString("phone"));
            user_data.setUser_city(documentSnapshot.getString("city"));
            user_data.setUser_district(documentSnapshot.getString("district"));
            user_data.setDetail_address(documentSnapshot.getString("detail_address"));

            edtUserName_NTDanh.setText(user_data.getUser_fullname());
            edtUserPhone_NTDanh.setText(user_data.getUser_phone());
            edtDetailAddress_NTDanh.setText(user_data.getDetail_address());

            loadCity();
        });
    }
    private void loadCity(){
        provinceAPI_NTDanh cityApi = retrofit.proviceClient_NTDanh().create(provinceAPI_NTDanh.class);
        cityApi.getProvinces().enqueue(new Callback<List<province_NTDanh>>() {
            @Override
            public void onResponse(Call<List<province_NTDanh>> call, Response<List<province_NTDanh>> response) {
                if (response.isSuccessful()){
                    List<province_NTDanh> province_ntDanhList = response.body();
                    ArrayAdapter<province_NTDanh> adapter = new ArrayAdapter<>(
                            getContext(),
                            R.layout.spinner_item,
                            province_ntDanhList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_City_NTDanh.setAdapter(adapter);

                    int user_city_position = 0;
                    for (int i = 0; i < province_ntDanhList.size(); i++){
                        if (province_ntDanhList.get(i).getName().equals(user_data.getUser_city())){
                            user_city_position = i;
                            break;
                        }
                    }
                    spinner_City_NTDanh.setSelection(user_city_position);
                    loadDistrict(province_ntDanhList.get(user_city_position).getCode());

                    spinner_City_NTDanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            province_NTDanh selected_city = province_ntDanhList.get(i);
                            loadDistrict(selected_city.getCode());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else {
                    Log.d("API", "Empty body, Code:" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<province_NTDanh>> call, Throwable t) {

            }
        });
    }
    private void loadDistrict(int code) {
        provinceAPI_NTDanh districtApi = retrofit.proviceClient_NTDanh().create(provinceAPI_NTDanh.class);
        districtApi.getProvinceWithDistricts(code).enqueue(new Callback<provincewdistrict_NTDanh>() {
            @Override
            public void onResponse(Call<provincewdistrict_NTDanh> call, Response<provincewdistrict_NTDanh> response) {
                List<district_NTDanh> district_ntDanhList = response.body().getDistricts();
                ArrayAdapter<district_NTDanh> adapter = new ArrayAdapter<>(
                        getContext(),
                        R.layout.spinner_item,
                        district_ntDanhList
                );
                spinner_District_NTDanh.setAdapter(adapter);

                int user_district_position = 0;
                for (int i = 0; i < district_ntDanhList.size(); i++){
                    if (district_ntDanhList.get(i).getName().equals(user_data.getUser_district())){
                        user_district_position = i;
                    }
                }
                spinner_District_NTDanh.setSelection(user_district_position);
            }

            @Override
            public void onFailure(Call<provincewdistrict_NTDanh> call, Throwable t) {

            }
        });
    }
    private void requestPermission(){
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1001);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getContext(), "Granting access Succesfully", Toast.LENGTH_SHORT).show();
                getLocation();
            }else {
                Toast.makeText(getContext(), "Access Deny", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null){
                cus_Lat = location.getLatitude();
                cus_Long = location.getLongitude();
                txt_Coordinate_NTDanh.setText(String.valueOf(cus_Lat) + String.valueOf(cus_Long));
                Bundle bundle = new Bundle();
                bundle.putDouble("cus_Lat", cus_Lat);
                bundle.putDouble("cus_Long", cus_Long);
                MyMapFragment fragment = new MyMapFragment();
                fragment.setArguments(bundle);
                getChildFragmentManager().beginTransaction().replace(R.id.map_container, fragment).commit();
            }else {
                Toast.makeText(getContext(), "Can't get location", Toast.LENGTH_SHORT).show();
                txt_Coordinate_NTDanh.setText("Can't get location");
            }
        });
    }

    private void nextStep_NTDanh(){

        MyCart_3rd_NTDanh fragment = new MyCart_3rd_NTDanh();
        Bundle bundle1 = new Bundle();
        bundle1.putStringArrayList("CarID_List", new ArrayList<>(checkListFoodID_NTDanh));
        getOrderInformation();
        bundle1.putParcelable("Order_Infor", order_info);
        fragment.setArguments(bundle1);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_mycart2nd , fragment).addToBackStack(null).commit();
    }
    private void getOrderInformation(){
        order_info.setOrder_name(edtUserName_NTDanh.getText().toString());
        order_info.setOrder_phone(edtUserPhone_NTDanh.getText().toString());

        province_NTDanh province_ntDanh = (province_NTDanh) spinner_City_NTDanh.getSelectedItem();
        String city = province_ntDanh.getName();

        district_NTDanh district_ntDanh  = (district_NTDanh) spinner_District_NTDanh.getSelectedItem();
        String district = district_ntDanh.getName();
        order_info.setOrder_address(edtDetailAddress_NTDanh.getText().toString() + ", " + district + ", " + city );
        order_info.setOrder_address_lat(cus_Lat);
        order_info.setOrder_address_long(cus_Long);
    }
}