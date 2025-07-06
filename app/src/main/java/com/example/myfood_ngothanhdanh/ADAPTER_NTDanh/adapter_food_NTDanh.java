package com.example.myfood_ngothanhdanh.ADAPTER_NTDanh;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh.Food_Detail_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class adapter_food_NTDanh extends RecyclerView.Adapter<adapter_food_NTDanh.ViewHolder> {
    private List<food_NTDanh> food_ntDanhList;
    public adapter_food_NTDanh(List<food_NTDanh> food_ntDanhList){
        this.food_ntDanhList = food_ntDanhList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_ResImg_NTDanh ;
        TextView txt_FoodName_NTDanh, txt_FoodPrice_NTDanh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_ResImg_NTDanh = itemView.findViewById(R.id.img_ResImg_NTDanh);
            txt_FoodName_NTDanh = itemView.findViewById(R.id.txt_FoodName_NTDanh);
            txt_FoodPrice_NTDanh = itemView.findViewById(R.id.txt_FoodPrice_NTDanh);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_ntdanh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_food_NTDanh.ViewHolder holder, int position) {
        food_NTDanh food_ntDanh = food_ntDanhList.get(position);
        Glide.with(holder.itemView.getContext()).load(food_ntDanh.getFood_img()).into(holder.img_ResImg_NTDanh);
        holder.txt_FoodName_NTDanh.setText(food_ntDanh.getFood_name());
        holder.txt_FoodPrice_NTDanh.setText(String.format("%,d", food_ntDanh.getFood_price().intValue()));
        holder.itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("FoodID", food_ntDanh.getFood_id());
            Intent intent = new Intent(holder.itemView.getContext(), Food_Detail_NTDanh.class);
            intent.putExtras(bundle);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return food_ntDanhList.size();
    }


}
