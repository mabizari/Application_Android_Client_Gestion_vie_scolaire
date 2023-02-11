package com.zerophi.gestionvie.login;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;

public class etudiantLogin extends AppCompatActivity {
Button btn;
CheckBox mcheckbox;
  final static String url =Globalurls.loginetudiant;// "https://gestionviescolaire.000webhostapp.com/loginetudiant.php";
TextInputEditText musername, mpassowrd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_login);
        btn = (Button) findViewById(R.id.btn_etudiant_login);
        musername = (TextInputEditText) findViewById(R.id.edtetudiantlogin);
        mpassowrd = (TextInputEditText) findViewById(R.id.edtetudiantpassword);
        mcheckbox = (CheckBox)findViewById(R.id.checkboxetudiant);
        mcheckbox.setChecked(true );

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = musername.getText().toString();
                String password = mpassowrd.getText().toString();

    boolean noErrors = true;




                    if (username.isEmpty()) {
                   musername.setError("remplire ce champ obligatoire");
                   noErrors = false;
               } else {
                   musername.setError(null);
               }
                if (password.isEmpty()) {
                    mpassowrd.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    mpassowrd.setError(null);
                }

                 if (noErrors) {
                      new LoginHelper(etudiantLogin.this, url, musername, mpassowrd,mcheckbox).execute();
           }

            }
        });

    }
}
