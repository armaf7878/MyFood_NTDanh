package com.example.myfood_ngothanhdanh.API;

import com.example.myfood_ngothanhdanh.Model_NTDanh.province_NTDanh;
import com.example.myfood_ngothanhdanh.Model_NTDanh.provincewdistrict_NTDanh;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface  provinceAPI_NTDanh {
    @GET("api/p/")
    Call<List<province_NTDanh>> getProvinces();
    @GET("api/p/{code}?depth=2")
    Call<provincewdistrict_NTDanh> getProvinceWithDistricts(@Path("code") int code);
}
