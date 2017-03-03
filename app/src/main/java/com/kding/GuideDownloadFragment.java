//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.kding.base.BaseDialogFragment;

public class GuideDownloadFragment extends BaseDialogFragment implements OnClickListener {
  private TextView tv_cancel;
  private TextView tv_content;
  private TextView tv_open;

  public GuideDownloadFragment() {
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

  public void onClick(View var1) {
    switch(var1.getId()) {
      case 2131230766:
        this.dismiss();
        return;
      case 2131230767:
        Intent var2 = new Intent("android.intent.action.VIEW", Uri.parse("http://static.7xz.com/files/soft/corp/qgyx_0622092000.apk"));
        this.mActivity.startActivity(var2);
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
    View var4 = var1.inflate(2130903059, var2);
    this.initViews(var4);
    this.initEvents();
    return var4;
  }
}
