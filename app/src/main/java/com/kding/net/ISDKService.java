package com.kding.net;

import com.kding.bean.AwardCouponBean;
import com.kding.bean.CouponBean;
import com.kding.bean.GameGiftBean;
import com.kding.bean.IssueDetailBean;
import com.kding.bean.IssueListBean;
import com.kding.bean.ItemStatusBean;
import com.kding.bean.NoticeBean;
import com.kding.bean.PayResult;
import com.kding.bean.PersonalGiftBean;
import com.kding.bean.ResponseData;
import com.kding.bean.UserCoinBean;
import com.kding.bean.login.LoginEntity;
import com.kding.bean.login.VcodeBean;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public abstract interface ISDKService
{
  @FormUrlEncoded
  @POST("t201sdk/payfanli")
  public abstract Call<ResponseData<AwardCouponBean>> getAwardCoupon(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("mgc/voucherlist")
  public abstract Call<ResponseData<List<CouponBean>>> getCouponList(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("t201sdk/sdkgamegrab")
  public abstract Call<ResponseData<GameGiftBean>> getGameGift(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("t201sdk/getGrab")
  public abstract Call<ResponseData<String>> getGrab(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("t201sdk/getQuestionDetail")
  public abstract Call<ResponseData<IssueDetailBean>> getIssueDetail(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("t201sdk/getQuestionList")
  public abstract Call<ResponseData<IssueListBean>> getIssueList(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("t201sdk/getCornerState")
  public abstract Call<ResponseData<ItemStatusBean>> getItemStatus(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("t201sdk/sdk_notice_list")
  public abstract Call<ResponseData<List<NoticeBean>>> getNotice(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("t201sdk/getothergrab")
  public abstract Call<ResponseData<String>> getOtherGrab(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("t201sdk/getMyHaveGrablist")
  public abstract Call<ResponseData<List<PersonalGiftBean>>> getPersonalGift(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("nsdk/usefulvoucher")
  public abstract Call<ResponseData<List<CouponBean>>> getUsefulCouponList(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("t201sdk/getCoinAndCanUser")
  public abstract Call<ResponseData<UserCoinBean>> getUserCoin(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("t201sdk/clickRecordSeeTime")
  public abstract Call<ResponseData<String>> itemClick(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("/nsdk/login")
  public abstract Call<ResponseData<LoginEntity>> login(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("t201sdk/modifypw")
  public abstract Call<ResponseData<String>> modifyPw(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("nsdk/AliPayAndUnionPayAndCoinPay")
  public abstract Call<ResponseData<PayResult>> pay(@FieldMap Map<String, String> paramMap);
  
  @FormUrlEncoded
  @POST("t201sdk/sendvcode")
  public abstract Call<ResponseData<VcodeBean>> sendVcode(@FieldMap Map<String, String> paramMap);
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\net\ISDKService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */