package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfood_ngothanhdanh.DAO_NTDanh.userDAO_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.user_NTDanh;
import com.example.myfood_ngothanhdanh.R;

public class Login_NTDanh extends AppCompatActivity {
    private Button btnDangNhap_NTDanh;
    private EditText edtUserName_NTDanh, edtPass_NTDanh;
    private String username, pass;
    private ImageView btn_close_NTDanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        btnDangNhap_NTDanh = findViewById(R.id.btnDangNhap_NTDanh);
        edtUserName_NTDanh = findViewById(R.id.edtUserName_NTDanh);
        edtPass_NTDanh = findViewById(R.id.edtPass_NTDanh);
        btnDangNhap_NTDanh.setOnClickListener(view2 ->{
            username = edtUserName_NTDanh.getText().toString();
            pass = edtPass_NTDanh.getText().toString();
            userDAO_NTDanh userDAO_ntDanh = new userDAO_NTDanh(this);
            user_NTDanh userNtDanh = userDAO_ntDanh.getByUserName_NTDanh(username);
            if (userNtDanh != null){
                if (userNtDanh.getUser_pass().equals(pass)){
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("UserID", userNtDanh.getUser_id());
                    editor.apply();
                    Intent intent = new Intent(this, Home_NTDanh.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Không kiếm thấy username", Toast.LENGTH_SHORT).show();
            }
        });
        btn_close_NTDanh = findViewById(R.id.btn_close_NTDanh);
        btn_close_NTDanh.setOnClickListener(view -> {finish();});

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}