package com.kding.pay;

import com.kding.bean.PayResult;

public abstract interface PayCallBack
{
  public abstract void onError(String paramString);

  public abstract void onFail(String paramString);

  public abstract void onSuc(PayResult paramPayResult);
}