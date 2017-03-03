//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.kding.base.BaseDrawerFragment;
import com.kding.net.App;
import com.kding.utils.CircleTransform;
import com.squareup.picasso.Picasso;

public class SettingFragment extends BaseDrawerFragment implements OnClickListener {
    private View backBtn;

    public SettingFragment() {
    }

    public static SettingFragment newInstance() {
        Bundle var0 = new Bundle();
        SettingFragment var1 = new SettingFragment();
        var1.setArguments(var0);
        return var1;
    }

    public int getLayoutId() {
        return R.layout.fragment_setting;
    }

    public void initEvents() {
        this.backBtn.setOnClickListener(this);
    }

    public void initViews(View var1) {
        this.backBtn = var1.findViewById(R.id.back_btn);
        ((TextView) var1.findViewById(R.id.title)).setText("个人设置");
        ImageView var2 = (ImageView) var1.findViewById(R.id.imageView);
        TextView var3 = (TextView) var1.findViewById(R.id.qiguo_id);
        TextView var4 = (TextView) var1.findViewById(R.id.user_nick);
        TextView var5 = (TextView) var1.findViewById(R.id.gender);
        Picasso.with(this.mActivity).load(App.getUserEntity().getAvatar()).transform(new CircleTransform()).into(var2);
        var3.setText(App.getUserEntity().getQiguoid());
        var4.setText(App.getUserEntity().getUsernick());
        var5.setText(App.getUserEntity().getGender());
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
