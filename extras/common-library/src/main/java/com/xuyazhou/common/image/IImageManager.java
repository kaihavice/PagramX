package com.xuyazhou.common.image;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

public interface IImageManager {

    /**
     * 获取ImageLoader
     *
     * @return
     */
    public ImageLoader getImageLoader();

    /**
     * 获取圆形图loader
     *
     * @return
     */
    public ImageLoader getCircleImageLoader();

    /**
     * 获取本地图片
     * 1.先读缓存
     * 2.读本地图片（根据指定的图片长宽最大值，将图片缩放至合理大小，并缓存图片）
     *
     * @param path      本地图片路径，同时也是获取缓存的键值
     * @param maxHeight 图片最大高度
     * @param maxWidth  图片最大宽度
     * @return
     */
    public Bitmap getLocalBitmap(String path, int maxHeight, int maxWidth);

    /**
     * 获取本地图片
     * 先读缓存，后本地
     *
     * @param path 本地图片路径，同时也是获取缓存的键值
     * @return
     */
    public Bitmap getLocalBitmap(String path);

    /**
     * ImageListener默认实现之一。
     * （1）Showing a default image until the network response is received, at which point
     * it will switch to either the actual image or the error image.
     * （2）根据指定的图片最大高宽，调整下载图片尺寸
     *
     * @param view              The imageView that the listener is associated with.
     * @param defaultImageResId Default image resource ID to use, or 0 if it doesn't exist.
     * @param errorImageResId   Error image resource ID to use, or 0 if it doesn't exist.
     * @param maxHeight
     * @param maxWidth
     * @return
     */
    public ImageLoader.ImageListener getImageListener(final ImageView view, final ImageView border,
                                                      final int defaultImageResId, final int errorImageResId, final int maxHeight, final int maxWidth);
}
