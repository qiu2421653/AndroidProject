package com.qiu.retrofit.core.callback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiu.retrofit.presenter.BasePresenter;


public interface IBaseFragment {
	/**
	 * 创建视图，统一处理
	 */
	View onCreateMyView(LayoutInflater inflater, ViewGroup container,
						Bundle savedInstanceState);

	/**
	 * 初始化视图
	 */
	void initView(View view);

	/**
	 * 初始化数据
	 */
	void initData();

	/**
	 * )
	 */
	BasePresenter initPresenter();

}
