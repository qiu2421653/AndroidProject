package com.qiu.retrofit.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.qiu.retrofit.R;
import com.qiu.retrofit.core.log.CLog;
import com.qiu.retrofit.core.rxbus.RxBus;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;
import com.qiu.retrofit.view.activity.search.SearchActivity;
import com.qiu.retrofit.view.activity.search.SearchJianActivity;
import com.qiu.retrofit.view.activity.search.SearchableActivity;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;

/**
 * 首页
 */
public class SplashActivity extends BaseActivity {

	private static final String TAG = "SplashActivity";

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	private Observable<Integer> themeMode;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_splash);
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@Override
	public void initView() {
		setSupportActionBar(toolbar);
	}

	@Override
	public void initData() {
		//注册RxJava(RxBus)
		setSubscribe();
	}

	@Override
	protected void onDestroy() {
		RxBus.getInstance().unregister(TAG, themeMode);
		super.onDestroy();
	}


	@OnClick({
			R.id.tv_retrofit, R.id.tv_drawer, R.id.tv_coord, R.id.tv_toolbarwithImage, R.id.tv_shareTrans, R.id.tv_tabLayoutwithImage, R.id.tv_bottomsheet,
			R.id.tv_tinker, R.id.tv_materialView, R.id.tv_percent, R.id.tv_recycler, R.id.tv_custom, R.id.tv_rxjava, R.id.tv_intent, R.id.tv_svg, R.id.tv_daynight,
			R.id.tv_search, R.id.tv_keep, R.id.tv_vanimator, R.id.tv_searchable, R.id.tv_wallpager
	})
	public void onMyClick(View view) {
		int Id = view.getId();
		switch (Id) {
			case R.id.tv_retrofit:
				//Retrofit 2.0
				startActivity(new Intent(this, RetrofitActivity.class));
				break;
			case R.id.tv_drawer:
				//DrawerLayout
				startActivity(new Intent(this, DrawerActivity.class));
				break;
			case R.id.tv_coord:
				//CoordinatorLayout
				startActivity(new Intent(this, CoordActivity.class));
				break;
			case R.id.tv_shareTrans:
				//CoordinatorLayout
				startActivity(new Intent(this, ShareTransActivity.class));
				break;
			case R.id.tv_toolbarwithImage:
				//ToolBarWithImage
				startActivity(new Intent(this, ToolBarImageActivity.class));
				break;
			case R.id.tv_tabLayoutwithImage:
				//TabLayoutWithImage
				startActivity(new Intent(this, TabsHeaderActivity.class));
				break;
			case R.id.tv_bottomsheet:
				//BottomSheet
				startActivity(new Intent(this, BottomSheetActivity.class));
				break;
			case R.id.tv_tinker:
				//Tinker
				startActivity(new Intent(this, TinkerActivity.class));
				break;
			case R.id.tv_materialView:
				//MaterialView
				startActivity(new Intent(this, MaterialViewActivity.class));
				break;
			case R.id.tv_percent:
				//Percent
				startActivity(new Intent(this, PercentActivity.class));
				break;
			case R.id.tv_recycler:
				//RecyclerView
				startActivity(new Intent(this, RecyclerViewActivity.class));
				break;
			case R.id.tv_custom:
				//CustomView
				startActivity(new Intent(this, CustomActivity.class));
				break;
			case R.id.tv_rxjava:
				//RxJava
				startActivity(new Intent(this, RxJavaActivity.class));
				break;
			case R.id.tv_intent:
				//Intent
				Intent intent = new Intent();
				intent.setAction("com.qiu.intentactivity");
				startActivity(intent);
				break;
			case R.id.tv_svg:
				//Svg
				startAnimActivity(new Intent(this, SvgActivity.class));
				break;
			case R.id.tv_daynight:
				//DayOrNight
				startAnimActivity(new Intent(this, DayNightActivity.class));
				break;
			case R.id.tv_search:
				//Search
				startAnimActivity(new Intent(this, SearchActivity.class));
				break;
			case R.id.tv_searchable:
				//SearchAble
				startAnimActivity(new Intent(this, SearchableActivity.class));
				break;
			case R.id.tv_searchjian:
				//SearchJian
				startAnimActivity(new Intent(this, SearchJianActivity.class));
				break;
			case R.id.tv_keep:
				//KeepLive
				startAnimActivity(new Intent(this, KeepLiveActivity.class));
				break;
			case R.id.tv_vanimator:
				//Animator
				startAnimActivity(new Intent(this, AnimatorActivity.class));
				break;
			case R.id.tv_wallpager:
				//Wallpaper
				startAnimActivity(new Intent(this, WallPaperActivity.class));
				break;
			default:
				break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == event.KEYCODE_POWER) {
			//关机
			CLog.e(TAG, "点击关机键-------->");
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_POWER) {
			CLog.e(TAG, "dispatchKeyEvent--->点击关机键-------->");
		}
		return super.dispatchKeyEvent(event);
	}

	/**
	 * 注册Subscribe
	 */
	private void setSubscribe() {
		themeMode = RxBus.getInstance().register(TAG, String.class);
		themeMode.subscribe(new Action1<Integer>() {
			@Override
			public void call(Integer mode) {
				CLog.e("themeMode", "--------> " + mode);
				AppCompatDelegate.setDefaultNightMode(mode);
				getDelegate().setLocalNightMode(mode);
				recreate();
			}
		});
	}
}
