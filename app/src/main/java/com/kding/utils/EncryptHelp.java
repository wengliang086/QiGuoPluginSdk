package com.kding.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;

public class EncryptHelp
{
  private static final String CLIENT_KEY_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALyyi6PfuTdZ+9mcRMu88UfE0tea20+KUFj+yHnkmDflp7ucw+losv23Gm6of0OwTh2PNYcHeDLpk6xqlkfECAELny1n6PdPIMrAV1VYMOD+oLsL1+cskOjouCQJtrXR4hmWWX6qC2nJWvbeRZaASQ5jHUAg2HLX4mxtPsWr4ju3AgMBAAECgYB0ofdl5xbYe6oLq2dqdvK75ZBc6766v0dCetj3XrAnfK/cat09HBXmdJLF6ygeco8V/jqbp6ZH8c/xNkCFQ0meKf1zv9P07qrGuWNhOalAU/I+ITJcEtcl15AzN+3U+paPd7s6caLnrvNV91dNaRAg2jgj9sIKy8lwqaXhiLL2oQJBAPjrSQwfWxD9EPMLxvtKFv5Ny0Nwagz/bCj55of1qeGNiDzdaHiUWY53KfbB999GIn6DaSqOFcHKO/rLdEaNPtMCQQDCELSSvvATklZ0YDFHeX7dWNBDdYksfaOV6zgfIl1pA9bNOFNJeLk1SLvGCWqaQJCSDxHX/l2BJj3TTahQWOkNAkEAxIMj8SEUCO5xIh/LIHnWez+5V+14m/hOUG8x02Zbjojo5Hw7TO55YWKsS3XIlYlOFCj0rrbrcEmTXqSekFBUJwJAGCwCgeC8gIOSty4gFToB3koorq5eJqeDj7HbrK0YG3N59tfUL+uUjhmAIfucRphSKY8s9s1dEjAUNVSP6WoZpQJARSwak50+OCdncbUM/gwfnDHvr8AhV5G0gQovi70OqVPh6K8bNpmv+rzbDiUpHH695FyG0BM1FlDvnfRZGRI82Q==";
  private static final String SERVER_KEY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsqlbe/B5drdIDTFzyWbALOzul8OSGcIxWXLRLWgJfMkhVo+pwsZvXw5QN/LbXD0J4h1ZdyhLKXr1PtFk70DRbbihlHAvBZ81gZrrNJjiUu0AFH9R4KqqYwDM679avSCUJSpUUMz1tqxV2D7uoF+/myI83hzm48u/cwwsaz81HUwIDAQAB";

