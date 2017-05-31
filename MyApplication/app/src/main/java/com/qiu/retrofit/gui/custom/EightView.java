package com.qiu.retrofit.gui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.qiu.retrofit.core.log.CLog;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:
 * @date 2017/2/6 9:28
 */
public class EightView extends View {

	private static final String TAG = "EightView";
	private Paint mPaint;

	private int width, height;

	private Path mPath;

	private Region mRegion;//区域


	public EightView(Context context) {
		this(context, null);
	}

	public EightView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public EightView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
		mPath = new Path();
		mRegion = new Region();
	}

	private void initPaint() {
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setAntiAlias(true);//设置抗锯齿
		mPaint.setColor(Color.RED);//设置画笔为红色
		mPaint.setStrokeWidth(5);//设置画笔宽度为5px
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		width = MeasureSpec.getSize(widthMeasureSpec);
		height = MeasureSpec.getSize(heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//添加个圆
		mPath.addCircle(width / 2, height / 2, 300, Path.Direction.CW);
		Region region = new Region(0, 0, width, height);
		mRegion.setPath(mPath, region);
		canvas.drawPath(mPath, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 根据触摸位置更新控制点，并提示重绘
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				int x = (int) event.getX();
				int y = (int) event.getY();
				if (mRegion.contains(x, y)) {
					//在范围内
					CLog.e(TAG, "************在点击范围内");
				}
				break;
		}
		return true;
	}
}
