package com.qiu.retrofit.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;
import com.qiu.retrofit.view.adapter.BaseRecyclerAdapter;
import com.qiu.retrofit.view.holder.ToolBarImageHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Qiu
 * @version V1.3
 * @Description:BottomSheet
 * @date 2017/1/13 17:27
 */
public class BottomSheetActivity extends BaseActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.swipe_target)
	RecyclerView recyclerView;

	@BindView(R.id.gmail_coordinator)
	CoordinatorLayout coordinatorLayout;

	@BindView(R.id.gmail_bottom_sheet)
	View bottomSheet;


	private BaseRecyclerAdapter adapter;
	private BottomSheetBehavior behavior;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_gmail_style);
	}

	@Override
	public void initView() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

		behavior = BottomSheetBehavior.from(bottomSheet);

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
		adapter = new BaseRecyclerAdapter(listData, R.layout.item_toolimage, ToolBarImageHolder.class);
		recyclerView.setAdapter(adapter);


		behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
			@Override
			public void onStateChanged(@NonNull View bottomSheet, int newState) {

				switch (newState) {
					case BottomSheetBehavior.STATE_DRAGGING:
						break;
					case BottomSheetBehavior.STATE_COLLAPSED:
						break;
					case BottomSheetBehavior.STATE_EXPANDED:
						break;
				}
			}
			@Override
			public void onSlide(View bottomSheet, float slideOffset) {

			}
		});
	}
}
