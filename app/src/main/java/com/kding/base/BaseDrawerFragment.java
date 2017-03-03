package com.kding.base;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.kding.utils.FragmentHelper;
import com.kding.utils.SystemUtils;
import com.nineoldandroids.animation.ObjectAnimator;

public abstract class BaseDrawerFragment
        extends DialogFragment {
    private boolean isShowing = false;
    public Activity mActivity;
    public FragmentHelper mFragmentHelper;
    String[] tags = {"HomeFragment", "NotifyFragment", "GiftFragment", "CouponFragment", "SettingFragment", "ModifyFragment", "FeedBackFragment"};
    private View view;

    public void dismissAnim(View paramView) {
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = paramView.getX();
        arrayOfFloat[1] = (-100 + -paramView.getWidth());
        android.animation.ObjectAnimator localObjectAnimator = android.animation.ObjectAnimator.ofFloat(paramView, "translationX", arrayOfFloat);
        localObjectAnimator.setDuration(400L);
        localObjectAnimator.addListener(new AnimatorListener() {
            public void onAnimationCancel(Animator paramAnonymousAnimator) {
            }

            public void onAnimationEnd(Animator paramAnonymousAnimator) {
                BaseDrawerFragment.this.dismiss();
            }

            public void onAnimationRepeat(Animator paramAnonymousAnimator) {
            }

            public void onAnimationStart(Animator paramAnonymousAnimator) {
            }
        });
        localObjectAnimator.start();
    }

    public void finish() {
        dismissAnim(this.view);
    }

    public abstract int getLayoutId();

    public abstract void initEvents();

    public abstract void initViews(View paramView);

    public void onAttach(Activity paramActivity) {
        this.mActivity = paramActivity;
        try {
            this.mFragmentHelper = ((FragmentHelper) paramActivity);
            super.onAttach(paramActivity);
            return;
        } catch (ClassCastException localClassCastException) {
            throw new ClassCastException(paramActivity.toString() + " must implement FragmentHelper");
        }
    }

    public void onBackPress() {
        this.mFragmentHelper.finishFragment(this);
    }

    public void onCancel(DialogInterface paramDialogInterface) {
        super.onCancel(paramDialogInterface);
        this.mActivity.finish();
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setStyle(0, 2131165186);
    }

    public Dialog onCreateDialog(Bundle paramBundle) {
        return new Dialog(getActivity(), getTheme()) {
            public void onBackPressed() {
                BaseDrawerFragment.this.onBackPress();
            }
        };
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        getDialog().setCanceledOnTouchOutside(true);
        this.view = paramLayoutInflater.inflate(getLayoutId(), paramViewGroup, false);
        initViews(this.view);
        initEvents();
        this.view.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                BaseDrawerFragment.this.startEnterAnim(BaseDrawerFragment.this.view);
            }
        });
        Display localDisplay = this.mActivity.getWindowManager().getDefaultDisplay();
        if (SystemUtils.isLandscape(this.mActivity)) {
        }
        for (float f = (int) (0.9D * localDisplay.getWidth()); ; f = (int) (0.8D * localDisplay.getWidth())) {
            this.view.setX(-f);
            return this.view;
        }
    }

    public void onDismiss(DialogInterface paramDialogInterface) {
        super.onDismiss(paramDialogInterface);
        this.mFragmentHelper.dialogDismiss();
    }

    public void onResume() {
        super.onResume();
        setWindow();
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    protected void setDialogSize(Display paramDisplay, LayoutParams paramLayoutParams) {
        paramLayoutParams.gravity = 3;
        if (SystemUtils.isLandscape(getActivity())) {
            paramLayoutParams.width = ((int) (0.9D * paramDisplay.getWidth()));
            return;
        }
        paramLayoutParams.width = ((int) (0.8D * paramDisplay.getWidth()));
    }

    public void setWindow() {
        Display localDisplay = getActivity().getWindowManager().getDefaultDisplay();
        LayoutParams localLayoutParams = getDialog().getWindow().getAttributes();
        setDialogSize(localDisplay, localLayoutParams);
        getDialog().getWindow().setAttributes(localLayoutParams);
    }

    public void show(FragmentManager paramFragmentManager, String paramString) {
        super.show(paramFragmentManager, paramString);
        this.isShowing = true;
    }

    public void startEnterAnim(View paramView) {
        if (this.isShowing) {
            float[] arrayOfFloat = new float[2];
            arrayOfFloat[0] = paramView.getX();
            arrayOfFloat[1] = 0.0F;
            ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(paramView, "translationX", arrayOfFloat);
            localObjectAnimator.setDuration(400L);
            localObjectAnimator.start();
            this.isShowing = false;
        }
    }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\base\BaseDrawerFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */