package com.zerophi.gestionvie.etudiantespace;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.enseignantespace.settingenseignant;
import com.zerophi.gestionvie.sharedpref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class settingetudiant extends AppCompatActivity {



    TextInputEditText recmot,nvmot1,nvmot2;
    MaterialButton btnchangermotpass;
    private String changermotpass = Globalurls.changermotpassetudiant;
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
                    final String email = sharedpref.readSharedSetting(settingetudiant.this,"email","0");
                    new changermotpass(settingetudiant.this, recpassword, nvpassword, email).execute();
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
    /*
Button btn,btninfo;
EditText motpassactuell , nouveaumotpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingetudiant);

        motpassactuell = (EditText) findViewById(R.id.motpassactuelletudiant);
        nouveaumotpass = (EditText) findViewById(R.id.nouveaumotpassetudiant);
        btn = (Button) findViewById(R.id.changermotpassetudiant);
        btninfo = (Button) findViewById(R.id.informationsetting);
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/selectetudiantbyid.php")
                        .addBodyParameter("etudiantuser_id","1")
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{


                            JSONObject jo;



                            jo = response.getJSONObject(0);


                            String id_unique = jo.getString("id_unique");
                            int etudiantuser_id = jo.getInt("etudiantuser_id");
                            int annee_id = jo.getInt("annee_id");
                            int departement_id = jo.getInt("departement_id");
                            //    String password = jo.getString("password");
                            String address = jo.getString("address");
                            String telephone = jo.getString("telephone");
                            String email = jo.getString("email");
                            String first_name = jo.getString("first_name");
                            String last_name = jo.getString("last_name");
                            int semestre_id = jo.getInt("semestre_id");
                            String section = jo.getString("section");
                            String profile_image = jo.getString("profile_image");

                            Toast.makeText(getApplicationContext(),etudiantuser_id+id_unique+annee_id+departement_id+address+telephone+email+first_name+last_name+semestre_id+section+profile_image+"",Toast.LENGTH_LONG).show();

                            openDetailActivity(etudiantuser_id+"",id_unique+"",annee_id+"",
                                    departement_id+"",address,telephone,email,first_name,last_name,semestre_id+""/*,password*///,section,profile_image);

/*

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        //  mListView.setAdapter(new CustomAdaptermodifieretudiant(getApplicationContext(),etudiant));


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
            }
        });

        final String email = sharedpref.readSharedSetting(getApplicationContext(),"email","0");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/verifiermotpassetudiant.php")
                        .addBodyParameter("email",email)
                        .addBodyParameter("password",motpassactuell.getText().toString())
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            JSONObject jo;

                            jo = response.getJSONObject(0);
                            if (jo.getInt("exist") == 1) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(settingetudiant.this);
                                builder.setMessage("est ce que tu est sure que vous voullez changer ton mot de pass?")
                                        .setCancelable(false)
                                        .setPositiveButton("oui", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/verifiermotpassetudiant.php")
                                                        .addBodyParameter("email",email)
                                                        .addBodyParameter("password",nouveaumotpass.getText().toString())
                                                        .build().getAsString(new StringRequestListener() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        Toast.makeText(getApplicationContext(),response+"",Toast.LENGTH_SHORT).show();
                                                    }

                                                    @Override
                                                    public void onError(ANError anError) {

                                                    }
                                                });
                                            }
                                        })
                                        .setNegativeButton("non", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }else{
                                Toast.makeText(getApplicationContext(),"mot de pass incorect ! ",Toast.LENGTH_SHORT).show();
                            }


                    } catch (JSONException e) {
                            e.printStackTrace();
                        }


                }

                    @Override
                    public void onError(ANError anError) {

                    }

            }
        );
    }
        });


}
    private void openDetailActivity(String ... details) {
        Intent intent = new Intent(getApplicationContext(),settingetudiant2.class);
        intent.putExtra("etudiantuser_id",details[0]+"");
        intent.putExtra("id_unique",details[1]+"");
        intent.putExtra("annee_id",details[2]+"");
        intent.putExtra("departement_id",details[3]+"");

        intent.putExtra("address",details[4]+"");
        intent.putExtra("telephone",details[5]+"");
        intent.putExtra("email",details[6]+"");
        intent.putExtra("first_name",details[7]+"");
        intent.putExtra("last_name",details[8]+"");
        intent.putExtra("semestre_id",details[9]+"");
        intent.putExtra("semestre_id",details[10]+"");
          intent.putExtra("profile_image",details[11]+"");
        //   intent.putExtra("password",details[10]);




        startActivity(intent);
    }

}
*/