package com.example.myfood_ngothanhdanh.DAO_NTDanh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfood_ngothanhdanh.Database_NTDanh.dbHelper_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.order_detail_NTDanh;

import java.util.ArrayList;
import java.util.List;

public class order_detailDAO_NTDanh {
    private SQLiteDatabase db;
    public order_detailDAO_NTDanh(Context context){
        dbHelper_NTDanh dbHelper = new dbHelper_NTDanh(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    private List<order_detail_NTDanh> get (String sql, String... selectArgs){
        List<order_detail_NTDanh> orderDetailNtDanhList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            order_detail_NTDanh orderDetailNtDanh = new order_detail_NTDanh();
            orderDetailNtDanh.setOrder_id(cursor.getInt(cursor.getColumnIndexOrThrow("order_id")));
            orderDetailNtDanh.setFood_id(cursor.getInt(cursor.getColumnIndexOrThrow("food_id")));
            orderDetailNtDanh.setOrder_detail_size(cursor.getString(cursor.getColumnIndexOrThrow("order_detail_size")));
            orderDetailNtDanh.setOrder_detail_food(cursor.getString(cursor.getColumnIndexOrThrow("order_detail_food")));
            orderDetailNtDanh.setOrder_detail_quantity(cursor.getInt(cursor.getColumnIndexOrThrow("order_detail_quantity")));
            orderDetailNtDanhList.add(orderDetailNtDanh);
        }
        cursor.close();
        return orderDetailNtDanhList;
    }

    public List<order_detail_NTDanh> getAll(){
        String sql = "SELECT * FROM order_detail";
        return get(sql);
    }
}
