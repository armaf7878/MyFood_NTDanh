package com.example.myfood_ngothanhdanh.Modle_NTDanh;

public class user_NTDanh {
    private int user_id;
    private String user_fullname;
    private String user_gender;
    private String user_birthday;
    private String user_phone;
    private String user_name;
    private String user_pass;
    public user_NTDanh() {
    }

    public user_NTDanh(String user_pass, String user_name, String user_phone, String user_birthday, String user_gender, String user_fullname) {
        this.user_pass = user_pass;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_birthday = user_birthday;
        this.user_gender = user_gender;
        this.user_fullname = user_fullname;
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

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
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

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }
}
