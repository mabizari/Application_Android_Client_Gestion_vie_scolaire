package com.zerophi.gestionvie.adminespace.gestion_utilisateur;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.connection.Dowloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class modifieradmin extends AppCompatActivity {
    String jsonURL= Globalurls.selectadmin;//"https://gestionviescolaire.000webhostapp.com/selectadmin.php";
    ListView mListView;
    Button btn;
    SwipeRefreshLayout refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifieradmin);
        mListView  = (ListView) findViewById(R.id.adminlistview);
        btn = (Button) findViewById(R.id.btnsyncadmin);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refreshmodifieradmin);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                 new Dowloader(modifieradmin.this,jsonURL, mListView).execute();
                 refresh.setRefreshing(false);
            }
        });
        new Dowloader(modifieradmin.this,jsonURL, mListView).execute();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             new Dowloader(modifieradmin.this,jsonURL, mListView).execute();
            }
        });
    }
}
