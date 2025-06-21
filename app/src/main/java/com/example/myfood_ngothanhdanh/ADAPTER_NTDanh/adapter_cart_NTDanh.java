package com.example.myfood_ngothanhdanh.ADAPTER_NTDanh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.Modle_NTDanh.cart_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class adapter_cart_NTDanh extends RecyclerView.Adapter<adapter_cart_NTDanh.ViewHolder> {
    private List<cart_NTDanh> cart_ntDanhList;
    public adapter_cart_NTDanh(List<cart_NTDanh> cart_ntDanhList){
        this.cart_ntDanhList = cart_ntDanhList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
