package com.example.myfood_ngothanhdanh.Model_NTDanh;

public class orders_NTDanh {
    private int order_id;
    private String order_address;
    private String order_date;
    private String order_name;
    private String order_phone;
    private int user_ID;
    public orders_NTDanh() {
    }


    public orders_NTDanh(int user_ID,String order_name, String order_phone, String order_date, String order_address) {
        this.user_ID = user_ID;
        this.order_date = order_date;
        this.order_address = order_address;
        this.order_name = order_name;
        this.order_phone = order_phone;
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

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }
    public String getOrder_phone() {
        return order_phone;
    }

    public void setOrder_phone(String order_phone) {
        this.order_phone = order_phone;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

}
