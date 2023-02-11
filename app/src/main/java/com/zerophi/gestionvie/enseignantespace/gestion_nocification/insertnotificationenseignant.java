package com.zerophi.gestionvie.enseignantespace.gestion_nocification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.connection.connect;
import com.zerophi.gestionvie.connection.errors;
import com.zerophi.gestionvie.sharedpref;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class insertnotificationenseignant extends AppCompatActivity {
    final static String url = Globalurls.ajouternotificationenseignant;  //"https://gestionviescolaire.000webhostapp.com/ajouternotificationenseignant.php";
    Button btn;
    Context c;
    Button voir;
    TextInputEditText notic_titre, dept;
    EditText notic_description;
    TextView nomprof, nommodule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertnotificationenseignant);
        btn = (Button) findViewById(R.id.btnajouternotificationenseignant);
        voir = (Button) findViewById(R.id.voirnotificationenseignant);

        final int enseignantuser_id = sharedpref.readSharedSettingint(getApplicationContext(), "id", 0);
        final String profname = sharedpref.readSharedSetting(getApplicationContext(), "name", "");
        final int module_id = getIntent().getIntExtra("module_id", 0);
        final String module_name = getIntent().getStringExtra("module_name");
        final String semestre_id = getIntent().getStringExtra("semestre_id");
        final String departemnt_id = getIntent().getStringExtra("departement_id");
        notic_titre = (TextInputEditText) findViewById(R.id.noticenseignant_titre);
        notic_description = (EditText) findViewById(R.id.noticenseignant_description);
        nomprof = (TextView) findViewById(R.id.notificationenseignantprofname);
        nommodule = (TextView) findViewById(R.id.notificationenseignantmodule);

        nomprof.setText(profname+module_id+ " "+semestre_id + " "+ departemnt_id );
        nommodule.setText(module_name);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new noticificationsend(insertnotificationenseignant.this, url, notic_titre, notic_description,enseignantuser_id,semestre_id,module_id, departemnt_id,profname,module_name).execute();

            }
        });
        voir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertnotificationenseignant.super.onBackPressed();
            }
        });


    }


    public class noticificationsend extends AsyncTask<Void, Void, String> {
        Context c;
        String urlAddress;
        TextInputEditText notic_titre;
        EditText notic_description;


        String semestre_id, departement_id, prof_name, module_name;
        int module_id;
        int enseignantuser_id;
        ProgressDialog pd;

        public noticificationsend(Context c, String urlAddress, TextInputEditText notic_titre, EditText notic_description
              ,int enseignantuser_id  ,String semestre_id , int module_id, String departement_id, String prof_name, String module_name) {
            this.c = c;
            this.urlAddress = urlAddress;
            this.notic_titre = notic_titre;
            this.notic_description = notic_description;


            this.semestre_id = semestre_id;
            this.module_id = module_id;
            this.departement_id= departement_id;
            this.prof_name = prof_name ;
            this.module_name = module_name;
this.enseignantuser_id = enseignantuser_id;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(c, "atteint !!! ", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(c, s, Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return this.notic();

        }

        private String notic() {
            Object mconnect = connect.connect(urlAddress);
            if (mconnect.toString().startsWith("Error")) {
                return mconnect.toString();
            }
            try {
                HttpURLConnection connection = (HttpURLConnection) mconnect;

                OutputStream os = new BufferedOutputStream(connection.getOutputStream());
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                String LoginData = "enseignantuser_id="+enseignantuser_id
                        +"&notic_titre="+notic_titre.getText().toString()+"&notic_description="
                        +notic_description.getText().toString()+"&departement_id="+1
                        +"&enseignant_name="+prof_name+"&module_id="+module_id;

                bw.write(LoginData);
                bw.flush();
                bw.close();
                os.close();
                int responsecode = connection.getResponseCode();
                if (responsecode == connection.HTTP_OK) {

                    InputStream is = new BufferedInputStream(connection.getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        response.append(line + "\n");
                    }
                    br.close();
                    is.close();
                    return response.toString();


                } else {
                    return errors.RESPONSE_ERROR + String.valueOf(responsecode);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            //Get response


            return "go go go !!! ";
        }
    }
}
