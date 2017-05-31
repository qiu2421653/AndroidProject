package com.qiu.retrofit.view.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;
import com.qiu.retrofit.view.activity.custom.EighthActivity;
import com.qiu.retrofit.view.activity.custom.ElevenActivity;
import com.qiu.retrofit.view.activity.custom.FifthActivity;
import com.qiu.retrofit.view.activity.custom.FirstActivity;
import com.qiu.retrofit.view.activity.custom.FourActivity;
import com.qiu.retrofit.view.activity.custom.NinthActivity;
import com.qiu.retrofit.view.activity.custom.SecondActivity;
import com.qiu.retrofit.view.activity.custom.SeventhActivity;
import com.qiu.retrofit.view.activity.custom.SixActivity;
import com.qiu.retrofit.view.activity.custom.TenActivity;
import com.qiu.retrofit.view.activity.custom.ThirdActivity;
import com.qiu.retrofit.view.activity.custom.ViewDragHelperActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:自定义View
 * @date 2017/2/4 17:51
 */
public class CustomActivity extends BaseActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_custom);
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
				startAnimActivity(SecondActivity.class);
				break;
			case R.id.tv_third:
				startAnimActivity(ThirdActivity.class);
				break;
			case R.id.tv_fourth:
				startAnimActivity(FourActivity.class);
				break;
			case R.id.tv_fifth:
				startAnimActivity(FifthActivity.class);
				break;
			case R.id.tv_sixth:
				startAnimActivity(SixActivity.class);
				break;
			case R.id.tv_seven:
				startAnimActivity(SeventhActivity.class);
				break;
			case R.id.tv_eighth:
				startAnimActivity(EighthActivity.class);
				break;
			case R.id.tv_ninth:
				startAnimActivity(NinthActivity.class);
				break;
			case R.id.tv_ten:
				startAnimActivity(TenActivity.class);
				break;
			case R.id.tv_eleven:
				startAnimActivity(ElevenActivity.class);
				break;
			case R.id.tv_drag:
				startAnimActivity(ViewDragHelperActivity.class);
				break;
		}
	}
}
