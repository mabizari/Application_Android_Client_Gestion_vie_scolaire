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

import com.zerophi.gestionvie.models.enseignantmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class deleteenseignant extends AppCompatActivity {
    String jsonURL= Globalurls.selectenseignant; //"https://gestionviescolaire.000webhostapp.com/selectenseignant.php";
    ListView mListView;
    Button btn;
SwipeRefreshLayout refresh ;
    ArrayList<enseignantmodel> enseignant = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteenseignant);
        mListView = (ListView) findViewById(R.id.deleteenseignantlisteview);
        btn = (Button) findViewById(R.id.btnsyncenseignantdelete);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refreshdeleteenseignant);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                 AndroidNetworking.post(jsonURL)
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{


                            JSONObject jo;

                            enseignant.clear();
                            enseignantmodel menseignantmodel;

                            for (int i = 0; i < response.length(); i++) {
                                jo = response.getJSONObject(i);

                                int enseignantuser_id = jo.getInt("enseignantuser_id");
                                String email = jo.getString("email");
                                String first_name = jo.getString("first_name");
                                String last_name = jo.getString("last_name");


                                menseignantmodel = new enseignantmodel();

                                menseignantmodel.setEnseignantuser_id(enseignantuser_id);
                                menseignantmodel.setEmail(email);
                                menseignantmodel.setFirst_name(first_name);
                                menseignantmodel.setLast_name(last_name);

                                enseignant.add(menseignantmodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        mListView.setAdapter(new CustomAdapterdeleteenseignant(getApplicationContext(),enseignant));
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

                            enseignant.clear();
                            enseignantmodel menseignantmodel;

                            for (int i = 0; i < response.length(); i++) {
                                jo = response.getJSONObject(i);

                                int enseignantuser_id = jo.getInt("enseignantuser_id");
                                String email = jo.getString("email");
                                String first_name = jo.getString("first_name");
                                String last_name = jo.getString("last_name");


                                menseignantmodel = new enseignantmodel();

                                menseignantmodel.setEnseignantuser_id(enseignantuser_id);
                                menseignantmodel.setEmail(email);
                                menseignantmodel.setFirst_name(first_name);
                                menseignantmodel.setLast_name(last_name);

                                enseignant.add(menseignantmodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        mListView.setAdapter(new CustomAdapterdeleteenseignant(getApplicationContext(),enseignant));
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
            }
        });
    }

    private class CustomAdapterdeleteenseignant extends BaseAdapter {
        private Context mContext;
        private ArrayList<enseignantmodel> mEnseignantmodelArrayList;

        public CustomAdapterdeleteenseignant(Context context, ArrayList <enseignantmodel>enseignantmodelArrayList) {
            mContext = context;
            this.mEnseignantmodelArrayList = enseignantmodelArrayList;
        }

        @Override
        public int getCount() {
            return mEnseignantmodelArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return mEnseignantmodelArrayList.get(position);
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
            enseignantmodel menseignantmodel =  (enseignantmodel)  this.getItem(position);


            final int enseignantuser_id = menseignantmodel.getEnseignantuser_id();
            final    String email = menseignantmodel.getEmail();
            final String first_name = menseignantmodel.getFirst_name();
            final  String last_name = menseignantmodel.getLast_name();


           txtnom.setText(first_name+" "+last_name);
            txtmail.setText(email);

          //  txtnom.setText(first_name+" "+last_name);
            trashenseignant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                      openDetailActivity(first_name,enseignantuser_id+" ",last_name,email);
                }
            });
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //  openDetailActivity(adminuser_id+"",admin_name,email,first_name,last_name,role_id+"");
                    openDetailActivity(first_name,enseignantuser_id+" ",last_name,email);

                }
            });
            return convertView;

        }



        private void openDetailActivity(String ... details) {
            Intent intent = new Intent(mContext,deleteenseignant2.class);
            intent.putExtra("first_name",details[0]);
            intent.putExtra("enseignantuser_id",details[1]);
            intent.putExtra("last_name",details[2]);
            intent.putExtra("email",details[3]);
            mContext.startActivity(intent);
        }
    }
}
