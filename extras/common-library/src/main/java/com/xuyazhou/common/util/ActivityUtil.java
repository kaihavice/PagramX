package com.xuyazhou.common.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActivityUtil {

	public static void moveToActivity(Activity activity, Class targetClass,
			Bundle bundle) {

		if (activity == null || activity.isFinishing()) {
			return;
		}
		Intent intent = new Intent(activity, targetClass);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivity(intent);
	}

	public static void moveToActivity(Activity activity, Class targetClass) {
		moveToActivity(activity, targetClass, null);
	}

	public static void moveToActivityForResult(Activity activity,
			Class targetClass, int requestCode, Bundle bundle) {
		if (activity == null || activity.isFinishing()) {
			return;
		}
		Intent intent = new Intent(activity, targetClass);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivityForResult(intent, requestCode);
	}

	public static void moveToActivityForResult(Activity activity,
			Class targetClass, int resultCode) {
		moveToActivityForResult(activity, targetClass, resultCode, null);
	}

}
