package com.xuyazhou.pagramx.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.xuyazhou.common.framework.view.IBaseView;
import com.xuyazhou.common.systembar.SystemBarTintManager;
import com.xuyazhou.common.systembar.SystemBarTintManager.SystemBarConfig;
import com.xuyazhou.pagramx.PagramXApplication;
import com.xuyazhou.pagramx.R;
import com.xuyazhou.pagramx.activity.wellcomeAcitivity;

/**
 * Activity 基类
 * 
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * 
 * Date: 2015-02-09
 */

public class SkeletonBaseActivity extends FragmentActivity implements IBaseView {
	protected SystemBarConfig config;
	protected SystemBarTintManager tintManager;

	@Override
	public void showErrorMessage(String err) {
		Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showErrorMessage(int idRes) {
		Toast.makeText(this, idRes, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PagramXApplication.getApplication().addActivity(this);

		tintManager = new SystemBarTintManager(this);
		config = tintManager.getConfig();

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			setTranslucentStatus(true);
			tintManager.setStatusBarTintEnabled(true);
			if (this instanceof wellcomeAcitivity) {
				tintManager.setStatusBarTintResource(R.color.white_alpha);
			} else {
				tintManager.setStatusBarTintResource(R.color.blue);
			}
		}

		// enable status bar tint

		// enable navigation bar tint
		// tintManager.setNavigationBarTintEnabled(true);

		//

	}

	@TargetApi(19)
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	@Override
	public Activity getActivityContext() {
		return this;
	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
