package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfood_ngothanhdanh.R;

public class Order_NTDanh extends AppCompatActivity {
    private int quantity, foodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_ntdanh);
        getQuantityFoodIDFromBundle_NTDanh();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void getQuantityFoodIDFromBundle_NTDanh(){
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            quantity = bundle.getInt("Quantity");
            foodID = bundle.getInt("FoodID");
        }else {
            Toast.makeText(this, "Không lấy ID thành công", Toast.LENGTH_SHORT).show();
        }
    }
}