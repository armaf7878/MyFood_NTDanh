package com.example.myfood_ngothanhdanh.Modle_NTDanh;

import java.util.Date;

public class orders_NTDanh {
    private int order_id;
    private String order_address;
    private String order_date;
    private String order_status;
    private int user_ID;
    public orders_NTDanh() {
    }


    public orders_NTDanh(int user_ID, String order_status, String order_date, String order_address) {
        this.user_ID = user_ID;
        this.order_status = order_status;
        this.order_date = order_date;
        this.order_address = order_address;
    }


    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }
}
