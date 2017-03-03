//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import com.kding.utils.DensityUtil;
import com.kding.utils.ToastUtils;

public class ModifyPwActivity extends Activity implements OnClickListener {
  private ImageView btnBack;
  private String confirmPwd;
  private Drawable errorDrawable;
  private EditText et_confirm_pwd;
  private EditText et_new_pwd;
  private EditText et_old_pwd;
  private boolean isSubmiting = false;
  private String newPwd;
  private String oldPwd;
  private TextView submitBtn;
  private TextView tv_title;

  public ModifyPwActivity() {
  }

  private boolean dataCheck() {
    this.oldPwd = this.et_old_pwd.getText().toString();
    this.newPwd = this.et_new_pwd.getText().toString();
    this.confirmPwd = this.et_confirm_pwd.getText().toString();
    if(TextUtils.isEmpty(this.oldPwd)) {
      this.et_old_pwd.setError(Html.fromHtml("<font color=\'red\'>请输入原密码</font>"), this.errorDrawable);
      return false;
    } else if(TextUtils.isEmpty(this.newPwd)) {
      this.et_new_pwd.setError(Html.fromHtml("<font color=\'red\'>请输入新密码</font>"), this.errorDrawable);
      return false;
    } else if(this.newPwd.length() >= 8 && this.newPwd.length() <= 12) {
      if(!TextUtils.equals(this.newPwd, this.confirmPwd)) {
        this.et_confirm_pwd.setError(Html.fromHtml("<font color=\'red\'>两次输入密码不一致</font>"), this.errorDrawable);
        return false;
      } else {
        return true;
      }
    } else {
      this.et_new_pwd.setError(Html.fromHtml("<font color=\'red\'>密码应该为8到12位</font>"), this.errorDrawable);
      return false;
    }
  }

  public static Intent getIntent(Context var0) {
    return new Intent(var0, ModifyPwActivity.class);
  }

  private void initEvents() {
    this.btnBack.setOnClickListener(this);
    this.submitBtn.setOnClickListener(this);
    this.et_old_pwd.addTextChangedListener(new TextWatcher() {
      public void afterTextChanged(Editable var1) {
      }

      public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
        ModifyPwActivity.this.et_old_pwd.setError((CharSequence)null);
      }

      public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
      }
    });
    this.et_new_pwd.addTextChangedListener(new TextWatcher() {
      public void afterTextChanged(Editable var1) {
      }

      public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
        ModifyPwActivity.this.et_new_pwd.setError((CharSequence)null);
        ModifyPwActivity.this.et_confirm_pwd.setError((CharSequence)null);
      }

      public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
      }
    });
    this.et_confirm_pwd.addTextChangedListener(new TextWatcher() {
      public void afterTextChanged(Editable var1) {
      }

      public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {
        ModifyPwActivity.this.et_confirm_pwd.setError((CharSequence)null);
      }

      public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {
      }
    });
  }

  private void initViews() {
    this.btnBack = (ImageView)this.findViewById(2131230817);
    this.btnBack.setImageResource(2130837504);
    this.tv_title = (TextView)this.findViewById(2131230818);
    this.tv_title.setText("修改密码");
    this.et_old_pwd = (EditText)this.findViewById(2131230730);
    this.et_new_pwd = (EditText)this.findViewById(2131230731);
    this.et_confirm_pwd = (EditText)this.findViewById(2131230732);
    this.submitBtn = (TextView)this.findViewById(2131230733);
    this.submitBtn.setBackgroundResource(2130837540);
    this.errorDrawable = this.getResources().getDrawable(2130837507);
    this.errorDrawable.setBounds(0, 0, DensityUtil.dp2px(this, 18.0F), DensityUtil.dp2px(this, 18.0F));
  }

  private void modifyPw() {
    if(this.dataCheck() && !this.isSubmiting) {
      this.isSubmiting = true;
      SDKService.getInstance(this).modifyPw(this.oldPwd, this.newPwd, new ResponseCallBack<String>() {
        public void onError(int var1, String var2, Throwable var3) {
          ModifyPwActivity.this.isSubmiting = false;
          ToastUtils.showToast(ModifyPwActivity.this, var2);
        }

        public void onSuccess(int var1, String var2) {
          ModifyPwActivity.this.isSubmiting = false;
          ToastUtils.showToast(ModifyPwActivity.this, "密码修改成功");
          ModifyPwActivity.this.finish();
        }
      });
    }
  }

  public void onClick(View var1) {
    if(this.btnBack == var1) {
      this.finish();
    } else if(this.submitBtn == var1) {
      this.modifyPw();
      return;
    }

  }

  protected void onCreate(Bundle var1) {
    super.onCreate(var1);
    this.setContentView(2130903043);
    this.initViews();
    this.initEvents();
  }
}
