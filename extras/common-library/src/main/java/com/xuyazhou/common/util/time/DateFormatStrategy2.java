package com.xuyazhou.common.util.time;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 关系页面使用的日期格式策略
 */
public class DateFormatStrategy2 implements IDateFormatStrategy{
	public static final String DATE_FORMAT_TODAY = "今日 HH:mm"; // 日期是今日的日期格式
	public static final String DATE_FORMAT_YESTERDAY = "昨日"; // 日期是昨日的日期格式
	public static final String DATE_FORMAT_BEFORE_YESTERDAY = "yyyy/MM/dd"; // 日期是昨日之前的日期格式
	
	public String formatTime(long millisecond){
		String formatTime;
		
		int difInDay = TimeUtils.getDateDifference(System.currentTimeMillis(), millisecond);
		switch (difInDay) {
		case 0:{
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_TODAY);
			formatTime = sdf.format(new Date(millisecond));
			}
			break;
		case 1:{
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YESTERDAY);
			formatTime = sdf.format(new Date(millisecond));
			}
			break;
		default:{
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_BEFORE_YESTERDAY);
			formatTime = sdf.format(new Date(millisecond));
			}
			break;
		}
	
		return formatTime;
	}
}
