package com.example.timepay.timepay;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by Sahil on 20-06-2015.
 */
public class Webview extends Activity {
    private WebView webView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        Intent intent = getIntent();
        String wvAgree = intent.getStringExtra("wvTerms");
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(wvAgree);
    }
}
