package com.zerophi.gestionvie.login;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.zerophi.gestionvie.adminespace.drawerDashAdmin;
import com.zerophi.gestionvie.connection.connect;
import com.zerophi.gestionvie.connection.errors;
import com.zerophi.gestionvie.enseignantespace.drawDashEnseignant;
import com.zerophi.gestionvie.etudiantespace.drawerDashEtudiant;
import com.zerophi.gestionvie.login.LoginData;
import com.zerophi.gestionvie.sharedpref;
import com.zerophi.gestionvie.user;

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

public class LoginHelper extends AsyncTask<Void, Void , String> {
    Context c ;
    String url ;
    TextInputEditText usernametxt , passwordtxt;
    ProgressBar pd;
    user mUser;
    CheckBox mCheckBox;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
   Toast.makeText(c,"attendez  !!! ",Toast.LENGTH_LONG).show();

    }


    public LoginHelper(Context c , String url , TextInputEditText username , TextInputEditText password, CheckBox mCheckBox ) {

    this.c = c;
    this.url = url;
    this.usernametxt = username;
    this.passwordtxt = password;
    this.mCheckBox = mCheckBox;

    mUser = new user();
    mUser.setUsername(usernametxt.getText().toString());
    mUser.setPassword(passwordtxt.getText().toString());

    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.login();
    }

    private String login() {
        Object mconnect = connect.connect(url);
        if(mconnect.toString().startsWith("Error"))
        {
            return mconnect.toString();
        }
       try{ HttpURLConnection connection =(HttpURLConnection) mconnect;

           OutputStream os = new BufferedOutputStream(connection.getOutputStream());
           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

           String LoginData = new LoginData(mUser).packLoginData();

           bw.write(LoginData);
           bw.flush();
           bw.close();
           os.close();

           //Get response
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

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        usernametxt.setText("");
        passwordtxt.setText("");
        display(s);



    }

    private void display(String s) {
        final String JSON_STRING = "{\"person\":"+s+"}";
        try {
            JSONObject js = (new JSONObject(JSON_STRING)).getJSONObject("person");
            String error = js.getString("error");

            if(error!="true") {
                int role_id = js.getInt("role_id");


              //  Toast.makeText(c, "fin !!" + name, Toast.LENGTH_LONG).show();
                if(role_id==1) {   int id = js.getInt("adminuser_id");
                    String name = js.getString ("name");
                    String email = js.getString("email");
                    String profile_image = js.getString("profile_image");
                    sharedpref.saveSharedSetting(c,"profile_image",profile_image);
                    sharedpref.saveSharedSetting(c,"email",email);
                    sharedpref.saveSharedSettingint(c,"id",id);
                    sharedpref.saveSharedSetting(c,"name",name);

                       if(mCheckBox.isChecked()){
                           sharedpref.saveSharedSetting(c,"admin","false");
                       }

                 //   c.startActivity(new Intent(c, dashAdmin.class));
                    c.startActivity(new Intent(c, drawerDashAdmin.class));

                }else if(role_id==2){
                    int id = js.getInt("enseignantuser_id");
                    String name = js.getString ("name");
                    String email = js.getString("email");
                    String profile_image = js.getString("profile_image");

                     sharedpref.saveSharedSetting(c,"profile_image",profile_image);
                    sharedpref.saveSharedSetting(c,"email",email);
                    sharedpref.saveSharedSettingint(c,"id",id);

                    sharedpref.saveSharedSetting(c,"name",name);
                if(mCheckBox.isChecked()) {
                    sharedpref.saveSharedSetting(c, "enseignant", "false");

                }

                    c.startActivity(new Intent(c, drawDashEnseignant.class ));
                    
                }else if(role_id==3){
                    int id = js.getInt("etudiantuser_id");
                    String section = js.getString("section");
                    int departement_id = js.getInt("departement_id");
                    int semestre_id = js.getInt("semestre_id");
                    String email = js.getString("email");
                    sharedpref.saveSharedSetting(c,"email",email);
                    String profile_image = js.getString("profile_image");
                    sharedpref.saveSharedSetting(c,"profile_image",profile_image);
                    String name = js.getString ("name");
                    sharedpref.saveSharedSettingint(c,"id",id);
                    sharedpref.saveSharedSetting(c,"name",name);
                    sharedpref.saveSharedSettingint(c,"departement_id",departement_id);
                    sharedpref.saveSharedSettingint(c,"semestre_id",semestre_id);
                    sharedpref.saveSharedSetting(c,"section",section);
                   if(mCheckBox.isChecked()) {
                       sharedpref.saveSharedSetting(c, "etudiant", "false");


                   }

                    c.startActivity(new Intent(c, drawerDashEtudiant.class));
                }else {
                    Toast.makeText(c, "problem !!!! !!!! ", Toast.LENGTH_LONG).show();
                }
            }else{
                String error_msg = js.getString("error_msg");
                Toast.makeText(c, "no fin !!" + error_msg, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //    Toast.makeText(c, "fin !!" + JSON_STRING,Toast.LENGTH_LONG).show();
    }



}
