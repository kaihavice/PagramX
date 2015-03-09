package com.xuyazhou.common.framework.view;

import android.app.Activity;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-02-09
 */

public interface IBaseView {

	public void showErrorMessage(String err);

	public void showErrorMessage(int idRes);

	public Activity getActivityContext();
}
