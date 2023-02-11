package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;

import com.androidnetworking.error.ANError;

import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.material.textfield.TextInputEditText;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import org.json.JSONArray;

import androidx.appcompat.app.AppCompatActivity;

public class modifieradmin2 extends AppCompatActivity {

    TextView mnom,mprenom,memail ;
    TextInputEditText mpassword,mpassword2;
    Button btnmodifier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifieradmin2);


mnom = (TextView) findViewById(R.id.nommodifieradmin) ;
mprenom = (TextView)findViewById(R.id.prenommodifieradmin);
memail = (TextView)findViewById(R.id.emailmodifieradmin);
mpassword = (TextInputEditText) findViewById(R.id.passwordmodifieradmin);
mpassword2 = (TextInputEditText) findViewById(R.id.passwordmodifieradmin2);
btnmodifier  = (Button) findViewById(R.id.modifieruseradmin);

         Intent intent =this.getIntent();
        final String first_name = intent.getExtras().getString("first_name") ;
        final String adminuser_id = intent.getExtras().getString("adminuser_id");
        final String last_name = intent.getExtras().getString("last_name");
        final String email = intent.getExtras().getString("email");
       // Toast.makeText(getApplicationContext(),first_name,Toast.LENGTH_LONG).show();

         mnom.setText(first_name);
         mnom.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {


                 LayoutInflater li = LayoutInflater.from(getApplicationContext());
                 View promptsView = li.inflate(R.layout.prompt, null);
                 android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifieradmin2.this);
                 alertDialogBuilder.setTitle("ecrire un autre nom");
            //    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                //        getApplicationContext());

                 // set prompts.xml to alertdialog builder
           alertDialogBuilder.setView(promptsView);

                 final EditText userInput = (EditText) promptsView
                         .findViewById(R.id.promptedt);


                 // set dialog message
          alertDialogBuilder
                         .setCancelable(false)
                         .setPositiveButton("OK",
                                 new DialogInterface.OnClickListener() {
                                     public void onClick(DialogInterface dialog,int id) {
                                         // get user input and set it to result
                                         // edit text
                                         mnom.setText(userInput.getText());
                                     }
                                 })
                         .setNegativeButton("Cancel",
                                 new DialogInterface.OnClickListener() {
                                     public void onClick(DialogInterface dialog,int id) {
                                         dialog.cancel();
                                     }
                                 });

                 // create alert dialog
              android.app.AlertDialog alertDialog = alertDialogBuilder.create();

                 // show it
                 alertDialog.show();


             }
         });
         mprenom.setText(last_name);

        mprenom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt, null);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifieradmin2.this);
                alertDialogBuilder.setTitle("ecrire une autre prenom !");
                //    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                //        getApplicationContext());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.promptedt);


                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        mprenom.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                android.app.AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }
        });
         memail.setText(email);


        memail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt, null);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifieradmin2.this);
                alertDialogBuilder.setTitle("ecrire une autre email !");
                //    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                //        getApplicationContext());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.promptedt);


                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        memail.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                android.app.AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }
        });




         btnmodifier.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {


if (mpassword.getText().toString()=="") {
    AndroidNetworking.post(Globalurls.modifieradminwithoutpassword/*"https://gestionviescolaire.000webhostapp.com/modifieradmin.php"*/)
            .addBodyParameter("adminuser_id",adminuser_id.toString() )
            .addBodyParameter("first_name", mnom.getText().toString())
            .addBodyParameter("last_name", mprenom.getText().toString())
            .addBodyParameter("email", memail.getText().toString())
            .build()
            .getAsString(new StringRequestListener() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "dd" + response, Toast.LENGTH_LONG).show();

                }

                @Override
                public void onError(ANError anError) {
                    Toast.makeText(getApplicationContext(), anError + "dd ", Toast.LENGTH_LONG).show();
                }
            });
}else {
    boolean noErrors = true;
    String passwordss = mpassword.getText().toString();
    String passwords2 = mpassword2.getText().toString();


    if (!passwordss.equals(passwords2)) {
        mpassword.setError("les deux mot de pass pas les memes");
        mpassword2.setError("les deux mot de pass pas les memes");
        noErrors = false;
    } else {
        mpassword.setError(null);
        mpassword2.setError(null);
    }
    if (noErrors) {


        AndroidNetworking.post(Globalurls.modifieradmin)//"https://gestionviescolaire.000webhostapp.com/modifieradmin.php")
                .addBodyParameter("adminuser_id", adminuser_id.toString())
                .addBodyParameter("first_name", mnom.getText().toString())
                .addBodyParameter("last_name", mprenom.getText().toString())
                .addBodyParameter("email", memail.getText().toString())
                .addBodyParameter("password", mpassword.getText().toString())
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response + "d", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), anError + "dd ", Toast.LENGTH_LONG).show();
                    }
                });
        modifieradmin2.super.onBackPressed();
    }
}




             }
         });

    }
}
