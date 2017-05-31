package com.qiu.retrofit.view.holder;

import android.view.View;
import android.widget.TextView;

import com.qiu.retrofit.R;

import butterknife.BindView;

/**
 * @author Qiu
 * @version V1.3
 * @Description:
 * @date 2017/1/13 14:11
 */
public class ToolBarImageHolder extends BaseHolder<String> {

	@BindView(R.id.title_tv)
	TextView titleTv;

	public ToolBarImageHolder(View view) {
		super(view);
	}

	@Override
	public void setData(String mData) {
		super.setData(mData);
		titleTv.setText(mData);
	}
}
