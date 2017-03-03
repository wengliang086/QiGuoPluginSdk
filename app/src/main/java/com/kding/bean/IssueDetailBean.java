package com.kding.bean;

import java.util.List;

public class IssueDetailBean
{
  private String qid;
  private List<QuestionContentArrBean> question_content_arr;
  private String question_type;
  
  public String getQid()
  {
    return this.qid;
  }
  
  public List<QuestionContentArrBean> getQuestion_content_arr()
  {
    return this.question_content_arr;
  }
  
  public String getQuestion_type()
  {
    return this.question_type;
  }
  
  public void setQid(String paramString)
  {
    this.qid = paramString;
  }
  
  public void setQuestion_content_arr(List<QuestionContentArrBean> paramList)
  {
    this.question_content_arr = paramList;
  }
  
  public void setQuestion_type(String paramString)
  {
    this.question_type = paramString;
  }
  
  public static class QuestionContentArrBean
  {
    private String content;
    private List<String> imgs;
    private boolean is_ask;
    private String time;
    
    public String getContent()
    {
      return this.content;
    }
    
    public List<String> getImgs()
    {
      return this.imgs;
    }
    
    public String getTime()
    {
      return this.time;
    }
    
    public boolean is_ask()
    {
      return this.is_ask;
    }
    
    public void setContent(String paramString)
    {
      this.content = paramString;
    }
    
    public void setImgs(List<String> paramList)
    {
      this.imgs = paramList;
    }
    
    public void setIs_ask(boolean paramBoolean)
    {
      this.is_ask = paramBoolean;
    }
    
    public void setTime(String paramString)
    {
      this.time = paramString;
    }
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\bean\IssueDetailBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */