package com.qiu.retrofit.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

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
 * @Description:ToolBar 带 图片效果
 * @date 2017/1/13 13:55
 */
public class ToolBarImageActivity extends BaseActivity {
	@BindView(R.id.anim_toolbar)
	Toolbar toolbar;
	@BindView(R.id.collapsing_toolbar)
	CollapsingToolbarLayout collapsingToolbar;
	@BindView(R.id.header)
	ImageView header;
	@BindView(R.id.swipe_target)
	RecyclerView recyclerView;

	private BaseRecyclerAdapter adapter;
	private int mutedColor = R.attr.colorPrimary;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_toolarimage);
	}

	@Override
	public void initView() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		collapsingToolbar.setTitle("WithImage");
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
	}

	@Override
	public void initData() {
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.header);

		//动态色彩->从图像中提取突出的颜色
		Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
			@SuppressWarnings("ResourceType")
			@Override
			public void onGenerated(Palette palette) {
				mutedColor = palette.getMutedColor(R.color.colorPrimary);
				collapsingToolbar.setContentScrimColor(mutedColor);
				collapsingToolbar.setStatusBarScrimColor(R.color.colorAccent);
			}
		});


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
}
