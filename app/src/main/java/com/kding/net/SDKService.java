package com.kding.net;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;

import com.kding.bean.AwardCouponBean;
import com.kding.bean.CouponBean;
import com.kding.bean.GameGiftBean;
import com.kding.bean.IssueDetailBean;
import com.kding.bean.IssueListBean;
import com.kding.bean.ItemStatusBean;
import com.kding.bean.NoticeBean;
import com.kding.bean.PayResult;
import com.kding.bean.PersonalGiftBean;
import com.kding.bean.UserCoinBean;
import com.kding.bean.login.LoginEntity;
import com.kding.bean.login.VcodeBean;
import com.kding.converter.GsonConverterFactory;
import com.kding.qxzapi.QiGuoInfo;
import com.kding.utils.EncryptHelp;
import com.kding.utils.QxzInfo;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public final class SDKService {
    private static final int CODE_SUCCESS = 1;
    private static SDKService sRemoteService;
    private final Context mAppContext;
    private final OkHttpClient mHttpClient;
    private final Retrofit mRetrofit;
    private final ISDKService mService;

    private SDKService(Context paramContext) {
        this.mAppContext = paramContext;
        this.mHttpClient = new OkHttpClient.Builder().connectTimeout(20L, TimeUnit.SECONDS).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).cache(new Cache(new File(this.mAppContext.getCacheDir(), "net"), 10485760L)).build();
        this.mRetrofit = new Retrofit.Builder().baseUrl("http://sdk.7xz.com/").client(this.mHttpClient).addConverterFactory(GsonConverterFactory.create()).validateEagerly(true).build();
        this.mService = ((ISDKService) this.mRetrofit.create(ISDKService.class));
    }

    public static SDKService getInstance(Context paramContext) {
        if (sRemoteService == null) {
        }
        try {
            if (sRemoteService == null) {
                sRemoteService = new SDKService(paramContext.getApplicationContext());
            }
            return sRemoteService;
        } finally {
        }
    }

    public void getAwardCoupon(ResponseCallBack<AwardCouponBean> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("appid", QiGuoInfo.INSTANCE.getAppId());
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.getAwardCoupon(localMap).enqueue(paramResponseCallBack);
    }

    public void getCouponList(ResponseCallBack<List<CouponBean>> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("appid", QiGuoInfo.INSTANCE.getAppId());
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.getCouponList(localMap).enqueue(paramResponseCallBack);
    }

    public void getGameGift(int paramInt, ResponseCallBack<GameGiftBean> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("appid", QiGuoInfo.INSTANCE.getAppId());
        localHashMap.put("cpi", "" + paramInt);
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.getGameGift(localMap).enqueue(paramResponseCallBack);
    }

    public void getGrab(String paramString, ResponseCallBack<String> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("lbid", paramString);
        localHashMap.put("token", App.getUserEntity().getToken());
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.getGrab(localMap).enqueue(paramResponseCallBack);
    }

    public OkHttpClient getHttpClient() {
        return this.mHttpClient.newBuilder().build();
    }

    public void getIssueDetail(String paramString, ResponseCallBack<IssueDetailBean> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("qid", paramString);
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.getIssueDetail(localMap).enqueue(paramResponseCallBack);
    }

    public void getIssueList(int paramInt, ResponseCallBack<IssueListBean> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("cpi", "" + paramInt);
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.getIssueList(localMap).enqueue(paramResponseCallBack);
    }

    public void getItemStatus(ResponseCallBack<ItemStatusBean> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("appid", QiGuoInfo.INSTANCE.getAppId());
        localHashMap.put("tune", QiGuoInfo.INSTANCE.getChannel());
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.getItemStatus(localMap).enqueue(paramResponseCallBack);
    }

    public void getNotice(int paramInt, ResponseCallBack<List<NoticeBean>> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("appid", QiGuoInfo.INSTANCE.getAppId());
        localHashMap.put("cpi", "" + paramInt);
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.getNotice(localMap).enqueue(paramResponseCallBack);
    }

    public void getOtherGrab(String paramString, ResponseCallBack<String> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("lbid", paramString);
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.getOtherGrab(localMap).enqueue(paramResponseCallBack);
    }

    public void getPersonalGift(int paramInt, ResponseCallBack<List<PersonalGiftBean>> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("appid", QiGuoInfo.INSTANCE.getAppId());
        localHashMap.put("cpi", "" + paramInt);
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.getPersonalGift(localMap).enqueue(paramResponseCallBack);
    }

    public Retrofit getRetrofit() {
        return this.mRetrofit;
    }

    public void getUsefulCouponList(ResponseCallBack<List<CouponBean>> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("appid", QiGuoInfo.INSTANCE.getAppId());
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.getUsefulCouponList(localMap).enqueue(paramResponseCallBack);
    }

    public void getUserCoin(ResponseCallBack<UserCoinBean> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("appid", QiGuoInfo.INSTANCE.getAppId());
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.getUserCoin(localMap).enqueue(paramResponseCallBack);
    }

    public void itemClick(String paramString1, String paramString2, ResponseCallBack<String> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("appid", QiGuoInfo.INSTANCE.getAppId());
        localHashMap.put("type", paramString1);
        localHashMap.put("itemid", paramString2);
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.itemClick(localMap).enqueue(paramResponseCallBack);
    }

    public void login(Context paramContext, String paramString1, String paramString2, String paramString3, ResponseCallBack<LoginEntity> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("name", paramString1);
        localHashMap.put("pwd", paramString2);
        localHashMap.put("sms", paramString3);
        localHashMap.put("appid", QiGuoInfo.INSTANCE.getAppId());
        localHashMap.put("tune", QiGuoInfo.INSTANCE.getChannel());
        localHashMap.put("device", Build.BRAND);
        localHashMap.put("dtype", Build.MODEL);
        localHashMap.put("os", VERSION.RELEASE);
        localHashMap.put("dpl", QxzInfo.getDpl(paramContext));
        localHashMap.put("net", QxzInfo.getNetWorkType(paramContext) + "");
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.login(localMap).enqueue(paramResponseCallBack);
    }

    public void modifyPw(String paramString1, String paramString2, ResponseCallBack<String> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("oldpw", paramString1);
        localHashMap.put("newpw", paramString2);
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.modifyPw(localMap).enqueue(paramResponseCallBack);
    }

    public void pay(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, ResponseCallBack<PayResult> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("good_id", paramString1);
        localHashMap.put("appid", QiGuoInfo.INSTANCE.getAppId());
        localHashMap.put("coupon_id", paramString2);
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("tune", QiGuoInfo.INSTANCE.getChannel());
        localHashMap.put("szName", paramString3);
        localHashMap.put("money", paramString4);
        localHashMap.put("paytype", paramString5);
        localHashMap.put("extra", paramString6);
        Log.e("pay", "keyMap = " + localHashMap.toString());
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.pay(localMap).enqueue(paramResponseCallBack);
    }

    public void sendVcode(String paramString1, String paramString2, ResponseCallBack<VcodeBean> paramResponseCallBack) {
        HashMap localHashMap = new HashMap();
        localHashMap.put("uid", QiGuoInfo.INSTANCE.getUid());
        localHashMap.put("cellphone", paramString1);
        localHashMap.put("type", paramString2);
        Map localMap = EncryptHelp.encrypt(localHashMap);
        this.mService.sendVcode(localMap).enqueue(paramResponseCallBack);
    }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\net\SDKService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */