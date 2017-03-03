package com.kding.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class DensityUtil
{
  private DensityUtil()
  {
    throw new UnsupportedOperationException("cannot be instantiated");
  }
  
  public static int dp2px(Context paramContext, float paramFloat)
  {
    return (int)TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics());
  }
  
  public static float px2dp(Context paramContext, float paramFloat)
  {
    return paramFloat / paramContext.getResources().getDisplayMetrics().density;
  }
  
  public static float px2sp(Context paramContext, float paramFloat)
  {
    return paramFloat / paramContext.getResources().getDisplayMetrics().scaledDensity;
  }
  
  public static int sp2px(Context paramContext, float paramFloat)
  {
    return (int)TypedValue.applyDimension(2, paramFloat, paramContext.getResources().getDisplayMetrics());
  }
}