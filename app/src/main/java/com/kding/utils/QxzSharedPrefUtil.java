package com.kding.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

@TargetApi(11)
public class QxzSharedPrefUtil
{
  public static final String AGREEMENT = "agreement";
  public static final String AUTOLOGIN = "autologin";
  public static final String GSON_LISTH_HISTORY = "gson_list";
  public static final String LAST_LOGIN_NAME = "lastloginname";
  public static final String LAST_LOGIN_PASSWD = "lastloginpasswd";
  public static final String LAST_OPEN_HOME_TIME = "qxz_last_opentime";
  public static final String SHOULD_SHOW_FLOAT_VIEW = "shouldShowFloatView";
  
  public static Boolean getBoolean(Context paramContext, String paramString, Boolean paramBoolean)
  {
    return Boolean.valueOf(paramContext.getSharedPreferences("kd", 4).getBoolean(paramString, paramBoolean.booleanValue()));
  }
  
  public static String getIdentifyCode(Context paramContext)
  {
    String str = getString(paramContext, "identifyCode", "");
    if ((TextUtils.isEmpty(str)) || ("m8wg5al5O0KAYUSfnvlofg%3D%3D".equals(str))) {
      return "";
    }
    return EncryptHelp.decryptResponse(str);
  }
  
  public static int getInt(Context paramContext, String paramString, int paramInt)
  {
    return paramContext.getSharedPreferences("kd", 4).getInt(paramString, paramInt);
  }
  
  public static long getLong(Context paramContext, String paramString, long paramLong)
  {
    return paramContext.getSharedPreferences("kd", 4).getLong(paramString, paramLong);
  }
  
  public static String getPassword(Context paramContext)
  {
    String str = getString(paramContext, "loginPassword", "");
    if ((TextUtils.isEmpty(str)) || ("m8wg5al5O0KAYUSfnvlofg%3D%3D".equals(str))) {
      return "";
    }
    return EncryptHelp.decryptResponse(str);
  }
  
  public static String getString(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences("kd", 4).getString(paramString1, paramString2);
  }
  
  public static void putBoolean(Context paramContext, String paramString, Boolean paramBoolean)
  {
    Editor localEditor = paramContext.getSharedPreferences("kd", 4).edit();
    localEditor.putBoolean(paramString, paramBoolean.booleanValue());
    localEditor.commit();
  }
  
  public static void putIdentifyCode(Context paramContext, String paramString)
  {
    putString(paramContext, "identifyCode", EncryptHelp.encryptRequestData(paramString));
  }
  
  public static void putInt(Context paramContext, String paramString, int paramInt)
  {
    Editor localEditor = paramContext.getSharedPreferences("kd", 4).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }
  
  public static void putLong(Context paramContext, String paramString, long paramLong)
  {
    Editor localEditor = paramContext.getSharedPreferences("kd", 4).edit();
    localEditor.putLong(paramString, paramLong);
    localEditor.commit();
  }
  
  public static void putPassword(Context paramContext, String paramString)
  {
    putString(paramContext, "loginPassword", EncryptHelp.encryptRequestData(paramString));
  }
  
  public static void putString(Context paramContext, String paramString1, String paramString2)
  {
    Editor localEditor = paramContext.getSharedPreferences("kd", 4).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
  
  public static void remove(Context paramContext, String paramString)
  {
    Editor localEditor = paramContext.getSharedPreferences("kd", 4).edit();
    localEditor.remove(paramString);
    localEditor.commit();
  }
}