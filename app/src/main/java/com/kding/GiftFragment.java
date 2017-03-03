//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.kding.AllGiftFragment.SetGameName;
import com.kding.base.BaseDrawerFragment;
import com.kding.utils.SystemUtils;

import java.lang.reflect.Field;

public class GiftFragment extends BaseDrawerFragment implements OnClickListener {
    private AllGiftFragment allGiftFragment;
    private View backBtn;
    private String gameName;
    private View group_gift;
    private View group_my_gift;
    private ImageView iv_gift;
    private ImageView iv_my_gift;
    private MineGiftFragment mineGiftFragment;
    private TextView tv_gift;
    private TextView tv_my_gift;
    private TextView tv_title;

    public GiftFragment() {
    }

    public static GiftFragment newInstance() {
        Bundle var0 = new Bundle();
        GiftFragment var1 = new GiftFragment();
        var1.setArguments(var0);
        return var1;
    }

    public int getLayoutId() {
        return R.layout.fragment_gift;
    }

    public void initEvents() {
        this.group_gift.setOnClickListener(this);
        this.group_my_gift.setOnClickListener(this);
        this.backBtn.setOnClickListener(this);
    }

    public void initViews(View var1) {
        this.allGiftFragment = new AllGiftFragment();
        this.allGiftFragment.addCallback(new SetGameName() {
            public void setGameName(String var1) {
                if (var1 != null) {
                    GiftFragment.this.tv_title.setText(var1);
                }

            }
        });
        this.mineGiftFragment = new MineGiftFragment();
        FragmentTransaction var2 = this.getChildFragmentManager().beginTransaction();
        var2.replace(R.id.gift_layout, this.allGiftFragment);
        var2.commit();
        this.backBtn = var1.findViewById(R.id.back_btn);
        this.group_gift = var1.findViewById(R.id.group_gift);
        this.group_my_gift = var1.findViewById(R.id.group_my_gift);
        this.tv_title = (TextView) var1.findViewById(R.id.tv_title);
        this.tv_gift = (TextView) var1.findViewById(R.id.tv_gift);
        this.iv_gift = (ImageView) var1.findViewById(R.id.iv_gift);
        this.tv_my_gift = (TextView) var1.findViewById(R.id.tv_my_gift);
        this.iv_my_gift = (ImageView) var1.findViewById(R.id.iv_my_gift);
    }

    public void onClick(View var1) {
        switch (var1.getId()) {
            case R.id.group_gift:
                this.switchPage(0);
                return;
            case R.id.group_my_gift:
                this.switchPage(1);
                return;
            case R.id.back_btn:
                this.onBackPress();
                return;
            default:
        }
    }

    public void onDetach() {
        super.onDetach();

        try {
            Field var1 = Fragment.class.getDeclaredField("mChildFragmentManager");
            var1.setAccessible(true);
            var1.set(this, (Object) null);
        } catch (NoSuchFieldException var2) {
            throw new RuntimeException(var2);
        } catch (IllegalAccessException var3) {
            throw new RuntimeException(var3);
        }
    }

    public void switchPage(int var1) {
        Object var2;
        if (var1 == 0) {
            var2 = this.allGiftFragment;
            this.tv_gift.setTextColor(Color.parseColor("#FF5757"));
            this.tv_my_gift.setTextColor(Color.parseColor("#999999"));
            if (!SystemUtils.isLandscape(this.mActivity)) {
                this.iv_gift.setVisibility(0);
                this.iv_my_gift.setVisibility(4);
            } else {
                this.iv_gift.setImageDrawable(this.getResources().getDrawable(R.drawable.game_gift_sel));
                this.iv_my_gift.setImageDrawable(this.getResources().getDrawable(R.drawable.my_gift_gray));
            }
        } else {
            var2 = this.mineGiftFragment;
            this.tv_gift.setTextColor(Color.parseColor("#999999"));
            this.tv_my_gift.setTextColor(Color.parseColor("#FF5757"));
            if (!SystemUtils.isLandscape(this.mActivity)) {
                this.iv_gift.setVisibility(4);
                this.iv_my_gift.setVisibility(0);
            } else {
                this.iv_gift.setImageDrawable(this.getResources().getDrawable(R.drawable.game_gift_gray));
                this.iv_my_gift.setImageDrawable(this.getResources().getDrawable(R.drawable.my_gift_sel));
            }
        }

        FragmentTransaction var3 = this.getChildFragmentManager().beginTransaction();
        var3.replace(R.id.gift_layout, (Fragment) var2);
        var3.commit();
    }
}
