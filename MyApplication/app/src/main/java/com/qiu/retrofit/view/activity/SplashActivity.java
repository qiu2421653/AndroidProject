package com.qiu.retrofit.view.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_splash);
		setSupportActionBar(toolbar);
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


	@OnClick({R.id.tv_retrofit, R.id.tv_drawer, R.id.tv_coord, R.id.tv_shareTrans})
	public void onMyClick(View view) {
		int Id = view.getId();
		switch (Id) {
			case R.id.tv_retrofit:
				//Retrofit 2.0
				startActivity(new Intent(this, MainActivity.class));
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
			default:
				break;
		}
	}
}
