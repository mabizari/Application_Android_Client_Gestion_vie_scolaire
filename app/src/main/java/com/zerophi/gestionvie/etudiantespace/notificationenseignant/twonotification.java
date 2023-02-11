package com.zerophi.gestionvie.etudiantespace.notificationenseignant;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.etudiantespace.nocification.nocification;

import androidx.fragment.app.Fragment;


public class twonotification extends Fragment {

Button btnadmin , btnenseignant;
    public twonotification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_twonotification, container, false);

        btnadmin = (Button) view.findViewById(R.id.notiadmin);
        btnenseignant  = (Button) view.findViewById(R.id.notienseignant);
        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity().getApplicationContext(),nocification.class));
            }
        });
        btnenseignant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   startActivity(new Intent(getActivity().getApplicationContext(),notificationenseignantetudiant.class));
            }
        });
        return  view;
    }


}
