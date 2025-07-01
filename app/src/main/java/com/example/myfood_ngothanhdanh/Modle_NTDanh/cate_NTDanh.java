package com.example.myfood_ngothanhdanh.Modle_NTDanh;

public class cate_NTDanh {
    private int cate_id;
    private String cate_name;
    private int cate_img;

    public cate_NTDanh() {
    }

    public cate_NTDanh(String cate_name, int cate_img) {
        this.cate_name = cate_name;
        this.cate_img = cate_img;
    }


    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }
    public int getCate_img() {
        return cate_img;
    }

    public void setCate_img(int cate_img) {
        this.cate_img = cate_img;
    }
}
