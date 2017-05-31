package com.qiu.retrofit.view.activity.quick;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

import com.qiu.retrofit.R;

import java.lang.ref.WeakReference;

/**
 * @author @Qiu
 * @version V1.0
 * @Description:快速启动
 * @date 2017/3/10 9:32
 */
public class QuickActivity extends AppCompatActivity {

	private MyHandler mHandler = new MyHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quick);
		final StartFragment startFragment = new StartFragment();
		final ViewStub mainLayout = (ViewStub) findViewById(R.id.content_viewstub);
		//1.先显示启动页面
		FragmentManager fManager = getSupportFragmentManager();
		if (fManager != null) {
			FragmentTransaction fragmentTransaction = fManager.beginTransaction();
			if (fragmentTransaction != null) {
				fragmentTransaction.replace(R.id.container, startFragment);
				fragmentTransaction.commit();
			}
		}
		//2.如果主页面有网络耗时操作，可以现在就开始做了
		new Thread(new Runnable() {
			@Override
			public void run() {
				//耗时操作模拟
				SystemClock.sleep(3000);
				mHandler.sendEmptyMessage(0);
			}
		}).start();

		//3.渲染完毕后吗，加载主页布局
		getWindow().getDecorView().post(new Runnable() {
			@Override
			public void run() {
				View mainView = mainLayout.inflate();
				initView(mainView);
			}
		});

		//4.启动页有动画，延迟一下播放完动画，执行remove
		getWindow().getDecorView().post(new Runnable() {
			@Override
			public void run() {
				mHandler.postDelayed(new DelayRunnableImpl(QuickActivity.this, startFragment), 2000);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mHandler != null) {
			mHandler.removeCallbacksAndMessages(null);
		}
	}

	private void initView(View mainView) {
		if (mainView != null) {

		}
	}

	private static class MyHandler extends Handler {
		private WeakReference<QuickActivity> mWr;

		public MyHandler(QuickActivity qActivity) {
			mWr = new WeakReference<QuickActivity>(qActivity);
		}

		@Override
		public void handleMessage(Message msg) {
			QuickActivity quickActivity = mWr.get();
			if (quickActivity != null) {
			}
		}
	}

	private class DelayRunnableImpl implements Runnable {
		private WeakReference<Context> contextWref;
		private WeakReference<Fragment> fragmentWref;

		public DelayRunnableImpl(Context context, Fragment fragment) {
			contextWref = new WeakReference<Context>(context);
			fragmentWref = new WeakReference<Fragment>(fragment);
		}

		@Override
		public void run() {
			AppCompatActivity context = (AppCompatActivity) contextWref.get();
			if (context != null) {
				FragmentManager fragmentManager = context.getSupportFragmentManager();
				if (fragmentManager != null) {
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					StartFragment startFragment = (StartFragment) fragmentWref.get();
					if (startFragment != null) {
						fragmentTransaction.remove(startFragment).commit();
					}
				}
			}
		}
	}
}
