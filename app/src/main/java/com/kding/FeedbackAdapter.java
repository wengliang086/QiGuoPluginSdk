//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.kding.FeedbackDetailActivity;
import com.kding.bean.IssueListBean.QuestionArrBean;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import java.util.Vector;

public class FeedbackAdapter extends BaseAdapter {
  private Vector<QuestionArrBean> datas = new Vector();
  private Context mContext;

  public FeedbackAdapter(Context var1) {
    this.mContext = var1;
  }

  private void itemOnClick(QuestionArrBean var1) {
    this.mContext.startActivity(FeedbackDetailActivity.getIntent(this.mContext, var1.getQid()));
    SDKService.getInstance(this.mContext).itemClick("2", var1.getQid(), new ResponseCallBack<String>() {
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
    FeedbackAdapter.ViewHolder var5;
    if(var2 == null) {
      var2 = LayoutInflater.from(var3.getContext()).inflate(2130903053, (ViewGroup)null);
      var5 = new FeedbackAdapter.ViewHolder(var2);
      var2.setTag(var5);
    } else {
      var5 = (FeedbackAdapter.ViewHolder)var2.getTag();
    }

    final QuestionArrBean var4 = (QuestionArrBean)this.datas.get(var1);
    if(var4.getQ_state() == 1) {
      var5.tv_issue_state.setText("已回复");
      var5.tv_issue_state.setTextColor(Color.parseColor("#93FF99"));
    } else if(var4.getQ_state() == 0) {
      var5.tv_issue_state.setText("待处理");
      var5.tv_issue_state.setTextColor(Color.parseColor("#FFB527"));
    } else {
      var5.tv_issue_state.setText("已解决");
      var5.tv_issue_state.setTextColor(Color.parseColor("#93FF99"));
    }

    var5.tv_latest_ask.setText(var4.getQ_title());
    var5.tv_latest_time.setText(var4.getQ_time());
    var2.setOnClickListener(new OnClickListener() {
      public void onClick(View var1) {
        FeedbackAdapter.this.itemOnClick(var4);
      }
    });
    return var2;
  }

  public void setDatas(Vector<QuestionArrBean> var1) {
    this.datas.clear();
    this.datas.addAll(var1);
    this.notifyDataSetChanged();
  }

  class ViewHolder {
    private TextView tv_issue_state;
    private TextView tv_latest_ask;
    private TextView tv_latest_time;

    public ViewHolder(View var2) {
      this.tv_latest_ask = (TextView)var2.findViewById(2131230753);
      this.tv_latest_time = (TextView)var2.findViewById(2131230754);
      this.tv_issue_state = (TextView)var2.findViewById(2131230752);
    }
  }
}
