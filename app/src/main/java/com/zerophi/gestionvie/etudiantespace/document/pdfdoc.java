package com.zerophi.gestionvie.etudiantespace.document;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;
import com.zerophi.gestionvie.R;

import java.io.File;
import java.net.ResponseCache;

import androidx.appcompat.app.AppCompatActivity;

public class pdfdoc extends AppCompatActivity implements OnLoadCompleteListener,OnPageErrorListener {
ProgressBar pdfviewprogressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfdoc);
        final PDFView pdfView = findViewById(R.id.documentpdfview);
        pdfviewprogressbar = findViewById(R.id.pdfdocprogr);
        pdfviewprogressbar.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        final String path = intent.getExtras().getString ("path");
        FileLoader.with(this)
                .load(path,false)
                .fromDirectory("mypdf",FileLoader.DIR_INTERNAL)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest fileLoadRequest, FileResponse<File> fileResponse) {
                         pdfviewprogressbar.setVisibility(View.GONE);
                         File pdffile = fileResponse.getBody() ;
                    try {
                        pdfView.fromFile(pdffile)
                                .defaultPage(1)
                                .enableAnnotationRendering(true)
                                .onLoad(pdfdoc.this)
                                .scrollHandle(new DefaultScrollHandle(pdfdoc.this))
                                .spacing(10)
                                .onPageError(pdfdoc.this)
                                .load();
                    }catch (Exception e){

                    }
                    }

                    @Override
                    public void onError(FileLoadRequest fileLoadRequest, Throwable throwable) {
pdfviewprogressbar.setVisibility(View.GONE);
                        Toast.makeText(pdfdoc.this,throwable.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void loadComplete(int nbPages) {
        pdfviewprogressbar.setVisibility(View.GONE);
        Toast.makeText(pdfdoc.this,String.valueOf(nbPages),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageError(int page, Throwable t) {
        pdfviewprogressbar.setVisibility(View.GONE);
        Toast.makeText(pdfdoc.this,t.getMessage(),Toast.LENGTH_LONG).show();
    }
}