  public static String decryptResponse(String paramString)
  {
    try
    {
      String str = decryptWithAES(Base64Utils.decode(URLDecoder.decode(paramString, "UTF-8")));
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }

  private static String decryptWithAES(byte[] paramArrayOfByte)
  {
    try
    {
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(getAESKey().getBytes("utf-8"), "AES");
      Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      localCipher.init(2, localSecretKeySpec);
      String str = new String(localCipher.doFinal(paramArrayOfByte));
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }

//  public static List<NameValuePair> encrypt(List<NameValuePair> paramList)
//  {
//    ArrayList localArrayList = new ArrayList();
//    try
//    {
//      String str1 = encryptRequestData(generateRequestParams(paramList));
//      String str2 = encryptAESKey(getAESKey());
//      localArrayList.add(new BasicNameValuePair("reqdata", str1));
//      localArrayList.add(new BasicNameValuePair("reqkey", str2));
//      return localArrayList;
//    }
//    catch (Exception localException)
//    {
//      localException.printStackTrace();
//    }
//    return localArrayList;
//  }

  public static Map<String, String> encrypt(Map<String, String> paramMap)
  {
    TreeMap localTreeMap = new TreeMap();
    try
    {
      String str1 = encryptRequestData(generateRequestParams(paramMap));
      String str2 = encryptAESKey(getAESKey());
      localTreeMap.put("reqdata", str1);
      localTreeMap.put("reqkey", str2);
      return localTreeMap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return localTreeMap;
  }

  private static String encryptAESKey(String paramString)
  {
    String str1 = "";
    try
    {
      PublicKey localPublicKey = getPublicKey();
      Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      localCipher.init(1, localPublicKey);
      str1 = Base64Utils.encode(localCipher.doFinal(paramString.getBytes()));
      String str2 = URLEncoder.encode(str1, "UTF-8");
      return str2;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return str1;
  }

  public static String encryptRequestData(String paramString)
  {
    String str1 = "";
    try
    {
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(getAESKey().getBytes("utf-8"), "AES");
      Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      localCipher.init(1, localSecretKeySpec);
      str1 = Base64Utils.encode(localCipher.doFinal(paramString.getBytes("utf-8")));
      String str2 = URLEncoder.encode(str1, "UTF-8");
      return str2;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return str1;
  }

//  private static String generateRequestParams(List<NameValuePair> paramList)
//  {
//    String str1 = "";
//    try
//    {
//      Collections.sort(paramList, new Comparator()
//      {
//        public int compare(NameValuePair paramAnonymousNameValuePair1, NameValuePair paramAnonymousNameValuePair2)
//        {
//          return paramAnonymousNameValuePair1.getName().compareTo(paramAnonymousNameValuePair2.getName());
//        }
//      });
//      Iterator localIterator = paramList.iterator();
//      while (localIterator.hasNext())
//      {
//        NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
//        if (str1 != "") {
//          str1 = str1 + "&";
//        }
//        String str3 = str1 + localNameValuePair.getName();
//        String str4 = str3 + "=";
//        str1 = str4 + localNameValuePair.getValue();
//      }
//      String str2 = signRequestData(str1);
//      return str2;
//    }
//    catch (Exception localException)
//    {
//      localException.printStackTrace();
//    }
//    return "";
//  }

  private static String generateRequestParams(Map<String, String> paramMap)
  {
    String str1 = "";
    try
    {
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str3 = (String)localIterator.next();
        if (str1 != "") {
          str1 = str1 + "&";
        }
        String str4 = str1 + str3;
        String str5 = str4 + "=";
        str1 = str5 + (String)paramMap.get(str3);
      }
      String str2 = signRequestData(str1);
      return str2;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }

  private static String getAESKey()
  {
    return "9acda1ea7f99396b";
  }

  private static PrivateKey getPrivateKey()
  {
    try
    {
      PKCS8EncodedKeySpec localPKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64Utils.decode("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALyyi6PfuTdZ+9mcRMu88UfE0tea20+KUFj+yHnkmDflp7ucw+losv23Gm6of0OwTh2PNYcHeDLpk6xqlkfECAELny1n6PdPIMrAV1VYMOD+oLsL1+cskOjouCQJtrXR4hmWWX6qC2nJWvbeRZaASQ5jHUAg2HLX4mxtPsWr4ju3AgMBAAECgYB0ofdl5xbYe6oLq2dqdvK75ZBc6766v0dCetj3XrAnfK/cat09HBXmdJLF6ygeco8V/jqbp6ZH8c/xNkCFQ0meKf1zv9P07qrGuWNhOalAU/I+ITJcEtcl15AzN+3U+paPd7s6caLnrvNV91dNaRAg2jgj9sIKy8lwqaXhiLL2oQJBAPjrSQwfWxD9EPMLxvtKFv5Ny0Nwagz/bCj55of1qeGNiDzdaHiUWY53KfbB999GIn6DaSqOFcHKO/rLdEaNPtMCQQDCELSSvvATklZ0YDFHeX7dWNBDdYksfaOV6zgfIl1pA9bNOFNJeLk1SLvGCWqaQJCSDxHX/l2BJj3TTahQWOkNAkEAxIMj8SEUCO5xIh/LIHnWez+5V+14m/hOUG8x02Zbjojo5Hw7TO55YWKsS3XIlYlOFCj0rrbrcEmTXqSekFBUJwJAGCwCgeC8gIOSty4gFToB3koorq5eJqeDj7HbrK0YG3N59tfUL+uUjhmAIfucRphSKY8s9s1dEjAUNVSP6WoZpQJARSwak50+OCdncbUM/gwfnDHvr8AhV5G0gQovi70OqVPh6K8bNpmv+rzbDiUpHH695FyG0BM1FlDvnfRZGRI82Q=="));
      PrivateKey localPrivateKey = KeyFactory.getInstance("RSA").generatePrivate(localPKCS8EncodedKeySpec);
      return localPrivateKey;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  private static PublicKey getPublicKey()
  {
    try
    {
      X509EncodedKeySpec localX509EncodedKeySpec = new X509EncodedKeySpec(Base64Utils.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsqlbe/B5drdIDTFzyWbALOzul8OSGcIxWXLRLWgJfMkhVo+pwsZvXw5QN/LbXD0J4h1ZdyhLKXr1PtFk70DRbbihlHAvBZ81gZrrNJjiUu0AFH9R4KqqYwDM679avSCUJSpUUMz1tqxV2D7uoF+/myI83hzm48u/cwwsaz81HUwIDAQAB"));
      PublicKey localPublicKey = KeyFactory.getInstance("RSA").generatePublic(localX509EncodedKeySpec);
      return localPublicKey;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  private static String signRequestData(String paramString)
    throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException, Exception
  {
    PrivateKey localPrivateKey = getPrivateKey();
    Signature localSignature = Signature.getInstance("MD5withRSA");
    localSignature.initSign(localPrivateKey);
    localSignature.update(paramString.getBytes("UTF-8"));
    String str = Base64Utils.encode(localSignature.sign());
    return paramString + "&sign=" + URLEncoder.encode(str, "UTF-8");
  }
}