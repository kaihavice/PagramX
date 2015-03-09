package com.xuyazhou.pagramx.fragment;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.gson.Gson;
import com.xuyazhou.bean.JsonData;
import com.xuyazhou.bean.UserBean;
import com.xuyazhou.common.volley.MyJsonObjectRequest;
import com.xuyazhou.common.volley.RequestManager;
import com.xuyazhou.dao.JsonDaoHelper;
import com.xuyazhou.dao.UserBeanDaoHelper;
import com.xuyazhou.pagramx.R;
import com.xuyazhou.pagramx.adapter.FeedAdapter;
import com.xuyazhou.pagramx.api.PagramXApi;
import com.xuyazhou.pagramx.base.SkeletonBaseFragment;
import com.xuyazhou.pagramx.bean.Feed;
import com.xuyazhou.pagramx.bean.Media;
import com.xuyazhou.pagramx.util.EndlessRecyclerOnScrollListener;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-01-27
 */

public class FeedFragment extends SkeletonBaseFragment implements
		SwipeRefreshLayout.OnRefreshListener,
		FeedAdapter.OnFeedItemClickListener {

    @InjectView(R.id.fab)
    FloatingActionsMenu fab;
    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;
    @InjectView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

	private FeedAdapter feedAdapter;
	private LinearLayoutManager linearLayoutManager;
	private EndlessRecyclerOnScrollListener endlessScrollListener;
	private ArrayList<Media> mediaList = new ArrayList<Media>();

	private boolean isloadMore = false;
	private Feed feed;
	private JsonData jsonData;
	private JsonDaoHelper jsonDaoHelper;
	private Toolbar mToolbar;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_feed, container,
				false);

		ButterKnife.inject(this, rootView);

		jsonDaoHelper = JsonDaoHelper.getInstance();

		jsonData = jsonDaoHelper.getDataById("feedFragment");

		setAdapter();

		// fab.attachToRecyclerView(recyclerView);
		if (jsonData != null) {

			getCache();
		}

		getdata();

		return rootView;
	}

	private void getCache() {

		try {
			processData(new JSONObject(jsonData.getData()), 0);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		endlessScrollListener.reset(0, true);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}

	@OnClick(R.id.new_photo)
	public void NewPhoto() {

		hideFloatingActionButton();

	}

	@OnClick(R.id.new_video)
	public void NewVideo() {

	}

	@OnClick(R.id.pick_photo)
	public void PickPhoto() {

	}

	@OnClick(R.id.pick_video)
	public void PickVideo() {

	}

	private void hideFloatingActionButton() {
		if (fab.isExpanded()) {
			fab.toggle();
		}
	}

	@Override
	public void onRefresh() {
		isloadMore = false;
		getdata();

	}

	@Override
	protected void processData(JSONObject response, int dataType) {

		if (swipeRefreshLayout.isRefreshing()) {
			this.swipeRefreshLayout.setRefreshing(false);
		}

		Gson gson = new Gson();
		feed = gson.fromJson(response.toString(), Feed.class);

		JsonData josn = new JsonData();
		josn.setCreateTime(feed.getData().get(0).getCreated_time());
		josn.setData(response.toString());
		josn.setData_type("feedFragment");
		jsonDaoHelper.addData(josn);

		if (isloadMore) {
			mediaList.addAll(feed.getData());
			feedAdapter.addItems(mediaList, false);

		} else {
			mediaList.clear();
			mediaList.addAll(feed.getData());
			feedAdapter.updataItems(false);
		}

	}

	private void setAdapter() {
		linearLayoutManager = new LinearLayoutManager(getActivityContext()) {
			@Override
			protected int getExtraLayoutSpace(RecyclerView.State state) {
				return 300;
			}
		};

		recyclerView.setLayoutManager(linearLayoutManager);
		swipeRefreshLayout.setOnRefreshListener(this);

		endlessScrollListener = new EndlessRecyclerOnScrollListener(
				linearLayoutManager) {
			@Override
			public void onLoadMore(int current_page) {
				isloadMore = true;
				getdata();
			}

			@Override
			public void onShow() {
				showViews();
			}

			@Override
			public void onHide() {
				hideViews();
			}
		};

		recyclerView.setHasFixedSize(false);
		recyclerView.setOnScrollListener(endlessScrollListener);

		feedAdapter = new FeedAdapter(getActivityContext(), mediaList);

		feedAdapter.setOnFeedItemClickListener(this);

		recyclerView.setAdapter(feedAdapter);

	}

	private void getdata() {

		RequestManager.getRequestQueue().add(
				new MyJsonObjectRequest(initParams(), responseListener(0),
						errorListener()));

	}

	private String initParams() {

		UserBeanDaoHelper userBeanDaoHelper = UserBeanDaoHelper.getInstance();
		UserBean userBean = userBeanDaoHelper.getDataById("userBean");
		String params;

		if (isloadMore) {
			params = feed.getPagination().getNext_url();
		} else {
			params = String.format(PagramXApi.FEED_PARAMS, "access_token",
					userBean.getAccess_token(), "count", PagramXApi.COUNT);
		}
		return params;
	}

	@Override
	public void onCommentsClick(View v, int position) {

	}

	@Override
	public void onMoreClick(View v, int position) {

	}

	@Override
	public void onProfileClick(View v) {

	}

	@Override
	public void onPhotoLike(View v, int position) {

	}

	private void hideViews() {

		// swipeRefreshLayout.setProgressViewOffset(false, 30,
		// -(int) (1.5D * mToolbar.getHeight()));

		mToolbar.animate().translationY(-mToolbar.getHeight())
				.setInterpolator(new AccelerateInterpolator(2))
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						mToolbar.setVisibility(View.GONE);
					}
				});

		FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) fab
				.getLayoutParams();
		int fabBottomMargin = lp.bottomMargin;
		fab.animate().translationY(fab.getHeight() + fabBottomMargin)
				.setInterpolator(new AccelerateInterpolator(2)).start();
		hideFloatingActionButton();
	}

	private void showViews() {

		mToolbar.animate().translationY(0)
				.setInterpolator(new OvershootInterpolator(1.f))
				.setStartDelay(10).setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						mToolbar.setVisibility(View.VISIBLE);
					}

				});
		fab.animate().translationY(0)
				.setInterpolator(new DecelerateInterpolator(2)).start();
	}

	public void setToolbar(Toolbar toolbar) {
		this.mToolbar = toolbar;
	}

}
