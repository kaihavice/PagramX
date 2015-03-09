package com.xuyazhou.dao;

import java.util.List;

import android.text.TextUtils;

import com.xuyazhou.bean.JsonData;
import com.xuyazhou.pagramx.PagramXApplication;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-03-07
 */

public class JsonDaoHelper implements MyDaoHelperInterface {

	private static JsonDaoHelper instance;
	private JsonDao jsonDao;

	public JsonDaoHelper() {
		try {
			jsonDao = PagramXApplication.getApplication().getDaoSession()
					.getJsonDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JsonDaoHelper getInstance() {
		if (instance == null) {
			instance = new JsonDaoHelper();
		}
		return instance;
	}

	@Override
	public <T> void addData(T t) {

		if (jsonDao != null && t != null) {
			jsonDao.insertOrReplace((JsonData) t);
		}

	}

	@Override
	public void deleteData(String id) {
		if (jsonDao != null && !TextUtils.isEmpty(id)) {
			jsonDao.deleteByKey(id);
		}
	}

	@Override
	public JsonData getDataById(String id) {
		if (jsonDao != null && !TextUtils.isEmpty(id)) {
			return jsonDao.load(id);
		}
		return null;
	}

	@Override
	public List getAllData() {
		if (jsonDao != null) {
			return jsonDao.loadAll();
		}
		return null;
	}

	@Override
	public boolean hasKey(String id) {
		if (jsonDao == null || TextUtils.isEmpty(id)) {
			return false;
		}
		QueryBuilder<JsonData> qb = jsonDao.queryBuilder();
		qb.where(JsonDao.Properties.Data_type.eq(id));
		long count = qb.buildCount().count();
		return count > 0;
	}

	@Override
	public long getTotalCount() {
		if (jsonDao == null) {
			return 0;
		}
		QueryBuilder<JsonData> qb = jsonDao.queryBuilder();
		return qb.buildCount().count();
	}

	@Override
	public void deleteAll() {
		if (jsonDao != null) {
			jsonDao.deleteAll();
		}
	}
}
