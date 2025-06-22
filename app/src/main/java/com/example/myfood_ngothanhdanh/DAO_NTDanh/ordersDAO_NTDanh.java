package com.example.myfood_ngothanhdanh.DAO_NTDanh;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfood_ngothanhdanh.Database_NTDanh.dbHelper_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.orders_NTDanh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ordersDAO_NTDanh {
    private SQLiteDatabase db;
    public ordersDAO_NTDanh(Context context){
        dbHelper_NTDanh dbHelper = new dbHelper_NTDanh(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    private List<orders_NTDanh> get (String sql, String... selectArgs){
        List<orders_NTDanh> orders_ntDanhList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            orders_NTDanh orders_ntDanh = new orders_NTDanh();
            orders_ntDanh.setOrder_id(cursor.getInt(cursor.getColumnIndexOrThrow("order_id")));
            orders_ntDanh.setOrder_address(cursor.getString(cursor.getColumnIndexOrThrow("order_address")));
            orders_ntDanh.setOrder_date(cursor.getString(cursor.getColumnIndexOrThrow("order_date")));
            orders_ntDanh.setUser_ID(cursor.getInt(cursor.getColumnIndexOrThrow("user_ID")));
            orders_ntDanh.setOrder_name(cursor.getString(cursor.getColumnIndexOrThrow("order_name")));
            orders_ntDanh.setOrder_phone(cursor.getString(cursor.getColumnIndexOrThrow("order_phone")));
            orders_ntDanh.setOrder_status(cursor.getString(cursor.getColumnIndexOrThrow("order_status")));
            orders_ntDanhList.add(orders_ntDanh);
        }
        cursor.close();
        return orders_ntDanhList;
    }

    public List<orders_NTDanh> getAll(){
        String sql = "SELECT * FROM orders";
        return get(sql);
    }

    public long insert_NTDanh(orders_NTDanh orders_ntDanh){
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_address", orders_ntDanh.getOrder_address());
        contentValues.put("order_date", orders_ntDanh.getOrder_date());
        contentValues.put("user_ID", orders_ntDanh.getUser_ID());
        contentValues.put("order_name", orders_ntDanh.getOrder_name());
        contentValues.put("order_phone", orders_ntDanh.getOrder_phone());
        contentValues.put("order_status", orders_ntDanh.getOrder_status());

        return db.insert("orders", null, contentValues);
    }
}
