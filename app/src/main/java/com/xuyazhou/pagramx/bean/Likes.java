package com.xuyazhou.pagramx.bean;

import com.xuyazhou.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-03-05
 */
public class Likes {
	/**
	 * count : 29 data :
	 * [{"id":"221983913","username":"ryaneisenauer","profile_picture":
	 * "http://photos-h.ak.instagram.com/hphotos-ak-xfa1/t51.2885-19/10948571_697144327067095_1132153351_a.jpg"
	 * ,"full_name":"Ryan Eisenauer"},{"id":"1550823010","username":
	 * "golf_gods","profile_picture":
	 * "http://photos-e.ak.instagram.com/hphotos-ak-xaf1/t51.2885-19/10919391_892131914182956_1479928622_a.jpg"
	 * ,"full_name":"Golf Gods"},{"id":"226854804","username":"dan_s_87",
	 * "profile_picture":
	 * "http://photos-c.ak.instagram.com/hphotos-ak-xpf1/t51.2885-19/10611107_1453115881628858_214489101_a.jpg"
	 * ,"full_name":"Daniel Smith"},{"id":"1516609053","username":"ian.sena"
	 * ,"profile_picture":
	 * "http://photos-a.ak.instagram.com/hphotos-ak-xaf1/t51.2885-19/10946206_850049098391496_2140091891_a.jpg"
	 * ,"full_name":"Ian Sena"}]
	 */
	private int count;
	private ArrayList<UserBean> data;

	public void setCount(int count) {
		this.count = count;
	}

	public void setData(ArrayList<UserBean> data) {
		this.data = data;
	}

	public int getCount() {

		return count;
	}

	public List<UserBean> getData() {
		return data;
	}

}
