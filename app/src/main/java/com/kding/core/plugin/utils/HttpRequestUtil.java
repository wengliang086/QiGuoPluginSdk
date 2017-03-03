/*    */ package com.kding.core.plugin.utils;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URL;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HttpRequestUtil
/*    */ {
/*    */   public static void sendGetHttpRequest(final String address, final HttpCallbackListener listener)
/*    */   {
/* 68 */     new Thread(new Runnable()
/*    */     {
/*    */       public void run()
/*    */       {
/* 26 */         HttpURLConnection connection = null;
/*    */         try
/*    */         {
/* 29 */           URL url = new URL(address);
/*    */           
/* 31 */           connection = (HttpURLConnection)url.openConnection();
/*    */           
/* 33 */           connection.setRequestMethod("GET");
/*    */           
/* 35 */           connection.setConnectTimeout(8000);
/*    */           
/* 37 */           connection.setReadTimeout(8000);
/*    */           
/* 39 */           connection.setDoInput(true);
/*    */           
/* 41 */           connection.setDoOutput(true);
/*    */           
/* 43 */           InputStream in = connection.getInputStream();
/*    */           
/* 45 */           BufferedReader reader = new BufferedReader(new InputStreamReader(in));
/*    */           
/*    */ 
/* 48 */           StringBuilder response = new StringBuilder();
/*    */           String line;
/* 50 */           while ((line = reader.readLine()) != null) {
/* 51 */             response.append(line);
/*    */           }
/* 53 */           if (listener != null)
/*    */           {
/* 55 */             listener.onFinish(response.toString());
/*    */           }
/*    */         } catch (Exception e) {
/* 58 */           if (listener != null)
/*    */           {
/* 60 */             listener.onError(e);
/*    */           }
/*    */         } finally {
/* 63 */           if (connection != null) {
/* 64 */             connection.disconnect();
/*    */           }
/*    */         }
/*    */       }
/*    */     })
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 68 */       .start();
/*    */   }
/*    */ }