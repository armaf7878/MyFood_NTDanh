package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.foodDAO_NTDanh;
import com.example.myfood_ngothanhdanh.MainActivity;
import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Food_Detail_NTDanh extends AppCompatActivity {
    private String foodID = "0", userID;
    private int quantity = 1;
    private TextView txt_foodName_NTDanh, txt_foodPrice_NTDanh, txt_FoodDes_NTDanh, txt_quantity_NTDanh, txt_ResName_NTDanh;
    private Button btn_descrease_NTDanh, btn_incresase_NTDanh, btn_cart_NTDanh;
    private ImageButton btn_back_NTDanh;
    private ImageView img_FoodImg_NTDanh, img_Res;
    private boolean checked;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_detail_ntdanh);
        txt_foodName_NTDanh = findViewById(R.id.txt_foodName_NTDanh);
        txt_foodPrice_NTDanh = findViewById(R.id.txt_foodPrice_NTDanh);
        txt_FoodDes_NTDanh = findViewById(R.id.txt_FoodDes_NTDanh);
        txt_quantity_NTDanh = findViewById(R.id.txt_quantity_NTDanh);
        btn_descrease_NTDanh = findViewById(R.id.btn_descrease_NTDanh);
        btn_incresase_NTDanh = findViewById(R.id.btn_incresase_NTDanh);
        btn_cart_NTDanh = findViewById(R.id.btn_cart_NTDanh);
        btn_back_NTDanh = findViewById(R.id.btn_back_NTDanh);
        img_FoodImg_NTDanh = findViewById(R.id.img_FoodImg_NTDanh);
        txt_ResName_NTDanh = findViewById(R.id.txt_ResName_NTDanh);
        img_Res = findViewById(R.id.img_Res);
        getIDFromBundle_NTDanh();
        Log.d("foodID", String.valueOf(foodID));
        loadFoodandRes();

        btn_back_NTDanh.setOnClickListener(view -> {finish();});

        btn_cart_NTDanh.setOnClickListener(view -> {checkFoodExists_NTDanh();});

        btn_incresase_NTDanh.setOnClickListener(view -> {
            quantity += 1;
            txt_quantity_NTDanh.setText(String.valueOf(quantity));
        });

        btn_descrease_NTDanh.setOnClickListener(view -> {
            if (quantity > 1){
                quantity -= 1;
                txt_quantity_NTDanh.setText(String.valueOf(quantity));
            }else {
                Toast.makeText(this, "Số lượng tối thiểu", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void getIDFromBundle_NTDanh(){
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            foodID = bundle.getString("FoodID");
        }else {
            Toast.makeText(this, "Lấy ID food thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFoodandRes(){
        if (!Objects.equals(foodID, "0")){
            foodDAO_NTDanh foodDAO_ntDanh = new foodDAO_NTDanh(this);
            db.collection("Foods").document(foodID).get().addOnSuccessListener(documentSnapshot -> {
                txt_foodName_NTDanh.setText(documentSnapshot.getString("food_name"));
                txt_foodPrice_NTDanh.setText(String.format("%,d", documentSnapshot.getDouble("food_price").intValue()));
                txt_FoodDes_NTDanh.setText(documentSnapshot.getString("food_des"));
                Glide.with(this).load(documentSnapshot.getString("food_img")).into(img_FoodImg_NTDanh);

                String resID =  documentSnapshot.getString("res_id");
                db.collection("Restaurants").document(resID).get().addOnSuccessListener(documentSnapshot1 -> {
                    txt_ResName_NTDanh.setText(documentSnapshot1.getString("res_name"));
                    Glide.with(this).load(documentSnapshot1.getString("res_img")).into(img_Res);
                }).addOnFailureListener(e -> {

                });
            }).addOnFailureListener(e -> {

            });
        }
    }
    private void checkFoodExists_NTDanh(){
        Intent intent = new Intent(Food_Detail_NTDanh.this, MainActivity.class);
        intent.putExtra("fragment_to_open_ntdanh", "cart");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            userID  = currentUser.getUid();
        }

        db.collection("Cart").whereEqualTo("user_id", userID).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                if (documentSnapshot.getString("food_id").equals(foodID)){
                    checked = true;
                }
            }

            if (checked != true){
                Map<String, Object> newCart = new HashMap<>();
                newCart.put("food_id", foodID);
                newCart.put("user_id", userID);
                newCart.put("quantity", quantity);
                db.collection("Cart").document().set(newCart).addOnSuccessListener(aVoid ->{
                    Toast.makeText(this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }).addOnFailureListener(e ->{
                    Toast.makeText(this, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                });
            }else {
                Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}