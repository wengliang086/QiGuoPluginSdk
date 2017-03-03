//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class QxzInfo {
  private static int CurrNetWorkType = 0;
  private static final int NETWORKTYPE_2G = 2;
  private static final int NETWORKTYPE_3G = 3;
  private static final int NETWORKTYPE_INVALID = 0;
  private static final int NETWORKTYPE_WAP = 1;
  private static final int NETWORKTYPE_WIFI = 4;

  public QxzInfo() {
  }

  public static String getDpl(Context var0) {
    DisplayMetrics var1 = new DisplayMetrics();
    ((WindowManager)var0.getSystemService("window")).getDefaultDisplay().getMetrics(var1);
    return var1.widthPixels + "*" + var1.heightPixels;
  }

  public static int getNetWorkType(Context var0) {
    NetworkInfo var2 = ((ConnectivityManager)var0.getSystemService("connectivity")).getActiveNetworkInfo();
    if(var2 != null && var2.isAvailable()) {
      switch(var2.getType()) {
        case 0:
          byte var1;
          if(TextUtils.isEmpty(Proxy.getDefaultHost())) {
            if(isFastMobileNetwork(var0)) {
              var1 = 3;
            } else {
              var1 = 2;
            }
          } else {
            var1 = 1;
          }

          CurrNetWorkType = var1;
          break;
        case 1:
          CurrNetWorkType = 4;
          break;
        default:
          CurrNetWorkType = 0;
      }
    } else {
      CurrNetWorkType = 0;
    }

    return CurrNetWorkType;
  }

  public static String getOperators(Context var0) {
    TelephonyManager var1 = (TelephonyManager)var0.getSystemService("phone");
    String var2 = null;
    String var3 = var1.getSubscriberId();
    if(var3 != null && !var3.equals("")) {
      if(!var3.startsWith("46000") && !var3.startsWith("46002")) {
        if(var3.startsWith("46001")) {
          var2 = "中国联通";
        } else if(var3.startsWith("46003")) {
          var2 = "中国电信";
        }
      } else {
        var2 = "中国移动";
      }

      return var2;
    } else {
      return null;
    }
  }

  public static int getSDKVersionNumber() {
    try {
      int var0 = Integer.valueOf(VERSION.SDK).intValue();
      return var0;
    } catch (NumberFormatException var2) {
      return 0;
    }
  }

  public static boolean isFastMobileNetwork(Context var0) {
    int var1 = ((TelephonyManager)var0.getSystemService("phone")).getNetworkType();
    if(getSDKVersionNumber() <= 11) {
      switch(var1) {
        case 0:
        case 1:
        case 2:
        case 4:
        case 7:
        case 11:
        default:
          break;
        case 3:
          return true;
        case 5:
          return true;
        case 6:
          return true;
        case 8:
          return true;
        case 9:
          return true;
        case 10:
          return true;
      }
    } else {
      switch(var1) {
        case 0:
        case 1:
        case 2:
        case 4:
        case 7:
        case 11:
          break;
        case 3:
          return true;
        case 5:
          return true;
        case 6:
          return true;
        case 8:
          return true;
        case 9:
          return true;
        case 10:
          return true;
        case 12:
          return true;
        case 13:
          return true;
        case 14:
          return true;
        case 15:
          return true;
        default:
          return false;
      }
    }

    return false;
  }
}
