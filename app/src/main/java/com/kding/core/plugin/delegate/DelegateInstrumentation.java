//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.core.plugin.delegate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Instrumentation;
import android.app.UiAutomation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class DelegateInstrumentation extends Instrumentation {
    private Instrumentation mBase;

    public DelegateInstrumentation(Instrumentation mBase) {
        this.mBase = mBase;
    }

    public void onCreate(Bundle arguments) {
        this.mBase.onCreate(arguments);
    }

    public void start() {
        this.mBase.start();
    }

    public void onStart() {
        this.mBase.onStart();
    }

    public boolean onException(Object obj, Throwable e) {
        return this.mBase.onException(obj, e);
    }

    public void sendStatus(int resultCode, Bundle results) {
        this.mBase.sendStatus(resultCode, results);
    }

    public void finish(int resultCode, Bundle results) {
        this.mBase.finish(resultCode, results);
    }

    public void setAutomaticPerformanceSnapshots() {
        this.mBase.setAutomaticPerformanceSnapshots();
    }

    public void startPerformanceSnapshot() {
        this.mBase.startPerformanceSnapshot();
    }

    public void endPerformanceSnapshot() {
        this.mBase.endPerformanceSnapshot();
    }

    public void onDestroy() {
        this.mBase.onDestroy();
    }

    public Context getContext() {
        return this.mBase.getContext();
    }

    public ComponentName getComponentName() {
        return this.mBase.getComponentName();
    }

    public Context getTargetContext() {
        return this.mBase.getTargetContext();
    }

    public boolean isProfiling() {
        return this.mBase.isProfiling();
    }

    public void startProfiling() {
        this.mBase.startProfiling();
    }

    public void stopProfiling() {
        this.mBase.stopProfiling();
    }

    public void setInTouchMode(boolean inTouch) {
        this.mBase.setInTouchMode(inTouch);
    }

    public void waitForIdle(Runnable recipient) {
        this.mBase.waitForIdle(recipient);
    }

    public void waitForIdleSync() {
        this.mBase.waitForIdleSync();
    }

    public void runOnMainSync(Runnable runner) {
        this.mBase.runOnMainSync(runner);
    }

    public Activity startActivitySync(Intent intent) {
        return this.mBase.startActivitySync(intent);
    }

    public void addMonitor(ActivityMonitor monitor) {
        this.mBase.addMonitor(monitor);
    }

    public ActivityMonitor addMonitor(IntentFilter filter, ActivityResult result, boolean block) {
        return this.mBase.addMonitor(filter, result, block);
    }

    public ActivityMonitor addMonitor(String cls, ActivityResult result, boolean block) {
        return this.mBase.addMonitor(cls, result, block);
    }

    public boolean checkMonitorHit(ActivityMonitor monitor, int minHits) {
        return this.mBase.checkMonitorHit(monitor, minHits);
    }

    public Activity waitForMonitor(ActivityMonitor monitor) {
        return this.mBase.waitForMonitor(monitor);
    }

    public Activity waitForMonitorWithTimeout(ActivityMonitor monitor, long timeOut) {
        return this.mBase.waitForMonitorWithTimeout(monitor, timeOut);
    }

    public void removeMonitor(ActivityMonitor monitor) {
        this.mBase.removeMonitor(monitor);
    }

    public boolean invokeMenuActionSync(Activity targetActivity, int id, int flag) {
        return this.mBase.invokeMenuActionSync(targetActivity, id, flag);
    }

    public boolean invokeContextMenuAction(Activity targetActivity, int id, int flag) {
        return this.mBase.invokeContextMenuAction(targetActivity, id, flag);
    }

    public void sendStringSync(String text) {
        this.mBase.sendStringSync(text);
    }

    public void sendKeySync(KeyEvent event) {
        this.mBase.sendKeySync(event);
    }

    public void sendKeyDownUpSync(int key) {
        this.mBase.sendKeyDownUpSync(key);
    }

    public void sendCharacterSync(int keyCode) {
        this.mBase.sendCharacterSync(keyCode);
    }

    public void sendPointerSync(MotionEvent event) {
        this.mBase.sendPointerSync(event);
    }

    public void sendTrackballEventSync(MotionEvent event) {
        this.mBase.sendTrackballEventSync(event);
    }

    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return this.mBase.newApplication(cl, className, context);
    }

    public void callApplicationOnCreate(Application app) {
        this.mBase.callApplicationOnCreate(app);
    }

    public Activity newActivity(Class<?> clazz, Context context, IBinder token, Application application, Intent intent, ActivityInfo info, CharSequence title, Activity parent, String id, Object lastNonConfigurationInstance) throws InstantiationException, IllegalAccessException {
        return this.mBase.newActivity(clazz, context, token, application, intent, info, title, parent, id, lastNonConfigurationInstance);
    }

    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return this.mBase.newActivity(cl, className, intent);
    }

    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        this.mBase.callActivityOnCreate(activity, icicle);
    }

    public void callActivityOnDestroy(Activity activity) {
        this.mBase.callActivityOnDestroy(activity);
    }

    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState) {
        this.mBase.callActivityOnRestoreInstanceState(activity, savedInstanceState);
    }

    public void callActivityOnPostCreate(Activity activity, Bundle icicle) {
        this.mBase.callActivityOnPostCreate(activity, icicle);
    }

    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        this.mBase.callActivityOnNewIntent(activity, intent);
    }

    public void callActivityOnStart(Activity activity) {
        this.mBase.callActivityOnStart(activity);
    }

    public void callActivityOnRestart(Activity activity) {
        this.mBase.callActivityOnRestart(activity);
    }

    public void callActivityOnResume(Activity activity) {
        this.mBase.callActivityOnResume(activity);
    }

    public void callActivityOnStop(Activity activity) {
        this.mBase.callActivityOnStop(activity);
    }

    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState) {
        this.mBase.callActivityOnSaveInstanceState(activity, outState);
    }

    public void callActivityOnPause(Activity activity) {
        this.mBase.callActivityOnPause(activity);
    }

    @TargetApi(3)
    public void callActivityOnUserLeaving(Activity activity) {
        this.mBase.callActivityOnUserLeaving(activity);
    }

    public void startAllocCounting() {
        this.mBase.startAllocCounting();
    }

    public void stopAllocCounting() {
        this.mBase.stopAllocCounting();
    }

    public Bundle getAllocCounts() {
        return this.mBase.getAllocCounts();
    }

    public Bundle getBinderCounts() {
        return this.mBase.getBinderCounts();
    }

    @TargetApi(18)
    public UiAutomation getUiAutomation() {
        return this.mBase.getUiAutomation();
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Fragment fragment, Intent intent, int requestCode) {
//        return this.mBase.execStartActivity(who, contextThread, token, fragment, intent, requestCode);
        return null;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Fragment fragment, Intent intent, int requestCode, Bundle options) {
//        return this.mBase.execStartActivity(who, contextThread, token, fragment, intent, requestCode, options);
        return null;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode) {
//        return this.mBase.execStartActivity(who, contextThread, token, target, intent, requestCode);
        return null;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode, Bundle options) {
//        return this.mBase.execStartActivity(who, contextThread, token, target, intent, requestCode, options);
        return null;
    }
}
