package com.example.myfood_ngothanhdanh.Database_NTDanh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class createtable_NTDanh {
    public static final String CREATE_TABLE_ORDER_NTDANH =
            "CREATE TABLE orders(" +
                    "order_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "order_address TEXT NOT NULL," +
                    "order_date TEXT NOT NULL," +
                    "order_status TEXT NOT NULL," +
                    "user_ID INTEGER NOT NULL," +
                    "FOREIGN KEY (user_ID) REFERENCES user(user_id) " +
                    "ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");";

    public static final String CREATE_TABLE_USER_NTDANH =
            "CREATE TABLE user(" +
                    "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_fullname TEXT NOT NULL," +
                    "user_gender TEXT NULL," +
                    "user_birthday TEXT NULL," +
                    "user_phone TEXT NULL," +
                    "user_name TEXT NOT NULL UNIQUE," +
                    "user_pass TEXT NOT NULL" + ");";

    public static final String CREATE_TABLE_ORDERDETAIL_NTDANH =
            "CREATE TABLE order_detail(" +
                    "order_id INTEGER NOT NULL," +
                    "food_id INTEGER NOT NULL," +
                    "order_detail_size TEXT NOT NULL," +
                    "order_detail_food TEXT NOT NULL," +
                    "order_detail_quantity INTEGER NOT NULL," +
                    "FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "FOREIGN KEY (food_id) REFERENCES food(food_id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");";

    public static final String CREATE_TABLE_RESTAURANT_NTDANH =
            "CREATE TABLE restaurant(" +
                    "res_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "res_name TEXT NOT NULL," +
                    "res_address TEXT NOT NULL," +
                    "res_phone TEXT NOT NULL," +
                    "res_img INTEGER NOT NULL"+ ");";

    public static final String CREATE_TABLE_FOOD_NTDANH =
            "CREATE TABLE food(" +
                    "food_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "food_name TEXT NOT NULL," +
                    "food_type TEXT NOT NULL," +
                    "food_des TEXT NOT NULL," +
                    "food_img INTEGER NOT NULL," +
                    "food_price REAL NOT NULL," +
                    "res_id INTEGER NOT NULL," +
                    "FOREIGN KEY (res_id) REFERENCES restaurant(res_id) " +
                    "ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");";

    public static final String CREATE_TABLE_CART_NTDANH =
            "CREATE TABLE food(" +
                    "cart_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_id INTEGER NOT NULL," +
                    "food_id INTEGER NOT NULL," +
                    "quantity INTEGER NOT NULL," +
                    "FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "FOREIGN KEY (food_id) REFERENCES food(food_id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    ");";


}