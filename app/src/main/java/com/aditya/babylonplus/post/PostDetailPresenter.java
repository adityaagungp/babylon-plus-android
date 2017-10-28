package com.aditya.babylonplus.post;

import com.aditya.babylonplus.model.api.ApiCaller;
import com.aditya.babylonplus.model.object.Comment;
import com.aditya.babylonplus.model.object.Post;
import com.aditya.babylonplus.model.object.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailPresenter {

    private PostDetailView view;
    private List<Comment> comments;
    private Post post;
    private User author;
    private Callback<List<User>> userCallback;
    private Callback<List<Comment>> commentsCallback;

    public PostDetailPresenter(PostDetailView view) {
        this.view = view;
        this.userCallback = new AuthorCallbackImpl(this);
        this.commentsCallback = new CommentsCallback(this);
    }

    public void setPost(Post post) {
        this.post = post;
        view.showPost(this.post);
    }

    public void onFetchComment(long postId) {
        Map<String, String> params = new HashMap<>();
        params.put("postId", String.valueOf(postId));
        ApiCaller.getInstance().getComments(params, commentsCallback);
    }

    public void onFetchUser(long userId) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(userId));
        ApiCaller.getInstance().getUsers(params, userCallback);
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
