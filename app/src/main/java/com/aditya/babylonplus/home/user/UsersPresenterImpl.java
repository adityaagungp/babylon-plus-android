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
    private UsersCallback mCallback;

    public UsersPresenterImpl(UsersView view){
        mView = view;
        mUsers = new ArrayList<>();
        mCallback = new UsersCallback(this);
    }

    @Override
    public void fetchUsers() {
        ApiCaller.getInstance().getUsers(new HashMap<String, String>(), mCallback);
    }

    @Override
    public void onSuccessLoadingUsers(List<User> users) {
        mView.onFinishLoadingUsers();
        mUsers = new ArrayList<>();
        mUsers.addAll(users);
        mView.showUsers(mUsers);
    }

    @Override
    public void onFailedLoadingUsers(String message) {
        mView.onFinishLoadingUsers();
        mView.onErrorLoadingUsers(message);
    }
}
