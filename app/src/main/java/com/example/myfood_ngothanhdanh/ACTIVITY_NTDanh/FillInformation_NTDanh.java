package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfood_ngothanhdanh.MainActivity;
import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FillInformation_NTDanh extends AppCompatActivity {
    private Button btnDangKy;
    private EditText edtPhone, edtFullName;
    private String fullname, gender, phone;
    private RadioButton radio_male, radio_female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fillinformation_ntdanh);
        btnDangKy = findViewById(R.id.btnDangKy);
        edtPhone = findViewById(R.id.edtPhone);
        radio_male = findViewById(R.id.radio_male);
        radio_female = findViewById(R.id.radio_female);
        edtFullName = findViewById(R.id.edtFullName);
        btnDangKy.setOnClickListener(view -> {
            if (radio_male.isChecked()){
                gender = "male";
            }else {gender = "female";};
            fullname = edtFullName.getText().toString();
            phone = edtPhone.getText().toString();

            if (fullname.isEmpty() || phone.isEmpty() || !radio_male.isChecked() && !radio_female.isChecked()){
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = getIntent();
                String uid = intent.getStringExtra("user_uid");
                if (!uid.isEmpty()){
                    createNewAccount(uid, fullname, phone, gender);
                }else {
                    Toast.makeText(this, "Thông tin UID trống", Toast.LENGTH_SHORT).show();
                }

            }


        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void createNewAccount(String uid, String fullname, String phone, String gender) {
        FirebaseFirestore db =  FirebaseFirestore.getInstance();
        Map<String, Object> newUser = new HashMap<>();
        newUser.put("fullname", fullname);
        newUser.put("phone", phone);
        newUser.put("gender", gender);
        newUser.put("role", "customer");
        db.collection("Users").document(uid).set(newUser).addOnSuccessListener(aVoid -> {
            Log.d("Firestore", "User created successfully");
            Intent intent = new Intent(FillInformation_NTDanh.this, MainActivity.class);
            startActivity(intent);
        }).addOnFailureListener(e -> {
            Log.e("Firestore", "Failed to create user", e);
        });
    }
}