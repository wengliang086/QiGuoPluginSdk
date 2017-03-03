/*     */ package com.kding.core.plugin.utils;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.AssetManager;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.channels.FileChannel;
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
/*     */ public class FileUtil
/*     */ {
/*     */   public static void writeToFile(InputStream dataIns, File target)
/*     */     throws IOException
/*     */   {
/*  41 */     int BUFFER = 1024;
/*  42 */     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target));
/*     */     
/*     */ 
/*  45 */     byte[] data = new byte['Ѐ'];
/*  46 */     int count; while ((count = dataIns.read(data, 0, 1024)) != -1) {
/*  47 */       bos.write(data, 0, count);
/*     */     }
/*  49 */     bos.close();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void copyFile(File source, File target)
/*     */   {
/*  65 */     FileInputStream fi = null;
/*  66 */     FileOutputStream fo = null;
/*     */     
/*  68 */     FileChannel in = null;
/*     */     
/*  70 */     FileChannel out = null;
/*     */     try
/*     */     {
/*  73 */       fi = new FileInputStream(source);
/*     */       
/*  75 */       fo = new FileOutputStream(target);
/*     */       
/*  77 */       in = fi.getChannel();
/*     */       
/*  79 */       out = fo.getChannel();
/*     */       
/*  81 */       in.transferTo(0L, in.size(), out); return;
/*     */     }
/*     */     catch (IOException e) {
/*  84 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/*  87 */         if (fi != null) {
/*  88 */           fi.close();
/*     */         }
/*     */         
/*  91 */         if (in != null) {
/*  92 */           in.close();
/*     */         }
/*     */         
/*  95 */         if (fo != null) {
/*  96 */           fo.close();
/*     */         }
/*     */         
/*  99 */         if (out != null) {
/* 100 */           out.close();
/*     */         }
/*     */       }
/*     */       catch (IOException e) {
/* 104 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void copyFileFromAssets(Context context, String sourceName, File dexInternalStoragePath, String targetFileName)
/*     */   {
/* 111 */     File tempFile = new File(dexInternalStoragePath, targetFileName + ".temp");
/* 112 */     File targetFile = new File(dexInternalStoragePath, targetFileName);
/* 113 */     AssetManager am = context.getAssets();
/* 114 */     InputStream is = null;
/* 115 */     FileOutputStream fos = null;
/*     */     try {
/* 117 */       is = am.open(sourceName);
/* 118 */       fos = new FileOutputStream(tempFile);
/* 119 */       byte[] buffer = new byte['Ѐ'];
/* 120 */       int count = 0;
/* 121 */       while ((count = is.read(buffer)) > 0) {
/* 122 */         fos.write(buffer, 0, count);
/*     */       }
/* 124 */       fos.flush();
/* 125 */       tempFile.renameTo(targetFile);
/*     */     } catch (IOException e) {
/* 127 */       e.printStackTrace();
/*     */     } finally {
/* 129 */       closeSilently(is);
/* 130 */       closeSilently(fos);
/*     */     }
/*     */   }
/*     */   
/* 134 */   private static void closeSilently(Closeable closeable) { if (closeable == null) {
/* 135 */       return;
/*     */     }
/*     */     try {
/* 138 */       closeable.close();
/*     */     }
/*     */     catch (Throwable e) {}
/*     */   }
/*     */ }