package com.aditya.babylonplus.home.user;

import com.aditya.babylonplus.model.api.ApiCaller;
import com.aditya.babylonplus.model.object.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class UsersPresenterImpl implements UsersPresenter {

    private UsersView mView;
    private List<User> mUsers;

    public UsersPresenterImpl(UsersView view){
        mView = view;
        mUsers = new ArrayList<>();
    }

    @Override
    public void fetchUsers() {
        ApiCaller.getInstance().getUsers(new HashMap<String, String>(), this);
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        mView.onFinishLoadingUsers();
        if (response.isSuccessful()){
            mUsers = new ArrayList<>();
            mUsers.addAll(response.body());
            mView.showUsers(mUsers);
        } else {
            String errorMessage = "";
            try {
                errorMessage = response.errorBody().string();
            } catch (IOException e) {
                errorMessage = null;
            }
            mView.onErrorLoadingUsers(errorMessage);
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        mView.onFinishLoadingUsers();
        mView.onErrorLoadingUsers(t.getMessage());
    }
}
