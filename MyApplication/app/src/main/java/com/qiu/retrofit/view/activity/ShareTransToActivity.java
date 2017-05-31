package com.qiu.retrofit.view.activity;

import android.support.v7.widget.Toolbar;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;

/**
 * @author Qiu
 * @version V1.3
 * @Description:共享元素页面(目标页)
 * @date 2017/1/12 17:09
 */
public class ShareTransToActivity extends BaseActivity {
	private static final String TAG = "ShareTransToActivity";
	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@Override
	public void onCreateMyView() {
//		getWindow().setEnterTransition(new Fade().setDuration(1000));
//		getWindow().setExitTransition(new Fade().setDuration(1000));
		setContentView(R.layout.activity_sharetransto);
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
}