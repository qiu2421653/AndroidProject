package com.qiu.retrofit.view.activity;

import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.qiu.retrofit.R;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import butterknife.BindView;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:MaterialView实例
 * @date 2017/1/17 18:11
 */
public class MaterialViewActivity extends BaseActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.sp_current)
	Spinner spCurrent;
	@BindView(R.id.sp_green)
	Spinner spGreen;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_material_view);
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
		setSpinner();
	}

	/**
	 * 设置Spinner数据
	 */
	private void setSpinner() {
		ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(
				mContext,
				R.array.spinner_items_array,
				R.layout.item_spinner);

		spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spCurrent.setAdapter(spAdapter);
		spGreen.setAdapter(spAdapter);
	}
}
