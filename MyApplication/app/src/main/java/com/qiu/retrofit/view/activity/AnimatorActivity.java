package com.qiu.retrofit.view.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.ValueAnimator.FirstActivity;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Qiu
 * @version V1.0
 * @Description:属性动画
 * @date 2017/4/13 17:40
 */
public class AnimatorActivity extends BaseActivity {
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


	@OnClick({R.id.tv_first, R.id.tv_second, R.id.tv_third, R.id.tv_fourth, R.id.tv_fifth, R.id.tv_sixth, R.id.tv_seven, R.id.tv_eighth, R.id.tv_ninth, R.id.tv_drag, R.id.tv_ten, R.id.tv_eleven})
	public void onClick(View view) {
		int Id = view.getId();
		switch (Id) {
			case R.id.tv_first:
				startAnimActivity(FirstActivity.class);
				break;
			case R.id.tv_second:
				break;
			case R.id.tv_third:
				break;
			case R.id.tv_fourth:
				break;
			case R.id.tv_fifth:
				break;
			case R.id.tv_sixth:
				break;
			case R.id.tv_seven:
				break;
			case R.id.tv_eighth:
				break;
			case R.id.tv_ninth:
				break;
			case R.id.tv_ten:
				break;
			case R.id.tv_eleven:
				break;
			case R.id.tv_drag:
				break;
		}
	}
}
