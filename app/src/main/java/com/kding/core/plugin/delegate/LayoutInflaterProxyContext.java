//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.delegate;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;

public class LayoutInflaterProxyContext extends ContextWrapper {
    private LayoutInflater mInflater;

    public LayoutInflaterProxyContext(Context base) {
        super(base);
    }

    public Object getSystemService(String name) {
        if("layout_inflater".equals(name)) {
            if(this.mInflater == null) {
                this.mInflater = LayoutInflater.from(this.getBaseContext()).cloneInContext(this);
            }

            return this.mInflater;
        } else {
            return super.getSystemService(name);
        }
    }
}
