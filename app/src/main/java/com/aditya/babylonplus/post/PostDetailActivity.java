package com.aditya.babylonplus.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aditya.babylonplus.R;
import com.aditya.babylonplus.customview.HeaderPostView;
import com.aditya.babylonplus.model.object.Comment;
import com.aditya.babylonplus.model.object.Post;
import com.aditya.babylonplus.model.object.User;
import com.aditya.babylonplus.user.UserDetailsActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailActivity extends AppCompatActivity implements PostDetailView {

    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.progressBar)
    AVLoadingIndicatorView progressBar;
    @BindView(R.id.emptyText)
    TextView emptyText;

    private PostDetailPresenter mPresenter;
    private CommentAdapter mAdapter;
    private HeaderPostView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_default);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter = new PostDetailPresenter(this);
        mAdapter = new CommentAdapter(this);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        DividerItemDecoration divider = new DividerItemDecoration(list.getContext(), llm.getOrientation());
        list.addItemDecoration(divider);
        list.setLayoutManager(llm);
        list.setAdapter(mAdapter);
        emptyText.setText(getString(R.string.empty_comments));
        Intent intent = getIntent();
        if (intent.hasExtra("Post")){
            Post post = intent.getParcelableExtra("Post");
            mPresenter.setPost(post);
            mPresenter.onFetchComment(post.getId());
            mPresenter.onFetchUser(post.getUserId());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showPost(Post post) {
        header = new HeaderPostView(this);
        header.bindPost(post);
        mAdapter.setHeader(header);
    }

    @Override
    public void showComments(List<Comment> comments) {
        mAdapter.setComments(comments);
        emptyText.setVisibility(comments.size() > 0 ? View.GONE: View.VISIBLE);
    }

    @Override
    public void onErrorLoadingComments() {

    }

    @Override
    public void showAuthor(final User user) {
        if (header == null){
            header = new HeaderPostView(this);
            mAdapter.setHeader(header);
        }
        header.setUser(user);
        header.setOnAuthorClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toAuthorDetails(user);
            }
        });
    }

    @Override
    public void onErrorLoadingAuthor() {

    }

    private void toAuthorDetails(User user) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }
}
