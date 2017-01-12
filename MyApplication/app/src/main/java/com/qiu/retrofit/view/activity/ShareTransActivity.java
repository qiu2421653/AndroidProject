package com.qiu.retrofit.view.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
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
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, ivShare, "transition_animation_news_photos");
			mContext.startActivity(intent, options.toBundle());
		} else {
			//让新的Activity从一个小的范围扩大到全屏
			ActivityOptionsCompat options = ActivityOptionsCompat
					.makeScaleUpAnimation(ivShare, ivShare.getWidth() / 2, ivShare.getHeight() / 2, 0, 0);
			ActivityCompat.startActivity((Activity) mContext, intent, options.toBundle());
		}
	}
}
