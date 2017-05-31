package com.qiu.retrofit.view.activity.search;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.adapter.BaseFragmentAdapter;
import com.qiu.retrofit.view.fragment.NewsFrament;
import com.qiu.retrofit.view.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:搜索
 * @date 2017/4/11 17:17
 */
public class SearchFragment extends BaseFragment {

	private static final String TAG = "SearchFragment";

	@BindView(R.id.stoolbar)
	Toolbar toolbar;
	@BindView(R.id.tabs)
	TabLayout tabs;
	@BindView(R.id.view_pager)
	ViewPager viewPager;


	private BaseFragmentAdapter fragmentAdapter;
	private List<Fragment> mNewsFragmentList;
	private ArrayList<String> listTitle;


	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@Override
	public View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_search, null);
	}

	@Override
	public void initView(View view) {
		setHasOptionsMenu(true);
		((SearchActivity) getActivity()).setSupportActionBar(toolbar);
		((SearchActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		//解决跳转Fragment后 返回键不响应的问题
		if (!hidden) {
			((SearchActivity) getActivity()).setSupportActionBar(toolbar);
			((SearchActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			toolbar.getMenu().clear();
			toolbar.inflateMenu(R.menu.menu_search);
		}
	}

	/**
	 * 初始化数据
	 */
	@Override
	public void initData() {
		listTitle = new ArrayList<>();
		listTitle.add("热门推荐");
		listTitle.add("热门收藏");
		listTitle.add("本月热榜");
		listTitle.add("今日热榜");

		//为TabLayout添加tab名称
		tabs.addTab(tabs.newTab().setText(listTitle.get(0)));
		tabs.addTab(tabs.newTab().setText(listTitle.get(1)));
		tabs.addTab(tabs.newTab().setText(listTitle.get(2)));
		tabs.addTab(tabs.newTab().setText(listTitle.get(3)));

		//添加Fragment
		mNewsFragmentList = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			mNewsFragmentList.add(createListFragments());
		}
		initIal();
	}

	private void initIal() {
		//设置TabLayout的模式
		tabs.setTabMode(TabLayout.MODE_FIXED);

		if (fragmentAdapter == null) {
			fragmentAdapter = new BaseFragmentAdapter(getChildFragmentManager(), mNewsFragmentList, listTitle);
		} else {
			//刷新fragment
			fragmentAdapter.setFragments(getChildFragmentManager(), mNewsFragmentList, listTitle);
		}

		viewPager.setAdapter(fragmentAdapter);
		tabs.setupWithViewPager(viewPager);
	}

	private NewsFrament createListFragments() {
		NewsFrament fragment = new NewsFrament();
		return fragment;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.menu_search, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 搜索按键点击
		int id = item.getItemId();
		if (id == R.id.action_search) {
			//搜索
			((SearchActivity) getActivity()).changeTipsPage();
			return true;
		} else if (id == android.R.id.home) {
			//返回->关闭
			((SearchActivity) getActivity()).onFinish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
