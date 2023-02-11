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

import com.zerophi.gestionvie.models.etudiantsmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class deleteetudiant extends AppCompatActivity {
    String jsonURL=Globalurls.selectetudiant;/*"https://gestionviescolaire.000webhostapp.com/selectetudiant.php";*/
    ListView mListView;
    Button btn;
    ArrayList<etudiantsmodel> etudiant = new ArrayList<>();
    SwipeRefreshLayout refresh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteetudiant);
        mListView = (ListView) findViewById(R.id.deleteetudiantlisteview);
        btn = (Button) findViewById(R.id.btnsyncetudiantdelete);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refreshdeleteetudiant);

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
                           //     String id_unique = jo.getString("id_unique");
                           //     int annee_id = jo.getInt("annee_id");
                           //     int departement_id = jo.getInt("departement_id");
                           //     String password = jo.getString("password");
                           /*     String address = jo.getString("address");
                                String telephone = jo.getString("telephone");*/
                                String email = jo.getString("email");
                                String first_name = jo.getString("first_name");
                                String last_name = jo.getString("last_name");
                              //  int semestre_id = jo.getInt("semestre_id");


                                metudiantsmodel = new etudiantsmodel();

                                metudiantsmodel.setEtudiantuser_id(etudiantuser_id);
                           //     metudiantsmodel.setId_unique(id_unique);
                          //      metudiantsmodel.setAnnee_id(annee_id);
                          //      metudiantsmodel.setDepartement_id(departement_id);
                          //      metudiantsmodel.setPassword(password);
                          //      metudiantsmodel.setAddress(address);
                         //       metudiantsmodel.setTelephone(telephone);
                                metudiantsmodel.setEmail(email);
                                metudiantsmodel.setFirst_name(first_name);
                                metudiantsmodel.setLast_name(last_name);
                           //     metudiantsmodel.setSemestre_id(semestre_id);



                                etudiant.add(metudiantsmodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        mListView.setAdapter(new CustomAdapterdeleteetudiant(getApplicationContext(),etudiant));
                        refresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
                      refresh.setRefreshing(false);
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
                           //     String id_unique = jo.getString("id_unique");
                           //     int annee_id = jo.getInt("annee_id");
                           //     int departement_id = jo.getInt("departement_id");
                           //     String password = jo.getString("password");
                           /*     String address = jo.getString("address");
                                String telephone = jo.getString("telephone");*/
                                String email = jo.getString("email");
                                String first_name = jo.getString("first_name");
                                String last_name = jo.getString("last_name");
                              //  int semestre_id = jo.getInt("semestre_id");


                                metudiantsmodel = new etudiantsmodel();

                                metudiantsmodel.setEtudiantuser_id(etudiantuser_id);
                           //     metudiantsmodel.setId_unique(id_unique);
                          //      metudiantsmodel.setAnnee_id(annee_id);
                          //      metudiantsmodel.setDepartement_id(departement_id);
                          //      metudiantsmodel.setPassword(password);
                          //      metudiantsmodel.setAddress(address);
                         //       metudiantsmodel.setTelephone(telephone);
                                metudiantsmodel.setEmail(email);
                                metudiantsmodel.setFirst_name(first_name);
                                metudiantsmodel.setLast_name(last_name);
                           //     metudiantsmodel.setSemestre_id(semestre_id);



                                etudiant.add(metudiantsmodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        mListView.setAdapter(new CustomAdapterdeleteetudiant(getApplicationContext(),etudiant));
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
                refresh.setRefreshing(false);
            }
        });
    }

    private class CustomAdapterdeleteetudiant extends BaseAdapter {
        private Context mContext;
        private ArrayList<etudiantsmodel> mEtudiantsmodels;

        public CustomAdapterdeleteetudiant(Context context, ArrayList <etudiantsmodel>etudiantsmodels) {
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
          //  TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);
  TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);
            TextView txtmail = (TextView) convertView.findViewById(R.id.emailuser) ;
            ImageView trashenseignant = (ImageView) convertView.findViewById(R.id.trashadmin);
            final etudiantsmodel etudiantsmodel =  (etudiantsmodel)  this.getItem(position);


            final int etudiantuser_id = etudiantsmodel.getEtudiantuser_id();
            //    final String admin_name = adminmodel.getAdmin_name();
            final    String email = etudiantsmodel.getEmail();
            final String first_name = etudiantsmodel.getFirst_name();
            final  String last_name = etudiantsmodel.getLast_name();
            //   final  int role_id = adminmodel.getRole_id();
 txtnom.setText(first_name+" "+last_name);
            txtmail.setText(email);

        //    txtnom.setText(first_name+"  "+last_name);

            trashenseignant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                      openDetailActivity(first_name,etudiantuser_id+" ",last_name,email);
                }
            });
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    openDetailActivity(first_name,etudiantuser_id+" ",last_name,email);

                }
            });
            return convertView;

        }



        private void openDetailActivity(String ... details) {
            Intent intent = new Intent(mContext,deleteetudiant2.class);
            intent.putExtra("first_name",details[0]);
            intent.putExtra("etudiantuser_id",details[1]);
            intent.putExtra("last_name",details[2]);
            intent.putExtra("email",details[3]);

            mContext.startActivity(intent);
        }
    }
}
