package com.zerophi.gestionvie.etudiantespace.nocification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;

public class nocification_detail extends AppCompatActivity {

    TextView txtvnotic_titre,txtvdepartement,txtvnotictitle2,txtvnoticdescription,txtvnoticpublishdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nocification_detail);
      /*    intent.putExtra("noticTitre",details[0]);
        intent.putExtra("noticDescription",details[1]);
        intent.putExtra("publish_date",details[2]);
        intent.putExtra("depts",details[3]);*/


      txtvnotic_titre = (TextView) findViewById(R.id.notic_titre);
      txtvnotictitle2 =(TextView) findViewById(R.id.notic_titre2);
      txtvdepartement =(TextView) findViewById(R.id.departement_idnotic);
      txtvnoticdescription =(TextView)findViewById(R.id.notic_description);

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
