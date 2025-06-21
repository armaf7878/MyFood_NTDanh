package com.example.myfood_ngothanhdanh.DAO_NTDanh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfood_ngothanhdanh.Database_NTDanh.dbHelper_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.cart_NTDanh;

import java.util.ArrayList;
import java.util.List;

public class cartDAO_NTDanh {
    private SQLiteDatabase db;
    public cartDAO_NTDanh(Context context){
        dbHelper_NTDanh dbHelper = new dbHelper_NTDanh(context);
        db = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    private List<cart_NTDanh> get(String sql,String... selectArgs){
        List<cart_NTDanh> cart_ntDanhList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            cart_NTDanh cartNtDanh = new cart_NTDanh();
            cartNtDanh.setCartID(cursor.getInt(cursor.getColumnIndexOrThrow("cart_id")));
            cartNtDanh.setFoodID(cursor.getInt(cursor.getColumnIndexOrThrow("food_id")));
            cartNtDanh.setUserID(cursor.getInt(cursor.getColumnIndexOrThrow("user_id")));
            cartNtDanh.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow("quantity")));
            cart_ntDanhList.add(cartNtDanh);
        }
        cursor.close();
        return cart_ntDanhList;
    }

    public
}
