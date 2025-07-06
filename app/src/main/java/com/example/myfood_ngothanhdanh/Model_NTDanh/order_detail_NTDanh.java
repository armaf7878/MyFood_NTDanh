package com.example.myfood_ngothanhdanh.Model_NTDanh;

public class order_detail_NTDanh {
    private int order_detail_id;
    private int order_id;
    private int food_id;
    private int quantity;
    private Double sub_total;


    public order_detail_NTDanh(int order_id, int food_id, int quantity, Double sub_total ) {
        this.order_id = order_id;
        this.food_id = food_id;
        this.quantity = quantity;
        this.sub_total = sub_total;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getSub_total() {
        return sub_total;
    }

    public void setSub_total(Double sub_total) {
        this.sub_total = sub_total;
    }

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }
}
