package com.qiu.retrofit.view.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Qiu
 * @version V1.3
 * @Description:
 * @date 2017/1/12 17:09
 */
public class ShareTransActivity extends BaseActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.iv_share)
	ImageView ivShare;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_sharetrans);
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

	@OnClick(R.id.iv_share)
	public void onClick(View view) {
		shareTransition();
	}

	/**
	 * 共享元素测试
	 */
	private void shareTransition() {
		Intent intent = new Intent(mContext, ShareTransToActivity.class);
		if (android.os.Build.VERSION.SDK_INT > 20) {
			startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, ivShare, "transitionImg").toBundle());
		} else {
			startActivity(intent);
		}
	}
}
