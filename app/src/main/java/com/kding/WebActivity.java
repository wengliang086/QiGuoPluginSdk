package com.kding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class WebActivity
        extends Activity {
    private static final String TITLE = "TITLE";
    private static final String URL = "URL";
    private ImageView backBtn;
    private String mTitle;
    private String mUrl;
    private WebView mWebView;
    private final WebViewClient mWebViewClient = new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString) {
            WebActivity.this.mWebView.loadUrl(paramAnonymousString);
            return true;
        }
    };
    private TextView tv_title;

    public static Intent getIntent(Context paramContext, String paramString1, String paramString2) {
        Intent localIntent = new Intent(paramContext, WebActivity.class);
        localIntent.putExtra("TITLE", paramString1);
        localIntent.putExtra("URL", paramString2);
        return localIntent;
    }

    private void initViews() {
        this.mTitle = getIntent().getStringExtra("TITLE");
        this.mUrl = getIntent().getStringExtra("URL");
        this.tv_title = ((TextView) findViewById(R.id.title));
        this.backBtn = ((ImageView) findViewById(R.id.back_btn));
        this.backBtn.setImageResource(R.drawable.back);
        this.backBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View paramAnonymousView) {
                WebActivity.this.finish();
            }
        });
        this.tv_title.setText(this.mTitle);
        this.mWebView = ((WebView) findViewById(R.id.web_view));
        WebSettings localWebSettings = this.mWebView.getSettings();
        localWebSettings.setJavaScriptEnabled(true);
        localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        localWebSettings.setAllowFileAccess(true);
        localWebSettings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        localWebSettings.setSupportZoom(false);
        localWebSettings.setBuiltInZoomControls(true);
        localWebSettings.setUseWideViewPort(true);
        localWebSettings.setSupportMultipleWindows(true);
        localWebSettings.setLoadWithOverviewMode(true);
        localWebSettings.setAppCacheEnabled(true);
        localWebSettings.setDatabaseEnabled(true);
        localWebSettings.setDomStorageEnabled(true);
        localWebSettings.setGeolocationEnabled(true);
        localWebSettings.setPluginState(PluginState.ON);
        localWebSettings.setRenderPriority(RenderPriority.HIGH);
        localWebSettings.setCacheMode(2);
        this.mWebView.setWebViewClient(this.mWebViewClient);
    }

    private void loadUrl() {
        this.mWebView.loadUrl(this.mUrl);
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_web);
        initViews();
        loadUrl();
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if ((paramInt == 4) && (this.mWebView.canGoBack())) {
            this.mWebView.goBack();
            return true;
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    protected void onPause() {
        super.onPause();
        this.mWebView.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.mWebView.onResume();
    }
}


/* Location:              C:\Users\Administrator\Desktop\app-debug\assets\app\classes_dex2jar.jar!\com\kding\WebActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */