package com.kding;

import android.app.Activity;
import android.os.Bundle;

import com.kding.base.BaseDrawerFragment;
import com.kding.utils.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity
        extends Activity
        implements FragmentHelper {
    private CouponFragment couponFragment;
    boolean end;
    private FeedBackFragment feedBackFragment;
    private GiftFragment giftFragment;
    private HomeFragment homeFragment;
    List<BaseDrawerFragment> mFragmentList = new ArrayList();
    int mPosition;
    private ModifyFragment modifyFragment;
    private NotifyFragment notifyFragment;
    private SettingFragment settingFragment;
    String[] tags = {"HomeFragment", "NotifyFragment", "GiftFragment", "CouponFragment", "SettingFragment", "ModifyFragment", "FeedBackFragment"};

    public void dialogDismiss() {
        if (this.end) {
            finish();
        }
    }

    public void finishFragment(BaseDrawerFragment paramBaseDrawerFragment) {
        this.mPosition = 0;
        paramBaseDrawerFragment.finish();
        if (paramBaseDrawerFragment.getTag().equals(this.tags[0])) {
            this.end = true;
            return;
        }
        this.end = false;
        ((BaseDrawerFragment) this.mFragmentList.get(0)).show(getFragmentManager(), this.tags[0]);
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.empty);
        this.homeFragment = HomeFragment.newInstance();
        this.notifyFragment = NotifyFragment.newInstance();
        this.giftFragment = GiftFragment.newInstance();
        this.couponFragment = CouponFragment.newInstance();
        this.settingFragment = SettingFragment.newInstance();
        this.modifyFragment = ModifyFragment.newInstance();
        this.feedBackFragment = FeedBackFragment.newInstance();
        this.mFragmentList.add(this.homeFragment);
        this.mFragmentList.add(this.notifyFragment);
        this.mFragmentList.add(this.giftFragment);
        this.mFragmentList.add(this.couponFragment);
        this.mFragmentList.add(this.settingFragment);
        this.mFragmentList.add(this.modifyFragment);
        this.mFragmentList.add(this.feedBackFragment);
        this.mPosition = 0;
        ((BaseDrawerFragment) this.mFragmentList.get(0)).show(getFragmentManager(), this.tags[0]);
    }

    public void startFragment(BaseDrawerFragment paramBaseDrawerFragment, int paramInt) {
        this.mPosition = paramInt;
        paramBaseDrawerFragment.finish();
        ((BaseDrawerFragment) this.mFragmentList.get(paramInt)).show(getFragmentManager(), this.tags[paramInt]);
    }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\HomeActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */