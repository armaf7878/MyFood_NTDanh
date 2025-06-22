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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_food_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.foodDAO_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.restaurantDAO_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.restaurant_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class Food_NTDanh extends AppCompatActivity {
    private int resID = 0;
    private ImageView img_ResImg_NTDanh;
    private TextView txt_ResName_NTDanh, txt_ResAddress_NTDanh, txt_ResPhone_NTDanh;
    private RecyclerView recycler_Food;
    private adapter_food_NTDanh adapterFoodNtDanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_ntdanh);
        img_ResImg_NTDanh = findViewById(R.id.img_ResImg_NTDanh);
        txt_ResName_NTDanh = findViewById(R.id.txt_ResName_NTDanh);
        txt_ResAddress_NTDanh = findViewById(R.id.txt_ResAddress_NTDanh);
        txt_ResPhone_NTDanh = findViewById(R.id.txt_ResPhone_NTDanh);
        recycler_Food = findViewById(R.id.recycler_Food);

        getIDFromBundle();
        Log.d("RESID", String.valueOf(resID));
        if (resID != 0){
            restaurantDAO_NTDanh restaurantDAO_ntDanh = new restaurantDAO_NTDanh(this);
            restaurant_NTDanh restaurant_ntDanh = restaurantDAO_ntDanh.getByResID_NTDanh(resID);
            img_ResImg_NTDanh.setImageResource(restaurant_ntDanh.getRes_img());
            txt_ResName_NTDanh.setText(restaurant_ntDanh.getRes_name());
            txt_ResAddress_NTDanh.setText(restaurant_ntDanh.getRes_address());
            txt_ResPhone_NTDanh.setText(restaurant_ntDanh.getRes_phone());

            foodDAO_NTDanh foodDAO_ntDanh = new foodDAO_NTDanh(this);
//            food_NTDanh food_ntDanh = new food_NTDanh();
//            food_ntDanh.setFood_name("Phở bò");
//            food_ntDanh.setFood_price(35000.0);
//            food_ntDanh.setFood_type("Món nước");
//            food_ntDanh.setFood_des("Phở bò tuyệt đỉnh, thơm ngon");
//            food_ntDanh.setFood_img(R.drawable.food_phobo);
//            food_ntDanh.setRes_id(1);
//            if(foodDAO_ntDanh.insertFood_NTDanh(food_ntDanh) != -1){
//                Toast.makeText(this, "INSERT THÀNH CÔNG", Toast.LENGTH_SHORT).show();
//                Log.d("insert", "1");
//            }
//
//            food_NTDanh food_ntDanh1 = new food_NTDanh();
//            food_ntDanh1.setFood_name("Quẩy ăn kèm");
//            food_ntDanh1.setFood_price(5000.0);
//            food_ntDanh1.setFood_type("Món ăn kèm");
//            food_ntDanh1.setFood_des("Quẩy giòn, thơm ngon");
//            food_ntDanh1.setFood_img(R.drawable.food_quay);
//            food_ntDanh1.setRes_id(1);
//            if(foodDAO_ntDanh.insertFood_NTDanh(food_ntDanh1) != -1){
//                Toast.makeText(this, "INSERT THÀNH CÔNG", Toast.LENGTH_SHORT).show();
//            }
//
//            food_NTDanh food_ntDanh2 = new food_NTDanh();
//            food_ntDanh2.setFood_name("Phở đặc biệt'");
//            food_ntDanh2.setFood_price(55000.0);
//            food_ntDanh2.setFood_type("Món ăn kèm");
//            food_ntDanh2.setFood_des("Phở đầy đủ chất lượng cao, thơm ngon");
//            food_ntDanh2.setFood_img(R.drawable.food_phodacbiet);
//            food_ntDanh2.setRes_id(1);
//            if(foodDAO_ntDanh.insertFood_NTDanh(food_ntDanh2) != -1){
//                Toast.makeText(this, "INSERT THÀNH CÔNG", Toast.LENGTH_SHORT).show();
//            }

            List<food_NTDanh> food_ntDanhList = foodDAO_ntDanh.getFoodByResID_NTDanh(resID);
            if (food_ntDanhList != null){
                adapterFoodNtDanh = new adapter_food_NTDanh(food_ntDanhList);
                recycler_Food.setLayoutManager(new LinearLayoutManager(this));
                recycler_Food.setAdapter(adapterFoodNtDanh);
            }else {
                Log.d("TEST FOOD","BUG NÈ");
                Toast.makeText(this, "Của hàng đã hết món", Toast.LENGTH_LONG).show();
            }

        }
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
}