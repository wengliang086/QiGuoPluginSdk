/*    */ package com.kding.core.plugin.selector;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.content.pm.ActivityInfo;
/*    */ import com.kding.core.plugin.QiGuoActivity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultActivitySelector
/*    */   implements DynamicActivitySelector
/*    */ {
/* 14 */   private static DynamicActivitySelector DEFAULT = new DefaultActivitySelector();
/*    */   
/*    */   public Class<? extends Activity> selectDynamicActivity(ActivityInfo pluginActivityInfo)
/*    */   {
/* 18 */     return QiGuoActivity.class;
/*    */   }
/*    */   
/*    */   public static DynamicActivitySelector getDefault() {
/* 22 */     return DEFAULT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\QiguoSdk.jar!\com\kding\core\plugin\selector\DefaultActivitySelector.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */