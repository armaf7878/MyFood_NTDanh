package com.example.myfood_ngothanhdanh.DAO_NTDanh;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfood_ngothanhdanh.Database_NTDanh.dbHelper_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.restaurant_NTDanh;

import java.util.ArrayList;
import java.util.List;

public class restaurantDAO_NTDanh {
    private SQLiteDatabase db;
    public restaurantDAO_NTDanh(Context context){
        dbHelper_NTDanh dbHelper = new dbHelper_NTDanh(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    private List<restaurant_NTDanh> get (String sql, String... selectArgs){
        List<restaurant_NTDanh> restaurant_ntDanhList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            restaurant_NTDanh restaurant_ntDanh = new restaurant_NTDanh();
            restaurant_ntDanh.setRes_id(cursor.getString(cursor.getColumnIndexOrThrow("res_id")));
            restaurant_ntDanh.setRes_name(cursor.getString(cursor.getColumnIndexOrThrow("res_name")));
            restaurant_ntDanh.setRes_address(cursor.getString(cursor.getColumnIndexOrThrow("res_address")));
            restaurant_ntDanh.setRes_phone(cursor.getString(cursor.getColumnIndexOrThrow("res_phone")));
            restaurant_ntDanh.setRes_img(cursor.getString(cursor.getColumnIndexOrThrow("res_img")));
            restaurant_ntDanhList.add(restaurant_ntDanh);
        }
        cursor.close();
        return restaurant_ntDanhList;
    }

    public List<restaurant_NTDanh> getAll(){
        String sql = "SELECT * FROM restaurant";
        return get(sql);
    }

    public restaurant_NTDanh getByResID_NTDanh(int Res_ID){
        String sql = "SELECT * FROM restaurant WHERE res_id=?";
        List<restaurant_NTDanh> restaurant_ntDanhList = get(sql, String.valueOf(Res_ID));
        if (restaurant_ntDanhList.size()>0){
            return restaurant_ntDanhList.get(0);
        }
        return null;
    }

    public long insert_NTDanh(restaurant_NTDanh restaurant_ntDanh){
        ContentValues contentValues = new ContentValues();
        contentValues.put("res_name", restaurant_ntDanh.getRes_name());
        contentValues.put("res_address", restaurant_ntDanh.getRes_address());
        contentValues.put("res_phone", restaurant_ntDanh.getRes_phone());
        contentValues.put("res_img", restaurant_ntDanh.getRes_img());
        return db.insert("restaurant", null, contentValues);
    }
}
