//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.kding.utils.DensityUtil;
import java.lang.reflect.Field;

public class CircleProgressBar extends ProgressBar {
  private static final String COLOR_FFD3D3D5 = "#ffe3e3e5";
  private static final String COLOR_FFF2A670 = "#fff2a670";
  private static final int DEFAULT_LINE_COUNT = 45;
  private static final float DEFAULT_LINE_WIDTH = 4.0F;
  private static final String DEFAULT_PATTERN = "%d%%";
  private static final float DEFAULT_PROGRESS_STROKE_WIDTH = 1.0F;
  private static final float DEFAULT_PROGRESS_TEXT_SIZE = 11.0F;
  private static final float DEFAULT_START_DEGREE = -90.0F;
  private static final int LINE = 0;
  private static final int LINEAR = 0;
  private static final int RADIAL = 1;
  private static final int SOLID = 1;
  private static final int SOLID_LINE = 2;
  private static final int SWEEP = 2;
  private int mBackgroundColor;
  private final Paint mBackgroundPaint;
  private Cap mCap;
  private float mCenterX;
  private float mCenterY;
  private boolean mDrawProgressText;
  private int mLineCount;
  private float mLineWidth;
  private int mProgressBackgroundColor;
  private final Paint mProgressBackgroundPaint;
  private int mProgressEndColor;
  private final Paint mProgressPaint;
  private final RectF mProgressRectF;
  private int mProgressStartColor;
  private float mProgressStrokeWidth;
  private int mProgressTextColor;
  private String mProgressTextFormatPattern;
  private final Paint mProgressTextPaint;
  private final Rect mProgressTextRect;
  private float mProgressTextSize;
  private float mRadius;
  private int mShader;
  private int mStyle;

  public CircleProgressBar(Context var1) {
    this(var1, (AttributeSet)null);
  }

  public CircleProgressBar(Context var1, AttributeSet var2) {
    super(var1, var2);
    this.mProgressRectF = new RectF();
    this.mProgressTextRect = new Rect();
    this.mProgressPaint = new Paint(1);
    this.mProgressBackgroundPaint = new Paint(1);
    this.mBackgroundPaint = new Paint(1);
    this.mProgressTextPaint = new Paint(1);
    this.adjustIndeterminate();
    this.initFromAttributes(var1, var2);
    this.initPaint();
  }

  private void adjustIndeterminate() {
    try {
      Field var1 = ProgressBar.class.getDeclaredField("mOnlyIndeterminate");
      var1.setAccessible(true);
      var1.set(this, Boolean.valueOf(false));
      var1 = ProgressBar.class.getDeclaredField("mIndeterminate");
      var1.setAccessible(true);
      var1.set(this, Boolean.valueOf(false));
      var1 = ProgressBar.class.getDeclaredField("mCurrentDrawable");
      var1.setAccessible(true);
      var1.set(this, (Object)null);
    } catch (NoSuchFieldException var2) {
      var2.printStackTrace();
    } catch (IllegalAccessException var3) {
      var3.printStackTrace();
    }
  }

  private void drawBackground(Canvas var1) {
    if(this.mBackgroundColor != 0) {
      var1.drawCircle(this.mCenterX, this.mCenterX, this.mRadius, this.mBackgroundPaint);
    }

  }

  private void drawLineProgress(Canvas var1) {
    float var2 = (float)(6.283185307179586D / (double)this.mLineCount);
    float var3 = this.mRadius;
    float var4 = this.mRadius - this.mLineWidth;
    int var10 = (int)((float)this.getProgress() / (float)this.getMax() * (float)this.mLineCount);

    for(int var9 = 0; var9 < this.mLineCount; ++var9) {
      float var8 = (float)var9 * var2;
      float var5 = this.mCenterX + (float)Math.sin((double)var8) * var4;
      float var6 = this.mCenterX - (float)Math.cos((double)var8) * var4;
      float var7 = this.mCenterX + (float)Math.sin((double)var8) * var3;
      var8 = this.mCenterX - (float)Math.cos((double)var8) * var3;
      if(var9 < var10) {
        var1.drawLine(var5, var6, var7, var8, this.mProgressPaint);
      } else {
        var1.drawLine(var5, var6, var7, var8, this.mProgressBackgroundPaint);
      }
    }

  }

  private void drawProgress(Canvas var1) {
    switch(this.mStyle) {
      case 1:
        this.drawSolidProgress(var1);
        return;
      case 2:
        this.drawSolidLineProgress(var1);
        return;
      default:
        this.drawLineProgress(var1);
    }
  }

