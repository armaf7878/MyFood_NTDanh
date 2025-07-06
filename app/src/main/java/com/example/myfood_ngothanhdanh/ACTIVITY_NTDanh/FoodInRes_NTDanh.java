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
import com.example.myfood_ngothanhdanh.Model_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.restaurant_NTDanh;
import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FoodInRes_NTDanh extends AppCompatActivity {
    private String resID = "0";
    private TextView txt_ResName_NTDanh;
    private RecyclerView recycler_Food, recycler_ResInfo;
    private adapter_food_NTDanh adapterFoodNtDanh;
    private ImageView btn_back_NTDanh;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
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
            resID = bundle.getString("ResID");
        }else {
            Toast.makeText(this, "Lấy ID nhà hàng thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private void loadFood(){
        getIDFromBundle();
        Log.d("RESID", String.valueOf(resID));
        if (!Objects.equals(resID, "0")){
            List<food_NTDanh> food_ntDanhList = new ArrayList<>();
            db.collection("Foods").whereEqualTo("res_id", resID).get().addOnSuccessListener(queryDocumentSnapshots -> {
                for(DocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    food_NTDanh food_ntDanh = new food_NTDanh();
                    food_ntDanh.setFood_id(documentSnapshot.getId());
                    food_ntDanh.setFood_name(documentSnapshot.getString("food_name"));
                    food_ntDanh.setFood_img(documentSnapshot.getString("food_img"));
                    food_ntDanh.setFood_price(documentSnapshot.getDouble("food_price"));
                    food_ntDanhList.add(food_ntDanh);
                }
                adapterFoodNtDanh = new adapter_food_NTDanh(food_ntDanhList);
                recycler_Food.setLayoutManager(new GridLayoutManager(this, 2));
                recycler_Food.setAdapter(adapterFoodNtDanh);
            });
        }
    }

    private void loadResDetail(){
        getIDFromBundle();
        Log.d("RESID", String.valueOf(resID));
        if (!Objects.equals(resID, "0")){
            db.collection("Restaurants").document(resID).get().addOnSuccessListener(documentSnapshot -> {
                restaurant_NTDanh restaurant_ntDanh = new restaurant_NTDanh();
                restaurant_ntDanh.setRes_name(documentSnapshot.getString("res_name"));
                restaurant_ntDanh.setRes_address(documentSnapshot.getString("res_address"));
                restaurant_ntDanh.setRes_phone(documentSnapshot.getString("res_phone"));
                restaurant_ntDanh.setRes_img(documentSnapshot.getString("res_img"));
                restaurant_ntDanh.setOwner_id(documentSnapshot.getString("owner_id"));

                txt_ResName_NTDanh.setText(documentSnapshot.getString("res_name"));

                adapter_restaurant_detail_NTDanh adapterRestaurantDetailNtDanh = new adapter_restaurant_detail_NTDanh(restaurant_ntDanh);
                recycler_ResInfo.setLayoutManager(new LinearLayoutManager(this));
                recycler_ResInfo.setAdapter(adapterRestaurantDetailNtDanh);
            }).addOnFailureListener(e -> {
                Log.e("loadResDetail", "Error",e);
            });


        }
    }
}