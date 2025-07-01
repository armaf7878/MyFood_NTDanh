package com.example.myfood_ngothanhdanh.FRAGMENT_NTDanh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfood_ngothanhdanh.ADAPTER_NTDanh.adapter_cart_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.cartDAO_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.cart_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class MyCart_NTDanh extends Fragment {

    private Button btn_Order_NTDanh;
    private RecyclerView recycler_cart_NTDanh;
    private adapter_cart_NTDanh adapterCartNtDanh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_my_cart__n_t_danh, container, false);
       btn_Order_NTDanh = view.findViewById(R.id.btn_Order_NTDanh);
       recycler_cart_NTDanh = view.findViewById(R.id.recycler_cart_NTDanh);
        loadCartItem_NTDanh();
       btn_Order_NTDanh.setOnClickListener(v -> {
           adapterCartNtDanh.checkListFood_NTDanh(getContext());
        });
        return view;
    }
    private void loadCartItem_NTDanh(){
        cartDAO_NTDanh cartDAO_ntDanh = new cartDAO_NTDanh(getContext());
        List<cart_NTDanh> cartList = cartDAO_ntDanh.getAll_NTDanh();
        recycler_cart_NTDanh.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterCartNtDanh = new adapter_cart_NTDanh(cartList);
        recycler_cart_NTDanh.setAdapter(adapterCartNtDanh);
    }
}