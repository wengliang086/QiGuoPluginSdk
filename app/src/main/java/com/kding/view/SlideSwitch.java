//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.AccelerateDecelerateInterpolator;

public class SlideSwitch extends View {
  private static final int DEFAULT_COLOR_THEME = Color.parseColor("#ffF44331");
  private static final int RIM_SIZE = 6;
  public static final int SHAPE_CIRCLE = 2;
  public static final int SHAPE_RECT = 1;
  private int alpha;
  private RectF backCircleRect;
  private Rect backRect;
  private int color_theme;
  private int diffX;
  private int eventLastX;
  private int eventStartX;
  private RectF frontCircleRect;
  private Rect frontRect;
  private int frontRect_left;
  private int frontRect_left_begin;
  public boolean isOpen;
  private SlideSwitch.SlideListener listener;
  private int max_left;
  private int min_left;
  private Paint paint;
  private int shape;
  private boolean slideable;

  public SlideSwitch(Context var1) {
    this(var1, (AttributeSet)null);
  }

  public SlideSwitch(Context var1, AttributeSet var2) {
    this(var1, var2, 0);
  }

  public SlideSwitch(Context var1, AttributeSet var2, int var3) {
    super(var1, var2, var3);
    this.frontRect_left_begin = 6;
    this.diffX = 0;
    this.slideable = true;
    this.listener = null;
    this.paint = new Paint();
    this.paint.setAntiAlias(true);
    this.color_theme = DEFAULT_COLOR_THEME;
    this.isOpen = true;
    this.shape = 2;
  }

  private void invalidateView() {
    if(Looper.getMainLooper() == Looper.myLooper()) {
      this.invalidate();
    } else {
      this.postInvalidate();
    }
  }

  public void initDrawingVal() {
    int var1 = this.getMeasuredWidth();
    int var2 = this.getMeasuredHeight();
    this.backCircleRect = new RectF();
    this.frontCircleRect = new RectF();
    this.frontRect = new Rect();
    this.backRect = new Rect(0, 0, var1, var2);
    this.min_left = 6;
    if(this.shape == 1) {
      this.max_left = var1 / 2;
    } else {
      this.max_left = var1 - (var2 - 12) - 6;
    }

    if(this.isOpen) {
      this.frontRect_left = this.max_left;
      this.alpha = 255;
    } else {
      this.frontRect_left = 6;
      this.alpha = 0;
    }

    this.frontRect_left_begin = this.frontRect_left;
  }

  public int measureDimension(int var1, int var2) {
    int var4 = MeasureSpec.getMode(var2);
    int var3 = MeasureSpec.getSize(var2);
    if(var4 == 1073741824) {
      var1 = var3;
    } else {
      var2 = var1;
      var1 = var1;
      if(var4 == -2147483648) {
        return Math.min(var2, var3);
      }
    }

    return var1;
  }

  public void moveToDest(final boolean var1) {
    int var3 = this.frontRect_left;
    int var2;
    if(var1) {
      var2 = this.max_left;
    } else {
      var2 = this.min_left;
    }

    ValueAnimator var4 = ValueAnimator.ofInt(new int[]{var3, var2});
    var4.setDuration(500L);
    var4.setInterpolator(new AccelerateDecelerateInterpolator());
    var4.start();
    var4.addUpdateListener(new AnimatorUpdateListener() {
      public void onAnimationUpdate(ValueAnimator var1) {
        SlideSwitch.this.frontRect_left = ((Integer)var1.getAnimatedValue()).intValue();
        SlideSwitch.this.alpha = (int)(255.0F * (float)SlideSwitch.this.frontRect_left / (float)SlideSwitch.this.max_left);
        SlideSwitch.this.invalidateView();
      }
    });
    var4.addListener(new AnimatorListenerAdapter() {
      public void onAnimationEnd(Animator var1x) {
        if(var1) {
          SlideSwitch.this.isOpen = true;
          if(SlideSwitch.this.listener != null) {
            SlideSwitch.this.listener.open();
          }

          SlideSwitch.this.frontRect_left_begin = SlideSwitch.this.max_left;
        } else {
          SlideSwitch.this.isOpen = false;
          if(SlideSwitch.this.listener != null) {
            SlideSwitch.this.listener.close();
          }

          SlideSwitch.this.frontRect_left_begin = SlideSwitch.this.min_left;
        }
      }
    });
  }

