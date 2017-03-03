/*    */ package com.kding.core.plugin.environment;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.ContextWrapper;
/*    */ import android.content.res.AssetManager;
/*    */ import android.content.res.Resources;
/*    */ import com.kding.core.plugin.delegate.LayoutInflaterProxyContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PluginContext
/*    */   extends LayoutInflaterProxyContext
/*    */ {
/*    */   private PlugInfo plugInfo;
/*    */   
/*    */   public PluginContext(Context hostContext, PlugInfo plugInfo)
/*    */   {
/* 20 */     super(hostContext);
/* 21 */     if (plugInfo == null) {
/* 22 */       throw new IllegalStateException("Create a plugin context, but not given host context!");
/*    */     }
/* 24 */     this.plugInfo = plugInfo;
/*    */   }
/*    */   
/*    */   public Resources getResources()
/*    */   {
/* 29 */     return this.plugInfo.getResources();
/*    */   }
/*    */   
/*    */   public AssetManager getAssets()
/*    */   {
/* 34 */     return this.plugInfo.getAssets();
/*    */   }
/*    */   
/*    */   public ClassLoader getClassLoader()
/*    */   {
/* 39 */     return this.plugInfo.getClassLoader();
/*    */   }
/*    */   
/*    */   public Context getBaseContext()
/*    */   {
/* 44 */     return getBaseContextInner(super.getBaseContext());
/*    */   }
/*    */   
/*    */   private Context getBaseContextInner(Context baseContext) {
/* 48 */     Context realBaseContext = baseContext;
/* 49 */     while ((realBaseContext instanceof ContextWrapper)) {
/* 50 */       realBaseContext = ((ContextWrapper)realBaseContext).getBaseContext();
/*    */     }
/* 52 */     return realBaseContext;
/*    */   }
/*    */   
/*    */   public Object getSystemService(String name)
/*    */   {
/* 57 */     if (name.equals("GetHostContext"))
/* 58 */       return super.getBaseContext();
/* 59 */     if (name.equals("GetHostRes"))
/* 60 */       return this.plugInfo.getResources();
/* 61 */     if (name.equals("GetHostAssets"))
/* 62 */       return this.plugInfo.getAssets();
/* 63 */     if (name.equals("GetHostClassLoader"))
/* 64 */       return this.plugInfo.getClassLoader();
/* 65 */     if (name.equals("GetPluginPath"))
/* 66 */       return this.plugInfo.getFilePath();
/* 67 */     if (name.equals("GetPluginPkgName"))
/* 68 */       return this.plugInfo.getPackageName();
/* 69 */     if (name.equals("GetPluginPkgInfo")) {
/* 70 */       return this.plugInfo.getPackageInfo();
/*    */     }
/* 72 */     return super.getSystemService(name);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\QiguoSdk.jar!\com\kding\core\plugin\environment\PluginContext.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */