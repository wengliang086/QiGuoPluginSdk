//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kding.base.BaseDialogFragment;
import com.kding.bean.login.LoginEntity;
import com.kding.bean.login.UserEntity;
import com.kding.bean.login.VcodeBean;
import com.kding.net.App;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import com.kding.qxzapi.QiGuoInfo;
import com.kding.utils.DensityUtil;
import com.kding.utils.QxzSharedPrefUtil;
import com.kding.utils.SharePrefUtil;
import com.kding.utils.ToastUtils;

import java.util.regex.Pattern;

public class LoginFragment extends BaseDialogFragment implements OnClickListener {
    public static final int LOGIN_TYPE_ACCOUNT = 2;
    public static final int LOGIN_TYPE_PHONE = 1;
    private Drawable errorDrawable;
    private boolean mCountDownFinished = true;
    private CountDownTimer mCountDownTimer;
    private String mLoginName;
    private String mLoginPhone;
    private int mLoginType = 1;
    private ProgressBar progressBar;
    private Button sdk_dialog_login_button;
    private ImageView sdk_dialog_login_clear;
    private EditText sdk_dialog_login_phone_name;
    private TextView sdk_dialog_login_sendvcode;
    private EditText sdk_dialog_login_sms_pwd;
    private TextView sdk_dialog_login_switcher;
    private TextView sdk_dialog_login_title;

    public LoginFragment() {
    }

    private void initCountDownTimer() {
        this.mCountDownTimer = new CountDownTimer(60000L, 1000) {
            public void onFinish() {
                LoginFragment.this.mCountDownFinished = true;
                LoginFragment.this.sdk_dialog_login_sendvcode.setText("获取验证码");
            }

            public void onTick(long var1) {
                LoginFragment.this.sdk_dialog_login_sendvcode.setText(var1 / 1000L + "s");
            }
        };
    }

    private void initEvents() {
        this.sdk_dialog_login_clear.setOnClickListener(this);
        this.sdk_dialog_login_sendvcode.setOnClickListener(this);
        this.sdk_dialog_login_button.setOnClickListener(this);
        this.sdk_dialog_login_switcher.setOnClickListener(this);
    }

    private void initViews(View var1) {
        this.progressBar = (ProgressBar) var1.findViewById(R.id.progressbar);
        this.sdk_dialog_login_title = (TextView) var1.findViewById(R.id.sdk_dialog_login_title);
        this.sdk_dialog_login_phone_name = (EditText) var1.findViewById(R.id.sdk_dialog_login_phone_name);
        this.sdk_dialog_login_clear = (ImageView) var1.findViewById(R.id.sdk_dialog_login_clear);
        this.sdk_dialog_login_sms_pwd = (EditText) var1.findViewById(R.id.sdk_dialog_login_sms_pwd);
        this.sdk_dialog_login_sendvcode = (TextView) var1.findViewById(R.id.sdk_dialog_login_sendvcode);
        this.sdk_dialog_login_button = (Button) var1.findViewById(R.id.sdk_dialog_login_button);
        this.sdk_dialog_login_switcher = (TextView) var1.findViewById(R.id.sdk_dialog_login_switcher);
        if (SharePrefUtil.getInstance(this.mActivity).getUserEntity() != null) {
            this.mLoginPhone = SharePrefUtil.getInstance(this.mActivity).getUserEntity().getCellphone();
            this.mLoginName = SharePrefUtil.getInstance(this.mActivity).getUserEntity().getUsername();
            this.sdk_dialog_login_clear.setVisibility(0);
        }
        if (this.mLoginPhone == null) {
            this.mLoginPhone = "";
        }
        if (this.mLoginName == null) {
            this.mLoginName = "";
        }
        this.sdk_dialog_login_phone_name.setText(this.mLoginPhone);
        this.errorDrawable = this.getResources().getDrawable(R.drawable.edit_text_error);
        this.errorDrawable.setBounds(0, 0, DensityUtil.dp2px(this.getActivity(), 18.0F), DensityUtil.dp2px(this.getActivity(), 18.0F));
        this.progressBar.setVisibility(8);
    }

