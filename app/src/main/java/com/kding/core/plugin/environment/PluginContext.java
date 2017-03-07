//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.environment;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.kding.core.plugin.delegate.LayoutInflaterProxyContext;
import com.kding.core.plugin.environment.PlugInfo;

public class PluginContext extends LayoutInflaterProxyContext {
    private PlugInfo plugInfo;

    public PluginContext(Context hostContext, PlugInfo plugInfo) {
        super(hostContext);
        if (plugInfo == null) {
            throw new IllegalStateException("Create a plugin context, but not given host context!");
        } else {
            this.plugInfo = plugInfo;
        }
    }

    public Resources getResources() {
        return this.plugInfo.getResources();
    }

    public AssetManager getAssets() {
        return this.plugInfo.getAssets();
    }

    public ClassLoader getClassLoader() {
        return this.plugInfo.getClassLoader();
    }

    public Context getBaseContext() {
        return this.getBaseContextInner(super.getBaseContext());
    }

    private Context getBaseContextInner(Context baseContext) {
        Context realBaseContext;
        for (realBaseContext = baseContext; realBaseContext instanceof ContextWrapper; realBaseContext = ((ContextWrapper) realBaseContext).getBaseContext()) {
            ;
        }

        return realBaseContext;
    }

    public Object getSystemService(String name) {
        return name.equals("GetHostContext") ? super.getBaseContext() : (name.equals("GetHostRes") ? this.plugInfo.getResources() : (name.equals("GetHostAssets") ? this.plugInfo.getAssets() : (name.equals("GetHostClassLoader") ? this.plugInfo.getClassLoader() : (name.equals("GetPluginPath") ? this.plugInfo.getFilePath() : (name.equals("GetPluginPkgName") ? this.plugInfo.getPackageName() : (name.equals("GetPluginPkgInfo") ? this.plugInfo.getPackageInfo() : super.getSystemService(name)))))));
    }
}
