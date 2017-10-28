package com.aditya.babylonplus.post;

import com.aditya.babylonplus.model.object.Comment;
import com.aditya.babylonplus.model.object.Post;
import com.aditya.babylonplus.model.object.User;

import java.util.List;

public interface PostDetailView {

    void showPost(Post post);
    void showComments(List<Comment> comments);
    void onErrorLoadingComments();
    void showAuthor(User user);
    void onErrorLoadingAuthor();
}
