package com.aditya.babylonplus.home.user;

import com.aditya.babylonplus.model.object.User;

import java.util.List;

public interface UsersPresenter {

    void fetchUsers();
    void onSuccessLoadingUsers(List<User> users);
    void onFailedLoadingUsers(String message);
}
