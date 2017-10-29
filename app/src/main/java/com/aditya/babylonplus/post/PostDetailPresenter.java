package com.aditya.babylonplus.post;

import com.aditya.babylonplus.model.api.ApiCaller;
import com.aditya.babylonplus.model.object.Comment;
import com.aditya.babylonplus.model.object.Post;
import com.aditya.babylonplus.model.object.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Callback;

public class PostDetailPresenter {

    private PostDetailView view;
    private List<Comment> comments;
    private Post post;
    private User author;
    private Callback<List<User>> mAuthorCallback;
    private Callback<List<Comment>> mCommentsCallback;

    public PostDetailPresenter(PostDetailView view) {
        this.view = view;
        this.mAuthorCallback = new AuthorCallback(this);
        this.mCommentsCallback = new CommentsCallback(this);
    }

    public void setPost(Post post) {
        this.post = post;
        view.showPost(this.post);
    }

    public void onFetchComment(long postId) {
        Map<String, String> params = new HashMap<>();
        params.put("postId", String.valueOf(postId));
        ApiCaller.getInstance().getComments(params, mCommentsCallback);
    }

    public void onFetchUser(long userId) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(userId));
        ApiCaller.getInstance().getUsers(params, mAuthorCallback);
    }

    public void setAuthor(User user){
        author = user;
        view.showAuthor(author);
    }

    public void setComments (List<Comment> comments) {
        this.comments = new ArrayList<>();
        this.comments.addAll(comments);
        view.showComments(this.comments);
    }
}
