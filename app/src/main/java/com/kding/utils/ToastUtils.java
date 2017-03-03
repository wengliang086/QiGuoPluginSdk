//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.utils;

import android.content.Context;
import android.widget.Toast;
import com.kding.qxzapi.QiGuoInfo;

public class ToastUtils {
  private static Toast toast = null;

  public ToastUtils() {
  }

  public static void showToast(Context var0, int var1) {
    if(QiGuoInfo.INSTANCE.isShowToast()) {
      if(toast == null) {
        toast = Toast.makeText(var0, var1, 0);
      } else {
        toast.setText(var1);
      }

      toast.show();
    }
  }

  public static void showToast(Context var0, String var1) {
    if(QiGuoInfo.INSTANCE.isShowToast()) {
      if(toast == null) {
        toast = Toast.makeText(var0, var1, 0);
      } else {
        toast.setText(var1);
      }

      toast.show();
    }
  }
}
