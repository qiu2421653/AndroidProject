package com.qiu.retrofit.view.activity;


import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

/**
 *1.仅需要配置 action和 category android:name="android.intent.category.DEFAULT"
 *
 *
 *
 *
 */


/**
 * @author @Qiu
 * @version V1.3
 * @Description:Intent测试
 * @date 2017/2/10 14:24
 */
public class IntentActivity extends BaseActivity {

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_intent);
	}

	@Override
	public void initView() {

	}

	@Override
	public void initData() {

	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}
}