    public void onClick(View var1) {
        switch (var1.getId()) {
            case R.id.sdk_dialog_login_clear:
                this.sdk_dialog_login_phone_name.setText("");
                return;
            case R.id.sdk_dialog_login_sms_pwd:
            default:
                return;
            case R.id.sdk_dialog_login_sendvcode:
                this.requstIndentifyCode();
                return;
            case R.id.sdk_dialog_login_button:
                this.login();
                return;
            case R.id.sdk_dialog_login_switcher:
                this.switchLoginMode();
        }
    }

    private void login() {
        switch (this.mLoginType) {
            case 1:
                this.loginWithPhone();
                return;
            case 2:
                this.loginWithAccount();
                return;
            default:
        }
    }

    private void loginFail(String var1) {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(8);
        }

        QiGuoInfo.INSTANCE.getQiGuoCallBack().onFailure(var1);
        ToastUtils.showToast(this.mActivity, var1);
    }

    private void loginWithAccount() {
        String var1 = this.sdk_dialog_login_phone_name.getText().toString();
        final String var2 = this.sdk_dialog_login_sms_pwd.getText().toString();
        if (TextUtils.isEmpty(var1)) {
            ToastUtils.showToast(this.mActivity, "请输入用户名");
        } else if (TextUtils.isEmpty(var2)) {
            ToastUtils.showToast(this.mActivity, "请输入密码");
        } else {
            this.progressBar.setVisibility(0);
            SDKService.getInstance(this.mActivity).login(this.mActivity, var1, var2, "", new ResponseCallBack<LoginEntity>() {
                public void onError(int var1, String var2x, Throwable var3) {
                    LoginFragment.this.loginFail(var2x);
                }

                public void onSuccess(int var1, LoginEntity var2x) {
                    LoginFragment.this.loginSuccess(LoginFragment.this.mActivity, var2x);
                    QxzSharedPrefUtil.putPassword(LoginFragment.this.mActivity, var2);
                    SharePrefUtil.getInstance(LoginFragment.this.mActivity).putUserBinded(false);
                    LoginFragment.this.dismiss();
                }
            });
        }
    }

    private void loginWithPhone() {
        String var1 = this.sdk_dialog_login_phone_name.getText().toString();
        if (TextUtils.isEmpty(var1)) {
            ToastUtils.showToast(this.mActivity, "请填写手机号");
        } else {
            final String var2 = this.sdk_dialog_login_sms_pwd.getText().toString();
            if (TextUtils.isEmpty(var2)) {
                ToastUtils.showToast(this.mActivity, "请填写验证码");
            } else if (!this.isVcode(var2)) {
                ToastUtils.showToast(this.mActivity, "验证码格式不正确");
            } else {
                this.progressBar.setVisibility(0);
                SDKService.getInstance(this.mActivity).login(this.mActivity, var1, "", var2, new ResponseCallBack<LoginEntity>() {
                    public void onError(int var1, String var2x, Throwable var3) {
                        LoginFragment.this.loginFail(var2x);
                    }

                    public void onSuccess(int var1, LoginEntity var2x) {
                        LoginFragment.this.loginSuccess(LoginFragment.this.mActivity, var2x);
                        LoginFragment.this.dismiss();
                        SharePrefUtil.getInstance(LoginFragment.this.mActivity).putUserBinded(true);
                        QxzSharedPrefUtil.putIdentifyCode(LoginFragment.this.mActivity, var2);
                    }
                });
            }
        }
    }

    private void requstIndentifyCode() {
        if (this.mCountDownFinished && this.mLoginType != 2) {
            String var1 = this.sdk_dialog_login_phone_name.getText().toString();
            if (!this.isPhoneNumber(var1)) {
                ToastUtils.showToast(this.mActivity, "手机号格式不正确");
            } else {
                this.mCountDownTimer.start();
                SDKService.getInstance(this.mActivity).sendVcode(var1, "0", new ResponseCallBack<VcodeBean>() {
                    public void onError(int var1, String var2, Throwable var3) {
                        ToastUtils.showToast(LoginFragment.this.mActivity, var2);
                    }

                    public void onSuccess(int var1, VcodeBean var2) {
                        LoginFragment.this.mCountDownFinished = false;
                        ToastUtils.showToast(LoginFragment.this.mActivity, "短信验证码发送成功");
                    }
                });
            }
        }
    }

    public boolean isPhoneNumber(String var1) {
        return Pattern.compile("^((1[0-9][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(var1).matches();
    }

    public boolean isVcode(String var1) {
        return var1 != null && var1.length() == 4 ? Pattern.compile("[0-9]{4}").matcher(var1).matches() : false;
    }

    public void loginSuccess(Activity var1, LoginEntity var2) {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(8);
        }

        if (var2 == null) {
            QiGuoInfo.INSTANCE.getQiGuoCallBack().onFailure("服务器异常");
            ToastUtils.showToast(var1, "登录失败-服务器异常");
        } else {
            UserEntity var3 = var2.getArr();
            App.setUserEntity(var1, var3);
            App.setCornerState(var2.getCornerState());
            ToastUtils.showToast(var1, "登录成功，欢迎用户" + var3.getUsernick() + "进入游戏");
            QiGuoInfo.INSTANCE.setUid(var3.getUid());
            QxzSDKAgent.INSTANCE.initFloatView(var1.getApplication());
            QxzSDKAgent.INSTANCE.showFloatingView();
            QiGuoInfo.INSTANCE.getQiGuoCallBack().onSuccess();
        }
    }

    public void onCancel(DialogInterface var1) {
        super.onCancel(var1);
        QiGuoInfo.INSTANCE.getQiGuoCallBack().onCancel();
    }

    public void onCreate(Bundle var1) {
        super.onCreate(var1);
    }

    public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
        this.getDialog().requestWindowFeature(1);
        this.getDialog().setCanceledOnTouchOutside(false);
        View var4 = var1.inflate(R.layout.sdk_dialog_fragment_login, var2);
        this.initViews(var4);
        this.initEvents();
        this.initCountDownTimer();
        return var4;
    }

    public void switchLoginMode() {
        if (this.mLoginType == 1) {
            this.mLoginType = 2;
        } else {
            this.mLoginType = 1;
        }

        this.sdk_dialog_login_sms_pwd.setText("");
        switch (this.mLoginType) {
            case 1:
                this.sdk_dialog_login_title.setText(this.getString(R.string.qxz_login_title));
                this.sdk_dialog_login_phone_name.setHint(R.string.qxz_login_phonenumber_hint);
                this.sdk_dialog_login_phone_name.setInputType(3);
                this.sdk_dialog_login_sms_pwd.setHint(R.string.qxz_login_indentify_code_hint);
                this.sdk_dialog_login_sms_pwd.setInputType(2);
                this.sdk_dialog_login_switcher.setText(this.getString(R.string.qxz_login_use_password));
                this.sdk_dialog_login_sendvcode.setVisibility(0);
                this.sdk_dialog_login_phone_name.setText(this.mLoginPhone);
                return;
            case 2:
                this.sdk_dialog_login_title.setText(this.getString(R.string.qxz_login_use_password));
                this.sdk_dialog_login_phone_name.setHint(R.string.qxz_login_account_number);
                this.sdk_dialog_login_phone_name.setInputType(1);
                this.sdk_dialog_login_sms_pwd.setHint(R.string.qxz_login_input_password);
                this.sdk_dialog_login_sms_pwd.setInputType(129);
                this.sdk_dialog_login_switcher.setText(this.getString(R.string.qxz_login_dynamic_login));
                this.sdk_dialog_login_sendvcode.setVisibility(8);
                this.sdk_dialog_login_phone_name.setText(this.mLoginName);
                return;
            default:
        }
    }
}
