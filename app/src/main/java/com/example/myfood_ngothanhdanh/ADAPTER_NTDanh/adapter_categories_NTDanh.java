package com.example.myfood_ngothanhdanh.ADAPTER_NTDanh;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfood_ngothanhdanh.ACTIVITY_NTDanh.FoodInCate;
import com.example.myfood_ngothanhdanh.Model_NTDanh.cate_NTDanh;
import com.example.myfood_ngothanhdanh.R;

import java.util.List;

public class adapter_categories_NTDanh extends RecyclerView.Adapter<adapter_categories_NTDanh.ViewHolder> {
    List<cate_NTDanh> cate_ntDanhList;

    public adapter_categories_NTDanh(List<cate_NTDanh> cate_ntDanhList){
        this.cate_ntDanhList = cate_ntDanhList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_CateName_NTDanh;
        private ImageView img_CateImg_NTDanh;
        private LinearLayout background;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_CateName_NTDanh = itemView.findViewById(R.id.txt_CateName_NTDanh);
            img_CateImg_NTDanh  = itemView.findViewById(R.id.img_CateImg_NTDanh);
            background = itemView.findViewById(R.id.background);
        }
    }
    @NonNull
    @Override
    public adapter_categories_NTDanh.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_categories_ntdanh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_categories_NTDanh.ViewHolder holder, int position) {
        cate_NTDanh cate_ntDanh = cate_ntDanhList.get(position);
        holder.txt_CateName_NTDanh.setText(cate_ntDanh.getCate_name());
        Glide.with(holder.itemView.getContext()).load(cate_ntDanh.getCate_img()).into(holder.img_CateImg_NTDanh);
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

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), FoodInCate.class);
            intent.putExtra("cate_id", cate_ntDanh.getCate_id());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cate_ntDanhList.size();
    }
}
