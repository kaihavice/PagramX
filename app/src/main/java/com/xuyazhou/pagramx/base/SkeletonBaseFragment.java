package com.xuyazhou.pagramx.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.xuyazhou.common.framework.view.IBaseView;
import com.xuyazhou.pagramx.R;

import org.json.JSONObject;

import java.util.Map;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 *
 * Date: 2015-02-09
 */

public abstract class SkeletonBaseFragment extends Fragment implements
		IBaseView {
	@Override
	public void showErrorMessage(String err) {
		if (getActivity() != null && !getActivity().isFinishing())
			Toast.makeText(getActivity(), err, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showErrorMessage(int idRes) {
		if (getActivity() != null && !getActivity().isFinishing())
			Toast.makeText(getActivity(), idRes, Toast.LENGTH_SHORT).show();

	}

	@Override
	public Activity getActivityContext() {
		return getActivity();
	}

	protected Response.Listener<JSONObject> responseListener(final int dataType) {
		return new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				processData(response, dataType);
			}
		};
	}

	protected Response.ErrorListener errorListener() {
		return new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if (error instanceof TimeoutError) {
					Toast.makeText(getActivityContext(), R.string.timeouterror,
							Toast.LENGTH_LONG).show();
				} else if (error instanceof NetworkError) {
					Toast.makeText(getActivityContext(), R.string.httperror,
							Toast.LENGTH_LONG).show();
				} else if (error instanceof ParseError) {
					Toast.makeText(getActivityContext(), R.string.parseerror,
							Toast.LENGTH_LONG).show();
				}
			}
		};
	}

	protected abstract void processData(JSONObject response, int dataType);

}
