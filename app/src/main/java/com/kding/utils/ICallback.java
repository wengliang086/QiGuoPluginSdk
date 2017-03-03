package com.kding.utils;

public abstract interface ICallback<T>
{
  public abstract void onError(Exception paramException);
  
  public abstract void onFailure(String paramString);
  
  public abstract void onResponse(T paramT);
}