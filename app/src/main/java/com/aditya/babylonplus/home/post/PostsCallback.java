package com.aditya.babylonplus.home.post;

import com.aditya.babylonplus.model.object.Post;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsCallback implements Callback<List<Post>> {

    private PostsPresenter mPresenter;

    public PostsCallback(PostsPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if (response.isSuccessful()){
            mPresenter.onSuccessLoadingPosts(response.body());
        } else {
            String errorMessage = "";
            try {
                errorMessage = response.errorBody().string();
            } catch (IOException e) {
                errorMessage = null;
            }
            mPresenter.onFailedLoadingPosts(errorMessage);
        }
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        mPresenter.onFailedLoadingPosts(t.getMessage());
    }
}
