package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.app.AlertDialog;
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
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;

public class modifieretudiant2 extends AppCompatActivity {
TextView codemassar , nom , prenom , telephone , address, email;
Button btnannee, btndept, btnsemestre ;
    String[] stannee, stsemestre, stdepartement;
    int annee , departement , semestre ;
    Button btnajouter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifieretudiant2);

        codemassar = (TextView) findViewById(R.id.id_uniquemodifieretudiant);
        address = (TextView) findViewById(R.id.addressmodifieretudiat);
        nom = (TextView) findViewById(R.id.nommodifieretudiant);
        prenom = (TextView) findViewById(R.id.prenommodifieretudiant);
        email = (TextView) findViewById(R.id.emailmodifieradmin2);
        telephone = (TextView) findViewById(R.id.telephonemodifieretudiant);
        btnannee = (Button) findViewById(R.id.anneemodifieretudiantbtn);
        btnsemestre = (Button) findViewById(R.id.semestremodifieretudiant);
        btndept =(Button)findViewById(R.id.departementmodifieretudiant);
        btnajouter = (Button) findViewById(R.id.modifieretudiant22);
        Intent intent =this.getIntent();

        final String etudiantuser_id = intent.getExtras().getString("etudiantuser_id");
        final String id_unique = intent.getExtras().getString("id_unique");
        final String annee_id=  intent.getExtras().getString("annee_id");
        final String departement_id = intent.getExtras().getString("departement_id");
  //      final String password = intent.getExtras().getString("password");
        final String addresst = intent.getExtras().getString("address");
        final String telephonet = intent.getExtras().getString("telephone");
        final String emailt = intent.getExtras().getString("email");
        final String first_name = intent.getExtras().getString("first_name") ;
        final String last_name  = intent.getExtras().getString("last_name");
        final String semestre_id = intent.getExtras().getString("semestre_id");

        codemassar.setText(id_unique+"");
      codemassar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt, null);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifieretudiant2.this);
                alertDialogBuilder.setTitle("ecrire un autre code massar");
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

                                        codemassar.setText(userInput.getText());
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

        nom.setText(last_name+"");
       nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt, null);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifieretudiant2.this);
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

                                        nom.setText(userInput.getText());
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

        prenom.setText(first_name+"");
        prenom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt, null);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifieretudiant2.this);
                alertDialogBuilder.setTitle("ecrire un autre prenom");


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

                                        prenom.setText(userInput.getText());
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

        address.setText(addresst+"");
       address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt, null);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifieretudiant2.this);
                alertDialogBuilder.setTitle("ecrire un autre address");
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

                                        address.setText(userInput.getText());
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

        email.setText(emailt+"");
     email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt, null);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifieretudiant2.this);
                alertDialogBuilder.setTitle("ecrire un autre email");
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

                                        email.setText(userInput.getText());
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

        telephone.setText(telephonet+"");
      telephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt, null);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifieretudiant2.this);
                alertDialogBuilder.setTitle("ecrire un autre telephone");
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

                                        telephone.setText(userInput.getText());

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

    btndept.setText(departement_id+"");

        btnsemestre.setText(semestre_id+"");
        btnannee.setText(annee_id+"");
       btnannee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stannee = new String[]{

                        "2018/2019", "2019/2020", "2020/2021"
                };
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(modifieretudiant2.this);
                mBuilder.setTitle("choisir un ANNEE");
               mBuilder.setIcon(R.drawable.ic_id_card);
                mBuilder.setSingleChoiceItems(stannee, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        annee = ++which;
                        btnannee.setText(annee+"");
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mAlertDialog = mBuilder.create();
                mAlertDialog.show();
            }

        });
        btndept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stdepartement = new String[]{
                        "GI"
                };
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(modifieretudiant2.this);
                mBuilder.setTitle("choisir un departement");
                mBuilder.setIcon(R.drawable.ic_id_card);
                mBuilder.setSingleChoiceItems(stdepartement, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        departement = ++which;
                        btndept.setText(departement+"");
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mAlertDialog = mBuilder.create();
                mAlertDialog.show();
            }

        });
        btnsemestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stsemestre = new String[]{
                        "s1", "s2", "s3", "s4", "s5", "s6"
                };
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(modifieretudiant2.this);
                mBuilder.setTitle("choisir un semestre");
                mBuilder.setIcon(R.drawable.ic_id_card);
                mBuilder.setSingleChoiceItems(stsemestre, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        semestre = ++which;
                        btnsemestre.setText(semestre+"");
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mAlertDialog = mBuilder.create();
                mAlertDialog.show();


            }
        });


btnajouter.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AndroidNetworking.post(Globalurls.modifieretudiant)
                .addBodyParameter("etudiantuser_id", etudiantuser_id+"")
                .addBodyParameter("first_name", prenom.getText().toString())
                .addBodyParameter("last_name", nom.getText().toString())
                .addBodyParameter("email", email.getText().toString())
                .addBodyParameter("address",address.getText().toString())
                .addBodyParameter("telephone",telephone.getText().toString())
                .addBodyParameter("annee_id",btnannee.getText().toString())
                .addBodyParameter("semestre_id",btnsemestre.getText().toString())
                .addBodyParameter("departement_id",btndept.getText().toString())
                .addBodyParameter("id_unique",codemassar.getText().toString())
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
    }
});


            }
}
