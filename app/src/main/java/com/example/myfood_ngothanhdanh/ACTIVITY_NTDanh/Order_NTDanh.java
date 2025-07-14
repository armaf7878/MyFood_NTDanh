package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfood_ngothanhdanh.API_INTERFACE.provinceAPI_NTDanh;
import com.example.myfood_ngothanhdanh.API_INTERFACE.retrofitClient_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.cartDAO_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.foodDAO_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.order_detailDAO_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.ordersDAO_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.cart_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.district_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.order_detail_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.orders_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.province_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.provincewdistrict_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Order_NTDanh extends AppCompatActivity {
    private EditText edt_OrderName_NTDanh, edt_OrderPhone_NTDanh, edt_OrderDetailAddress_NTDanh;
    private Spinner spinnier_City_NTDanh, spinner_District_NTDanh;
    private TextView txt_Sum_NTDanh;
    private Button btn_OrderConfirm_NTDanh;
    private ProgressBar progressBar_NTDanh, progressBar_NTDanh2;
    private String order_name, order_phone, order_address;
    private Double sum = 0.0;
    private List<Integer> CartIDList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_ntdanh);
        edt_OrderName_NTDanh = findViewById(R.id.edt_OrderName_NTDanh);
        edt_OrderPhone_NTDanh = findViewById(R.id.edt_OrderPhone_NTDanh);
        edt_OrderDetailAddress_NTDanh = findViewById(R.id.edt_OrderDetailAddress_NTDanh);
        spinnier_City_NTDanh = findViewById(R.id.spinnier_City_NTDanh);
        spinner_District_NTDanh = findViewById(R.id.spinner_District_NTDanh);
        txt_Sum_NTDanh = findViewById(R.id.txt_Sum_NTDanh);
        btn_OrderConfirm_NTDanh = findViewById(R.id.btn_OrderConfirm_NTDanh);
        progressBar_NTDanh = findViewById(R.id.progressBar_NTDanh);
        progressBar_NTDanh2 = findViewById(R.id.progressBar_NTDanh2);
        progressBar_NTDanh.setVisibility(View.VISIBLE);
        progressBar_NTDanh2.setVisibility(View.VISIBLE);
        loadAPI_NTDanh();
        order_Sum_NTDanh();
        btn_OrderConfirm_NTDanh.setOnClickListener(view -> {
            order_name = edt_OrderName_NTDanh.getText().toString();
            order_phone = edt_OrderPhone_NTDanh.getText().toString();
            order_address = edt_OrderDetailAddress_NTDanh.getText().toString() + spinnier_City_NTDanh.getSelectedItem() + spinner_District_NTDanh.getSelectedItem();

            if (!order_name.isEmpty() && !order_phone.isEmpty() && !order_address.isEmpty()){

                orders_NTDanh orders_ntDanh = new orders_NTDanh();
                SharedPreferences sharedPreferences = getSharedPreferences("Session",MODE_PRIVATE);
                int userID = sharedPreferences.getInt("UserID", -1);
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String date = now.format(formatter);
                orders_ntDanh.setUser_ID(userID);
                orders_ntDanh.setOrder_name(order_name);
                orders_ntDanh.setOrder_phone(order_phone);
                orders_ntDanh.setOrder_address(order_address);
                orders_ntDanh.setOrder_date(date);

                ordersDAO_NTDanh ordersDAO_ntDanh = new ordersDAO_NTDanh(this);
                long orderID = ordersDAO_ntDanh.insert_NTDanh(orders_ntDanh);
                if (orderID != -1){
                    for (int i: CartIDList){
                        cartDAO_NTDanh cartDAO_ntDanh = new cartDAO_NTDanh(this);
                        cart_NTDanh cartNtDanh = cartDAO_ntDanh.getByID_NTDanh(i);

                        foodDAO_NTDanh foodDAO_ntDanh = new foodDAO_NTDanh(this);
//                        food_NTDanh food_ntDanh = foodDAO_ntDanh.getFoodByFoodID_NTDanh(cartNtDanh.getFoodID());

                        order_detail_NTDanh order_detail_ntDanh = new order_detail_NTDanh();
                        order_detail_ntDanh.setOrder_id(Integer.parseInt(String.valueOf(orderID)));
//                        order_detail_ntDanh.setFood_id(cartNtDanh.getFoodID());
                        order_detail_ntDanh.setQuantity(cartNtDanh.getQuantity());
                        order_detail_ntDanh.setSub_total(3500.0);

                        order_detailDAO_NTDanh orderDetailDAONtDanh = new order_detailDAO_NTDanh(this);
                        long checked = orderDetailDAONtDanh.insert_NTDanh(order_detail_ntDanh);
                        if (checked != -1){
                            Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Order_NTDanh.this, Home_NTDanh.class);
                            startActivity(intent);
                        }
                    }

                }

            }else {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void loadAPI_NTDanh(){
        provinceAPI_NTDanh provinceAPI_ntDanh = retrofitClient_NTDanh.proviceClient_NTDanh().create(provinceAPI_NTDanh.class);
        provinceAPI_ntDanh.getProvinces().enqueue(new Callback<List<province_NTDanh>>() {
            @Override
            public void onResponse(Call<List<province_NTDanh>> call, Response<List<province_NTDanh>> response) {
                progressBar_NTDanh.setVisibility(View.GONE);
                List<province_NTDanh> province_ntDanhList = response.body();
                ArrayAdapter<province_NTDanh> adapter = new ArrayAdapter<>(
                        Order_NTDanh.this,
                        android.R.layout.simple_spinner_item,
                        province_ntDanhList
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnier_City_NTDanh.setAdapter(adapter);
                spinnier_City_NTDanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        progressBar_NTDanh2.setVisibility(View.VISIBLE);
                        province_NTDanh proviceSelected = province_ntDanhList.get(i);
                        load_DistrictAPI_NTDanh(proviceSelected);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }
            @Override
            public void onFailure(Call<List<province_NTDanh>> call, Throwable t) {
                progressBar_NTDanh.setVisibility(View.GONE);
            }
        });
    }
    private void load_DistrictAPI_NTDanh(province_NTDanh proviceSelected){
        provinceAPI_NTDanh provinceAPI_ntDanh = retrofitClient_NTDanh.proviceClient_NTDanh().create(provinceAPI_NTDanh.class);
        provinceAPI_ntDanh.getProvinceWithDistricts(proviceSelected.getCode()).enqueue(new Callback<provincewdistrict_NTDanh>() {
            @Override
            public void onResponse(Call<provincewdistrict_NTDanh> call, Response<provincewdistrict_NTDanh> response) {
                progressBar_NTDanh2.setVisibility(View.GONE);
                List<district_NTDanh> district_ntDanhList = response.body().getDistricts();
                ArrayAdapter<district_NTDanh> adapter = new ArrayAdapter<>(
                        Order_NTDanh.this,
                        android.R.layout.simple_spinner_item,
                        district_ntDanhList
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_District_NTDanh.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<provincewdistrict_NTDanh> call, Throwable t) {
                progressBar_NTDanh2.setVisibility(View.GONE);

            }
        });

    }
    private void order_Sum_NTDanh(){
        Bundle bundle = getIntent().getExtras();
        CartIDList = bundle.getIntegerArrayList("CartID");
        for(int i : CartIDList){
            cartDAO_NTDanh cartDAO_ntDanh = new cartDAO_NTDanh(this);
            cart_NTDanh cart_ntDanh = cartDAO_ntDanh.getByID_NTDanh(i);

            foodDAO_NTDanh foodDAO_ntDanh = new foodDAO_NTDanh(this);
//            food_NTDanh food_ntDanh = foodDAO_ntDanh.getFoodByFoodID_NTDanh(cart_ntDanh.getFoodID());
//            sum += cart_ntDanh.getQuantity() * food_ntDanh.getFood_price();
        }
        txt_Sum_NTDanh.setText(sum.toString());
    }
}