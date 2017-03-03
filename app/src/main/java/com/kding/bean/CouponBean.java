package com.kding.bean;

public class CouponBean
{
  public static final int STATUS_BEEN_USED = 4;
  public static final int STATUS_OVERDUE = 5;
  public static final int STATUS_USEFUL = 3;
  private String deadline;
  private String des;
  private String id;
  private String name;
  private String price;
  private int status;
  
  public String getDeadline()
  {
    return this.deadline;
  }
  
  public String getDes()
  {
    return this.des;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getPrice()
  {
    return this.price;
  }
  
  public int getStatus()
  {
    return this.status;
  }
  
  public void setDeadline(String paramString)
  {
    this.deadline = paramString;
  }
  
  public void setDes(String paramString)
  {
    this.des = paramString;
  }
  
  public void setId(String paramString)
  {
    this.id = paramString;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setPrice(String paramString)
  {
    this.price = paramString;
  }
  
  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\bean\CouponBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */