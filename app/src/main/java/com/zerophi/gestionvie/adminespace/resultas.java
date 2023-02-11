package com.zerophi.gestionvie.adminespace;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.zerophi.gestionvie.MainActivity;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.connection.Dowloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class resultas extends AppCompatActivity {

    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String jsonURL="https://gestionviescolaire.000webhostapp.com/res.php";
        mListView = (ListView)findViewById(R.id.listeview) ;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "syncronisation maintent ...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                 new Dowloader(resultas.this,jsonURL, mListView).execute();
            }
        });
    }

}
