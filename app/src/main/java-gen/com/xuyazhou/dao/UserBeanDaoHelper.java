package com.xuyazhou.dao;

import java.util.List;

import android.text.TextUtils;

import com.xuyazhou.bean.UserBean;
import com.xuyazhou.pagramx.PagramXApplication;

import de.greenrobot.dao.query.QueryBuilder;

public class UserBeanDaoHelper implements MyDaoHelperInterface {
	private static UserBeanDaoHelper instance;
	private UserBeanDao userBeanDao;

	private UserBeanDaoHelper() {
		try {
			userBeanDao = PagramXApplication.getApplication().getDaoSession()
					.getUserBeanDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UserBeanDaoHelper getInstance() {
		if (instance == null) {
			instance = new UserBeanDaoHelper();
		}

		return instance;
	}

	@Override
	public <T> void addData(T bean) {
		if (userBeanDao != null && bean != null) {
			userBeanDao.insertOrReplace((UserBean) bean);
		}
	}

	@Override
	public void deleteData(String id) {
		if (userBeanDao != null && !TextUtils.isEmpty(id)) {
			userBeanDao.deleteByKey(id);
		}
	}

	@Override
	public UserBean getDataById(String id) {
		if (userBeanDao != null && !TextUtils.isEmpty(id)) {
			return userBeanDao.load(id);
		}
		return null;
	}

	@Override
	public List getAllData() {
		if (userBeanDao != null) {
			return userBeanDao.loadAll();
		}
		return null;
	}

	@Override
	public boolean hasKey(String id) {
		if (userBeanDao == null || TextUtils.isEmpty(id)) {
			return false;
		}

		QueryBuilder<UserBean> qb = userBeanDao.queryBuilder();
		qb.where(UserBeanDao.Properties.Id.eq(id));
		long count = qb.buildCount().count();
		return count > 0;
	}

	@Override
	public long getTotalCount() {
		if (userBeanDao == null) {
			return 0;
		}

		QueryBuilder<UserBean> qb = userBeanDao.queryBuilder();
		return qb.buildCount().count();
	}

	@Override
	public void deleteAll() {
		if (userBeanDao != null) {
			userBeanDao.deleteAll();
		}
	}

	public void testQueryBy() {
		// List joes = userBeanDao.queryBuilder()
		// .where(UserBeanDao.Properties.Phone.eq("Joe"))
		// .orderAsc(UserBeanDao.Properties.Phone).list();
		//
		// QueryBuilder<UserBean> qb = userBeanDao.queryBuilder();
		// qb.where(qb.or(UserBeanDao.Properties.Phone.gt(10698.85), qb.and(
		// UserBeanDao.Properties.Phone.eq("id"),
		// UserBeanDao.Properties.Phone.eq("xx"))));
		//
		// qb.orderAsc(UserBeanDao.Properties.Id);// 排序依据
		// qb.list();
	}
}