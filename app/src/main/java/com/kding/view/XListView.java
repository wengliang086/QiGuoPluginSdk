//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.kding.R;

public class XListView extends ListView implements OnScrollListener {
    private static final float OFFSET_RADIO = 1.8F;
    private static final int PULL_LOAD_MORE_DELTA = 50;
    private static final int SCROLL_BACK_FOOTER = 1;
    private static final int SCROLL_BACK_HEADER = 0;
    private static final int SCROLL_DURATION = 400;
    private boolean mEnableAutoLoad = false;
    private boolean mEnablePullLoad = true;
    private boolean mEnablePullRefresh = true;
    private LinearLayout mFooterLayout;
    private XFooterView mFooterView;
    private XHeaderView mHeader;
    private RelativeLayout mHeaderContent;
    private int mHeaderHeight;
    private TextView mHeaderTime;
    private boolean mIsFooterReady = false;
    private float mLastY = -1.0F;
    private XListView.IXListViewListener mListener;
    private boolean mPullLoading = false;
    private boolean mPullRefreshing = false;
    private int mScrollBack;
    private OnScrollListener mScrollListener;
    private Scroller mScroller;
    private int mTotalItemCount;

    public XListView(Context var1) {
        super(var1);
        this.initWithContext(var1);
    }

    public XListView(Context var1, AttributeSet var2) {
        super(var1, var2);
        this.initWithContext(var1);
    }

    public XListView(Context var1, AttributeSet var2, int var3) {
        super(var1, var2, var3);
        this.initWithContext(var1);
    }

