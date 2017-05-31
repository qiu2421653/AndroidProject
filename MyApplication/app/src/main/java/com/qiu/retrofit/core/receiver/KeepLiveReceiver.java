package com.qiu.retrofit.core.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.qiu.retrofit.core.log.CLog;
import com.qiu.retrofit.util.KeepLiveManager;

/**
 * @author Qiu
 * @version V1.0
 * @Description:保活
 * @date 2017/4/13 10:14
 */
public class KeepLiveReceiver extends BroadcastReceiver {


	private static final String TAG = "KeepLiveReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();

		if (action.equals(Intent.ACTION_SCREEN_OFF)) {
			CLog.e(TAG, "屏幕熄灭");
			KeepLiveManager.getInstance().startKeepLiveActivity(context);
		} else if (action.equals(Intent.ACTION_SCREEN_ON)) {
			CLog.e(TAG, "屏幕点亮");
			KeepLiveManager.getInstance().finishKeepLiveActivity();
		}
		KeepLiveManager.getInstance().startKeepServiceLive(context);
	}
}
