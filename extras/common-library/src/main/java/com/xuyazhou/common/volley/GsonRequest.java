package com.xuyazhou.common.volley;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Volley adapter for JSON requests that will be parsed into Java objects by
 * Gson.
 */
public class GsonRequest<T> extends Request<T> {
	private final Gson gson = new Gson();
	private final Class<T> clazz;
	private final Map<String, String> params;
	private final Listener<T> listener;

	/**
	 * Make a POST request and return a parsed object from JSON.
	 *
	 * @param url
	 *            URL of the request to make
	 * @param params
	 *            Map of request params
	 * @param clazz
	 *            Relevant class object, for Gson's reflection
	 * 
	 * 
	 */
	public GsonRequest(String url, Map<String, String> params, Class<T> clazz,
			Listener<T> listener, ErrorListener errorListener) {
		super(Method.POST, url, errorListener);
		this.params = params;
		this.clazz = clazz;
		this.listener = listener;
	}

	public GsonRequest(int method, String url, Map<String, String> params,
			Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		this.params = params;
		this.clazz = clazz;
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
	protected void deliverResponse(T response) {
		listener.onResponse(response);
	}

	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try {
			String json = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			return Response.success(gson.fromJson(json, clazz),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JsonSyntaxException e) {
			return Response.error(new ParseError(e));
		}
	}

}
