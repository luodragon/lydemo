package com.lieying.lydemo3.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkManager {
   // String baseUrl = "http://192.168.16.48:8091/";
    String baseUrl = "http://120.77.243.156:8081/";
//    String baseUrl = "http://192.168.16.38:8095/";
    private static NetWorkManager mInstance;
    private static Retrofit retrofit;
    private static volatile Request request = null;
    public static NetWorkManager getInstance(){
        if(mInstance==null){
            synchronized (NetWorkManager.class){
                if(mInstance==null){
                    mInstance = new NetWorkManager();
                }
            }
        }
        return mInstance;
    }
    public void init(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder().client(client).baseUrl(baseUrl).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();

    }
    public static Request getRequest(){
        if(request==null){
            synchronized (Request.class){
                request = retrofit.create(Request.class);
            }
        }
        return request;
    }
}
