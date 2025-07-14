package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfood_ngothanhdanh.API_INTERFACE.provinceAPI_NTDanh;
import com.example.myfood_ngothanhdanh.API_INTERFACE.retrofitClient_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.district_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.province_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.provincewdistrict_NTDanh;
import com.example.myfood_ngothanhdanh.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Account_Information extends AppCompatActivity {
    private Spinner spinner_City_NTDanh, spinner_District_NTDanh;
    private retrofitClient_NTDanh retrofit = new retrofitClient_NTDanh();
    private EditText edt_DetailAddress, edt_userName, edt_Phone;
    private Button btn_Save_NTDanh, btn_Cancel_NTDanh;
    private String detail_Address, user_Name, user_Phone, user_city, user_district;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_information);

        spinner_City_NTDanh = findViewById(R.id.spinner_City_NTDanh);
        spinner_District_NTDanh = findViewById(R.id.spinner_District_NTDanh);
        edt_DetailAddress = findViewById(R.id.edt_DetailAddress);
        edt_userName = findViewById(R.id.edt_userName);
        edt_Phone = findViewById(R.id.edt_Phone);
        btn_Save_NTDanh = findViewById(R.id.btn_Save_NTDanh);
        btn_Cancel_NTDanh = findViewById(R.id.btn_Cancel_NTDanh);
        fetchData_NTDanh();
        btn_Cancel_NTDanh.setOnClickListener(view -> finish());
        btn_Save_NTDanh.setOnClickListener(view -> {saveChange_NTDanh();});
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void saveChange_NTDanh() {
        detail_Address = edt_DetailAddress.getText().toString();
        user_Name = edt_userName.getText().toString();
        user_Phone = edt_Phone.getText().toString();
        province_NTDanh current_City = (province_NTDanh) spinner_City_NTDanh.getSelectedItem();
        user_city = current_City.getName();
        district_NTDanh current_District = (district_NTDanh) spinner_District_NTDanh.getSelectedItem();
        user_district = current_District.getName();
        if (detail_Address.isEmpty() || user_Name.isEmpty() || user_Phone.isEmpty() || user_city.isEmpty() || user_district.isEmpty()){
            Toast.makeText(this, "Please fill in all information", Toast.LENGTH_SHORT).show();
        }else {
            FirebaseUser user = mAuth.getCurrentUser();
            String uid = user.getUid();
            Map<String, Object> update = new HashMap<>();
            update.put("city", user_city);
            update.put("district", user_district);
            update.put("fullname", user_Name);
            update.put("phone", user_Phone);
            firestore.collection("Users").document(uid).update(update).addOnSuccessListener(avoid -> {
                Toast.makeText(this, "Save successfully", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void fetchData_NTDanh(){
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        firestore.collection("Users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                detail_Address =  documentSnapshot.getString("detail_address");
                user_Phone = documentSnapshot.getString("phone");
                user_Name = documentSnapshot.getString("fullname");
                user_district = documentSnapshot.getString("district");
                user_city = documentSnapshot.getString("city");
                loadCitySpinner();
                loadData_User();
            }
        });
    }
    private void loadData_User(){
        edt_DetailAddress.setText(detail_Address);
        edt_userName.setText(user_Name);
        edt_Phone.setText(user_Phone);
    }
    private void loadCitySpinner(){
        provinceAPI_NTDanh provinceAPI_ntDanh = retrofit.proviceClient_NTDanh().create(provinceAPI_NTDanh.class);
        provinceAPI_ntDanh.getProvinces().enqueue(new Callback<List<province_NTDanh>>() {
            @Override
            public void onResponse(Call<List<province_NTDanh>> call, Response<List<province_NTDanh>> response) {
                if (response.isSuccessful()){
                    List<province_NTDanh> province_ntDanhList = response.body();
                    ArrayAdapter<province_NTDanh> adapter = new ArrayAdapter<>(
                            Account_Information.this,
                            R.layout.spinner_item,
                            province_ntDanhList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_City_NTDanh.setAdapter(adapter);
                    int position_userCity = 0;
                    for (int i = 0; i < province_ntDanhList.size(); i++){
                        Log.d("position_userCity", String.valueOf(user_city));
                        if (province_ntDanhList.get(i).getName().equals(user_city)){
                            position_userCity = i;
                            break;
                        }
                    };
                    spinner_City_NTDanh.setSelection(position_userCity);
                    Log.d("position_userCity", String.valueOf(position_userCity));
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
                    Log.d("API", "Response failed or empty body, code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<province_NTDanh>> call, Throwable t) {
                Log.d("API", "onFailure called: " + t.getMessage());
                Toast.makeText(Account_Information.this, "Lỗi kết nối API", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadDistrict(int province_code){
        provinceAPI_NTDanh district = retrofit.proviceClient_NTDanh().create(provinceAPI_NTDanh.class);
        district.getProvinceWithDistricts(province_code).enqueue(new Callback<provincewdistrict_NTDanh>() {
            @Override
            public void onResponse(Call<provincewdistrict_NTDanh> call, Response<provincewdistrict_NTDanh> response) {
                if (response.isSuccessful()){
                    List<district_NTDanh> districtList = response.body().getDistricts();
                    districtList.add(new district_NTDanh(0,"Chọn tỉnh"));
                    ArrayAdapter<district_NTDanh> adapter = new ArrayAdapter<>(
                            Account_Information.this,
                            R.layout.spinner_item,
                            districtList
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_District_NTDanh.setAdapter(adapter);
                    int position_userDistrict = 0;
                    for (int i = 0; i < districtList.size(); i++){
                        if (districtList.get(i).getName().equals(user_district)){
                            position_userDistrict = i;
                            break;
                        }
                    }
                    spinner_District_NTDanh.setSelection(position_userDistrict);
                }else {
                    Log.d("API", "Respond failed or empty body, code" + response.code());
                }
            }

            @Override
            public void onFailure(Call<provincewdistrict_NTDanh> call, Throwable t) {
                Log.d("API", "onFaile Called" + t.getMessage());
                Toast.makeText(Account_Information.this, "Lỗi kết nối API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}