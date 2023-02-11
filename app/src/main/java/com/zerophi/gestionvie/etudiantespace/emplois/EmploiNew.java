package com.zerophi.gestionvie.etudiantespace.emplois;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;

public class EmploiNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploi_new);

            Intent intent =this.getIntent();
        final String imageurl = intent.getExtras().getString("imageurl");
        final String title1 = intent.getExtras().getString("title1");
        final String section=  intent.getExtras().getString("section");
        Toast.makeText(getApplicationContext(), imageurl+title1+section+"", Toast.LENGTH_SHORT).show();
    }
}
