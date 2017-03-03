package com.kding.bean.login;

public final class LoginEntity
{
  public static final int BINDED = 1;
  public static final int NOT_BINDED = -1;
  private CornerStateClass CornerState;
  private UserEntity arr;
  private int isBinded;
  
  public UserEntity getArr()
  {
    return this.arr;
  }
  
  public CornerStateClass getCornerState()
  {
    return this.CornerState;
  }
  
  public int getIsBinded()
  {
    return this.isBinded;
  }
  
  public boolean isBinded()
  {
    return this.isBinded == 1;
  }
  
  public void setArr(UserEntity paramUserEntity)
  {
    this.arr = paramUserEntity;
  }
  
  public void setCornerState(CornerStateClass paramCornerStateClass)
  {
    this.CornerState = paramCornerStateClass;
  }
  
  public void setIsBinded(int paramInt)
  {
    this.isBinded = paramInt;
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\bean\login\LoginEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */