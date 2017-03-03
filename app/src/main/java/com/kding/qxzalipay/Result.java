package com.kding.qxzalipay;

public class Result
{
  String memo;
  String result;
  public String resultStatus;
  
  public Result(String paramString)
  {
    try
    {
      for (String str : paramString.split(";"))
      {
        if (str.startsWith("resultStatus")) {
          this.resultStatus = gatValue(str, "resultStatus");
        }
        if (str.startsWith("result")) {
          this.result = gatValue(str, "result");
        }
        if (str.startsWith("memo")) {
          this.memo = gatValue(str, "memo");
        }
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private String gatValue(String paramString1, String paramString2)
  {
    String str = paramString2 + "={";
    return paramString1.substring(paramString1.indexOf(str) + str.length(), paramString1.lastIndexOf("}"));
  }
  
  public String toString()
  {
    return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + "}";
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\qxzalipay\Result.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */