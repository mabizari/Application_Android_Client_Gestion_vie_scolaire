package com.zerophi.gestionvie.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


import com.zerophi.gestionvie.R;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class actualite extends Fragment {
WebView mWebView ;

    public actualite() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_actualite, container, false);
      mWebView = (WebView)  view.findViewById(R.id.webview_actualite);
      mWebView.getSettings().setJavaScriptEnabled(true);
      mWebView.getSettings().setBuiltInZoomControls(true);
      mWebView.getSettings().setLoadWithOverviewMode(true);
      mWebView.getSettings().setUseWideViewPort(true);
      mWebView.loadUrl("www.est-umi.ac.ma");
        return view;
    }

}
