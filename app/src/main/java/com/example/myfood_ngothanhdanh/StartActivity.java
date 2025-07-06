package com.example.myfood_ngothanhdanh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh.Login_NTDanh;
import com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh.FillInformation_NTDanh;
import com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh.Register_NTDanh;


public class StartActivity extends AppCompatActivity {
    private Button btn_login_NTDanh, btn_signup_NTDanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        btn_login_NTDanh = findViewById(R.id.btn_login_NTDanh);
        btn_login_NTDanh.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, Login_NTDanh.class);
            startActivity(intent);
        });
        btn_signup_NTDanh = findViewById(R.id.btn_signup_NTDanh);
        btn_signup_NTDanh.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, Register_NTDanh.class);
            startActivity(intent);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}