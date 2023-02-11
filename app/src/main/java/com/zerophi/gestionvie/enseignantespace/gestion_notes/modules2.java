package com.zerophi.gestionvie.enseignantespace.gestion_notes;

import android.app.ProgressDialog;
import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.models.modulesmodel;
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

import androidx.appcompat.app.AppCompatActivity;

public class modules2 extends AppCompatActivity {
    Button btnsyncmodules;
    String jsonURL = Globalurls.selectmodule;
    ListView mListView;
    int enseignantuser_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules2);
         enseignantuser_id = sharedpref.readSharedSettingint(getApplicationContext(),"id",0);
           btnsyncmodules = (Button) findViewById(R.id.btnsyncenseignantmodule2);
        mListView = (ListView) findViewById(R.id.list_modules2);
         new Dowloader(modules2.this, jsonURL, mListView).execute();
        btnsyncmodules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  new Dowloader(modules2.this, jsonURL, mListView).execute();


            }
        });
    }

     private class Dowloader extends AsyncTask<Void, Integer, String> {
        modulesmodel c;

        Context mContext;
        String jurl;
        ListView mListView;
        ProgressDialog pd;

        public Dowloader(Context c, String jurl, ListView listView) {
            this.mContext = c;
            this.jurl = jurl;
            this.mListView = listView;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(mContext);
            pd.setTitle("Attendez s'il veut plait !");
            pd.setMessage("searching les modules :) !!! ");
            pd.show();
        }

        @Override
        protected String doInBackground(Void... params) {

            return downloadModules();

        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            pd.dismiss();
            ;

            if (s.startsWith("Error")) {
                Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();

            } else { // Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();

               Parser p = new Parser(mContext, s, mListView);
                p.execute();

            }
        }



        private String downloadModules() {




            Object connection = connect.connect(jurl);
            HttpURLConnection connection1 = (HttpURLConnection) connection;
            try {
                OutputStream os = new BufferedOutputStream(connection1.getOutputStream());
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

                  String dataurl = "enseignantuser_id=" + enseignantuser_id;

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
    private class Parser  extends AsyncTask<Void,Void,Boolean> {


        Context mContext;
        ListView mListView;
        String jdata;
        int jj;

        ArrayList<modulesmodel> modules = new ArrayList<>();

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
            pd.setTitle("attend ");
            pd.setMessage("atteint un moment !!! ");
            pd.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            return this.parsecontact();

        }


        private Boolean parsecontact() {
            try
            {

                JSONArray ja=new JSONArray(jdata);
                JSONObject jo;

                modules.clear();
                modulesmodel con;

                for (int i=0;i<ja.length();i++)
                {
                    jo=ja.getJSONObject(i);

                   int  module_id = jo.getInt("module_id");
                    String module_name = jo.getString("module_name");
                    int departement_id = jo.getInt("departement_id");
                    int semestre_id = jo.getInt("semestre_id");
               /* StringBuffer sb = new StringBuffer(contact_id);
                int enseignantuser_id =    Integer.parseInt(sb.substring(0,1));*/


                    con = new modulesmodel();

                    con.setModule_id(module_id);
                    con.setModule_name(module_name);
                    con.setDepartement_id(departement_id);
                    con.setSemestre_id(semestre_id);
                    modules.add(con);
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
            if(isParsed){
//Toast.makeText(Contact.this,contact.get(0).getEmail(),Toast.LENGTH_LONG).show();
                mListView.setAdapter(new CustomAdaptermodules(mContext, modules));

            }
            else {
                Toast.makeText(mContext, jdata, Toast.LENGTH_SHORT).show();
            }



        }

    }
    private static class connect{

        public static Object connect(String url)
        {

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

    private class CustomAdaptermodules extends BaseAdapter {

        private Context mContext;
        private ArrayList<modulesmodel> arraymodules;

        public CustomAdaptermodules(Context context, ArrayList <modulesmodel> arraymodules) {
            mContext = context;
            this.arraymodules = arraymodules;
        }

        @Override
        public int getCount() {
            return arraymodules.size();
        }

        @Override
        public Object getItem(int position) {
            return arraymodules.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null)
            {
                convertView=LayoutInflater.from(mContext).inflate(R.layout.row_modules,parent,false);
            }

            TextView moduletxt = (TextView) convertView.findViewById(R.id.moduletextenseignant);


            final modulesmodel module =  (modulesmodel)  this.getItem(position);

            final int module_id =  module.getModule_id();

            final String module_name = module.getModule_name();
               ImageView imageView = (ImageView) convertView.findViewById(R.id.moduleimageenseignant);
            Glide.with(getApplicationContext()).load(Globalurls.modulesimages+module_id+".jpg").into(imageView);

            moduletxt.setText(module_name);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
            openmodule(module_name , module_id );
                }
                });
            return convertView;

        }


}











 private void openmodule(String module_name, int module_id) {
        Intent intent = new Intent(getApplicationContext(),ajouternote.class);
        intent.putExtra("module_name",module_name);
        intent.putExtra("module_id",module_id);

        startActivity(intent);
    }
}
