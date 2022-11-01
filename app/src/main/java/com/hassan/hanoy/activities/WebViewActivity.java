package com.hassan.hanoy.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hassan.hanoy.R;
import com.hassan.hanoy.preferences.PreferenceManager;
import com.hassan.hanoy.utils.Constants;

import static com.hassan.hanoy.Hanoy.mFirebaseRemoteConfig;

public class WebViewActivity extends BasicActivity implements KeyListener {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
    }

    @Override
    public void initViews() {
        super.initViews();
        this.mWebView = findViewById(R.id.web_view);
    }

    @Override
    public void initValuesInViews() {
        super.initValuesInViews();
        MyWebViewClient webViewClient = new MyWebViewClient();
        this.mWebView.setWebViewClient(webViewClient);

        this.mWebView.setWebChromeClient(new WebChromeClient());

        WebSettings webSettings = this.mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        this.mWebView.loadUrl(mFirebaseRemoteConfig.getString(Constants.URL));

        if (PreferenceManager.getInstance(this).getOpenCount() >= 1 && PreferenceManager.getInstance(this).isShowRateUs()) {
            RateUsDialog dialog = new RateUsDialog(this);
            dialog.show();
        }
    }

    @Override
    public int getInputType() {
        return 0;
    }

    @Override
    public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyOther(View view, Editable text, KeyEvent event) {
        return false;
    }

    @Override
    public void clearMetaKeyState(View view, Editable content, int states) {

    }


    private static class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            return false;
        }
    }
}