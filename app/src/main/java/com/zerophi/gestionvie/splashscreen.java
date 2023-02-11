package com.zerophi.gestionvie;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.zerophi.gestionvie.adminespace.drawerDashAdmin;
import com.zerophi.gestionvie.connection.isConnectedInternet;
import com.zerophi.gestionvie.enseignantespace.drawDashEnseignant;
import com.zerophi.gestionvie.etudiantespace.drawerDashEtudiant;

import androidx.appcompat.app.AppCompatActivity;

public class splashscreen extends AppCompatActivity {

    private static final int time = 500 ;
    ProgressBar mProgressBar ;
    TextView splashtextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        mProgressBar = (ProgressBar) findViewById(R.id.splashprogrssbar);
        splashtextview = (TextView)findViewById(R.id.splashtextview);
        splashtextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BackgroundSplashTask().execute();
            }
        });
        new BackgroundSplashTask().execute();

        isConnectedInternet test_connection = new isConnectedInternet();

 /*      if( test_connection.isConnected(getApplicationContext())==false){
           Toast.makeText(getApplicationContext(),getString(R.string.aucun_connection).toString()+"",Toast.LENGTH_LONG).show();
       }*/
    }

    private class BackgroundSplashTask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            mProgressBar.setVisibility(View.GONE);
            CheckSession();
   if(!new isConnectedInternet().isConnected(getApplicationContext())==false) {
       finish();
   }
        }


    }

    private void CheckSession () {

        Boolean Checkadmin = Boolean.valueOf(sharedpref.readSharedSetting(splashscreen.this,"admin","true"));
        Boolean Checkenseignant = Boolean.valueOf(sharedpref.readSharedSetting(splashscreen.this,"enseignant","true"));
        Boolean Checketudiant = Boolean.valueOf(sharedpref.readSharedSetting(splashscreen.this,"etudiant","true"));
        Intent intoMain = new Intent(splashscreen.this,Main2Activity.class);
        Intent intodashadmin = new Intent (splashscreen.this,drawerDashAdmin.class);
        Intent intodashenseignant = new Intent (splashscreen.this,drawDashEnseignant.class);
        Intent intodashetudiant = new Intent (splashscreen.this,drawerDashEtudiant.class);


        if(new isConnectedInternet().isConnected(getApplicationContext())==false)
        {
            splashtextview.setVisibility(View.VISIBLE);
        }

        else if(!Checkadmin){
            startActivity(intodashadmin);
            finish();
        }
        else if (!Checkenseignant){
                startActivity(intodashenseignant);
                finish();
        }else if (!Checketudiant)
        {
            startActivity(intodashetudiant);
            finish();
        }else
        {
            startActivity(intoMain);
            finish();
        }

    }
}
