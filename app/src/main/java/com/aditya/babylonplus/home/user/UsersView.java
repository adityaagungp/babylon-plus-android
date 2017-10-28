package com.aditya.babylonplus.home.user;

import com.aditya.babylonplus.model.object.User;

import java.util.List;

public interface UsersView {

    void onWillFetchUsers();

    void showUsers(List<User> users);

    void onFinishLoadingUsers();

    void onErrorLoadingUsers(String errorMessage);
}
