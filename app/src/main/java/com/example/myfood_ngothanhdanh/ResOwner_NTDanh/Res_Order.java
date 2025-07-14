package com.example.myfood_ngothanhdanh.ResOwner_NTDanh;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_OrderApprove_NTDanh;
import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Res_Order extends AppCompatActivity {
    private RecyclerView recycler_order;
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private String resId;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private List<String> ListOrder_Item = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_res_order);
        recycler_order = findViewById(R.id.recycler_order);
        getResID();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void getResID(){
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        firestore.collection("Restaurants").whereEqualTo("owner_id", uid).get().addOnSuccessListener(queryDocumentSnapshots -> {
           for (DocumentSnapshot documentSnapshot: queryDocumentSnapshots){
               resId = documentSnapshot.getId();
               Log.d("res_id", resId);
               getOrder();
           }
        });
    }
    private void getOrder(){
        firestore.collection("Orders").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                String order_id = documentSnapshot.getId();
                firestore.collection("Orders").document(order_id).collection("OrderList").whereEqualTo("res_id", resId).get().addOnSuccessListener(queryDocumentSnapshots1 -> {
                    for (DocumentSnapshot documentSnapshot1 : queryDocumentSnapshots1){
                        if (!documentSnapshot1.getBoolean("confirm_status")){
                            ListOrder_Item.add(documentSnapshot1.getId());
                            Log.d("ListOrder_Item", documentSnapshot1.getId());
                        }
                    }
                    adapter_OrderApprove_NTDanh adapter = new adapter_OrderApprove_NTDanh(ListOrder_Item);
                    recycler_order.setLayoutManager(new LinearLayoutManager(this));
                    recycler_order.setAdapter(adapter);
                });
            }
        });
    }
}