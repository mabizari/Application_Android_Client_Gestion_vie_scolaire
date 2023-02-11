package com.zerophi.gestionvie.etudiantespace.absence;

import android.content.Context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.enseignantespace.gestion_notes.CustomAdapteretudiant2;
import com.zerophi.gestionvie.models.absencemodel;
import com.zerophi.gestionvie.models.etudiantsmodel;
import com.zerophi.gestionvie.sharedpref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class absenceetudiant extends Fragment {
    String jsonURL = Globalurls.viewabsenceetudiant2;//"https://gestionviescolaire.000webhostapp.com/viewabsenceetudiant2.php";
    ListView mListView;
    Button btn;
   SwipeRefreshLayout refresh ;
    CustomAdapterabsence adapter;
    int etudiantuser_id;
    int module_id;
    ArrayList<absencemodel> absencemodelarray;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_absenceetudiant, container, false);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refreshetudiantabsence);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    AndroidNetworking.post(jsonURL)
                        .addBodyParameter("etudiantuser_id", etudiantuser_id + "")
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            String module_name;
                            JSONObject jo;

                            absencemodelarray.clear();
                            absencemodel mabsencemodel;

                            for (int i = 0; i < response.length(); i++) {
                                jo = response.getJSONObject(i);

                                String absence_date = jo.getString("absence_date");
                                String status = jo.getString("status");

                                int heur = jo.getInt("heur");
                                int module_id = jo.getInt("module_id");

                                if (module_id == 1) {
                                    module_name = "mathematiques generales";
                                } else if (module_id == 2) {
                                    module_name = "algorithmique et bases de programmation c";
                                } else if (module_id == 3) {
                                    module_name = "langages et technique d'expression et de communication";
                                } else if (module_id == 4) {
                                    module_name = "architecture des ordinateurs";
                                } else if (module_id == 5) {
                                    module_name = "system d'information et bases de donnees";
                                } else if (module_id == 6) {
                                    module_name = "algorithmique et structures des donnes";
                                } else if (module_id == 7) {
                                    module_name = "systeme d'exploitation & reseaux";
                                } else if (module_id == 8) {
                                    module_name = "environnement economique et juridique de l'entreprise";
                                } else if (module_id == 9) {
                                    module_name = "interconnexion des reseaux";
                                } else if (module_id == 10) {
                                    module_name = "base de donnnes avancees";
                                } else if (module_id == 11) {
                                    module_name = "programmation orientees object java";
                                } else if (module_id == 12) {
                                    module_name = "outils d'aide a la decision";
                                } else if (module_id == 13) {
                                    module_name = "atelier genie logiciel";
                                } else if (module_id == 14) {
                                    module_name = "claud computing securite";
                                } else if (module_id == 15) {
                                    module_name = "administration services reseau";
                                } else if (module_id == 16) {
                                    module_name = "projet de fin d'etud";
                                } else {
                                    module_name = "stage";
                                }
                                mabsencemodel = new absencemodel();

                                mabsencemodel.setAbsence_date(absence_date);
                                mabsencemodel.setModule(module_name);
                                mabsencemodel.setStatus(status);
                                mabsencemodel.setHeur(heur);
                                mabsencemodel.setModule_id(module_id);


                                absencemodelarray.add(mabsencemodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        adapter = new CustomAdapterabsence(absencemodelarray);
                        mListView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
refresh.setRefreshing(false);
            }
        });
        mListView = (ListView) view.findViewById(R.id.absencelistview);
        btn = (Button) view.findViewById(R.id.btnsyncabsence);

        etudiantuser_id = sharedpref.readSharedSettingint(getActivity().getApplicationContext(), "id", 0);


        absencemodelarray = new ArrayList<absencemodel>();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AndroidNetworking.post(jsonURL)
                        .addBodyParameter("etudiantuser_id", etudiantuser_id + "")
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            String module_name;
                            JSONObject jo;

                            absencemodelarray.clear();
                            absencemodel mabsencemodel;

                            for (int i = 0; i < response.length(); i++) {
                                jo = response.getJSONObject(i);

                                String absence_date = jo.getString("absence_date");
                                String status = jo.getString("status");

                                int heur = jo.getInt("heur");
                                int module_id = jo.getInt("module_id");

                                if (module_id == 1) {
                                    module_name = "mathematiques generales";
                                } else if (module_id == 2) {
                                    module_name = "algorithmique et bases de programmation c";
                                } else if (module_id == 3) {
                                    module_name = "langages et technique d'expression et de communication";
                                } else if (module_id == 4) {
                                    module_name = "architecture des ordinateurs";
                                } else if (module_id == 5) {
                                    module_name = "system d'information et bases de donnees";
                                } else if (module_id == 6) {
                                    module_name = "algorithmique et structures des donnes";
                                } else if (module_id == 7) {
                                    module_name = "systeme d'exploitation & reseaux";
                                } else if (module_id == 8) {
                                    module_name = "environnement economique et juridique de l'entreprise";
                                } else if (module_id == 9) {
                                    module_name = "interconnexion des reseaux";
                                } else if (module_id == 10) {
                                    module_name = "base de donnnes avancees";
                                } else if (module_id == 11) {
                                    module_name = "programmation orientees object java";
                                } else if (module_id == 12) {
                                    module_name = "outils d'aide a la decision";
                                } else if (module_id == 13) {
                                    module_name = "atelier genie logiciel";
                                } else if (module_id == 14) {
                                    module_name = "claud computing securite";
                                } else if (module_id == 15) {
                                    module_name = "administration services reseau";
                                } else if (module_id == 16) {
                                    module_name = "projet de fin d'etud";
                                } else {
                                    module_name = "stage";
                                }
                                mabsencemodel = new absencemodel();

                                mabsencemodel.setAbsence_date(absence_date);
                                mabsencemodel.setModule(module_name);
                                mabsencemodel.setStatus(status);
                                mabsencemodel.setHeur(heur);
                                mabsencemodel.setModule_id(module_id);


                                absencemodelarray.add(mabsencemodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        adapter = new CustomAdapterabsence(absencemodelarray);
                        mListView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

            }
        });

        return view;
    }

    /*
    public class absenceetudiant extends AppCompatActivity {
        String jsonURL="https://gestionviescolaire.000webhostapp.com/viewabsenceetudiant2.php";
        ListView mListView;
        Button btn ;

        CustomAdapterabsence adapter;
        int etudiantuser_id  ;
        int module_id;
        ArrayList<absencemodel> absencemodelarray;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_absenceetudiant);

            mListView = (ListView) findViewById(R.id.absencelistview);
            btn = (Button) findViewById(R.id.btnsyncabsence);

            etudiantuser_id  = sharedpref.readSharedSettingint(getApplicationContext(),"id",0);


            absencemodelarray = new ArrayList<absencemodel>();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AndroidNetworking.post(jsonURL)
                            .addBodyParameter("etudiantuser_id",etudiantuser_id+"")
                            .build().getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {

                            try
                            {
                                String module_name;
                                JSONObject jo;

                                absencemodelarray.clear();
                                absencemodel mabsencemodel ;

                                for (int i=0;i<response.length();i++)
                                {
                                    jo=response.getJSONObject(i);

                                    String absence_date = jo.getString("absence_date");
                                    String status = jo.getString("status");

                                    int heur = jo.getInt("heur");
                                    int module_id = jo.getInt("module_id");

                                    if(module_id==1){
                                        module_name= "mathematiques generales";
                                    }else if (module_id==2){
                                        module_name= "algorithmique et bases de programmation c";
                                    }else if(module_id==3){
                                        module_name= "langages et technique d'expression et de communication";
                                    }else if(module_id==4){
                                        module_name= "architecture des ordinateurs";
                                    }else if(module_id==5){
                                        module_name= "system d'information et bases de donnees";
                                    }else if(module_id ==6){
                                        module_name= "algorithmique et structures des donnes";
                                    }else if(module_id==7){
                                        module_name= "systeme d'exploitation & reseaux";
                                    }else if(module_id==8){
                                        module_name= "environnement economique et juridique de l'entreprise";
                                    }else if(module_id==9){
                                        module_name= "interconnexion des reseaux";
                                    }else if(module_id==10){
                                        module_name= "base de donnnes avancees";
                                    }else if(module_id==11){
                                        module_name= "programmation orientees object java";
                                    }else if(module_id==12){
                                        module_name= "outils d'aide a la decision";
                                    }else if(module_id==13){
                                        module_name= "atelier genie logiciel";
                                    }else if(module_id ==14){
                                        module_name= "claud computing securite";
                                    }else if(module_id==15){
                                        module_name= "administration services reseau";
                                    }else if (module_id==16){
                                        module_name= "projet de fin d'etud";
                                    }else{
                                        module_name= "stage";
                                    }
                                    mabsencemodel = new absencemodel();

                                    mabsencemodel.setAbsence_date(absence_date);
                                    mabsencemodel.setModule(module_name);
                                    mabsencemodel.setStatus(status);
                                    mabsencemodel.setHeur(heur);
                                    mabsencemodel.setModule_id(module_id);



                                    absencemodelarray.add(mabsencemodel);

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                            adapter = new CustomAdapterabsence(absencemodelarray);
                            mListView.setAdapter( adapter);
                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });

                }
            });



        }
    */
    private class CustomAdapterabsence extends BaseAdapter {

        private ArrayList<absencemodel> mabs;

        public CustomAdapterabsence(ArrayList<absencemodel> absencemodelArrayList) {
            this.mabs = absencemodelArrayList;
        }

        @Override
        public int getCount() {
            return mabs.size();
        }

        @Override
        public Object getItem(int position) {
            return mabs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.rowabsence, parent, false);
            }
            TextView txtabsence_date = (TextView) convertView.findViewById(R.id.absence_date);
            TextView txtstatus = (TextView) convertView.findViewById(R.id.statusab);
            TextView txtmodule_id = (TextView) convertView.findViewById(R.id.module_idab);
            TextView txtheur = (TextView) convertView.findViewById(R.id.heur);

            absencemodel mabsencemodel = (absencemodel) this.getItem(position);

StringBuffer datesb = new StringBuffer(mabsencemodel.getAbsence_date());
String date = datesb.substring(0,11);
            txtabsence_date.setText(date);
            txtstatus.setText(mabsencemodel.getStatus());
            txtmodule_id.setText(mabsencemodel.getModule());
            txtheur.setText(mabsencemodel.getHeur() + "heur ");

            return convertView;
        }
    }


}