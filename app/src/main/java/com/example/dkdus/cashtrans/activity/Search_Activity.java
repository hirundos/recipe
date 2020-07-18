package com.example.dkdus.cashtrans.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.dkdus.cashtrans.R;


public class Search_Activity extends Activity{

    private WebView mwebView;
    private WebSettings mWebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_search);

        Intent intent = getIntent();
        String getUrl = intent.getStringExtra("URL");

        mwebView = (WebView)findViewById(R.id.web);

        mwebView.setWebViewClient(new WebViewClient());
        mWebSettings = mwebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportMultipleWindows(false);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);

        mwebView.loadUrl(getUrl);



    }
}
