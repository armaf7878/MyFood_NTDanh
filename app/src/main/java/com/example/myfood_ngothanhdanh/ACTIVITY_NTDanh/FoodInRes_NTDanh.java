package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_food_NTDanh;
import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_restaurant_detail_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.foodDAO_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.restaurantDAO_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.restaurant_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class FoodInRes_NTDanh extends AppCompatActivity {
    private int resID = 0;
    private TextView txt_ResName_NTDanh;
    private RecyclerView recycler_Food, recycler_ResInfo;
    private adapter_food_NTDanh adapterFoodNtDanh;
    private ImageView btn_back_NTDanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_in_res_ntdanh);
        txt_ResName_NTDanh = findViewById(R.id.txt_ResName_NTDanh);
        recycler_Food = findViewById(R.id.recycler_Food);

        loadFood();

        recycler_ResInfo = findViewById(R.id.recycler_ResInfo);
        loadResDetail();

        btn_back_NTDanh = findViewById(R.id.btn_back_NTDanh);
        btn_back_NTDanh.setOnClickListener(view -> {finish();});
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void getIDFromBundle(){
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            resID = bundle.getInt("ResID");
        }else {
            Toast.makeText(this, "Lấy ID nhà hàng thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private void loadFood(){
        getIDFromBundle();
        Log.d("RESID", String.valueOf(resID));
        if (resID != 0){
            restaurantDAO_NTDanh restaurantDAO_ntDanh = new restaurantDAO_NTDanh(this);
            restaurant_NTDanh restaurant_ntDanh = restaurantDAO_ntDanh.getByResID_NTDanh(resID);
            txt_ResName_NTDanh.setText(restaurant_ntDanh.getRes_name());

            foodDAO_NTDanh foodDAO_ntDanh = new foodDAO_NTDanh(this);
            List<food_NTDanh> food_ntDanhList = foodDAO_ntDanh.getFoodByResID_NTDanh(resID);
            if (food_ntDanhList != null){
                adapterFoodNtDanh = new adapter_food_NTDanh(food_ntDanhList);
                recycler_Food.setLayoutManager(new GridLayoutManager(this, 2));
                recycler_Food.setAdapter(adapterFoodNtDanh);
            }else {
                Log.d("TEST FOOD","BUG NÈ");
                Toast.makeText(this, "Của hàng đã hết món", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void loadResDetail(){
        getIDFromBundle();
        Log.d("RESID", String.valueOf(resID));
        if (resID != 0){
            restaurantDAO_NTDanh restaurantDAO_ntDanh = new restaurantDAO_NTDanh(this);
            restaurant_NTDanh restaurant_ntDanh = restaurantDAO_ntDanh.getByResID_NTDanh(resID);
            adapter_restaurant_detail_NTDanh adapterRestaurantDetailNtDanh = new adapter_restaurant_detail_NTDanh(restaurant_ntDanh);
            recycler_ResInfo.setLayoutManager(new LinearLayoutManager(this));
            recycler_ResInfo.setAdapter(adapterRestaurantDetailNtDanh);
        }
    }
}