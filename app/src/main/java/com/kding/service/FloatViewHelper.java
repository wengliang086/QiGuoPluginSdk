package com.kding.service;

import android.content.Context;
import android.util.Log;

import com.kding.view.FloatView;

public class FloatViewHelper {
    private static FloatViewHelper instance;
    private RecordCountBean.RecordCountData data;
    private FloatView mFloatView;

    private FloatViewHelper(Context paramContext) {
        this.mFloatView = new FloatView(paramContext);
    }

    public static FloatViewHelper getInstance() {
        if (instance == null) {
            Log.e("KdingGameSdk", "init must called before getInstance!");
            return null;
        }
        return instance;
    }

    public static void init(Context paramContext) {
        if (instance == null) {
            try {
                if (instance == null) {
                    instance = new FloatViewHelper(paramContext);
                }
                return;
            } finally {
            }
        }
    }

    public void destroyFloat() {
        if (this.mFloatView != null) {
            this.mFloatView.destroy();
        }
        this.mFloatView = null;
    }

    public void hideFloat() {
        if (this.mFloatView != null) {
            this.mFloatView.hide();
        }
    }

    public void showFloat() {
        if (this.mFloatView != null) {
            this.mFloatView.show();
        }
    }

    protected static class RecordCountBean {
        public RecordCountData data;
        public String msg;
        public boolean success;

        class RecordCountData {
            public int newactivity;
            public int newgrab;

            RecordCountData() {
            }
        }
    }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\service\FloatViewHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */