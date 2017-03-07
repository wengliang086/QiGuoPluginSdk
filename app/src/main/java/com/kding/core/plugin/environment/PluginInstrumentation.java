//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.environment;

import android.app.Activity;
import android.app.Fragment;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Window;

import com.kding.core.plugin.PluginManager;
import com.kding.core.plugin.delegate.DelegateInstrumentation;
import com.kding.core.plugin.reflect.Reflect;
import com.kding.core.plugin.reflect.ReflectException;
import com.kding.core.plugin.utils.LogUtils;
import com.kding.core.plugin.verify.PluginNotFoundException;
import com.kding.core.plugin.widget.LayoutInflaterWrapper;

import java.lang.reflect.Field;

public class PluginInstrumentation extends DelegateInstrumentation {
    private PlugInfo currentPlugin;

    public PluginInstrumentation(Instrumentation mBase) {
        super(mBase);
    }

    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        CreateActivityData activityData = (CreateActivityData) intent.getSerializableExtra("flag_act_fp");
        if (activityData != null && PluginManager.getSingleton().getPlugins().size() > 0) {
            PlugInfo plugInfo;
            try {
                LogUtils.d(this.getClass().getSimpleName(), "+++ Start Plugin Activity => " + activityData.pluginPkg + " / " + activityData.activityName);
                plugInfo = PluginManager.getSingleton().tryGetPluginInfo(activityData.pluginPkg);
                plugInfo.ensureApplicationCreated();
            } catch (PluginNotFoundException var7) {
                PluginManager.getSingleton().dump();
                throw new IllegalAccessException("Cannot get plugin Info : " + activityData.pluginPkg);
            }

            if (activityData.activityName != null) {
                className = activityData.activityName;
                cl = plugInfo.getClassLoader();
            }
        }

        return super.newActivity(cl, className, intent);
    }

    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        this.lookupActivityInPlugin(activity);
        if (this.currentPlugin != null) {
            Context baseContext = activity.getBaseContext();
            PluginContext pluginContext = new PluginContext(baseContext, this.currentPlugin);

            try {
                try {
                    Reflect.on(activity).set("mResources", pluginContext.getResources());
                } catch (Throwable var12) {
                    ;
                }

                Field activityInfo = ContextWrapper.class.getDeclaredField("mBase");
                activityInfo.setAccessible(true);
                activityInfo.set(activity, pluginContext);

                try {
                    Reflect.on(activity).set("mApplication", this.currentPlugin.getApplication());
                } catch (ReflectException var11) {
                    LogUtils.e("Application not inject success into : " + activity);
                }
            } catch (Throwable var13) {
                var13.printStackTrace();
            }

            ActivityInfo activityInfo1 = this.currentPlugin.findActivityByClassName(activity.getClass().getName());
            if (activityInfo1 != null) {
                int window = activityInfo1.getThemeResource();
                if (window != 0) {
                    boolean windowRef = true;

                    try {
                        Field e = ContextThemeWrapper.class.getDeclaredField("mTheme");
                        e.setAccessible(true);
                        windowRef = e.get(activity) == null;
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }

                    if (windowRef) {
                        changeActivityInfo(activityInfo1, activity);
                        activity.setTheme(window);
                        activity.setFinishOnTouchOutside(true);
                    }
                }
            }

            if (Build.MODEL.startsWith("GT")) {
                Window window1 = activity.getWindow();
                Reflect windowRef1 = Reflect.on(window1);

                try {
                    LayoutInflater e1 = window1.getLayoutInflater();
                    if (!(e1 instanceof LayoutInflaterWrapper)) {
                        windowRef1.set("mLayoutInflater", new LayoutInflaterWrapper(e1));
                    }
                } catch (Throwable var9) {
                    var9.printStackTrace();
                }
            }
        }

        super.callActivityOnCreate(activity, icicle);
    }

    public void callActivityOnResume(Activity activity) {
        this.lookupActivityInPlugin(activity);
        super.callActivityOnResume(activity);
    }

    private static void changeActivityInfo(ActivityInfo activityInfo, Activity activity) {
        Field field_mActivityInfo;
        try {
            field_mActivityInfo = Activity.class.getDeclaredField("mActivityInfo");
            field_mActivityInfo.setAccessible(true);
        } catch (Exception var5) {
            var5.printStackTrace();
            return;
        }

        try {
            field_mActivityInfo.set(activity, activityInfo);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void callActivityOnDestroy(Activity activity) {
        super.callActivityOnDestroy(activity);
    }

    private void lookupActivityInPlugin(Activity activity) {
        ClassLoader classLoader = activity.getClass().getClassLoader();
        if (classLoader instanceof PluginClassLoader) {
            this.currentPlugin = ((PluginClassLoader) classLoader).getPlugInfo();
        } else {
            this.currentPlugin = null;
        }

    }

    private void replaceIntentTargetIfNeed(Context from, Intent intent) {
        if (!intent.hasExtra("flag_act_fp") && this.currentPlugin != null) {
            ComponentName componentName = intent.getComponent();
            if (componentName != null) {
                String pkgName = componentName.getPackageName();
                String activityName = componentName.getClassName();
                if (pkgName != null) {
                    CreateActivityData createActivityData = new CreateActivityData(activityName, this.currentPlugin.getPackageName());
                    ActivityInfo activityInfo = this.currentPlugin.findActivityByClassName(activityName);
                    if (activityInfo != null) {
                        intent.setClass(from, PluginManager.getSingleton().getActivitySelector().selectDynamicActivity(activityInfo));
                        intent.putExtra("flag_act_fp", createActivityData);
                        intent.setExtrasClassLoader(this.currentPlugin.getClassLoader());
                    }
                }
            }
        }

    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Fragment fragment, Intent intent, int requestCode) {
        this.replaceIntentTargetIfNeed(who, intent);
        return super.execStartActivity(who, contextThread, token, fragment, intent, requestCode);
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Fragment fragment, Intent intent, int requestCode, Bundle options) {
        this.replaceIntentTargetIfNeed(who, intent);
        return super.execStartActivity(who, contextThread, token, fragment, intent, requestCode, options);
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode) {
        this.replaceIntentTargetIfNeed(who, intent);
        return super.execStartActivity(who, contextThread, token, target, intent, requestCode);
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode, Bundle options) {
        this.replaceIntentTargetIfNeed(who, intent);
        return super.execStartActivity(who, contextThread, token, target, intent, requestCode, options);
    }
}
