package com.qiu.retrofit.gui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:
 * @date 2017/2/6 9:28
 */
public class SevenView extends View {

	private Paint mPaint;

	private int width, height;

	private PointF control;

	public SevenView(Context context) {
		this(context, null);
	}

	public SevenView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SevenView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
		control = new PointF(0, 0);
	}

	private void initPaint() {
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setAntiAlias(true);//设置抗锯齿
		mPaint.setColor(Color.RED);//设置画笔为红色
		mPaint.setStrokeWidth(5);//设置画笔宽度为5px
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
		canvas.translate(width / 2, height / 2);
//		Path mPath = new Path();
//		mPath.lineTo(100, 300);
//		mPath.lineTo(100, 0);
//		//闭合
//		mPath.close();
//		canvas.drawPath(mPath, mPaint);

		// 绘制贝塞尔曲线
		Path path = new Path();
		path.moveTo(100, 100);
		path.quadTo(control.x, control.y, 400, 100);

		canvas.drawPath(path, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 根据触摸位置更新控制点，并提示重绘
		control.x = event.getX();
		control.y = event.getY();
		invalidate();
		return true;
	}
}
