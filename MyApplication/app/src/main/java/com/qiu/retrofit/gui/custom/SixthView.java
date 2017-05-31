package com.qiu.retrofit.gui.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.qiu.retrofit.R;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:
 * @date 2017/2/6 9:28
 */
public class SixthView extends View {

	private Paint mPaint;

	private int width, height;

	private Bitmap bitmap;

	public SixthView(Context context) {
		this(context, null);
	}

	public SixthView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SixthView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initPaint();
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.header);
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
		//Bitmap_1(原点)
		//canvas.drawBitmap(bitmap, new Matrix(), mPaint);

		//Bitmap_2(偏离原点)
		//canvas.drawBitmap(bitmap, 200, 500, mPaint);

		//Bitmap_3
		//将画布坐标系移动到画布中央
		canvas.translate(width / 2, height / 2);
		//指定图片绘制区域(左上角的四分之一)
		Rect src = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
		// 指定图片在屏幕上显示的区域
		Rect dst = new Rect(0, 0, 200, 400);
		// 绘制图片
		canvas.drawBitmap(bitmap, src, dst, null);
	}
}
