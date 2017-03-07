//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.environment;

import android.text.TextUtils;

import java.io.Serializable;

public final class CreateActivityData implements Serializable {
    public String activityName;
    public String pluginPkg;

    public CreateActivityData(String activityName, String pluginPkg) {
        this.activityName = activityName;
        this.pluginPkg = pluginPkg;
    }

    public boolean equals(Object o) {
        if (!(o instanceof CreateActivityData)) {
            return false;
        } else {
            CreateActivityData another = (CreateActivityData) o;
            return TextUtils.equals(this.activityName, another.activityName) && TextUtils.equals(this.pluginPkg, another.pluginPkg);
        }
    }

    public int hashCode() {
        int result = this.activityName != null ? this.activityName.hashCode() : 0;
        result = 31 * result + (this.pluginPkg != null ? this.pluginPkg.hashCode() : 0);
        return result;
    }
}
