package com.kding.utils;

import android.content.ClipboardManager;
import android.content.Context;

public class CopyUtil
{
  public static void copy(String paramString, Context paramContext)
  {
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setText(paramString.trim());
  }
}