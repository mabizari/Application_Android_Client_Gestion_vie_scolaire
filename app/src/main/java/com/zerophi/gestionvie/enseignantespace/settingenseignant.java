package com.zerophi.gestionvie.enseignantespace;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.adminespace.settingadmin;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class settingenseignant extends AppCompatActivity {


    TextInputEditText recmot,nvmot1,nvmot2;
    MaterialButton btnchangermotpass;
    private String changermotpass = Globalurls.changermotpassenseignant;
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
                    final String email = sharedpref.readSharedSetting(settingenseignant.this,"email","0");
                    new changermotpass(settingenseignant.this, recpassword, nvpassword, email).execute();
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

/*    Button btn;
    EditText motpassactuell , nouveaumotpass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_settingenseignant,container,false);
        motpassactuell = (EditText) view.findViewById(R.id.motpassactuellenseignant);
        nouveaumotpass = (EditText) view.findViewById(R.id.nouveaumotpassenseignant);
        btn = (Button) view.findViewById(R.id.changermotpassenseignant);
        final String email = sharedpref.readSharedSetting(getActivity().getApplicationContext(),"email","0");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/verifiermotpasseenseignant.php")
                        .addBodyParameter("email",email)
                        .addBodyParameter("password",motpassactuell.getText().toString())
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                                                    @Override
                                                    public void onResponse(JSONArray response) {
                                                        try{
                                                            JSONObject jo;

                                                            jo = response.getJSONObject(0);
                                                            if (jo.getInt("exist") == 1) {
                                                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext());
                                                                builder.setMessage("est ce que tu est sure que vous voullez changer ton mot de pass?")
                                                                        .setCancelable(false)
                                                                        .setPositiveButton("oui", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/changermotpassenseignant.php")
                                                                                        .addBodyParameter("email",email)
                                                                                        .addBodyParameter("password",nouveaumotpass.getText().toString())
                                                                                        .build().getAsString(new StringRequestListener() {
                                                                                    @Override
                                                                                    public void onResponse(String response) {
                                                                                        Toast.makeText(getActivity().getApplicationContext(),response+"",Toast.LENGTH_SHORT).show();
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
                                                                Toast.makeText(getActivity().getApplicationContext(),"mot de pass incorect ! ",Toast.LENGTH_SHORT).show();
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


        return view;
    }
}
/*
public class settingenseignant extends AppCompatActivity {
    Button btn;
    EditText motpassactuell , nouveaumotpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingenseignant);
        motpassactuell = (EditText) findViewById(R.id.motpassactuellenseignant);
        nouveaumotpass = (EditText) findViewById(R.id.nouveaumotpassenseignant);
        btn = (Button) findViewById(R.id.changermotpassenseignant);
        final String email = sharedpref.readSharedSetting(getApplicationContext(),"email","0");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/verifiermotpasseenseignant.php")
                        .addBodyParameter("email",email)
                        .addBodyParameter("password",motpassactuell.getText().toString())
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                                                    @Override
                                                    public void onResponse(JSONArray response) {
                                                        try{
                                                            JSONObject jo;

                                                            jo = response.getJSONObject(0);
                                                            if (jo.getInt("exist") == 1) {
                                                                AlertDialog.Builder builder = new AlertDialog.Builder(settingenseignant.this);
                                                                builder.setMessage("est ce que tu est sure que vous voullez changer ton mot de pass?")
                                                                        .setCancelable(false)
                                                                        .setPositiveButton("oui", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                               AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/changermotpassenseignant.php")
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
}
*/
