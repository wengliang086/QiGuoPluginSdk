package com.kding.bean;

import java.util.List;

public class IssueListBean
{
  private List<QuestionArrBean> question_arr;
  
  public List<QuestionArrBean> getQuestion_arr()
  {
    return this.question_arr;
  }
  
  public void setQuestion_arr(List<QuestionArrBean> paramList)
  {
    this.question_arr = paramList;
  }
  
  public static class QuestionArrBean
  {
    private int q_state;
    private String q_time;
    private String q_title;
    private String qid;
    
    public int getQ_state()
    {
      return this.q_state;
    }
    
    public String getQ_time()
    {
      return this.q_time;
    }
    
    public String getQ_title()
    {
      return this.q_title;
    }
    
    public String getQid()
    {
      return this.qid;
    }
    
    public void setQ_state(int paramInt)
    {
      this.q_state = paramInt;
    }
    
    public void setQ_time(String paramString)
    {
      this.q_time = paramString;
    }
    
    public void setQ_title(String paramString)
    {
      this.q_title = paramString;
    }
    
    public void setQid(String paramString)
    {
      this.qid = paramString;
    }
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\bean\IssueListBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */