//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.environment;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.kding.core.plugin.utils.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PlugInfo implements Serializable {
    private String id;
    private String filePath;
    private PackageInfo packageInfo;
    private Map<String, ResolveInfo> activities;
    private ResolveInfo mainActivity;
    private List<ResolveInfo> services;
    private List<ResolveInfo> receivers;
    private List<ResolveInfo> providers;
    private transient ClassLoader classLoader;
    private transient Application application;
    private transient AssetManager assetManager;
    private transient Resources resources;
    private transient boolean isApplicationOnCreated;

    public PlugInfo() {
    }

    public String getPackageName() {
        return this.packageInfo.packageName;
    }

    public ActivityInfo findActivityByClassNameFromPkg(String actName) {
        if (actName.startsWith(".")) {
            actName = this.getPackageName() + actName;
        }
        if (this.packageInfo.activities == null) {
            return null;
        } else {
            ActivityInfo[] var2 = this.packageInfo.activities;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                ActivityInfo act = var2[var4];
                if (act.name.equals(actName)) {
                    return act;
                }
            }

            return null;
        }
    }

    public ActivityInfo findActivityByClassName(String actName) {
        if (this.packageInfo.activities == null) {
            return null;
        } else {
            if (actName.startsWith(".")) {
                actName = this.getPackageName() + actName;
            }

            ResolveInfo act = (ResolveInfo) this.activities.get(actName);
            return act == null ? null : act.activityInfo;
        }
    }

    public ActivityInfo findActivityByAction(String action) {
        if (this.activities != null && !this.activities.isEmpty()) {
            Iterator var2 = this.activities.values().iterator();

            ResolveInfo act;
            do {
                if (!var2.hasNext()) {
                    return null;
                }

                act = (ResolveInfo) var2.next();
            } while (act.filter == null || !act.filter.hasAction(action));

            return act.activityInfo;
        } else {
            return null;
        }
    }

    public ActivityInfo findReceiverByClassName(String className) {
        if (this.packageInfo.receivers == null) {
            return null;
        } else {
            ActivityInfo[] var2 = this.packageInfo.receivers;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                ActivityInfo receiver = var2[var4];
                if (receiver.name.equals(className)) {
                    return receiver;
                }
            }

            return null;
        }
    }

    public ServiceInfo findServiceByClassName(String className) {
        if (this.packageInfo.services == null) {
            return null;
        } else {
            ServiceInfo[] var2 = this.packageInfo.services;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                ServiceInfo service = var2[var4];
                if (service.name.equals(className)) {
                    return service;
                }
            }

            return null;
        }
    }

    public ServiceInfo findServiceByAction(String action) {
        if (this.services != null && !this.services.isEmpty()) {
            Iterator var2 = this.services.iterator();

            ResolveInfo ser;
            do {
                if (!var2.hasNext()) {
                    return null;
                }
                ser = (ResolveInfo) var2.next();
            } while (ser.filter == null || !ser.filter.hasAction(action));

            return ser.serviceInfo;
        } else {
            return null;
        }
    }

    public void addActivity(ResolveInfo activity) {
        if (this.activities == null) {
            this.activities = new HashMap(15);
        }

        this.fixActivityInfo(activity.activityInfo);
        this.activities.put(activity.activityInfo.name, activity);
        if (this.mainActivity == null && activity.filter != null && activity.filter.hasAction("android.intent.action.MAIN") && activity.filter.hasCategory("android.intent.category.LAUNCHER")) {
            this.mainActivity = activity;
        }

    }

    private void fixActivityInfo(ActivityInfo activityInfo) {
        if (activityInfo != null && activityInfo.name.startsWith(".")) {
            activityInfo.name = this.getPackageName() + activityInfo.name;
        }

    }

    public void addReceiver(ResolveInfo receiver) {
        if (this.receivers == null) {
            this.receivers = new ArrayList();
        }

        this.receivers.add(receiver);
    }

    public void addService(ResolveInfo service) {
        if (this.services == null) {
            this.services = new ArrayList();
        }

        this.services.add(service);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public PackageInfo getPackageInfo() {
        return this.packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
        this.activities = new HashMap(packageInfo.activities.length);
    }

    public Application getApplication() {
        return this.application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public AssetManager getAssets() {
        return this.assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public Resources getResources() {
        return this.resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Collection<ResolveInfo> getActivities() {
        return this.activities == null ? null : this.activities.values();
    }

    public List<ResolveInfo> getServices() {
        return this.services;
    }

    public void setServices(List<ResolveInfo> services) {
        this.services = services;
    }

    public List<ResolveInfo> getProviders() {
        return this.providers;
    }

    public void setProviders(List<ResolveInfo> providers) {
        this.providers = providers;
    }

    public ResolveInfo getMainActivity() {
        return this.mainActivity;
    }

    public List<ResolveInfo> getReceivers() {
        return this.receivers;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public int hashCode() {
        boolean prime = true;
        byte result = 1;
        int result1 = 31 * result + (this.id == null ? 0 : this.id.hashCode());
        return result1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            PlugInfo other = (PlugInfo) obj;
            if (this.id == null) {
                if (other.id != null) {
                    return false;
                }
            } else if (!this.id.equals(other.id)) {
                return false;
            }

            return true;
        }
    }

    public boolean isApplicationCreated() {
        return this.application != null || this.isApplicationOnCreated;
    }

    public void ensureApplicationCreated() {
        if (this.isApplicationCreated()) {
            synchronized (this) {
                try {
                    this.application.onCreate();
                    if (this.receivers != null && this.receivers.size() > 0) {
                        Iterator ignored = this.receivers.iterator();

                        while (ignored.hasNext()) {
                            ResolveInfo resolveInfo = (ResolveInfo) ignored.next();
                            if (resolveInfo.activityInfo != null) {
                                try {
                                    BroadcastReceiver e = (BroadcastReceiver) this.classLoader.loadClass(resolveInfo.activityInfo.name).newInstance();
                                    this.application.registerReceiver(e, resolveInfo.filter);
                                } catch (Throwable var6) {
                                    var6.printStackTrace();
                                    LogUtils.e("Unable to create Receiver : " + resolveInfo.activityInfo.name);
                                }
                            }
                        }
                    }
                } catch (Throwable var7) {
                }
                this.isApplicationOnCreated = true;
            }
        }
    }

    public String toString() {
        return super.toString() + "[ id=" + this.id + ", pkg=" + this.getPackageName() + " ]";
    }
}
