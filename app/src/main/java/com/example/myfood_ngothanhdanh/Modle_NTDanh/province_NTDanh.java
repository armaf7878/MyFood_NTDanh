package com.example.myfood_ngothanhdanh.Modle_NTDanh;

public class province_NTDanh {
    private int code;
    private String name;

    public province_NTDanh(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public province_NTDanh() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name; // hiển thị trong Spinner
    }
}
