//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.kding.FeedbackDetailAdpater;
import com.kding.api.QiGuoApi;
import com.kding.bean.IssueDetailBean;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import com.kding.utils.AppCallUtil;
import com.kding.utils.ToastUtils;

public class FeedbackDetailActivity extends Activity implements OnClickListener {
  private static final String QID = "QID";
  private ImageView backBtn;
  private boolean isLoading = false;
  private FeedbackDetailAdpater mAdapter;
  private IssueDetailBean mBean;
  private String mQid;
  private ListView rv_feedback_detail;
  private TextView tv_go_to_app;
  private TextView tv_title;

  public FeedbackDetailActivity() {
  }

  private void getData() {
    if(!this.isLoading) {
      this.isLoading = true;
      SDKService.getInstance(this).getIssueDetail(this.mQid, new ResponseCallBack<IssueDetailBean>() {
        public void onError(int var1, String var2, Throwable var3) {
          FeedbackDetailActivity.this.isLoading = false;
          ToastUtils.showToast(FeedbackDetailActivity.this, var2);
        }

        public void onSuccess(int var1, IssueDetailBean var2) {
          FeedbackDetailActivity.this.isLoading = false;
          FeedbackDetailActivity.this.mBean = var2;
          FeedbackDetailActivity.this.updateUI();
        }
      });
    }
  }

  public static Intent getIntent(Context var0, String var1) {
    Intent var2 = new Intent(var0, FeedbackDetailActivity.class);
    var2.putExtra("QID", var1);
    return var2;
  }

  private void initEvents() {
    this.backBtn.setOnClickListener(this);
    this.tv_go_to_app.setOnClickListener(this);
  }

  private void initViews() {
    this.mQid = this.getIntent().getStringExtra("QID");
    this.backBtn = (ImageView)this.findViewById(2131230817);
    this.backBtn.setImageResource(2130837504);
    this.tv_title = (TextView)this.findViewById(2131230818);
    this.tv_title.setText("反馈详情");
    this.rv_feedback_detail = (ListView)this.findViewById(2131230720);
    this.mAdapter = new FeedbackDetailAdpater(this);
    this.rv_feedback_detail.setAdapter(this.mAdapter);
    this.tv_go_to_app = (TextView)this.findViewById(2131230721);
  }

  private void updateUI() {
    if(this.mBean != null) {
      this.mAdapter.setDatas(this.mBean.getQuestion_type(), this.mBean.getQuestion_content_arr());
    }
  }

  protected void onActivityResult(int var1, int var2, Intent var3) {
    if('밂' == var1 && var2 == -1) {
      this.getData();
    }

  }

  public void onClick(View var1) {
    if(this.backBtn == var1) {
      this.finish();
    } else if(this.tv_go_to_app == var1) {
      AppCallUtil.goToAppIssue(this, QiGuoApi.INSTANCE.getUserId(), this.mQid);
      return;
    }

  }

  protected void onCreate(Bundle var1) {
    super.onCreate(var1);
    this.setContentView(2130903040);
    this.initViews();
    this.initEvents();
    this.getData();
  }
}
