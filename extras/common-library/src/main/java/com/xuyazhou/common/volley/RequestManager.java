package com.xuyazhou.common.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by yazhou on 2014/9/22
 */
public class RequestManager {

	private static RequestQueue mRequestQueue;
	private static ImageLoader mImageLoader;

	private RequestManager() {
		// no instances
	}

	public static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
      //  mImageLoader = new ImageLoader(mRequestQueue);

	}

	public static RequestQueue getRequestQueue() {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} else {
			throw new IllegalStateException("RequestQueue not initialized");
		}
	}

}
