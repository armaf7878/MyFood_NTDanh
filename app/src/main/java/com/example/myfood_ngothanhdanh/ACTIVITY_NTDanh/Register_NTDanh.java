package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfood_ngothanhdanh.R;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register_NTDanh extends AppCompatActivity {
    private Button btnDangKy;
    private EditText edt_RePass, edt_Pass, edt_userName;
    private String user, pass, repass;
    private FirebaseAuth mAuth;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_ntdanh);
        btnDangKy = findViewById(R.id.btnDangKy);
        edt_Pass = findViewById(R.id.edt_Pass);
        edt_RePass = findViewById(R.id.edt_RePass);
        edt_userName = findViewById(R.id.edt_userName);

        mAuth = FirebaseAuth.getInstance();
        btnDangKy.setOnClickListener(view -> {
            user = edt_userName.getText().toString().trim();
            pass = edt_Pass.getText().toString().trim();
            repass = edt_RePass.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }if (pass.equals(repass)){
                mAuth.createUserWithEmailAndPassword(user, pass)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                                String uid = mAuth.getCurrentUser().getUid();

                                Intent intent = new Intent(this, FillInformation_NTDanh.class);
                                intent.putExtra("user_uid", uid);
                                startActivity(intent);
                            } else {
                                Toast.makeText(this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }else {
                Toast.makeText(this, "Nhập mật khẩu khác nhau", Toast.LENGTH_SHORT).show();
            }


        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void signUpAccount_NTDanh(){

    }
}