package com.qiu.retrofit.view.activity.ValueAnimator;

import android.animation.ValueAnimator;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Qiu
 * @version V1.3
 * @Description:实现activity变暗的效果
 * @date 2017/2/4 17:44
 */
public class FirstActivity extends BaseActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;

	private boolean isNight;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_animator_first);
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


	@OnClick(R.id.btn_click)
	public void onClick(View view) {
		if (isNight) {
			/** 窗口背景变亮*/
			dimBackground(0.5f, 1.0f);
		} else {
			/** 窗口背景变暗*/
			dimBackground(1.0f, 0.5f);
		}
	}

	/**
	 * 变换Alpha颜色
	 *
	 * @param from
	 * @param to
	 */
	private void dimBackground(final float from, final float to) {
		isNight = !isNight;
		final Window window = getWindow();
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
		valueAnimator.setDuration(500);
		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				WindowManager.LayoutParams params = window.getAttributes();
				params.alpha = (Float) animation.getAnimatedValue();
				window.setAttributes(params);
			}

		});
		valueAnimator.start();
	}
}
