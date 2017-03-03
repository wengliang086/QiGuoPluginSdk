//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin;

import android.app.Application;
import android.app.Instrumentation;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Looper;
import com.kding.core.plugin.PluginActivityLifeCycleCallback;
import com.kding.core.plugin.delegate.DelegateActivityThread;
import com.kding.core.plugin.environment.CreateActivityData;
import com.kding.core.plugin.environment.PlugInfo;
import com.kding.core.plugin.environment.PluginClassLoader;
import com.kding.core.plugin.environment.PluginContext;
import com.kding.core.plugin.environment.PluginInstrumentation;
import com.kding.core.plugin.selector.DefaultActivitySelector;
import com.kding.core.plugin.selector.DynamicActivitySelector;
import com.kding.core.plugin.utils.FileUtil;
import com.kding.core.plugin.utils.LogUtils;
import com.kding.core.plugin.utils.PluginManifestUtil;
import com.kding.core.plugin.verify.PluginNotFoundException;
import com.kding.core.plugin.verify.PluginOverdueVerifier;
import com.kding.core.plugin.verify.SimpleLengthVerifier;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PluginManager implements FileFilter {
    private static PluginManager SINGLETON;
    private final Map<String, PlugInfo> pluginPkgToInfoMap = new ConcurrentHashMap();
    private Context context;
    private String dexOutputPath;
    private File dexInternalStoragePath;
    private ClassLoader pluginParentClassLoader = ClassLoader.getSystemClassLoader().getParent();
    private PluginActivityLifeCycleCallback pluginActivityLifeCycleCallback;
    private PluginOverdueVerifier pluginOverdueVerifier = new SimpleLengthVerifier();
    private DynamicActivitySelector activitySelector = DefaultActivitySelector.getDefault();
    public static final String SDK_NAME = "qiguo_sdk.apk";

    private PluginManager(Context context) {
        if(!this.isMainThread()) {
            throw new IllegalThreadStateException("PluginManager must init in UI Thread!");
        } else {
            this.context = context;
            File optimizedDexPath = context.getDir("plugins-file", 0);
            this.dexOutputPath = optimizedDexPath.getAbsolutePath();
            this.dexInternalStoragePath = context.getDir("plugins-opt", 0);
            DelegateActivityThread delegateActivityThread = DelegateActivityThread.getSingleton();
            Instrumentation originInstrumentation = delegateActivityThread.getInstrumentation();
            if(!(originInstrumentation instanceof PluginInstrumentation)) {
                PluginInstrumentation pluginInstrumentation = new PluginInstrumentation(originInstrumentation);
                delegateActivityThread.setInstrumentation(pluginInstrumentation);
            }

        }
    }

    public static PluginManager getSingleton() {
        checkInit();
        return SINGLETON;
    }

    private static void checkInit() {
        if(SINGLETON == null) {
            throw new IllegalStateException("Please init the PluginManager first!");
        }
    }

    public static void init(Context context) {
        if(SINGLETON != null) {
            LogUtils.d("PluginManager have been initialized, YOU needn\'t initialize it again!");
        } else {
            LogUtils.d("init PluginManager...");
            SINGLETON = new PluginManager(context);
        }
    }

    public PlugInfo tryGetPluginInfo(String plugPkg) throws PluginNotFoundException {
        PlugInfo plug = this.findPluginByPackageName(plugPkg);
        if(plug == null) {
            throw new PluginNotFoundException("plug not found by:" + plugPkg);
        } else {
            return plug;
        }
    }

    public File getPluginBasePath(PlugInfo plugInfo) {
        return new File(this.getDexInternalStoragePath(), plugInfo.getId() + "-dir");
    }

    public File getPluginLibPath(PlugInfo plugInfo) {
        return new File(this.getDexInternalStoragePath(), plugInfo.getId() + "-dir/lib/");
    }

    public PlugInfo findPluginByPackageName(String packageName) {
        return (PlugInfo)this.pluginPkgToInfoMap.get(packageName);
    }

    public Collection<PlugInfo> getPlugins() {
        return this.pluginPkgToInfoMap.values();
    }

    public void uninstallPluginByPkg(String pkg) {
        this.removePlugByPkg(pkg);
    }

    public void deletePlugin() {
        File sdkFile = new File(this.dexInternalStoragePath, "qiguo_sdk.apk");
        if(sdkFile.exists()) {
            sdkFile.delete();
        }

    }

    private PlugInfo removePlugByPkg(String pkg) {
        synchronized(this) {
            PlugInfo pl = (PlugInfo)this.pluginPkgToInfoMap.remove(pkg);
            return pl == null?null:pl;
        }
    }

    public PlugInfo loadSdk(Context context) throws Exception {
        File sdkFile = new File(this.dexInternalStoragePath, "qiguo_sdk.apk");
        if(!sdkFile.exists()) {
            LogUtils.e("load_plugin", "plugin uninstall start install");
            this.copyApkToPrivatePathFromAssets(context, "app.apk", "qiguo_sdk.apk");
            this.setSdkVersion(100);
        } else {
            LogUtils.e("load_plugin", "plugin install start init");
        }

        PlugInfo one = this.buildSdkPlugin(sdkFile, (String)null);
        if(one != null) {
            this.savePluginToMap(one);
        }

        return one;
    }

    private PlugInfo buildSdkPlugin(File pluginApk, String pluginId) throws Exception {
        PlugInfo info = new PlugInfo();
        info.setId(pluginId == null?pluginApk.getName():pluginId);
        File privateFile = new File(this.dexInternalStoragePath, "qiguo_sdk.apk");
        info.setFilePath(privateFile.getAbsolutePath());
        String dexPath = privateFile.getAbsolutePath();
        return this.buildPlugInfo(dexPath, info);
    }

    /** @deprecated */
    @Deprecated
    public Collection<PlugInfo> loadPlugin(File pluginSrcDirFile) throws Exception {
        if(pluginSrcDirFile != null && pluginSrcDirFile.exists()) {
            if(pluginSrcDirFile.isFile()) {
                PlugInfo var9 = this.buildPlugInfoFromSdCard(pluginSrcDirFile, (String)null, (String)null);
                if(var9 != null) {
                    this.savePluginToMap(var9);
                }

                return Collections.singletonList(var9);
            } else {
                File[] pluginApkFiles = pluginSrcDirFile.listFiles(this);
                if(pluginApkFiles != null && pluginApkFiles.length != 0) {
                    File[] var3 = pluginApkFiles;
                    int var4 = pluginApkFiles.length;

                    for(int var5 = 0; var5 < var4; ++var5) {
                        File pluginApk = var3[var5];

                        try {
                            PlugInfo e = this.buildPlugInfoFromSdCard(pluginApk, (String)null, (String)null);
                            if(e != null) {
                                this.savePluginToMap(e);
                            }
                        } catch (Throwable var8) {
                            var8.printStackTrace();
                        }
                    }

                    return this.pluginPkgToInfoMap.values();
                } else {
                    throw new FileNotFoundException("could not find plugins in:" + pluginSrcDirFile);
                }
            }
        } else {
            LogUtils.d("invalidate plugin file or Directory :" + pluginSrcDirFile);
            return null;
        }
    }

    private synchronized void savePluginToMap(PlugInfo plugInfo) {
        this.pluginPkgToInfoMap.put(plugInfo.getPackageName(), plugInfo);
    }

    /** @deprecated */
    @Deprecated
    private PlugInfo buildPlugInfoFromSdCard(File pluginApk, String pluginId, String targetFileName) throws Exception {
        PlugInfo info = new PlugInfo();
        info.setId(pluginId == null?pluginApk.getName():pluginId);
        File privateFile = new File(this.dexInternalStoragePath, targetFileName == null?pluginApk.getName():targetFileName);
        info.setFilePath(privateFile.getAbsolutePath());
        if(!pluginApk.getAbsolutePath().equals(privateFile.getAbsolutePath())) {
            this.copyApkToPrivatePath(pluginApk, privateFile);
        }

        String dexPath = privateFile.getAbsolutePath();
        return this.buildPlugInfo(dexPath, info);
    }

    private PlugInfo buildPlugInfo(String dexPath, PlugInfo info) throws Exception {
        PluginManifestUtil.setManifestInfo(this.context, dexPath, info);

        try {
            AssetManager pluginClassLoader = (AssetManager)AssetManager.class.newInstance();
            pluginClassLoader.getClass().getMethod("addAssetPath", new Class[]{String.class}).invoke(pluginClassLoader, new Object[]{dexPath});
            info.setAssetManager(pluginClassLoader);
            Resources appInfo = this.context.getResources();
            Resources app = new Resources(pluginClassLoader, appInfo.getDisplayMetrics(), appInfo.getConfiguration());
            info.setResources(app);
        } catch (Exception var6) {
            throw new RuntimeException("Unable to create Resources&Assets for " + info.getPackageName() + " : " + var6.getMessage());
        }

        PluginClassLoader pluginClassLoader1 = new PluginClassLoader(info, dexPath, this.dexOutputPath, this.getPluginLibPath(info).getAbsolutePath(), this.getClass().getClassLoader());
        info.setClassLoader(pluginClassLoader1);
        ApplicationInfo appInfo1 = info.getPackageInfo().applicationInfo;
        Application app1 = this.makeApplication(info, appInfo1);
        this.attachBaseContext(info, app1);
        info.setApplication(app1);
        LogUtils.d("Build pluginInfo => " + info);
        return info;
    }

    private void attachBaseContext(PlugInfo info, Application app) {
        try {
            Field e = ContextWrapper.class.getDeclaredField("mBase");
            e.setAccessible(true);
            e.set(app, new PluginContext(this.context.getApplicationContext(), info));
        } catch (Throwable var4) {
            var4.printStackTrace();
        }

    }

    public void setPluginParentClassLoader(ClassLoader parentClassLoader) {
        if(parentClassLoader != null) {
            this.pluginParentClassLoader = parentClassLoader;
        } else {
            this.pluginParentClassLoader = ClassLoader.getSystemClassLoader().getParent();
        }

    }

    public ClassLoader getPluginParentClassLoader() {
        return this.pluginParentClassLoader;
    }

    private Application makeApplication(PlugInfo plugInfo, ApplicationInfo appInfo) {
        String appClassName = appInfo.className;
        if(appClassName == null) {
            appClassName = Application.class.getName();
        }

        try {
            return (Application)plugInfo.getClassLoader().loadClass(appClassName).newInstance();
        } catch (Throwable var5) {
            throw new RuntimeException("Unable to create Application for " + plugInfo.getPackageName() + ": " + var5.getMessage());
        }
    }

    private void copyApkToPrivatePathFromAssets(Context context, String pluginName, String targetFileName) {
        FileUtil.copyFileFromAssets(context, pluginName, this.dexInternalStoragePath, targetFileName);
    }

    private void copyApkToPrivatePath(File pluginApk, File targetPutApk) {
        if(this.pluginOverdueVerifier == null || !targetPutApk.exists() || !this.pluginOverdueVerifier.isOverdue(pluginApk, targetPutApk)) {
            FileUtil.copyFile(pluginApk, targetPutApk);
        }
    }

    File getDexInternalStoragePath() {
        return this.dexInternalStoragePath;
    }

    Context getContext() {
        return this.context;
    }

    public PluginActivityLifeCycleCallback getPluginActivityLifeCycleCallback() {
        return this.pluginActivityLifeCycleCallback;
    }

    public void setPluginActivityLifeCycleCallback(PluginActivityLifeCycleCallback pluginActivityLifeCycleCallback) {
        this.pluginActivityLifeCycleCallback = pluginActivityLifeCycleCallback;
    }

    public PluginOverdueVerifier getPluginOverdueVerifier() {
        return this.pluginOverdueVerifier;
    }

    public void setPluginOverdueVerifier(PluginOverdueVerifier pluginOverdueVerifier) {
        this.pluginOverdueVerifier = pluginOverdueVerifier;
    }

    public boolean accept(File pathname) {
        return !pathname.isDirectory() && pathname.getName().endsWith(".apk");
    }

    public boolean setSdkVersion(int versionCode) {
        try {
            File e = new File(this.dexInternalStoragePath, "Version");
            if(!e.exists()) {
                e.createNewFile();
            }

            BufferedWriter out = new BufferedWriter(new FileWriter(e));
            out.write("" + versionCode);
            out.close();
            return true;
        } catch (IOException var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public String getSdkVersion() {
        InputStreamReader read = null;

        String var4;
        try {
            File e = new File(this.dexInternalStoragePath, "Version");
            if(!e.isFile() || !e.exists()) {
                LogUtils.e("set_sdk_version", "set version fail");
                return "100";
            }

            read = new InputStreamReader(new FileInputStream(e));
            BufferedReader bufferedReader = new BufferedReader(read);
            var4 = bufferedReader.readLine();
        } catch (Exception var15) {
            LogUtils.e("set_sdk_version", "set version fail");
            var15.printStackTrace();
            return "100";
        } finally {
            if(read != null) {
                try {
                    read.close();
                } catch (IOException var14) {
                    var14.printStackTrace();
                }
            }

        }

        return var4;
    }

    public void startMainActivity(Context from, PlugInfo plugInfo, Intent intent) {
        if(this.pluginPkgToInfoMap.containsKey(plugInfo.getPackageName())) {
            ActivityInfo activityInfo = plugInfo.getMainActivity().activityInfo;
            if(activityInfo == null) {
                throw new ActivityNotFoundException("Cannot find Main Activity from plugin.");
            } else {
                this.startActivity(from, plugInfo, activityInfo, intent);
            }
        }
    }

    public void startMainActivity(Context from, PlugInfo plugInfo) {
        this.startMainActivity(from, plugInfo, (Intent)null);
    }

    public void startMainActivity(Context from, String pluginPkgName) throws PluginNotFoundException, ActivityNotFoundException {
        PlugInfo plugInfo = this.tryGetPluginInfo(pluginPkgName);
        this.startMainActivity(from, plugInfo);
    }

    public void startActivity(Context from, PlugInfo plugInfo, ActivityInfo activityInfo, Intent intent) {
        if(activityInfo == null) {
            throw new ActivityNotFoundException("Cannot find ActivityInfo from plugin, could you declare this Activity in plugin?");
        } else {
            if(intent == null) {
                intent = new Intent();
            }

            CreateActivityData createActivityData = new CreateActivityData(activityInfo.name, plugInfo.getPackageName());
            intent.setClass(from, this.activitySelector.selectDynamicActivity(activityInfo));
            intent.putExtra("flag_act_fp", createActivityData);
            from.startActivity(intent);
        }
    }

    public DynamicActivitySelector getActivitySelector() {
        return this.activitySelector;
    }

    public void setActivitySelector(DynamicActivitySelector activitySelector) {
        if(activitySelector == null) {
            activitySelector = DefaultActivitySelector.getDefault();
        }

        this.activitySelector = activitySelector;
    }

    public void startActivity(Context from, PlugInfo plugInfo, String targetActivity, Intent intent) {
        ActivityInfo activityInfo = plugInfo.findActivityByClassName(targetActivity);
        this.startActivity(from, plugInfo, activityInfo, intent);
    }

    public void startActivity(Context from, PlugInfo plugInfo, String targetActivity) {
        this.startActivity(from, (PlugInfo)plugInfo, (String)targetActivity, (Intent)null);
    }

    public void startActivity(Context from, String pluginPkgName, String targetActivity) throws PluginNotFoundException, ActivityNotFoundException {
        this.startActivity(from, (String)pluginPkgName, (String)targetActivity, (Intent)null);
    }

    public void startActivity(Context from, String pluginPkgName, String targetActivity, Intent intent) throws PluginNotFoundException, ActivityNotFoundException {
        PlugInfo plugInfo = this.tryGetPluginInfo(pluginPkgName);
        this.startActivity(from, plugInfo, targetActivity, intent);
    }

    public void dump() {
        LogUtils.d(this.pluginPkgToInfoMap.size() + " Plugins is loaded, " + Arrays.toString(this.pluginPkgToInfoMap.values().toArray()));
    }

    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
