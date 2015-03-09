package com.xuyazhou.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-02-07
 */

public class MyDevOpenHelper extends DaoMaster.OpenHelper {
	public MyDevOpenHelper(Context context, String name,
			SQLiteDatabase.CursorFactory factory) {
		super(context, name, factory);
	}

	/**
	 * Called when the database needs to be upgraded. The implementation should
	 * use this method to drop tables, add tables, or do anything else it needs
	 * to upgrade to the new schema version.
	 * <p/>
	 * <p>
	 * The SQLite ALTER TABLE documentation can be found <a
	 * href="http://sqlite.org/lang_altertable.html">here</a>. If you add new
	 * columns you can use ALTER TABLE to insert them into a live table. If you
	 * rename or remove columns you can use ALTER TABLE to rename the old table,
	 * then create the new table and then populate the new table with the
	 * contents of the old table.
	 * </p>
	 * <p>
	 * This method executes within a transaction. If an exception is thrown, all
	 * changes will automatically be rolled back.
	 * </p>
	 *
	 * @param db
	 *            The database.
	 * @param oldVersion
	 *            The old database version.
	 * @param newVersion
	 *            The new database version.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {

		case 1:
			// 创建新表，注意createTable()是静态方法
			// OrderDao.createTable(db, true);

			// 加入新字段
			// db.execSQL("ALTER TABLE 'moments'ADD 'audio_path' TEXT;");

			// TODO
			// break;

		case 2:
			//NoteDao.createTable(db, false);

			// 加入新字段
			//db.execSQL("ALTER TABLE 'user'ADD 'result' TEXT;");

			break;
		}
	}
}
