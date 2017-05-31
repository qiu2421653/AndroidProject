package com.qiu.retrofit.gui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.qiu.retrofit.R;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:
 * @date 2017/2/6 9:28
 */
public class NinthView extends View {

	private static final String TAG = "NinthView";

	private int width, height;

	private int images[] = {R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3};

	private Bitmap mBitmaps[] = new Bitmap[3];

	public NinthView(Context context) {
		this(context, null);
	}

	public NinthView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public NinthView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ActionBar);
		typedArray.recycle();
		initBitmap();
	}

	private void initBitmap() {
		int size = images.length;
		Bitmap bp;
		for (int i = 0; i < size; i++) {
			bp = BitmapFactory.decodeResource(getResources(), images[i]);
			mBitmaps[i] = Bitmap.createScaledBitmap(bp, 400, 600, false);
		}
		setBackgroundColor(Color.GRAY);
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
		canvas.save();
		canvas.translate(20, 120);
		int size = images.length;
		for (int i = 0; i < size; i++) {
			canvas.translate(120, 0);
			canvas.save();
			if (i < size - 1) {
				//切割需要展示的部分
				canvas.clipRect(0, 0, 120, mBitmaps[i].getHeight());
			}
			canvas.drawBitmap(mBitmaps[i], 0, 0, null);
			canvas.restore();
		}
		canvas.restore();
	}
}
