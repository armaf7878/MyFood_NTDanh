package com.example.myfood_ngothanhdanh.API_INTERFACE;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitClient_NTDanh{
    private static final String BASE_URL =  "https://provinces.open-api.vn/";
    private static Retrofit retrofit  = null;
    public static Retrofit proviceClient_NTDanh(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
