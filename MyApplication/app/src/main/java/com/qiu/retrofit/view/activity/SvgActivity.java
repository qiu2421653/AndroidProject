package com.qiu.retrofit.view.activity;

import android.graphics.Path;
import android.support.v7.widget.Toolbar;

import com.qiu.retrofit.R;
import com.qiu.retrofit.gui.path.PathAnimView;
import com.qiu.retrofit.gui.path.SvgPathParser;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;

import java.text.ParseException;

import butterknife.BindView;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:svg动画
 * @date 2017/2/15 16:37
 */
public class SvgActivity extends BaseActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.storeView)
	PathAnimView storeView;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_svg);
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
		SvgPathParser svgPathParser = new SvgPathParser();
		String tieta = getString(R.string.tieta);
		try {
			Path path = svgPathParser.parsePath(tieta);
			storeView.setSourcePath(path);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		storeView.getPathAnimHelper().setAnimTime(5000);
		storeView.startAnim();
	}


}
