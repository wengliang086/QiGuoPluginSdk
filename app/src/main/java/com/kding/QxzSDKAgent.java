package com.kding;

import android.content.Context;
import com.kding.service.FloatViewHelper;

public enum QxzSDKAgent
{
  INSTANCE;

  private FloatViewHelper mFloatViewHelper;

  private QxzSDKAgent() {}

  public void destroy()
  {
    try
    {
      hideFloatingView();
      this.mFloatViewHelper.destroyFloat();
      return;
    }
    catch (Exception localException) {}
  }

  public void hideFloatingView()
  {
    if (this.mFloatViewHelper != null) {
      this.mFloatViewHelper.hideFloat();
    }
  }

  public void initFloatView(Context paramContext)
  {
    try
    {
      FloatViewHelper.init(paramContext);
      this.mFloatViewHelper = FloatViewHelper.getInstance();
      return;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void showFloatingView()
  {
    if (this.mFloatViewHelper != null) {
      this.mFloatViewHelper.showFloat();
    }
  }
}