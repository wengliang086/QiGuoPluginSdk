package com.kding.bean;

public class ResponseData<T>
{
  private T data;
  private String error = null;
  private String msg;
  private int npi = -1;
  private boolean success;
  
  public T getData()
  {
    return (T)this.data;
  }
  
  public String getError()
  {
    return this.error;
  }
  
  public String getMsg()
  {
    return this.msg;
  }
  
  public int getNpi()
  {
    return this.npi;
  }
  
  public boolean isSuccess()
  {
    return this.success;
  }
  
  public void setData(T paramT)
  {
    this.data = paramT;
  }
  
  public void setError(String paramString)
  {
    this.error = paramString;
  }
  
  public void setMsg(String paramString)
  {
    this.msg = paramString;
  }
  
  public void setNpi(int paramInt)
  {
    this.npi = paramInt;
  }
  
  public void setSuccess(boolean paramBoolean)
  {
    this.success = paramBoolean;
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\bean\ResponseData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */