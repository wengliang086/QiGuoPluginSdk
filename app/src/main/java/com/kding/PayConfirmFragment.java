//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.kding.utils.PayConfirmHelper;
import com.kding.utils.SystemUtils;

public class PayConfirmFragment extends DialogFragment implements OnClickListener {
  public Activity mActivity;
  private int mCoinCost = 0;
  private int mRequestCoinConfirm;
  public PayConfirmHelper payConfirmHelper;
  private TextView tv_cancel;
  private TextView tv_content;
  private TextView tv_open;

  public PayConfirmFragment() {
  }

  private void initEvents() {
    this.tv_cancel.setOnClickListener(this);
    this.tv_open.setOnClickListener(this);
  }

  private void initViews(View var1) {
    this.tv_content = (TextView)var1.findViewById(2131230736);
    this.tv_cancel = (TextView)var1.findViewById(2131230766);
    this.tv_open = (TextView)var1.findViewById(2131230767);
    if(this.mCoinCost > 0) {
      this.tv_content.setText("本次支付将支付" + this.mCoinCost + "平台币\n请确认支付");
    }

  }

  public void onAttach(Activity var1) {
    this.mActivity = var1;
    super.onAttach(var1);

    try {
      this.payConfirmHelper = (PayConfirmHelper)var1;
    } catch (ClassCastException var2) {
      ;
    }
  }

  public void onClick(View var1) {
    switch(var1.getId()) {
      case 2131230766:
        this.payConfirmHelper.onCancel(this.mCoinCost, this.mRequestCoinConfirm);
        this.dismiss();
        return;
      case 2131230767:
        this.payConfirmHelper.onSuc(this.mCoinCost, this.mRequestCoinConfirm);
        this.dismiss();
        return;
      default:
    }
  }

  public void onCreate(Bundle var1) {
    super.onCreate(var1);
    this.setStyle(0, 2131165186);
  }

  public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
    this.getDialog().requestWindowFeature(1);
    this.getDialog().setCanceledOnTouchOutside(false);
    View var4 = var1.inflate(2130903067, var2);
    this.initViews(var4);
    this.initEvents();
    return var4;
  }

  public void onResume() {
    super.onResume();
    this.setWindow();
  }

  public void setCoinCost(int var1, int var2) {
    this.mRequestCoinConfirm = var2;
    this.mCoinCost = var1;
  }

  protected void setDialogSize(Display var1, LayoutParams var2) {
    var2.gravity = 17;
    if(SystemUtils.isLandscape(this.getActivity())) {
      var2.width = (int)((double)var1.getWidth() * 0.6D);
    } else {
      var2.width = (int)((double)var1.getWidth() * 0.9D);
    }
  }

  public void setWindow() {
    Display var1 = this.getActivity().getWindowManager().getDefaultDisplay();
    LayoutParams var2 = this.getDialog().getWindow().getAttributes();
    this.setDialogSize(var1, var2);
    this.getDialog().getWindow().setAttributes(var2);
  }
}
