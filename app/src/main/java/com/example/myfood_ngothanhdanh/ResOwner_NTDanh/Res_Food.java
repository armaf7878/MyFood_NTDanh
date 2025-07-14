package com.example.myfood_ngothanhdanh.ResOwner_NTDanh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_food_management;
import com.example.myfood_ngothanhdanh.Model_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Res_Food extends AppCompatActivity {
    private RecyclerView recycler_food_NTDanh;
    private Button btn_AddFood_NTDanh;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private String resId;
    private static final int REQUEST_CODE_ADD_FOOD = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_res_food);
        recycler_food_NTDanh = findViewById(R.id.recycler_food_NTDanh);
        btn_AddFood_NTDanh = findViewById(R.id.btn_AddFood_NTDanh);
        btn_AddFood_NTDanh.setOnClickListener(view -> {addFood();});
        getResID();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void getResID(){
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        firestore.collection("Restaurants").whereEqualTo("owner_id", uid).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                resId = documentSnapshot.getId();
                Log.d("res_id", resId);
                getFoodList();
            }
        });
    }
    private void getFoodList(){

        firestore.collection("Foods").whereEqualTo("res_id", resId).get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<food_NTDanh> food_ntDanhList = new ArrayList<>();
            for (DocumentSnapshot documentSnapshot: queryDocumentSnapshots){
               food_NTDanh food = new food_NTDanh();
               food.setFood_id(documentSnapshot.getId());
               food.setFood_name(documentSnapshot.getString("food_name"));
               food.setFood_price(documentSnapshot.getDouble("food_price"));
               food.setFood_des(documentSnapshot.getString("food_des"));
               food.setCate_id(documentSnapshot.getString("cate_id"));
               food.setFood_img(documentSnapshot.getString("food_img"));
               food.setRes_id(documentSnapshot.getString("res_id"));
               food_ntDanhList.add(food);
           }
           adapter_food_management adapter = new adapter_food_management(this,food_ntDanhList, () ->{
               getFoodList();
           });
            Log.d("food_list", String.valueOf(food_ntDanhList.size()));
           recycler_food_NTDanh.setLayoutManager(new LinearLayoutManager(this));
           recycler_food_NTDanh.setAdapter(adapter);
        });
    }

    private void addFood(){
        Intent intent = new Intent(this, Res_Food_Add.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_FOOD);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_FOOD && resultCode == RESULT_OK) {
            getFoodList(); // Load lại danh sách món ăn sau khi thêm
        }
        if (requestCode == 1001) {
            getFoodList(); // reload danh sách món
        }
    }

}