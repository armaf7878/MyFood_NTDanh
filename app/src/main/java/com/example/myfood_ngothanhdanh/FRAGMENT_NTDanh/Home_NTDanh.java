package com.example.myfood_ngothanhdanh.FRAGMENT_NTDanh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_TodayMenu_NTDanh;
import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_categories_NTDanh;
import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_restaurant_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.cate_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.restaurant_NTDanh;
import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class Home_NTDanh extends Fragment {
    private RecyclerView RecyclerRes, recycler_todayMenu_NTDanh, recycler_categories_NTDanh;
    private adapter_restaurant_NTDanh adapterRestaurantNtDanh;
    private List<restaurant_NTDanh> restaurant_ntDanhList = new ArrayList<>();
    private adapter_TodayMenu_NTDanh adapterTodayMenuNtDanh;
    private adapter_categories_NTDanh adapterCategoriesNtDanh;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Home_NTDanh() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerRes = view.findViewById(R.id.RecyclerRes);
        loadRes_NTDanh(RecyclerRes);

        recycler_todayMenu_NTDanh = view.findViewById(R.id.recycler_todayMenu_NTDanh);
        loadTodayMenu(recycler_todayMenu_NTDanh);

        recycler_categories_NTDanh = view.findViewById(R.id.recycler_categories_NTDanh);
        loadCate(recycler_categories_NTDanh);

        return view;
    }

    private void loadRes_NTDanh( RecyclerView RecyclerRes){

        db.collection("Restaurants").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                restaurant_NTDanh restaurant_ntDanh = new restaurant_NTDanh();
                restaurant_ntDanh.setRes_id(documentSnapshot.getId());
                restaurant_ntDanh.setRes_name(documentSnapshot.getString("res_name"));
                restaurant_ntDanh.setOwner_id(documentSnapshot.getString("owner_id"));
                restaurant_ntDanh.setRes_address(documentSnapshot.getString("res_address"));
                restaurant_ntDanh.setRes_phone(documentSnapshot.getString("res_phone"));
                restaurant_ntDanh.setRes_img(documentSnapshot.getString("res_img"));
                Log.d("Image1", documentSnapshot.getString("res_img"));
                restaurant_ntDanhList.add(restaurant_ntDanh);
                Log.d("restaurant_ntDanhList", String.valueOf(restaurant_ntDanhList.size()));
            }
            RecyclerRes.setLayoutManager(new LinearLayoutManager(getContext()));
            adapterRestaurantNtDanh = new adapter_restaurant_NTDanh(restaurant_ntDanhList);
            RecyclerRes.setAdapter(adapterRestaurantNtDanh);
        }).addOnFailureListener(e -> {
            Log.e("Restaurant", "Take Date", e);
        });
    }

    private void loadTodayMenu(RecyclerView recycler_todayMenu_NTDanh){

        List<food_NTDanh> food_ntDanhList = new ArrayList<>();
        db.collection("Foods").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                food_NTDanh food_ntDanh = new food_NTDanh();
                food_ntDanh.setFood_id(documentSnapshot.getId());
                food_ntDanh.setRes_id(documentSnapshot.getString("res_id"));
                food_ntDanh.setCate_id(documentSnapshot.getString("cate_id"));
                food_ntDanh.setFood_name(documentSnapshot.getString("food_name"));
                food_ntDanh.setFood_price(documentSnapshot.getDouble("food_price"));
                food_ntDanh.setFood_img(documentSnapshot.getString("food_img"));
                food_ntDanh.setFood_des(documentSnapshot.getString("food_des"));
                food_ntDanhList.add(food_ntDanh);
            }
            adapterTodayMenuNtDanh = new adapter_TodayMenu_NTDanh(food_ntDanhList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recycler_todayMenu_NTDanh.setLayoutManager(layoutManager);
            recycler_todayMenu_NTDanh.setAdapter(adapterTodayMenuNtDanh);
        }).addOnFailureListener(e -> {
            Log.e("loadTodayMenu", "Error", e);
        });
    }

    private void loadCate(RecyclerView recycler_categories_NTDanh){
        List<cate_NTDanh> cate_ntDanhList = new ArrayList<>();
        db.collection("Categories").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                cate_NTDanh cate_ntDanh = new cate_NTDanh();
                cate_ntDanh.setCate_id(documentSnapshot.getId());
                cate_ntDanh.setCate_name(documentSnapshot.getString("cate_name"));
                cate_ntDanh.setCate_img(documentSnapshot.getString("cate_img"));
                cate_ntDanhList.add(cate_ntDanh);
            }
            adapterCategoriesNtDanh = new adapter_categories_NTDanh(cate_ntDanhList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            recycler_categories_NTDanh.setLayoutManager(linearLayoutManager);
            recycler_categories_NTDanh.setAdapter(adapterCategoriesNtDanh);
        }).addOnFailureListener(e -> {
            Log.e("loadCate", "Error", e);
        });
    }
}