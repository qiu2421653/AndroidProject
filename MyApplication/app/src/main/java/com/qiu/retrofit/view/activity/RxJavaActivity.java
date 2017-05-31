package com.qiu.retrofit.view.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.qiu.retrofit.R;
import com.qiu.retrofit.core.log.CLog;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:RxJava
 * @date 2017/2/8 9:03
 */
public class RxJavaActivity extends BaseActivity {
	private static final String TAG = "RxJavaActivity";
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.iv_iamge)
	ImageView iv_iamge;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_rxjava);
	}

	@Override
	public void initView() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void initData() {
	}



	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@OnClick({R.id.btn_from, R.id.btn_image})
	public void onClick(View view) {
		int Id = view.getId();
		switch (Id) {
			case R.id.btn_from:
				//测试点击
				initObFrom();
				break;
			case R.id.btn_image:
				//测试点击
				initObImage();
				break;
		}
	}

	/**
	 * from输出
	 */
	private void initObFrom() {
		String ss[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",};
		Observable.from(ss).
				subscribeOn(Schedulers.io()).// 指定 subscribe() 发生在 IO 线程
				observeOn(AndroidSchedulers.mainThread()).// 指定 Subscriber 的回调发生在主线程
				subscribe(new Action1<String>() {
			@Override
			public void call(String s) {
				CLog.e(TAG, "from:" + s);
			}
		});
	}

	/**
	 * 显示图片
	 */
	private void initObImage() {
		final int drawableRes = R.mipmap.header;
		Observable.create(new Observable.OnSubscribe<Drawable>() {
			@Override
			public void call(Subscriber<? super Drawable> subscriber) {
				Drawable drawable = getTheme().getDrawable(drawableRes);
				subscriber.onNext(drawable);
				subscriber.onCompleted();
			}
		}).
				subscribeOn(Schedulers.io()).// 指定 subscribe() 发生在 IO 线程
				observeOn(AndroidSchedulers.mainThread()).// 指定 Subscriber 的回调发生在主线程
				subscribe(new Observer<Drawable>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable e) {
				CLog.e(TAG, "initObImage--->Error");
			}

			@Override
			public void onNext(Drawable drawable) {
				iv_iamge.setVisibility(iv_iamge.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
				iv_iamge.setBackground(drawable);
			}
		});
	}

	/**
	 * 观察者
	 */
	private void initObserver() {

	}
}
