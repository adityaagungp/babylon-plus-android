package com.aditya.babylonplus.home.user;

import com.aditya.babylonplus.model.object.User;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersCallback implements Callback<List<User>> {

    private UsersPresenter mPresenter;

    public UsersCallback(UsersPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful()) {
            mPresenter.onSuccessLoadingUsers(response.body());
        } else {
            String errorMessage = "";
            try {
                errorMessage = response.errorBody().string();
            } catch (IOException e) {
                errorMessage = null;
            }
            mPresenter.onFailedLoadingUsers(errorMessage);
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        mPresenter.onFailedLoadingUsers(t.getMessage());
    }
}
