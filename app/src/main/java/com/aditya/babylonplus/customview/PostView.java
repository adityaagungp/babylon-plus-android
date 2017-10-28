package com.aditya.babylonplus.customview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aditya.babylonplus.R;
import com.aditya.babylonplus.model.object.Post;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostView extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.body)
    TextView body;

    private Post post;
    private ItemClickListener listener;

    public PostView(View view, ItemClickListener listener) {
        super(view);
        ButterKnife.bind(this, view);
        this.listener = listener;
        view.setOnClickListener(this);
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
        icon.setContentDescription(post.getTitle());
        title.setText(post.getTitle());
        body.setText(post.getBody());
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onItemClicked(getAdapterPosition());
    }
}
