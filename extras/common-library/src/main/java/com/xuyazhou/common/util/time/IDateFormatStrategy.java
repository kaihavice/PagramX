package com.xuyazhou.common.util.time;


public interface IDateFormatStrategy {
    /**
     * 格式化日期
     *
     * @param millisecond 日期
     * @return
     */
	String formatTime(long millisecond);
}
