package com.xuyazhou.pagramx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.gc.materialdesign.views.ButtonFlat;
import com.xuyazhou.bean.UserBean;
import com.xuyazhou.common.util.StringsUtils;
import com.xuyazhou.dao.UserBeanDaoHelper;
import com.xuyazhou.pagramx.R;
import com.xuyazhou.pagramx.base.SkeletonBaseActivity;
import com.xuyazhou.pagramx.instagram.Instagram;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 启动页面
 * 
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * 
 * Date: 2015-02-10
 */

public class wellcomeAcitivity extends SkeletonBaseActivity {

	@InjectView(R.id.button_login)
	ButtonFlat mButtonLogin;
	@InjectView(R.id.button_out)
	ButtonFlat mButtonOut;
	@InjectView(R.id.root_layout)
	RelativeLayout rootLayout;

	private Instagram instagram;
	private UserBeanDaoHelper userBeanDaoHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.wellcome_layout);

		ButterKnife.inject(this);

		rootLayout.setPadding(0, config.getStatusBarHeight(), 0, 0);

		userBeanDaoHelper = UserBeanDaoHelper.getInstance();

		if (userBeanDaoHelper.getDataById("userBean") != null) {
			mButtonLogin.setVisibility(View.GONE);
			mButtonOut.setVisibility(View.GONE);

			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					Intent intent = new Intent(wellcomeAcitivity.this,
							MainActivity.class);

					startActivity(intent);
				}
			};

			Timer timer = new Timer();

			timer.schedule(task, 1000);

		}

	}

	@OnClick(R.id.button_login)
	public void logIn() {
		instagram = new Instagram(this);

		instagram.authorize(mAuthListener);
	}

	@OnClick(R.id.button_out)
	public void out() {
		this.finish();
	}

	private Instagram.InstagramAuthListener mAuthListener = new Instagram.InstagramAuthListener() {
		@Override
		public void onSuccess(UserBean user) {
			finish();

			startActivity(new Intent(wellcomeAcitivity.this, MainActivity.class));
		}

		@Override
		public void onError(String error) {
			showToast(error);
		}

		@Override
		public void onCancel() {
			showToast("OK. Maybe later?");

		}
	};

	private void showToast(String text) {
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
	}

}
