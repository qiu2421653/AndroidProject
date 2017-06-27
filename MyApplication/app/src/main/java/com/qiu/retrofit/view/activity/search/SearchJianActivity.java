package com.qiu.retrofit.view.activity.search;

import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;

/**
 * @author Qiu
 * @version V1.0
 * @Description:简书搜索栏
 * @date 2017/6/27 9:10
 */
public class SearchJianActivity extends BaseActivity {
	@BindView(R.id.tv_search)
	TextView tvSearch;
	@BindView(R.id.ll_search)
	LinearLayout mSearchLayout;
	@BindView(R.id.scrollView)
	ScrollView mScrollView;
	@BindView(R.id.iv_img)
	ImageView ivImg;
	@BindView(R.id.toolbar)
	Toolbar toolbar;

	private TransitionSet mSet;
	private boolean isExpand = false;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_search_able);
	}

	@Override
	public void initView() {
		//设置toolbar初始透明度为0
		toolbar.getBackground().mutate().setAlpha(0);
	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@Override
	public void initData() {
		//scrollview滚动状态监听
		mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
			@Override
			public void onScrollChanged() {
				//改变toolbar的透明度
				changeToolbarAlpha();
				//滚动距离>=大图高度-toolbar高度 即toolbar完全盖住大图的时候 且不是伸展状态 进行伸展操作
				if (mScrollView.getScrollY() >= ivImg.getHeight() - toolbar.getHeight() && !isExpand) {
					expand();
					isExpand = true;
				}
				//滚动距离<=0时 即滚动到顶部时  且当前伸展状态 进行收缩操作
				else if (mScrollView.getScrollY() <= 0 && isExpand) {
					reduce();
					isExpand = false;
				}
			}
		});
	}

	private void changeToolbarAlpha() {
		int scrollY = mScrollView.getScrollY();
		//快速下拉会引起瞬间scrollY<0
		if (scrollY < 0) {
			toolbar.getBackground().mutate().setAlpha(0);
			return;
		}
		//计算当前透明度比率
		float radio = Math.min(1, scrollY / (ivImg.getHeight() - toolbar.getHeight() * 1f));
		//设置透明度
		toolbar.getBackground().mutate().setAlpha((int) (radio * 0xFF));
	}


	private void expand() {
		//设置伸展状态时的布局
		tvSearch.setText("搜索简书的内容和朋友");
		RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) mSearchLayout.getLayoutParams();
		LayoutParams.width = LayoutParams.MATCH_PARENT;
		LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
		mSearchLayout.setLayoutParams(LayoutParams);
		//开始动画
		beginDelayedTransition(mSearchLayout);
	}

	private void reduce() {
		//设置收缩状态时的布局
		tvSearch.setText("搜索");
		RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) mSearchLayout.getLayoutParams();
		LayoutParams.width = dip2px(80);
		LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
		mSearchLayout.setLayoutParams(LayoutParams);
		//开始动画
		beginDelayedTransition(mSearchLayout);
	}

	void beginDelayedTransition(ViewGroup view) {
		mSet = new AutoTransition();
		mSet.setDuration(300);
		TransitionManager.beginDelayedTransition(view, mSet);
	}

	private int dip2px(float dpVale) {
		final float scale = getResources().getDisplayMetrics().density;
		return (int) (dpVale * scale + 0.5f);
	}
}

