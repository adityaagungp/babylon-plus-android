package com.aditya.babylonplus.customview;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.aditya.babylonplus.R;
import com.aditya.babylonplus.model.object.Post;
import com.aditya.babylonplus.model.object.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderPostView extends ConstraintLayout {

    @BindView(R.id.postTitle)
    TextView postTitle;

    @BindView(R.id.authorName)
    TextView authorName;

    @BindView(R.id.body)
    TextView body;

    public HeaderPostView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.header_post, this, true);
        ButterKnife.bind(this);
    }

    public void bindPost(Post post){
        postTitle.setText(post.getTitle());
        body.setText(post.getBody());
    }

    public void setUser(User user){
        authorName.setText(user.getName());
    }
}
