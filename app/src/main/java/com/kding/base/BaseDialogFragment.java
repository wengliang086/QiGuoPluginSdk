package com.kding.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.kding.utils.PayConfirmHelper;
import com.kding.utils.SystemUtils;

public class BaseDialogFragment
        extends DialogFragment {
    public Activity mActivity;
    public PayConfirmHelper payConfirmHelper;

    public void onAttach(Activity paramActivity) {
        this.mActivity = paramActivity;
        super.onAttach(paramActivity);
        try {
            this.payConfirmHelper = ((PayConfirmHelper) paramActivity);
            return;
        } catch (ClassCastException localClassCastException) {
        }
    }

    public void onCancel(DialogInterface paramDialogInterface) {
        super.onCancel(paramDialogInterface);
        this.mActivity.finish();
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setStyle(0, 2131165186);
    }

    public void onDismiss(DialogInterface paramDialogInterface) {
        super.onDismiss(paramDialogInterface);
        this.mActivity.finish();
    }

    public void onResume() {
        super.onResume();
        setWindow();
    }

    protected void setDialogSize(Display paramDisplay, LayoutParams paramLayoutParams) {
        paramLayoutParams.gravity = 17;
        if (SystemUtils.isLandscape(getActivity())) {
            paramLayoutParams.width = ((int) (0.6D * paramDisplay.getWidth()));
            return;
        }
        paramLayoutParams.width = ((int) (0.9D * paramDisplay.getWidth()));
    }

    public void setWindow() {
        Display localDisplay = getActivity().getWindowManager().getDefaultDisplay();
        LayoutParams localLayoutParams = getDialog().getWindow().getAttributes();
        setDialogSize(localDisplay, localLayoutParams);
        getDialog().getWindow().setAttributes(localLayoutParams);
    }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\base\BaseDialogFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */