package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class modifierenseignant2 extends AppCompatActivity {
    TextView edt_id_unique , nom , prenom , edt_telephone , edt_address, edt_email;
    Button btn_modules ;
    String[] modules;
    int module_id ;
    Button btnajouter;
    TextView select ;
       private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifierenseignant2);


        edt_id_unique = (TextView) findViewById(R.id.id_uniquemodifierenseignant);
        edt_address = (TextView) findViewById(R.id.addressmodifierenseignant);
        nom = (TextView) findViewById(R.id.nommodifierenseignant);
        prenom = (TextView) findViewById(R.id.prenommodifierenseignant);
        edt_email = (TextView) findViewById(R.id.emailmodifierenseignant);
        edt_telephone = (TextView) findViewById(R.id.telephonemodifierenseignant);
        btn_modules = (Button) findViewById(R.id.modulemodifiernseignant);
        btnajouter = (Button) findViewById(R.id.modifieruserenseignant);
        select = (TextView) findViewById(R.id.datemodifierenseignatn);
        Intent intent =this.getIntent();

        final String enseignantuser_id = intent.getExtras().getString("enseignantuser_id");
        final String id_unique = intent.getExtras().getString("id_unique");
        final String address = intent.getExtras().getString("address");
        final String telephone = intent.getExtras().getString("telephone");
        final String email = intent.getExtras().getString("email");
        final String first_name = intent.getExtras().getString("first_name") ;
        final String last_name  = intent.getExtras().getString("last_name");
        final String join_date = intent.getExtras().getString("join_date");
     //   final String password = intent.getExtras().getString("password");
        select.setText(join_date);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Calendar cal = Calendar.getInstance();
                int annee = cal.get(Calendar.YEAR);
                int mounth = cal.get(Calendar.MONTH);
                int jour = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(modifierenseignant2.this, android.R.style.Theme_DeviceDefault_Light_DarkActionBar, mOnDateSetListener,
                        annee, mounth, jour);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }

        });
         mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String Date = year + "-" + month + "-" + dayOfMonth;
                select.setText(Date);
            }
        };

        edt_id_unique.setText(id_unique+"");
      edt_id_unique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(modifierenseignant2.this);
                alertDialogBuilder.setTitle("modifier l'id unique");

                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.promptedt);


                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

                                        edt_id_unique.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(modifierenseignant2.this);
                alertDialogBuilder.setTitle("ecrire un autre nom");

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
                AlertDialog alertDialog = alertDialogBuilder.create();

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
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifierenseignant2.this);
                alertDialogBuilder.setTitle("ecrire un autre prenom");



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

   edt_address.setText(address+"");
       edt_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt, null);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifierenseignant2.this);
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

                                        edt_address.setText(userInput.getText());
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


        edt_email.setText(email+"");
        edt_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt, null);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifierenseignant2.this);
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

                                        edt_email.setText(userInput.getText());
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

        edt_telephone.setText(telephone+"");
        edt_telephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View promptsView = li.inflate(R.layout.prompt, null);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(modifierenseignant2.this);
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

                                        edt_telephone.setText(userInput.getText());

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












        btnajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post(Globalurls.modifierenseignant)
                        .addBodyParameter("enseignantuser_id",enseignantuser_id)
                        .addBodyParameter("first_name", prenom.getText().toString())
                        .addBodyParameter("last_name", nom.getText().toString())
                        .addBodyParameter("email", edt_email.getText().toString())
                        .addBodyParameter("address",edt_address.getText().toString())
                        .addBodyParameter("telephone",edt_telephone.getText().toString())
                        .addBodyParameter("id_unique",edt_id_unique.getText().toString())
                        .addBodyParameter("join_date",select.getText().toString())

                        .build()
                        .getAsString(new StringRequestListener() {
                         @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response + "d", Toast.LENGTH_LONG).show();
                                modifierenseignant2.super.onBackPressed();
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
