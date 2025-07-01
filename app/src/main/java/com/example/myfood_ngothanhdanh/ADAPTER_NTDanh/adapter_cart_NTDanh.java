package com.example.myfood_ngothanhdanh.ADAPTER_NTDanh;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh.Order_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.cartDAO_NTDanh;
import com.example.myfood_ngothanhdanh.DAO_NTDanh.foodDAO_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.cart_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.ArrayList;
import java.util.List;

public class adapter_cart_NTDanh extends RecyclerView.Adapter<adapter_cart_NTDanh.ViewHolder> {
    private List<cart_NTDanh> cart_ntDanhList;
    private List<Integer> checkListFoodID_NTDanh = new ArrayList<>();;
    public adapter_cart_NTDanh(List<cart_NTDanh> cart_ntDanhList){
        this.cart_ntDanhList = cart_ntDanhList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox_NTDanh;
        ImageView img_FoodImg_NTDanh;
        TextView txt_FoodName_NTDanh, txt_quantity_NTDanh, txt_price_NTDanh, btn_descrease_NTDanh, btn_incresase_NTDanh;
        Button btn_delete_NTDanh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox_NTDanh = itemView.findViewById(R.id.checkBox_NTDanh);
            img_FoodImg_NTDanh = itemView.findViewById(R.id.img_FoodImg_NTDanh);
            txt_FoodName_NTDanh = itemView.findViewById(R.id.txt_FoodName_NTDanh);
            txt_quantity_NTDanh = itemView.findViewById(R.id.txt_quantity_NTDanh);
            txt_price_NTDanh = itemView.findViewById(R.id.txt_price_NTDanh);
            btn_descrease_NTDanh = itemView.findViewById(R.id.btn_descrease_NTDanh);
            btn_incresase_NTDanh = itemView.findViewById(R.id.btn_incresase_NTDanh);
            btn_delete_NTDanh = itemView.findViewById(R.id.btn_delete_NTDanh);
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
        cartDAO_NTDanh cartDAO_ntDanh = new cartDAO_NTDanh(holder.itemView.getContext());
        holder.txt_quantity_NTDanh.setText(String.valueOf(cartNtDanh.getQuantity()));
        foodDAO_NTDanh foodDAO_ntDanh = new foodDAO_NTDanh(holder.itemView.getContext());
        food_NTDanh food_ntDanh = foodDAO_ntDanh.getFoodByFoodID_NTDanh(cartNtDanh.getFoodID());
        holder.txt_FoodName_NTDanh.setText(food_ntDanh.getFood_name());
        holder.txt_price_NTDanh.setText(String.format("%,d",(int)(food_ntDanh.getFood_price() * cartNtDanh.getQuantity())));
        holder.img_FoodImg_NTDanh.setImageResource(food_ntDanh.getFood_img());

        holder.checkBox_NTDanh.setOnClickListener(view -> {
            if (holder.checkBox_NTDanh.isChecked()){
                checkListFoodID_NTDanh.add(cartNtDanh.getCartID());
            }else{
                checkListFoodID_NTDanh.remove(Integer.valueOf(cartNtDanh.getCartID()));
            }
        });

        holder.btn_delete_NTDanh.setOnClickListener(view -> {
            int check = cartDAO_ntDanh.delete_cartByID_NTDanh(cartNtDanh.getCartID());
            if (check > 0 ) {
                Toast.makeText(holder.itemView.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                cart_ntDanhList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cart_ntDanhList.size());
            }else {
                Toast.makeText(holder.itemView.getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }

        });

        holder.btn_incresase_NTDanh.setOnClickListener(view -> {
            int quantity = Integer.parseInt(holder.txt_quantity_NTDanh.getText().toString()) + 1;
            int check = cartDAO_ntDanh.update_cartQuantityByID_NTDanh(cartNtDanh.getCartID(), quantity);
            if (check > 0 ){
                holder.txt_quantity_NTDanh.setText(String.valueOf(quantity));
                holder.txt_price_NTDanh.setText(String.format("%,d",(int)(food_ntDanh.getFood_price() * quantity)));
            }else{
                Toast.makeText(holder.itemView.getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }

        });

        holder.btn_descrease_NTDanh.setOnClickListener(view -> {
            if (Integer.parseInt(holder.txt_quantity_NTDanh.getText().toString()) > 1){
                int quantity = Integer.parseInt(holder.txt_quantity_NTDanh.getText().toString()) - 1;
                int check = cartDAO_ntDanh.update_cartQuantityByID_NTDanh(cartNtDanh.getCartID(), quantity);
                if (check > 0 ){
                    holder.txt_quantity_NTDanh.setText(String.valueOf(quantity));
                    holder.txt_price_NTDanh.setText(String.format("%,d",(int)(food_ntDanh.getFood_price() * quantity)));
                    Toast.makeText(holder.itemView.getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(holder.itemView.getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(holder.itemView.getContext(), "Tối thiểu", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cart_ntDanhList.size();
    }

    public void checkListFood_NTDanh(Context context){
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("CartID", new ArrayList<>(checkListFoodID_NTDanh));
        Intent intent = new Intent(context, Order_NTDanh.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
