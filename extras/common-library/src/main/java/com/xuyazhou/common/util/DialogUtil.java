package com.xuyazhou.common.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.xuyazhou.common_library.R;

/**
 * Created by mayuhan on 14/12/13.
 */
public class DialogUtil {
	public static void showSingleChoiceListDialog(Context context,
			String title, String[] items,
			DialogInterface.OnClickListener onItemClickCallBack) {
		Dialog alertDialog = new AlertDialog.Builder(context).setTitle(title)
				.setItems(items, onItemClickCallBack).create();
		alertDialog.setCanceledOnTouchOutside(true);
		alertDialog.show();
	}

	public static void showConfirmDialog(Context context, String title,
			String msg, DialogInterface.OnClickListener positive,
			DialogInterface.OnClickListener negative) {
		Dialog alertDialog = new AlertDialog.Builder(context).setTitle(title)
				.setMessage(msg).setPositiveButton(R.string.confirm, positive)
				.setNegativeButton(R.string.cancel, negative).create();
		alertDialog.setCanceledOnTouchOutside(true);
		alertDialog.show();
	}
}
