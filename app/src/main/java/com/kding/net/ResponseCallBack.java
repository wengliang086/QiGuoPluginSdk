package com.kding.net;

import android.text.TextUtils;
import com.kding.bean.ResponseData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class ResponseCallBack<T>
  implements Callback<ResponseData<T>>
{
  private static final String DATA_NULL = "data is null";
  public static final int ERROR_TYPE_NOT_NULL = 1;
  public static final int ERROR_TYPE_NULL = -1;
  private ResponseData<T> responseData;
  
  protected final String getMsg()
  {
    if ((this.responseData == null) || (this.responseData.getMsg() == null)) {
      return "链接服务器失败";
    }
    return this.responseData.getMsg();
  }
  
  public abstract void onError(int paramInt, String paramString, Throwable paramThrowable);
  
  public final void onFailure(Call<ResponseData<T>> paramCall, Throwable paramThrowable)
  {
    onError(1, getMsg(), paramThrowable);
  }
  
  public final void onResponse(Call<ResponseData<T>> paramCall, Response<ResponseData<T>> paramResponse)
  {
    if (paramResponse == null) {}
    for (ResponseData localResponseData = null;; localResponseData = (ResponseData)paramResponse.body())
    {
      this.responseData = localResponseData;
      if (this.responseData != null) {
        break;
      }
      onError(1, getMsg(), new NullPointerException("data is null"));
      return;
    }
    if (!TextUtils.isEmpty(this.responseData.getError())) {
      this.responseData.setSuccess("1".equals(this.responseData.getError()));
    }
    if (this.responseData.isSuccess())
    {
      onSuccess(this.responseData.getNpi(), this.responseData.getData());
      return;
    }
    onError(0, getMsg(), new NullPointerException("data is null"));
  }
  
  public abstract void onSuccess(int paramInt, T paramT);
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\net\ResponseCallBack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */