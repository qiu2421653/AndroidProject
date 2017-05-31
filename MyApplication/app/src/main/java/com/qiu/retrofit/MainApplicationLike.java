package com.qiu.retrofit;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.qiu.retrofit.core.handler.FetchPatchHandler;
import com.qiu.retrofit.core.receiver.KeepLiveReceiver;
import com.qiu.retrofit.dao.ConfigDao;
import com.qiu.retrofit.dao.DBManager;
import com.qiu.retrofit.dao.MySQLiteOpenHelper;
import com.qiu.retrofit.entity.DaoMaster;
import com.qiu.retrofit.entity.DaoSession;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tinkerpatch.sdk.TinkerPatch;

import org.greenrobot.greendao.database.Database;

/**
 * @author Qiu
 * @version V1.3
 * @Description:Tinker测试
 * @date 2017/1/10 16:05
 */
@SuppressWarnings("unused")
@DefaultLifeCycle(application = "com.qiu.retrofit.MainApplication", flags = ShareConstants.TINKER_ENABLE_ALL, loadVerifyFlag = false)
public class MainApplicationLike extends DefaultApplicationLike {
	private static final String TAG = "MainApplicationLike";
	private static final String dbName = "reft.db";
	private static String THEME_KEY = "theme_mode";
	private static Context sCtx;
	private static DaoSession daoSession;//数据库session
	private static Database db;//数据库
	private static int themeMode;//白天/夜晚模式

	public MainApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
		super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void onBaseContextAttached(Context base) {
		super.onBaseContextAttached(base);
		MultiDex.install(base);
//		TinkerManager.setTinkerApplicationLike(this);
//		TinkerManager.initFastCrashProtect();
//		//should set before tinker is installed
//		TinkerManager.setUpgradeRetryEnable(true);
//		//optional set logIml, or you can use default debug log
//		TinkerInstaller.setLogIml(new MyLogImp());
//		//installTinker after load multiDex
//		//or you can put com.tencent.tinker.** to main dex
//		TinkerManager.installTinker(this);
//		Tinker tinker = Tinker.with(getApplication());
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
		getApplication().registerActivityLifecycleCallbacks(callback);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		sCtx = getApplication();
		//数据库
		upGradeDb();
		//TinkerPatch设置
		upTinkerPatch();
		//白天/夜晚切换
		initThemeMode();
		//注册保活服务
		//registerKeepLive();
	}

	/**
	 * 保活服务
	 */
	private void registerKeepLive() {
		//屏幕关闭和打开的广播必须动态注册
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_SCREEN_ON);
		getContext().registerReceiver(new KeepLiveReceiver(), filter);
	}

	/**
	 * 数据库管理
	 */
	private void upGradeDb() {
		//数据库管理(创建|复制)
		DBManager.getInstance(sCtx).copyDBFile(dbName);
//		//1.0
//		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(sCtx, dbName);
//		db = helper.getWritableDb();
//		daoSession = new DaoMaster(db).newSession();

		// >1.0 版本升级 查看日志信息
		MigrationHelper.DEBUG = true;
		MySQLiteOpenHelper helper = new MySQLiteOpenHelper(sCtx, dbName, null);
		db = helper.getWritableDb();
		daoSession = new DaoMaster(helper.getWritableDatabase()).newSession();
	}

	/**
	 * Tinker
	 */
	private void upTinkerPatch() {
		// 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化 SDK
		TinkerPatch.init(this)
				.reflectPatchLibrary()
				.setPatchRollbackOnScreenOff(true)
				.setPatchRestartOnSrceenOff(true);

		// 每隔3个小时去访问后台时候有更新,通过handler实现轮训的效果
		new FetchPatchHandler().fetchPatchWithInterval(3);
	}


	/**
	 * 初始化白天/黑夜模式
	 * AppCompatDelegate.MODE_NIGHT_YES 夜间模式
	 * AppCompatDelegate.MODE_NIGHT_NO  白天模式
	 */
	private void initThemeMode() {
		themeMode = ConfigDao.getInstance().getInt(THEME_KEY);
		AppCompatDelegate.setDefaultNightMode(themeMode);
	}


	/**
	 * 设置白天/黑夜模式
	 *
	 * @param activity
	 * @param mode
	 */
	public static void setTheme(AppCompatActivity activity, int mode) {
		if (themeMode == mode) {
			return;
		}
		AppCompatDelegate.setDefaultNightMode(mode);
		activity.getDelegate().setLocalNightMode(mode);
		themeMode = mode;
		ConfigDao.getInstance().setInteger(THEME_KEY, themeMode);
		activity.recreate();
	}


	public static Context getContext() {
		return sCtx;
	}

}
