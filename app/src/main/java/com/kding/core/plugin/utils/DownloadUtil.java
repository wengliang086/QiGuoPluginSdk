/*     */ package com.kding.core.plugin.utils;
/*     */ 
/*     */ import android.app.ProgressDialog;
/*     */ import android.content.Context;
/*     */ import android.os.AsyncTask;
/*     */ import com.kding.api.QiGuoApi;
/*     */ import com.kding.core.plugin.PluginManager;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
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
/*     */ public class DownloadUtil
/*     */   extends AsyncTask<Void, Integer, Integer>
/*     */ {
/*     */   private final ProgressDialog progressDialog;
/*     */   private final int code;
/*     */   private final File filePath;
/*  30 */   private String dowloadUrl = "";
/*     */   private File file_path;
/*     */   
/*     */   public DownloadUtil(Context context, int code, String url, ProgressDialog progressDialog) {
/*  34 */     this.progressDialog = progressDialog;
/*  35 */     this.code = code;
/*  36 */     this.dowloadUrl = url;
/*  37 */     this.filePath = context.getDir("plugins-opt", 0);
/*     */   }
/*     */   
/*     */   protected void onPreExecute()
/*     */   {
/*  42 */     this.progressDialog.show();
/*     */   }
/*     */   
/*     */   protected Integer doInBackground(Void... params)
/*     */   {
/*  47 */     HttpURLConnection connection = null;
/*  48 */     InputStream in = null;
/*  49 */     FileOutputStream out = null;
/*     */     try {
/*  51 */       URL url = new URL(this.dowloadUrl);
/*  52 */       connection = (HttpURLConnection)url.openConnection();
/*     */       
/*  54 */       in = connection.getInputStream();
/*  55 */       long fileLength = connection.getContentLength();
/*  56 */       this.file_path = new File(this.filePath, "qiguo_sdk.apk.temp");
/*  57 */       if (this.file_path.exists()) {
/*  58 */         this.file_path.delete();
/*     */       }
/*     */       
/*  61 */       out = new FileOutputStream(this.file_path);
/*  62 */       byte[] buffer = new byte[1048576];
/*  63 */       int len = 0;
/*  64 */       long readLength = 0L;
/*  65 */       int curProgress; while ((len = in.read(buffer)) != -1) {
/*  66 */         out.write(buffer, 0, len);
/*  67 */         readLength += len;
/*  68 */         curProgress = (int)((float)readLength / (float)fileLength * 100.0F);
/*  69 */         publishProgress(new Integer[] { Integer.valueOf(curProgress) });
/*  70 */         if (readLength >= fileLength) {
/*     */           break;
/*     */         }
/*     */       }
/*     */       
/*  75 */       out.flush();
/*     */       
/*     */ 
/*  78 */       return Integer.valueOf(1);
/*     */     }
/*     */     catch (Exception e) {
/*  81 */       e.printStackTrace();
/*     */     } finally {
/*  83 */       if (out != null) {
/*     */         try {
/*  85 */           out.close();
/*     */         } catch (IOException e) {
/*  87 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */       
/*  91 */       if (in != null) {
/*     */         try {
/*  93 */           in.close();
/*     */         } catch (IOException e) {
/*  95 */           e.printStackTrace();
/*     */         }
/*     */       }
/*  98 */       if (connection != null) {
/*  99 */         connection.disconnect();
/*     */       }
/*     */     }
/* 102 */     return Integer.valueOf(0);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void onProgressUpdate(Integer... values)
/*     */   {
/* 108 */     this.progressDialog.setProgress(values[0].intValue());
/*     */   }
/*     */   
/*     */   protected void onPostExecute(Integer integer)
/*     */   {
/* 113 */     this.progressDialog.dismiss();
/* 114 */     if ((integer.intValue() == 1) && (this.file_path != null)) {
/* 115 */       File sdkFile = new File(this.filePath, "qiguo_sdk.apk");
/* 116 */       if (sdkFile.exists()) {
/* 117 */         sdkFile.delete();
/*     */       }
/* 119 */       this.file_path.renameTo(sdkFile);
/* 120 */       PluginManager.getSingleton().setSdkVersion(this.code);
/* 121 */       QiGuoApi.INSTANCE.reStart();
/*     */     } else {
/* 123 */       QiGuoApi.INSTANCE.reStart();
/*     */     }
/*     */   }
/*     */ }