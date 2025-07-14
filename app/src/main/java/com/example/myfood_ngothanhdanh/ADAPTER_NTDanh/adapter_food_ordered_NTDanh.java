package com.example.myfood_ngothanhdanh.ADAPTER_NTDanh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.API_INTERFACE.OnTotalCalculatedListener;
import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class adapter_food_ordered_NTDanh extends RecyclerView.Adapter<adapter_food_ordered_NTDanh.ViewHolder> {
    private List<String> cartIDList;
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private long Total;
    private OnTotalCalculatedListener listener;
    private int loadedCount = 0;

    public adapter_food_ordered_NTDanh(List<String> cartIDList, OnTotalCalculatedListener listener) {
        this.cartIDList = cartIDList;
        this.listener =  listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_foodName_NTDanh, txt_foodQuantiy_NTDanh, txt_subTotal_NTDanh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_foodName_NTDanh = itemView.findViewById(R.id.txt_foodName_NTDanh);
            txt_foodQuantiy_NTDanh = itemView.findViewById(R.id.txt_foodQuantiy_NTDanh);
            txt_subTotal_NTDanh = itemView.findViewById(R.id.txt_subTotal_NTDanh);


        }
    }

    @NonNull
    @Override
    public adapter_food_ordered_NTDanh.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_foodordered, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_food_ordered_NTDanh.ViewHolder holder, int position) {
        String cartID = cartIDList.get(position);
        firestore.collection("Cart").document(cartID).get().addOnSuccessListener(documentSnapshot -> {
            String foodID = documentSnapshot.getString("food_id");
            holder.txt_foodQuantiy_NTDanh.setText(String.valueOf(documentSnapshot.getLong("quantity")));
            long quantity = documentSnapshot.getLong("quantity");
            firestore.collection("Foods").document(foodID).get().addOnSuccessListener(documentSnapshot1 -> {
                holder.txt_foodName_NTDanh.setText(documentSnapshot1.getString("food_name"));
                holder.txt_subTotal_NTDanh.setText(String.format("%,d",documentSnapshot1.getLong("food_price") * quantity));
                Total += documentSnapshot1.getLong("food_price") * quantity;
                loadedCount++;
                if (loadedCount == cartIDList.size() && listener != null){
                    listener.onTotalCalculated(Total);
                }

            });
        });
    }

    @Override
    public int getItemCount() {
        return cartIDList.size();
    }
}
