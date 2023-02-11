package com.zerophi.gestionvie.enseignantespace.gestion_nocification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zerophi.gestionvie.R;

public class notification_enseignantdetail extends AppCompatActivity {
 TextView txtvnotic_titre,txtvdepartement,txtvnotictitle2,txtvnoticdescription,txtvnoticpublishdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_enseignantdetail);
           txtvnotic_titre = (TextView) findViewById(R.id.notic_titreenseignant);
      txtvnotictitle2 =(TextView) findViewById(R.id.notic_titre2enseignant);
      txtvdepartement =(TextView) findViewById(R.id.departement_idnoticenseignant);
      txtvnoticdescription =(TextView)findViewById(R.id.notic_descriptionenseignant);

        Intent intent =this.getIntent();

      String notice_titre = intent.getExtras().getString("noticeTitre") ;
        String noticDescription=  intent.getExtras().getString("noticDescription");
      String publish_date=  intent.getExtras().getString("publish_date");
        String dept=  intent.getExtras().getString("dept");

        txtvnotic_titre.setText(notice_titre);
        txtvnotictitle2.setText(noticDescription);
        txtvdepartement.setText(publish_date);
        txtvnoticdescription.setText(dept);


    }
}
