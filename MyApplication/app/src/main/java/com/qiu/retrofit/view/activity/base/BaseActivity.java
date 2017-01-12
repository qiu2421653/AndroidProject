package com.qiu.retrofit.view.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.qiu.retrofit.core.callback.IBase;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.impl.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 父类
 *
 * @author Qiu
 */
public abstract class BaseActivity extends AppCompatActivity implements IBase {
	protected Context mContext;
	protected BasePresenter mPresenter;// P层基类
	protected Unbinder unbinder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getApplicationContext();
		try {
			onCreateMyView();
			getPresenter();
			bindView();
			initView();
			initData();
		} catch (Exception e) {
			// 处理|网络上传异常
			e.printStackTrace();
		}
	}


	@Override
	protected void onDestroy() {
		unbinder.unbind();
		if (mPresenter != null && this instanceof IBaseView) {
			mPresenter.detachView();
			mPresenter = null;
		}
		super.onDestroy();
	}

	/**
	 * 绑定
	 */
	protected void bindView() {
		unbinder = ButterKnife.bind(this);
	}

	/**
	 * 初始化视图
	 */
	public abstract void onCreateMyView();

	/**
	 * 初始化视图
	 */
	public abstract void initView();

	/**
	 * 初始化数据
	 */
	public abstract void initData();


	/**
	 * 实例P层
	 *
	 * @param
	 */
	private void getPresenter() {
		mPresenter = initPresenter();
		if (mPresenter != null && this instanceof IBaseView) {
			mPresenter.attach((IBaseView) this, this);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
