//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.kding.base.BaseDrawerFragment;

public class ModifyFragment extends BaseDrawerFragment implements OnClickListener {
    private View backBtn;

    public ModifyFragment() {
    }

    public static ModifyFragment newInstance() {
        Bundle var0 = new Bundle();
        ModifyFragment var1 = new ModifyFragment();
        var1.setArguments(var0);
        return var1;
    }

    public int getLayoutId() {
        return R.layout.fragment_modify;
    }

    public void initEvents() {
        this.backBtn.setOnClickListener(this);
    }

    public void initViews(View var1) {
        this.backBtn = var1.findViewById(R.id.back_btn);
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
