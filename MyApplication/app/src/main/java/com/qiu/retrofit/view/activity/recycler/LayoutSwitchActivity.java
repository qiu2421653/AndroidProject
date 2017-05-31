package com.qiu.retrofit.view.activity.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;
import com.qiu.retrofit.view.adapter.BaseRecyclerAdapter;
import com.qiu.retrofit.view.holder.ToolBarImageHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:布局切换
 * @date 2017/1/23 16:04
 */
public class LayoutSwitchActivity extends BaseActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.swipe_target)
	RecyclerView swipeTarget;

	private BaseRecyclerAdapter adapter;
	private GridLayoutManager gridLayoutManager;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_switch);
	}

	@Override
	public void initView() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void initData() {
		List<String> listData = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			listData.add("string-->" + i);
		}

		gridLayoutManager = new GridLayoutManager(this, 1);
		swipeTarget.setLayoutManager(gridLayoutManager);
		adapter = new BaseRecyclerAdapter(listData, R.layout.item_toolimage, ToolBarImageHolder.class);
		swipeTarget.setAdapter(adapter);
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.meun_switch, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_switch_layout) {
			switchLayout();
			switchIcon(item);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void switchLayout() {
		switch (gridLayoutManager.getSpanCount()) {
			case 1:
				gridLayoutManager.setSpanCount(3);
				break;
			case 3:
				gridLayoutManager.setSpanCount(1);
				break;
			default:
				gridLayoutManager.setSpanCount(1);
				break;
		}
		adapter.notifyItemRangeChanged(0, adapter.getItemCount());
	}

	private void switchIcon(MenuItem item) {
		if (gridLayoutManager.getSpanCount() == 3) {
			item.setIcon(getResources().getDrawable(R.drawable.ic_span_3));
		} else {
			item.setIcon(getResources().getDrawable(R.drawable.ic_span_1));
		}
	}
}
