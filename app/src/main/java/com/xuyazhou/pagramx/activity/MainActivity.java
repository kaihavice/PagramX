package com.xuyazhou.pagramx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.xuyazhou.bean.UserBean;
import com.xuyazhou.common.image.ImageManager;
import com.xuyazhou.common.util.ActivityUtil;
import com.xuyazhou.dao.UserBeanDaoHelper;
import com.xuyazhou.pagramx.PagramXApplication;
import com.xuyazhou.pagramx.R;
import com.xuyazhou.pagramx.fragment.FeedFragment;
import com.xuyazhou.pagramx.fragment.LikesFragment;
import com.xuyazhou.pagramx.fragment.NearbyFragment;
import com.xuyazhou.pagramx.fragment.WhatsHotFragment;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;

/**
 *
 * 主界面
 *
 * Author: xuyazhou(xuyazhou18@gmail.com)
 *
 * Date: 2015-01-27
 */

public class MainActivity extends MaterialNavigationDrawer {

	@Override
	protected void setAccountDo() {

		this.addSection(newSection(getString(R.string.addAccount),
				R.mipmap.ic_add_black_24dp, new Intent(this,
						wellcomeAcitivity.class)));

		this.addSection(newSection(getString(R.string.logout),
				R.mipmap.ic_exit_to_app_black_24dp, new Intent(this,
						wellcomeAcitivity.class)));
	}

	@Override
	protected void gotoSearch() {
		ActivityUtil.moveToActivity(this, SearchActivity.class);
	}

	@Override
	public void init(Bundle bundle, Toolbar toolbar) {

		UserBeanDaoHelper userBeanDaoHelper = UserBeanDaoHelper.getInstance();
		UserBean user = userBeanDaoHelper.getDataById("userBean");

		MaterialAccount account = new MaterialAccount(this.getResources(),
				user.getFull_name(), user.getUsername(), user.getBio(),
				user.getWebsite(), R.mipmap.lampard_xu, R.mipmap.mat2);
		this.addAccount(account);

		FeedFragment feedFragment = new FeedFragment();
		feedFragment.setToolbar(toolbar);
		this.addSection(newSection(getString(R.string.feed),
				R.mipmap.ic_home_black_24dp, feedFragment).setSectionColor(
				getResources().getColor(R.color.blue)));

		this.addSection(newSection(getString(R.string.profile),
				R.mipmap.ic_account_box_black_24dp, new Intent(this,
						ProfileAcitivity.class)));

		this.addSection(newSection(getString(R.string.likes),
				R.mipmap.ic_favorite_black_24dp, new LikesFragment())
				.setSectionColor(getResources().getColor(R.color.blue)));

		this.addSection(newSection(getString(R.string.whatshot),
				R.mipmap.ic_whatshot_black_24dp, new WhatsHotFragment())
				.setSectionColor(getResources().getColor(R.color.blue)));

		this.addSection(newSection(getString(R.string.nearby),
				R.mipmap.ic_location_on_black_18dp, new NearbyFragment())
				.setSectionColor(getResources().getColor(R.color.blue)));

		// create bottom section

		this.addBottomSection(newSection(getString(R.string.settings),
				R.mipmap.ic_settings_black_24dp, new Intent(this,
						SettingActivity.class)));

		this.addBottomSection(newSection(getString(R.string.about),
				R.mipmap.ic_info_black_24dp, new Intent(this,
						AboutActivity.class)));

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		PagramXApplication.getApplication().exit();
	}
}
