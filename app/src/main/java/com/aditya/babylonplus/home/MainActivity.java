package com.aditya.babylonplus.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.aditya.babylonplus.R;
import com.aditya.babylonplus.home.post.PostsFragment;
import com.aditya.babylonplus.home.user.UsersFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab) TabLayout tab;
    @BindView(R.id.viewPager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addTab(PostsFragment.newInstance(), getString(R.string.posts));
        adapter.addTab(UsersFragment.newInstance(), getString(R.string.users));
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
    }
}
