//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView.BufferType;

public class BadgeView extends TextView {
  private boolean mHideOnNull;

  public BadgeView(Context var1) {
    this(var1, (AttributeSet)null);
  }

  public BadgeView(Context var1, AttributeSet var2) {
    this(var1, var2, 16842884);
  }

  public BadgeView(Context var1, AttributeSet var2, int var3) {
    super(var1, var2, var3);
    this.mHideOnNull = true;
    this.init();
  }

  private int dip2Px(float var1) {
    return (int)(this.getContext().getResources().getDisplayMetrics().density * var1 + 0.5F);
  }

  private void init() {
    if(!(this.getLayoutParams() instanceof LayoutParams)) {
      this.setLayoutParams(new LayoutParams(-2, -2, 53));
    }

    this.setTextColor(-1);
    this.setTypeface(Typeface.DEFAULT_BOLD);
    this.setTextSize(2, 7.0F);
    this.setPadding(this.dip2Px(3.5F), this.dip2Px(1.0F), this.dip2Px(4.5F), this.dip2Px(1.0F));
    this.setBackground(9, Color.parseColor("#c61d25"));
    this.setGravity(17);
    this.setHideOnNull(true);
    this.setBadgeCount(0);
  }

  public void decrementBadgeCount(int var1) {
    this.incrementBadgeCount(-var1);
  }

  public Integer getBadgeCount() {
    if(this.getText() == null) {
      return null;
    } else {
      String var2 = this.getText().toString();

      int var1;
      try {
        var1 = Integer.parseInt(var2);
      } catch (NumberFormatException var3) {
        return null;
      }

      return Integer.valueOf(var1);
    }
  }

  public int getBadgeGravity() {
    return ((LayoutParams)this.getLayoutParams()).gravity;
  }

  public int[] getBadgeMargin() {
    LayoutParams var1 = (LayoutParams)this.getLayoutParams();
    return new int[]{var1.leftMargin, var1.topMargin, var1.rightMargin, var1.bottomMargin};
  }

  public void incrementBadgeCount(int var1) {
    Integer var2 = this.getBadgeCount();
    if(var2 == null) {
      this.setBadgeCount(var1);
    } else {
      this.setBadgeCount(var2.intValue() + var1);
    }
  }

  public boolean isHideOnNull() {
    return this.mHideOnNull;
  }

  public void setBackground(int var1, int var2) {
    var1 = this.dip2Px((float)var1);
    ShapeDrawable var3 = new ShapeDrawable(new RoundRectShape(new float[]{(float)var1, (float)var1, (float)var1, (float)var1, (float)var1, (float)var1, (float)var1, (float)var1}, (RectF)null, (float[])null));
    var3.getPaint().setColor(var2);
    this.setBackgroundDrawable(var3);
  }

  public void setBadgeCount(int var1) {
    this.setText(String.valueOf(var1));
  }

  public void setBadgeGravity(int var1) {
    LayoutParams var2 = (LayoutParams)this.getLayoutParams();
    var2.gravity = var1;
    this.setLayoutParams(var2);
  }

  public void setBadgeMargin(int var1) {
    this.setBadgeMargin(var1, var1, var1, var1);
  }

  public void setBadgeMargin(int var1, int var2, int var3, int var4) {
    LayoutParams var5 = (LayoutParams)this.getLayoutParams();
    var5.leftMargin = this.dip2Px((float)var1);
    var5.topMargin = this.dip2Px((float)var2);
    var5.rightMargin = this.dip2Px((float)var3);
    var5.bottomMargin = this.dip2Px((float)var4);
    this.setLayoutParams(var5);
  }

  public void setHideOnNull(boolean var1) {
    this.mHideOnNull = var1;
    this.setText(this.getText());
  }

  public void setTargetView(View var1) {
    if(this.getParent() != null) {
      ((ViewGroup)this.getParent()).removeView(this);
    }

    if(var1 != null) {
      if(var1.getParent() instanceof FrameLayout) {
        ((FrameLayout)var1.getParent()).addView(this);
        return;
      }

      if(var1.getParent() instanceof ViewGroup) {
        ViewGroup var3 = (ViewGroup)var1.getParent();
        int var2 = var3.indexOfChild(var1);
        var3.removeView(var1);
        FrameLayout var4 = new FrameLayout(this.getContext());
        var3.addView(var4, var2, var1.getLayoutParams());
        var4.addView(var1);
        var4.addView(this);
        return;
      }

      if(var1.getParent() == null) {
        Log.e(this.getClass().getSimpleName(), "ParentView is needed");
        return;
      }
    }

  }

  public void setTargetView(TabWidget var1, int var2) {
    this.setTargetView(var1.getChildTabViewAt(var2));
  }

  public void setText(CharSequence var1, BufferType var2) {
    if(!this.isHideOnNull() || var1 != null && !var1.toString().equalsIgnoreCase("0")) {
      this.setVisibility(0);
    } else {
      this.setVisibility(8);
    }

    super.setText(var1, var2);
  }
}
