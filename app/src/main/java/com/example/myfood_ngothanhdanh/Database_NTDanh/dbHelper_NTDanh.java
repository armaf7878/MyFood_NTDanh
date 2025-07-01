package com.example.myfood_ngothanhdanh.Database_NTDanh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myfood_ngothanhdanh.R;

public class dbHelper_NTDanh extends SQLiteOpenHelper {
    private static final String DB_NAME = "Food_NTDANH.db";
    private static final int DB_VER = 1;
    public dbHelper_NTDanh(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createtable_NTDanh.CREATE_TABLE_ROLE_NTDANH);
        db.execSQL("INSERT INTO role(role_name) VALUES ('admin');");
        db.execSQL("INSERT INTO role(role_name) VALUES ('user');");
        db.execSQL(createtable_NTDanh.CREATE_TABLE_USER_NTDANH);
        db.execSQL("INSERT INTO user (" +
                "user_fullname, user_gender, user_birthday, user_phone, user_name, user_pass, role_id" +
                ") VALUES (" +
                "'Ngô Thành Danh', 'Nam', '2004-10-20', '0909123456', '1', '1', 2" +
                ");");
        db.execSQL(createtable_NTDanh.CREATE_TABLE_FOOD_NTDANH);
        db.execSQL(createtable_NTDanh.CREATE_TABLE_CATEGORY_NTDANH);
        db.execSQL("INSERT INTO category(cate_name, cate_img) VALUES ('Món nước', " + R.drawable.food_phobo + ");");
        db.execSQL("INSERT INTO category(cate_name, cate_img) VALUES ('Món khô', " + R.drawable.food_phodacbiet + ");");
        db.execSQL("INSERT INTO category(cate_name, cate_img) VALUES ('Đồ uống', " + R.drawable.food_quay + ");");
        db.execSQL(createtable_NTDanh.CREATE_TABLE_ORDER_NTDANH);
        db.execSQL(createtable_NTDanh.CREATE_TABLE_ORDERDETAIL_NTDANH);
        db.execSQL(createtable_NTDanh.CREATE_TABLE_RESTAURANT_NTDANH);
        db.execSQL(createtable_NTDanh.CREATE_TABLE_CART_NTDANH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int DB_VER, int i1) {
        db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS role");
        db.execSQL("DROP TABLE IF EXISTS order_detail");
        db.execSQL("DROP TABLE IF EXISTS food");
        db.execSQL("DROP TABLE IF EXISTS cart");
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS restaurant");
        onCreate(db);
    }
}

