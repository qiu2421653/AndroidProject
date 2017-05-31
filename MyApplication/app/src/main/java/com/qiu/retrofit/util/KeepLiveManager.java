package com.qiu.retrofit.util;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import com.qiu.retrofit.core.log.CLog;
import com.qiu.retrofit.core.service.ForegroundService;
import com.qiu.retrofit.view.activity.KeepLiveActivity;

import java.lang.ref.WeakReference;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:保活
 * @date 2017/4/13 10:15
 */
public class KeepLiveManager {

	private static final String TAG = "KeepLiveManager";

	private static final int FOREGROUND_ID = 0x1;
	private static KeepLiveManager sKeepLiveManager;
	public WeakReference<KeepLiveActivity> mWeakActivity = null;

	private KeepLiveManager() {

	}

	public static KeepLiveManager getInstance() {
		if (sKeepLiveManager == null) {
			synchronized (KeepLiveManager.class) {
				if (sKeepLiveManager == null) {
					sKeepLiveManager = new KeepLiveManager();
				}
			}
		}
		return sKeepLiveManager;
	}

	public void startKeepLiveActivity(Context context) {
		CLog.e(TAG, "startKeepLiveActivity-------->");

		Intent intent = new Intent(context, KeepLiveActivity.class);
		//在Activity之外startActivity（）需要传入FLAG_ACTIVITY_NEW_TASK
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	public void finishKeepLiveActivity() {
		if (mWeakActivity == null) {
			return;
		}
		KeepLiveActivity activity = mWeakActivity.get();
		if (activity != null) {
			activity.finish();
			mWeakActivity.clear();
		}
		mWeakActivity = null;
	}

	public void startKeepServiceLive(Context context) {
		context.startService(new Intent(context, ForegroundService.class));
		context.startService(new Intent(context, ForegroundService.InnerService.class));
	}

	/**
	 * 提升Service的优先级为前台Service，且不被用户感知
	 */
	public void setForeground(final Service service, final Service innerService) {
		if (service != null && innerService != null) {
			CLog.e(TAG, "前台了------------------->");
			service.startForeground(FOREGROUND_ID, new Notification());
			innerService.startForeground(FOREGROUND_ID, new Notification());
			//可以使通知栏的显示消失，并且Service的优先级处于1，仅次于与用户交互的Activity的优先级
			innerService.stopSelf();
		}
	}


}
