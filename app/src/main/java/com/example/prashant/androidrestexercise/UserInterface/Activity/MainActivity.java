package com.example.prashant.androidrestexercise.UserInterface.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.prashant.androidrestexercise.Logic.DataObject;
import com.example.prashant.androidrestexercise.R;
import com.example.prashant.androidrestexercise.UserInterface.Fragment.ListFragment;

public class MainActivity extends AppCompatActivity implements ListFragment.OnRowSelectedListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));


        initFragment(ListFragment.newInstance());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void initFragment(Fragment detailFragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.mainFrame, detailFragment);
        transaction.commit();

    }

    @Override
    public void onArticleSelected(DataObject mData) {

    }

}
