package com.aditya.babylonplus.home.post;

import com.aditya.babylonplus.model.api.ApiCaller;
import com.aditya.babylonplus.model.object.Post;

import java.util.ArrayList;
import java.util.List;

public class PostsPresenterImpl implements PostsPresenter {

    private PostsView view;
    private List<Post> mPosts;
    private PostsCallback mPostsCallback;

    public PostsPresenterImpl(PostsView view) {
        this.view = view;
        mPosts = new ArrayList<>();
        mPostsCallback = new PostsCallback(this);
    }

    @Override
    public void fetchPosts() {
        ApiCaller.getInstance().getPosts(mPostsCallback);
    }

    @Override
    public void onSuccessLoadingPosts(List<Post> posts) {
        view.onFinishLoadingPosts();
        mPosts = new ArrayList<>();
        mPosts.addAll(posts);
        view.showPosts(mPosts);
    }

    @Override
    public void onFailedLoadingPosts(String message) {
        view.onFinishLoadingPosts();
        view.onErrorLoadingPosts(message);
    }
}
