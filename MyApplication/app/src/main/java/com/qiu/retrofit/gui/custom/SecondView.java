package com.qiu.retrofit.gui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Qiu
 * @version V1.3
 * @Description:自定义View
 * @date 2017/2/4 17:43
 */
public class SecondView extends View {

	private Paint mPaint;

	public SecondView(Context context) {
		this(context, null);
	}

	public SecondView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SecondView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
	}

	private void initPaint() {
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setAntiAlias(true);//设置抗锯齿
		mPaint.setColor(Color.RED);//设置画笔为绿色
		mPaint.setStrokeWidth(15);//设置画笔宽度为10px
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//绘制一个点
		canvas.drawPoint(100, 100, mPaint);
		//垂直绘制一排点
		canvas.drawPoints(new float[]{
				400, 400,
				400, 500,
				400, 600,
				400, 700,
		}, mPaint);

		//水平绘制一行点
		canvas.drawPoints(new float[]{
				300, 200,
				400, 200,
				500, 200,
				600, 200,
		}, mPaint);

		//水平绘制一条线
		canvas.drawLine(700, 300, 900, 300, mPaint);

		//矩形1
		canvas.drawRect(200, 800, 500, 1000, mPaint);
		//矩形2
		Rect rect = new Rect(600, 900, 800, 1100);
		canvas.drawRect(rect, mPaint);
		//矩形3
		RectF rectF = new RectF(200, 1200, 900, 1300);
		canvas.drawRect(rectF, mPaint);
		//圆角矩形
		RectF rRect = new RectF(200, 1350, 900, 1500);
		canvas.drawRoundRect(rRect, 50, 50, mPaint);
	}
}
