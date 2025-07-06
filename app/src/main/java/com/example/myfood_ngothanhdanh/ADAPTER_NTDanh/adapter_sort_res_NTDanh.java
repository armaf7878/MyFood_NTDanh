package com.example.myfood_ngothanhdanh.ADAPTER_NTDanh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfood_ngothanhdanh.Model_NTDanh.restaurant_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.restaurantwdistance;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class adapter_sort_res_NTDanh extends RecyclerView.Adapter<adapter_sort_res_NTDanh.ViewHolder> {

    private List<restaurantwdistance> list;

    public adapter_sort_res_NTDanh(List<restaurantwdistance> list) {
        this.list = list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageRes;
        TextView edtResName, edtAddress,distance_Res_NTDanh;
        Button btn_detail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageRes = itemView.findViewById(R.id.imageRes);
            edtResName = itemView.findViewById(R.id.edtResName);
            edtAddress = itemView.findViewById(R.id.edtAddress);
            distance_Res_NTDanh = itemView.findViewById(R.id.distance_Res_NTDanh);
//            btn_detail = itemView.findViewById(R.id.btn_detail);
        }
    }

    @NonNull
    @Override
    public adapter_sort_res_NTDanh.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_sort_res, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_sort_res_NTDanh.ViewHolder holder, int position) {
        restaurantwdistance restaurantwdistance = list.get(position);
        restaurant_NTDanh restaurant_ntDanh = restaurantwdistance.getRestaurant();
        Glide.with(holder.itemView.getContext()).load(restaurant_ntDanh.getRes_img()).into(holder.imageRes);
        holder.edtResName.setText(restaurant_ntDanh.getRes_name());
        holder.edtAddress.setText(restaurant_ntDanh.getRes_address());
        holder.distance_Res_NTDanh.setText(String.valueOf((int)(restaurantwdistance.getDistance()/1000)) + "Km");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
