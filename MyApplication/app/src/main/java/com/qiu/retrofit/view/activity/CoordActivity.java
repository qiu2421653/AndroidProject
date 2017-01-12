package com.qiu.retrofit.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;
import com.qiu.retrofit.view.adapter.BaseFragmentAdapter;
import com.qiu.retrofit.view.fragment.NewsFrament;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:CoordinatorLayout 滑动
 * @date 2017/1/11 11:46
 */
public class CoordActivity extends BaseActivity {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.tabs)
	TabLayout tabs;
	@BindView(R.id.view_pager)
	ViewPager viewPager;


	private BaseFragmentAdapter fragmentAdapter;
	private List<Fragment> mNewsFragmentList;
	private ArrayList<String> listTitle;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_coord);
	}

	@Override
	public void initView() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
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
			fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), mNewsFragmentList, listTitle);
		} else {
			//刷新fragment
			fragmentAdapter.setFragments(getSupportFragmentManager(), mNewsFragmentList, listTitle);
		}

		viewPager.setAdapter(fragmentAdapter);
		tabs.setupWithViewPager(viewPager);
	}

	private NewsFrament createListFragments() {
		NewsFrament fragment = new NewsFrament();
		return fragment;
	}

}
