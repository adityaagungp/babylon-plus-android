package com.aditya.babylonplus.home.post;

import com.aditya.babylonplus.model.object.Post;

import java.util.List;

import retrofit2.Callback;

public interface PostsPresenter {

    void fetchPosts();
    void onSuccessLoadingPosts(List<Post> posts);
    void onFailedLoadingPosts(String message);
}
