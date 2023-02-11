package com.zerophi.gestionvie.etudiantespace.notificationenseignant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

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


public class notificationenseignantetudiant extends AppCompatActivity {

    String jsonURL=Globalurls.selectenseignant;//"https://gestionviescolaire.000webhostapp.com/selectenseignant.php";
    ListView mListView;
    ArrayList<enseignantmodel> mEnseignantarray;
    Button btn;
    SwipeRefreshLayout refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifierenseignant);
          mEnseignantarray = new ArrayList<enseignantmodel>();
        mListView = (ListView) findViewById(R.id.enseignantlistview);
        btn= (Button) findViewById(R.id.btnsyncnenseignant);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refreshmodifierenseignant);
          AndroidNetworking.post(jsonURL)
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{


                            JSONObject jo;

                            mEnseignantarray.clear();
                            enseignantmodel menseignantmodel;

                            for (int i = 0; i < response.length(); i++) {
                                jo = response.getJSONObject(i);

                                int enseignantuser_id = jo.getInt("enseignantuser_id");
                                String id_unique = jo.getString("id_unique");
                               // int annee_id = jo.getInt("annee_id");

                                 //   String password = jo.getString("password");
                                String address = jo.getString("address");
                                String telephone = jo.getString("telephone");
                                String email = jo.getString("email");
                                String first_name = jo.getString("first_name");
                                String last_name = jo.getString("last_name");
                                String join_date = jo.getString("join_date");


                                menseignantmodel = new enseignantmodel();

                                menseignantmodel.setEnseignantuser_id(enseignantuser_id);
                                menseignantmodel.setId_unique(id_unique);


                             //   menseignantmodel.setPassword(password);
                                menseignantmodel.setAddress(address);
                                menseignantmodel.setTelephone(telephone);
                                menseignantmodel.setEmail(email);
                                menseignantmodel.setFirst_name(first_name);
                                menseignantmodel.setLast_name(last_name);
                                menseignantmodel.setJoin_date(join_date);


                                mEnseignantarray.add(menseignantmodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        mListView.setAdapter(new CustomAdaptermodifierenseignant(getApplicationContext(),mEnseignantarray));

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                     AndroidNetworking.post(jsonURL)
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{


                            JSONObject jo;

                            mEnseignantarray.clear();
                            enseignantmodel menseignantmodel;

                            for (int i = 0; i < response.length(); i++) {
                                jo = response.getJSONObject(i);

                                int enseignantuser_id = jo.getInt("enseignantuser_id");
                                String id_unique = jo.getString("id_unique");
                               // int annee_id = jo.getInt("annee_id");

                                 //   String password = jo.getString("password");
                                String address = jo.getString("address");
                                String telephone = jo.getString("telephone");
                                String email = jo.getString("email");
                                String first_name = jo.getString("first_name");
                                String last_name = jo.getString("last_name");
                                String join_date = jo.getString("join_date");


                                menseignantmodel = new enseignantmodel();

                                menseignantmodel.setEnseignantuser_id(enseignantuser_id);
                                menseignantmodel.setId_unique(id_unique);


                             //   menseignantmodel.setPassword(password);
                                menseignantmodel.setAddress(address);
                                menseignantmodel.setTelephone(telephone);
                                menseignantmodel.setEmail(email);
                                menseignantmodel.setFirst_name(first_name);
                                menseignantmodel.setLast_name(last_name);
                                menseignantmodel.setJoin_date(join_date);


                                mEnseignantarray.add(menseignantmodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        mListView.setAdapter(new CustomAdaptermodifierenseignant(getApplicationContext(),mEnseignantarray));
                        refresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
         //       new Dowloader(getApplicationContext(),jsonURL, mListView).execute();

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  new Dowloader(getApplicationContext(),jsonURL, mListView).execute();
                AndroidNetworking.post(jsonURL)
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{


                            JSONObject jo;

                            mEnseignantarray.clear();
                            enseignantmodel menseignantmodel;

                            for (int i = 0; i < response.length(); i++) {
                                jo = response.getJSONObject(i);

                                int enseignantuser_id = jo.getInt("enseignantuser_id");
                                String id_unique = jo.getString("id_unique");
                               // int annee_id = jo.getInt("annee_id");

                     //              String password = jo.getString("password");
                                String address = jo.getString("address");
                                String telephone = jo.getString("telephone");
                                String email = jo.getString("email");
                                String first_name = jo.getString("first_name");
                                String last_name = jo.getString("last_name");
                                String join_date = jo.getString("join_date");


                                menseignantmodel = new enseignantmodel();

                                menseignantmodel.setEnseignantuser_id(enseignantuser_id);
                                menseignantmodel.setId_unique(id_unique);


                         //          menseignantmodel.setPassword(password);
                                menseignantmodel.setAddress(address);
                                menseignantmodel.setTelephone(telephone);
                                menseignantmodel.setEmail(email);
                                menseignantmodel.setFirst_name(first_name);
                                menseignantmodel.setLast_name(last_name);
                                menseignantmodel.setJoin_date(join_date);


                                mEnseignantarray.add(menseignantmodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        mListView.setAdapter(new CustomAdaptermodifierenseignant(getApplicationContext(),mEnseignantarray));

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

            }
        });
    }


    private class CustomAdaptermodifierenseignant extends BaseAdapter {
        private Context mContext;
        private ArrayList<enseignantmodel> mEnseignant;
        public CustomAdaptermodifierenseignant(Context applicationContext, ArrayList<enseignantmodel> enseignantarray) {
              this.mContext = applicationContext;
            this.mEnseignant = enseignantarray;

        }
        @Override
        public int getCount() {
            return mEnseignant.size();
        }

        @Override
        public Object getItem(int position) {
            return mEnseignant.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null)
            {
                convertView=LayoutInflater.from(mContext).inflate(R.layout.rowrow,parent,false);


            }
        //    TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);
            TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);
            TextView txtmail = (TextView) convertView.findViewById(R.id.emailuser) ;

            final enseignantmodel enseignantmodel =  (enseignantmodel)  this.getItem(position);


            final int enseignantuser_id = enseignantmodel.getEnseignantuser_id();
            final String id_unique = enseignantmodel.getId_unique();
            final String join_date=  enseignantmodel.getJoin_date();
           // final String password = etudiantsmodel.getPassword();
            final String address = enseignantmodel.getAddress();
            final String telephone = enseignantmodel.getTelephone();
            final String email = enseignantmodel.getEmail();
            final String first_name = enseignantmodel.getFirst_name();
            final String last_name  = enseignantmodel.getLast_name();

            txtnom.setText(first_name+" "+last_name);
            txtmail.setText(email);
            txtmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mailIntent = new Intent();
                    mailIntent.setAction(Intent.ACTION_SEND);
                    mailIntent.setType("message/rfc822");
                    mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
                    startActivity(mailIntent);
                }
            });

         //   txtnom.setText(first_name+"  "+last_name);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DetailActivity(first_name,enseignantuser_id+"",last_name,
                            email/*,password*/);

                }
            });
            return convertView;

        }

        private void DetailActivity(String ... details) {
            Intent intent = new Intent(mContext, notificationenseignantetudiantmodule.class);
            intent.putExtra("first_name", details[0]);
            intent.putExtra("enseignantuser_id", details[1]);
            intent.putExtra("last_name", details[2]);
            intent.putExtra("email", details[3]);
            mContext.startActivity(intent);
        }



    }

}
