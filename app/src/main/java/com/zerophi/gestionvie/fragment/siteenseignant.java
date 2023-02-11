package com.zerophi.gestionvie.fragment;


import android.os.Bundle;
import android.webkit.WebView;

import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;

public class siteenseignant extends AppCompatActivity {
WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siteenseignant);

        mWebView  = (WebView) findViewById(R.id.recsiteenseignant);



        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.getSettings().setBuiltInZoomControls(true);


        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);


        mWebView.loadUrl("https://compte.umi.ac.ma/public/staffAccount.html");


    }
}
