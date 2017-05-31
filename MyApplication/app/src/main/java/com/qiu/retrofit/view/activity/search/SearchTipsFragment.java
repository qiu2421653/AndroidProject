package com.qiu.retrofit.view.activity.search;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.fragment.base.BaseFragment;

import butterknife.BindView;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:搜索跳转
 * @date 2017/4/11 18:18
 */
public class SearchTipsFragment extends BaseFragment {

	private static final String TAG = "SearchTipsFragment";
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.swipe_target)
	RelativeLayout swipe_target;
	@BindView(R.id.edittext)
	EditText edittext;

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@Override
	public View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_search_tips, null);
	}

	@Override
	public void initView(View view) {
		setHasOptionsMenu(true);

	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		if (!hidden) {
			((SearchActivity) getActivity()).setSupportActionBar(toolbar);
			((SearchActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			toolbar.getMenu().clear();
			toolbar.inflateMenu(R.menu.menu_clear);
		}
	}

	/**
	 * 初始化数据
	 */
	@Override
	public void initData() {
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.menu_clear, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 搜索按键点击
		int id = item.getItemId();
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_clear) {
			//清理
			edittext.setText("");
			return true;
		} else if (id == android.R.id.home) {
			//返回
			((SearchActivity) getActivity()).changePage();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
