package com.zerophi.gestionvie.etudiantespace.emploi;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class cache {

    private File cache;

    public cache(Context context) {
        this.cache = context.getCacheDir();
        if(!cache.exists())
        cache.mkdirs();
    }

      public File getFile(String url) {
        String filename = String.valueOf(url.hashCode());
        File f = new File(cache, filename);
        return f;
    }

    public void clear() {
        File[] files = cache.listFiles();
        if (files==null) return;
        for (File f:files) {

            f.delete();
        }
}
}

