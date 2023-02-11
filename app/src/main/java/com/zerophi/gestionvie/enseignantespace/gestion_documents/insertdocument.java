package com.zerophi.gestionvie.enseignantespace.gestion_documents;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.material.textfield.TextInputEditText;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.etudiantespace.document.FilePath;
import com.zerophi.gestionvie.sharedpref;

import org.w3c.dom.Text;

import java.io.File;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class insertdocument extends AppCompatActivity {
 Button SelectButton, UploadButton;

    TextInputEditText PdfNameEditText, titre ;
    String filePath;
    Uri uri;
 Activity act;

    File mfile;
    public static final int PERMISSIONS_REQUEST_CODE = 0;
    public static final int FILE_PICKER_REQUEST_CODE = 1;
    public static final String PDF_UPLOAD_HTTP_URL = Globalurls.uploadpdf2;// "http://10.0.0.1/chatbackend/uploadpdf2.php";

    public int PDF_REQ_CODE = 1;

    String PdfNameHolder, PdfPathHolder, PdfID;
    String titree;
    TextView profnomtxt , modulenametxt;

String departement_id, semestre_id ;
    int module_id , semestre_id2 , departement_id2,enseignantuser_id;
    String profname ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertdocument);

          act = this;


        SelectButton = (Button) findViewById(R.id.buttonselectenseignant);
        UploadButton = (Button) findViewById(R.id.button2uploadenseignant);
        PdfNameEditText = (TextInputEditText) findViewById(R.id.editTextdocumentenseignant);
        titre = (TextInputEditText) findViewById(R.id.nomdocumentenseignant);

        profnomtxt = (TextView) findViewById(R.id.documentenseignantprofname);
        modulenametxt = (TextView) findViewById(R.id.documentenseignantmodule);

            enseignantuser_id = sharedpref.readSharedSettingint(getApplicationContext(), "id", 0);
            module_id = getIntent().getIntExtra("module_id",0);
            final String module_name = getIntent().getStringExtra("module_name");
        /*
          //  semestre_id2 = Integer.parseInt(semestre_id);
            profname =sharedpref.readSharedSetting(getApplicationContext(),"name","");


          //  departement_id2 = Integer.parseInt(departemnt_id);*/
       semestre_id = getIntent().getStringExtra("semestre_id");
     departement_id= getIntent().getStringExtra("departement_id");

        profname =sharedpref.readSharedSetting(getApplicationContext(),"name","");

        profnomtxt.setText(profname);
            modulenametxt.setText(module_name);
           SelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // checkPermissionsAndOpenFilePicker();

                Intent intent = new Intent();

                intent.setType("*/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);



            }

        });

 UploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   boolean noErrors = true;
                    String pdfName = PdfNameEditText.getText().toString();
                    String titr = titre.getText().toString();




                    if (pdfName.isEmpty()) {
                   PdfNameEditText.setError("remplire ce champ obligatoire");
                   noErrors = false;
               } else {
                   PdfNameEditText.setError(null);
               }
                if (titr.isEmpty()) {
                    titre.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    titre.setError(null);
                }

                 if (noErrors) {
                     PdfUploadFunction();
                 }
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
       PdfNameHolder.replaceAll("\\s+","");

        titree = titre.getText().toString();
       PdfPathHolder = FilePath.getPath(getApplicationContext(), uri);



            try {



              File file = new File(PdfPathHolder);

                    AndroidNetworking.upload(PDF_UPLOAD_HTTP_URL)

                            .addMultipartFile("file",file)
                            .addMultipartParameter("filename",PdfNameHolder)
                            .addMultipartParameter("name",titree)
                            .addMultipartParameter("module_id",module_id+"")
                            .addMultipartParameter("enseignant_name",profname)
                            .addMultipartParameter("enseignantuser_id",enseignantuser_id+"")


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
        Toast.makeText(this, "Allow reading extrnal storage", Toast.LENGTH_SHORT).show();
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


    }



}
