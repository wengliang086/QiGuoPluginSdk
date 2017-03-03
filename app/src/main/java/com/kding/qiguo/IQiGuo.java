package com.kding.qiguo;

import android.content.Context;
import com.kding.api.QiGuoCallBack;

public abstract interface IQiGuo
{
  public abstract void addCallback(QiGuoCallBack paramQiGuoCallBack);
  
  public abstract void addStartActivityListen(OnStartActivityFromPlugin paramOnStartActivityFromPlugin);
  
  public abstract String getUid();
  
  public abstract void init(Context paramContext, String paramString);
  
  public abstract void showFloatView();
  
  public abstract void hideFloatView();
}


/* Location:              C:\Users\Administrator\Desktop\QiguoSdk.jar!\com\kding\qiguo\IQiGuo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */