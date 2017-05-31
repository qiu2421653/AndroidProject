package com.qiu.retrofit.gui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:
 * @date 2017/2/6 9:28
 */
public class ThirdView extends View {

	private Paint mPaint;

	private int width, height;

	private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080, 0xFFE6B800, 0xFF7CFC00};

	public ThirdView(Context context) {
		this(context, null);
	}

	public ThirdView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ThirdView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
	}

	private void initPaint() {
		//STROKE                //描边
		//FILL                  //填充
		//FILL_AND_STROKE       //描边加填充

		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setAntiAlias(true);//设置抗锯齿
		mPaint.setColor(Color.RED);//设置画笔为绿色
		mPaint.setStrokeWidth(10);//设置画笔宽度为10px
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
		// 椭圆
//		RectF rectF = new RectF(100, 100, 800, 300);
//		canvas.drawOval(rectF, mPaint);
		//圆
//		canvas.drawCircle(500, 500, 100, mPaint);

		//画布原点移动到屏幕中心点位置
		canvas.translate(width / 2, height / 2);
		// 饼状图半径
		float r = (float) (Math.min(width, height) / 2 * 0.8);

		RectF rect = new RectF(-r, -r, r, r);
		float currentStartAngle = 0;
		for (int i = 0; i < 6; i++) {
			mPaint.setColor(mColors[i]);
			canvas.drawArc(rect, currentStartAngle, 30 * (i + 1), true, mPaint);
			currentStartAngle += 30 * (1 + i);
		}
	}
}
