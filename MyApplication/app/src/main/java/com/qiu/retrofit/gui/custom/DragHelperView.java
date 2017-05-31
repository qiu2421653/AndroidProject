package com.qiu.retrofit.gui.custom;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author Qiu
 * @version V1.3
 * @Description:
 * @date 2017/2/8 9:44
 */
public class DragHelperView extends LinearLayout {

	private ViewDragHelper dragHelper;

	private View mDragView;
	private View mAutoBackView;
	private View mEdgeTrackerView;

	private Point mAutoBackOriginPos = new Point();

	public DragHelperView(Context context) {
		this(context, null);
	}

	public DragHelperView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DragHelperView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initDragHelper();
	}

	private void initDragHelper() {
		dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
			@Override
			public boolean tryCaptureView(View child, int pointerId) {
				//启动拦截
				return child == mDragView || child == mAutoBackView;
			}

			//可以在该方法中对child移动的边界进行控制
			@Override
			public int clampViewPositionHorizontal(View child, int left, int dx) {
				//只在ViewGroup的内部移动，即：最小>=paddingleft，最大<=ViewGroup.getWidth()-paddingright-child.getWidth
				final int leftBound = getPaddingLeft();
				final int rightBound = getWidth() - child.getWidth() - leftBound;
				final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
				return newLeft;
			}

			@Override
			public int clampViewPositionVertical(View child, int top, int dy) {
				return top;
			}

			//子view可以点击时 得重写下面这两个方法：
			@Override
			public int getViewHorizontalDragRange(View child) {
				return getMeasuredWidth() - child.getMeasuredWidth();
			}

			@Override
			public int getViewVerticalDragRange(View child) {
				return getMeasuredHeight() - child.getMeasuredHeight();
			}

			//手指释放的时候回调
			@Override
			public void onViewReleased(View releasedChild, float xvel, float yvel) {
				if (releasedChild == mAutoBackView) {
					//返回原位置
					dragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
					invalidate();
				}
			}

			//在边界拖动时回调
			@Override
			public void onEdgeDragStarted(int edgeFlags, int pointerId) {
				dragHelper.captureChildView(mEdgeTrackerView, pointerId);
			}
		});
		//启动边界拖动回调
		dragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		return dragHelper.shouldInterceptTouchEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		dragHelper.processTouchEvent(event);
		return true;
	}

	@Override
	public void computeScroll() {
		if (dragHelper.continueSettling(true)) {
			invalidate();
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		//赋值原始位置坐标
		mAutoBackOriginPos.x = mAutoBackView.getLeft();
		mAutoBackOriginPos.y = mAutoBackView.getTop();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		mDragView = getChildAt(0);
		mAutoBackView = getChildAt(1);
		mEdgeTrackerView = getChildAt(2);

	}
}
