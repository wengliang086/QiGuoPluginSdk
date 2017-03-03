package com.kding.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

public class SystemUtils
{
  public static boolean isLandscape(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 2;
  }
}