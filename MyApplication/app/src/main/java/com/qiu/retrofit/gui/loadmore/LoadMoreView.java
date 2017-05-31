package com.qiu.retrofit.gui.loadmore;

import com.qiu.retrofit.view.holder.BaseHolder;


/**
 * @author @Qiu
 * @version V1.3
 * @Description:加载更多
 * @date 2017/2/4 13:57
 */
public abstract class LoadMoreView {

	public static final int STATUS_DEFAULT = 1;
	public static final int STATUS_LOADING = 2;
	public static final int STATUS_FAIL = 3;
	public static final int STATUS_END = 4;

	private int mLoadMoreStatus = STATUS_DEFAULT;
	private boolean mLoadMoreEndGone = false;

	public void setLoadMoreStatus(int loadMoreStatus) {
		this.mLoadMoreStatus = loadMoreStatus;
	}

	public int getLoadMoreStatus() {
		return mLoadMoreStatus;
	}

	public void convert(BaseHolder holder) {
		switch (mLoadMoreStatus) {
			case STATUS_LOADING:
				visibleLoading(holder, true);
				visibleLoadFail(holder, false);
				visibleLoadEnd(holder, false);
				break;
			case STATUS_FAIL:
				visibleLoading(holder, false);
				visibleLoadFail(holder, true);
				visibleLoadEnd(holder, false);
				break;
			case STATUS_END:
				visibleLoading(holder, false);
				visibleLoadFail(holder, false);
				visibleLoadEnd(holder, true);
				break;
			case STATUS_DEFAULT:
				visibleLoading(holder, false);
				visibleLoadFail(holder, false);
				visibleLoadEnd(holder, false);
				break;
		}
	}

	private void visibleLoading(BaseHolder holder, boolean visible) {
	}

	private void visibleLoadFail(BaseHolder holder, boolean visible) {
	}

	private void visibleLoadEnd(BaseHolder holder, boolean visible) {
	}

	public final void setLoadMoreEndGone(boolean loadMoreEndGone) {
		this.mLoadMoreEndGone = loadMoreEndGone;
	}

	public final boolean isLoadEndMoreGone() {
		return mLoadMoreEndGone;
	}

	public boolean isLoadEndGone() {
		return mLoadMoreEndGone;
	}

}
