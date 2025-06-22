package com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_cart_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.cartDAO_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.cart_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class Cart_NTDanh extends AppCompatActivity {
    private Button btn_Order_NTDanh;
    private RecyclerView recycler_cart_NTDanh;
    private adapter_cart_NTDanh adapterCartNtDanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_ntdanh);
        btn_Order_NTDanh = findViewById(R.id.btn_Order_NTDanh);
        recycler_cart_NTDanh = findViewById(R.id.recycler_cart_NTDanh);
        cartDAO_NTDanh cartDAO_ntDanh = new cartDAO_NTDanh(this);
        List<cart_NTDanh> cartList = cartDAO_ntDanh.getAll_NTDanh();
        recycler_cart_NTDanh.setLayoutManager(new LinearLayoutManager(this));
        adapterCartNtDanh = new adapter_cart_NTDanh(cartList);
        recycler_cart_NTDanh.setAdapter(adapterCartNtDanh);
        btn_Order_NTDanh.setOnClickListener(view -> {
            adapterCartNtDanh.checkListFood_NTDanh(this);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}