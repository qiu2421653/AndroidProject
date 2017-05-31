package com.qiu.retrofit.view.activity;

import android.view.View;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;
import com.qiu.retrofit.view.activity.recycler.LayoutSwitchActivity;
import com.qiu.retrofit.view.activity.recycler.PullToRefreshUseActivity;

import butterknife.OnClick;

/**
 * @author Qiu
 * @version V1.3
 * @Description:RecyclerView测试
 * @date 2017/1/23 14:38
 */
public class RecyclerViewActivity extends BaseActivity {
	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_recycler);
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


	@OnClick({R.id.tv_switch, R.id.tv_pull})
	public void onClick(View view) {
		int Id = view.getId();
		switch (Id) {
			case R.id.tv_switch:
				startAnimActivity(LayoutSwitchActivity.class);
				break;
			case R.id.tv_pull:
				startAnimActivity(PullToRefreshUseActivity.class);
				break;
		}
	}

}
