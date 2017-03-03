package com.kding.bean;

import java.util.List;

public class GameGiftBean
{
  private String gamename;
  private List<GiftListBean> gift_list;
  
  public String getGamename()
  {
    return this.gamename;
  }
  
  public List<GiftListBean> getGift_list()
  {
    return this.gift_list;
  }
  
  public void setGamename(String paramString)
  {
    this.gamename = paramString;
  }
  
  public void setGift_list(List<GiftListBean> paramList)
  {
    this.gift_list = paramList;
  }
  
  public static class GiftListBean
  {
    private String grab_id;
    private String grab_info;
    private int grab_issued_times;
    private boolean grab_ketao;
    private boolean grab_log;
    private String grab_name;
    private int grab_remain;
    private int grab_total;
    
    public String getGrab_id()
    {
      return this.grab_id;
    }
    
    public String getGrab_info()
    {
      return this.grab_info;
    }
    
    public int getGrab_issued_times()
    {
      return this.grab_issued_times;
    }
    
    public String getGrab_name()
    {
      return this.grab_name;
    }
    
    public int getGrab_remain()
    {
      return this.grab_remain;
    }
    
    public int getGrab_total()
    {
      return this.grab_total;
    }
    
    public boolean isGrab_ketao()
    {
      return this.grab_ketao;
    }
    
    public boolean isGrab_log()
    {
      return this.grab_log;
    }
    
    public void setGrab_id(String paramString)
    {
      this.grab_id = paramString;
    }
    
    public void setGrab_info(String paramString)
    {
      this.grab_info = paramString;
    }
    
    public void setGrab_issued_times(int paramInt)
    {
      this.grab_issued_times = paramInt;
    }
    
    public void setGrab_ketao(boolean paramBoolean)
    {
      this.grab_ketao = paramBoolean;
    }
    
    public void setGrab_log(boolean paramBoolean)
    {
      this.grab_log = paramBoolean;
    }
    
    public void setGrab_name(String paramString)
    {
      this.grab_name = paramString;
    }
    
    public void setGrab_remain(int paramInt)
    {
      this.grab_remain = paramInt;
    }
    
    public void setGrab_total(int paramInt)
    {
      this.grab_total = paramInt;
    }
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\bean\GameGiftBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */