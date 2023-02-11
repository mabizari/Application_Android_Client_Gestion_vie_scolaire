package com.zerophi.gestionvie.enseignantespace.gestion_absence;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import java.util.ArrayList;

public class ListAdapter2 extends BaseAdapter  {
    ArrayList<String> nameList;
    String module_id;
    Activity activity;

    ArrayList<Integer> etudiant;
    ArrayList<Boolean> attendanceList;

    public ListAdapter2(Activity activity, ArrayList<String> nameList,ArrayList<Integer>etudiant, ArrayList<Boolean> attendanceList ,String module_id) {
        this.nameList = nameList;
        this.activity = activity;
        this.module_id = module_id;
        this.etudiant =etudiant;
        this.attendanceList = attendanceList;


    }



    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int position) {
        return nameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(activity);
            v = vi.inflate(R.layout.list_ele, null);
        }
        final int pos = position;
        TextView textView = (TextView) v.findViewById(R.id.attendanceName);
        textView.setText(nameList.get(position));
        final CheckBox checkBox = (CheckBox) v.findViewById(R.id.attMarker);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             attendanceList.set(pos, checkBox.isChecked());
             //  Toast.makeText(activity.getApplicationContext(),attendanceList.size()+"",Toast.LENGTH_SHORT).show();

            }
        });
        return v;
    }

    public void saveAll() {

        for (int i = 0; i < nameList.size(); i++) {
            String sts = "p";
            if (attendanceList.get(i))
                sts = "p";
            else sts = "a";
            AndroidNetworking.post(Globalurls.ajouterabsence)//"https://gestionviescolaire.000webhostapp.com/ajouterabsence.php")
                    .addBodyParameter("absence_date",abcensecl.time)
                    .addBodyParameter("heur",abcensecl.period)
                    .addBodyParameter("etudiantuser_id",etudiant.get(i).toString())
                    .addBodyParameter("module_id",module_id+"")
                    .addBodyParameter("status",sts+"")
                    .build().getAsString(new StringRequestListener() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(activity.getApplicationContext(),response+" ", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(ANError anError) {

                }
            });
            activity.finish();
        }
    }
}
