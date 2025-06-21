package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfood_ngothanhdanh.DAO_NTDanh.userDAO_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.user_NTDanh;
import com.example.myfood_ngothanhdanh.R;

public class Register_NTDanh extends AppCompatActivity {
    private Button btnDangKy;
    private EditText edtUserName, edtPass, edtCheckPas, edtFullName;
    private String username, pass, fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_ntdanh);
        btnDangKy = findViewById(R.id.btnDangKy);
        edtUserName = findViewById(R.id.edtUserName);
        edtPass = findViewById(R.id.edtPass);
        edtCheckPas = findViewById(R.id.edtCheckPass);
        edtFullName = findViewById(R.id.edtFullName);
        btnDangKy.setOnClickListener(view -> {
            username = edtUserName.getText().toString().trim();
            pass = edtPass.getText().toString().trim();
            fullname = edtFullName.getText().toString();
            if (!pass.equals(edtCheckPas.getText().toString().trim())){
                Toast.makeText(this, "Mật khẩu khác nhau", Toast.LENGTH_SHORT).show();
            }if (pass.equals(edtCheckPas.getText().toString().trim()) && !username.isEmpty() && !fullname.isEmpty()){
                user_NTDanh user_ntDanh = new user_NTDanh();
                user_ntDanh.setUser_fullname(fullname);
                user_ntDanh.setUser_name(username);
                user_ntDanh.setUser_pass(pass);
                userDAO_NTDanh userDAO_ntDanh = new userDAO_NTDanh(this);
                long check = userDAO_ntDanh.insert_NTDanh(user_ntDanh);
                if (check != -1){
                    Toast.makeText(this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}