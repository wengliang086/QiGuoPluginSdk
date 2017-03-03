//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kding.base.BaseDrawerFragment;
import com.kding.bean.CouponBean;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import com.kding.utils.ToastUtils;

import java.util.List;

public class CouponFragment extends BaseDrawerFragment implements OnClickListener {
    private ImageView backBtn;
    private boolean isLoading = false;
    private CouponAdapter mAdapter;
    private ListView rv_coupon;
    private TextView tv_title;

    public CouponFragment() {
    }

    private void getData() {
        if (!this.isLoading) {
            this.isLoading = true;
            SDKService.getInstance(this.getActivity()).getUsefulCouponList(new ResponseCallBack<List<CouponBean>>() {
                public void onError(int var1, String var2, Throwable var3) {
                    CouponFragment.this.isLoading = false;
                    ToastUtils.showToast(CouponFragment.this.getActivity(), var2);
                }

                public void onSuccess(int var1, List<CouponBean> var2) {
                    CouponFragment.this.isLoading = false;
                    CouponFragment.this.mAdapter.setDatas(var2);
                }
            });
        }
    }

    public static CouponFragment newInstance() {
        Bundle var0 = new Bundle();
        CouponFragment var1 = new CouponFragment();
        var1.setArguments(var0);
        return var1;
    }

    public int getLayoutId() {
        return R.layout.fragment_coupon;
    }

    public void initEvents() {
        this.backBtn.setOnClickListener(this);
        this.getData();
    }

    public void initViews(View var1) {
        this.backBtn = (ImageView) var1.findViewById(R.id.back_btn);
        this.backBtn.setImageResource(R.drawable.back);
        this.tv_title = (TextView) var1.findViewById(R.id.title);
        this.tv_title.setText("代金券");
        this.rv_coupon = (ListView) var1.findViewById(R.id.rv_coupon);
        this.mAdapter = new CouponAdapter();
        this.rv_coupon.setAdapter(this.mAdapter);
    }

    public void onClick(View var1) {
        switch (var1.getId()) {
            case R.id.back_btn:
                this.onBackPress();
                return;
            default:
        }
    }
}
