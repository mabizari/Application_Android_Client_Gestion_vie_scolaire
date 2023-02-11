package com.zerophi.gestionvie.etudiantespace.emplois;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;

public class emplois2 extends AppCompatActivity {
WebView mWebView;
String imageurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emplois2);
        Intent intent = getIntent();
        imageurl = intent.getExtras().getString("imageurl");

        mWebView =(WebView) findViewById(R.id.webviewemploi);

        mWebView.loadUrl("https://gestionviescolaire.000webhostapp.com/uploadEmploiNew/images/"+imageurl);
    }
}
