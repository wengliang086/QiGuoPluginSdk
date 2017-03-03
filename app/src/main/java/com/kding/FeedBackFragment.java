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
import com.kding.FeedbackAdapter;
import com.kding.base.BaseDrawerFragment;
import com.kding.bean.IssueListBean;
import com.kding.bean.IssueListBean.QuestionArrBean;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import com.kding.utils.ToastUtils;
import com.kding.view.XListView;
import com.kding.view.XListView.IXListViewListener;
import java.util.Vector;

public class FeedBackFragment extends BaseDrawerFragment implements OnClickListener, IXListViewListener {
  private static final int TYPE_LOADMORE = 1;
  private static final int TYPE_REFRESH = 0;
  private View backBtn;
  private boolean isLoading = false;
  private XListView lv_feedback;
  private FeedbackAdapter mAdapter;
  private Vector<QuestionArrBean> mDatas = new Vector();
  private int mNpi = 0;

  public FeedBackFragment() {
  }

  private void getData(int var1, final int var2) {
    if(!this.isLoading) {
      this.isLoading = true;
      SDKService.getInstance(this.getActivity()).getIssueList(var1, new ResponseCallBack<IssueListBean>() {
        public void onError(int var1, String var2x, Throwable var3) {
          FeedBackFragment.this.isLoading = false;
          ToastUtils.showToast(FeedBackFragment.this.getActivity(), var2x);
          if(var2 == 0) {
            FeedBackFragment.this.lv_feedback.stopRefresh();
          } else {
            FeedBackFragment.this.lv_feedback.stopLoadMore();
          }
        }

        public void onSuccess(int var1, IssueListBean var2x) {
          FeedBackFragment.this.isLoading = false;
          FeedBackFragment.this.mNpi = var1;
          if(var2 == 0) {
            FeedBackFragment.this.mDatas.clear();
          }

          FeedBackFragment.this.mDatas.addAll(var2x.getQuestion_arr());
          FeedBackFragment.this.mAdapter.setDatas(FeedBackFragment.this.mDatas);
          if(FeedBackFragment.this.mNpi == -1) {
            FeedBackFragment.this.lv_feedback.setPullLoadEnable(false);
          } else {
            FeedBackFragment.this.lv_feedback.setPullLoadEnable(true);
          }

          if(var2 == 0) {
            FeedBackFragment.this.lv_feedback.stopRefresh();
          } else {
            FeedBackFragment.this.lv_feedback.stopLoadMore();
          }
        }
      });
    }
  }

  private void initHeader() {
    View var1 = LayoutInflater.from(this.mActivity).inflate(2130903054, (ViewGroup)null);
    this.lv_feedback.addHeaderView(var1);
  }

  public static FeedBackFragment newInstance() {
    Bundle var0 = new Bundle();
    FeedBackFragment var1 = new FeedBackFragment();
    var1.setArguments(var0);
    return var1;
  }

  public int getLayoutId() {
    return 2130903052;
  }

  public void initEvents() {
    this.backBtn.setOnClickListener(this);
  }

  public void initViews(View var1) {
    this.backBtn = var1.findViewById(2131230817);
    ((TextView)var1.findViewById(2131230818)).setText("问题反馈");
    this.lv_feedback = (XListView)var1.findViewById(2131230751);
    this.lv_feedback.setPullRefreshEnable(true);
    this.lv_feedback.setPullLoadEnable(false);
    this.lv_feedback.setAutoLoadEnable(false);
    this.lv_feedback.setXListViewListener(this);
    this.mAdapter = new FeedbackAdapter(this.getActivity());
    this.lv_feedback.setAdapter(this.mAdapter);
    this.initHeader();
    this.getData(0, 0);
  }

  public void onClick(View var1) {
    switch(var1.getId()) {
      case 2131230817:
        this.onBackPress();
        return;
      default:
    }
  }

  public void onLoadMore() {
    if(this.mNpi != -1) {
      this.getData(this.mNpi, 1);
    } else {
      this.lv_feedback.setPullLoadEnable(false);
      ToastUtils.showToast(this.getActivity(), "没有更多了");
    }
  }

  public void onRefresh() {
    this.lv_feedback.setPullLoadEnable(false);
    this.getData(0, 0);
  }
}
