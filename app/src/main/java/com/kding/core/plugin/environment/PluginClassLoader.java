//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.environment;

import android.annotation.TargetApi;

import com.kding.core.plugin.environment.PlugInfo;

import dalvik.system.DexClassLoader;

@TargetApi(3)
public class PluginClassLoader extends DexClassLoader {
    protected PlugInfo plugInfo;

    public PluginClassLoader(PlugInfo plugInfo, String dexPath, String optimizedDirectory, String libraryPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, libraryPath, parent);
        this.plugInfo = plugInfo;
    }

    public PlugInfo getPlugInfo() {
        return this.plugInfo;
    }
}
