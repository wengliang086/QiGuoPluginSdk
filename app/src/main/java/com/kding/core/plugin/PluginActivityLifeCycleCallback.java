//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin;

import android.app.Activity;

public interface PluginActivityLifeCycleCallback {
  void onCreate(String var1, Activity var2);

  void onResume(String var1, Activity var2);

  void onPause(String var1, Activity var2);

  void onStart(String var1, Activity var2);

  void onRestart(String var1, Activity var2);

  void onStop(String var1, Activity var2);

  void onDestroy(String var1, Activity var2);
}
