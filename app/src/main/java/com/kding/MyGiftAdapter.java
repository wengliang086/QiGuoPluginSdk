//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.kding.bean.PersonalGiftBean;
import com.kding.utils.CopyUtil;
import com.kding.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;

public class MyGiftAdapter extends BaseAdapter {
  private List<PersonalGiftBean> datas = new ArrayList();
  private Context mContext;

  public MyGiftAdapter(Context var1) {
    this.mContext = var1;
  }

  public int getCount() {
    return this.datas.size();
  }

  public Object getItem(int var1) {
    return null;
  }

  public long getItemId(int var1) {
    return 0L;
  }

  public View getView(int var1, View var2, ViewGroup var3) {
    MyGiftAdapter.ViewHolder var5;
    if(var2 == null) {
      var2 = LayoutInflater.from(this.mContext).inflate(2130903063, (ViewGroup)null, false);
      var5 = new MyGiftAdapter.ViewHolder(var2);
      var2.setTag(var5);
    } else {
      var5 = (MyGiftAdapter.ViewHolder)var2.getTag();
    }

    final PersonalGiftBean var4 = (PersonalGiftBean)this.datas.get(var1);
    var5.tv_name.setText(var4.getGrab_name());
    var5.tv_key.setText(var4.getGrab_key());
    var5.tv_des.setText(var4.getGrab_desc());
    var5.tv_copy.setOnClickListener(new OnClickListener() {
      public void onClick(View var1) {
        CopyUtil.copy(var4.getGrab_key(), MyGiftAdapter.this.mContext);
        ToastUtils.showToast(MyGiftAdapter.this.mContext, "已复制至剪贴板");
      }
    });
    return var2;
  }

  public void setDatas(List<PersonalGiftBean> var1) {
    this.datas.clear();
    this.datas.addAll(var1);
    this.notifyDataSetChanged();
  }

  class ViewHolder {
    private TextView tv_copy;
    private TextView tv_des;
    private TextView tv_key;
    private TextView tv_name;

    public ViewHolder(View var2) {
      this.tv_name = (TextView)var2.findViewById(2131230747);
      this.tv_key = (TextView)var2.findViewById(2131230755);
      this.tv_des = (TextView)var2.findViewById(2131230749);
      this.tv_copy = (TextView)var2.findViewById(2131230756);
    }
  }
}
