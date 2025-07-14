package com.example.myfood_ngothanhdanh.FRAGMENT_NTDanh;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_cart_NTDanh;
import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_food_ordered_NTDanh;
import com.example.myfood_ngothanhdanh.MainActivity;
import com.example.myfood_ngothanhdanh.Model_NTDanh.orders_NTDanh;
import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MyCart_3rd_NTDanh extends Fragment {
    private List<String> CartID_List = new ArrayList<>();
    private orders_NTDanh order_Info = new orders_NTDanh();
    private RecyclerView recycler_FoodList_NTDanh;
    private TextView txt_FullName_NTDanh, txt_UserPhone_NTDanh, txt_Address_NTDanh, txt_TotalPrice_NTDanh;
    private Button btn_NextStep_NTDanh;
    private adapter_food_ordered_NTDanh adapter_food_ordered_ntDanh;
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cart_3rd__n_t_danh, container, false);
        recycler_FoodList_NTDanh = view.findViewById(R.id.recycler_FoodList_NTDanh);
        txt_FullName_NTDanh = view.findViewById(R.id.txt_FullName_NTDanh);
        txt_UserPhone_NTDanh = view.findViewById(R.id.txt_UserPhone_NTDanh);
        txt_Address_NTDanh = view.findViewById(R.id.txt_Address_NTDanh);
        recycler_FoodList_NTDanh = view.findViewById(R.id.recycler_FoodList_NTDanh);
        txt_TotalPrice_NTDanh = view.findViewById(R.id.txt_TotalPrice_NTDanh);
        btn_NextStep_NTDanh = view.findViewById(R.id.btn_NextStep_NTDanh);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataFromBundle();
        loadUserInfo();
        loadFoodandTotal();
        btn_NextStep_NTDanh.setOnClickListener(view1 -> {createOrder_NTDanh();});
    }
    private void getDataFromBundle(){
        Bundle bundle = getArguments();
        if (bundle != null){
            order_Info = bundle.getParcelable("Order_Infor");
            CartID_List = bundle.getStringArrayList("CarID_List");
        }
    }
    private void loadUserInfo(){
        txt_FullName_NTDanh.setText(order_Info.getOrder_name());
        txt_UserPhone_NTDanh.setText(order_Info.getOrder_phone());
        txt_Address_NTDanh.setText(order_Info.getOrder_address());
    }
    private void loadFoodandTotal(){
        adapter_food_ordered_ntDanh = new adapter_food_ordered_NTDanh(CartID_List, total -> {
            txt_TotalPrice_NTDanh.setText(String.format("%,d",total) + "đ");
            order_Info.setTotal(Double.valueOf(total));
        });
        recycler_FoodList_NTDanh.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_FoodList_NTDanh.setAdapter(adapter_food_ordered_ntDanh);
    }
    private void createOrder_NTDanh(){
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String datetime = format.format(currentDate);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        Map<String, Object> order = new HashMap<>();
        order.put("order_name", order_Info.getOrder_name());
        order.put("order_phone",order_Info.getOrder_phone());
        order.put("order_address", order_Info.getOrder_address());
        order.put("order_date", datetime);
        order.put("order_address_long", order_Info.getOrder_address_long());
        order.put("order_address_lat", order_Info.getOrder_address_lat());
        order.put("user_id", uid);
        order.put("total", order_Info.getTotal());
        DocumentReference documentReference = firestore.collection("Orders").document();
        documentReference.set(order).addOnSuccessListener(aVoid ->{
            Toast.makeText(getContext(), "Ordered successfully", Toast.LENGTH_SHORT).show();
            createOrder_Item(documentReference);
        });
    }

    private void createOrder_Item(DocumentReference documentReference) {

        for (String CartID : CartID_List){
            DocumentReference Cart_Doc = firestore.collection("Cart").document(CartID);
            Cart_Doc.get().addOnSuccessListener(documentSnapshot -> {
                long quantity = documentSnapshot.getLong("quantity");
                String food_id = documentSnapshot.getString("food_id");
                String cart_id = documentSnapshot.getId();
                firestore.collection("Foods").document(food_id).get().addOnSuccessListener(documentSnapshot1 -> {
                    Map<String, Object> order_item = new HashMap<>();
                    order_item.put("quantity", quantity);
                    order_item.put("food_id", food_id);
                    order_item.put("food_name", documentSnapshot1.getString("food_name"));
                    order_item.put("confirm_status", false);
                    order_item.put("res_id", documentSnapshot1.getString("res_id"));
                    documentReference.collection("OrderList").document(cart_id).set(order_item).addOnSuccessListener(aVoid -> {
                        Log.d("Collection", "Added successfully");
                        Cart_Doc.delete().addOnSuccessListener(aVoid1 -> {
                            Log.d("Collection", "Delete Cart Item Succesfully");
                            count += 1;
                            if (count == CartID_List.size()){
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                requireActivity().finish();
                            }
                        });
                    });
                });
            });
        }
    }
}