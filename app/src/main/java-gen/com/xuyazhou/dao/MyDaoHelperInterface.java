package com.xuyazhou.dao;

import java.util.List;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-02-07
 */

public interface MyDaoHelperInterface {

	public <T> void addData(T t);

	public void deleteData(String id);



	public <T> T getDataById(String id);

	public List getAllData();

	public boolean hasKey(String id);

	public long getTotalCount();

	public void deleteAll();
}
