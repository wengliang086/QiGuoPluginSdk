//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.net;

import android.content.Context;
import com.kding.bean.login.CornerStateClass;
import com.kding.bean.login.UserEntity;
import com.kding.utils.SharePrefUtil;

public class App {
  private static final CornerStateClass sCornerState = new CornerStateClass();
  private static final UserEntity sUserEntity = new UserEntity();

  public App() {
  }

  public static CornerStateClass getCornerState() {
    return sCornerState;
  }

  public static UserEntity getUserEntity() {
    return sUserEntity;
  }

  public static boolean isBinded(Context var0) {
    return SharePrefUtil.getInstance(var0).isUserBinded();
  }

  public static boolean isLogin(Context var0) {
    return SharePrefUtil.getInstance(var0).isUserBinded();
  }

  public static void setCornerState(CornerStateClass var0) {
    if(var0 == null) {
      sCornerState.setLibao(0);
      sCornerState.setNotice(0);
      sCornerState.setQuestion(0);
    } else {
      sCornerState.setLibao(var0.getLibao());
      sCornerState.setNotice(var0.getNotice());
      sCornerState.setQuestion(var0.getQuestion());
    }
  }

  public static void setUserEntity(Context var0, UserEntity var1) {
    if(var1 == null) {
      SharePrefUtil.getInstance(var0).putUserBinded(false);
      sUserEntity.setUid("");
      sUserEntity.setGender("");
      sUserEntity.setAvatar("");
      sUserEntity.setCellphone("");
      sUserEntity.setCoin(0);
      sUserEntity.setUsername("");
      sUserEntity.setToken("");
      sUserEntity.setDays("");
      sUserEntity.setUsernick("");
      sUserEntity.setQiguoid("");
      sUserEntity.setLevel("0");
      SharePrefUtil.getInstance(var0).putUserEntity(sUserEntity);
    } else {
      sUserEntity.setUid(var1.getUid());
      sUserEntity.setGender(var1.getGender());
      sUserEntity.setAvatar(var1.getAvatar());
      sUserEntity.setCellphone(var1.getCellphone());
      sUserEntity.setCoin(var1.getCoin());
      sUserEntity.setUsername(var1.getUsername());
      sUserEntity.setToken(var1.getToken());
      sUserEntity.setDays(var1.getDays());
      sUserEntity.setUsernick(var1.getUsernick());
      sUserEntity.setQiguoid(var1.getQiguoid());
      if(!"".equals(var1.getLevel()) && var1.getLevel() != null) {
        sUserEntity.setLevel(var1.getLevel());
      } else {
        sUserEntity.setLevel("0");
      }

      SharePrefUtil.getInstance(var0).putUserEntity(var1);
    }
  }
}
