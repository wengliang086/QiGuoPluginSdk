//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtils {
    public DialogUtils() {
    }

    public static Dialog createLoadingDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setProgressStyle(0);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("初始化中...");
        dialog.show();
        return dialog;
    }

    public static void closeDialog(Dialog mDialogUtils) {
        if(mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss();
        }

    }
}
