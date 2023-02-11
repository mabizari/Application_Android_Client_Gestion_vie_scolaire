package com.zerophi.gestionvie.login;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;

public class enseignantLogin extends AppCompatActivity {
MaterialButton btn;
  final static String url = Globalurls.loginenseignant;//"https://gestionviescolaire.000webhostapp.com/loginenseignant.php";
TextInputEditText musername, mpassowrd ;
CheckBox mCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enseignant_login);
        btn = (MaterialButton) findViewById(R.id.btn_enseignant_login);
        musername = (TextInputEditText) findViewById(R.id.edtenseignantlogin);
        mpassowrd = (TextInputEditText) findViewById(R.id.edtenseignantpassword);
        mCheckBox =(CheckBox)findViewById(R.id.checkboxenseignant);
        mCheckBox.setChecked(true);
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
                      new LoginHelper(enseignantLogin.this, url, musername, mpassowrd,mCheckBox).execute();
           }

            }
        });
    }
}
