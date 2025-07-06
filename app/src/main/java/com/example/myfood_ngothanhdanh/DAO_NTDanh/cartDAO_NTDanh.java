package com.example.myfood_ngothanhdanh.DAO_NTDanh;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfood_ngothanhdanh.Database_NTDanh.dbHelper_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.cart_NTDanh;

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
            cartNtDanh.setCartID(cursor.getString(cursor.getColumnIndexOrThrow("cart_id")));
            cartNtDanh.setFoodID(cursor.getString(cursor.getColumnIndexOrThrow("food_id")));
            cartNtDanh.setUserID(cursor.getString(cursor.getColumnIndexOrThrow("user_id")));
            cartNtDanh.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow("quantity")));
            cart_ntDanhList.add(cartNtDanh);
        }
        cursor.close();
        return cart_ntDanhList;
    }

    public List<cart_NTDanh> getAll_NTDanh (){
        String sql = "SELECT * FROM cart";
        return get(sql);
    }

    public cart_NTDanh getByID_NTDanh (int cartID){
        String sql = "SELECT * FROM cart WHERE cart_id=?";
        List<cart_NTDanh> cart_ntDanhList = get(sql, String.valueOf(cartID));
        if (cart_ntDanhList.size()>0){
            return cart_ntDanhList.get(0);
        }
        return null;
    }
    public long insert_NTDanh(cart_NTDanh cartNtDanh){
        ContentValues contentValues = new ContentValues();
        contentValues.put("food_id", cartNtDanh.getFoodID());
        contentValues.put("user_id", cartNtDanh.getUserID());
        contentValues.put("quantity", cartNtDanh.getQuantity());
        return db.insert("cart", null, contentValues);
    }

    public int delete_cartByID_NTDanh(int id){
        return db.delete("cart", "cart_id=?", new String[]{String.valueOf(id)});
    }

    public int update_cartQuantityByID_NTDanh(int id, int quantity){
        ContentValues contentValues = new ContentValues();
        contentValues.put("quantity", quantity);
        return db.update("cart", contentValues, "cart_id=?", new String[]{String.valueOf(String.valueOf(id))});
    }
}
