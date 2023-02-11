package com.zerophi.gestionvie.adminespace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
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
import java.net.MalformedURLException;
import java.net.URL;

public class settingadmin extends AppCompatActivity {
TextInputEditText recmot,nvmot1,nvmot2;
MaterialButton btnchangermotpass;
private String changermotpass = Globalurls.changermotpassadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingadmin);

        recmot = (TextInputEditText) findViewById(R.id.recmotpassadmin);
        nvmot1 = (TextInputEditText)findViewById(R.id.nvmotpassadmin);
        nvmot2 = (TextInputEditText) findViewById(R.id.nvmotpass2admin);
        btnchangermotpass =(MaterialButton) findViewById(R.id.btnchangermotdepassadmin);


        btnchangermotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean noErrors = true;
                String recpassword = recmot.getText().toString();
                String nvpassword = nvmot1.getText().toString() ;
                String nvpassword2 = nvmot2.getText().toString();

                if (recpassword.isEmpty()) {
                    recmot.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    recmot.setError(null);
                }
                if (nvpassword.isEmpty()) {
                    nvmot1.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    nvmot1.setError(null);
                }
                if (nvpassword2.isEmpty()) {
                    nvmot2.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    nvmot2.setError(null);
                }
                if(!nvpassword.equals(nvpassword2)){
                    nvmot2.setError("les deux mot deux pass pas le meme");
                    nvmot1.setError("les deux mot deux pass pas le meme");
                    noErrors= false;
                }else{
                    nvmot2.setError(null);
                    nvmot1.setError(null);
                }
                if (noErrors) {
                    final String email = sharedpref.readSharedSetting(settingadmin.this,"email","0");
                    new changermotpass(settingadmin.this, recpassword, nvpassword, email).execute();
                }
            }

        });


    }

    private class changermotpass extends AsyncTask<Void,Void,String> {
        String recpassword , nvpassword , email ;
        Context ct;
        public changermotpass(Context ctx, String recpassword, String nvpassword, String email) {
            this.recpassword =recpassword;
            this.ct = ctx;
            this.nvpassword= nvpassword;
            this.email = email;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(ct, "atteint !!! ", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"response : "+s,Toast.LENGTH_LONG);
        }

        @Override
        protected String doInBackground(Void... voids) {
            return this.send();
        }

        private String send() {
            Object mconnect = connect.connect(changermotpass);
            if (mconnect.toString().startsWith("Error")) {
                return mconnect.toString();
            }
            try {
                HttpURLConnection connection = (HttpURLConnection) mconnect;

                OutputStream os = new BufferedOutputStream(connection.getOutputStream());
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                String dataurl = "recpassword=" + recpassword + "&password=" + nvpassword + "&email=" + email;

                bw.write(dataurl);
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
                    //        edtmessage.setText("");
                    return response.toString();


                } else {
                    return "erreurs" + String.valueOf(responsecode);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            //Get response


            return "go go go !!! ";
        }


    }
    static class connect {

        public static Object connect(String url) {

            HttpURLConnection connection = null;

            try {
                connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("POST");
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(25000);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(true);
                connection.setDefaultUseCaches(true);
                return connection;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Error : url n'existe pas";

            } catch (IOException e) {
                e.printStackTrace();
                return "Error : erreur de connection !! ";
            }


        }


    }
}
