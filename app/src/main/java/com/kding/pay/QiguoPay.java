package com.kding.pay;

import android.app.Activity;
import com.kding.bean.PayResult;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;

public enum QiguoPay
{
  INSTANCE;

  public static final int ALI_PAY = 1;
  public static final String ALI_PAY_STR = "alipay";
  public static final int COIN_PAY = 3;
  public static final String COIN_PAY_STR = "coinpay";
  public static final int COUPON_PAY = 4;
  public static final String COUPON_PAY_STR = "couponpay";
  public static final int UNION_PAY = 2;
  public static final String UNION_PAY_STR = "unionpay";

  private QiguoPay() {}

  private void startPay(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, final PayCallBack paramPayCallBack)
  {
    SDKService.getInstance(paramActivity).pay(paramString2, paramString3, paramString1, paramString4, paramString5, paramString6, new ResponseCallBack<PayResult>()
    {
      public void onError(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        paramPayCallBack.onFail(paramAnonymousString);
      }

      public void onSuccess(int paramAnonymousInt, PayResult paramAnonymousPayResult)
      {
        paramPayCallBack.onSuc(paramAnonymousPayResult);
      }
    });
  }

  public void startPay(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, String paramString5, PayCallBack paramPayCallBack)
  {
    switch (paramInt)
    {
      default:
        return;
      case 1:
        startPay(paramActivity, paramString1, paramString2, paramString3, paramString4, "alipay", paramString5, paramPayCallBack);
        return;
      case 2:
        startPay(paramActivity, paramString1, paramString2, paramString3, paramString4, "unionpay", paramString5, paramPayCallBack);
        return;
      case 3:
        startPay(paramActivity, paramString1, paramString2, paramString3, paramString4, "coinpay", paramString5, paramPayCallBack);
        return;
    }
//    startPay(paramActivity, paramString1, paramString2, paramString3, paramString4, "couponpay", paramString5, paramPayCallBack);
  }
}