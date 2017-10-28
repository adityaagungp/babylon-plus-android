package com.aditya.babylonplus.post;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aditya.babylonplus.R;
import com.aditya.babylonplus.customview.CommentView;
import com.aditya.babylonplus.customview.HeaderViewHolder;
import com.aditya.babylonplus.model.object.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_HEADER = 0;
    private static final int VIEW_COMMENT = 1;

    private Context context;
    private List<Comment> comments = new ArrayList<>();
    private View header;

    public CommentAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getItemViewType(int position){
        if (header != null && position == 0)
            return VIEW_HEADER;
        else
            return VIEW_COMMENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_HEADER:
                return new HeaderViewHolder(header);
            case VIEW_COMMENT:
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.item_comment, parent, false);
                return new CommentView(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (header == null || position != 0){
            ((CommentView) holder).setComment(comments.get(getCommentIndex(position)));
        }
    }

    @Override
    public int getItemCount() {
        return comments.size() + (header == null ? 0 : 1);
    }

    public void setComments(List<Comment> comments){
        this.comments = comments;
        notifyDataSetChanged();
    }

    public void setHeader(View view){
        header = view;
        notifyDataSetChanged();
    }

    private int getCommentIndex(int position){
        return position - (header == null ? 0 : 1);
    }
}
