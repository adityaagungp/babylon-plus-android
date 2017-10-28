package com.aditya.babylonplus.post;

import com.aditya.babylonplus.model.object.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorCallbackImpl implements Callback<List<User>> {

    private PostDetailPresenter presenter;

    public AuthorCallbackImpl(PostDetailPresenter postDetailPresenter){
        presenter = postDetailPresenter;
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful()){
            presenter.setAuthor(response.body().get(0));
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {

    }
}
