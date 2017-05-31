package com.qiu.retrofit.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.fragment.base.BaseFragment;

/**
 * @author Qiu
 * @version V1.3
 * @Description:百分比布局
 * @date 2017/1/11 13:49
 */
public class PercentFrament extends BaseFragment {
	int layoutId;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.layoutId = getArguments().getInt("layoutId");
	}

	@Override
	public View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(layoutId, null);
	}

	@Override
	public void initView(View view) {
	}

	@Override
	public void initData() {
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}
}
