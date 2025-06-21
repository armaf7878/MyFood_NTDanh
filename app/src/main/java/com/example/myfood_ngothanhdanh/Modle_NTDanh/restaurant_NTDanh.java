package com.example.myfood_ngothanhdanh.Modle_NTDanh;

public class restaurant_NTDanh {
    private int res_id;
    private String res_name;
    private String res_address;
    private String res_phone;
    private int res_img;

    public restaurant_NTDanh(int res_img, String res_phone, String res_address, String res_name) {
        this.res_img = res_img;
        this.res_phone = res_phone;
        this.res_address = res_address;
        this.res_name = res_name;
    }

    public restaurant_NTDanh() {
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getRes_address() {
        return res_address;
    }

    public void setRes_address(String res_address) {
        this.res_address = res_address;
    }

    public String getRes_phone() {
        return res_phone;
    }

    public void setRes_phone(String res_phone) {
        this.res_phone = res_phone;
    }

    public int getRes_img() {
        return res_img;
    }

    public void setRes_img(int res_img) {
        this.res_img = res_img;
    }
}
