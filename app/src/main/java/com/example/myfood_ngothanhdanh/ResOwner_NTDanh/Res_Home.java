package com.example.myfood_ngothanhdanh.ResOwner_NTDanh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

public class Res_Home extends AppCompatActivity {
    private Button btn_ResInformation_NTDanh, btn_ResOrder_NTDanh, btn_ResFood_NTDanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_res_home);
        btn_ResOrder_NTDanh = findViewById(R.id.btn_ResOrder_NTDanh);
        btn_ResFood_NTDanh = findViewById(R.id.btn_ResFood_NTDanh);
        btn_ResInformation_NTDanh = findViewById(R.id.btn_ResInformation_NTDanh);

        btn_ResOrder_NTDanh.setOnClickListener(view -> {orderIntent();});

        btn_ResFood_NTDanh.setOnClickListener(view -> {foodIntent();});

        btn_ResInformation_NTDanh.setOnClickListener(view -> {informationIntent();});
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void orderIntent(){
        Intent intent = new Intent(this, Res_Order.class);
        startActivity(intent);
    }
    private void foodIntent(){
        Intent intent = new Intent(this, Res_Food.class);
        startActivity(intent);
    }
    private void informationIntent(){
        Intent intent = new Intent(this, Res_Information.class);
        startActivity(intent);
    }
}