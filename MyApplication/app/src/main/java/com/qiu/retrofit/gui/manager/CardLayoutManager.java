package com.qiu.retrofit.gui.manager;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author Qiu
 * @version V1.0
 * @Description:RecyclerView-manager
 * @date 2017/3/1 11:46
 */
public class CardLayoutManager extends RecyclerView.LayoutManager {

	@Override
	public RecyclerView.LayoutParams generateDefaultLayoutParams() {
		return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	}

}
