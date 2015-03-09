package com.xuyazhou.pagramx.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-03-04
 */

public class RecyclerLoadScrollListener extends RecyclerView.OnScrollListener {

	private RecyclerView.LayoutManager mLayoutManager;
	private OnLoadMoreListener mOnLoadMoreListener;
	private boolean loading = true;
	int pastVisiblesItems, visibleItemCount, totalItemCount;

	public RecyclerLoadScrollListener(
			RecyclerView.LayoutManager paramLayoutManager,
			OnLoadMoreListener paramOnLoadMoreListener) {
		this.mLayoutManager = paramLayoutManager;
		this.mOnLoadMoreListener = paramOnLoadMoreListener;
	}

	@Override
	public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

		super.onScrolled(recyclerView, dx, dy);

		// visibleItemCount = mLayoutManager.getChildCount();
		// totalItemCount = mLayoutManager.getItemCount();
		// pastVisiblesItems = ((LinearLayoutManager) mLayoutManager)
		// .findFirstVisibleItemPosition();
		//
		// // if (loading) {
		// if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
		// // loading = false;
		// mOnLoadMoreListener.onLoadMore();
		// }
		// // }

		int lastVisibleItem = ((LinearLayoutManager) mLayoutManager)
				.findLastVisibleItemPosition();
		int totalItemCount = mLayoutManager.getItemCount();
		// lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载
		// dy>0 表示向下滑动
		if (lastVisibleItem >= totalItemCount - 1 && dy > 0) {
			mOnLoadMoreListener.onLoadMore();
		}

	}

	// @Override
	// public void onScrollStateChanged(RecyclerView recyclerView, int newState)
	// {
	// int totalItemCount = mLayoutManager.getItemCount();
	// int lastVisibleItem = ((LinearLayoutManager) mLayoutManager)
	// .findFirstVisibleItemPosition();
	//
	// if (totalItemCount > 1) {
	// if (lastVisibleItem >= totalItemCount - 1) {
	// // End has been reached
	// // do something
	// mOnLoadMoreListener.onLoadMore();
	// }
	// }
	// }

	public static abstract interface OnLoadMoreListener {
		public abstract void onLoadMore();
	}
}
