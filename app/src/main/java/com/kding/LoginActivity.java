package com.kding;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;

import com.kding.bean.login.LoginEntity;
import com.kding.bean.login.UserEntity;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import com.kding.qxzapi.QiGuoInfo;
import com.kding.utils.DialogUtils;
import com.kding.utils.QxzSharedPrefUtil;
import com.kding.utils.SharePrefUtil;
import com.kding.utils.ToastUtils;

public class LoginActivity
        extends Activity {
    private LoginFragment loginFragment;

    public void autoLogin() {
        UserEntity localUserEntity = SharePrefUtil.getInstance(this).getUserEntity();
        if (localUserEntity != null) {
            final Dialog localDialog = DialogUtils.createLoadingDialog(this);
            String str1 = QxzSharedPrefUtil.getPassword(this);
            String str2 = QxzSharedPrefUtil.getIdentifyCode(this);
            if ((!TextUtils.isEmpty(str2)) && (!TextUtils.isEmpty(str1))) {
                str2 = null;
            }
            SDKService.getInstance(this).login(this, localUserEntity.getUsername(), str1, str2, new ResponseCallBack<LoginEntity>() {
                public void onError(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable) {
                    DialogUtils.closeDialog(localDialog);
                    QiGuoInfo.INSTANCE.getQiGuoCallBack().onFailure(paramAnonymousString);
                    ToastUtils.showToast(LoginActivity.this, "自动登录失败，请重新登录");
                    LoginActivity.this.normalLogin();
                }

                public void onSuccess(int paramAnonymousInt, LoginEntity paramAnonymousLoginEntity) {
                    DialogUtils.closeDialog(localDialog);
                    LoginActivity.this.loginFragment.loginSuccess(LoginActivity.this, paramAnonymousLoginEntity);
                    LoginActivity.this.finish();
                }
            });
            return;
        }
        normalLogin();
    }

    public void normalLogin() {
        this.loginFragment.show(getFragmentManager(), "");
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.empty);
        if (this.loginFragment == null) {
            this.loginFragment = new LoginFragment();
        }
        if (QxzSharedPrefUtil.getBoolean(this, "autologin", Boolean.valueOf(true)).booleanValue()) {
            autoLogin();
            return;
        }
        normalLogin();
    }
}