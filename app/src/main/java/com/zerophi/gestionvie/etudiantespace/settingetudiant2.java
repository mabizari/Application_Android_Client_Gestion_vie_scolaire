package com.zerophi.gestionvie.etudiantespace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;

public class settingetudiant2 extends AppCompatActivity {
ImageView profile_Imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingetudiant2);

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
        final String section = intent.getExtras().getString("section");
        final String profile_image = intent.getExtras().getString("profile_image");

  profile_Imageview = (ImageView) findViewById(R.id.profile_image_etudiantsetting);

        Glide.with(getApplicationContext()).load(profile_image).into(profile_Imageview);
        Toast.makeText(getApplicationContext(),etudiantuser_id+id_unique+annee_id+departement_id+addresst+telephonet+emailt+first_name+last_name+semestre_id+section+profile_image+"",Toast.LENGTH_LONG).show();
    }
}
