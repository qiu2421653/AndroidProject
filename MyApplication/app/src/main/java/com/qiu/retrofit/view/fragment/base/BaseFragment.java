package com.qiu.retrofit.view.fragment.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiu.retrofit.R;
import com.qiu.retrofit.core.callback.IBaseFragment;
import com.qiu.retrofit.core.callback.IRetryListener;
import com.qiu.retrofit.gui.loading.LoadingState;
import com.qiu.retrofit.gui.loading.LoadingView;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.impl.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseFragment
 *
 * @author Qiu
 */
public abstract class BaseFragment extends Fragment implements IBaseFragment, IRetryListener {

	protected Context mContext;
	protected BasePresenter mPresenter;// P层基类
	protected Unbinder unbinder;
	protected LoadingView fl_loading;// 加载

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = null;
		mContext = getContext().getApplicationContext();
		try {
			view = onCreateMyView(inflater, container, savedInstanceState);
			getPresenter();
			bindView(view);
			initView(view);
			initData();
		} catch (Exception e) {
			// 处理|网络上传异常
			e.printStackTrace();
		}
		return view;
	}

	@Override
	public void onDestroy() {
		if (unbinder != null) {
			unbinder.unbind();
		}
		if (mPresenter != null && this instanceof IBaseView) {
			mPresenter.detachView();
			mPresenter = null;
		}
		super.onDestroy();
	}


	/**
	 * 绑定view
	 */
	protected void bindView(View view) {
		unbinder = ButterKnife.bind(this, view);
	}


	/**
	 * 实例P层
	 *
	 * @param
	 */
	private void getPresenter() {
		mPresenter = initPresenter();
		if (mPresenter != null && this instanceof IBaseView) {
			mPresenter.attach((IBaseView) this, getActivity());
		}
	}

	/**
	 * 跳转Intent
	 */
	protected void startAnimActivity(Intent intent) {
		startActivity(intent);
	}

	/**
	 * 跳转Intent
	 */
	protected void startAnimActivity(Class<?> cla) {
		startActivity(new Intent(mContext, cla));
	}

	/**
	 * @Title: 加载动画
	 * @Description: 设置LoadingView
	 */
	protected void initLoading(View view) {
		fl_loading = (LoadingView) view.findViewById(R.id.app_loading);
		fl_loading.withLoadedEmptyText("该条件下暂无车源")
				.withEmptyIco(R.drawable.ic_chat_empty)
				.withBtnEmptyEnnable(false)
				.withErrorIco(R.drawable.ic_chat_empty)
				.withLoadedErrorText(getString(R.string.error))
				.withbtnErrorText("请重试")
				.withLoadedNoNetText(getString(R.string.neterror))
				.withNoNetIco(R.drawable.ic_chat_empty).withbtnNoNetText("重试")
				.withLoadingIco(R.drawable.frame_loading)
				.withLoadingText("加载中...").withOnRetryListener(this).build();
	}

	public void showSuccess() {
		if (fl_loading != null) {
			fl_loading.setVisibility(View.GONE);
		}
	}

	public void showEmpty() {
		if (fl_loading != null) {
			fl_loading.setVisibility(View.VISIBLE);
			fl_loading.setState(LoadingState.STATE_EMPTY);
		}
	}

	public void showLoading() {
		if (fl_loading != null) {
			fl_loading.setVisibility(View.VISIBLE);
			fl_loading.setState(LoadingState.STATE_LOADING);
		}
	}

	public void showFaild() {
		if (fl_loading != null) {
			fl_loading.setVisibility(View.VISIBLE);
			fl_loading.setState(LoadingState.STATE_ERROR);
		}
	}

	public void showNoNet() {
		if (fl_loading != null) {
			fl_loading.setVisibility(View.VISIBLE);
			fl_loading.setState(LoadingState.STATE_NO_NET);
		}
	}
	@Override
	public void onRetry() {

	}
}
