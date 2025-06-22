package com.example.myfood_ngothanhdanh.Modle_NTDanh;

public class order_detail_NTDanh {
    private int order_detail_id;
    private int order_id;
    private int food_id;
    private String order_detail_size;
    private String order_detail_food;
    private int order_detail_quantity;


    public order_detail_NTDanh(int order_id, int food_id, String order_detail_size, String order_detail_food, int order_detail_quantity) {
        this.order_id = order_id;
        this.food_id = food_id;
        this.order_detail_size = order_detail_size;
        this.order_detail_food = order_detail_food;
        this.order_detail_quantity = order_detail_quantity;
    }

    public order_detail_NTDanh() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getOrder_detail_size() {
        return order_detail_size;
    }

    public void setOrder_detail_size(String order_detail_size) {
        this.order_detail_size = order_detail_size;
    }

    public String getOrder_detail_food() {
        return order_detail_food;
    }

    public void setOrder_detail_food(String order_detail_food) {
        this.order_detail_food = order_detail_food;
    }

    public int getOrder_detail_quantity() {
        return order_detail_quantity;
    }

    public void setOrder_detail_quantity(int order_detail_quantity) {
        this.order_detail_quantity = order_detail_quantity;
    }
    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }
}
