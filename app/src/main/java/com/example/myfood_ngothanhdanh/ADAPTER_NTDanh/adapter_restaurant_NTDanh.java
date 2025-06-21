package com.example.myfood_ngothanhdanh.ADAPTER_NTDanh;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh.Food_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.restaurant_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class adapter_restaurant_NTDanh extends RecyclerView.Adapter<adapter_restaurant_NTDanh.ViewHolder> {
    List<restaurant_NTDanh> restaurant_ntDanhList ;

    public adapter_restaurant_NTDanh(List<restaurant_NTDanh> restaurant_ntDanhList) {
        this.restaurant_ntDanhList = restaurant_ntDanhList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView edtResName, edtAddress;
        ImageView imageRes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            edtResName = itemView.findViewById(R.id.edtResName);
            edtAddress = itemView.findViewById(R.id.edtAddress);
            imageRes = itemView.findViewById(R.id.imageRes);
        }
    }
    @NonNull
    @Override
    public adapter_restaurant_NTDanh.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_restaurant_NTDanh.ViewHolder holder, int position) {
        restaurant_NTDanh restaurant_ntDanh = restaurant_ntDanhList.get(position);
        holder.edtAddress.setText(restaurant_ntDanh.getRes_address());
        holder.edtResName.setText(restaurant_ntDanh.getRes_name());
        holder.imageRes.setImageResource(restaurant_ntDanh.getRes_img());
        holder.itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("ResID", restaurant_ntDanh.getRes_id());
            Intent intent = new Intent(holder.itemView.getContext(), Food_NTDanh.class);
            intent.putExtras(bundle);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return restaurant_ntDanhList.size();
    }


}
