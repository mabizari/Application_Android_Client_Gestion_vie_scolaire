package com.zerophi.gestionvie.adminespace.gestion_noticification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.connection.connect;
import com.zerophi.gestionvie.connection.errors;

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

public class noc extends AppCompatActivity {
    TextView txtvnotic_titre, txtvdepartement, txtvnotictitle2, txtvnoticdescription, txtvnoticpublishdate;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nocification_detail2);
        txtvnotic_titre = (TextView) findViewById(R.id.notic_titreadmin);
        txtvnotictitle2 = (TextView) findViewById(R.id.notic_titre2admin);
        txtvdepartement = (TextView) findViewById(R.id.departement_idnoticadmin);
        txtvnoticdescription = (TextView) findViewById(R.id.notic_descriptionadmin);
        delete = (Button) findViewById(R.id.delllete);
        Intent intent = this.getIntent();

        String notice_titre = intent.getExtras().getString("noticeTitre");
        String noticDescription = intent.getExtras().getString("noticDescription");
        String publish_date = intent.getExtras().getString("publish_date");
        String dept = intent.getExtras().getString("dept");
       String notic_id = intent.getExtras().getString("notic_id");

        txtvnotic_titre.setText(notice_titre);
        txtvnotictitle2.setText(noticDescription);
        txtvdepartement.setText(publish_date);
        txtvnoticdescription.setText(dept);
        final int not = Integer.parseInt(notic_id);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new noticificationdelete(getApplicationContext(), Globalurls.deletenotificationadmin,not);
                noc.super.onBackPressed();
            }
        });


    }

    private class noticificationdelete extends AsyncTask<Void, Void, String> {
        Context c;
        String urlAddress;

        int notic_id;
        //  nocificationmodel mNocificationmodel;

        ProgressDialog pd;

        public noticificationdelete(Context c, String urlAddress, int notic_id) {
            this.c = c;
            this.urlAddress = urlAddress;
            this.notic_id = notic_id;

            //  mNocificationmodel = new nocificationmodel();
            //  mNocificationmodel.setNotic_id(notic_id);
            //  mNocificationmodel.setDept(rgdept.getCheckedRadioButtonId());
            //  mNocificationmodel.setDept(1);
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
                String LoginData = "notic_id=" + notic_id;

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
