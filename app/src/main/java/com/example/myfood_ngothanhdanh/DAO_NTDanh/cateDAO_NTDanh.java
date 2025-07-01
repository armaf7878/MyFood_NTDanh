package com.example.myfood_ngothanhdanh.DAO_NTDanh;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfood_ngothanhdanh.Database_NTDanh.dbHelper_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.cate_NTDanh;
import com.example.myfood_ngothanhdanh.Modle_NTDanh.food_NTDanh;

import java.util.ArrayList;
import java.util.List;

public class cateDAO_NTDanh {
    private SQLiteDatabase db;
    public cateDAO_NTDanh (Context context){
        dbHelper_NTDanh dbHelperNtDanh = new dbHelper_NTDanh(context);
        db = dbHelperNtDanh.getWritableDatabase();
    }
    @SuppressLint("Range")
    private List<cate_NTDanh> get (String sql, String... selectArgs){
        List<cate_NTDanh> cate_ntDanhList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            cate_NTDanh cateNtDanh = new cate_NTDanh();
            cateNtDanh.setCate_id(cursor.getInt(cursor.getColumnIndexOrThrow("cate_id")));
            cateNtDanh.setCate_name(cursor.getString(cursor.getColumnIndexOrThrow("cate_name")));
            cateNtDanh.setCate_img(cursor.getInt(cursor.getColumnIndexOrThrow("cate_img")));
            cate_ntDanhList.add(cateNtDanh);
        }
        cursor.close();
        return cate_ntDanhList;
    }

    public List<cate_NTDanh> getAll(){
        String sql = "SELECT * FROM category";
        return get(sql);
    }

    public List<cate_NTDanh> getCateByID_NTDanh(int cateid){
        String sql = "SELECT * FROM category WHERE cate_id=?";
        return get(sql, String.valueOf(cateid));
    }

    public long insertCate_NTDanh(cate_NTDanh cate_NTDanh){
        ContentValues contentValues = new ContentValues();
        contentValues.put("cate_name", cate_NTDanh.getCate_name());
        contentValues.put("cate_img", cate_NTDanh.getCate_name());
        return db.insert("food", null, contentValues);
    }

}
