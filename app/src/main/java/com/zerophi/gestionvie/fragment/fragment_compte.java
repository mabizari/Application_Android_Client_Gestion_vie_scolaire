package com.zerophi.gestionvie.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;



import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;


import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class fragment_compte extends AppCompatActivity {

LinearLayout mcardetudiant , mcardenseignant ,macaCardViewadmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_compte_rec);

        mcardenseignant = (LinearLayout) findViewById(R.id.recuperer_enseignant_compte);
        mcardetudiant = (LinearLayout) findViewById(R.id.recuperer_etudiant_compte);
        macaCardViewadmin =(LinearLayout) findViewById(R.id.contacteradministrateur);

        mcardetudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),recetudiant.class));
            }
        });
        mcardenseignant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),recenseignant.class));
            }
        });
        macaCardViewadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent();
                mailIntent.setAction(Intent.ACTION_SEND);
                mailIntent.setType("message/rfc822");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"saad19551945@gmail.com"});
                startActivity(mailIntent);
            }
        });
    }

    }





