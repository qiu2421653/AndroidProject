package com.qiu.retrofit.gui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Qiu
 * @version V1.3
 * @Description:自定义View
 * @date 2017/2/4 17:43
 */
public class FirstView extends View {

	public FirstView(Context context) {
		super(context);
	}

	public FirstView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FirstView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setSaveEnabled(true);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.GRAY);
	}


	//View有唯一的ID；
	//View的初始化时要调用setSaveEnabled(true) ；
	@Override
	protected Parcelable onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putParcelable("superState", super.onSaveInstanceState());
		bundle.putInt("status", 1);
		return bundle;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		if (state instanceof Bundle) {
			Bundle bundle = (Bundle) state;
			int status = bundle.getInt("status");
			state = bundle.getParcelable("superState");
		}
		super.onRestoreInstanceState(state);
	}
}
