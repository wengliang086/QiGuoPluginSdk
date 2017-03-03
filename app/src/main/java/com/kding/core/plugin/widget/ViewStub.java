//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewGroup.LayoutParams;
import android.widget.RemoteViews.RemoteView;
import java.lang.ref.WeakReference;

@RemoteView
public final class ViewStub extends View {
    private int mLayoutResource;
    private int mInflatedId;
    private WeakReference<View> mInflatedViewRef;
    private LayoutInflater mInflater;
    private ViewStub.OnInflateListener mInflateListener;

    public ViewStub(Context context) {
        super(context);
        this.mLayoutResource = 0;
        this.initialize(context);
    }

    public ViewStub(Context context, int layoutResource) {
        super(context);
        this.mLayoutResource = 0;
        this.mLayoutResource = layoutResource;
        this.initialize(context);
    }

    public ViewStub(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewStub(Context context, AttributeSet attrs, int defStyle) {
        super(context);
        this.mLayoutResource = 0;
        int[] attrsArr = null;
        int index = 0;
        int layoutId = 0;
        int[] attrViewArr = null;
        int viewId = 0;

        try {
            Class a = Class.forName("com.android.internal.R$styleable");
            attrsArr = (int[])((int[])a.getField("ViewStub").get((Object)null));
            index = a.getField("ViewStub_inflatedId").getInt((Object)null);
            layoutId = a.getField("ViewStub_layout").getInt((Object)null);
            attrViewArr = (int[])((int[])a.getField("View").get((Object)null));
            viewId = a.getField("View_id").getInt((Object)null);
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        TypedArray a1 = context.obtainStyledAttributes(attrs, attrsArr, defStyle, 0);
        this.mInflatedId = a1.getResourceId(index, -1);
        this.mLayoutResource = a1.getResourceId(layoutId, 0);
        a1.recycle();
        a1 = context.obtainStyledAttributes(attrs, attrViewArr, defStyle, 0);
        this.setId(a1.getResourceId(viewId, -1));
        a1.recycle();
        this.initialize(context);
    }

    private void initialize(Context context) {
        this.setVisibility(8);
        this.setWillNotDraw(true);
    }

    public int getInflatedId() {
        return this.mInflatedId;
    }

    public void setInflatedId(int inflatedId) {
        this.mInflatedId = inflatedId;
    }

    public int getLayoutResource() {
        return this.mLayoutResource;
    }

    public void setLayoutResource(int layoutResource) {
        this.mLayoutResource = layoutResource;
    }

    public void setLayoutInflater(LayoutInflater inflater) {
        this.mInflater = inflater;
    }

    public LayoutInflater getLayoutInflater() {
        return this.mInflater;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.setMeasuredDimension(0, 0);
    }

    @SuppressLint({"MissingSuperCall"})
    public void draw(Canvas canvas) {
    }

    protected void dispatchDraw(Canvas canvas) {
    }

    public void setVisibility(int visibility) {
        if(this.mInflatedViewRef != null) {
            View view = (View)this.mInflatedViewRef.get();
            if(view == null) {
                throw new IllegalStateException("setVisibility called on un-referenced view");
            }

            view.setVisibility(visibility);
        } else {
            super.setVisibility(visibility);
            if(visibility == 0 || visibility == 4) {
                this.inflate();
            }
        }

    }

    public View inflate() {
        ViewParent viewParent = this.getParent();
        if(viewParent != null && viewParent instanceof ViewGroup) {
            if(this.mLayoutResource != 0) {
                ViewGroup parent = (ViewGroup)viewParent;
                LayoutInflater factory;
                if(this.mInflater != null) {
                    factory = this.mInflater;
                } else {
                    factory = LayoutInflater.from(this.getContext());
                }

                View view = factory.inflate(this.mLayoutResource, parent, false);
                if(this.mInflatedId != -1) {
                    view.setId(this.mInflatedId);
                }

                int index = parent.indexOfChild(this);
                parent.removeViewInLayout(this);
                LayoutParams layoutParams = this.getLayoutParams();
                if(layoutParams != null) {
                    parent.addView(view, index, layoutParams);
                } else {
                    parent.addView(view, index);
                }

                this.mInflatedViewRef = new WeakReference(view);
                if(this.mInflateListener != null) {
                    this.mInflateListener.onInflate(this, view);
                }

                return view;
            } else {
                throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
            }
        } else {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        }
    }

    public void setOnInflateListener(ViewStub.OnInflateListener inflateListener) {
        this.mInflateListener = inflateListener;
    }

    public interface OnInflateListener {
        void onInflate(ViewStub var1, View var2);
    }
}
