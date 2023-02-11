package com.zerophi.gestionvie.etudiantespace.nocification;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.MainActivity;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.connection.Dowloader;
import com.zerophi.gestionvie.etudiantespace.drawerDashEtudiant;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/*
public class nocification extends Fragment {
    String jsonURL="https://gestionviescolaire.000webhostapp.com/noticetudiant.php";
    ListView mListView;
    Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_nocification,container,false);
      
        mListView = (ListView) view.findViewById(R.id.noticlistview);
        btn = (Button) view.findViewById(R.id.btnsyncnotic);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Dowloader(getActivity().getApplicationContext(),jsonURL,mListView).execute();
            }
        });

        return view;
    }
}
/*
 */

public class nocification extends AppCompatActivity {
SwipeRefreshLayout refresh;

    String jsonURL= Globalurls.noticetudiant;//"https://gestionviescolaire.000webhostapp.com/noticetudiant.php";
    ListView mListView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nocification);

refresh = (SwipeRefreshLayout) findViewById(R.id.refreshnoci) ;
        mListView = (ListView) findViewById(R.id.noticlistview);
        btn = (Button) findViewById(R.id.btnsyncnotic);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                  new Dowloader(nocification.this,jsonURL, mListView).execute();
                  refresh.setRefreshing(false);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new Dowloader(nocification.this,jsonURL, mListView).execute();
            }
        });


    }
}
