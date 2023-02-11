package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.connection.CustomAdapter;
import com.zerophi.gestionvie.models.adminmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class deleteadmin extends AppCompatActivity {
    String jsonURL= Globalurls.selectadmin;//"https://gestionviescolaire.000webhostapp.com/selectadmin.php";
    ListView mListView;
    Button btn;
    ArrayList<adminmodel> admin = new ArrayList<>();
    SwipeRefreshLayout refresh ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteadmin);

        mListView = (ListView) findViewById(R.id.deleteadminlisteview);
        btn = (Button) findViewById(R.id.btnsyncadmindelete);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refreshsupprimeradmin) ;
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AndroidNetworking.post(jsonURL)
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{


                            JSONObject jo;

                            admin.clear();
                            adminmodel madminmodel;

                            for (int i = 0; i < response.length(); i++) {
                                jo = response.getJSONObject(i);

                                int adminuser_id = jo.getInt("adminuser_id");
                                String admin_name = jo.getString("admin_name");
                                String email = jo.getString("email");
                                String first_name = jo.getString("first_name");
                                String last_name = jo.getString("last_name");
                                int role_id = jo.getInt("role_id");


                                madminmodel = new adminmodel();

                                madminmodel.setAdminuser_id(adminuser_id);
                                madminmodel.setAdmin_name(admin_name);
                                madminmodel.setEmail(email);
                                madminmodel.setFirst_name(first_name);
                                madminmodel.setLast_name(last_name);
                                madminmodel.setRole_id(role_id);
                                admin.add(madminmodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        mListView.setAdapter(new CustomAdapterdelete(getApplicationContext(),admin));
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

                            admin.clear();
                            adminmodel madminmodel;

                            for (int i = 0; i < response.length(); i++) {
                                jo = response.getJSONObject(i);

                                int adminuser_id = jo.getInt("adminuser_id");
                                String admin_name = jo.getString("admin_name");
                                String email = jo.getString("email");
                                String first_name = jo.getString("first_name");
                                String last_name = jo.getString("last_name");
                                int role_id = jo.getInt("role_id");


                                madminmodel = new adminmodel();

                                madminmodel.setAdminuser_id(adminuser_id);
                                madminmodel.setAdmin_name(admin_name);
                                madminmodel.setEmail(email);
                                madminmodel.setFirst_name(first_name);
                                madminmodel.setLast_name(last_name);
                                madminmodel.setRole_id(role_id);
                                admin.add(madminmodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        mListView.setAdapter(new CustomAdapterdelete(getApplicationContext(),admin));
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
            }
        });
    }

    private class CustomAdapterdelete extends BaseAdapter {
        private Context mContext;
        private ArrayList<adminmodel> mAdminmodels;

        public CustomAdapterdelete(Context context, ArrayList <adminmodel>adminmodels) {
            mContext = context;
            this.mAdminmodels = adminmodels;
        }

        @Override
        public int getCount() {
            return mAdminmodels.size();
        }

        @Override
        public Object getItem(int position) {
            return mAdminmodels.get(position);
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
        //    TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);
              TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);
            TextView txtmail = (TextView) convertView.findViewById(R.id.emailuser) ;
            ImageView trashadmin = (ImageView) convertView.findViewById(R.id.trashadmin);

            adminmodel adminmodel =  (adminmodel)  this.getItem(position);


            final int adminuser_id = adminmodel.getAdminuser_id();
            //    final String admin_name = adminmodel.getAdmin_name();
            final    String email = adminmodel.getEmail();
            final String first_name = adminmodel.getFirst_name();
            final  String last_name = adminmodel.getLast_name();
            //   final  int role_id = adminmodel.getRole_id();


         //   txtnom.setText(first_name+""+last_name);

            txtnom.setText(first_name+" "+last_name);
            txtmail.setText(email);
                 trashadmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                       openDetailActivity(first_name,adminuser_id+" ",last_name,email);
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //  openDetailActivity(adminuser_id+"",admin_name,email,first_name,last_name,role_id+"");
                    openDetailActivity(first_name,adminuser_id+" ",last_name,email);

                }
            });
            return convertView;

        }



        private void openDetailActivity(String ... details) {
            Intent intent = new Intent(mContext,deleteadmin2.class);
            intent.putExtra("first_name",details[0]);
            intent.putExtra("adminuser_id",details[1]);
            intent.putExtra("last_name",details[2]);
            intent.putExtra("email",details[3]);


            //   intent.putExtra("role_id",details[5]);


            mContext.startActivity(intent);
        }
    }
}
