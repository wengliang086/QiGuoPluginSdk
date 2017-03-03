//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Filter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.kding.core.plugin.utils.LogUtils;
import com.kding.core.plugin.widget.ViewStub;
import org.xmlpull.v1.XmlPullParser;

public class LayoutInflaterWrapper extends LayoutInflater {
    private static final String TAG = "LayoutInflaterWrapper";
    private LayoutInflater target;
    private final Class<?> layoutClass;
    private final Class<?> idClass;
    private final Class<?> attrClass;
    private final int screenTitle;

    public LayoutInflaterWrapper(LayoutInflater target) {
        super(target.getContext());
        this.target = target;
        Class layoutClass = null;
        Class idClass = null;
        Class attrClass = null;
        int screenTitle = 0;

        try {
            layoutClass = Class.forName("com.android.internal.R$layout");
            idClass = Class.forName("com.android.internal.R$id");
            attrClass = Class.forName("com.android.internal.R$attr");
            screenTitle = layoutClass.getField("screenTitle").getInt((Object)null);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        this.layoutClass = layoutClass;
        this.idClass = idClass;
        this.attrClass = attrClass;
        this.screenTitle = screenTitle;
    }

    public LayoutInflater cloneInContext(Context newContext) {
        return this.target.cloneInContext(newContext);
    }

    public Context getContext() {
        return this.target.getContext();
    }

    public void setFactory(Factory factory) {
        this.target.setFactory(factory);
    }

    public Filter getFilter() {
        return this.target.getFilter();
    }

    public void setFilter(Filter filter) {
        this.target.setFilter(filter);
    }

    public View inflate(int resource, ViewGroup root) {
        LogUtils.i("LayoutInflaterWrapper", "inflate布局( resource=" + resource + ", root=" + root + " )");
        if(resource == this.screenTitle) {
            LogUtils.i("LayoutInflaterWrapper", "使用自定义布局");
            return this.createLayoutScreenSimple(resource, root);
        } else {
            return this.target.inflate(resource, root);
        }
    }

    private View createLayoutScreenSimple(int resource, ViewGroup root) {
        LinearLayout lyt = new LinearLayout(this.getContext());
        lyt.setOrientation(1);
        if(VERSION.SDK_INT >= 14) {
            try {
                LinearLayout.class.getMethod("setFitsSystemWindows", new Class[]{Boolean.TYPE}).invoke(lyt, new Object[]{Boolean.valueOf(true)});
            } catch (Throwable var11) {
                ;
            }
        }

        int viewStubId = 0;
        int frameLytId = 0;
        int layoutResource = 0;
        int inflatedId = 0;

        try {
            frameLytId = this.idClass.getField("content").getInt((Object)null);
            viewStubId = this.idClass.getField("action_mode_bar_stub").getInt((Object)null);
            inflatedId = this.idClass.getField("action_mode_bar").getInt((Object)null);
            layoutResource = this.layoutClass.getField("action_mode_bar").getInt((Object)null);
        } catch (Throwable var10) {
            var10.printStackTrace();
        }

        ViewStub flyt_content = new ViewStub(this.getContext());
        flyt_content.setId(viewStubId);
        if(inflatedId != 0) {
            flyt_content.setInflatedId(inflatedId);
        }

        if(layoutResource != 0) {
            flyt_content.setLayoutResource(layoutResource);
        }

        lyt.addView(flyt_content, new LayoutParams(-1, -2));
        FrameLayout flyt_content1 = new FrameLayout(this.getContext());
        flyt_content1.setId(frameLytId);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        flyt_content1.setForegroundGravity(55);
        lyt.addView(flyt_content1, layoutParams);
        return lyt;
    }

    public View inflate(XmlPullParser parser, ViewGroup root) {
        return this.target.inflate(parser, root);
    }

    public View inflate(int resource, ViewGroup root, boolean attachToRoot) {
        return this.target.inflate(resource, root, attachToRoot);
    }

    public View inflate(XmlPullParser parser, ViewGroup root, boolean attachToRoot) {
        return this.target.inflate(parser, root, attachToRoot);
    }

    protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        try {
            return (View)LayoutInflater.class.getDeclaredMethod("onCreateView", new Class[]{String.class, AttributeSet.class}).invoke(this.target, new Object[]{name, attrs});
        } catch (Exception var4) {
            var4.printStackTrace();
            return super.onCreateView(name, attrs);
        }
    }

    protected View onCreateView(View parent, String name, AttributeSet attrs) throws ClassNotFoundException {
        try {
            return (View)LayoutInflater.class.getDeclaredMethod("onCreateView", new Class[]{View.class, String.class, AttributeSet.class}).invoke(this.target, new Object[]{parent, name, attrs});
        } catch (Exception var5) {
            var5.printStackTrace();
            return super.onCreateView(name, attrs);
        }
    }
}
