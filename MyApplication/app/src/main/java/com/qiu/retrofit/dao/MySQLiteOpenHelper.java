package com.qiu.retrofit.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.qiu.retrofit.entity.DaoMaster;
import com.qiu.retrofit.entity.UserDao;

/**
 * @author Qiu
 * @version V1.0
 * @Description:
 * @date 2016/12/5 8:45
 */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
	public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
		super(context, name, factory);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		MigrationHelper.migrate(db, UserDao.class
		);
	}

}
