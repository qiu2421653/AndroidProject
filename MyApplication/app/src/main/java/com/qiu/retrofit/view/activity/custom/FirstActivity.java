package com.qiu.retrofit.view.activity.custom;

import android.support.v7.widget.Toolbar;

import com.qiu.retrofit.R;
import com.qiu.retrofit.gui.custom.FirstView;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;

/**
 * @author Qiu
 * @version V1.3
 * @Description:
 * @date 2017/2/4 17:44
 */
public class FirstActivity extends BaseActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.customView)
	FirstView customView;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_first);
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
