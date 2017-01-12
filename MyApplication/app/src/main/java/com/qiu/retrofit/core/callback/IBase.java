package com.qiu.retrofit.core.callback;


import com.qiu.retrofit.presenter.BasePresenter;

public interface IBase {
	/**
	 * 创建视图，统一处理
	 */
	void onCreateMyView();

	/**
	 * 初始化视图
	 */
	void initView();

	/**
	 * 初始化数据
	 */
	void initData();

	/**
	 *
	 */
	BasePresenter initPresenter();

}