  protected void onDraw(Canvas var1) {
    if(this.shape == 1) {
      this.paint.setColor(-7829368);
      var1.drawRect(this.backRect, this.paint);
      this.paint.setColor(this.color_theme);
      this.paint.setAlpha(this.alpha);
      var1.drawRect(this.backRect, this.paint);
      this.frontRect.set(this.frontRect_left, 6, this.frontRect_left + this.getMeasuredWidth() / 2 - 6, this.getMeasuredHeight() - 6);
      this.paint.setColor(-1);
      var1.drawRect(this.frontRect, this.paint);
    } else {
      int var2 = this.backRect.height() / 2 - 6;
      this.paint.setColor(-7829368);
      this.backCircleRect.set(this.backRect);
      var1.drawRoundRect(this.backCircleRect, (float)var2, (float)var2, this.paint);
      this.paint.setColor(this.color_theme);
      this.paint.setAlpha(this.alpha);
      var1.drawRoundRect(this.backCircleRect, (float)var2, (float)var2, this.paint);
      this.frontRect.set(this.frontRect_left, 6, this.frontRect_left + this.backRect.height() - 12, this.backRect.height() - 6);
      this.frontCircleRect.set(this.frontRect);
      this.paint.setColor(-1);
      var1.drawRoundRect(this.frontCircleRect, (float)var2, (float)var2, this.paint);
    }
  }

  protected void onMeasure(int var1, int var2) {
    super.onMeasure(var1, var2);
    this.setMeasuredDimension(this.measureDimension(280, var1), this.measureDimension(140, var2));
    this.initDrawingVal();
  }

  protected void onRestoreInstanceState(Parcelable var1) {
    Parcelable var2 = var1;
    if(var1 instanceof Bundle) {
      Bundle var3 = (Bundle)var1;
      this.isOpen = var3.getBoolean("isOpen");
      var2 = var3.getParcelable("instanceState");
    }

    super.onRestoreInstanceState(var2);
  }

  protected Parcelable onSaveInstanceState() {
    Bundle var1 = new Bundle();
    var1.putParcelable("instanceState", super.onSaveInstanceState());
    var1.putBoolean("isOpen", this.isOpen);
    return var1;
  }

  public boolean onTouchEvent(MotionEvent var1) {
    boolean var5 = true;
    boolean var4;
    if(!this.slideable) {
      var4 = super.onTouchEvent(var1);
    } else {
      int var2;
      switch(var1.getAction()) {
        case 0:
          this.eventStartX = (int)var1.getRawX();
          return true;
        case 1:
          var2 = (int)(var1.getRawX() - (float)this.eventStartX);
          this.frontRect_left_begin = this.frontRect_left;
          if(this.frontRect_left_begin > this.max_left / 2) {
            var4 = true;
          } else {
            var4 = false;
          }

          var5 = var4;
          if(Math.abs(var2) < 3) {
            if(!var4) {
              var5 = true;
            } else {
              var5 = false;
            }
          }

          this.moveToDest(var5);
          return true;
        case 2:
          this.eventLastX = (int)var1.getRawX();
          this.diffX = this.eventLastX - this.eventStartX;
          int var3 = this.diffX + this.frontRect_left_begin;
          var2 = var3;
          if(var3 > this.max_left) {
            var2 = this.max_left;
          }

          var3 = var2;
          if(var2 < this.min_left) {
            var3 = this.min_left;
          }

          var4 = var5;
          if(var3 >= this.min_left) {
            var4 = var5;
            if(var3 <= this.max_left) {
              this.frontRect_left = var3;
              this.alpha = (int)(255.0F * (float)var3 / (float)this.max_left);
              this.invalidateView();
              return true;
            }
          }
          break;
        default:
          return true;
      }
    }

    return var4;
  }

  public void setShapeType(int var1) {
    this.shape = var1;
  }

  public void setSlideListener(SlideSwitch.SlideListener var1) {
    this.listener = var1;
  }

  public void setSlideable(boolean var1) {
    this.slideable = var1;
  }

  public void setState(boolean var1) {
    this.isOpen = var1;
    this.initDrawingVal();
    this.invalidateView();
    if(this.listener != null) {
      if(!var1) {
        this.listener.close();
        return;
      }

      this.listener.open();
    }

  }

  public interface SlideListener {
    void close();

    void open();
  }
}
