package com.qiu.retrofit.view.activity.recycler;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.qiu.retrofit.R;
import com.qiu.retrofit.core.callback.AdapterCallback;
import com.qiu.retrofit.core.callback.IRequestLoadMoreListener;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;
import com.qiu.retrofit.view.adapter.BaseRecyclerFootAdapter;
import com.qiu.retrofit.view.holder.CommonFooterHolder;
import com.qiu.retrofit.view.holder.ToolBarImageHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author @Qiu
 * @version V1.3
 * @Description:刷新
 * @date 2017/2/3 9:36
 */
public class PullToRefreshUseActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, IRequestLoadMoreListener, AdapterCallback {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.swipe_target)
	RecyclerView swipeTarget;
	@BindView(R.id.swipeLayout)
	SwipeRefreshLayout swipeLayout;

	private BaseRecyclerFootAdapter adapter;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_pullrefresh);
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

	@Override
	public void initData() {
		List<String> listData = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			listData.add("string-->" + i);
		}
		swipeLayout.setOnRefreshListener(this);
		swipeLayout.setColorSchemeResources(
				android.R.color.holo_blue_light,
				android.R.color.holo_red_light,
				android.R.color.holo_green_light);

		swipeTarget.setLayoutManager(new LinearLayoutManager(this));

		adapter = new BaseRecyclerFootAdapter(listData, R.layout.item_toolimage, ToolBarImageHolder.class, CommonFooterHolder.STATUS_DEFAULT, R.layout.view_load_more, CommonFooterHolder.class);
		adapter.setCallBack(this);
		adapter.setOnLoadMoreRequested(this);
		swipeTarget.setAdapter(adapter);
	}


	@Override
	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				swipeLayout.setRefreshing(false);
			}
		}, 2000);
	}

	@Override
	public void onLoadMoreRequested() {
		//记载更多
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				adapter.nofityFooterChange(CommonFooterHolder.STATUS_FAIL);
				swipeLayout.setRefreshing(false);
			}
		}, 2000);
	}

	@Override
	public void onDataCallBack(Object data, int position) {

	}

	@Override
	public void onDataCallBack(Object data, int position, int index) {

	}

	@Override
	public void onDataCallBack(Object data, int position, int index, int tag) {

	}

	@Override
	public void onCallback(Object data) {

	}

	@Override
	public void onLongCallBack(Object data, int position) {

	}
}
