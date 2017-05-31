package com.qiu.retrofit.view.activity.quick;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qiu.retrofit.R;

/**
 * @author Qiu
 * @version V1.0
 * @Description:快速启动Fragment
 * @date 2017/3/10 9:37
 */
public class StartFragment extends Fragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View ret = inflater.inflate(R.layout.fragment_start, container, false);
		initView(ret);
		return ret;
	}

	public void initView(View view) {
		if (view != null) {
			ImageView imageView = (ImageView) view.findViewById(R.id.lancher_logo);
			playAnimator(imageView);
		}
	}

	public void playAnimator(ImageView view) {
		if (view != null) {
			PropertyValuesHolder pHolder = PropertyValuesHolder.ofFloat("alpha", 1f, 0.7f, 0.1f);
			ObjectAnimator.ofPropertyValuesHolder(view, pHolder)
					.setDuration(2000).start();
		}

	}
}
