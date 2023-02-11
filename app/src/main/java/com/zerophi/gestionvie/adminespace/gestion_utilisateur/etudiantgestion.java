package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;

public class etudiantgestion extends AppCompatActivity {
Button btnajouter, btnsupprimer,btnmodifier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiantgestion);

            btnajouter = (Button) findViewById(R.id.ajouteruseretudiant);
            btnsupprimer = (Button) findViewById(R.id.supprimeruseretudiant);
            btnmodifier = (Button) findViewById(R.id.modifieruseretudiant);
        btnajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent (getApplicationContext(),ajouteretudiant.class);
            startActivity(intent);
            }
        });

        btnsupprimer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
startActivity(new Intent(getApplicationContext(),deleteetudiant.class));
            }
        });
        btnmodifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 startActivity(new Intent(getApplicationContext(),modifieretudiant.class));
            }
        });
    }
}
