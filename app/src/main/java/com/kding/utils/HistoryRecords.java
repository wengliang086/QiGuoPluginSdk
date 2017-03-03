//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.utils;

import android.content.Context;
import android.util.Pair;
import com.kding.utils.QxzSharedPrefUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class HistoryRecords {
  public HistoryRecords() {
  }

  private static List<Pair<String, String>> add(String var0, String var1) {
    ArrayList var2 = new ArrayList();
    var2.add(new Pair(var0, var1));
    return var2;
  }

  public static void add(Context var0, String var1, String var2) {
    String var3 = QxzSharedPrefUtil.getString(var0, "gson_list", "");
    if(var3 != null && var3.length() != 0) {
      if(!isRecordExist(var3, var1)) {
        List var4 = (List)(new Gson()).fromJson(var3, (new TypeToken() {
        }).getType());
        var4.addAll(add(var1, var2));
        save(var0, var4);
        return;
      }
    } else {
      save(var0, add(var1, var2));
    }

  }

  public static List<Pair<String, String>> getAllRecords(Context var0) {
    Gson var1 = new Gson();
    String var2 = QxzSharedPrefUtil.getString(var0, "gson_list", "");
    return var2 != null && var2.length() != 0?(List)var1.fromJson(var2, (new TypeToken() {
    }).getType()):null;
  }

  private static boolean isRecordExist(String var0, String var1) {
    return var0.contains(var1);
  }

  public static void remove(List<Pair<String, String>> var0, int var1) {
    if(var0 != null && var0.size() != 0) {
      var0.remove(var1);
    }
  }

  public static void save(Context var0, List<Pair<String, String>> var1) {
    QxzSharedPrefUtil.putString(var0, "gson_list", (new Gson()).toJson(var1));
  }
}