  private void drawProgressText(Canvas var1) {
    if(this.mDrawProgressText) {
      String var2 = String.format(this.mProgressTextFormatPattern, new Object[]{Integer.valueOf(this.getProgress())});
      this.mProgressTextPaint.setTextSize(this.mProgressTextSize);
      this.mProgressTextPaint.setColor(this.mProgressTextColor);
      this.mProgressTextPaint.getTextBounds(var2, 0, var2.length(), this.mProgressTextRect);
      var1.drawText(var2, this.mCenterX, this.mCenterY + (float)(this.mProgressTextRect.height() / 2), this.mProgressTextPaint);
    }
  }

  private void drawSolidLineProgress(Canvas var1) {
    var1.drawArc(this.mProgressRectF, -90.0F, 360.0F, false, this.mProgressBackgroundPaint);
    var1.drawArc(this.mProgressRectF, -90.0F, (float)this.getProgress() * 360.0F / (float)this.getMax(), false, this.mProgressPaint);
  }

  private void drawSolidProgress(Canvas var1) {
    var1.drawArc(this.mProgressRectF, -90.0F, 360.0F, false, this.mProgressBackgroundPaint);
    var1.drawArc(this.mProgressRectF, -90.0F, (float)this.getProgress() * 360.0F / (float)this.getMax(), true, this.mProgressPaint);
  }

  private void init() {
    this.mBackgroundColor = Color.parseColor("#1F243A");
    this.mDrawProgressText = true;
    this.mLineCount = 45;
    this.mProgressTextFormatPattern = "领取";
    this.mStyle = 2;
    this.mShader = 2;
    this.mCap = Cap.BUTT;
    this.mLineWidth = 4.0F;
    this.mProgressTextSize = (float)DensityUtil.dp2px(this.getContext(), 10.0F);
    this.mProgressStrokeWidth = (float)DensityUtil.dp2px(this.getContext(), 2.0F);
    this.mProgressStartColor = Color.parseColor("#fff2a670");
    this.mProgressEndColor = Color.parseColor("#fff2a670");
    this.mProgressTextColor = Color.parseColor("#fff2a670");
    this.mProgressBackgroundColor = Color.parseColor("#9DA7BF");
  }

  private void initFromAttributes(Context var1, AttributeSet var2) {
    this.init();
  }

  private void initPaint() {
    this.mProgressTextPaint.setTextAlign(Align.CENTER);
    this.mProgressTextPaint.setTextSize(this.mProgressTextSize);
    Paint var2 = this.mProgressPaint;
    android.graphics.Paint.Style var1;
    if(this.mStyle == 1) {
      var1 = android.graphics.Paint.Style.FILL;
    } else {
      var1 = android.graphics.Paint.Style.STROKE;
    }

    var2.setStyle(var1);
    this.mProgressPaint.setStrokeWidth(this.mProgressStrokeWidth);
    this.mProgressPaint.setColor(this.mProgressStartColor);
    this.mProgressPaint.setStrokeCap(this.mCap);
    var2 = this.mProgressBackgroundPaint;
    if(this.mStyle == 1) {
      var1 = android.graphics.Paint.Style.FILL;
    } else {
      var1 = android.graphics.Paint.Style.STROKE;
    }

    var2.setStyle(var1);
    this.mProgressBackgroundPaint.setStrokeWidth(this.mProgressStrokeWidth);
    this.mProgressBackgroundPaint.setColor(this.mProgressBackgroundColor);
    this.mProgressBackgroundPaint.setStrokeCap(this.mCap);
    this.mBackgroundPaint.setStyle(android.graphics.Paint.Style.FILL);
    this.mBackgroundPaint.setColor(this.mBackgroundColor);
  }

  private void updateProgressShader() {
    if(this.mProgressStartColor == this.mProgressEndColor) {
      this.mProgressPaint.setShader((Shader)null);
      this.mProgressPaint.setColor(this.mProgressStartColor);
    } else {
      Object var4 = null;
      switch(this.mShader) {
        case 0:
          var4 = new LinearGradient(this.mProgressRectF.left, this.mProgressRectF.top, this.mProgressRectF.left, this.mProgressRectF.bottom, this.mProgressStartColor, this.mProgressEndColor, TileMode.CLAMP);
          break;
        case 1:
          var4 = new RadialGradient(this.mCenterX, this.mCenterY, this.mRadius, this.mProgressStartColor, this.mProgressEndColor, TileMode.CLAMP);
          break;
        case 2:
          float var3 = (float)((double)this.mProgressStrokeWidth / 3.141592653589793D * 2.0D / (double)this.mRadius);
          double var1;
          if(this.mCap == Cap.BUTT && this.mStyle == 2) {
            var1 = 0.0D;
          } else {
            var1 = Math.toDegrees((double)var3);
          }

          var3 = (float)(-90.0D - var1);
          var4 = new SweepGradient(this.mCenterX, this.mCenterY, new int[]{this.mProgressStartColor, this.mProgressEndColor}, new float[]{0.0F, 1.0F});
          Matrix var5 = new Matrix();
          var5.postRotate(var3, this.mCenterX, this.mCenterY);
          ((Shader)var4).setLocalMatrix(var5);
      }

      this.mProgressPaint.setShader((Shader)var4);
    }
  }

