package com.aditya.babylonplus.home.post;

import com.aditya.babylonplus.model.api.ApiCaller;
import com.aditya.babylonplus.model.object.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PostsPresenterImpl implements PostsPresenter {

    private PostsView view;
    private List<Post> mPosts;

    public PostsPresenterImpl(PostsView view){
        this.view = view;
        mPosts = new ArrayList<>();
    }

    @Override
    public void fetchPosts() {
        ApiCaller.getInstance().getPosts(this);
    }

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        view.onFinishLoadingPosts();
        if (response.isSuccessful()){
            mPosts = new ArrayList<>();
            mPosts.addAll(response.body());
            view.showPosts(mPosts);
        } else {
            String errorMessage = "";
            try {
                errorMessage = response.errorBody().string();
            } catch (IOException e) {
                errorMessage = null;
            }
            view.onErrorLoadingPosts(errorMessage);
        }
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        view.onFinishLoadingPosts();
        view.onErrorLoadingPosts(t.getMessage());
    }
}
