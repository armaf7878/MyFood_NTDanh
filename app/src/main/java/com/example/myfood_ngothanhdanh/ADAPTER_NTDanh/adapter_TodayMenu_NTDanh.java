package com.example.myfood_ngothanhdanh.ADAPTER_NTDanh;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.Modle_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class adapter_TodayMenu_NTDanh extends RecyclerView.Adapter<adapter_TodayMenu_NTDanh.ViewHolder> {
    private List<food_NTDanh> food_ntDanhList;
    public adapter_TodayMenu_NTDanh(List<food_NTDanh> food_ntDanhList){
        this.food_ntDanhList = food_ntDanhList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtfoodName_NTDanh, txt_foodPrice_NTDanh;
        private ImageView img_FoodImg_NTDanh;
        private View background;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_FoodImg_NTDanh = itemView.findViewById(R.id.img_FoodImg_NTDanh);
            txtfoodName_NTDanh = itemView.findViewById(R.id.txtfoodName_NTDanh);
            txt_foodPrice_NTDanh = itemView.findViewById(R.id.txt_foodPrice_NTDanh);
            background = itemView.findViewById(R.id.background);
        }
    }
    @NonNull
    @Override
    public adapter_TodayMenu_NTDanh.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_todaymenu_ntdanh,  parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_TodayMenu_NTDanh.ViewHolder holder, int position) {
        food_NTDanh food_ntDanh = food_ntDanhList.get(position);
        holder.txtfoodName_NTDanh.setText(food_ntDanh.getFood_name());
        holder.txt_foodPrice_NTDanh.setText(String.valueOf(food_ntDanh.getFood_price()));
        holder.img_FoodImg_NTDanh.setImageResource(food_ntDanh.getFood_img());

        int color;
        switch (position % 5){
            case 0:
                color = Color.parseColor("#FFCDD2"); // đỏ nhạt
                break;
            case 1:
                color = Color.parseColor("#C8E6C9"); // xanh lá nhạt
                break;
            case 2:
                color = Color.parseColor("#BBDEFB"); // xanh dương nhạt
                break;
            case 3:
                color = Color.parseColor("#D1C4E9"); // tím nhạt
                break;
            default:
                color = Color.parseColor("#FFE0B2"); // cam nhạt
                break;
        }
        holder.background.setBackgroundResource(R.drawable.rounded);
        GradientDrawable drawable = (GradientDrawable) holder.background.getBackground();
        drawable.setColor(color);
    }

    @Override
    public int getItemCount() {
        return Math.min(food_ntDanhList.size(),5);
    }
}
