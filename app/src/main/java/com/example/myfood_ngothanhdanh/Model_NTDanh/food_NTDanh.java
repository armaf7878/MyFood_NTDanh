package com.example.myfood_ngothanhdanh.Model_NTDanh;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class food_NTDanh implements Parcelable {
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

    protected food_NTDanh(Parcel in) {
        food_id = in.readString();
        food_name = in.readString();
        food_des = in.readString();
        if (in.readByte() == 0) {
            food_price = null;
        } else {
            food_price = in.readDouble();
        }
        food_img = in.readString();
        res_id = in.readString();
        cate_id = in.readString();
    }

    public static final Creator<food_NTDanh> CREATOR = new Creator<food_NTDanh>() {
        @Override
        public food_NTDanh createFromParcel(Parcel in) {
            return new food_NTDanh(in);
        }

        @Override
        public food_NTDanh[] newArray(int size) {
            return new food_NTDanh[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(food_id);
        parcel.writeString(food_name);
        parcel.writeString(food_des);
        if (food_price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(food_price);
        }
        parcel.writeString(food_img);
        parcel.writeString(res_id);
        parcel.writeString(cate_id);
    }
}
