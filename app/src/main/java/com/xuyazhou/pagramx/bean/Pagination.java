package com.xuyazhou.pagramx.bean;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-03-05
 */

public class Pagination {

	/**
	 * next_url : https://api.instagram.com/v1/users/self/feed?access_token=
	 * 383571398.43e9b79.bfddc77cd3f94e5483dae348c12ad573&count=20&max_id=
	 * 933539468576066694_259169642 next_max_id : 933539468576066694_259169642
	 */
	private String next_url;
	private String next_max_id;

	public void setNext_url(String next_url) {
		this.next_url = next_url;
	}

	public void setNext_max_id(String next_max_id) {
		this.next_max_id = next_max_id;
	}

	public String getNext_url() {
		return next_url;
	}

	public String getNext_max_id() {
		return next_max_id;
	}
}
