package com.example.myfood_ngothanhdanh.ADAPTER_NTDanh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.API_INTERFACE.OnFoodChangedListener;
import com.example.myfood_ngothanhdanh.Model_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.R;
import com.example.myfood_ngothanhdanh.ResOwner_NTDanh.Res_Food_Edit;

import java.util.ArrayList;
import java.util.List;

public class adapter_food_management extends RecyclerView.Adapter<adapter_food_management.ViewHolder> {
    private List<food_NTDanh> food_ntDanhList;
    private OnFoodChangedListener listener;
    private Context context;

    public adapter_food_management(Context context, List<food_NTDanh> food_ntDanhList, OnFoodChangedListener listener) {
        this.food_ntDanhList = food_ntDanhList;
        this.listener = listener;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_foodName_NTDanh;
        private Button btn_AddFood_NTDanh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_foodName_NTDanh = itemView.findViewById(R.id.txt_foodName_NTDanh);
            btn_AddFood_NTDanh = itemView.findViewById(R.id.btn_AddFood_NTDanh);
        }
    }
    @NonNull
    @Override
    public adapter_food_management.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_food_management, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_food_management.ViewHolder holder, int position) {
        food_NTDanh food_ntDanh = food_ntDanhList.get(position);
        holder.txt_foodName_NTDanh.setText(food_ntDanh.getFood_name());
        holder.btn_AddFood_NTDanh.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), Res_Food_Edit.class);
            intent.putExtra("food_ntDanh", food_ntDanh);
            ((Activity) context).startActivityForResult(intent, 1001);
        });
    }

    @Override
    public int getItemCount() {
        return food_ntDanhList.size();
    }
}
