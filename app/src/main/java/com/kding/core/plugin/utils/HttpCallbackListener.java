package com.kding.core.plugin.utils;

public abstract interface HttpCallbackListener
{
  public abstract void onFinish(String paramString);
  
  public abstract void onError(Exception paramException);
}