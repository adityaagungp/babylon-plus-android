package com.aditya.babylonplus.home.user;

import com.aditya.babylonplus.model.object.User;

import java.util.List;

import retrofit2.Callback;

public interface UsersPresenter extends Callback<List<User>> {

    void fetchUsers();
}
