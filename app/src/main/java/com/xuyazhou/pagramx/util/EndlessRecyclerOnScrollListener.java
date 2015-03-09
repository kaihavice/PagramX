package com.xuyazhou.pagramx.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 *
 * Date: 2015-03-04
 */

public abstract class EndlessRecyclerOnScrollListener extends
		RecyclerView.OnScrollListener {
	public static String TAG = EndlessRecyclerOnScrollListener.class
			.getSimpleName();

	private int previousTotal = 0; // The total number of items in the dataset
									// after the last load
	private boolean loading = true; // True if we are still waiting for the last
									// set of data to load.
	private int visibleThreshold = 1; // The minimum amount of items to have
										// below your current scroll position
										// before loading more.
	int firstVisibleItem, visibleItemCount, totalItemCount;

	private int current_page = 1;

	private static final int HIDE_THRESHOLD = 20;

	private int scrolledDistance = 0;
	private boolean controlsVisible = true;

	private LinearLayoutManager mLinearLayoutManager;

	public EndlessRecyclerOnScrollListener(
			LinearLayoutManager linearLayoutManager) {
		this.mLinearLayoutManager = linearLayoutManager;
	}

	@Override
	public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
		super.onScrolled(recyclerView, dx, dy);

		visibleItemCount = recyclerView.getChildCount();
		totalItemCount = mLinearLayoutManager.getItemCount();
		firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        //check is scrolling
		if (firstVisibleItem == 0) {
			if (!controlsVisible) {
				onShow();
				controlsVisible = true;
			}
		} else {
			if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
				onHide();
				controlsVisible = false;
				scrolledDistance = 0;
			} else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
				onShow();
				controlsVisible = true;
				scrolledDistance = 0;
			}
		}

		if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
			scrolledDistance += dy;
		}

		if (loading) {
			if (totalItemCount > previousTotal) {
				loading = false;
				previousTotal = totalItemCount;

			}
		}
		if (!loading
				&& (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
			// End has been reached

			// Do something
			current_page++;

			onLoadMore(current_page);

			loading = true;
		}
	}

	public void reset(int previousTotal, boolean loading) {
		this.previousTotal = previousTotal;
		this.loading = loading;
	}

	public abstract void onLoadMore(int current_page);

	public abstract void onShow();

	public abstract void onHide();
}
