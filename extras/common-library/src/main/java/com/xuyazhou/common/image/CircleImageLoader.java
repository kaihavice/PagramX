package com.xuyazhou.common.image;

import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.xuyazhou.common.util.ImageUtil;

/**
 * 
 */
public class CircleImageLoader extends ImageLoader {

	public CircleImageLoader(RequestQueue queue, ImageCache imageCache) {
		super(queue, imageCache);
	}

	@Override
	protected void onGetImageSuccess(String cacheKey, Bitmap response) {
		Bitmap circleBitmap = ImageUtil.toOvalBitmap(response);
		super.onGetImageSuccess(cacheKey, circleBitmap);
	}
}
