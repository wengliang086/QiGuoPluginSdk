package com.kding.core.plugin.selector;

import android.app.Activity;
import android.content.pm.ActivityInfo;

public abstract interface DynamicActivitySelector
{
  public abstract Class<? extends Activity> selectDynamicActivity(ActivityInfo paramActivityInfo);
}


/* Location:              C:\Users\Administrator\Desktop\QiguoSdk.jar!\com\kding\core\plugin\selector\DynamicActivitySelector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */