package com.aditya.babylonplus.home.post;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aditya.babylonplus.R;
import com.aditya.babylonplus.customview.ItemClickListener;
import com.aditya.babylonplus.customview.PostView;
import com.aditya.babylonplus.model.object.Post;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostView> {

    private Context context;
    private List<Post> items;
    private ItemClickListener postListener;

    public PostsAdapter(Context context, ItemClickListener listener) {
        this.context = context;
        postListener = listener;
    }

    @Override
    public PostView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_post, parent, false);
        return new PostView(view, postListener);
    }

    @Override
    public void onBindViewHolder(PostView holder, int position) {
        holder.setPost(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setPosts(List<Post> posts) {
        items = posts;
        notifyDataSetChanged();
    }

    public void clearPosts() {
        items.clear();
        notifyDataSetChanged();
    }

    public Post getPostAt(int position){
        return items.get(position);
    }
}
