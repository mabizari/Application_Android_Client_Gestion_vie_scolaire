package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.connection.Dowloader;
import com.zerophi.gestionvie.models.etudiantsmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class modifieretudiant extends AppCompatActivity {
    String jsonURL= Globalurls.selectetudiant; //"https://gestionviescolaire.000webhostapp.com/selectetudiant.php";
    ArrayList<etudiantsmodel> etudiant ;
    ListView mListView;
    Button btn;
    SwipeRefreshLayout refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifieretudiant);
        etudiant = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.etudiantmodifierlistview);
        btn = (Button) findViewById(R.id.btnsyncmodifieretudiant);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refreshmodifieretudiant);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                 AndroidNetworking.post(jsonURL)
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{


                            JSONObject jo;

                            etudiant.clear();
                            etudiantsmodel metudiantsmodel;

                            for (int i = 0; i < response.length(); i++) {
                                jo = response.getJSONObject(i);

                               int etudiantuser_id = jo.getInt("etudiantuser_id");
                                   String id_unique = jo.getString("id_unique");
                                int annee_id = jo.getInt("annee_id");
                                 int departement_id = jo.getInt("departement_id");
                             //    String password = jo.getString("password");
                             String address = jo.getString("address");
                                String telephone = jo.getString("telephone");
                                String email = jo.getString("email");
                                String first_name = jo.getString("first_name");
                                String last_name = jo.getString("last_name");
                              int semestre_id = jo.getInt("semestre_id");


                                metudiantsmodel = new etudiantsmodel();

                                metudiantsmodel.setEtudiantuser_id(etudiantuser_id);
                                metudiantsmodel.setId_unique(id_unique);
                                    metudiantsmodel.setAnnee_id(annee_id);
                                   metudiantsmodel.setDepartement_id(departement_id);
                              //      metudiantsmodel.setPassword(password);
                                    metudiantsmodel.setAddress(address);
                                   metudiantsmodel.setTelephone(telephone);
                                metudiantsmodel.setEmail(email);
                                metudiantsmodel.setFirst_name(first_name);
                                metudiantsmodel.setLast_name(last_name);
                                   metudiantsmodel.setSemestre_id(semestre_id);



                                etudiant.add(metudiantsmodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        mListView.setAdapter(new CustomAdaptermodifieretudiant(getApplicationContext(),etudiant));
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post(jsonURL)
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{


                            JSONObject jo;

                            etudiant.clear();
                            etudiantsmodel metudiantsmodel;

                            for (int i = 0; i < response.length(); i++) {
                                jo = response.getJSONObject(i);

                               int etudiantuser_id = jo.getInt("etudiantuser_id");
                                   String id_unique = jo.getString("id_unique");
                                int annee_id = jo.getInt("annee_id");
                                 int departement_id = jo.getInt("departement_id");
                             //    String password = jo.getString("password");
                             String address = jo.getString("address");
                                String telephone = jo.getString("telephone");
                                String email = jo.getString("email");
                                String first_name = jo.getString("first_name");
                                String last_name = jo.getString("last_name");
                              int semestre_id = jo.getInt("semestre_id");


                                metudiantsmodel = new etudiantsmodel();

                                metudiantsmodel.setEtudiantuser_id(etudiantuser_id);
                                metudiantsmodel.setId_unique(id_unique);
                                    metudiantsmodel.setAnnee_id(annee_id);
                                   metudiantsmodel.setDepartement_id(departement_id);
                              //      metudiantsmodel.setPassword(password);
                                    metudiantsmodel.setAddress(address);
                                   metudiantsmodel.setTelephone(telephone);
                                metudiantsmodel.setEmail(email);
                                metudiantsmodel.setFirst_name(first_name);
                                metudiantsmodel.setLast_name(last_name);
                                   metudiantsmodel.setSemestre_id(semestre_id);



                                etudiant.add(metudiantsmodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        mListView.setAdapter(new CustomAdaptermodifieretudiant(getApplicationContext(),etudiant));

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
                refresh.setRefreshing(false);
            }
        });
    }

    private class CustomAdaptermodifieretudiant extends BaseAdapter {
        private Context mContext;
        private ArrayList<etudiantsmodel> mEtudiantsmodels;

        public CustomAdaptermodifieretudiant(Context context, ArrayList <etudiantsmodel>etudiantsmodels) {
            mContext = context;
            this.mEtudiantsmodels = etudiantsmodels;
        }

        @Override
        public int getCount() {
            return mEtudiantsmodels.size();
        }

        @Override
        public Object getItem(int position) {
            return mEtudiantsmodels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null)
            {
                convertView=LayoutInflater.from(mContext).inflate(R.layout.rowadminuser,parent,false);


            }
         //   TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);
   TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);
            TextView txtmail = (TextView) convertView.findViewById(R.id.emailuser) ;
            ImageView trashenseignant = (ImageView) convertView.findViewById(R.id.trashadmin);
            final etudiantsmodel etudiantsmodel =  (etudiantsmodel)  this.getItem(position);


            final int etudiantuser_id = etudiantsmodel.getEtudiantuser_id();
            final String id_unique = etudiantsmodel.getId_unique();
           final int annee_id=  etudiantsmodel.getAnnee_id();
            final int departement_id = etudiantsmodel.getDepartement_id();
      //      final String password = etudiantsmodel.getPassword();
            final String address = etudiantsmodel.getAddress();
            final String telephone = etudiantsmodel.getTelephone();
            final String email = etudiantsmodel.getEmail();
            final String first_name = etudiantsmodel.getFirst_name();
            final String last_name  = etudiantsmodel.getLast_name();
            final int semestre_id = etudiantsmodel.getSemestre_id();
            txtnom.setText(first_name+" "+last_name);
            txtmail.setText(email);

            trashenseignant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     deleteDetailActivity(first_name,etudiantuser_id+" ",last_name,email);
                }
            });


        //    txtnom.setText(first_name+"  "+last_name);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDetailActivity(etudiantuser_id+"",id_unique+"",annee_id+"",
                            departement_id+"",address,telephone,email,first_name,last_name,semestre_id+""/*,password*/);

                }
            });
            return convertView;

        }

    private void deleteDetailActivity(String ... details) {
            Intent intent = new Intent(mContext,deleteetudiant2.class);
            intent.putExtra("first_name",details[0]);
            intent.putExtra("etudiantuser_id",details[1]);
            intent.putExtra("last_name",details[2]);
            intent.putExtra("email",details[3]);

            mContext.startActivity(intent);
        }

        private void openDetailActivity(String ... details) {
            Intent intent = new Intent(mContext,modifieretudiant2.class);
            intent.putExtra("etudiantuser_id",details[0]+"");
            intent.putExtra("id_unique",details[1]+"");
            intent.putExtra("annee_id",details[2]+"");
            intent.putExtra("departement_id",details[3]+"");

            intent.putExtra("address",details[4]+"");
            intent.putExtra("telephone",details[5]+"");
            intent.putExtra("email",details[6]+"");
            intent.putExtra("first_name",details[7]+"");
            intent.putExtra("last_name",details[8]+"");
            intent.putExtra("semestre_id",details[9]+"");
         //   intent.putExtra("password",details[10]);




            mContext.startActivity(intent);
        }
    }
}
