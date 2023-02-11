package com.zerophi.gestionvie.login;

import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import java.io.File;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    MaterialButton btn;
    final static String url = Globalurls.login;//"https://gestionviescolaire.000webhostapp.com/login.php";
    TextInputEditText usernametxt, passwordtxt;
    CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = (MaterialButton) findViewById(R.id.btnadminlogin);
        usernametxt = (TextInputEditText) findViewById(R.id.edtadminlogin);
        passwordtxt = (TextInputEditText) findViewById(R.id.edtadminpassword);
        mCheckBox = (CheckBox) findViewById(R.id.checkboxadmin);
        mCheckBox.setChecked(true);

          try {
           File httpCacheDir = new File(getApplicationContext().getCacheDir(), "http");
           long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
           HttpResponseCache.install(httpCacheDir, httpCacheSize);
       } catch (IOException e) {
           Log.i("info", "HTTP response cache installation failed:" + e);
       }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Toast.makeText(login.this, "ddd",Toast.LENGTH_SHORT).show();
             /*   AlertDialog.Builder builder = new AlertDialog.Builder(loginf.this);
                builder.setTitle("login to responsable");
                builder.setMessage("login login !! ");
                builder.setPositiveButton("ok", null);
                builder.create().show();*/
                String username = usernametxt.getText().toString();
                String password = passwordtxt.getText().toString();
                boolean noErrors = true;




                    if (username.isEmpty()) {
                   usernametxt.setError("remplire ce champ obligatoire");
                   noErrors = false;
               } else {
                   usernametxt.setError(null);
               }
                if (password.isEmpty()) {
                    passwordtxt.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    passwordtxt.setError(null);
                }

                 if (noErrors) {
                      new LoginHelper(login.this, url, usernametxt, passwordtxt,mCheckBox).execute();
           }



            }
        });

    }
}
