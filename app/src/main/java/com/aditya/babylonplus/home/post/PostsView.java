package com.aditya.babylonplus.home.post;

import com.aditya.babylonplus.model.object.Post;

import java.util.List;

public interface PostsView {

    void onWillFetchPosts();

    void showPosts(List<Post> posts);

    void onFinishLoadingPosts();

    void onErrorLoadingPosts(String errorMessage);
}
