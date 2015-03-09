package com.xuyazhou.common.image;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;

/**
 * A cache that holds strong references to a limited number of Bitmaps. Each time a Bitmap is
 * accessed, it is moved to the head of a queue. When a Bitmap is added to a full cache, the Bitmap
 * at the end of that queue is evicted and may become eligible for garbage collection.<br />
 * <br />
 * <b>NOTE:</b> This cache uses only strong references for stored Bitmaps.
 *
 * @author Sergey Tarasevich (elephant[at]gmail[dot]com)
 * @since 1.8.1
 */

/**
 * 改造LruMemoryCache使他适用于Volley图片缓存需求
 */
public class LruMemoryCache implements ImageLoader.ImageCache {
	/**
	 * 推荐内存缓存大小
	 */
	public static final int REC_CAHCE_SIZE = 4 * 1024 * 1024;

	protected LinkedHashMap<String, Bitmap> map;

	protected int maxSize;
	/**
	 * Size of this cache in bytes
	 */
	protected int size;

	/**
	 * @param maxSize
	 *            Maximum sum of the sizes of the Bitmaps in this cache
	 */
	public LruMemoryCache(int maxSize) {
		if (maxSize <= 0) {
			throw new IllegalArgumentException("maxSize <= 0");
		}
		this.maxSize = maxSize;
		this.map = new LinkedHashMap<String, Bitmap>(0, 0.75f, true);
	}

	public LruMemoryCache() {
		this(REC_CAHCE_SIZE);
	}

	/**
	 * Returns the Bitmap for {@code url} if it exists in the cache. If a Bitmap
	 * was returned, it is moved to the head of the queue. This returns null if
	 * a Bitmap is not cached.
	 */
	@Override
	public Bitmap getBitmap(String url) {
		if (url == null) {
			throw new NullPointerException("url == null");
		}

		synchronized (this) {
			return map.get(url);
		}
	}

	/**
	 * Caches {@code Bitmap} for {@code url}. The Bitmap is moved to the head of
	 * the queue.
	 */
	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		if (url == null || bitmap == null) {
			throw new NullPointerException("url == null || bitmap == null");
		}

		synchronized (this) {
			size += sizeOf(url, bitmap);
			Bitmap previous = map.put(url, bitmap);
			if (previous != null) {
				size -= sizeOf(url, previous);
			}
		}

		trimToSize(maxSize);
	}

	/**
	 * Remove the eldest entries until the total of remaining entries is at or
	 * below the requested size.
	 *
	 * @param maxSize
	 *            the maximum size of the cache before returning. May be -1 to
	 *            evict even 0-sized elements.
	 */
	protected void trimToSize(int maxSize) {
		while (true) {
			String url;
			Bitmap bitmap;
			synchronized (this) {
				if (size < 0 || (map.isEmpty() && size != 0)) {
					throw new IllegalStateException(getClass().getName()
							+ ".sizeOf() is reporting inconsistent results!");
				}

				if (size <= maxSize || map.isEmpty()) {
					break;
				}

				Map.Entry<String, Bitmap> toEvict = map.entrySet().iterator()
						.next();
				if (toEvict == null) {
					break;
				}
				url = toEvict.getKey();
				bitmap = toEvict.getValue();
				map.remove(url);
				size -= sizeOf(url, bitmap);
			}
		}
	}

	/**
	 * Removes the entry for {@code url} if it exists.
	 */
	public void remove(String url) {
		if (url == null) {
			throw new NullPointerException("url == null");
		}

		synchronized (this) {
			Bitmap previous = map.remove(url);
			if (previous != null) {
				size -= sizeOf(url, previous);
			}
		}
	}

	public Collection<String> urls() {
		synchronized (this) {
			return new HashSet<String>(map.keySet());
		}
	}

	public void clear() {
		trimToSize(-1); // -1 will evict 0-sized elements
	}

	/**
	 * Returns the size {@code Bitmap} in bytes.
	 * <p/>
	 * An entry's size must not change while it is in the cache.
	 */
	protected int sizeOf(String url, Bitmap bitmap) {
		return bitmap.getRowBytes() * bitmap.getHeight();
	}

	@Override
	public synchronized final String toString() {
		return String.format("LruCache[maxSize=%d]", maxSize);
	}

	/**
	 * 获取缓存大小
	 * 
	 * @return
	 */
	public int getMemoryCacheSize() {
		return size;
	}

	/**
	 * 清除全部缓存
	 */
	public void clearAllMemoryCache() {
		clear();
	}
}
