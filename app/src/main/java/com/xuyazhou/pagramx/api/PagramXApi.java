package com.xuyazhou.pagramx.api;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 *
 * Date: 2015-02-05
 */

public class PagramXApi {

	public static final String COUNT = "20";

	public static final String BASE_URL = "https://api.instagram.com/";

	public static final String CLIENT_ID = "43e9b7956a914ef7b43fb1cef71174ec";
	public static final String CLIENT_SECRET = "ea8ab6bfd1c4470baaab9e39d233883a";
	public static final String WEBSITE_URL = "http://xuyazhou.com";
	public static final String REDIRECT_URI = "http://xuyazhou.com/PagramX";
	public static final String GRANT_TYPE = "authorization_code";
	public static final String ACCESS_TOKEN = "https://api.instagram.com/oauth/access_token";

	public static final String AUTHORIZATION = BASE_URL
			+ "oauth/authorize/?client_id=" + CLIENT_ID + "&redirect_uri="
			+ REDIRECT_URI + "&response_type=code";

	public static final String FEED = BASE_URL + "v1/users/self/feed";
	public static final String FEED_PARAMS = FEED + "?%1$s=%2$s&%3$s=%4$s";
	// public static final String FEED =
	// "https://api.instagram.com/v1/users/self/feed?access_token=383571398.1fb234f.c77e1e2d40564b019b67e1f5e24eeffc&count=20";
}
