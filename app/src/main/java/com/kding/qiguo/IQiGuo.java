package com.kding.qiguo;

import android.content.Context;

import com.kding.api.QiGuoCallBack;

public abstract interface IQiGuo {
    public abstract void addCallback(QiGuoCallBack paramQiGuoCallBack);

    public abstract void addStartActivityListen(OnStartActivityFromPlugin paramOnStartActivityFromPlugin);

    public abstract String getUid();

    public abstract void init(Context paramContext, String paramString);

    public abstract void showFloatView();

    public abstract void hideFloatView();
}