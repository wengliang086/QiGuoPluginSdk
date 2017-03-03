package com.kding.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.kding.bean.login.UserEntity;

public final class SharePrefUtil
{
  public static final String PREF_AVATAR = "avatar.pref";
  public static final String PREF_CELLPHONE = "cellphone.pref";
  public static final String PREF_COIN = "coin.pref";
  public static final String PREF_FILENAME = "kding.pref";
  public static final String PREF_GENDER = "gender.pref";
  private static final String PREF_HEAD_SCULPTURE_KEY = "head_sculpture_key.pref";
  public static final String PREF_UID = "uid.pref";
  public static final String PREF_USERNAME = "username.pref";
  private static final String PREF_USER_BINDED = "user_binded.pref";
  private static SharePrefUtil sSharePrefUtil;
  private final Context mAppContext;
  private final SharedPreferences mSharePref;
  
  private SharePrefUtil(Context paramContext)
  {
    this.mAppContext = paramContext;
    this.mSharePref = this.mAppContext.getSharedPreferences("kding.pref", 0);
  }
  
  public static SharePrefUtil getInstance(Context paramContext)
  {
    if (sSharePrefUtil == null) {}
    try
    {
      if (sSharePrefUtil == null) {
        sSharePrefUtil = new SharePrefUtil(paramContext.getApplicationContext());
      }
      return sSharePrefUtil;
    }
    finally {}
  }
  
  public String getHeadSculptureKey()
  {
    return this.mSharePref.getString("head_sculpture_key.pref", String.valueOf(System.currentTimeMillis()));
  }
  
  public UserEntity getUserEntity()
  {
    String str = this.mSharePref.getString("uid.pref", "");
    if (TextUtils.isEmpty(str)) {
      return null;
    }
    UserEntity localUserEntity = new UserEntity();
    localUserEntity.setUid(str);
    localUserEntity.setUsername(this.mSharePref.getString("username.pref", ""));
    localUserEntity.setCellphone(this.mSharePref.getString("cellphone.pref", ""));
    localUserEntity.setCoin(this.mSharePref.getInt("coin.pref", 0));
    localUserEntity.setGender(this.mSharePref.getString("gender.pref", ""));
    localUserEntity.setAvatar(this.mSharePref.getString("avatar.pref", ""));
    return localUserEntity;
  }
  
  public boolean isUserBinded()
  {
    return this.mSharePref.getBoolean("user_binded.pref", false);
  }
  
  public boolean putUserBinded(boolean paramBoolean)
  {
    return this.mSharePref.edit().putBoolean("user_binded.pref", paramBoolean).commit();
  }
  
  public boolean putUserEntity(UserEntity paramUserEntity)
  {
    if (paramUserEntity == null) {
      return false;
    }
    return this.mSharePref.edit().putString("uid.pref", paramUserEntity.getUid()).putString("username.pref", paramUserEntity.getUsername()).putString("cellphone.pref", paramUserEntity.getCellphone()).putInt("coin.pref", paramUserEntity.getCoin()).putString("avatar.pref", paramUserEntity.getAvatar()).putString("gender.pref", paramUserEntity.getGender()).commit();
  }
  
  public boolean putUserId(String paramString)
  {
    return this.mSharePref.edit().putString("uid.pref", paramString).commit();
  }
  
  public void updateHeadSculptureKey()
  {
    this.mSharePref.edit().putString("head_sculpture_key.pref", String.valueOf(System.currentTimeMillis())).commit();
  }
}