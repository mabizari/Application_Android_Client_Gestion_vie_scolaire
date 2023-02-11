package com.zerophi.gestionvie;

import android.content.Intent;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerophi.gestionvie.login.enseignantLogin;
import com.zerophi.gestionvie.login.etudiantLogin;
import com.zerophi.gestionvie.login.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class MainActivity extends Fragment {
    CardView responsabe, etudiant, enseignant, help;
  /*  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);

        responsabe = view.findViewById(R.id.responsable);
        enseignant = view.findViewById(R.id.enseignant);
        etudiant = view.findViewById(R.id.etudiant);


        responsabe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(v.getContext(),login.class);
                startActivity(intent);
            }
        });
        enseignant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(v.getContext(), enseignantLogin.class);
                startActivity(intent);
            }
        });
        etudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(v.getContext(), etudiantLogin.class);
                startActivity(intent);
            }
        });
        return view;
    }





}
