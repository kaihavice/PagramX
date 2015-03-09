package com.xuyazhou.pagramx.bean;

import com.xuyazhou.bean.UserBean;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-03-05
 */

public class comment {
	private String id;
	private long created_time;
	private UserBean from;
	private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public UserBean getFrom() {
        return from;
    }

    public void setFrom(UserBean from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
