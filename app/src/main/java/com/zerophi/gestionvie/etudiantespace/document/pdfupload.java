package com.zerophi.gestionvie.etudiantespace.document;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class pdfupload extends AppCompatActivity {
Button btnchoose, btnupload ;
EditText edtname;
 Uri uri;
 public ProgressDialog pdialog;
 String name,tokens;
public static final String pdfurl = Globalurls.uploadpdf2;

 String PdfNameHolder, PdfPathHolder, PdfID;
private String pdfpath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfupload);

        btnchoose = (Button) findViewById(R.id.choosepdf);
        btnupload = (Button) findViewById(R.id.uploadpdf);
        edtname = (EditText) findViewById(R.id.edtpdfname);
   AllowRunTimePermission();
 //  initDialog();
        btnchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*  Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), 1);
*/
selectpdf();


            }
        });
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    PdfUploadFunction();
                uploadpdf();

            }


        });

    }

    private void selectpdf() {
     new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(1)
                .withFilter(Pattern.compile(".*\\.pdf$")) // Filtering files and directories by file name using regexp
                .withFilterDirectories(true)// Set directories filterable (false by default)
                .withHiddenFiles(true) // Show hidden files and folders
                .start();

    }

    private void uploadpdf() {

     if (pdfpath == null){
         Toast.makeText(getApplicationContext(),"please select un fichier",Toast.LENGTH_LONG).show();
     }
     else {


        Map<String, RequestBody> map = new HashMap<>();
        File file = new File(pdfpath);

             AndroidNetworking.upload(pdfurl)

                            .addMultipartFile("file", file)
                            .addMultipartParameter("filename","sss")
                            .setPriority(Priority.HIGH)
                            .build().setUploadProgressListener(new UploadProgressListener() {
                        @Override
                        public void onProgress(long bytesUploaded, long totalBytes) {

                        }
                    }).getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Toast.makeText(getApplicationContext(),"fichier est upload",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });

     }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
       String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
        File file = new File(path);
       if(path != null){
            pdfpath =path;
            Toast.makeText(getApplicationContext(),"picked file"+path,Toast.LENGTH_LONG).show();
        }
   /* uri = data.getData();
            File file = new File(uri.getPath().toString());
            name = file.getName().toString();
pdfpath =uri.getPath().toString();*/
            btnchoose.setText("PDF is Selected");

        }
    }

    private void PdfUploadFunction() {
         // Getting pdf name from EditText.
    //    PdfNameHolder = edtname.getText().toString().trim();

        // Getting file path using Filepath class.
     //   PdfPathHolder = FilePath.getPath(this, uri);

        // If file path object is null then showing toast message to move file into internal storage.
    //    if (PdfPathHolder == null) {

     //       Toast.makeText(this, "Please move your PDF file to internal storage & try again.", Toast.LENGTH_LONG).show();

    ///    }
        // If file path is not null then PDF uploading file process will starts.
      //  else {

                File file = new File(uri.getPath().toString());

                    AndroidNetworking.upload(pdfurl)

                            .addMultipartFile("file", file)
                            .addMultipartParameter("filename","sss")
                            .setPriority(Priority.HIGH)
                            .build().setUploadProgressListener(new UploadProgressListener() {
                        @Override
                        public void onProgress(long bytesUploaded, long totalBytes) {

                        }
                    }).getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Toast.makeText(getApplicationContext(),"fichier est upload",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onError(ANError anError) {
 Toast.makeText(getApplicationContext(),"fichier not upload",Toast.LENGTH_LONG).show();
                        }
                    });
                }


        /*    try {
               PdfID = UUID.randomUUID().toString();

                new MultipartUploadRequest(this, PdfID, pdfurl)
                        .addFileToUpload(PdfPathHolder, "file")
                        .addParameter("filename", PdfNameHolder)
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(5)
                        .startUpload();



/*
                AndroidNetworking.upload(pdfurl)

                        .addMultipartFile("file", new File(PdfPathHolder))
                        .addMultipartParameter("filename","sss")
                        .setPriority(Priority.HIGH)
                        .build().setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {

                    }
                }).getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getApplicationContext(),"fichier est upload",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });*/

       /*     } catch (Exception exception) {

                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
            */





   public void AllowRunTimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(pdfupload.this, Manifest.permission.READ_EXTERNAL_STORAGE))
        {

            Toast.makeText(pdfupload.this,"READ_EXTERNAL_STORAGE permission Access Dialog", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(pdfupload.this,new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] Result) {

        switch (RC) {

            case 1:

                if (Result.length > 0 && Result[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(pdfupload.this,"Permission Granted", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(pdfupload.this,"Permission Canceled", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
}
