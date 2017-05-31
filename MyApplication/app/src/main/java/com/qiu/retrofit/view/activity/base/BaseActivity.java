package com.qiu.retrofit.view.activity.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.qiu.retrofit.core.callback.IBase;
import com.qiu.retrofit.gui.CustomClipLoading;
import com.qiu.retrofit.gui.MyToast;
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

	private static final String TAG = "BaseActivity";

	private static boolean isNight;
	protected Context mContext;
	protected BasePresenter mPresenter;// P层基类
	protected Unbinder unbinder;
	private MyToast mToast;
	private CustomClipLoading dialog;// 加载


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getApplicationContext();
		onCreateMyView();
		getPresenter();
		bindView();
		initView();
		initData();
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
	 * 显示Toast
	 */
	public void showToast(String text) {
		if (TextUtils.isEmpty(text)) {
			return;
		}
		if (mToast == null) {
			mToast = MyToast.makeTextToast(this, text, Toast.LENGTH_SHORT);
		}
		mToast.setText(text);
		mToast.show();
	}

	/**
	 * 取消显示
	 */
	private void cancelToast() {
		if (mToast != null) {
			mToast.cancel();
		}
	}

	/**
	 * 显示进度条
	 */
	protected void showLoadingDialog() {
		if (dialog == null) {
			dialog = new CustomClipLoading(this);
		}
		if (!isFinishing()) {
			dialog.show();
		}
	}

	/**
	 * 隐藏进度条
	 */
	protected void hideLoadingDialog() {
		if (dialog != null) {
			dialog.dismiss();
			dialog = null;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					finishAfterTransition();
				} else {
					finish();
				}
				break;
		}
		return super.onOptionsItemSelected(item);
	}

}
