package com.xuyazhou.pagramx.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.xuyazhou.pagramx.R;
import com.xuyazhou.pagramx.base.SkeletonBaseActivity;
import com.xuyazhou.pagramx.view.RevealBackgroundView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-03-03
 */

public class ProfileAcitivity extends SkeletonBaseActivity implements RevealBackgroundView.OnStateChangeListener {

    @InjectView(R.id.revealBackground)
    RevealBackgroundView revealBackground;
    @InjectView(R.id.userProfile)
    RecyclerView userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.inject(this);

        setupGird();
    }

    private void setupGird() {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        userProfile.setLayoutManager(layoutManager);
        userProfile.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                // userProfile.setLockedAnimations(true);
            }
        });
    }

    @Override
    public void onStateChange(int state) {

    }
}
