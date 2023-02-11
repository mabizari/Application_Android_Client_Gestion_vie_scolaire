package com.zerophi.gestionvie.etudiantespace.nocification;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zerophi.gestionvie.R;


import com.zerophi.gestionvie.models.nocificationmodel;

import java.util.ArrayList;

public class CustomAdapternotic  extends BaseAdapter {
    private Context mContext;
    private ArrayList<nocificationmodel> noticifications;

    public CustomAdapternotic(Context context, ArrayList <nocificationmodel>notic) {
        mContext = context;
        this.noticifications = notic;
    }

    @Override
    public int getCount() {
        return noticifications.size();
    }

    @Override
    public Object getItem(int position) {
        return noticifications.get(position);
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
        TextView txtvtitre = (TextView) convertView.findViewById(R.id.titre);
        TextView txtvtdesc = (TextView) convertView.findViewById(R.id.desc);
        TextView txtvtdate = (TextView) convertView.findViewById(R.id.date);

        nocificationmodel nocificationmodel =  (nocificationmodel)  this.getItem(position);

        final String noticTitre = nocificationmodel.getNotic_titre();
        final String noticDescription = nocificationmodel.getNotic_description();
        final String publish_date = nocificationmodel.getPublish_date();
        final  int dept = nocificationmodel.getDept();
        final String depts=dept+"";


       txtvtitre.setText(noticTitre);
        txtvtdesc.setText(noticDescription);
        txtvtdate.setText(publish_date);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDetailActivity(noticTitre,noticDescription,publish_date,depts);

            }
        });
        return convertView;

    }



    private void openDetailActivity(String ... details) {
        Intent intent = new Intent(mContext,nocification_detail.class);
        intent.putExtra("noticTitre",details[0]);
        intent.putExtra("noticDescription",details[1]);
        intent.putExtra("publish_date",details[2]);
        intent.putExtra("depts",details[3]);

        mContext.startActivity(intent);
    }}

