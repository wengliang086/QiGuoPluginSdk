//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.kding.bean.CouponBean;
import java.util.ArrayList;
import java.util.List;

public class CouponAdapter extends BaseAdapter {
  private List<CouponBean> datas = new ArrayList();

  public CouponAdapter() {
  }

  public int getCount() {
    return this.datas.size();
  }

  public Object getItem(int var1) {
    return this.datas.get(var1);
  }

  public long getItemId(int var1) {
    return (long)var1;
  }

  public View getView(int var1, View var2, ViewGroup var3) {
    CouponAdapter.ItemHolder var5;
    if(var2 == null) {
      var2 = LayoutInflater.from(var3.getContext()).inflate(2130903051, var3, false);
      var5 = new CouponAdapter.ItemHolder(var2);
      var2.setTag(var5);
    } else {
      var5 = (CouponAdapter.ItemHolder)var2.getTag();
    }

    CouponBean var4 = (CouponBean)this.datas.get(var1);
    var5.tv_price.setText("￥" + var4.getPrice());
    var5.tv_name.setText(var4.getName());
    var5.tv_des.setText(var4.getDes());
    var5.tv_deadline.setText(var4.getDeadline());
    return var2;
  }

  public void setDatas(List<CouponBean> var1) {
    this.datas.clear();
    this.datas.addAll(var1);
    this.notifyDataSetChanged();
  }

  class ItemHolder {
    private TextView tv_deadline;
    private TextView tv_des;
    private TextView tv_name;
    private TextView tv_price;

    public ItemHolder(View var2) {
      this.tv_price = (TextView)var2.findViewById(2131230748);
      this.tv_name = (TextView)var2.findViewById(2131230747);
      this.tv_des = (TextView)var2.findViewById(2131230749);
      this.tv_deadline = (TextView)var2.findViewById(2131230750);
    }
  }
}
