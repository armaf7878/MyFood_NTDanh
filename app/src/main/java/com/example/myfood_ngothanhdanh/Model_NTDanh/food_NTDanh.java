package com.example.myfood_ngothanhdanh.Model_NTDanh;

public class food_NTDanh {
    private String food_id;
    private String food_name;
    private String food_des;
    private Double food_price;
    private String food_img;
    private String res_id;
    private String cate_id;


    public food_NTDanh(String food_id, String food_name, String food_des, Double food_price, String food_img, String res_id, String cate_id) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_des = food_des;
        this.food_price = food_price;
        this.food_img = food_img;
        this.res_id = res_id;
        this.cate_id = cate_id;
    }

    public food_NTDanh() {
    }

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
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

    public Double getFood_price() {
        return food_price;
    }

    public void setFood_price(Double food_price) {
        this.food_price = food_price;
    }

    public String getFood_img() {
        return food_img;
    }

    public void setFood_img(String food_img) {
        this.food_img = food_img;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }
}
