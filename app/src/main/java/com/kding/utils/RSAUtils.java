package com.kding.utils;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

public class RSAUtils
{
  public static final String KEY_ALGORITHM = "RSA";
  private static final int MAX_DECRYPT_BLOCK = 128;
  private static final int MAX_ENCRYPT_BLOCK = 117;
  private static final String PRIVATE_KEY = "RSAPrivateKey";
  private static final String PUBLIC_KEY = "RSAPublicKey";
  public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
  
  public static byte[] decryptByPrivateKey(byte[] paramArrayOfByte, String paramString)
    throws Exception
  {
    PKCS8EncodedKeySpec localPKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64Utils.decode(paramString));
    KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
    PrivateKey localPrivateKey = localKeyFactory.generatePrivate(localPKCS8EncodedKeySpec);
    Cipher localCipher = Cipher.getInstance(localKeyFactory.getAlgorithm());
    localCipher.init(2, localPrivateKey);
    int i = paramArrayOfByte.length;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int j = 0;
    int k = 0;
    if (i - j > 0)
    {
      if (i - j > 128) {}
      for (byte[] arrayOfByte2 = localCipher.doFinal(paramArrayOfByte, j, 128);; arrayOfByte2 = localCipher.doFinal(paramArrayOfByte, j, i - j))
      {
        localByteArrayOutputStream.write(arrayOfByte2, 0, arrayOfByte2.length);
        k++;
        j = k * 128;
        break;
      }
    }
    byte[] arrayOfByte1 = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    return arrayOfByte1;
  }
  
  public static byte[] decryptByPublicKey(byte[] paramArrayOfByte, String paramString)
    throws Exception
  {
    X509EncodedKeySpec localX509EncodedKeySpec = new X509EncodedKeySpec(Base64Utils.decode(paramString));
    KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
    PublicKey localPublicKey = localKeyFactory.generatePublic(localX509EncodedKeySpec);
    Cipher localCipher = Cipher.getInstance(localKeyFactory.getAlgorithm());
    localCipher.init(2, localPublicKey);
    int i = paramArrayOfByte.length;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int j = 0;
    int k = 0;
    if (i - j > 0)
    {
      if (i - j > 128) {}
      for (byte[] arrayOfByte2 = localCipher.doFinal(paramArrayOfByte, j, 128);; arrayOfByte2 = localCipher.doFinal(paramArrayOfByte, j, i - j))
      {
        localByteArrayOutputStream.write(arrayOfByte2, 0, arrayOfByte2.length);
        k++;
        j = k * 128;
        break;
      }
    }
    byte[] arrayOfByte1 = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    return arrayOfByte1;
  }
  
  public static byte[] encryptByPrivateKey(byte[] paramArrayOfByte, String paramString)
    throws Exception
  {
    PKCS8EncodedKeySpec localPKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64Utils.decode(paramString));
    KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
    PrivateKey localPrivateKey = localKeyFactory.generatePrivate(localPKCS8EncodedKeySpec);
    Cipher localCipher = Cipher.getInstance(localKeyFactory.getAlgorithm());
    localCipher.init(1, localPrivateKey);
    int i = paramArrayOfByte.length;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int j = 0;
    int k = 0;
    if (i - j > 0)
    {
      if (i - j > 117) {}
      for (byte[] arrayOfByte2 = localCipher.doFinal(paramArrayOfByte, j, 117);; arrayOfByte2 = localCipher.doFinal(paramArrayOfByte, j, i - j))
      {
        localByteArrayOutputStream.write(arrayOfByte2, 0, arrayOfByte2.length);
        k++;
        j = k * 117;
        break;
      }
    }
    byte[] arrayOfByte1 = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    return arrayOfByte1;
  }
  
  public static byte[] encryptByPublicKey(byte[] paramArrayOfByte, String paramString)
    throws Exception
  {
    X509EncodedKeySpec localX509EncodedKeySpec = new X509EncodedKeySpec(Base64Utils.decode(paramString));
    KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
    PublicKey localPublicKey = localKeyFactory.generatePublic(localX509EncodedKeySpec);
    Cipher localCipher = Cipher.getInstance(localKeyFactory.getAlgorithm());
    localCipher.init(1, localPublicKey);
    int i = paramArrayOfByte.length;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int j = 0;
    int k = 0;
    if (i - j > 0)
    {
      if (i - j > 117) {}
      for (byte[] arrayOfByte2 = localCipher.doFinal(paramArrayOfByte, j, 117);; arrayOfByte2 = localCipher.doFinal(paramArrayOfByte, j, i - j))
      {
        localByteArrayOutputStream.write(arrayOfByte2, 0, arrayOfByte2.length);
        k++;
        j = k * 117;
        break;
      }
    }
    byte[] arrayOfByte1 = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    return arrayOfByte1;
  }
  
  public static Map<String, Object> genKeyPair()
    throws Exception
  {
    KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
    localKeyPairGenerator.initialize(1024);
    KeyPair localKeyPair = localKeyPairGenerator.generateKeyPair();
    RSAPublicKey localRSAPublicKey = (RSAPublicKey)localKeyPair.getPublic();
    RSAPrivateKey localRSAPrivateKey = (RSAPrivateKey)localKeyPair.getPrivate();
    HashMap localHashMap = new HashMap(2);
    localHashMap.put("RSAPublicKey", localRSAPublicKey);
    localHashMap.put("RSAPrivateKey", localRSAPrivateKey);
    return localHashMap;
  }
  
  public static String getPrivateKey(Map<String, Object> paramMap)
    throws Exception
  {
    return Base64Utils.encode(((Key)paramMap.get("RSAPrivateKey")).getEncoded());
  }
  
  public static String getPublicKey(Map<String, Object> paramMap)
    throws Exception
  {
    return Base64Utils.encode(((Key)paramMap.get("RSAPublicKey")).getEncoded());
  }
  
  public static String sign(byte[] paramArrayOfByte, String paramString)
    throws Exception
  {
    PKCS8EncodedKeySpec localPKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64Utils.decode(paramString));
    PrivateKey localPrivateKey = KeyFactory.getInstance("RSA").generatePrivate(localPKCS8EncodedKeySpec);
    Signature localSignature = Signature.getInstance("MD5withRSA");
    localSignature.initSign(localPrivateKey);
    localSignature.update(paramArrayOfByte);
    return Base64Utils.encode(localSignature.sign());
  }
  
  public static boolean verify(byte[] paramArrayOfByte, String paramString1, String paramString2)
    throws Exception
  {
    X509EncodedKeySpec localX509EncodedKeySpec = new X509EncodedKeySpec(Base64Utils.decode(paramString1));
    PublicKey localPublicKey = KeyFactory.getInstance("RSA").generatePublic(localX509EncodedKeySpec);
    Signature localSignature = Signature.getInstance("MD5withRSA");
    localSignature.initVerify(localPublicKey);
    localSignature.update(paramArrayOfByte);
    return localSignature.verify(Base64Utils.decode(paramString2));
  }
}