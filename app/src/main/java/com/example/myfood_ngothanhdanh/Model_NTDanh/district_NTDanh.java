package com.example.myfood_ngothanhdanh.Model_NTDanh;

public class district_NTDanh {
    private int code;
    private String name;

    public district_NTDanh(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() { return code; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name;
    }
}
