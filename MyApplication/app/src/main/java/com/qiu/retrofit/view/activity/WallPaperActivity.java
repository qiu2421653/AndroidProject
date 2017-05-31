package com.qiu.retrofit.view.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;

import com.qiu.retrofit.R;
import com.qiu.retrofit.core.service.VideoLiveWallpaper;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:屏保
 * @date 2017/5/31 10:37
 */
public class WallPaperActivity extends BaseActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_animator);
	}

	@Override
	public void initView() {
	}

	@Override
	public void initData() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}


	@OnClick({R.id.tv_first})
	public void onClick(View view) {
		int Id = view.getId();
		switch (Id) {
			case R.id.tv_first:
				VideoLiveWallpaper.setToWallPaper(this);
				break;
		}
	}

	@OnCheckedChanged(R.id.cb_voice)
	public void onCheck(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			// 静音
			VideoLiveWallpaper.voiceSilence(getApplicationContext());
		} else {
			VideoLiveWallpaper.voiceNormal(getApplicationContext());
		}
	}
}
