/*    */ package com.kding.core.plugin.environment;
/*    */ 
/*    */ import android.annotation.TargetApi;
/*    */ import dalvik.system.DexClassLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @TargetApi(3)
/*    */ public class PluginClassLoader
/*    */   extends DexClassLoader
/*    */ {
/*    */   protected PlugInfo plugInfo;
/*    */   
/*    */   public PluginClassLoader(PlugInfo plugInfo, String dexPath, String optimizedDirectory, String libraryPath, ClassLoader parent)
/*    */   {
/* 18 */     super(dexPath, optimizedDirectory, libraryPath, parent);
/* 19 */     this.plugInfo = plugInfo;
/*    */   }
/*    */   
/*    */   public PlugInfo getPlugInfo() {
/* 23 */     return this.plugInfo;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\QiguoSdk.jar!\com\kding\core\plugin\environment\PluginClassLoader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */