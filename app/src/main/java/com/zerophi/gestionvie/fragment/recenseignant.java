package com.zerophi.gestionvie.fragment;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;

public class recenseignant extends AppCompatActivity {
    Button btn1 , btn2 ;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recenseignant);
        btn1 = (Button)findViewById(R.id.geererunmotdepassenseignant);
        btn2 = (Button)findViewById(R.id.recupereremailelectroniqueenseignant);
        mEditText = (EditText) findViewById(R.id.emailenseigantrec);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post(Globalurls.recuperer_enseignant)//"https://gestionviescolaire.000webhostapp.com/recuperer_enseignant.php")
                        .addBodyParameter("email",mEditText.getText().toString())
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
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),siteenseignant.class));
            }
        });

    }
}
