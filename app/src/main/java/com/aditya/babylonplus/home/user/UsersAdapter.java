package com.aditya.babylonplus.home.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aditya.babylonplus.R;
import com.aditya.babylonplus.customview.ItemClickListener;
import com.aditya.babylonplus.customview.UserView;
import com.aditya.babylonplus.model.object.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserView> {

    private Context context;
    private List<User> items;
    private ItemClickListener listener;

    public UsersAdapter(Context context, ItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public UserView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_user, parent, false);
        return new UserView(view, listener);
    }

    @Override
    public void onBindViewHolder(UserView holder, int position) {
        holder.setUser(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setUsers(List<User> users) {
        items = users;
        notifyDataSetChanged();
    }

    public void clearUsers() {
        items.clear();
        notifyDataSetChanged();
    }

    public User getUserAt(int index){
        return items.get(index);
    }
}
