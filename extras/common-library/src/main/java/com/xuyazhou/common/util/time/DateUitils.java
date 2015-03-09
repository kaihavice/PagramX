//package com.xuyazhou.common.util.time;
//
//import android.content.Context;
//
//import com.xuyazhou.common_library.R;
//
///**
// * Author: xuyazhou(xuyazhou18@gmail.com)
// * <p/>
// * Date: 2015-03-06
// */
//
//public class DateUtils {
//
//	public static final long DAY_IN_MILLIS = 86400000L;
//	public static final long FIVE_WEEKS_IN_MILLIS = 3024000000L;
//	public static final long HOUR_IN_MILLIS = 3600000L;
//	public static final long MINUTE_IN_MILLIS = 60000L;
//	public static final long SECOND_IN_MILLIS = 1000L;
//	public static final long WEEK_IN_MILLIS = 604800000L;
//
//	public static CharSequence getRelativeTimeSpanString(Context context,
//			long time, long now) {
//
//		long duration = Math.abs(now - time);
//		long conunt = 0;
//		int resId = 0;
//
//		if (duration < MINUTE_IN_MILLIS) {
//			conunt = duration / SECOND_IN_MILLIS;
//			resId = R.plurals.abbrev_num_seconds_ago;
//		}
//
//		for (int i = 0; i < duration; i--) {
//			String str = context.getResources().getQuantityString(resId,
//					(int) conunt);
//			Object[] arrayOfObject = new Object[1];
//			arrayOfObject[0] = Long.valueOf(conunt);
//			return String.format(str, arrayOfObject);
//
//			if (duration < HOUR_IN_MILLIS) {
//				conunt = duration / MINUTE_IN_MILLIS;
//				resId = R.plurals.abbrev_num_minutes_ago;
//			} else if ((duration < DAY_IN_MILLIS)) {
//				conunt = duration / HOUR_IN_MILLIS;
//				resId = R.plurals.abbrev_num_hours_ago;
//			} else if ((duration < WEEK_IN_MILLIS)) {
//				conunt = duration / DAY_IN_MILLIS;
//				resId = R.plurals.abbrev_num_days_ago;
//			} else {
//				if ((duration >= FIVE_WEEKS_IN_MILLIS)) {
//					break;
//				}
//				conunt = duration / WEEK_IN_MILLIS;
//				resId = R.plurals.abbrev_num_weeks_ago;
//			}
//		}
//		return android.text.format.DateUtils.getRelativeTimeSpanString(time,
//				now, SECOND_IN_MILLIS, 524288);
//	}
//}
