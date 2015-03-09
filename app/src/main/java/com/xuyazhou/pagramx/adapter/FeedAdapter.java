package com.xuyazhou.pagramx.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.android.volley.toolbox.NetworkImageView;
import com.xuyazhou.common.image.ImageManager;
import com.xuyazhou.common.util.StringsUtils;
import com.xuyazhou.common.util.time.TimeUtils;
import com.xuyazhou.pagramx.R;
import com.xuyazhou.pagramx.bean.Media;

import it.neokree.materialnavigationdrawer.util.Utils;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-03-03
 */

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
		implements View.OnClickListener {
	private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
	private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
	private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(
			4);
	private static final int ANIMATED_ITEMS_COUNT = 2;

	private Context context;
	private int lastAnimatedPosition = -1;

	private boolean animateItems = false;
	private ArrayList<Media> mMediaList;
	private final Map<RecyclerView.ViewHolder, AnimatorSet> likeAnimations = new HashMap<>();
	private final ArrayList<Integer> likedPositions = new ArrayList<>();
	private OnFeedItemClickListener onFeedItemClickListener;

	public FeedAdapter(Context context, ArrayList<Media> mediaList) {
		this.context = context;
		this.mMediaList = mediaList;

	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {

		final View view = LayoutInflater.from(context).inflate(
				R.layout.item_feed, parent, false);
		final FeedViewHolder feedViewHolder = new FeedViewHolder(view);
		feedViewHolder.btnComments.setOnClickListener(this);
		feedViewHolder.btnMore.setOnClickListener(this);
		feedViewHolder.btnLike.setOnClickListener(this);
		feedViewHolder.btnMore.setOnClickListener(this);
		feedViewHolder.showImage.setOnClickListener(this);
		feedViewHolder.btnLike.setOnClickListener(this);
		feedViewHolder.userAvatar.setOnClickListener(this);

		return feedViewHolder;
	}

	private void runEnterAnimation(View view, int position) {
		if (!animateItems || position >= ANIMATED_ITEMS_COUNT - 1) {
			return;
		}

		if (position > lastAnimatedPosition) {
			lastAnimatedPosition = position;
			view.setTranslationY(Utils.getScreenHeight(context));
			view.animate().translationY(0)
					.setInterpolator(new DecelerateInterpolator(3.f))
					.setDuration(700).start();
		}
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		runEnterAnimation(holder.itemView, position);

		FeedViewHolder viewHolder = (FeedViewHolder) holder;
		final Media media = mMediaList.get(position);

		viewHolder.userName.setText(media.getUser().getUsername());

		updateLikesCounter(viewHolder, media, false);
		updateHeartButton(viewHolder, false);

		if (media.isUser_has_liked()) {
			viewHolder.btnLike.setImageResource(R.mipmap.ic_heart_red);
		} else {
			viewHolder.btnLike.setImageResource(R.mipmap.ic_heart_outline_grey);
		}

		viewHolder.showImage.setDefaultImageResId(R.drawable.bg_image);
		viewHolder.userAvatar.setDefaultImageResId(R.drawable.bg_image);
		viewHolder.userAvatar.setErrorImageResId(R.drawable.bg_image);
		viewHolder.showImage.setErrorImageResId(R.drawable.bg_image);

		// viewHolder.userAvatar.setTag("url");
		viewHolder.userAvatar.setImageUrl(media.getUser().getProfile_picture(),
				ImageManager.getCircleImageLoader());
		// viewHolder.showImage.setTag("url");
		viewHolder.showImage.setImageUrl(
				StringsUtils.HttpToHttps(media.getImages()
						.getStandard_resolution().getUrl()),
				ImageManager.getImageLoader());

		viewHolder.itemTime.setText(TimeUtils.getRelativeTimeSpanString(Long
				.parseLong(media.getCreated_time())));

		if (media.getCaption() != null) {
			viewHolder.feedItemDes.setText(media.getCaption().getText() + "");
		}
		// viewHolder.likeCount.setText(media.getLikes().getCount() + "");
		viewHolder.commentCount.setText(media.getComments().getCount() + "");

		viewHolder.btnComments.setTag(position);
		viewHolder.btnMore.setTag(position);
		//
		viewHolder.btnLike.setTag(viewHolder);
		viewHolder.showImage.setTag(viewHolder);
		viewHolder.userAvatar.setTag(viewHolder);

		if (likeAnimations.containsKey(viewHolder)) {
			likeAnimations.get(viewHolder).cancel();
		}
		resetLikeAnimationState(viewHolder);

	}

	private void updateLikesCounter(FeedViewHolder holder, Media media,
			boolean animated) {

		int currentLikesCount = media.getLikes().getCount();
		int updateLikesCount = currentLikesCount + 1;

		if (animated) {
			holder.tsLikesCounter.setText(updateLikesCount + "");
		} else {
			holder.tsLikesCounter.setCurrentText(currentLikesCount + "");
		}

	}

	@Override
	public int getItemCount() {
		return mMediaList.size();
	}

	private void updateHeartButton(final FeedViewHolder holder, boolean animated) {
		if (animated) {
			if (!likeAnimations.containsKey(holder)) {
				AnimatorSet animatorSet = new AnimatorSet();
				likeAnimations.put(holder, animatorSet);

				ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(
						holder.btnLike, "rotation", 0f, 360f);
				rotationAnim.setDuration(300);
				rotationAnim.setInterpolator(ACCELERATE_INTERPOLATOR);

				ObjectAnimator bounceAnimX = ObjectAnimator.ofFloat(
						holder.btnLike, "scaleX", 0.2f, 1f);
				bounceAnimX.setDuration(300);
				bounceAnimX.setInterpolator(OVERSHOOT_INTERPOLATOR);

				ObjectAnimator bounceAnimY = ObjectAnimator.ofFloat(
						holder.btnLike, "scaleY", 0.2f, 1f);
				bounceAnimY.setDuration(300);
				bounceAnimY.setInterpolator(OVERSHOOT_INTERPOLATOR);
				bounceAnimY.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationStart(Animator animation) {
						holder.btnLike.setImageResource(R.mipmap.ic_heart_red);
					}
				});

				animatorSet.play(rotationAnim);
				animatorSet.play(bounceAnimX).with(bounceAnimY)
						.after(rotationAnim);

				animatorSet.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						resetLikeAnimationState(holder);
					}
				});

				animatorSet.start();
			}
		} else {
			if (likedPositions.contains(holder.getPosition())) {
				holder.btnLike.setImageResource(R.mipmap.ic_heart_red);
			} else {
				holder.btnLike.setImageResource(R.mipmap.ic_heart_outline_grey);
			}
		}
	}

	private void resetLikeAnimationState(FeedViewHolder holder) {
		likeAnimations.remove(holder);
		holder.bgLike.setVisibility(View.GONE);
		holder.ivLike.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View view) {
		final int viewId = view.getId();
		switch (viewId) {
		case R.id.btnComments:
			if (onFeedItemClickListener != null) {
				onFeedItemClickListener.onCommentsClick(view,
						(Integer) view.getTag());
			}
			break;
		case R.id.btnMore:
			if (onFeedItemClickListener != null) {
				onFeedItemClickListener.onMoreClick(view,
						(Integer) view.getTag());
			}
			break;
		case R.id.btnLike:
			FeedViewHolder holder = (FeedViewHolder) view.getTag();
			if (!likedPositions.contains(holder.getPosition())) {
				likedPositions.add(holder.getPosition());
				updateLikesCounter(holder,
						mMediaList.get(holder.getPosition()), true);
				updateHeartButton(holder, true);
				if (onFeedItemClickListener != null) {
					onFeedItemClickListener.onPhotoLike(view,
							holder.getPosition());
				}
			}
			break;
		case R.id.user_avatar:
			if (onFeedItemClickListener != null) {
				onFeedItemClickListener.onProfileClick(view);
			}
			break;
		case R.id.show_image:
			FeedViewHolder holder2 = (FeedViewHolder) view.getTag();
			if (!likedPositions.contains(holder2.getPosition())) {
				likedPositions.add(holder2.getPosition());
				updateLikesCounter(holder2,
						mMediaList.get(holder2.getPosition()), true);
				animatePhotoLike(holder2);
				updateHeartButton(holder2, false);
			}
			break;
		}

	}

	public void addItems(ArrayList<Media> mediaList, boolean animated) {

		int startIndex = mMediaList.size();
		Iterator localIterator = mediaList.iterator();

		while (localIterator.hasNext()) {
			Media media = (Media) localIterator.next();
			if (!this.mMediaList.contains(media)) {
				this.mMediaList.add(media);
			}
		}
		animateItems = animated;

		notifyItemRangeInserted(startIndex, this.mMediaList.size() - 1);
	}

	public void updataItems(boolean animated) {
		animateItems = animated;

		notifyDataSetChanged();
	}

	public void setOnFeedItemClickListener(
			OnFeedItemClickListener onFeedItemClickListener) {
		this.onFeedItemClickListener = onFeedItemClickListener;
	}

	private void animatePhotoLike(final FeedViewHolder holder) {
		if (!likeAnimations.containsKey(holder)) {
			holder.bgLike.setVisibility(View.VISIBLE);
			holder.ivLike.setVisibility(View.VISIBLE);

			holder.bgLike.setScaleY(0.1f);
			holder.bgLike.setScaleX(0.1f);
			holder.bgLike.setAlpha(1f);
			holder.ivLike.setScaleY(0.1f);
			holder.ivLike.setScaleX(0.1f);

			AnimatorSet animatorSet = new AnimatorSet();
			likeAnimations.put(holder, animatorSet);

			ObjectAnimator bgScaleYAnim = ObjectAnimator.ofFloat(holder.bgLike,
					"scaleY", 0.1f, 1f);
			bgScaleYAnim.setDuration(200);
			bgScaleYAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
			ObjectAnimator bgScaleXAnim = ObjectAnimator.ofFloat(holder.bgLike,
					"scaleX", 0.1f, 1f);
			bgScaleXAnim.setDuration(200);
			bgScaleXAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
			ObjectAnimator bgAlphaAnim = ObjectAnimator.ofFloat(holder.bgLike,
					"alpha", 1f, 0f);
			bgAlphaAnim.setDuration(200);
			bgAlphaAnim.setStartDelay(150);
			bgAlphaAnim.setInterpolator(DECCELERATE_INTERPOLATOR);

			ObjectAnimator imgScaleUpYAnim = ObjectAnimator.ofFloat(
					holder.ivLike, "scaleY", 0.1f, 1f);
			imgScaleUpYAnim.setDuration(300);
			imgScaleUpYAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
			ObjectAnimator imgScaleUpXAnim = ObjectAnimator.ofFloat(
					holder.ivLike, "scaleX", 0.1f, 1f);
			imgScaleUpXAnim.setDuration(300);
			imgScaleUpXAnim.setInterpolator(DECCELERATE_INTERPOLATOR);

			ObjectAnimator imgScaleDownYAnim = ObjectAnimator.ofFloat(
					holder.ivLike, "scaleY", 1f, 0f);
			imgScaleDownYAnim.setDuration(300);
			imgScaleDownYAnim.setInterpolator(ACCELERATE_INTERPOLATOR);
			ObjectAnimator imgScaleDownXAnim = ObjectAnimator.ofFloat(
					holder.ivLike, "scaleX", 1f, 0f);
			imgScaleDownXAnim.setDuration(300);
			imgScaleDownXAnim.setInterpolator(ACCELERATE_INTERPOLATOR);

			animatorSet.playTogether(bgScaleYAnim, bgScaleXAnim, bgAlphaAnim,
					imgScaleUpYAnim, imgScaleUpXAnim);
			animatorSet.play(imgScaleDownYAnim).with(imgScaleDownXAnim)
					.after(imgScaleUpYAnim);

			animatorSet.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					resetLikeAnimationState(holder);
				}
			});
			animatorSet.start();
		}
	}

	static class FeedViewHolder extends RecyclerView.ViewHolder {
		@InjectView(R.id.user_avatar)
		NetworkImageView userAvatar;
		@InjectView(R.id.show_image)
		NetworkImageView showImage;
		@InjectView(R.id.user_name)
		TextView userName;
		@InjectView(R.id.user_container)
		LinearLayout userContainer;
		@InjectView(R.id.item_time)
		TextView itemTime;
		@InjectView(R.id.bgLike)
		View bgLike;
		@InjectView(R.id.ivLike)
		ImageView ivLike;
		@InjectView(R.id.location)
		TextView location;
		@InjectView(R.id.comment_count)
		TextView commentCount;
		// @InjectView(R.id.like_count)
		// TextView likeCount;
		@InjectView(R.id.feed_item_des)
		TextView feedItemDes;
		@InjectView(R.id.btnLike)
		ImageView btnLike;
		@InjectView(R.id.tsLikesCounter)
		TextSwitcher tsLikesCounter;
		@InjectView(R.id.btnComments)
		ImageView btnComments;

		@InjectView(R.id.btnMore)
		ImageView btnMore;
		@InjectView(R.id.card_view)
		CardView cardView;

		public FeedViewHolder(View view) {
			super(view);
			ButterKnife.inject(this, view);

		}
	}

	public interface OnFeedItemClickListener {
		public void onCommentsClick(View v, int position);

		public void onMoreClick(View v, int position);

		public void onProfileClick(View v);

		public void onPhotoLike(View v, int position);
	}
}
