package com.aditya.babylonplus.post;

import com.aditya.babylonplus.model.object.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsCallback implements Callback<List<Comment>>{

    private PostDetailPresenter mPresenter;

    public CommentsCallback(PostDetailPresenter mPresenter){
        this.mPresenter = mPresenter;
    }

    @Override
    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
        if (response.isSuccessful()) {
            mPresenter.setComments(response.body());
        } else {

        }
    }

    @Override
    public void onFailure(Call<List<Comment>> call, Throwable t) {

    }
}
