package com.zerophi.gestionvie.etudiantespace;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.sharedpref;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class acceuiletudiant extends Fragment {


    public acceuiletudiant() {
        // Required empty public constructor
    }
TextView acceuil_info_nom ,acceuil_info_email,acceuil_info_departemnt, acceuil_info_semestre,acceuil_info_section;
    ImageView acceuil_info_image;
    int etudiant_id ,departement_id,semestre_id;

    String name,section;

    String departement_name,semestre_name,section_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate(R.layout.fragment_acceuiletudiant, container, false);
        acceuil_info_email = (TextView) view.findViewById(R.id.acceuil_info_etudiant_email);
        acceuil_info_nom  = (TextView)view.findViewById(R.id.acceuil_info_etudiant_nom);
       
        acceuil_info_departemnt = (TextView) view.findViewById(R.id.acceuil_info_etudiant_departement);
        acceuil_info_semestre = (TextView) view.findViewById(R.id.acceuil_info_etudiant_semestre);
        acceuil_info_section = (TextView) view.findViewById(R.id.acceuil_info_etudiant_section);
        etudiant_id =    sharedpref.readSharedSettingint(getActivity().getApplicationContext(),"id",0);
        name=  sharedpref.readSharedSetting(getActivity().getApplicationContext(),"name"," ");
        departement_id = sharedpref.readSharedSettingint(getActivity().getApplicationContext(),"departement_id",0);
        semestre_id =  sharedpref.readSharedSettingint(getActivity().getApplicationContext(),"semestre_id",0);
        section =  sharedpref.readSharedSetting(getActivity().getApplicationContext(),"section"," ");
            String profile_image = sharedpref.readSharedSetting(getActivity().getApplicationContext(), "profile_image", "");
                 String email =    sharedpref.readSharedSetting(getActivity().getApplicationContext(),"email","");

              String name =       sharedpref.readSharedSetting(getActivity().getApplicationContext(),"name","");

        acceuil_info_nom.setText(name);
        acceuil_info_email.setText(email);
        if(departement_id == 1) {
             departement_name = "Génie informatique";
        }

        if(semestre_id == 1){
        semestre_name = "semestre 1 s1";
        }else if (semestre_id ==2){
            semestre_name = "semestre 2 s2";
        }else if (semestre_id == 3){
            semestre_name = "semestre 3 s3";
        }else if (semestre_id ==4 ){
            semestre_name = "semestre 4 ISR";
        }else if(semestre_id == 5){
            semestre_name = "semestre 5";
        }else if (semestre_id == 6) {
            semestre_name = "semestre 6";
        }

        acceuil_info_departemnt.setText(departement_name);
        acceuil_info_semestre.setText(semestre_name);
       acceuil_info_section.setText(section);


       //  Glide.with(getActivity().getApplicationContext()).load(sharedpref.readSharedSetting(getActivity().getApplicationContext(),"profile_image"," ")).into(acceuil_info_image);
        return view;
    }

}
