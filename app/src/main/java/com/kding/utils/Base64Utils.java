package com.kding.utils;

import it.sauronsoftware.base64.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Base64Utils
{
  private static final int CACHE_SIZE = 1024;
  
  public static void byteArrayToFile(byte[] paramArrayOfByte, String paramString)
    throws Exception
  {
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    File localFile = new File(paramString);
    if (!localFile.getParentFile().exists()) {
      localFile.getParentFile().mkdirs();
    }
    localFile.createNewFile();
    FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = localByteArrayInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localFileOutputStream.write(arrayOfByte, 0, i);
      localFileOutputStream.flush();
    }
    localFileOutputStream.close();
    localByteArrayInputStream.close();
  }
  
  public static byte[] decode(String paramString)
    throws Exception
  {
    return Base64.decode(paramString.getBytes());
  }
  
  public static void decodeToFile(String paramString1, String paramString2)
    throws Exception
  {
    byteArrayToFile(decode(paramString2), paramString1);
  }
  
  public static String encode(byte[] paramArrayOfByte)
    throws Exception
  {
    return new String(Base64.encode(paramArrayOfByte));
  }
  
  public static String encodeFile(String paramString)
    throws Exception
  {
    return encode(fileToByte(paramString));
  }
  
  public static byte[] fileToByte(String paramString)
    throws Exception
  {
    byte[] arrayOfByte1 = new byte[0];
    File localFile = new File(paramString);
    if (localFile.exists())
    {
      FileInputStream localFileInputStream = new FileInputStream(localFile);
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(2048);
      byte[] arrayOfByte2 = new byte['Ѐ'];
      for (;;)
      {
        int i = localFileInputStream.read(arrayOfByte2);
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte2, 0, i);
        localByteArrayOutputStream.flush();
      }
      localByteArrayOutputStream.close();
      localFileInputStream.close();
      arrayOfByte1 = localByteArrayOutputStream.toByteArray();
    }
    return arrayOfByte1;
  }
}