  public int getBackgroundColor() {
    return this.mBackgroundColor;
  }

  public Cap getCap() {
    return this.mCap;
  }

  public int getLineCount() {
    return this.mLineCount;
  }

  public float getLineWidth() {
    return this.mLineWidth;
  }

  public int getProgressBackgroundColor() {
    return this.mProgressBackgroundColor;
  }

  public int getProgressEndColor() {
    return this.mProgressEndColor;
  }

  public int getProgressStartColor() {
    return this.mProgressStartColor;
  }

  public float getProgressStrokeWidth() {
    return this.mProgressStrokeWidth;
  }

  public int getProgressTextColor() {
    return this.mProgressTextColor;
  }

  public String getProgressTextFormatPattern() {
    return this.mProgressTextFormatPattern;
  }

  public float getProgressTextSize() {
    return this.mProgressTextSize;
  }

  public int getShader() {
    return this.mShader;
  }

  public int getStyle() {
    return this.mStyle;
  }

  protected void onDraw(Canvas var1) {
    synchronized(this){}

    try {
      this.drawBackground(var1);
      this.drawProgress(var1);
      this.drawProgressText(var1);
    } finally {
      ;
    }

  }

  protected void onSizeChanged(int var1, int var2, int var3, int var4) {
    super.onSizeChanged(var1, var2, var3, var4);
    this.mCenterX = (float)(var1 / 2);
    this.mCenterY = (float)(var2 / 2);
    this.mRadius = Math.min(this.mCenterX, this.mCenterY);
    this.mProgressRectF.top = this.mCenterY - this.mRadius;
    this.mProgressRectF.bottom = this.mCenterY + this.mRadius;
    this.mProgressRectF.left = this.mCenterX - this.mRadius;
    this.mProgressRectF.right = this.mCenterX + this.mRadius;
    this.updateProgressShader();
    this.mProgressRectF.inset(this.mProgressStrokeWidth / 2.0F, this.mProgressStrokeWidth / 2.0F);
  }

  public void setBackgroundColor(int var1) {
    this.mBackgroundColor = var1;
    this.mBackgroundPaint.setColor(var1);
    this.invalidate();
  }

  public void setCap(Cap var1) {
    this.mCap = var1;
    this.mProgressPaint.setStrokeCap(var1);
    this.mProgressBackgroundPaint.setStrokeCap(var1);
    this.invalidate();
  }

  public void setLineCount(int var1) {
    this.mLineCount = var1;
    this.invalidate();
  }

  public void setLineWidth(float var1) {
    this.mLineWidth = var1;
    this.invalidate();
  }

  public void setProgressBackgroundColor(int var1) {
    this.mProgressBackgroundColor = var1;
    this.mProgressBackgroundPaint.setColor(this.mProgressBackgroundColor);
    this.invalidate();
  }

  public void setProgressEndColor(int var1) {
    this.mProgressEndColor = var1;
    this.updateProgressShader();
    this.invalidate();
  }

  public void setProgressStartColor(int var1) {
    this.mProgressStartColor = var1;
    this.updateProgressShader();
    this.invalidate();
  }

  public void setProgressStrokeWidth(float var1) {
    this.mProgressStrokeWidth = var1;
    this.mProgressRectF.inset(this.mProgressStrokeWidth / 2.0F, this.mProgressStrokeWidth / 2.0F);
    this.invalidate();
  }

  public void setProgressTextColor(int var1) {
    this.mProgressTextColor = var1;
    this.invalidate();
  }

  public void setProgressTextFormatPattern(String var1) {
    this.mProgressTextFormatPattern = var1;
    this.invalidate();
  }

  public void setProgressTextSize(float var1) {
    this.mProgressTextSize = var1;
    this.invalidate();
  }

  public void setShader(int var1) {
    this.mShader = var1;
    this.updateProgressShader();
    this.invalidate();
  }

  public void setStyle(int var1) {
    this.mStyle = var1;
    Paint var3 = this.mProgressPaint;
    android.graphics.Paint.Style var2;
    if(this.mStyle == 1) {
      var2 = android.graphics.Paint.Style.FILL;
    } else {
      var2 = android.graphics.Paint.Style.STROKE;
    }

    var3.setStyle(var2);
    var3 = this.mProgressBackgroundPaint;
    if(this.mStyle == 1) {
      var2 = android.graphics.Paint.Style.FILL;
    } else {
      var2 = android.graphics.Paint.Style.STROKE;
    }

    var3.setStyle(var2);
    this.invalidate();
  }

  private static enum ShaderMode {
    LINEAR,
    RADIAL,
    SWEEP;

    private ShaderMode() {
    }
  }

  private static enum Style {
    LINE,
    SOLID,
    SOLID_LINE;

    private Style() {
    }
  }
}
