package com.qiu.retrofit.view.activity.custom;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.qiu.retrofit.R;
import com.qiu.retrofit.gui.SendCommentButton;
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
 * @Description:测试发送按钮
 * @date 2017/2/4 17:44
 */
public class TenActivity extends BaseActivity implements SendCommentButton.OnSendClickListener {
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.etComment)
	EditText etComment;
	@BindView(R.id.btnSendComment)
	SendCommentButton btnSendComment;
	@BindView(R.id.swipe_target)
	RecyclerView swipeTarget;

	private BaseRecyclerAdapter adapter;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_ten);
	}

	@Override
	public void initView() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		btnSendComment.setOnSendClickListener(this);
	}

	@Override
	public void initData() {
		List<String> listData = new ArrayList<String>();
		for (int i = 0; i < 15; i++) {
			listData.add("string-->" + i);
		}
		swipeTarget.setLayoutManager(getLinearLayoutManager());
		adapter = new BaseRecyclerAdapter(listData, R.layout.item_toolimage, ToolBarImageHolder.class);
		swipeTarget.setAdapter(adapter);
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@Override
	public void onSendClickListener(View v) {
		if (validateComment()) {
			swipeTarget.smoothScrollBy(0, swipeTarget.getChildAt(0).getHeight() * adapter.getItemCount());
			etComment.setText(null);
			btnSendComment.setCurrentState(SendCommentButton.STATE_DONE);
		}
	}

	private LinearLayoutManager getLinearLayoutManager() {
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
			@Override
			protected int getExtraLayoutSpace(RecyclerView.State state) {
				return 300;
			}
		};
		return linearLayoutManager;
	}

	private boolean validateComment() {
		if (TextUtils.isEmpty(etComment.getText())) {
			btnSendComment.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake_error));
			return false;
		}
		return true;
	}
}
