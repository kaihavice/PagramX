package com.xuyazhou.pagramx.bean;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-03-05
 */
public class Images {
	/**
	 * thumbnail : {"height":150,"width":150,"url":
	 * "http://scontent.cdninstagram.com/hphotos-xaf1/t51.2885-15/s150x150/e15/10985988_910606002293845_928276327_n.jpg"
	 * } low_resolution : {"height":306,"width":306,"url":
	 * "http://scontent.cdninstagram.com/hphotos-xaf1/t51.2885-15/s306x306/e15/10985988_910606002293845_928276327_n.jpg"
	 * } standard_resolution : {"height":640,"width":640,"url":
	 * "http://scontent.cdninstagram.com/hphotos-xaf1/t51.2885-15/e15/10985988_910606002293845_928276327_n.jpg"
	 * }
	 */
	private Image thumbnail;
	private Image low_resolution;
	private Image standard_resolution;

	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}

	public void setLow_resolution(Image low_resolution) {
		this.low_resolution = low_resolution;
	}

	public void setStandard_resolution(Image standard_resolution) {
		this.standard_resolution = standard_resolution;
	}

	public Image getThumbnail() {
		return thumbnail;
	}

	public Image getLow_resolution() {
		return low_resolution;
	}

	public Image getStandard_resolution() {
		return standard_resolution;
	}

}