    private void initWithContext(Context var1) {
        this.mScroller = new Scroller(var1, new DecelerateInterpolator());
        super.setOnScrollListener(this);
        this.mHeader = new XHeaderView(var1);
        this.mHeaderContent = (RelativeLayout) this.mHeader.findViewById(R.id.qxz_header_content);
        this.mHeaderTime = (TextView) this.mHeader.findViewById(R.id.qxz_header_hint_time);
        this.addHeaderView(this.mHeader);
        this.mFooterView = new XFooterView(var1);
        this.mFooterLayout = new LinearLayout(var1);
        LayoutParams var2 = new LayoutParams(-1, -1);
//    var2.gravity = 17;
        this.mFooterLayout.addView(this.mFooterView, var2);
        ViewTreeObserver var3 = this.mHeader.getViewTreeObserver();
        if (var3 != null) {
            var3.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                @TargetApi(16)
                public void onGlobalLayout() {
                    XListView.this.mHeaderHeight = XListView.this.mHeaderContent.getHeight();
                    ViewTreeObserver var1 = XListView.this.getViewTreeObserver();
                    if (var1 != null) {
                        if (VERSION.SDK_INT >= 16) {
                            var1.removeOnGlobalLayoutListener(this);
                            return;
                        }

                        var1.removeGlobalOnLayoutListener(this);
                    }

                }
            });
        }

    }

    private void invokeOnScrolling() {
        if (this.mScrollListener instanceof XListView.OnXScrollListener) {
            ((XListView.OnXScrollListener) this.mScrollListener).onXScrolling(this);
        }

    }

    private void loadMore() {
        if (this.mEnablePullLoad && this.mListener != null) {
            this.mListener.onLoadMore();
        }

    }

    private void refresh() {
        if (this.mEnablePullRefresh && this.mListener != null) {
            this.mListener.onRefresh();
        }

    }

    private void resetFooterHeight() {
        int var1 = this.mFooterView.getBottomMargin();
        if (var1 > 0) {
            this.mScrollBack = 1;
            this.mScroller.startScroll(0, var1, 0, -var1, 400);
            this.invalidate();
        }

    }

    private void resetHeaderHeight() {
        int var3 = this.mHeader.getVisibleHeight();
        if (var3 != 0 && (!this.mPullRefreshing || var3 > this.mHeaderHeight)) {
            byte var2 = 0;
            int var1 = var2;
            if (this.mPullRefreshing) {
                var1 = var2;
                if (var3 > this.mHeaderHeight) {
                    var1 = this.mHeaderHeight;
                }
            }

            this.mScrollBack = 0;
            this.mScroller.startScroll(0, var3, 0, var1 - var3, 400);
            this.invalidate();
        }
    }

    private void startLoadMore() {
        this.mPullLoading = true;
        this.mFooterView.setState(2);
        this.loadMore();
    }

    private void updateFooterHeight(float var1) {
        int var2 = this.mFooterView.getBottomMargin() + (int) var1;
        if (this.mEnablePullLoad && !this.mPullLoading) {
            if (var2 > 50) {
                this.mFooterView.setState(1);
            } else {
                this.mFooterView.setState(0);
            }
        }

        this.mFooterView.setBottomMargin(var2);
    }

    private void updateHeaderHeight(float var1) {
        this.mHeader.setVisibleHeight((int) var1 + this.mHeader.getVisibleHeight());
        if (this.mEnablePullRefresh && !this.mPullRefreshing) {
            if (this.mHeader.getVisibleHeight() > this.mHeaderHeight) {
                this.mHeader.setState(1);
            } else {
                this.mHeader.setState(0);
            }
        }

        this.setSelection(0);
    }

    public void autoRefresh() {
        this.mHeader.setVisibleHeight(this.mHeaderHeight);
        if (this.mEnablePullRefresh && !this.mPullRefreshing) {
            if (this.mHeader.getVisibleHeight() > this.mHeaderHeight) {
                this.mHeader.setState(1);
            } else {
                this.mHeader.setState(0);
            }
        }

        this.mPullRefreshing = true;
        this.mHeader.setState(2);
        this.refresh();
    }

    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            if (this.mScrollBack == 0) {
                this.mHeader.setVisibleHeight(this.mScroller.getCurrY());
            } else {
                this.mFooterView.setBottomMargin(this.mScroller.getCurrY());
            }

            this.postInvalidate();
            this.invokeOnScrolling();
        }

        super.computeScroll();
    }

    public void onScroll(AbsListView var1, int var2, int var3, int var4) {
        this.mTotalItemCount = var4;
        if (this.mScrollListener != null) {
            this.mScrollListener.onScroll(var1, var2, var3, var4);
        }

    }

    public void onScrollStateChanged(AbsListView var1, int var2) {
        if (this.mScrollListener != null) {
            this.mScrollListener.onScrollStateChanged(var1, var2);
        }

        if (var2 == 0 && this.mEnableAutoLoad && this.getLastVisiblePosition() == this.getCount() - 1) {
            this.startLoadMore();
        }

    }

    public boolean onTouchEvent(MotionEvent var1) {
        if (this.mLastY == -1.0F) {
            this.mLastY = var1.getRawY();
        }

        switch (var1.getAction()) {
            case 0:
                this.mLastY = var1.getRawY();
                break;
            case 1:
            default:
                this.mLastY = -1.0F;
                if (this.getFirstVisiblePosition() == 0) {
                    if (this.mEnablePullRefresh && this.mHeader.getVisibleHeight() > this.mHeaderHeight) {
                        this.mPullRefreshing = true;
                        this.mHeader.setState(2);
                        this.refresh();
                    }

                    this.resetHeaderHeight();
                } else if (this.getLastVisiblePosition() == this.mTotalItemCount - 1) {
                    if (this.mEnablePullLoad && this.mFooterView.getBottomMargin() > 50) {
                        this.startLoadMore();
                    }

                    this.resetFooterHeight();
                }
                break;
            case 2:
                float var2 = var1.getRawY() - this.mLastY;
                this.mLastY = var1.getRawY();
                if (this.getFirstVisiblePosition() == 0 && (this.mHeader.getVisibleHeight() > 0 || var2 > 0.0F)) {
                    this.updateHeaderHeight(var2 / 1.8F);
                    this.invokeOnScrolling();
                } else if (this.getLastVisiblePosition() == this.mTotalItemCount - 1 && (this.mFooterView.getBottomMargin() > 0 || var2 < 0.0F)) {
                    this.updateFooterHeight(-var2 / 1.8F);
                }
        }

        return super.onTouchEvent(var1);
    }

    public void setAdapter(ListAdapter var1) {
        if (!this.mIsFooterReady) {
            this.mIsFooterReady = true;
            this.addFooterView(this.mFooterLayout);
        }

        super.setAdapter(var1);
    }

    public void setAutoLoadEnable(boolean var1) {
        this.mEnableAutoLoad = var1;
    }

    public void setOnScrollListener(OnScrollListener var1) {
        this.mScrollListener = var1;
    }

    public void setPullLoadEnable(boolean var1) {
        this.mEnablePullLoad = var1;
        if (!this.mEnablePullLoad) {
            this.mFooterView.setBottomMargin(0);
            this.mFooterView.hide();
            this.mFooterView.setPadding(0, 0, 0, 0);
            this.mFooterView.setOnClickListener((OnClickListener) null);
        } else {
            this.mPullLoading = false;
            this.mFooterView.setPadding(0, 0, 0, 0);
            this.mFooterView.show();
            this.mFooterView.setState(0);
            this.mFooterView.setOnClickListener(new OnClickListener() {
                public void onClick(View var1) {
                    XListView.this.startLoadMore();
                }
            });
        }
    }

    public void setPullRefreshEnable(boolean var1) {
        this.mEnablePullRefresh = var1;
        RelativeLayout var3 = this.mHeaderContent;
        int var2;
        if (var1) {
            var2 = 0;
        } else {
            var2 = 4;
        }
        var3.setVisibility(var2);
    }

    public void setRefreshTime(String var1) {
        this.mHeaderTime.setText(var1);
    }

    public void setXListViewListener(XListView.IXListViewListener var1) {
        this.mListener = var1;
    }

    public void stopLoadMore() {
        if (this.mPullLoading) {
            this.mPullLoading = false;
            this.mFooterView.setState(0);
        }

    }

    public void stopRefresh() {
        if (this.mPullRefreshing) {
            this.mPullRefreshing = false;
            this.resetHeaderHeight();
        }

    }

    public interface IXListViewListener {
        void onLoadMore();

        void onRefresh();
    }

    public interface OnXScrollListener extends OnScrollListener {
        void onXScrolling(View var1);
    }
}
