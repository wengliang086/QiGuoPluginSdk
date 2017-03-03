package com.kding.qxzapi;

import com.kding.api.QiGuoCallBack;
import com.kding.qiguo.OnStartActivityFromPlugin;

public enum QiGuoInfo
{
  INSTANCE;

  private String appId;
  private String channel;
  private boolean mEnable = false;
  private QiGuoCallBack mQiGuoCallBack;
  private OnStartActivityFromPlugin onStartActivityFromPlugin;
  private boolean showToast = true;
  private String uid;

  private QiGuoInfo() {}

  public String getAppId()
  {
    return this.appId;
  }

  public String getBaseUrl()
  {
    if (this.mEnable) {
      return "http://sdk2.7xz.com/";
    }
    return "http://sdk2.7xz.com/";
  }

  public String getChannel()
  {
    return this.channel;
  }

  public OnStartActivityFromPlugin getOnStartActivityFromPlugin()
  {
    return this.onStartActivityFromPlugin;
  }

  public QiGuoCallBack getQiGuoCallBack()
  {
    return this.mQiGuoCallBack;
  }

  public String getUid()
  {
    return this.uid;
  }

  public boolean isShowToast()
  {
    return this.showToast;
  }

  public void setAppId(String paramString)
  {
    this.appId = paramString;
  }

  public void setChannel(String paramString)
  {
    this.channel = paramString;
  }

  public void setOnStartActivityFromPlugin(OnStartActivityFromPlugin paramOnStartActivityFromPlugin)
  {
    this.onStartActivityFromPlugin = paramOnStartActivityFromPlugin;
  }

  public void setQiGuoCallBack(QiGuoCallBack paramQiGuoCallBack)
  {
    this.mQiGuoCallBack = paramQiGuoCallBack;
  }

  public void setShowToast(boolean paramBoolean)
  {
    this.showToast = paramBoolean;
  }

  public void setUid(String paramString)
  {
    this.uid = paramString;
  }
}
