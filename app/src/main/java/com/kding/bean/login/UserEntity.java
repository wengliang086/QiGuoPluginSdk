package com.kding.bean.login;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class UserEntity
  implements Cloneable, Parcelable
{
  public static final Creator<UserEntity> CREATOR = new Creator()
  {
    public UserEntity createFromParcel(Parcel paramAnonymousParcel)
    {
      return new UserEntity(paramAnonymousParcel);
    }
    
    public UserEntity[] newArray(int paramAnonymousInt)
    {
      return new UserEntity[paramAnonymousInt];
    }
  };
  private String age;
  private String avatar;
  private String cellphone;
  private int coin;
  private String days;
  private String game_number;
  private String gender;
  private String gender_int;
  private String grab_number;
  private String level;
  private String notice_no_read;
  private String qiguoid;
  private String token;
  private String uid;
  private String username;
  private String usernick;
  
  public UserEntity() {}
  
  protected UserEntity(Parcel paramParcel)
  {
    this.uid = paramParcel.readString();
    this.username = paramParcel.readString();
    this.cellphone = paramParcel.readString();
    this.coin = paramParcel.readInt();
    this.gender = paramParcel.readString();
    this.avatar = paramParcel.readString();
    this.token = paramParcel.readString();
    this.grab_number = paramParcel.readString();
    this.game_number = paramParcel.readString();
    this.usernick = paramParcel.readString();
    this.level = paramParcel.readString();
    this.days = paramParcel.readString();
    this.age = paramParcel.readString();
    this.qiguoid = paramParcel.readString();
    this.notice_no_read = paramParcel.readString();
    this.gender_int = paramParcel.readString();
  }
  
  public UserEntity clone()
  {
    UserEntity localUserEntity = new UserEntity();
    localUserEntity.uid = this.uid;
    localUserEntity.username = this.username;
    localUserEntity.cellphone = this.cellphone;
    localUserEntity.coin = this.coin;
    localUserEntity.gender = this.gender;
    localUserEntity.avatar = this.avatar;
    localUserEntity.token = this.token;
    localUserEntity.grab_number = this.grab_number;
    localUserEntity.game_number = this.game_number;
    localUserEntity.usernick = this.usernick;
    localUserEntity.level = this.level;
    localUserEntity.days = this.days;
    localUserEntity.age = this.age;
    localUserEntity.qiguoid = this.qiguoid;
    localUserEntity.notice_no_read = this.notice_no_read;
    localUserEntity.gender_int = this.gender_int;
    return localUserEntity;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAge()
  {
    return this.age;
  }
  
  public String getAvatar()
  {
    return this.avatar;
  }
  
  public String getCellphone()
  {
    return this.cellphone;
  }
  
  public int getCoin()
  {
    return this.coin;
  }
  
  public String getDays()
  {
    return this.days;
  }
  
  public String getGame_number()
  {
    return this.game_number;
  }
  
  public String getGender()
  {
    return this.gender;
  }
  
  public String getGender_int()
  {
    return this.gender_int;
  }
  
  public String getGrab_number()
  {
    return this.grab_number;
  }
  
  public String getLevel()
  {
    return this.level;
  }
  
  public String getNotice_no_read()
  {
    return this.notice_no_read;
  }
  
  public String getQiguoid()
  {
    return this.qiguoid;
  }
  
  public String getToken()
  {
    return this.token;
  }
  
  public String getUid()
  {
    return this.uid;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public String getUsernick()
  {
    return this.usernick;
  }
  
  public void setAge(String paramString)
  {
    this.age = paramString;
  }
  
  public void setAvatar(String paramString)
  {
    this.avatar = paramString;
  }
  
  public void setCellphone(String paramString)
  {
    this.cellphone = paramString;
  }
  
  public void setCoin(int paramInt)
  {
    this.coin = paramInt;
  }
  
  public void setDays(String paramString)
  {
    this.days = paramString;
  }
  
  public void setGame_number(String paramString)
  {
    this.game_number = paramString;
  }
  
  public void setGender(String paramString)
  {
    this.gender = paramString;
  }
  
  public void setGender_int(String paramString)
  {
    this.gender_int = paramString;
  }
  
  public void setGrab_number(String paramString)
  {
    this.grab_number = paramString;
  }
  
  public void setLevel(String paramString)
  {
    this.level = paramString;
  }
  
  public void setNotice_no_read(String paramString)
  {
    this.notice_no_read = paramString;
  }
  
  public void setQiguoid(String paramString)
  {
    this.qiguoid = paramString;
  }
  
  public void setToken(String paramString)
  {
    this.token = paramString;
  }
  
  public void setUid(String paramString)
  {
    this.uid = paramString;
  }
  
  public void setUsername(String paramString)
  {
    this.username = paramString;
  }
  
  public void setUsernick(String paramString)
  {
    this.usernick = paramString;
  }
  
  public String toString()
  {
    return "UserEntity{uid='" + this.uid + '\'' + ", username='" + this.username + '\'' + ", cellphone='" + this.cellphone + '\'' + ", coin=" + this.coin + ", gender='" + this.gender + '\'' + ", avatar='" + this.avatar + '\'' + ", token='" + this.token + '\'' + ", grab_number='" + this.grab_number + '\'' + ", game_number='" + this.game_number + '\'' + ", usernick='" + this.usernick + '\'' + ", level='" + this.level + '\'' + ", days='" + this.days + '\'' + '}';
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.uid);
    paramParcel.writeString(this.username);
    paramParcel.writeString(this.cellphone);
    paramParcel.writeInt(this.coin);
    paramParcel.writeString(this.gender);
    paramParcel.writeString(this.avatar);
    paramParcel.writeString(this.token);
    paramParcel.writeString(this.grab_number);
    paramParcel.writeString(this.game_number);
    paramParcel.writeString(this.usernick);
    paramParcel.writeString(this.level);
    paramParcel.writeString(this.days);
    paramParcel.writeString(this.age);
    paramParcel.writeString(this.qiguoid);
    paramParcel.writeString(this.notice_no_read);
    paramParcel.writeString(this.gender_int);
  }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\bean\login\UserEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */