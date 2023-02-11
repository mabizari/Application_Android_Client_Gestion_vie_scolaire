package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.connection.connect;
import com.zerophi.gestionvie.connection.errors;
import com.zerophi.gestionvie.models.adminmodel;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ajouteradmin extends AppCompatActivity {
private TextInputEditText edtnom , edtprenom , edtemail , edtpassword,edtpassword2 ;
private String urlajouter = Globalurls.ajouteradmin;//"https://gestionviescolaire.000webhostapp.com/ajouteradmin.php";
private Button btnajouter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouteradmin);

        edtnom =(TextInputEditText) findViewById(R.id.nomajouteradmin);
        edtprenom = (TextInputEditText) findViewById(R.id.prenomajouteradmin);
        edtemail = (TextInputEditText) findViewById(R.id.emailajouteradmin);
        edtpassword = (TextInputEditText) findViewById(R.id.passwordajouteradmin);
        edtpassword2  = (TextInputEditText) findViewById(R.id.passwordajouteradmin2);
        btnajouter = (Button) findViewById(R.id.ajouteruseradmin);

        btnajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   boolean noErrors = true;
                    String nom = edtnom.getText().toString();
                    String prenom = edtprenom.getText().toString();
                    String emailedtst = edtemail.getText().toString() ;
                    String passwordedtst = edtpassword.getText().toString();
                    String passwrodedtst2 = edtpassword2.getText().toString();



                    if (nom.isEmpty()) {
                   edtnom.setError("remplire ce champ obligatoire");
                   noErrors = false;
               } else {
                   edtnom.setError(null);
               }
                if (prenom.isEmpty()) {
                    edtprenom.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    edtprenom.setError(null);
                }
                if (emailedtst.isEmpty()) {
                    edtemail.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    edtemail.setError(null);
                }
                 if (passwordedtst.isEmpty()) {
                    edtpassword.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    edtpassword.setError(null);
                }
                 if (passwrodedtst2.isEmpty()) {
                    edtpassword2.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    edtpassword2.setError(null);
                }
                  if (!passwordedtst.equals(passwrodedtst2)) {
                    edtpassword.setError("les deux mot de pass pas les memes");
                      edtpassword2.setError("les deux mot de pass pas les memes");
                    noErrors = false;
                } else {
                    edtpassword.setError(null);
                      edtpassword2.setError(null);
                }
                 if (noErrors) {
                     adminajoutersend s = new adminajoutersend(getApplicationContext(), edtnom,edtprenom,edtemail,edtpassword,urlajouter);
                     s.execute();
                     ajouteradmin.super.onBackPressed();
           }




            }
        });


    }

    public class adminajoutersend extends AsyncTask<Void,Void,String> {
        Context c;
        private TextInputEditText edtnom , edtprenom , edtemail , edtpassword ;
        private String urlajouter;
        adminmodel mAdminmodel;
        public adminajoutersend(Context c, TextInputEditText edtnom, TextInputEditText edtprenom, TextInputEditText edtemail, TextInputEditText edtpassword, String urlajouter) {
            this.c = c;
            this.edtnom = edtnom;
            this.edtprenom = edtprenom;
            this.edtemail = edtemail;
            this.edtpassword = edtpassword;
            this.urlajouter = urlajouter;

            mAdminmodel = new adminmodel();
            mAdminmodel.setFirst_name(edtnom.getText().toString());
            mAdminmodel.setLast_name(edtprenom.getText().toString());
            mAdminmodel.setEmail(edtemail.getText().toString());
            mAdminmodel.setPassword(edtpassword.getText().toString());

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(c, "envoyer encore !!! ",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(c,s+" :)", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return this.ajouteradmin();
        }

        private String ajouteradmin() {
            Object mconnect = connect.connect(urlajouter);
            if (mconnect.toString().startsWith("Error")) {
                return mconnect.toString();
            }
            try {
                HttpURLConnection connection = (HttpURLConnection) mconnect;

                OutputStream os = new BufferedOutputStream(connection.getOutputStream());
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

                String LoginData = new packdataadmin(mAdminmodel).packadmin();

                bw.write(LoginData);
                bw.flush();
                bw.close();
                os.close();
                int responsecode = connection.getResponseCode();
                if(responsecode==connection.HTTP_OK){

                    InputStream is = new BufferedInputStream(connection.getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    while((line=br.readLine())!=null)
                    {
                        response.append(line+"\n");
                    }
                    br.close();
                    is.close();
                    return response.toString();

                }else {
                    return errors.RESPONSE_ERROR + String.valueOf(responsecode);
                }



            } catch (IOException e) {
                e.printStackTrace();
            }



            return "go go go !!! ";
        }
    }
    public class packdataadmin {

        adminmodel mAdminmodel;
        private String mFormBody;

        public packdataadmin(adminmodel adminmodel) {
            mAdminmodel = adminmodel;
        }


        public String packadmin(){

            List<NameValuePair> formData = new ArrayList<NameValuePair>();

            formData.add(new BasicNameValuePair("first_name",mAdminmodel.getFirst_name()));
            formData.add(new BasicNameValuePair("last_name",mAdminmodel.getLast_name()));
            formData.add(new BasicNameValuePair("email",mAdminmodel.getEmail()));
            formData.add(new BasicNameValuePair("password",mAdminmodel.getPassword()));
            if (formData == null){
                mFormBody = null;
            }

            StringBuilder sb = new StringBuilder();
            for(int i =0; i<formData.size();i++) {
                NameValuePair item = formData.get(i);

                sb.append(URLEncoder.encode(item.getName()));
                sb.append("=");
                sb.append(URLEncoder.encode(item.getValue()));
                if (i != (formData.size() - 1)) {
                    sb.append("&");
                }
            }
            mFormBody=sb.toString();

            return mFormBody;



        }
    }



}
