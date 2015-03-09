package com.xuyazhou.pagramx.bean;

import com.xuyazhou.bean.UserBean;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-03-05
 */
public class Caption {

	/**
	 * id : 933794980054163715 text : By @garethcopley
	 * "Back at the Brit! #gettysport #MyGettyOffice #stokeveverton" from :
	 * {"id":"586212807","username":"gettysport","profile_picture":
	 * "http://photos-a.ak.instagram.com/hphotos-ak-xaf1/t51.2885-19/10547190_1477461559162928_1853037914_a.jpg"
	 * ,"full_name":"Getty Images Sport"} created_time : 1425537057
	 */
	private String id;
	private String text;
	private UserBean from;
	private String created_time;

	public void setId(String id) {
		this.id = id;
	}

	public void setText(String text) {
		if (text == null) {
			this.text = "";
		} else {
			this.text = text;
		}
	}

	public void setFrom(UserBean from) {
		this.from = from;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public UserBean getFrom() {
		return from;
	}

	public String getCreated_time() {
		return created_time;
	}

}
