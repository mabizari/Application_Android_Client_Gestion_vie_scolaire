package com.zerophi.gestionvie.connection;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.adminespace.resultss;

import com.zerophi.gestionvie.models.etudiantsmodel;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<etudiantsmodel> etudians;

    public CustomAdapter(Context context, ArrayList <etudiantsmodel>etudians) {
        mContext = context;
        this.etudians = etudians;
    }

    @Override
    public int getCount() {
        return etudians.size();
    }

    @Override
    public Object getItem(int position) {
        return etudians.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         if(convertView==null)
        {
            convertView=LayoutInflater.from(mContext).inflate(R.layout.rows,parent,false);
        }
        TextView etudiantname = (TextView) convertView.findViewById(R.id.titre);
         TextView etudiantadress = (TextView) convertView.findViewById(R.id.desc);
        TextView etudianttel = (TextView) convertView.findViewById(R.id.date);

         etudiantsmodel etudiantModel =  (etudiantsmodel)  this.getItem(position);

         final String name = etudiantModel.getName();
         final String adress = etudiantModel.getAddress();
         final String tel = etudiantModel.getTelephone();

         etudiantname.setText(name);
         etudiantadress.setText(adress);
         etudianttel.setText(tel);

            convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OPEN DETAIL ACTIVITY
               openDetailActivity(name,adress,tel);

            }
        });
        return convertView;

    }



    private void openDetailActivity(String ... details) {
        Intent intent = new Intent(mContext,resultss.class);
        intent.putExtra("name",details[0]);
        intent.putExtra("semestre",details[1]);
        intent.putExtra("dept",details[2]);

        mContext.startActivity(intent);
    }}