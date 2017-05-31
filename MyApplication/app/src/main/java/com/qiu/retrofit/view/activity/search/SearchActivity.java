package com.qiu.retrofit.view.activity.search;

import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:搜索
 * @date 2017/4/11 17:17
 */
public class SearchActivity extends BaseActivity {

	private static final String TAG = "SearchActivity";

	@BindView(R.id.framelayout)
	FrameLayout framelayout;

	private SearchFragment searchFragment;
	private SearchTipsFragment searchTipsFragment;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_search_main);
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@Override
	public void initView() {
		getSupportFragmentManager().beginTransaction()
				.add(R.id.framelayout, searchFragment = new SearchFragment())
				.add(R.id.framelayout, searchTipsFragment = new SearchTipsFragment())
				.hide(searchTipsFragment)
				.show(searchFragment)
				.commit();
	}

	/**
	 * 初始化数据
	 */
	@Override
	public void initData() {

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			return false;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 切换页面到页面1
	 */
	public void changePage() {
		getSupportFragmentManager().beginTransaction().hide(searchTipsFragment).setTransition(FragmentTransaction.TRANSIT_NONE).show(searchFragment).commit();
	}

	/**
	 * 切换页面到页面2
	 */
	public void changeTipsPage() {
		getSupportFragmentManager().beginTransaction().hide(searchFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).show(searchTipsFragment).commit();
	}

	/**
	 * 关闭
	 */
	public void onFinish() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			finishAfterTransition();
		} else {
			finish();
		}
	}
}
