package com.example.myfood_ngothanhdanh.DAO_NTDanh;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfood_ngothanhdanh.Database_NTDanh.dbHelper_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.user_NTDanh;

import java.util.ArrayList;
import java.util.List;

public class userDAO_NTDanh {
    private SQLiteDatabase db;
    public userDAO_NTDanh(Context context){
        dbHelper_NTDanh dbHelper = new dbHelper_NTDanh(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    private List<user_NTDanh> get (String sql, String... selectArgs){
        List<user_NTDanh> user_ntDanhList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectArgs);
        while (cursor.moveToNext()){
            user_NTDanh userNtDanh = new user_NTDanh();
            userNtDanh.setUser_id(cursor.getInt(cursor.getColumnIndexOrThrow("user_id")));
            userNtDanh.setUser_fullname(cursor.getString(cursor.getColumnIndexOrThrow("user_fullname")));
            userNtDanh.setUser_birthday(cursor.getString(cursor.getColumnIndexOrThrow("user_birthday")));
            userNtDanh.setUser_gender(cursor.getString(cursor.getColumnIndexOrThrow("user_gender")));
            userNtDanh.setUser_phone(cursor.getString(cursor.getColumnIndexOrThrow("user_phone")));
            userNtDanh.setUser_name(cursor.getString(cursor.getColumnIndexOrThrow("user_name")));
            userNtDanh.setUser_pass(cursor.getString(cursor.getColumnIndexOrThrow("user_pass")));
            userNtDanh.setRole_id(cursor.getInt(cursor.getColumnIndexOrThrow("role_id")));
            user_ntDanhList.add(userNtDanh);
        }
        cursor.close();
        return user_ntDanhList;
    }

    public List<user_NTDanh> getAll_NTDanh(){
        String sql = "SELECT * FROM user";
        return get(sql);
    }

    public user_NTDanh getByUserName_NTDanh(String username){
        String sql = "SELECT * FROM user WHERE user_name=?";
        List<user_NTDanh> user_ntDanhList = get(sql, username);
        if (user_ntDanhList.size()>0){
            return user_ntDanhList.get(0);
        }
        return null;
    }

    public long insert_NTDanh(user_NTDanh userNtDanh){
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_fullname", userNtDanh.getUser_fullname());
        contentValues.put("user_birthday", userNtDanh.getUser_birthday());
        contentValues.put("user_gender", userNtDanh.getUser_gender());
        contentValues.put("user_phone", userNtDanh.getUser_phone());
        contentValues.put("user_name", userNtDanh.getUser_name());
        contentValues.put("user_pass", userNtDanh.getUser_pass());
        contentValues.put("role_pass", userNtDanh.getRole_id());

        return db.insert("user", null, contentValues);
    }
}
