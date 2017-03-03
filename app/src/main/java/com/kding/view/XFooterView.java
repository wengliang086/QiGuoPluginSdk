//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kding.R;

public class XFooterView extends LinearLayout {
    public static final int STATE_LOADING = 2;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_READY = 1;
    private final int ROTATE_ANIM_DURATION = 180;
    private TextView mHintView;
    private View mLayout;
    private View mProgressBar;
    private Animation mRotateDownAnim;
    private Animation mRotateUpAnim;
    private int mState = 0;

    public XFooterView(Context var1) {
        super(var1);
        this.initView(var1);
    }

    public XFooterView(Context var1, AttributeSet var2) {
        super(var1, var2);
        this.initView(var1);
    }

    private void initView(Context var1) {
        this.mLayout = LayoutInflater.from(var1).inflate(R.layout.qxz_vw_footer, (ViewGroup) null);
        this.mLayout.setLayoutParams(new LayoutParams(-1, -2));
        this.addView(this.mLayout);
        this.mProgressBar = this.mLayout.findViewById(R.id.qxz_footer_progressbar);
        this.mHintView = (TextView) this.mLayout.findViewById(R.id.qxz_footer_hint_text);
        this.mRotateUpAnim = new RotateAnimation(0.0F, 180.0F, 1, 0.5F, 1, 0.5F);
        this.mRotateUpAnim.setDuration(180L);
        this.mRotateUpAnim.setFillAfter(true);
        this.mRotateDownAnim = new RotateAnimation(180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
        this.mRotateDownAnim.setDuration(180L);
        this.mRotateDownAnim.setFillAfter(true);
    }

    public int getBottomMargin() {
        return ((LayoutParams) this.mLayout.getLayoutParams()).bottomMargin;
    }

    public void hide() {
        LayoutParams var1 = (LayoutParams) this.mLayout.getLayoutParams();
        var1.height = 0;
        this.mLayout.setLayoutParams(var1);
    }

    public void loading() {
        this.mHintView.setVisibility(8);
        this.mProgressBar.setVisibility(0);
    }

    public void normal() {
        this.mHintView.setVisibility(0);
        this.mProgressBar.setVisibility(8);
    }

    public void setBottomMargin(int var1) {
        if (var1 >= 0) {
            LayoutParams var2 = (LayoutParams) this.mLayout.getLayoutParams();
            var2.bottomMargin = var1;
            this.mLayout.setLayoutParams(var2);
        }
    }

    public void setState(int var1) {
        if (var1 != this.mState) {
            if (var1 == 2) {
                this.mProgressBar.setVisibility(0);
                this.mHintView.setVisibility(4);
            } else {
                this.mHintView.setVisibility(0);
                this.mProgressBar.setVisibility(4);
            }

            switch (var1) {
                case 0:
                    this.mHintView.setText("加载更多...");
                    break;
                case 1:
                    if (this.mState != 1) {
                        this.mHintView.setText("释放刷新");
                    }
            }

            this.mState = var1;
        }
    }

    public void show() {
        LayoutParams var1 = (LayoutParams) this.mLayout.getLayoutParams();
        var1.height = -2;
        this.mLayout.setLayoutParams(var1);
    }
}
