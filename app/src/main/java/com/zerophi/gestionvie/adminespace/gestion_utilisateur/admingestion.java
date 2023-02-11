package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zerophi.gestionvie.R;

import androidx.appcompat.app.AppCompatActivity;


public class admingestion extends AppCompatActivity {
    Button btnajouter, btnsupprimer,btnmodifier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admingestion);
        btnajouter = (Button) findViewById(R.id.ajouteruseradmin);
        btnsupprimer = (Button) findViewById(R.id.supprimeruseradmin);
        btnmodifier = (Button) findViewById(R.id.modifieruseradmin);

        btnajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),ajouteradmin.class);
                startActivity(intent);
            }
        });

        btnsupprimer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),deleteadmin.class));
            }
        });
        btnmodifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),modifieradmin.class));
            }
        });
    }


}

/*
public class admingestion extends Fragment {
Button btnajouter, btnsupprimer,btnmodifier;

    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admingestion);*/

   /* @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.activity_admingestion,container,false);
        btnajouter = (Button) view.findViewById(R.id.ajouteruseradmin);
        btnmodifier = (Button) view.findViewById(R.id.modifieruseradmin);
        btnsupprimer = (Button) view.findViewById(R.id.supprimeruseradmin);


        btnajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),ajouteradmin.class);
                startActivity(intent);
            }
        });

        btnsupprimer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(),deleteadmin.class));
            }
        });
        btnmodifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(),modifieradmin.class));
            }
        });
        return view;
    }



    }

*/