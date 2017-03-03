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
import com.kding.WebActivity;
import com.kding.bean.NoticeBean;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import java.util.Vector;

public class NotifyAdapter extends BaseAdapter {
  private Vector<NoticeBean> datas = new Vector();
  private Context mContext;

  public NotifyAdapter(Context var1) {
    this.mContext = var1;
  }

  private void itemOnClick(NoticeBean var1) {
    this.mContext.startActivity(WebActivity.getIntent(this.mContext, "通知详情", var1.getMsg_url()));
    SDKService.getInstance(this.mContext).itemClick("1", var1.getMsg_id(), new ResponseCallBack<String>() {
      public void onError(int var1, String var2, Throwable var3) {
      }

      public void onSuccess(int var1, String var2) {
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
    final NotifyAdapter.ViewHolder var5;
    if(var2 == null) {
      var2 = LayoutInflater.from(var3.getContext()).inflate(2130903065, (ViewGroup)null);
      var5 = new NotifyAdapter.ViewHolder(var2);
      var2.setTag(var5);
    } else {
      var5 = (NotifyAdapter.ViewHolder)var2.getTag();
    }

    final NoticeBean var4 = (NoticeBean)this.datas.get(var1);
    if(var4.isIs_read()) {
      var5.tv_notify_is_read.setText("已读");
      var5.tv_notify_is_read.setBackgroundDrawable(this.mContext.getResources().getDrawable(2130837525));
    } else {
      var5.tv_notify_is_read.setText("未读");
      var5.tv_notify_is_read.setBackgroundDrawable(this.mContext.getResources().getDrawable(2130837524));
    }

    var5.tv_notify_title.setText(var4.getMsg_title());
    var5.tv_notify_time.setText(var4.getMsg_time());
    var2.setOnClickListener(new OnClickListener() {
      public void onClick(View var1) {
        var5.tv_notify_is_read.setText("已读");
        var5.tv_notify_is_read.setBackgroundDrawable(NotifyAdapter.this.mContext.getResources().getDrawable(2130837525));
        NotifyAdapter.this.itemOnClick(var4);
      }
    });
    return var2;
  }

  public void setDatas(Vector<NoticeBean> var1) {
    this.datas.clear();
    this.datas.addAll(var1);
    this.notifyDataSetChanged();
  }

  class ViewHolder {
    private TextView tv_notify_is_read;
    private TextView tv_notify_time;
    private TextView tv_notify_title;

    public ViewHolder(View var2) {
      this.tv_notify_is_read = (TextView)var2.findViewById(2131230793);
      this.tv_notify_title = (TextView)var2.findViewById(2131230794);
      this.tv_notify_time = (TextView)var2.findViewById(2131230795);
    }
  }
}
