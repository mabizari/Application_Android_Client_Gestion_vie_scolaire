package com.zerophi.gestionvie.adminespace;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zerophi.gestionvie.R;

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;

public class resultss extends AppCompatActivity {
TextView etudiantname, semestreetudiant, deptetudiant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultss);

        etudiantname = (TextView) findViewById(R.id.nameetudiant);
        semestreetudiant = (TextView) findViewById(R.id.semestreetudiant);
        deptetudiant = (TextView) findViewById(R.id.deptetudiant);

        Intent intent =this.getIntent();

        String name=  intent.getExtras().getString("name");
       String semestre= intent.getExtras().getString("semestre");
      String dept=  intent.getExtras().getString("dept");

      etudiantname.setText(name);
      semestreetudiant.setText(semestre);
      deptetudiant.setText(dept);


    }
}
