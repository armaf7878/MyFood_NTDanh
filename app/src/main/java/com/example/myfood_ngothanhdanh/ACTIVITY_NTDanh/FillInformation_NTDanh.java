package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfood_ngothanhdanh.API_INTERFACE.provinceAPI_NTDanh;
import com.example.myfood_ngothanhdanh.API_INTERFACE.retrofitClient_NTDanh;
import com.example.myfood_ngothanhdanh.MainActivity;
import com.example.myfood_ngothanhdanh.Model_NTDanh.district_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.province_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.provincewdistrict_NTDanh;
import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FillInformation_NTDanh extends AppCompatActivity {
    private Button btnDangKy;
    private EditText edtPhone, edtFullName,edt_DetailAddress;
    private String fullname, gender, phone, district, city, detail_address;
    private RadioButton radio_male, radio_female;
    private Spinner spinner_City_NTDanh, spinner_District_NTDanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fillinformation_ntdanh);
        btnDangKy = findViewById(R.id.btnDangKy);
        edtPhone = findViewById(R.id.edtPhone);
        edt_DetailAddress = findViewById(R.id.edt_DetailAddress);
        radio_male = findViewById(R.id.radio_male);
        radio_female = findViewById(R.id.radio_female);
        edtFullName = findViewById(R.id.edtFullName);
        spinner_City_NTDanh = findViewById(R.id.spinner_City_NTDanh);
        spinner_District_NTDanh = findViewById(R.id.spinner_District_NTDanh);
        loadCity();
        btnDangKy.setOnClickListener(view -> {
            checkInput_NTDanh();
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadCity(){
        retrofitClient_NTDanh retrofit = new retrofitClient_NTDanh();
        provinceAPI_NTDanh city_api = retrofit.proviceClient_NTDanh().create(provinceAPI_NTDanh.class);
        city_api.getProvinces().enqueue(new Callback<List<province_NTDanh>>() {
            @Override
            public void onResponse(Call<List<province_NTDanh>> call, Response<List<province_NTDanh>> response) {
                if (response.isSuccessful()){
                    List<province_NTDanh> province_ntDanhList = response.body();
                    province_ntDanhList.add(0, new province_NTDanh(0,"Chọn tỉnh/thành phố"));
                    ArrayAdapter<province_NTDanh> adapter = new ArrayAdapter<>(
                            FillInformation_NTDanh.this,
                            R.layout.spinner_item,
                            province_ntDanhList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_City_NTDanh.setAdapter(adapter);

                    spinner_City_NTDanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            province_NTDanh province = province_ntDanhList.get(i);
                            loadDistrict(province.getCode());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else {
                    Log.d("API", "Respond failed or empty body, Code:" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<province_NTDanh>> call, Throwable t) {
                Log.d("API", "onFailure called: " + t.getMessage());
                Toast.makeText(FillInformation_NTDanh.this, "Lỗi kết nối API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDistrict(int province_code){
        retrofitClient_NTDanh retrofit = new retrofitClient_NTDanh();
        provinceAPI_NTDanh district_api = retrofit.proviceClient_NTDanh().create(provinceAPI_NTDanh.class);
        district_api.getProvinceWithDistricts(province_code).enqueue(new Callback<provincewdistrict_NTDanh>() {
            @Override
            public void onResponse(Call<provincewdistrict_NTDanh> call, Response<provincewdistrict_NTDanh> response) {
                if (response.isSuccessful()){
                    List<district_NTDanh> districtList = response.body().getDistricts();
                    districtList.add(0, new district_NTDanh(0, "Chọn huyện"));
                    ArrayAdapter<district_NTDanh> adapter = new ArrayAdapter<>(
                            FillInformation_NTDanh.this,
                            R.layout.spinner_item,
                            districtList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_District_NTDanh.setAdapter(adapter);
                }else {
                    Log.d("API", "Respond failed or empty body, Code:" + response.code());
                }
            }

            @Override
            public void onFailure(Call<provincewdistrict_NTDanh> call, Throwable t) {
                Log.d("API", "onFailure called:" + t.getMessage());
                Toast.makeText(FillInformation_NTDanh.this, "Lỗi kết nối API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkInput_NTDanh(){
        if (radio_male.isChecked()){
            gender = "male";
        }else {gender = "female";};
        fullname = edtFullName.getText().toString();
        phone = edtPhone.getText().toString();
        detail_address = edt_DetailAddress.getText().toString();

        if (fullname.isEmpty() || phone.isEmpty() || detail_address.isEmpty() || !radio_male.isChecked() && !radio_female.isChecked() || spinner_City_NTDanh.getSelectedItemPosition() == 0 || spinner_District_NTDanh.getSelectedItemPosition() ==0 ){
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = getIntent();
            String uid = intent.getStringExtra("user_uid");
            province_NTDanh provinceNtDanh = (province_NTDanh) spinner_City_NTDanh.getSelectedItem();
            city = provinceNtDanh.getName();
            district_NTDanh districtNtDanh = (district_NTDanh) spinner_District_NTDanh.getSelectedItem();
            district = districtNtDanh.getName();
            if (!uid.isEmpty()){
                createNewAccount_NTDanh(uid, fullname, phone, gender, city, district, detail_address);
            }else {
                Toast.makeText(this, "Thông tin UID trống", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void createNewAccount_NTDanh(String uid, String fullname, String phone, String gender, String city, String district, String detail_address) {
        FirebaseFirestore db =  FirebaseFirestore.getInstance();
        Map<String, Object> newUser = new HashMap<>();
        newUser.put("fullname", fullname);
        newUser.put("phone", phone);
        newUser.put("gender", gender);
        newUser.put("role", "customer");
        newUser.put("city", city);
        newUser.put("district", district);
        newUser.put("detail_address", detail_address);

        db.collection("Users").document(uid).set(newUser).addOnSuccessListener(aVoid -> {
            Log.d("Firestore", "User created successfully");
            Intent intent = new Intent(FillInformation_NTDanh.this, MainActivity.class);
            startActivity(intent);
        }).addOnFailureListener(e -> {
            Log.e("Firestore", "Failed to create user", e);
        });
    }


}