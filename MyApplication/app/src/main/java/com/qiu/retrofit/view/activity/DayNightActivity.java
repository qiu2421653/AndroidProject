package com.qiu.retrofit.view.activity;

import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.qiu.retrofit.MainApplicationLike;
import com.qiu.retrofit.R;
import com.qiu.retrofit.core.rxbus.RxBus;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:白天/夜晚切换
 * @date 2017/3/10 14:16
 */
public class DayNightActivity extends BaseActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_daynight);
	}

	@Override
	public void initView() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@Override
	public void initData() {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//初始化右上角菜单
		getMenuInflater().inflate(R.menu.menu_day_night, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_day) {
			setThemeMode(AppCompatDelegate.MODE_NIGHT_NO);
			return true;
		}
		if (id == R.id.action_night) {
			setThemeMode(AppCompatDelegate.MODE_NIGHT_YES);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 设置ThemeMode模式,发送RxBus总线
	 *
	 * @param mode
	 */
	private void setThemeMode(final int mode) {
		MainApplicationLike.setTheme(this, mode);
		RxBus.getInstance().post("SplashActivity", mode);
	}

}
