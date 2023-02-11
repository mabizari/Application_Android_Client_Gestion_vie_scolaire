package com.zerophi.gestionvie.etudiantespace.notificationenseignant;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.connection.errors;
import com.zerophi.gestionvie.enseignantespace.gestion_nocification.notification_enseignantdetail;
import com.zerophi.gestionvie.etudiantespace.nocification.nocification_detail;
import com.zerophi.gestionvie.models.notificationenseignantmodel;
import com.zerophi.gestionvie.sharedpref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class notificationetudiantfin extends AppCompatActivity {
 Button sync;
    ListView listnotificationadmin;
    SwipeRefreshLayout swiperefreshnotificationadmin;
    String jsonURL = Globalurls.selectnotificationenseignant;
    CustomAdapternoti adapter;
    int enseignantuser_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationetudiantfin);
      //    final int enseignantuser_id = sharedpref.readSharedSettingint(getApplicationContext(), "id", 0);
          enseignantuser_id = getIntent().getIntExtra("enseignantuser_id",0);
            final int module_id = getIntent().getIntExtra("module_id",0);
            final String module_name = getIntent().getStringExtra("module_name");
            final String semestre_id = getIntent().getStringExtra("semestre_id");
            final String departemnt_id= getIntent().getStringExtra("departement_id");


      listnotificationadmin = (ListView) findViewById(R.id.list_notificationenseignant);
        swiperefreshnotificationadmin = (SwipeRefreshLayout) findViewById(R.id.swiperefreshnotificationenseignant);
        sync = (Button) findViewById(R.id.btnsyncenseignantnotification);
          sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new Dowloader(notification_admin.this, jsonURL, listnotificationadmin).execute();
              //  startActivity(new Intent(getApplicationContext(),drawerDashAdmin.class));
            /*    notification_enseignant.super.onBackPressed();
                new Dowloader(notification_enseignant.this, jsonURL, listnotificationadmin).execute();*/
       /*      Intent intent = new Intent(getApplicationContext(), insertnotificationenseignant.class);
            intent.putExtra("module_name", module_name);
            intent.putExtra("module_id", module_id);
            intent.putExtra("semestre_id", semestre_id);
            intent.putExtra("departement_id", departemnt_id);
            startActivity(intent);
*/
            }
        });
             swiperefreshnotificationadmin.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Dowloader(notificationetudiantfin.this, jsonURL, listnotificationadmin).execute();
                swiperefreshnotificationadmin.setRefreshing(false);
            }
        });
        new Dowloader(notificationetudiantfin.this, jsonURL, listnotificationadmin).execute();

    }

      public class Dowloader extends AsyncTask<Void, Integer, String> {

        Context mContext;
        String jurl;
        ListView mListView;
        Boolean isTrue;
        ProgressDialog pd;
                 final int module_id = getIntent().getIntExtra("module_id",0);
        public Dowloader(Context c, String jurl, ListView listView) {
            this.mContext = c;
            this.jurl = jurl;
            this.mListView = listView;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(mContext);
            pd.setTitle("find data ... !!!");
            pd.setMessage("atteint");
            pd.show();
        }

        @Override
        protected String doInBackground(Void... params) {

            return downloadData();

        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pd.dismiss();
            ;

            if (s.startsWith("Error")) {
                Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();

            } else {
                Parser p = new Parser(mContext, s, mListView);
                p.execute();

            }
        }

        private String downloadData() {
    //    final int utilisateur_id = sharedpref.readSharedSettingint(notificationetudiantfin.this, "id", 0);
        Object connection = connect.connect(jurl);
        HttpURLConnection connection1 = (HttpURLConnection) connection;
        try {
            OutputStream os = new BufferedOutputStream(connection1.getOutputStream());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

           String dataurl = "enseignantuser_id=" + enseignantuser_id+"&module_id="+module_id;

           bw.write(dataurl);
            bw.flush();
            bw.close();
            os.close();
            if (connection1.getResponseCode() == connection1.HTTP_OK) {

                InputStream is = new BufferedInputStream(connection1.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line;
                StringBuffer jsonData = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    jsonData.append(line + "\n");
                }
                br.close();
                is.close();
                return jsonData.toString();
            } else {
                return "Error " + connection1.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }


    }

    private static class connect {

        public static Object connect(String url) {

            HttpURLConnection connection = null;

            try {
                connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("POST");
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(25000);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(true);
                connection.setDefaultUseCaches(true);
                return connection;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Error : url n'existe pas";

            } catch (IOException e) {
                e.printStackTrace();
                return "Error : erreur de connection !! ";
            }


        }


    }

       public class Parser extends AsyncTask<Void, Void, Boolean> {
        Context mContext;
        ListView mListView;
        String jdata;
        int jj;

        ArrayList<notificationenseignantmodel> nocifications = new ArrayList<>();
        ProgressDialog pd;

        public Parser(Context context, String jdata, ListView listView) {
            this.mContext = context;
            this.jdata = jdata;
            this.mListView = listView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(mContext);
            pd.setTitle("atteient");
            pd.setMessage("atteint un moment !!! ");
            pd.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            return this.parsenocification();

        }


        private Boolean parsenocification() {
            try {

                JSONArray ja = new JSONArray(jdata);
                JSONObject jo;

                nocifications.clear();
                notificationenseignantmodel noci;

                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);



                    int notic_id = jo.getInt("notic_id");
                    int departement_id = jo.getInt("departement_id");
                    int module_id = jo.getInt("module_id");
                    int enseignantuser_id = jo.getInt("enseignantuser_id");
                    String notic_titre =  jo.getString("notic_titre");
                    String notic_description = jo.getString ("notic_description");
                    String publish_date = jo.getString ("publish_date");
                    String enseignant_name = jo.getString("enseignant_name");

                    noci = new notificationenseignantmodel();

                noci.setNotic_id(notic_id);
                noci.setDepartement_id(departement_id);
                noci.setModule_id(module_id);
                noci.setEnseignantuser_id(enseignantuser_id);
                noci.setNotic_titre(notic_titre);
                noci.setNotic_description(notic_description);
                noci.setPublish_date(publish_date);
                noci.setEnseignant_name(enseignant_name);



                    nocifications.add(noci);
                }

                return true;

            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean isParsed) {
            super.onPostExecute(isParsed);

            pd.dismiss();
            if (isParsed) {


              adapter = new CustomAdapternoti(mContext, nocifications);
                mListView.setAdapter(adapter);


            } else {
                Toast.makeText(mContext, jdata, Toast.LENGTH_SHORT).show();
            }


        }

    }
        private class CustomAdapternoti extends BaseAdapter {
        private Context mContext;
        private ArrayList<notificationenseignantmodel> noticifications;

        public CustomAdapternoti(Context context, ArrayList<notificationenseignantmodel> notic) {
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

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.rows_fragment_a, parent, false);
            }
            TextView txtvtitre = (TextView) convertView.findViewById(R.id.titreadmin);
            TextView txtvtdesc = (TextView) convertView.findViewById(R.id.descadmin);
            TextView txtvtdate = (TextView) convertView.findViewById(R.id.dateadmin);
          //  ImageView trash = (ImageView) convertView.findViewById(R.id.MainIconadmin);

            notificationenseignantmodel nocificationmodel = (notificationenseignantmodel) this.getItem(position);

            final String noticTitre = nocificationmodel.getNotic_titre();
            final String noticDescription = nocificationmodel.getNotic_description();
            final String publish_date = nocificationmodel.getPublish_date();
            final int dept = nocificationmodel.getDepartement_id();
            final String depts = dept + "";
            final int notic_id = nocificationmodel.getNotic_id();

            txtvtitre.setText(noticTitre);
            txtvtdesc.setText(noticDescription);
            txtvtdate.setText(publish_date);
           /* trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                       AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("est ce que tu est sure que vous voulez supprimer cet notification ?"+ notic_id)
                        .setCancelable(false)
                        .setPositiveButton("oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                new noticificationdelete(getApplicationContext(), Globalurls.deletenotificationenseignant, notic_id);
                                      new Dowloader(notificationetudiantfin.this, jsonURL, listnotificationadmin).execute();
                            }
                        })
                        .setNegativeButton("non", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                }
            });*/
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    openDetailActivity( noticTitre, noticDescription, publish_date, depts,notic_id+" " );

                }
            });
            return convertView;

        }


        private void openDetailActivity(String... details) {
            Intent intent = new Intent(mContext, notification_enseignantdetail.class);

            intent.putExtra("noticTitre", details[0]);
            intent.putExtra("noticDescription", details[1]);
            intent.putExtra("publish_date", details[2]);
            intent.putExtra("depts", details[3]);



            mContext.startActivity(intent);
        }
        private class noticificationdelete extends AsyncTask<Void, Void, String> {
            Context c;
            String urlAddress;

            int notic_id;
          //  nocificationmodel mNocificationmodel;

            ProgressDialog pd;

            public noticificationdelete(Context c, String urlAddress, int notic_id) {
                this.c = c;
                this.urlAddress = urlAddress;
                this.notic_id = notic_id;

              //  mNocificationmodel = new nocificationmodel();
              //  mNocificationmodel.setNotic_id(notic_id);
                //  mNocificationmodel.setDept(rgdept.getCheckedRadioButtonId());
                //  mNocificationmodel.setDept(1);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(c, "atteint !!! ", Toast.LENGTH_LONG).show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(c, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                return this.notic();

            }

            private String notic() {
                Object mconnect = connect.connect(urlAddress);
                if (mconnect.toString().startsWith("Error")) {
                    return mconnect.toString();
                }
                try {
                    HttpURLConnection connection = (HttpURLConnection) mconnect;

                    OutputStream os = new BufferedOutputStream(connection.getOutputStream());
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                    String LoginData = "notic_id=" + notic_id;

                    bw.write(LoginData);
                    bw.flush();
                    bw.close();
                    os.close();
                    int responsecode = connection.getResponseCode();
                    if (responsecode == connection.HTTP_OK) {

                        InputStream is = new BufferedInputStream(connection.getInputStream());
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String line;
                        StringBuffer response = new StringBuffer();
                        while ((line = br.readLine()) != null) {
                            response.append(line + "\n");
                        }
                        br.close();
                        is.close();
                        return response.toString();


                    } else {
                        return errors.RESPONSE_ERROR + String.valueOf(responsecode);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Get response


                return "go go go !!! ";
            }
    }



    }


}
