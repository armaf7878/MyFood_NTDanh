package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_restaurant_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.restaurantDAO_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.restaurant_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class Home_NTDanh extends AppCompatActivity {
    RecyclerView RecyclerHome;
    adapter_restaurant_NTDanh adapterRestaurantNtDanh;
    List<restaurant_NTDanh> restaurant_ntDanhList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_ntdanh);
        restaurantDAO_NTDanh restaurantDAO_ntDanh = new restaurantDAO_NTDanh(this);
        restaurant_NTDanh restaurant_ntDanh = new restaurant_NTDanh();
        restaurant_ntDanh.setRes_name("Lẩu cua đồng Yên Lãng");
        restaurant_ntDanh.setRes_phone("0353991094");
        restaurant_ntDanh.setRes_address("175 Đào Duy Anh, Phường 9, Quận Phú Nhuận, Hồ Chí Minh");
        restaurant_ntDanh.setRes_img(R.drawable.store_lau);
        restaurantDAO_ntDanh.insert_NTDanh(restaurant_ntDanh);

        restaurant_NTDanh restaurant_ntDanh1 = new restaurant_NTDanh();
        restaurant_ntDanh1.setRes_name("Bánh mì Dân Tổ");
        restaurant_ntDanh1.setRes_phone("0353991094");
        restaurant_ntDanh1.setRes_address("175 Đào Duy Anh, Phường 9, Quận Phú Nhuận, Hồ Chí Minh");
        restaurant_ntDanh1.setRes_img(R.drawable.store_banhmi);
        restaurantDAO_ntDanh.insert_NTDanh(restaurant_ntDanh1);

        restaurant_NTDanh restaurant_ntDanh2 = new restaurant_NTDanh();
        restaurant_ntDanh2.setRes_name("Phở Lý Quốc Sư");
        restaurant_ntDanh2.setRes_phone("093323327");
        restaurant_ntDanh2.setRes_address("14 Nguyễn Oanh, Phường 17, Quận Gò Vấp, Hồ Chí Minh");
        restaurant_ntDanh2.setRes_img(R.drawable.store_pho);
        restaurantDAO_ntDanh.insert_NTDanh(restaurant_ntDanh2);

        restaurant_ntDanhList = restaurantDAO_ntDanh.getAll();
        RecyclerHome = findViewById(R.id.RecyclerHome);
        RecyclerHome.setLayoutManager(new LinearLayoutManager(this));
        adapterRestaurantNtDanh = new adapter_restaurant_NTDanh(restaurant_ntDanhList);
        RecyclerHome.setAdapter(adapterRestaurantNtDanh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}