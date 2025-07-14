package com.example.myfood_ngothanhdanh.Model_NTDanh;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class orders_NTDanh implements Parcelable {
    private int order_id;
    private String order_address;
    private Double order_address_lat;
    private Double order_address_long;
    private String order_date;
    private String order_name;
    private String order_phone;
    private Double Total;
    private int user_ID;
    public orders_NTDanh() {
    }


    public orders_NTDanh(int order_id, String order_address, Double order_address_lat, Double order_address_long, String order_date, String order_name, String order_phone, Double total, int user_ID) {
        this.order_id = order_id;
        this.order_address = order_address;
        this.order_address_lat = order_address_lat;
        this.order_address_long = order_address_long;
        this.order_date = order_date;
        this.order_name = order_name;
        this.order_phone = order_phone;
        Total = total;
        this.user_ID = user_ID;
    }

    protected orders_NTDanh(Parcel in) {
        order_id = in.readInt();
        order_address = in.readString();
        if (in.readByte() == 0) {
            order_address_lat = null;
        } else {
            order_address_lat = in.readDouble();
        }
        if (in.readByte() == 0) {
            order_address_long = null;
        } else {
            order_address_long = in.readDouble();
        }
        order_date = in.readString();
        order_name = in.readString();
        order_phone = in.readString();
        if (in.readByte() == 0) {
            Total = null;
        } else {
            Total = in.readDouble();
        }
        user_ID = in.readInt();
    }

    public static final Creator<orders_NTDanh> CREATOR = new Creator<orders_NTDanh>() {
        @Override
        public orders_NTDanh createFromParcel(Parcel in) {
            return new orders_NTDanh(in);
        }

        @Override
        public orders_NTDanh[] newArray(int size) {
            return new orders_NTDanh[size];
        }
    };

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public Double getOrder_address_lat() {
        return order_address_lat;
    }

    public void setOrder_address_lat(Double order_address_lat) {
        this.order_address_lat = order_address_lat;
    }

    public Double getOrder_address_long() {
        return order_address_long;
    }

    public void setOrder_address_long(Double order_address_long) {
        this.order_address_long = order_address_long;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_phone() {
        return order_phone;
    }

    public void setOrder_phone(String order_phone) {
        this.order_phone = order_phone;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(order_id);
        parcel.writeString(order_address);
        if (order_address_lat == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(order_address_lat);
        }
        if (order_address_long == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(order_address_long);
        }
        parcel.writeString(order_date);
        parcel.writeString(order_name);
        parcel.writeString(order_phone);
        if (Total == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(Total);
        }
        parcel.writeInt(user_ID);
    }
}
