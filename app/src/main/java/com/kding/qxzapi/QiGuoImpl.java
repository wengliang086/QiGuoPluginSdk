package com.kding.qxzapi;

import android.content.Context;
import android.util.Log;
import com.kding.QxzSDKAgent;
import com.kding.api.QiGuoCallBack;
import com.kding.qiguo.IQiGuo;
import com.kding.qiguo.OnStartActivityFromPlugin;
import com.kding.utils.ChannelUtil;

public class QiGuoImpl
  implements IQiGuo
{
  public void addCallback(QiGuoCallBack paramQiGuoCallBack)
  {
    QiGuoInfo.INSTANCE.setQiGuoCallBack(paramQiGuoCallBack);
  }
  
  public void addStartActivityListen(OnStartActivityFromPlugin paramOnStartActivityFromPlugin)
  {
    QiGuoInfo.INSTANCE.setOnStartActivityFromPlugin(paramOnStartActivityFromPlugin);
  }
  
  public String getUid()
  {
    return QiGuoInfo.INSTANCE.getUid();
  }
  
  public void hideFloatView()
  {
    QxzSDKAgent.INSTANCE.hideFloatingView();
  }
  
  public void init(Context paramContext, String paramString)
  {
    QiGuoInfo.INSTANCE.setAppId(paramString);
    QiGuoInfo.INSTANCE.setChannel(ChannelUtil.getChannel(paramContext));
    Log.e("init", "init-suc appId = " + paramString + " channel = " + ChannelUtil.getChannel(paramContext));
  }
  
  public void showFloatView()
  {
    QxzSDKAgent.INSTANCE.showFloatingView();
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\qxzapi\QiGuoImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */