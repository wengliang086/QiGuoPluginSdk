package com.kding.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.kding.GuideDownloadActivity;

public final class AppCallUtil
{
  private static final String APP_PACKAGE_NAME = "com.kding.gamecenter";
  private static final String ISSUE_ACTION = "com.kding.gamecenter.ISSUE_FOR_SDK";
  public static final int ISSUE_REQUEST_CODE = 48130;
  private static final String RECHARGE_ACTION = "com.kding.gamecenter.RECHARGE_FOR_SDK";
  public static final int RECHARGE_REQUEST_CODE = 48129;
  private static final String SDK_QID = "SDK_QID";
  private static final String SDK_UID = "SDK_UID";
  
  public static void goToAppIssue(Activity paramActivity, String paramString1, String paramString2)
  {
    if (!isAppInstalled(paramActivity, "com.kding.gamecenter"))
    {
      guideDownload(paramActivity);
      return;
    }
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("com.kding.gamecenter.ISSUE_FOR_SDK");
      localIntent.putExtra("SDK_UID", paramString1);
      localIntent.putExtra("SDK_QID", paramString2);
      paramActivity.startActivityForResult(localIntent, 48130);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      ToastUtils.showToast(paramActivity, "当前七果APP版本过低，请更新APP版本");
    }
  }
  
  public static void goToAppRecharge(Activity paramActivity, String paramString)
  {
    if (!isAppInstalled(paramActivity, "com.kding.gamecenter"))
    {
      guideDownload(paramActivity);
      return;
    }
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("com.kding.gamecenter.RECHARGE_FOR_SDK");
      localIntent.putExtra("SDK_UID", paramString);
      paramActivity.startActivityForResult(localIntent, 48129);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      ToastUtils.showToast(paramActivity, "当前七果APP版本过低，请更新APP版本");
    }
  }
  
  private static void guideDownload(Activity paramActivity)
  {
    paramActivity.startActivity(new Intent(paramActivity, GuideDownloadActivity.class));
    paramActivity.finish();
  }
  
  private static boolean isAppInstalled(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return true;
    }
    catch (NameNotFoundException localNameNotFoundException) {}
    return false;
  }
}