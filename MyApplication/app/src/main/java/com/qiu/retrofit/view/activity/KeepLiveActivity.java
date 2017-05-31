/*
 * 提高进程优先级:
 *    在屏幕关闭时创建一个1像素的Activity处于前台，将进程优先级从4提升到1。当屏幕开启时，关闭创建的Activity,并设置不显示在最近列表中，防止用户感知。
 *    该方式主要用于防止系统管理工具在检测到锁屏事件后一段时间内（一般为5分钟）会杀死后台进程，已达到省电的目的。
 *    创建一个Service,并将该Service设置为前台进程，然后再在该Service内部创建一个InnerService，用于取消通知栏。
 * 对死亡的进程拉活:
 *    监听系统广播（网络变化、屏幕亮灭、开机等），在收到广播时拉活死亡的进程。
 *    在进程死亡时发出一条自定义的广播，然后收到该广播后进行进程的拉活。
 *    利用Native进程拉活（有待学习）。
 *    利用第三方推送拉活。
 */
package com.qiu.retrofit.view.activity;

import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.qiu.retrofit.core.log.CLog;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.util.KeepLiveManager;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * @author Qiu
 * @version V1.0
 * @Description:保活
 * @date 2017/4/13
 */
public class KeepLiveActivity extends BaseActivity {

	private static final String TAG = "KeepLiveActivity";

	@Override
	public void onCreateMyView() {
		keepWindow();
	}

	@Override
	public void initView() {
	}

	@Override
	public void initData() {
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	/**
	 * 包活方法:启动一个1PX的透明窗口到桌面 ，
	 */
	private void keepWindow() {
		Window window = getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		params.x = 0;
		params.y = 0;
		params.width = 1;
		params.height = 1;

		window.setGravity(Gravity.TOP | Gravity.LEFT);
		window.setAttributes(params);

		KeepLiveManager.getInstance().mWeakActivity = new WeakReference<>(this);
		CLog.e(TAG, "启动------------>");
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		CLog.e(TAG, "销毁------------>");
	}
}
