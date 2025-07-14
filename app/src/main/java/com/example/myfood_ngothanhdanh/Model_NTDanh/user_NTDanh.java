package com.example.myfood_ngothanhdanh.Model_NTDanh;

public class user_NTDanh {
    private int user_id;
    private String user_fullname;
    private String user_gender;
    private String user_phone;
    private String user_name;
    private String user_city;
    private String user_district;
    private String detail_address;
    private int role_id;
    public user_NTDanh() {
    }

    public user_NTDanh(int user_id, String user_fullname, String user_gender, String user_phone, String user_name, String user_city, String user_district, String detail_address, int role_id) {
        this.user_id = user_id;
        this.user_fullname = user_fullname;
        this.user_gender = user_gender;
        this.user_phone = user_phone;
        this.user_name = user_name;
        this.user_city = user_city;
        this.user_district = user_district;
        this.detail_address = detail_address;
        this.role_id = role_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_district() {
        return user_district;
    }

    public void setUser_district(String user_district) {
        this.user_district = user_district;
    }

    public String getDetail_address() {
        return detail_address;
    }

    public void setDetail_address(String detail_address) {
        this.detail_address = detail_address;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
