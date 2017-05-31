package com.qiu.retrofit.view.activity.custom;

import android.support.v7.widget.Toolbar;

import com.qiu.retrofit.R;
import com.qiu.retrofit.gui.custom.FourView;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;

/**
 * @author Qiu
 * @version V1.3
 * @Description:
 * @date 2017/2/4 17:44
 */
public class FourActivity extends BaseActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.customView)
	FourView customView;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_fourth);
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
