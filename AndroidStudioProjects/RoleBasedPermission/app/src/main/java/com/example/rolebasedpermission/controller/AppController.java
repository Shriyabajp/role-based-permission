package com.example.rolebasedpermission.controller;

import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Retrofit getClientAuthentication(String url) {
        return new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
    }
}
