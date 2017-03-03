/*     */ package com.kding.core.plugin.environment;
/*     */ 
/*     */ import android.app.Application;
/*     */ import android.content.BroadcastReceiver;
/*     */ import android.content.IntentFilter;
/*     */ import android.content.pm.ActivityInfo;
/*     */ import android.content.pm.PackageInfo;
/*     */ import android.content.pm.ResolveInfo;
/*     */ import android.content.pm.ServiceInfo;
/*     */ import android.content.res.AssetManager;
/*     */ import android.content.res.Resources;
/*     */ import com.kding.core.plugin.utils.LogUtils;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlugInfo
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String filePath;
/*     */   private PackageInfo packageInfo;
/*     */   private Map<String, ResolveInfo> activities;
/*     */   private ResolveInfo mainActivity;
/*     */   private List<ResolveInfo> services;
/*     */   private List<ResolveInfo> receivers;
/*     */   private List<ResolveInfo> providers;
/*     */   private transient ClassLoader classLoader;
/*     */   private transient Application application;
/*     */   private transient AssetManager assetManager;
/*     */   private transient Resources resources;
/*     */   private transient boolean isApplicationOnCreated;
/*     */   
/*     */   public String getPackageName()
/*     */   {
/*  65 */     return this.packageInfo.packageName;
/*     */   }
/*     */   
/*     */   public ActivityInfo findActivityByClassNameFromPkg(String actName)
/*     */   {
/*  70 */     if (actName.startsWith(".")) {
/*  71 */       actName = getPackageName() + actName;
/*     */     }
/*  73 */     if (this.packageInfo.activities == null) {
/*  74 */       return null;
/*     */     }
/*  76 */     for (ActivityInfo act : this.packageInfo.activities) {
/*  77 */       if (act.name.equals(actName)) {
/*  78 */         return act;
/*     */       }
/*     */     }
/*  81 */     return null;
/*     */   }
/*     */   
/*  84 */   public ActivityInfo findActivityByClassName(String actName) { if (this.packageInfo.activities == null) {
/*  85 */       return null;
/*     */     }
/*  87 */     if (actName.startsWith(".")) {
/*  88 */       actName = getPackageName() + actName;
/*     */     }
/*  90 */     ResolveInfo act = (ResolveInfo)this.activities.get(actName);
/*  91 */     if (act == null) {
/*  92 */       return null;
/*     */     }
/*  94 */     return act.activityInfo;
/*     */   }
/*     */   
/*     */   public ActivityInfo findActivityByAction(String action) {
/*  98 */     if ((this.activities == null) || (this.activities.isEmpty())) {
/*  99 */       return null;
/*     */     }
/*     */     
/* 102 */     for (ResolveInfo act : this.activities.values()) {
/* 103 */       if ((act.filter != null) && (act.filter.hasAction(action))) {
/* 104 */         return act.activityInfo;
/*     */       }
/*     */     }
/* 107 */     return null;
/*     */   }
/*     */   
/*     */   public ActivityInfo findReceiverByClassName(String className) {
/* 111 */     if (this.packageInfo.receivers == null) {
/* 112 */       return null;
/*     */     }
/* 114 */     for (ActivityInfo receiver : this.packageInfo.receivers) {
/* 115 */       if (receiver.name.equals(className)) {
/* 116 */         return receiver;
/*     */       }
/*     */     }
/* 119 */     return null;
/*     */   }
/*     */   
/*     */   public ServiceInfo findServiceByClassName(String className) {
/* 123 */     if (this.packageInfo.services == null) {
/* 124 */       return null;
/*     */     }
/* 126 */     for (ServiceInfo service : this.packageInfo.services) {
/* 127 */       if (service.name.equals(className)) {
/* 128 */         return service;
/*     */       }
/*     */     }
/* 131 */     return null;
/*     */   }
/*     */   
/*     */   public ServiceInfo findServiceByAction(String action) {
/* 135 */     if ((this.services == null) || (this.services.isEmpty())) {
/* 136 */       return null;
/*     */     }
/* 138 */     for (ResolveInfo ser : this.services) {
/* 139 */       if ((ser.filter != null) && (ser.filter.hasAction(action))) {
/* 140 */         return ser.serviceInfo;
/*     */       }
/*     */     }
/* 143 */     return null;
/*     */   }
/*     */   
/* 146 */   public void addActivity(ResolveInfo activity) { if (this.activities == null) {
/* 147 */       this.activities = new HashMap(15);
/*     */     }
/* 149 */     fixActivityInfo(activity.activityInfo);
/* 150 */     this.activities.put(activity.activityInfo.name, activity);
/* 151 */     if ((this.mainActivity == null) && (activity.filter != null) && 
/* 152 */       (activity.filter.hasAction("android.intent.action.MAIN")) && 
/* 153 */       (activity.filter.hasCategory("android.intent.category.LAUNCHER")))
/*     */     {
/* 155 */       this.mainActivity = activity;
/*     */     }
/*     */   }
/*     */   
/*     */   private void fixActivityInfo(ActivityInfo activityInfo) {
/* 160 */     if ((activityInfo != null) && 
/* 161 */       (activityInfo.name.startsWith("."))) {
/* 162 */       activityInfo.name = (getPackageName() + activityInfo.name);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addReceiver(ResolveInfo receiver)
/*     */   {
/* 168 */     if (this.receivers == null) {
/* 169 */       this.receivers = new ArrayList();
/*     */     }
/* 171 */     this.receivers.add(receiver);
/*     */   }
/*     */   
/*     */   public void addService(ResolveInfo service) {
/* 175 */     if (this.services == null) {
/* 176 */       this.services = new ArrayList();
/*     */     }
/* 178 */     this.services.add(service);
/*     */   }
/*     */   
/*     */   public String getId() {
/* 182 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/* 186 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getFilePath() {
/* 190 */     return this.filePath;
/*     */   }
/*     */   
/*     */   public void setFilePath(String filePath) {
/* 194 */     this.filePath = filePath;
/*     */   }
/*     */   
/*     */   public PackageInfo getPackageInfo() {
/* 198 */     return this.packageInfo;
/*     */   }
/*     */   
/*     */   public void setPackageInfo(PackageInfo packageInfo) {
/* 202 */     this.packageInfo = packageInfo;
/* 203 */     this.activities = new HashMap(packageInfo.activities.length);
/*     */   }
/*     */   
/*     */   public Application getApplication() {
/* 207 */     return this.application;
/*     */   }
/*     */   
/*     */   public void setApplication(Application application) {
/* 211 */     this.application = application;
/*     */   }
/*     */   
/*     */   public AssetManager getAssets() {
/* 215 */     return this.assetManager;
/*     */   }
/*     */   
/*     */   public void setAssetManager(AssetManager assetManager) {
/* 219 */     this.assetManager = assetManager;
/*     */   }
/*     */   
/*     */   public Resources getResources() {
/* 223 */     return this.resources;
/*     */   }
/*     */   
/*     */   public void setResources(Resources resources) {
/* 227 */     this.resources = resources;
/*     */   }
/*     */   
/*     */   public Collection<ResolveInfo> getActivities()
/*     */   {
/* 232 */     if (this.activities == null) {
/* 233 */       return null;
/*     */     }
/* 235 */     return this.activities.values();
/*     */   }
/*     */   
/*     */   public List<ResolveInfo> getServices() {
/* 239 */     return this.services;
/*     */   }
/*     */   
/*     */   public void setServices(List<ResolveInfo> services) {
/* 243 */     this.services = services;
/*     */   }
/*     */   
/*     */   public List<ResolveInfo> getProviders() {
/* 247 */     return this.providers;
/*     */   }
/*     */   
/*     */   public void setProviders(List<ResolveInfo> providers) {
/* 251 */     this.providers = providers;
/*     */   }
/*     */   
/*     */   public ResolveInfo getMainActivity() {
/* 255 */     return this.mainActivity;
/*     */   }
/*     */   
/*     */   public List<ResolveInfo> getReceivers() {
/* 259 */     return this.receivers;
/*     */   }
/*     */   
/*     */   public ClassLoader getClassLoader() {
/* 263 */     return this.classLoader;
/*     */   }
/*     */   
/*     */   public void setClassLoader(ClassLoader classLoader) {
/* 267 */     this.classLoader = classLoader;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 280 */     int prime = 31;
/* 281 */     int result = 1;
/* 282 */     result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
/* 283 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj)
/*     */   {
/* 288 */     if (this == obj)
/* 289 */       return true;
/* 290 */     if (obj == null)
/* 291 */       return false;
/* 292 */     if (getClass() != obj.getClass())
/* 293 */       return false;
/* 294 */     PlugInfo other = (PlugInfo)obj;
/* 295 */     if (this.id == null) {
/* 296 */       if (other.id != null)
/* 297 */         return false;
/* 298 */     } else if (!this.id.equals(other.id))
/* 299 */       return false;
/* 300 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isApplicationCreated()
/*     */   {
/* 305 */     return (this.application != null) || (this.isApplicationOnCreated);
/*     */   }
/*     */   
/*     */   public void ensureApplicationCreated() {
/* 309 */     if (isApplicationCreated()) {
/* 310 */       synchronized (this) {
/*     */         try {
/* 312 */           this.application.onCreate();
/* 313 */           if ((this.receivers != null) && (this.receivers.size() > 0)) {
/* 314 */             for (ResolveInfo resolveInfo : this.receivers) {
/* 315 */               if (resolveInfo.activityInfo != null) {
/*     */                 try {
/* 317 */                   BroadcastReceiver broadcastReceiver = (BroadcastReceiver)this.classLoader.loadClass(resolveInfo.activityInfo.name).newInstance();
/* 318 */                   this.application.registerReceiver(broadcastReceiver, resolveInfo.filter);
/*     */                 } catch (Throwable e) {
/* 320 */                   e.printStackTrace();
/* 321 */                   LogUtils.e("Unable to create Receiver : " + resolveInfo.activityInfo.name);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (Throwable ignored) {}
/* 328 */         this.isApplicationOnCreated = true;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 335 */     return super.toString() + "[ id=" + this.id + ", pkg=" + getPackageName() + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\QiguoSdk.jar!\com\kding\core\plugin\environment\PlugInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */