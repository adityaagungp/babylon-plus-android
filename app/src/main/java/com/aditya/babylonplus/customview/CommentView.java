package com.aditya.babylonplus.customview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aditya.babylonplus.R;
import com.aditya.babylonplus.model.object.Comment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentView extends RecyclerView.ViewHolder {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.body)
    TextView body;

    private Comment comment;

    public CommentView(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
        name.setText(comment.getName());
        email.setText(comment.getEmail());
        body.setText(comment.getBody());
    }
}
