package com.aditya.babylonplus.model.api;

import com.aditya.babylonplus.BuildConfig;
import com.aditya.babylonplus.model.object.Comment;
import com.aditya.babylonplus.model.object.Post;
import com.aditya.babylonplus.model.object.User;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCaller {

    private static ApiCaller instance = new ApiCaller();

    private OkHttpClient httpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .connectTimeout(3, TimeUnit.SECONDS).build();

    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    private Retrofit retrofit = new Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BuildConfig.API_BASE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private ApiService service = retrofit.create(ApiService.class);

    ApiCaller(){

    }

    public static ApiCaller getInstance(){
        return instance;
    }

    public void getPosts(Callback<List<Post>> callback){
        service.getPosts().enqueue(callback);
    }

    public void getUsers(Map<String, String> params, Callback<List<User>> callback){
        service.getUsers(params).enqueue(callback);
    }

    public void getComments(Map<String, String> params, Callback<List<Comment>> callback){
        service.getComments(params).enqueue(callback);
    }
}
