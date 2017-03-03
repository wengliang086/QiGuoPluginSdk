//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.kding.bean.GameGiftBean.GiftListBean;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import com.kding.utils.CopyUtil;
import com.kding.utils.ToastUtils;
import com.kding.view.CircleProgressBar;
import java.util.ArrayList;
import java.util.List;

public class GiftAdapter extends BaseAdapter {
  private List<GiftListBean> datas = new ArrayList();
  private Context mContext;

  public GiftAdapter(Context var1) {
    this.mContext = var1;
  }

  private void getGrab(String var1) {
    SDKService.getInstance(this.mContext).getGrab(var1, new ResponseCallBack<String>() {
      public void onError(int var1, String var2, Throwable var3) {
        ToastUtils.showToast(GiftAdapter.this.mContext, var2);
      }

      public void onSuccess(int var1, String var2) {
        if(TextUtils.isEmpty(var2)) {
          ToastUtils.showToast(GiftAdapter.this.mContext, "领取失败");
        } else {
          CopyUtil.copy(var2, GiftAdapter.this.mContext);
          ToastUtils.showToast(GiftAdapter.this.mContext, "礼包码" + var2 + "已复制至剪贴板");
        }
      }
    });
  }

  private void getOtherGrab(String var1) {
    SDKService.getInstance(this.mContext).getOtherGrab(var1, new ResponseCallBack<String>() {
      public void onError(int var1, String var2, Throwable var3) {
        ToastUtils.showToast(GiftAdapter.this.mContext, var2);
      }

      public void onSuccess(int var1, String var2) {
        if(TextUtils.isEmpty(var2)) {
          ToastUtils.showToast(GiftAdapter.this.mContext, "淘号失败");
        } else {
          CopyUtil.copy(var2, GiftAdapter.this.mContext);
          ToastUtils.showToast(GiftAdapter.this.mContext, "礼包码" + var2 + "已复制至剪贴板");
        }
      }
    });
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
    GiftAdapter.ViewHolder var5;
    if(var2 == null) {
      var2 = LayoutInflater.from(this.mContext).inflate(2130903057, (ViewGroup)null);
      var5 = new GiftAdapter.ViewHolder(var2);
      var2.setTag(var5);
    } else {
      var5 = (GiftAdapter.ViewHolder)var2.getTag();
    }

    final GiftListBean var4 = (GiftListBean)this.datas.get(var1);
    var5.tv_name.setText(var4.getGrab_name());
    var5.tv_info.setText("内容：" + var4.getGrab_info());
    var5.progressBar.setMax(var4.getGrab_total());
    var5.progressBar.setProgress(var4.getGrab_total() - var4.getGrab_remain());
    if(var4.isGrab_ketao()) {
      var5.progressBar.setProgressTextFormatPattern("淘号");
      var5.progressBar.setProgressTextColor(Color.parseColor("#7AC960"));
      var5.progressBar.setProgressStartColor(Color.parseColor("#7AC960"));
      var5.progressBar.setProgressEndColor(Color.parseColor("#7AC960"));
    } else {
      var5.progressBar.setProgressTextFormatPattern("领取");
      var5.progressBar.setProgressTextColor(Color.parseColor("#FFB527"));
      var5.progressBar.setProgressStartColor(Color.parseColor("#FFB527"));
      var5.progressBar.setProgressEndColor(Color.parseColor("#FFB527"));
    }

    var5.progressBar.setOnClickListener(new OnClickListener() {
      public void onClick(View var1) {
        if(var4.isGrab_ketao()) {
          GiftAdapter.this.getOtherGrab(var4.getGrab_id());
        } else {
          GiftAdapter.this.getGrab(var4.getGrab_id());
        }
      }
    });
    return var2;
  }

  public void setDatas(List<GiftListBean> var1) {
    this.datas.clear();
    this.datas.addAll(var1);
    this.notifyDataSetChanged();
  }

  class ViewHolder {
    private CircleProgressBar progressBar;
    private TextView tv_info;
    private TextView tv_name;

    public ViewHolder(View var2) {
      this.tv_name = (TextView)var2.findViewById(2131230747);
      this.tv_info = (TextView)var2.findViewById(2131230764);
      this.progressBar = (CircleProgressBar)var2.findViewById(2131230765);
    }
  }
}
