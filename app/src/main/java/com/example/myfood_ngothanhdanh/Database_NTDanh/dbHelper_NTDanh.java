package com.example.myfood_ngothanhdanh.Database_NTDanh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper_NTDanh extends SQLiteOpenHelper {
    private static final String DB_NAME = "Food_NTDANH.db";
    private static final int DB_VER = 1;
    public dbHelper_NTDanh(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createtable_NTDanh.CREATE_TABLE_USER_NTDANH);
        db.execSQL(createtable_NTDanh.CREATE_TABLE_FOOD_NTDANH);
        db.execSQL(createtable_NTDanh.CREATE_TABLE_ORDER_NTDANH);
        db.execSQL(createtable_NTDanh.CREATE_TABLE_ORDERDETAIL_NTDANH);
        db.execSQL(createtable_NTDanh.CREATE_TABLE_RESTAURANT_NTDANH);
        db.execSQL(createtable_NTDanh.CREATE_TABLE_CART_NTDANH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int DB_VER, int i1) {
        db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS order_detail");
        db.execSQL("DROP TABLE IF EXISTS food");
        db.execSQL("DROP TABLE IF EXISTS restaurant");
        onCreate(db);

    }
}

