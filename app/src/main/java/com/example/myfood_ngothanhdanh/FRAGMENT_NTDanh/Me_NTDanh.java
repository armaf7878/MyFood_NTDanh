package com.example.myfood_ngothanhdanh.FRAGMENT_NTDanh;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh.Account_Information;
import com.example.myfood_ngothanhdanh.R;

public class Me_NTDanh extends Fragment {

    private LinearLayout btn_Account_NTDanh, btn_Location_NTDanh, btn_Package_NTDanh;
    private Button btn_logOut_NTDanh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me__n_t_danh, container, false);
        btn_Account_NTDanh = view.findViewById(R.id.btn_Account_NTDanh);
        btn_Location_NTDanh = view.findViewById(R.id.btn_Location_NTDanh);
        btn_Package_NTDanh = view.findViewById(R.id.btn_Package_NTDanh);
        btn_logOut_NTDanh = view.findViewById(R.id.btn_logOut_NTDanh);

        btn_Account_NTDanh.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), Account_Information.class);
            startActivity(intent);
        });
        return view;
    }
}