package com.example.myfood_ngothanhdanh.ADAPTER_NTDanh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.DAO_NTDanh.foodDAO_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.cart_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class adapter_cart_NTDanh extends RecyclerView.Adapter<adapter_cart_NTDanh.ViewHolder> {
    private List<cart_NTDanh> cart_ntDanhList;
    public adapter_cart_NTDanh(List<cart_NTDanh> cart_ntDanhList){
        this.cart_ntDanhList = cart_ntDanhList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox_NTDanh;
        ImageView img_FoodImg_NTDanh;
        TextView txt_FoodName_NTDanh, txt_quantity_NTDanh, txt_price_NTDanh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox_NTDanh = itemView.findViewById(R.id.checkBox_NTDanh);
            img_FoodImg_NTDanh = itemView.findViewById(R.id.img_FoodImg_NTDanh);
            txt_FoodName_NTDanh = itemView.findViewById(R.id.txt_FoodName_NTDanh);
            txt_quantity_NTDanh = itemView.findViewById(R.id.txt_quantity_NTDanh);
            txt_price_NTDanh = itemView.findViewById(R.id.txt_price_NTDanh);
        }
    }
    @NonNull
    @Override
    public adapter_cart_NTDanh.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_ntdanh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_cart_NTDanh.ViewHolder holder, int position) {
        cart_NTDanh cartNtDanh = cart_ntDanhList.get(position);
        holder.txt_quantity_NTDanh.setText(String.valueOf(cartNtDanh.getQuantity()));
        foodDAO_NTDanh foodDAO_ntDanh = new foodDAO_NTDanh(holder.itemView.getContext());
        food_NTDanh food_ntDanh = foodDAO_ntDanh.getFoodByFoodID_NTDanh(cartNtDanh.getFoodID());
        holder.txt_FoodName_NTDanh.setText(food_ntDanh.getFood_name());
        holder.txt_price_NTDanh.setText(String.valueOf(food_ntDanh.getFood_price() * cartNtDanh.getQuantity()));
        holder.img_FoodImg_NTDanh.setImageResource(food_ntDanh.getFood_img());
    }

    @Override
    public int getItemCount() {
        return cart_ntDanhList.size();
    }


}
