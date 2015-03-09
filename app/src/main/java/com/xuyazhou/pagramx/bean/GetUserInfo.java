package com.xuyazhou.pagramx.bean;

import com.xuyazhou.bean.UserBean;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-02-27
 */

public class GetUserInfo {

	/**
	 * user : {"id":"383571398","username":"xuyazhou15","profile_picture":
	 * "https://instagramimages-a.akamaihd.net/profiles/profile_383571398_75sq_1368843395.jpg"
	 * ,"bio":"Simplicity but not simple","website":"http://xuyazhou.com",
	 * "full_name":"lampard_xu"} access_token :
	 * 383571398.43e9b79.bfddc77cd3f94e5483dae348c12ad573
	 */
	private UserBean user;
	private String access_token;

	public void setUser(UserBean user) {
		this.user = user;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public UserBean getUser() {
		return user;
	}

	public String getAccess_token() {
		return access_token;
	}

}
