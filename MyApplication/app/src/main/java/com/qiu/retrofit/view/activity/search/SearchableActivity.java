package com.qiu.retrofit.view.activity.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:SearchAble展示
 * @date 2017/4/24 10:40
 */
public class SearchableActivity extends BaseActivity {
	private static final String TAG = SearchableActivity.class.getSimpleName();

	@BindView(R.id.text)
	TextView mTextView;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_search_able);
	}

	@Override
	public void initView() {
	}

	@Override
	public void initData() {

	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@Override
	protected void onNewIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			mTextView.setText(query);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_searchable, menu);
		//方式1
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		MenuItem searchMenuItem = menu.findItem(R.id.search);
		//使用v7的时候,需要使用MenuItemCompat
		SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		return true;
	}

}
