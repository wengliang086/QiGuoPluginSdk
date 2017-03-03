/*    */ package com.kding.core.plugin.environment;
/*    */ 
/*    */ import android.text.TextUtils;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CreateActivityData
/*    */   implements Serializable
/*    */ {
/*    */   public String activityName;
/*    */   public String pluginPkg;
/*    */   
/*    */   public CreateActivityData(String activityName, String pluginPkg)
/*    */   {
/* 25 */     this.activityName = activityName;
/* 26 */     this.pluginPkg = pluginPkg;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o)
/*    */   {
/* 31 */     if ((o instanceof CreateActivityData)) {
/* 32 */       CreateActivityData another = (CreateActivityData)o;
/*    */       
/* 34 */       return (TextUtils.equals(this.activityName, another.activityName)) && (TextUtils.equals(this.pluginPkg, another.pluginPkg));
/*    */     }
/* 36 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 41 */     int result = this.activityName != null ? this.activityName.hashCode() : 0;
/* 42 */     result = 31 * result + (this.pluginPkg != null ? this.pluginPkg.hashCode() : 0);
/* 43 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\QiguoSdk.jar!\com\kding\core\plugin\environment\CreateActivityData.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */