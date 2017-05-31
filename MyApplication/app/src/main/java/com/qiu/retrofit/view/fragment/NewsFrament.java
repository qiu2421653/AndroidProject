package com.qiu.retrofit.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.adapter.BaseRecyclerAdapter;
import com.qiu.retrofit.view.fragment.base.BaseFragment;
import com.qiu.retrofit.view.holder.ToolBarImageHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Qiu
 * @version V1.3
 * @Description:
 * @date 2017/1/11 13:49
 */
public class NewsFrament extends BaseFragment {
	@BindView(R.id.swipe_target)
	RecyclerView recyclerView;

	private BaseRecyclerAdapter adapter;

	@Override
	public View onCreateMyView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_news, null);
	}

	@Override
	public void initView(View view) {
		recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
	}

	@Override
	public void initData() {
		List<String> listData = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			listData.add("string-->" + i);
		}
		adapter = new BaseRecyclerAdapter(listData, R.layout.item_toolimage, ToolBarImageHolder.class);
		recyclerView.setAdapter(adapter);
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}


}
