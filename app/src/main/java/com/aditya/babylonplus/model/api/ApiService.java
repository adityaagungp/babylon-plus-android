package com.aditya.babylonplus.model.api;

import com.aditya.babylonplus.model.object.Comment;
import com.aditya.babylonplus.model.object.Post;
import com.aditya.babylonplus.model.object.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("users")
    Call<List<User>> getUsers(@QueryMap(encoded = true) Map<String, String> params);

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("comments")
    Call<List<Comment>> getComments(@QueryMap(encoded = true) Map<String, String> params);
}
