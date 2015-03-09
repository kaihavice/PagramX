package com.xuyazhou.pagramx;

import android.app.Activity;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.xuyazhou.common.image.ImageManager;
import com.xuyazhou.dao.DaoMaster;
import com.xuyazhou.dao.DaoSession;
import com.xuyazhou.dao.MyDevOpenHelper;
import com.xuyazhou.common.volley.RequestManager;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 *
 * Date: 2015-01-31
 */

public class PagramXApplication extends Application {
	private static PagramXApplication application;
	public DaoSession daoSession;
	private List<Activity> activityList = new LinkedList<Activity>();

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;

		RequestManager.init(this);
        ImageManager.init();
		SetupDatabase();// 数据库初始化

	}

	private void SetupDatabase() {
		MyDevOpenHelper helper = new MyDevOpenHelper(this, "PagramX-db", null);
		SQLiteDatabase db = helper.getWritableDatabase();
		DaoMaster daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
	}

	public DaoSession getDaoSession() {
		return daoSession;
	}

	public static PagramXApplication getApplication() {
		if (null == application) {
			application = new PagramXApplication();
		}
		return application;
	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity并finish
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}

}
