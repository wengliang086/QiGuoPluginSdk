//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.kding.base.BaseDrawerFragment;
import com.kding.bean.NoticeBean;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import com.kding.utils.ToastUtils;
import com.kding.view.XListView;
import com.kding.view.XListView.IXListViewListener;

import java.util.List;
import java.util.Vector;

public class NotifyFragment extends BaseDrawerFragment implements OnClickListener, IXListViewListener {
    private static final int TYPE_LOADMORE = 1;
    private static final int TYPE_REFRESH = 0;
    private View backBtn;
    private boolean isLoading = false;
    private XListView lv_notify;
    private NotifyAdapter mAdapter;
    private Vector<NoticeBean> mDatas = new Vector();
    private int mNpi = 0;

    public NotifyFragment() {
    }

    private void getData(int var1, final int var2) {
        if (!this.isLoading) {
            this.isLoading = true;
            SDKService.getInstance(this.getActivity()).getNotice(var1, new ResponseCallBack<List<NoticeBean>>() {
                public void onError(int var1, String var2x, Throwable var3) {
                    NotifyFragment.this.isLoading = false;
                    ToastUtils.showToast(NotifyFragment.this.getActivity(), var2x);
                    if (var2 == 0) {
                        NotifyFragment.this.lv_notify.stopRefresh();
                    } else {
                        NotifyFragment.this.lv_notify.stopLoadMore();
                    }
                }

                public void onSuccess(int var1, List<NoticeBean> var2x) {
                    NotifyFragment.this.isLoading = false;
                    NotifyFragment.this.mNpi = var1;
                    if (var2 == 0) {
                        NotifyFragment.this.mDatas.clear();
                    }

                    NotifyFragment.this.mDatas.addAll(var2x);
                    NotifyFragment.this.mAdapter.setDatas(NotifyFragment.this.mDatas);
                    if (NotifyFragment.this.mNpi == -1) {
                        NotifyFragment.this.lv_notify.setPullLoadEnable(false);
                    } else {
                        NotifyFragment.this.lv_notify.setPullLoadEnable(true);
                    }

                    if (var2 == 0) {
                        NotifyFragment.this.lv_notify.stopRefresh();
                    } else {
                        NotifyFragment.this.lv_notify.stopLoadMore();
                    }
                }
            });
        }
    }

    public static NotifyFragment newInstance() {
        Bundle var0 = new Bundle();
        NotifyFragment var1 = new NotifyFragment();
        var1.setArguments(var0);
        return var1;
    }

    public int getLayoutId() {
        return R.layout.fragment_notify;
    }

    public void initEvents() {
        this.backBtn.setOnClickListener(this);
    }

    public void initViews(View var1) {
        this.backBtn = var1.findViewById(R.id.back_btn);
        ((TextView) var1.findViewById(R.id.title)).setText("七果通知");
        this.lv_notify = (XListView) var1.findViewById(R.id.lv_notify);
        this.lv_notify.setPullRefreshEnable(true);
        this.lv_notify.setPullLoadEnable(false);
        this.lv_notify.setAutoLoadEnable(false);
        this.lv_notify.setXListViewListener(this);
        this.mAdapter = new NotifyAdapter(this.getActivity());
        this.lv_notify.setAdapter(this.mAdapter);
        this.getData(0, 0);
    }

    public void onClick(View var1) {
        switch (var1.getId()) {
            case R.id.back_btn:
                this.onBackPress();
                return;
            default:
        }
    }

    public void onLoadMore() {
        if (this.mNpi != -1) {
            this.getData(this.mNpi, 1);
        } else {
            this.lv_notify.setPullLoadEnable(false);
            ToastUtils.showToast(this.getActivity(), "没有更多了");
        }
    }

    public void onRefresh() {
        this.lv_notify.setPullLoadEnable(false);
        this.getData(0, 0);
    }
}
