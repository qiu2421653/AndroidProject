package com.qiu.retrofit.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiu.retrofit.R;

/**
 * @author Qiu
 * @version V1.3
 * @Description:
 * @date 2017/1/11 13:49
 */
public class NewsFrament extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_news, null);
	}
}
