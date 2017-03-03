package com.kding.bean;

public class NoticeBean
{
  private boolean is_read;
  private String msg_id;
  private String msg_time;
  private String msg_title;
  private String msg_url;
  
  public String getMsg_id()
  {
    return this.msg_id;
  }
  
  public String getMsg_time()
  {
    return this.msg_time;
  }
  
  public String getMsg_title()
  {
    return this.msg_title;
  }
  
  public String getMsg_url()
  {
    return this.msg_url;
  }
  
  public boolean isIs_read()
  {
    return this.is_read;
  }
  
  public void setIs_read(boolean paramBoolean)
  {
    this.is_read = paramBoolean;
  }
  
  public void setMsg_id(String paramString)
  {
    this.msg_id = paramString;
  }
  
  public void setMsg_time(String paramString)
  {
    this.msg_time = paramString;
  }
  
  public void setMsg_title(String paramString)
  {
    this.msg_title = paramString;
  }
  
  public void setMsg_url(String paramString)
  {
    this.msg_url = paramString;
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\bean\NoticeBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */