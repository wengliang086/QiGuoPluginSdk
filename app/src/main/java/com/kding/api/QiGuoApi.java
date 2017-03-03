//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kding.api;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import com.kding.api.QiGuoCallBack;
import com.kding.core.plugin.PluginManager;
import com.kding.core.plugin.environment.PlugInfo;
import com.kding.core.plugin.utils.DialogUtils;
import com.kding.core.plugin.utils.DownloadUtil;
import com.kding.core.plugin.utils.HttpCallbackListener;
import com.kding.core.plugin.utils.HttpRequestUtil;
import com.kding.core.plugin.utils.LogUtils;
import com.kding.qiguo.IQiGuo;
import com.kding.qiguo.OnStartActivityFromPlugin;
import org.json.JSONException;
import org.json.JSONObject;

public enum QiGuoApi {
    INSTANCE;

    private PlugInfo plug;
    private ClassLoader plugCls;
    private Activity mActivity;
    private IQiGuo qiGuo;
    private static final int INIT_FINISH = 0;
    private static final int INIT_ING = 1;
    private static final boolean SDK_INIT_FAIL = false;
    private static final boolean SDK_INIT_SUC = true;
    private int initStatus = 0;
    private boolean sdkStatus = false;
    private QiGuoApi.InitTask initTask;
    private ProgressDialog progressDialog;
    private String mAppId;
    private QiGuoCallBack mCallBack;

    private QiGuoApi() {
    }

    public void initSdk(Activity activity, String appId, QiGuoCallBack callBack) {
        this.mActivity = activity;
        this.mAppId = appId;
        this.mCallBack = callBack;
        LogUtils.init(true, "Qiguo");
        if(this.sdkStatus) {
            LogUtils.e("请勿重复初始化");
            this.mCallBack.onSuccess();
        } else {
            PluginManager.init(activity.getApplication());
            this.checkUpdate();
        }
    }

    public void reStart() {
        PluginManager.init(this.mActivity.getApplication());
        this.initCore();
    }

    private void initCore() {
        this.initTask = new QiGuoApi.InitTask(this.mActivity, this.mAppId, this.mCallBack);
        if(this.initStatus == 0) {
            this.initTask.execute(new String[0]);
        } else {
            LogUtils.d("qiguo_init", "sdk 正在初始化中");
        }

    }

    public String getUserId() {
        if(!this.isSdkWork()) {
            LogUtils.e("qiguo_init", "sdk未初始化或初始化异常");
            return "";
        } else {
            return this.qiGuo.getUid();
        }
    }

    public void showLogin(QiGuoCallBack callBack) {
        if(!this.isSdkWork()) {
            LogUtils.e("qiguo_init", "sdk未初始化或初始化异常");
        } else {
            this.qiGuo.addCallback(callBack);
            PluginManager.getSingleton().startActivity(this.mActivity, this.plug, "com.kding.LoginActivity");
        }
    }

    public void pay(String goodName, String goodDescription, String money, String extra, QiGuoCallBack callBack) {
        if(!this.isSdkWork()) {
            LogUtils.e("qiguo_init", "sdk未初始化或初始化异常");
        } else {
            this.qiGuo.addCallback(callBack);
            Intent intent = new Intent();
            intent.putExtra("name", goodName);
            intent.putExtra("descript", goodDescription);
            intent.putExtra("price", money);
            intent.putExtra("extra", extra);
            PluginManager.getSingleton().startActivity(this.mActivity, this.plug, "com.kding.PayCenterActivity", intent);
        }
    }

    private void checkUpdate() {
        HttpRequestUtil.sendGetHttpRequest("http://sdk.7xz.com/paycollection/getpaydata", new HttpCallbackListener() {
            public void onFinish(String response) {
                try {
                    JSONObject e = new JSONObject(response);
                    String error = e.getString("error");
                    if("1".equals(error)) {
                        JSONObject data = e.getJSONObject("data");
                        final int code = data.getInt("code");
                        final String version = data.getString("version");
                        final String url = data.getString("url");
                        final String des = data.getString("des");
                        if(code > Integer.valueOf(PluginManager.getSingleton().getSdkVersion()).intValue()) {
                            QiGuoApi.this.mActivity.runOnUiThread(new Runnable() {
                                public void run() {
                                    QiGuoApi.this.showNoticeDialog(code, version, url, des);
                                }
                            });
                        } else {
                            QiGuoApi.this.mActivity.runOnUiThread(new Runnable() {
                                public void run() {
                                    QiGuoApi.this.initCore();
                                }
                            });
                        }
                    } else {
                        QiGuoApi.this.mActivity.runOnUiThread(new Runnable() {
                            public void run() {
                                QiGuoApi.this.initCore();
                            }
                        });
                    }
                } catch (JSONException var9) {
                    var9.printStackTrace();
                }

            }

            public void onError(Exception e) {
                QiGuoApi.this.mActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        QiGuoApi.this.initCore();
                    }
                });
            }
        });
    }

    private void showNoticeDialog(final int code, String version, final String url, String des) {
        (new Builder(this.mActivity)).setTitle("检测到新版本！").setMessage(des).setPositiveButton("下载", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                QiGuoApi.this.showDownloadDialog(code, url);
            }
        }).setNegativeButton("下次再说", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                QiGuoApi.this.reStart();
            }
        }).create().show();
    }

    public void showDownloadDialog(int code, String url) {
        this.progressDialog = new ProgressDialog(this.mActivity);
        this.progressDialog.setTitle("正在下载...");
        this.progressDialog.setCanceledOnTouchOutside(false);
        this.progressDialog.setCancelable(false);
        this.progressDialog.setProgressStyle(1);
        (new DownloadUtil(this.mActivity, code, url, this.progressDialog)).execute(new Void[0]);
    }

    public void onResume() {
        if(this.qiGuo != null) {
            this.qiGuo.showFloatView();
        }

    }

    public void onPause() {
        if(this.qiGuo != null) {
            this.qiGuo.hideFloatView();
        }

    }

    public boolean isSdkWork() {
        return this.sdkStatus;
    }

    public void deleteSdk() {
        PluginManager.getSingleton().deletePlugin();
    }

    private class StartActivity implements OnStartActivityFromPlugin {
        private StartActivity() {
        }

        public void startActivity(Intent intent, String targetActivity) {
            PluginManager.getSingleton().startActivity(QiGuoApi.this.mActivity, QiGuoApi.this.plug, targetActivity, intent);
        }
    }

    private class InitTask extends AsyncTask<String, Integer, Boolean> {
        private final Activity mInnerActivity;
        private final String mAppId;
        private final QiGuoCallBack mCallBack;
        private final Dialog loadingDialog;

        public InitTask(Activity activity, String appId, QiGuoCallBack callBack) {
            this.mInnerActivity = activity;
            this.mAppId = appId;
            this.mCallBack = callBack;
            this.loadingDialog = DialogUtils.createLoadingDialog(QiGuoApi.this.mActivity);
        }

        protected Boolean doInBackground(String... params) {
            QiGuoApi.this.initStatus = 1;

            try {
                QiGuoApi.this.plug = PluginManager.getSingleton().loadSdk(QiGuoApi.this.mActivity);
                if(QiGuoApi.this.plug == null) {
                    return Boolean.valueOf(false);
                } else {
                    QiGuoApi.this.plugCls = QiGuoApi.this.plug.getClassLoader();
                    Class e = QiGuoApi.this.plugCls.loadClass("com.kding.qxzapi.QiGuoImpl");
                    QiGuoApi.this.qiGuo = (IQiGuo)e.newInstance();
                    QiGuoApi.this.qiGuo.init(this.mInnerActivity, this.mAppId);
                    PluginManager.getSingleton().dump();
                    LogUtils.d("qiguo_init", "初始化成功");
                    QiGuoApi.this.sdkStatus = true;
                    return Boolean.valueOf(true);
                }
            } catch (Exception var3) {
                LogUtils.e("qiguo_init", "初始化失败");
                var3.printStackTrace();
                return Boolean.valueOf(false);
            }
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            DialogUtils.closeDialog(this.loadingDialog);
            QiGuoApi.this.initStatus = 0;
            if(result.booleanValue()) {
                this.mCallBack.onSuccess();
                QiGuoApi.this.qiGuo.addStartActivityListen(QiGuoApi.this.new StartActivity());
            } else {
                this.mCallBack.onFailure("初始化失败");
            }

        }
    }
}
