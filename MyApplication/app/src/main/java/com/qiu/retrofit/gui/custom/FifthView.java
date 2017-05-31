package com.qiu.retrofit.gui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:
 * @date 2017/2/6 9:28
 */
public class FifthView extends View {

	private Paint mPaint;

	private int width, height;

	public FifthView(Context context) {
		this(context, null);
	}

	public FifthView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FifthView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
	}

	private void initPaint() {
		//STROKE                //描边
		//FILL                  //填充
		//FILL_AND_STROKE       //描边加填充

		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setAntiAlias(true);//设置抗锯齿
		mPaint.setColor(Color.RED);//设置画笔为绿色
		mPaint.setStrokeWidth(5);//设置画笔宽度为10px
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
		// 将坐标系原点移动到画布正中心
		canvas.translate(width / 2, height / 2);
		// 绘制两个圆形
		canvas.drawCircle(0, 0, 400, mPaint);
		canvas.drawCircle(0, 0, 380, mPaint);
		// 绘制圆形之间的连接线
		for (int i = 0; i <= 360; i += 10) {
			canvas.drawLine(0, 380, 0, 400, mPaint);
			canvas.rotate(10);
		}
	}
}
