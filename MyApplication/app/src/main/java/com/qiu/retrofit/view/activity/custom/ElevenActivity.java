package com.qiu.retrofit.view.activity.custom;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextSwitcher;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:测试TextSwitcher
 * @date 2017/3/9 11:25
 */
public class ElevenActivity extends BaseActivity {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.tsLikesCounter)
	TextSwitcher tsLikesCounter;

	private int likesCount = 100;


	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_eleven);
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
		updateLikesCounter();
	}

	/**
	 * 更新数值
	 * TextSwitcher能且只能添加两个TextView，多了少了都會出錯
	 */
	private void updateLikesCounter() {
		tsLikesCounter.setText(String.format(getString(R.string.likes_count), ++likesCount));
	}

}
