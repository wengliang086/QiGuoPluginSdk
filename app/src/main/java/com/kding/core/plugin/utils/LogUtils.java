//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.utils;

import android.util.Log;

public class LogUtils {
    private static boolean logSwitch = true;
    private static char logFilter = 118;
    private static String tag = "TAG";

    private LogUtils() {
        throw new UnsupportedOperationException("u can\'t instantiate me...");
    }

    public static void init(boolean logSwitch, String tag) {
        LogUtils.logSwitch = logSwitch;
        LogUtils.tag = tag;
    }

    public static LogUtils.Builder getBuilder() {
        return new LogUtils.Builder();
    }

    public static void v(Object msg) {
        v(tag, msg);
    }

    public static void v(String tag, Object msg) {
        v(tag, msg, (Throwable)null);
    }

    public static void v(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'v');
    }

    public static void d(Object msg) {
        d(tag, msg);
    }

    public static void d(String tag, Object msg) {
        d(tag, msg, (Throwable)null);
    }

    public static void d(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'd');
    }

    public static void i(Object msg) {
        i(tag, msg);
    }

    public static void i(String tag, Object msg) {
        i(tag, msg, (Throwable)null);
    }

    public static void i(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'i');
    }

    public static void w(Object msg) {
        w(tag, msg);
    }

    public static void w(String tag, Object msg) {
        w(tag, msg, (Throwable)null);
    }

    public static void w(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'w');
    }

    public static void e(Object msg) {
        e(tag, msg);
    }

    public static void e(String tag, Object msg) {
        e(tag, msg, (Throwable)null);
    }

    public static void e(String tag, Object msg, Throwable tr) {
        log(tag, msg.toString(), tr, 'e');
    }

    private static void log(String tag, String msg, Throwable tr, char type) {
        if(logSwitch) {
            if(101 != type || 101 != logFilter && 118 != logFilter) {
                if(119 == type && (119 == logFilter || 118 == logFilter)) {
                    Log.w(tag, msg, tr);
                } else if(100 == type && (100 == logFilter || 118 == logFilter)) {
                    Log.d(tag, msg, tr);
                } else if(105 == type && (100 == logFilter || 118 == logFilter)) {
                    Log.i(tag, msg, tr);
                }
            } else {
                Log.e(tag, msg, tr);
            }
        }

    }

    public static class Builder {
        private boolean logSwitch = true;
        private char logFilter = 118;
        private String tag = "TAG";

        public Builder() {
        }

        public LogUtils.Builder setLogSwitch(boolean logSwitch) {
            this.logSwitch = logSwitch;
            return this;
        }

        public LogUtils.Builder setLogFilter(char logFilter) {
            this.logFilter = logFilter;
            return this;
        }

        public LogUtils.Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public void create() {
            LogUtils.logSwitch = this.logSwitch;
            LogUtils.logFilter = this.logFilter;
            LogUtils.tag = this.tag;
        }
    }
}
