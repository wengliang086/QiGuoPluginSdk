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
import com.kding.utils.CopyUtil;
import com.kding.utils.ToastUtils;

public class GetGiftFragment extends BaseDialogFragment implements OnClickListener {
  private String mGrabKey;
  private TextView tv_copy;
  private TextView tv_key;

  public GetGiftFragment() {
  }

  private void initEvents() {
    this.tv_copy.setOnClickListener(this);
  }

  private void initViews(View var1) {
    this.tv_key = (TextView)var1.findViewById(2131230755);
    this.tv_copy = (TextView)var1.findViewById(2131230756);
    this.tv_key.setText("礼包码：" + this.mGrabKey);
  }

  public void onClick(View var1) {
    switch(var1.getId()) {
      case 2131230756:
        CopyUtil.copy(this.mGrabKey, this.mActivity);
        ToastUtils.showToast(this.mActivity, "已复制至剪贴板");
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
    View var4 = var1.inflate(2130903055, var2);
    this.initViews(var4);
    this.initEvents();
    return var4;
  }

  public void setGrabKey(String var1) {
    this.mGrabKey = var1;
  }
}
