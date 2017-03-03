//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.kding.base.BaseDialogFragment;
import com.kding.net.App;
import com.kding.utils.AppCallUtil;

public class GotoAppFragment extends BaseDialogFragment implements OnClickListener {
  private TextView tv_cancel;
  private TextView tv_content;
  private TextView tv_open;

  public GotoAppFragment() {
  }

  private void initEvents() {
    this.tv_cancel.setOnClickListener(this);
    this.tv_open.setOnClickListener(this);
  }

  private void initViews(View var1) {
    this.tv_content = (TextView)var1.findViewById(2131230736);
    this.tv_cancel = (TextView)var1.findViewById(2131230766);
    this.tv_open = (TextView)var1.findViewById(2131230767);
  }

  public void onActivityResult(int var1, int var2) {
    if('밁' == var1) {
      if(-1 != var2) {
        this.tv_content.setText("您本次充值尚未成功！");
        this.tv_open.setText("再试一次");
        return;
      }

      this.dismiss();
    }

  }

  public void onClick(View var1) {
    switch(var1.getId()) {
      case 2131230766:
        this.dismiss();
        return;
      case 2131230767:
        AppCallUtil.goToAppRecharge(this.mActivity, App.getUserEntity().getUid());
        return;
      default:
    }
  }

  public void onCreate(Bundle var1) {
    super.onCreate(var1);
  }

  public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
    this.getDialog().requestWindowFeature(1);
    this.getDialog().setCanceledOnTouchOutside(false);
    View var4 = var1.inflate(2130903058, var2);
    this.initViews(var4);
    this.initEvents();
    return var4;
  }
}
