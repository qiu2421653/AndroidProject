package com.qiu.retrofit.core.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.qiu.retrofit.util.KeepLiveManager;


/**
 * @author @Qiu
 * @version V1.0
 * @Description: 服务
 * @date 2017/4/13 10:17
 */
public class ForegroundService extends Service {

	private static ForegroundService sForegroundService = null;

	public ForegroundService() {
		sForegroundService = this;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		//Service被杀死后进行重建，不传Intent
		//第一次杀死会在5秒后重启，第二次杀死后会在10秒后重启，第三次是20秒
		//一旦短时间内被杀死五次，则系统不再拉起
		return Service.START_STICKY;
		/*
			//Service被杀死后不进行重建
            return Service.START_NOT_STICKY;
            //Service被杀死后进行重建但是不传递Intent
            return Service.START_REDELIVER_INTENT;
         */
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	public static class InnerService extends Service {

		@Nullable
		@Override
		public IBinder onBind(Intent intent) {
			return null;
		}

		@Override
		public int onStartCommand(Intent intent, int flags, int startId) {
			KeepLiveManager.getInstance().setForeground(sForegroundService, this);
			return super.onStartCommand(intent, flags, startId);
		}
	}
}
