package com.example.myfood_ngothanhdanh.ADAPTER_NTDanh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfood_ngothanhdanh.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class adapter_OrderApprove_NTDanh extends RecyclerView.Adapter<adapter_OrderApprove_NTDanh.ViewHolder> {
    private List<String> ListOrder_Item;
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public adapter_OrderApprove_NTDanh(List<String> listOrder_Item) {
        ListOrder_Item = listOrder_Item;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_foodName_NTDanh, txt_quantity_NTDanh;
        Button btn_Deny_NTDanh, btn_Approve_NTDanh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_foodName_NTDanh = itemView.findViewById(R.id.txt_foodName_NTDanh);
            txt_quantity_NTDanh = itemView.findViewById(R.id.txt_quantity_NTDanh);
            btn_Deny_NTDanh = itemView.findViewById(R.id.btn_Deny_NTDanh);
            btn_Approve_NTDanh = itemView.findViewById(R.id.btn_Approve_NTDanh);

        }
    }
    @NonNull
    @Override
    public adapter_OrderApprove_NTDanh.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_res_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_OrderApprove_NTDanh.ViewHolder holder, int position) {
        String cartID = ListOrder_Item.get(position);
        firestore.collection("Orders").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                String order_id = documentSnapshot.getId();
                firestore.collection("Orders").document(order_id).collection("OrderList").document(cartID).get().addOnSuccessListener(documentSnapshot1 -> {
                   holder.txt_foodName_NTDanh.setText(documentSnapshot1.getString("food_name"));
                   holder.txt_quantity_NTDanh.setText(String.valueOf(documentSnapshot1.getLong("quantity")));
                });
            }
        });
        holder.btn_Approve_NTDanh.setOnClickListener(view -> {
            firestore.collection("Orders").get().addOnSuccessListener(queryDocumentSnapshots -> {
                for (DocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    String order_id = documentSnapshot.getId();
                    firestore.collection("Orders").document(order_id).collection("OrderList").document(cartID).update("confirm_status", true).addOnSuccessListener(aVoid -> {
                        Toast.makeText(holder.itemView.getContext(), "Approve Successfully", Toast.LENGTH_SHORT).show();
                        ListOrder_Item.remove(cartID);
                        notifyDataSetChanged();
                    });
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return ListOrder_Item.size();
    }
}
