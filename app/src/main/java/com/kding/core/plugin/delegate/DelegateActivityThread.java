//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.delegate;

import android.app.ActivityThread;
import android.app.Application;
import android.app.Instrumentation;

import com.kding.core.plugin.reflect.Reflect;

public final class DelegateActivityThread {
    private static DelegateActivityThread SINGLETON = new DelegateActivityThread();
    private Reflect activityThreadReflect = Reflect.on(ActivityThread.currentActivityThread());

    public DelegateActivityThread() {
    }

    public static DelegateActivityThread getSingleton() {
        return SINGLETON;
    }

    public Application getInitialApplication() {
        return (Application) this.activityThreadReflect.get("mInitialApplication");
    }

    public Instrumentation getInstrumentation() {
        return (Instrumentation) this.activityThreadReflect.get("mInstrumentation");
    }

    public void setInstrumentation(Instrumentation newInstrumentation) {
        this.activityThreadReflect.set("mInstrumentation", newInstrumentation);
    }
}
