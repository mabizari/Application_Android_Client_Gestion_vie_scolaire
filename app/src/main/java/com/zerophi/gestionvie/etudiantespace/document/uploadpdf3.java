package com.zerophi.gestionvie.etudiantespace.document;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.MainActivity;
import com.zerophi.gestionvie.R;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class uploadpdf3 extends AppCompatActivity {

    Button SelectButton, UploadButton;

    EditText PdfNameEditText ;
    String filePath;
    Uri uri;
Activity act;

File mfile;
    public static final int PERMISSIONS_REQUEST_CODE = 0;
    public static final int FILE_PICKER_REQUEST_CODE = 1;
    public static final String PDF_UPLOAD_HTTP_URL = Globalurls.uploadpdf2;

    public int PDF_REQ_CODE = 1;

    String PdfNameHolder, PdfPathHolder, PdfID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpdf3);
        act = this;


        SelectButton = (Button) findViewById(R.id.button);
        UploadButton = (Button) findViewById(R.id.button2);
        PdfNameEditText = (EditText) findViewById(R.id.editText);

        SelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    checkPermissionsAndOpenFilePicker();
                //      openFilePicker();
                // PDF selection code start from here .

                Intent intent = new Intent();

                intent.setType("*/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

  /*  new MaterialFilePicker()
                .withActivity(act)
                .withRequestCode(PDF_REQ_CODE)
                .withFilter(Pattern.compile(".*\\.pdf$"))
                .withFilterDirectories(true)
                .withHiddenFiles(true)
                .start();
*/

            }

        });

        UploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PdfUploadFunction();

            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PDF_REQ_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();
         //filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            mfile = new File("/sdcard"+uri.getPath());
            SelectButton.setText(filePath);
        }
    }

    public void PdfUploadFunction() {

       PdfNameHolder = PdfNameEditText.getText().toString().trim();

       PdfPathHolder = FilePath.getPath(getApplicationContext(), uri);

      //  if (PdfPathHolder == null) {

     //       Toast.makeText(this, "Please move your PDF file to internal storage & try again.", Toast.LENGTH_LONG).show();

    //    } else {

            try {

          /*      PdfID = UUID.randomUUID().toString();

                new MultipartUploadRequest(getApplicationContext(), PdfID, PDF_UPLOAD_HTTP_URL)
                        .addFileToUpload(uri.getPath().toString(), "file")
                        .addParameter("filename", "eee")
                       .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(5)
                        .startUpload();
*/
              File file = new File(PdfPathHolder);

                    AndroidNetworking.upload(PDF_UPLOAD_HTTP_URL)

                            .addMultipartFile("file",file)
                            .addMultipartParameter("filename",PdfNameHolder)
                            .setPriority(Priority.HIGH)
                            .build()
                .getAsString(new StringRequestListener() {

                    @Override
                    public void onResponse(String response) {
 Toast.makeText(getApplicationContext(),"fichier est "+response,Toast.LENGTH_LONG).show();
                    }

                    @Override
                        public void onError(ANError anError) {
 Toast.makeText(getApplicationContext(),"fichier not upload"+anError.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });

            } catch (Exception exception) {

                Toast.makeText(getApplicationContext(), "kkkk", Toast.LENGTH_SHORT).show();
            }
        }
   // }


    private void checkPermissionsAndOpenFilePicker() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            openFilePicker();
        }
    }

    private void showError() {
        Toast.makeText(this, "Allow external storage reading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker();
                } else {
                    showError();
                }
            }
        }
    }

    private void openFilePicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                   .withFilter(Pattern.compile(".*\\.pdf$"))
                .withFilterDirectories(true)
                .withHiddenFiles(true)
                .withTitle("Sample title")
                .start();

      /*FilePickerBuilder.getInstance().setMaxCount(10)
               .setSelectedFiles(filePaths)
               .setActivityTheme(R.style.LibAppTheme)
               .pickFile(this);*/
    }



}
