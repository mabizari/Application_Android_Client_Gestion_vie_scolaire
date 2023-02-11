package com.zerophi.gestionvie.fragment;


import android.os.Bundle;
import android.webkit.WebView;

import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;

public class siteetudiant extends AppCompatActivity {
WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siteetudiant);
        mWebView  = (WebView) findViewById(R.id.recsiteetudiant);



        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.getSettings().setBuiltInZoomControls(true);


        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);


        mWebView.loadUrl("https://compte.umi.ac.ma/public/studentAccount.html");


    }
}
