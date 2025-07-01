package com.example.myfood_ngothanhdanh.Modle_NTDanh;

public class food_NTDanh {
    private int food_id;
    private String food_name;
    private String food_des;
    private Double food_price;
    private int food_img;
    private int res_id;
    private int cate_id;

    public food_NTDanh(String food_name, String food_des, int food_img, int res_id, int  cate_id, Double food_price) {
        this.food_name = food_name;
        this.food_des = food_des;
        this.food_img = food_img;
        this.res_id = res_id;
        this.food_price = food_price;
        this.cate_id = cate_id;
    }

    public food_NTDanh() {
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_des() {
        return food_des;
    }

    public void setFood_des(String food_des) {
        this.food_des = food_des;
    }

    public int getFood_img() {
        return food_img;
    }

    public void setFood_img(int food_img) {
        this.food_img = food_img;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }
    public Double getFood_price() {
        return food_price;
    }

    public void setFood_price(Double food_price) {
        this.food_price = food_price;
    }
    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

}
