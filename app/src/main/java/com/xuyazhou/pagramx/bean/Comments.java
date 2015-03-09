package com.xuyazhou.pagramx.bean;


import java.util.ArrayList;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-03-05
 */

public class Comments {
	private ArrayList<comment> data;
	int count;

    public ArrayList<comment> getData() {
        return data;
    }

    public void setData(ArrayList<comment> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
