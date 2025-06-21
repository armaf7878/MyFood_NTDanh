package com.example.myfood_ngothanhdanh.DAO_NTDanh;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfood_ngothanhdanh.Database_NTDanh.dbHelper_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.food_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.user_NTDanh;

import java.util.ArrayList;
import java.util.List;

public class foodDAO_NTDanh {
    private SQLiteDatabase db;
    public foodDAO_NTDanh(Context context){
        dbHelper_NTDanh dbHelper = new dbHelper_NTDanh(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    private List<food_NTDanh> get (String sql, String... selectArgs){
        List<food_NTDanh> food_ntDanhList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            food_NTDanh food_ntDanh = new food_NTDanh();
            food_ntDanh.setFood_id(cursor.getInt(cursor.getColumnIndexOrThrow("food_id")));
            food_ntDanh.setFood_name(cursor.getString(cursor.getColumnIndexOrThrow("food_name")));
            food_ntDanh.setFood_type(cursor.getString(cursor.getColumnIndexOrThrow("food_type")));
            food_ntDanh.setFood_des(cursor.getString(cursor.getColumnIndexOrThrow("food_des")));
            food_ntDanh.setFood_img(cursor.getInt(cursor.getColumnIndexOrThrow("food_img")));
            food_ntDanh.setRes_id(cursor.getInt(cursor.getColumnIndexOrThrow("res_id")));
            food_ntDanh.setFood_price(cursor.getDouble(cursor.getColumnIndexOrThrow("food_price")));
            food_ntDanhList.add(food_ntDanh);
        }
        cursor.close();
        return food_ntDanhList;
    }

    public List<food_NTDanh> getAll(){
        String sql = "SELECT * FROM food";
        return get(sql);
    }

    public List<food_NTDanh> getFoodByResID_NTDanh(int ResID){
        String sql = "SELECT * FROM food WHERE res_id=?";
        return get(sql, String.valueOf(ResID));
    }

    public food_NTDanh getFoodByFoodID_NTDanh(int FoodID){
        String sql = "SELECT * FROM food WHERE food_id=?";
        List<food_NTDanh> food_ntDanhList =  get(sql, String.valueOf(FoodID));
        if (food_ntDanhList.size()>0){
            return food_ntDanhList.get(0);
        }
        return null;
    }


    public long insertFood_NTDanh(food_NTDanh food_ntDanh){
        ContentValues contentValues = new ContentValues();
        contentValues.put("food_name", food_ntDanh.getFood_name());
        contentValues.put("food_type", food_ntDanh.getFood_type());
        contentValues.put("food_des", food_ntDanh.getFood_des());
        contentValues.put("food_img",food_ntDanh.getFood_img());
        contentValues.put("res_id", food_ntDanh.getRes_id());
        contentValues.put("food_price", food_ntDanh.getFood_price());
        return db.insert("food", null, contentValues);
    }
}
