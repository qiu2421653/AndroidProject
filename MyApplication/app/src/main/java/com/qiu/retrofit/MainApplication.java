package com.qiu.retrofit;

import android.app.Application;
import android.content.Context;

import com.qiu.retrofit.dao.DBManager;
import com.qiu.retrofit.entity.DaoMaster;
import com.qiu.retrofit.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * @author Qiu
 * @version V1.3
 * @Description:
 * @date 2017/1/10 16:05
 */
public class MainApplication extends Application {
	private static Context sCtx;
	private static DaoSession daoSession;//数据库session
	private static Database db;//数据库
	private String dbName = "reft.db";

	@Override
	public void onCreate() {
		super.onCreate();
		sCtx = getApplicationContext();
		//数据库
		upGradeDb();
	}

	private void upGradeDb() {
		//数据库管理(创建|复制)
		DBManager.getInstance(sCtx).copyDBFile(dbName);
//		//1.0
		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, dbName);
		db = helper.getWritableDb();
		daoSession = new DaoMaster(db).newSession();

		// >1.0 版本升级 查看日志信息
//		MigrationHelper.DEBUG = true;
//		MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this, dbName, null);
//		DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
//		db = helper.getWritableDb();
//		daoSession = daoMaster.newSession();
	}

	public static Context getContext() {
		return sCtx;
	}
}
