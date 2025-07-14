package com.example.myfood_ngothanhdanh.Model_NTDanh;

public class cate_NTDanh {
    private String cate_id;
    private String cate_name;
    private String cate_img;

    public cate_NTDanh() {
    }


    public cate_NTDanh(String cate_id, String cate_name, String cate_img) {
        this.cate_id = cate_id;
        this.cate_name = cate_name;
        this.cate_img = cate_img;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getCate_img() {
        return cate_img;
    }

    public void setCate_img(String cate_img) {
        this.cate_img = cate_img;
    }

    @Override
    public String toString() {
        return cate_name;
    }
}
