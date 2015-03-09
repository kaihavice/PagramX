package com.xyz;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class MyDaoGenerator {

	public static void main(String args[]) throws Exception {
		Schema schema = new Schema(1, "com.xuyazhou.bean");
		schema.setDefaultJavaPackageDao("com.xuyazhou.dao");
		// addCustomerOrder(schema);
		// addNote(schema);
		initUserBean(schema);
		addJson(schema);
		new DaoGenerator().generateAll(schema, args[0]);
	}

	private static void initUserBean(Schema schema) {
		Entity userBean = schema.addEntity("UserBean");// 表名
		userBean.setTableName("user"); // 可以对表重命名
		// 实现序列化接口
		userBean.implementsSerializable();
		// userBean.addLongProperty("index_id").primaryKey().autoincrement();//
		// 设置一个自增长ID列为主键：

		userBean.addStringProperty("index_id").primaryKey().index();// 主键，索引
		userBean.addStringProperty("id");
		userBean.addStringProperty("username");
		userBean.addStringProperty("bio");
		userBean.addStringProperty("website");
		userBean.addStringProperty("profile_picture");
		userBean.addStringProperty("full_name");
		userBean.addStringProperty("access_token");
		userBean.addStringProperty("result");

	}

	private static void addJson(Schema schema) {
		Entity json = schema.addEntity("Json");
		json.addStringProperty("data_type").primaryKey().index();
		json.addStringProperty("data");
		json.addStringProperty("createTime");
	}

	private static void addCustomerOrder(Schema schema) {
		Entity customer = schema.addEntity("Customer"); // 表名
		customer.addIdProperty();
		customer.addStringProperty("name").notNull();

		Entity order = schema.addEntity("Order");
		order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
		order.addIdProperty();
		Property orderDate = order.addDateProperty("date").getProperty();
		Property customerId = order.addLongProperty("customerId").notNull()
				.getProperty();
		order.addToOne(customer, customerId);

		ToMany customerToOrders = customer.addToMany(order, customerId);
		customerToOrders.setName("orders");
		customerToOrders.orderAsc(orderDate);
	}
}
