package com.xuyazhou.common.volley;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;

/**
 * 简单的带有参数的json request
 * 
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * 
 * Date: 2014-11-25
 */

public class MyJsonObjectRequest extends Request<JSONObject> {

	private final Map<String, String> params;
	private final Response.Listener<JSONObject> listener;

	public MyJsonObjectRequest(String url, Map<String, String> params,
			Response.Listener<JSONObject> listener,
			Response.ErrorListener errorListener) {

		super(Method.POST, url, errorListener);
		this.params = params;
		this.listener = listener;

	}

	public MyJsonObjectRequest(String url,
			Response.Listener<JSONObject> listener,
			Response.ErrorListener errorListener) {

		super(Method.GET, url, errorListener);
		this.params = null;
		this.listener = listener;

	}

	public MyJsonObjectRequest(int method, String url,
			Response.Listener<JSONObject> listener,
			Response.ErrorListener errorListener) {

		super(method, url, errorListener);
		this.params = null;
		this.listener = listener;

	}

	public MyJsonObjectRequest(int method, String url,
			Map<String, String> params, Response.Listener<JSONObject> listener,
			Response.ErrorListener errorListener) {

		super(method, url, errorListener);
		this.params = params;
		this.listener = listener;

	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {

		return params != null ? params : super.getParams();
	}

	@Override
	public RetryPolicy getRetryPolicy() {
		// 超时时间为5s
		int SOCKET_TIMEOUT = 5000;
		return new DefaultRetryPolicy(SOCKET_TIMEOUT,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
	}

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));

			return Response.success(new JSONObject(jsonString),
					HttpHeaderParser.parseCacheHeaders(response));

		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JSONException je) {
			return Response.error(new ParseError(je));
		} catch (NullPointerException e) {
			return Response.error(new ParseError(e));
		}
	}

	@Override
	protected void deliverResponse(JSONObject response) {
		listener.onResponse(response);
	}
}
