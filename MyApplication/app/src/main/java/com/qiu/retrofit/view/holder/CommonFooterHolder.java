package com.qiu.retrofit.view.holder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.qiu.retrofit.R;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author Qiu
 * @version V1.0
 * @Description:通用加载页脚
 * @date 2016/10/22 16:13
 */
public class CommonFooterHolder extends BaseHolder<Integer> {
	@BindView(R.id.load_more_loading_ll)
	LinearLayout loadingView;
	@BindView(R.id.load_more_fail_fl)
	FrameLayout failView;
	@BindView(R.id.load_more_end_fl)
	FrameLayout endView;

	public static final int STATUS_DEFAULT = 0;
	public static final int STATUS_LOADING = 1;
	public static final int STATUS_FAIL = 2;
	public static final int STATUS_END = 3;

	public CommonFooterHolder(View view) {
		super(view);
	}

	@Override
	public void setData(Integer data, int positoin) {
//		super.setData(data, positoin);
		switch (data) {
			case STATUS_LOADING:
				visibleLoading(true);
				visibleLoadFail(false);
				visibleLoadEnd(false);
				break;
			case STATUS_FAIL:
				visibleLoading(false);
				visibleLoadFail(true);
				visibleLoadEnd(false);
				break;
			case STATUS_END:
				visibleLoading(false);
				visibleLoadFail(false);
				visibleLoadEnd(true);
				break;
			case STATUS_DEFAULT:
				visibleLoading(true);
				visibleLoadFail(false);
				visibleLoadEnd(false);
				break;
		}
	}

	private void visibleLoading(boolean visible) {
		loadingView.setVisibility(visible ? View.VISIBLE : View.GONE);
	}

	private void visibleLoadFail(boolean visible) {
		failView.setVisibility(visible ? View.VISIBLE : View.GONE);
	}

	private void visibleLoadEnd(boolean visible) {
		endView.setVisibility(visible ? View.VISIBLE : View.GONE);
	}

	@OnClick({R.id.load_more_fail_fl})
	public void OnCLick(View view) {
		if (R.id.load_more_fail_fl == view.getId()) {
			//点我重试
		}
	}
}
