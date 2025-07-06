package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.content.Intent;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_food_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FoodInCate extends AppCompatActivity {
    private String cate_id = "0";
    private ImageView btn_back_NTDanh;
    private TextView txt_CateName_NTDanh;
    private RecyclerView recycler_Food;
    private FirebaseFirestore firestore =  FirebaseFirestore.getInstance();
    private List<food_NTDanh> food_ntDanhList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_in_cate);

        btn_back_NTDanh = findViewById(R.id.btn_back_NTDanh);
        btn_back_NTDanh.setOnClickListener(view -> finish());

        txt_CateName_NTDanh = findViewById(R.id.txt_CateName_NTDanh);
        recycler_Food = findViewById(R.id.recycler_Food);
        getIdFromIntent_NTDanh();
        loadFood();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void getIdFromIntent_NTDanh(){
        Intent intent = getIntent();
        cate_id = intent.getStringExtra("cate_id");
        if (!Objects.equals(cate_id, "0")){
            Log.d("cate_id", cate_id);
        }
    }
    private void loadFood(){
        if (cate_id.equals("0")){
            Toast.makeText(this, "Không lấy được danh sách món", Toast.LENGTH_SHORT).show();
        }else {
            firestore.collection("Categories").document(cate_id).get().addOnSuccessListener(documentSnapshot -> {
                txt_CateName_NTDanh.setText(documentSnapshot.getString("cate_name"));
            });
            firestore.collection("Foods").whereEqualTo("cate_id", cate_id).get().addOnSuccessListener(queryDocumentSnapshots -> {
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    food_NTDanh food_ntDanh = new food_NTDanh();
                    food_ntDanh.setFood_id(documentSnapshot.getId());
                    food_ntDanh.setFood_name(documentSnapshot.getString("food_name"));
                    food_ntDanh.setFood_price(documentSnapshot.getDouble("food_price"));
                    food_ntDanh.setFood_img(documentSnapshot.getString("food_img"));
                    food_ntDanhList.add(food_ntDanh);
                }
                adapter_food_NTDanh adapter_food_ntDanh = new adapter_food_NTDanh(food_ntDanhList);
                recycler_Food.setLayoutManager(new GridLayoutManager(this, 2));
                recycler_Food.setAdapter(adapter_food_ntDanh);
            });
        }
    }
}