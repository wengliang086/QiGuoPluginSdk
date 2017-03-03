//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class XHeaderView extends LinearLayout {
  public static final int STATE_NORMAL = 0;
  public static final int STATE_READY = 1;
  public static final int STATE_REFRESHING = 2;
  private final int ROTATE_ANIM_DURATION = 180;
  private ImageView mArrowImageView;
  private LinearLayout mContainer;
  private TextView mHintTextView;
  private boolean mIsFirst;
  private ProgressBar mProgressBar;
  private Animation mRotateDownAnim;
  private Animation mRotateUpAnim;
  private int mState = 0;

  public XHeaderView(Context var1) {
    super(var1);
    this.initView(var1);
  }

  public XHeaderView(Context var1, AttributeSet var2) {
    super(var1, var2);
    this.initView(var1);
  }

  private void initView(Context var1) {
    LayoutParams var2 = new LayoutParams(-1, 0);
    this.mContainer = (LinearLayout)LayoutInflater.from(var1).inflate(2130903075, (ViewGroup)null);
    this.addView(this.mContainer, var2);
    this.setGravity(80);
    this.mArrowImageView = (ImageView)this.findViewById(2131230829);
    this.mArrowImageView.setImageResource(2130837530);
    this.mHintTextView = (TextView)this.findViewById(2131230826);
    this.mProgressBar = (ProgressBar)this.findViewById(2131230828);
    this.mRotateUpAnim = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
    this.mRotateUpAnim.setDuration(180L);
    this.mRotateUpAnim.setFillAfter(true);
    this.mRotateDownAnim = new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
    this.mRotateDownAnim.setDuration(180L);
    this.mRotateDownAnim.setFillAfter(true);
  }

  public int getVisibleHeight() {
    return this.mContainer.getHeight();
  }

  public void setState(int var1) {
    if(var1 == this.mState && this.mIsFirst) {
      this.mIsFirst = true;
    } else {
      if(var1 == 2) {
        this.mArrowImageView.clearAnimation();
        this.mArrowImageView.setVisibility(4);
        this.mProgressBar.setVisibility(0);
      } else {
        this.mArrowImageView.setVisibility(0);
        this.mProgressBar.setVisibility(4);
      }

      switch(var1) {
        case 0:
          if(this.mState == 1) {
            this.mArrowImageView.startAnimation(this.mRotateDownAnim);
          }

          if(this.mState == 2) {
            this.mArrowImageView.clearAnimation();
          }

          this.mHintTextView.setText("下拉刷新");
          break;
        case 1:
          if(this.mState != 1) {
            this.mArrowImageView.clearAnimation();
            this.mArrowImageView.startAnimation(this.mRotateUpAnim);
            this.mHintTextView.setText("释放刷新");
          }
          break;
        case 2:
          this.mHintTextView.setText("刷新中...");
      }

      this.mState = var1;
    }
  }

  public void setVisibleHeight(int var1) {
    int var2 = var1;
    if(var1 < 0) {
      var2 = 0;
    }

    LayoutParams var3 = (LayoutParams)this.mContainer.getLayoutParams();
    var3.height = var2;
    this.mContainer.setLayoutParams(var3);
  }
}
