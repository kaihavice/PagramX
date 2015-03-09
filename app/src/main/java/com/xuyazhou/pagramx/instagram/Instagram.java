package com.xuyazhou.pagramx.instagram;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.xuyazhou.bean.UserBean;
import com.xuyazhou.dao.UserBeanDaoHelper;
import com.xuyazhou.pagramx.api.PagramXApi;
import com.xuyazhou.pagramx.bean.GetUserInfo;
import com.xuyazhou.common.volley.MyJsonObjectRequest;
import com.xuyazhou.common.volley.RequestManager;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 *
 * Date: 2015-02-12
 */

public class Instagram {

	private Context context;
	private InstagramDialog instagramDialog;
	private UserBeanDaoHelper userBeanDaoHelper;
	private InstagramAuthListener mListener;

	public Instagram(final Context context) {
		this.context = context;

		userBeanDaoHelper = UserBeanDaoHelper.getInstance();

		instagramDialog = new InstagramDialog(context,
				PagramXApi.AUTHORIZATION, PagramXApi.REDIRECT_URI,
				new InstagramDialog.InstagramDialogListener() {
					@Override
					public void onSuccess(String code) {
						retreiveAccessToken(context, code);
					}

					@Override
					public void onCancel() {
						mListener.onCancel();
					}

					@Override
					public void onError(String error) {
						mListener.onError(error);
					}
				});
	}

	/**
	 * Retreive access token.
	 *
	 * @param code
	 */
	private void retreiveAccessToken(Context context, String code) {

		final ProgressDialog progressDlg;

		progressDlg = new ProgressDialog(context);

		progressDlg.setMessage("Getting access token...");
		progressDlg.show();

		RequestManager.getRequestQueue().add(
				new MyJsonObjectRequest(PagramXApi.ACCESS_TOKEN,
						getParams(code), new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {

								GetUserInfo data = new Gson().fromJson(
										response.toString(), GetUserInfo.class);

								UserBean userBean = data.getUser();
								userBean.setAccess_token(data.getAccess_token());
								userBean.setIndex_id("userBean");

								userBeanDaoHelper.addData(userBean);

								progressDlg.dismiss();

								mListener.onSuccess(userBean);

							}
						}, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {

							}
						}));
	}

	protected Map<String, String> getParams(String code) {
		Map<String, String> params = new HashMap<String, String>();

		params.put("client_id", PagramXApi.CLIENT_ID);
		params.put("client_secret", PagramXApi.CLIENT_SECRET);
		params.put("grant_type", PagramXApi.GRANT_TYPE);
		params.put("redirect_uri", PagramXApi.REDIRECT_URI);
		params.put("code", code);

		return params;
	}

	/**
	 * Authorize user.
	 *
	 * @param listener
	 *            Auth listner
	 */
	public void authorize(InstagramAuthListener listener) {
		mListener = listener;

		instagramDialog.show();
	}

	public interface InstagramAuthListener {

		public abstract void onSuccess(UserBean user);

		public abstract void onError(String error);

		public abstract void onCancel();
	}

}
