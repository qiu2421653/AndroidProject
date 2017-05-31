package com.qiu.retrofit.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qiu.retrofit.BuildConfig;
import com.qiu.retrofit.R;
import com.qiu.retrofit.contacts.BuildInfo;
import com.qiu.retrofit.presenter.BasePresenter;
import com.qiu.retrofit.view.activity.base.BaseActivity;
import com.tencent.tinker.lib.library.TinkerLoadLibrary;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Qiu
 * @version V1.3
 * @Description:Tinker
 * @date 2017/1/16 17:14
 */
public class TinkerActivity extends BaseActivity {

	public static String TEST_MESSAGE = "I won't change with tinker patch!";
	public static String BASE_TINKER_ID = BuildConfig.TINKER_ID;

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@Override
	public void onCreateMyView() {
		setContentView(R.layout.activity_tinker);
	}

	@Override
	public void initView() {
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void initData() {

	}

	@Override
	public BasePresenter initPresenter() {
		return null;
	}

	@OnClick({R.id.loadPatch, R.id.loadLibrary, R.id.cleanPatch, R.id.killSelf, R.id.showInfo})
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.loadPatch:
				TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.zip");
				break;
			case R.id.loadLibrary:
				TinkerLoadLibrary.installNavitveLibraryABI(getApplicationContext(), "armeabi");
				System.loadLibrary("stlport_shared");
				break;
			case R.id.cleanPatch:
				Tinker.with(getApplicationContext()).cleanPatch();
				break;
			case R.id.showInfo:
				showInfo(TinkerActivity.this);
				break;
			case R.id.killSelf:
				ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
				android.os.Process.killProcess(android.os.Process.myPid());
				break;
		}
	}

	public boolean showInfo(Context context) {
		// add more Build Info
		final StringBuilder sb = new StringBuilder();
		Tinker tinker = Tinker.with(getApplicationContext());
		if (tinker.isTinkerLoaded()) {
			sb.append(String.format("[patch is loaded] \n"));
			sb.append(String.format("[buildConfig TINKER_ID] %s \n", BuildInfo.TINKER_ID));
			sb.append(String.format("[buildConfig BASE_TINKER_ID] %s \n", BASE_TINKER_ID));

			sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
			sb.append(String.format("[TINKER_ID] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName(ShareConstants.TINKER_ID)));
			sb.append(String.format("[packageConfig patchMessage] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName("patchMessage")));
			sb.append(String.format("[TINKER_ID Rom Space] %d k \n", tinker.getTinkerRomSpace()));

		} else {
			sb.append(String.format("[patch is not loaded] \n"));
			sb.append(String.format("[buildConfig TINKER_ID] %s \n", BuildInfo.TINKER_ID));
			sb.append(String.format("[buildConfig BASE_TINKER_ID] %s \n", BASE_TINKER_ID));

			sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
			sb.append(String.format("[TINKER_ID] %s \n", ShareTinkerInternals.getManifestTinkerID(getApplicationContext())));
		}
		sb.append(String.format("[BaseBuildInfo Message] %s \n", TEST_MESSAGE));

		final TextView v = new TextView(context);
		v.setText(sb);
		v.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
		v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
		v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		v.setTextColor(0xFF000000);
		v.setTypeface(Typeface.MONOSPACE);
		final int padding = 16;
		v.setPadding(padding, padding, padding, padding);

		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);
		builder.setView(v);
		final AlertDialog alert = builder.create();
		alert.show();
		return true;
	}

}
