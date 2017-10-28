package com.aditya.babylonplus.home.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aditya.babylonplus.R;
import com.aditya.babylonplus.customview.ItemClickListener;
import com.aditya.babylonplus.model.object.Post;
import com.aditya.babylonplus.post.PostDetailActivity;
import com.aditya.babylonplus.user.UserDetailsActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsFragment extends Fragment implements ItemClickListener, PostsView {

    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.progressBar)
    AVLoadingIndicatorView progressBar;
    @BindView(R.id.emptyText)
    TextView emptyText;

    private PostsAdapter mAdapter;
    private PostsPresenter mPresenter;

    public PostsFragment() {
        // Required empty public constructor
    }

    public static PostsFragment newInstance() {
        PostsFragment fragment = new PostsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new PostsAdapter(getContext(), this);
        mPresenter = new PostsPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_default, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView(){
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        DividerItemDecoration divider = new DividerItemDecoration(list.getContext(), llm.getOrientation());
        list.addItemDecoration(divider);
        list.setLayoutManager(llm);
        list.setAdapter(mAdapter);
        emptyText.setText(getString(R.string.empty_posts));
        onWillFetchPosts();
        mPresenter.fetchPosts();
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(getActivity(), PostDetailActivity.class);
        intent.putExtra("Post", mAdapter.getPostAt(position));
        startActivity(intent);
    }

    @Override
    public void onWillFetchPosts() {
        emptyText.setVisibility(View.GONE);
        list.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPosts(List<Post> posts) {
        mAdapter.setPosts(posts);
        list.setVisibility(View.VISIBLE);
        emptyText.setVisibility(posts.size() > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onFinishLoadingPosts() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoadingPosts(String errorMessage) {
        progressBar.setVisibility(View.GONE);
        if (TextUtils.isEmpty(errorMessage)) {
            Snackbar.make(getActivity().getWindow().getDecorView().getRootView(), R.string.error_network_general, Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(getActivity().getWindow().getDecorView().getRootView(), errorMessage, Snackbar.LENGTH_LONG).show();
        }
    }
}
