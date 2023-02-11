package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class deleteetudiant2 extends AppCompatActivity {
Button btn ;
TextView mTextView,emailetudiant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteetudiant2);

        mTextView = (TextView)findViewById(R.id.nomdeleteetudiant2) ;
        btn =(Button)findViewById(R.id.btndeleteetudiant2);
        emailetudiant = (TextView) findViewById(R.id.emaildeleteetudiant2);
        Intent intent =this.getIntent();
        final String first_name = intent.getExtras().getString("first_name") ;
        final String etudiantuser_id = intent.getExtras().getString("etudiantuser_id");
        final String last_name = intent.getExtras().getString("last_name");
        final String email = intent.getExtras().getString("email");
        mTextView.setText(first_name+" "+last_name);
        emailetudiant.setText(email);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(deleteetudiant2.this);
                builder.setMessage("est ce que tu est sure que vous voulez supprimer cet etudiant")
                        .setCancelable(false)
                        .setPositiveButton("oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/deleteetudiant.php")
                                        .addBodyParameter("etudiantuser_id",etudiantuser_id)
                                        .build().getAsString(new StringRequestListener() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(getApplicationContext(),response+" ",Toast.LENGTH_LONG).show();
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
            }
        });


    }
}
