package com.zerophi.gestionvie.enseignantespace.gestion_notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zerophi.gestionvie.R;

import com.zerophi.gestionvie.models.etudiantsmodel;

import java.util.ArrayList;

public class CustomAdapteretudiant2 extends  BaseAdapter {
int semestre_id,module_id;

    private Context mContext;
    private ArrayList<etudiantsmodel> mEtudiantsmodelArrayList;

    public CustomAdapteretudiant2(Context context, ArrayList<etudiantsmodel> etudiantsmodelArrayList,int semestre_id,int module_id) {
        this.mContext = context;
        this.mEtudiantsmodelArrayList = etudiantsmodelArrayList;
        this.semestre_id = semestre_id;
        this.module_id = module_id;
    }

    @Override
    public int getCount() {
        return mEtudiantsmodelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mEtudiantsmodelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.rowusers, parent, false);
        }
        TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);

        etudiantsmodel metudinatmodel = (etudiantsmodel) this.getItem(position);

        final int etudiantuser_id = metudinatmodel.getEtudiantuser_id();


        final String first_name = metudinatmodel.getFirst_name();
        final String last_name = metudinatmodel.getLast_name();

       final String id_unique = metudinatmodel.getId_unique();
       // final int departement_id = metudinatmodel.getDepartement_id();
      //  final int semestre_id = metudinatmodel.getSemestre_id();
     //   final int annee_id = metudinatmodel.getAnnee_id();

        txtnom.setText(first_name);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDetailActivity(etudiantuser_id + "", first_name, last_name,
                       id_unique + "");

            }
        });
        return convertView;

    }
        private void openDetailActivity(String... details) {
            Intent intent = new Intent(mContext, ajoternote2.class);

            intent.putExtra("etudiantuser_id", details[0]);
            intent.putExtra("first_name", details[1]+"");
            intent.putExtra("last_name", details[2]+"");
            intent.putExtra("id_unique", details[3]+"");
            intent.putExtra("semestre_id",semestre_id);
            intent.putExtra("module_id",module_id);

            mContext.startActivity(intent);
        }
    }

