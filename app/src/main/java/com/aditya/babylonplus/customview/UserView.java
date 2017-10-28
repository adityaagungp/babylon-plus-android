package com.aditya.babylonplus.customview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aditya.babylonplus.R;
import com.aditya.babylonplus.model.object.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserView extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.company)
    TextView company;

    private User user;
    private ItemClickListener listener;

    public UserView(View view, ItemClickListener listener) {
        super(view);
        ButterKnife.bind(this, view);
        this.listener = listener;
        view.setOnClickListener(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        name.setText(user.getName());
        email.setText(user.getEmail());
        company.setText(user.getCompany().getName());
    }

    @Override
    public void onClick(View view) {
        listener.onItemClicked(getAdapterPosition());
    }
}
