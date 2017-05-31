package com.qiu.retrofit.view.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;
import com.qiu.retrofit.view.adapter.BaseFragmentAdapter;
import com.qiu.retrofit.view.fragment.PercentFrament;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author @Qiu
 * @version V1.3
 * @Description:百分比
 * @date 2017/1/19 16:25
 */
public class PercentActivity extends BaseActivity {

	@BindView(R.id.tabs)
	TabLayout tabs;
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.viewpager)
	ViewPager viewPager;
	@BindView(R.id.collapse_toolbar)
	CollapsingToolbarLayout collapseToolbar;

	private ArrayList<String> listTitle;
	private List<Fragment> mNewsFragmentList;
	private BaseFragmentAdapter fragmentAdapter;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_percent);
	}

	@Override
	public void initView() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		collapseToolbar.setTitleEnabled(false);
	}

	@Override
	public void initData() {
		listTitle = new ArrayList<>();
		listTitle.add("view1");
		listTitle.add("view2");
		listTitle.add("view3");
		listTitle.add("view4");
		listTitle.add("view5");

		//为TabLayout添加tab名称
		tabs.addTab(tabs.newTab().setText(listTitle.get(0)));
		tabs.addTab(tabs.newTab().setText(listTitle.get(1)));
		tabs.addTab(tabs.newTab().setText(listTitle.get(2)));
		tabs.addTab(tabs.newTab().setText(listTitle.get(3)));

		//添加Fragment
		mNewsFragmentList = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			int layoutId = getResources().getIdentifier("percent_view" + i, "layout", getPackageName());
			mNewsFragmentList.add(createListFragments(layoutId));
		}
		initIalFragments();
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	private void initIalFragments() {
		//设置TabLayout的模式
		tabs.setTabMode(TabLayout.MODE_FIXED);
		if (fragmentAdapter == null) {
			fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), mNewsFragmentList, listTitle);
		} else {
			//刷新fragment
			fragmentAdapter.setFragments(getSupportFragmentManager(), mNewsFragmentList, listTitle);
		}

		viewPager.setAdapter(fragmentAdapter);
		tabs.setupWithViewPager(viewPager);
	}

	private PercentFrament createListFragments(int layoutId) {
		PercentFrament fragment = new PercentFrament();

		Bundle bundle = new Bundle();
		bundle.putInt("layoutId", layoutId);

		fragment.setArguments(bundle);
		return fragment;
	}
}
