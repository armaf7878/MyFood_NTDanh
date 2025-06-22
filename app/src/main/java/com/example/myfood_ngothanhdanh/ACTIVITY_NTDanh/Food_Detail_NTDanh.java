package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfood_ngothanhdanh.DAO_NTDanh.cartDAO_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.foodDAO_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.cart_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class Food_Detail_NTDanh extends AppCompatActivity {
    private int foodID = 0;
    private int quantity = 1;
    private TextView txt_foodName_NTDanh, txt_foodPrice_NTDanh, txt_FoodDes_NTDanh, txt_quantity_NTDanh;
    private AppCompatButton btn_descrease_NTDanh, btn_incresase_NTDanh;
    private ImageButton btn_cart_NTDanh, btn_back_NTDanh;
    private ImageView img_FoodImg_NTDanh;
    private boolean checked;

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

        getIDFromBundle_NTDanh();
        Log.d("foodID", String.valueOf(foodID));
        if (foodID != 0){
            foodDAO_NTDanh foodDAO_ntDanh = new foodDAO_NTDanh(this);
            food_NTDanh food_ntDanh = foodDAO_ntDanh.getFoodByFoodID_NTDanh(foodID);
            Log.d("food_ntDanh", food_ntDanh.getFood_name());
            txt_foodName_NTDanh.setText(food_ntDanh.getFood_name());
            txt_foodPrice_NTDanh.setText(food_ntDanh.getFood_price().toString());
            txt_FoodDes_NTDanh.setText(food_ntDanh.getFood_des());
            img_FoodImg_NTDanh.setImageResource(food_ntDanh.getFood_img());

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

            btn_back_NTDanh.setOnClickListener(view -> {
                finish();
            });

            btn_cart_NTDanh.setOnClickListener(view -> {
                checkFoodExists_NTDanh();
            });

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void getIDFromBundle_NTDanh(){
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            foodID = bundle.getInt("FoodID");
        }else {
            Toast.makeText(this, "Lấy ID food thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private void checkFoodExists_NTDanh(){
        SharedPreferences sharedPreferences  = getSharedPreferences("Session", MODE_PRIVATE);
        int userID  = sharedPreferences.getInt("UserID", -1);
        cartDAO_NTDanh cartDAO_ntDanh = new cartDAO_NTDanh(this);
        List<cart_NTDanh> cartList = cartDAO_ntDanh.getAll_NTDanh();
        for (cart_NTDanh cartNtDanh1 : cartList){
            if(cartNtDanh1.getFoodID() == foodID && cartNtDanh1.getUserID() == userID){
                checked = true;
            }
        }
        if (checked != true){
            cart_NTDanh cartNtDanh = new cart_NTDanh(userID, quantity , foodID);
            long i = cartDAO_ntDanh.insert_NTDanh(cartNtDanh);
            if (i != -1){
                Intent intent = new Intent(Food_Detail_NTDanh.this, Cart_NTDanh.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Thêm món ăn thất bại", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Food_Detail_NTDanh.this, Cart_NTDanh.class);
            startActivity(intent);
        }
    }
}