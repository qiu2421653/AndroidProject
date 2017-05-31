package com.qiu.retrofit.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

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
 * @Description:TabLayout+Image
 * @date 2017/1/13 16:30
 */
public class TabsHeaderActivity extends BaseActivity {

	@BindView(R.id.tabs)
	TabLayout tabs;
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.viewpager)
	ViewPager viewPager;
	@BindView(R.id.iv_header)
	ImageView header;
	@BindView(R.id.collapse_toolbar)
	CollapsingToolbarLayout collapseToolbar;

	private BaseFragmentAdapter fragmentAdapter;
	private List<Fragment> mNewsFragmentList;
	private ArrayList<String> listTitle;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_tabs_header);
	}

	@Override
	public void initView() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		collapseToolbar.setTitleEnabled(false);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		}
	}

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


		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.header);

		Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
			@SuppressWarnings("ResourceType")
			@Override
			public void onGenerated(Palette palette) {
				int vibrantColor = palette.getVibrantColor(R.color.colorPrimary);
				int vibrantDarkColor = palette.getDarkVibrantColor(R.color.colorPrimary);
				collapseToolbar.setContentScrimColor(vibrantColor);
				collapseToolbar.setStatusBarScrimColor(vibrantDarkColor);
			}
		});
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.drawer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
			case R.id.action_settings:
				return true;
		}
		return super.onOptionsItemSelected(item);
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
