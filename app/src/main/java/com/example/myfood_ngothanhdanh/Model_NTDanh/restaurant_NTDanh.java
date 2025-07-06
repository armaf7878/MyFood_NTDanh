package com.example.myfood_ngothanhdanh.Model_NTDanh;

public class restaurant_NTDanh {
    private String res_id;
    private String res_name;
    private String res_address;
    private String res_phone;
    private String res_img;
    private String owner_id;
    private Double res_Lat;
    private Double res_Long;
    public restaurant_NTDanh() {
    }

    public restaurant_NTDanh(String res_id, String res_name, String res_address, String res_phone, String res_img, String owner_id, Double res_Lat, Double res_Long) {
        this.res_id = res_id;
        this.res_name = res_name;
        this.res_address = res_address;
        this.res_phone = res_phone;
        this.res_img = res_img;
        this.owner_id = owner_id;
        this.res_Lat = res_Lat;
        this.res_Long = res_Long;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
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

    public String getRes_img() {
        return res_img;
    }

    public void setRes_img(String res_img) {
        this.res_img = res_img;
    }

    public Double getRes_Lat() {
        return res_Lat;
    }

    public void setRes_Lat(Double res_Lat) {
        this.res_Lat = res_Lat;
    }

    public Double getRes_Long() {
        return res_Long;
    }

    public void setRes_Long(Double res_Long) {
        this.res_Long = res_Long;
    }
}
