//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.kding.base.BaseDrawerFragment;
import com.kding.bean.ItemStatusBean;
import com.kding.bean.UserCoinBean;
import com.kding.net.App;
import com.kding.net.ResponseCallBack;
import com.kding.net.SDKService;
import com.kding.utils.CircleTransform;
import com.kding.utils.QxzSharedPrefUtil;
import com.kding.view.SlideSwitch;
import com.kding.view.SlideSwitch.SlideListener;
import com.squareup.picasso.Picasso;

public class HomeFragment extends BaseDrawerFragment implements OnClickListener {
    private SlideSwitch autoLogin;
    private TextView coinNum;
    private TextView couponBtn;
    private TextView feedBackBtn;
    private View feedBackBtnPoint;
    private TextView giftBtn;
    private View giftBtnPoint;
    private ImageView iconImg;
    private TextView mLevel;
    private TextView motifyBtn;
    private TextView nickName;
    private TextView notifyBtn;
    private View notifyBtnPoint;
    private TextView rechargeBtn;
    private TextView settingBtn;
    private TextView useDay;

    public HomeFragment() {
    }

    private void getCoin() {
        SDKService.getInstance(this.mActivity).getUserCoin(new ResponseCallBack<UserCoinBean>() {
            public void onError(int var1, String var2, Throwable var3) {
            }

            public void onSuccess(int var1, UserCoinBean var2) {
                if (var2 != null) {
                    HomeFragment.this.coinNum.setText(var2.getUsercoin() + "");
                }

            }
        });
    }

    private void getPoint() {
        SDKService.getInstance(this.mActivity).getItemStatus(new ResponseCallBack<ItemStatusBean>() {
            public void onError(int var1, String var2, Throwable var3) {
            }

            public void onSuccess(int var1, ItemStatusBean var2) {
                if (var2 != null) {
                    if (var2.getNotice_no_read() == 0) {
                        HomeFragment.this.notifyBtnPoint.setVisibility(8);
                    } else {
                        HomeFragment.this.notifyBtnPoint.setVisibility(0);
                    }

                    if (var2.getNew_gift() == 0) {
                        HomeFragment.this.giftBtnPoint.setVisibility(8);
                    } else {
                        HomeFragment.this.giftBtnPoint.setVisibility(0);
                    }

                    if (var2.getIssue_new_response() != 0) {
                        HomeFragment.this.feedBackBtnPoint.setVisibility(0);
                        return;
                    }

                    HomeFragment.this.feedBackBtnPoint.setVisibility(8);
                }

            }
        });
    }

    private void giftClick() {
        SDKService.getInstance(this.mActivity).itemClick("0", "", new ResponseCallBack<String>() {
            public void onError(int var1, String var2, Throwable var3) {
            }

            public void onSuccess(int var1, String var2) {
            }
        });
    }

    public static HomeFragment newInstance() {
        Bundle var0 = new Bundle();
        HomeFragment var1 = new HomeFragment();
        var1.setArguments(var0);
        return var1;
    }

    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    public void initEvents() {
        this.rechargeBtn.setOnClickListener(this);
        this.notifyBtn.setOnClickListener(this);
        this.giftBtn.setOnClickListener(this);
        this.couponBtn.setOnClickListener(this);
        this.settingBtn.setOnClickListener(this);
        this.motifyBtn.setOnClickListener(this);
        this.feedBackBtn.setOnClickListener(this);
        this.autoLogin.setSlideListener(new SlideListener() {
            public void close() {
                QxzSharedPrefUtil.putBoolean(HomeFragment.this.mActivity, "autologin", Boolean.valueOf(false));
            }

            public void open() {
                QxzSharedPrefUtil.putBoolean(HomeFragment.this.mActivity, "autologin", Boolean.valueOf(true));
            }
        });
    }

    public void initViews(View var1) {
        this.iconImg = (ImageView) var1.findViewById(R.id.icon_img);
        this.nickName = (TextView) var1.findViewById(R.id.nick_name);
        this.mLevel = (TextView) var1.findViewById(R.id.level);
        this.useDay = (TextView) var1.findViewById(R.id.use_day);
        this.coinNum = (TextView) var1.findViewById(R.id.coin_num);
        this.rechargeBtn = (TextView) var1.findViewById(R.id.recharge_btn);
        this.notifyBtn = (TextView) var1.findViewById(R.id.notify_btn);
        this.giftBtn = (TextView) var1.findViewById(R.id.gift_btn);
        this.couponBtn = (TextView) var1.findViewById(R.id.coupon_btn);
        this.settingBtn = (TextView) var1.findViewById(R.id.setting_btn);
        this.motifyBtn = (TextView) var1.findViewById(R.id.motify_btn);
        this.feedBackBtn = (TextView) var1.findViewById(R.id.feed_back_btn);
        this.notifyBtnPoint = var1.findViewById(R.id.notify_btn_point);
        this.giftBtnPoint = var1.findViewById(R.id.gift_btn_point);
        this.feedBackBtnPoint = var1.findViewById(R.id.feed_back_btn_point);
        this.autoLogin = (SlideSwitch) var1.findViewById(R.id.qxz_menu_autologin);
        Picasso.with(this.mActivity).load(App.getUserEntity().getAvatar()).transform(new CircleTransform()).into(this.iconImg);
        this.nickName.setText(App.getUserEntity().getUsernick());
        this.mLevel.setText("LV." + App.getUserEntity().getLevel());
        this.useDay.setText("您使用七果已有" + App.getUserEntity().getDays() + "天");
        if (QxzSharedPrefUtil.getBoolean(this.mActivity, "autologin", Boolean.valueOf(true)).booleanValue()) {
            this.autoLogin.setState(true);
        } else {
            this.autoLogin.setState(false);
        }
    }

    public void onClick(View var1) {
        switch (var1.getId()) {
            case R.id.setting_btn:
                this.mFragmentHelper.startFragment(this, 4);
                return;
            case R.id.motify_btn:
                this.getActivity().startActivity(ModifyPwActivity.getIntent(this.getActivity()));
                return;
            case R.id.feed_back_btn:
                this.mFragmentHelper.startFragment(this, 6);
                this.feedBackBtnPoint.setVisibility(8);
                return;
            case R.id.feed_back_btn_point:
                return;
            case R.id.notify_btn:
                this.mFragmentHelper.startFragment(this, 1);
                this.notifyBtnPoint.setVisibility(8);
                return;
            case R.id.gift_btn:
                this.mFragmentHelper.startFragment(this, 2);
                this.giftBtnPoint.setVisibility(8);
                this.giftClick();
                return;
            case R.id.coupon_btn:
                this.mFragmentHelper.startFragment(this, 3);
                return;
            case R.id.recharge_btn:
                this.mActivity.startActivity(new Intent(this.mActivity, GotoAppActivity.class));
        }
    }

    public void onResume() {
        super.onResume();
        this.getCoin();
        this.getPoint();
    }
}
