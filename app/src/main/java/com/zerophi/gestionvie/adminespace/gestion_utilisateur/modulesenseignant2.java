package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.bumptech.glide.Glide;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import com.zerophi.gestionvie.models.enseignantmodel;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class modulesenseignant2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int pos;
    private String par;
    Spinner spinner;
    Button btnajoutermodule;
    Button btnsyncmodules;
    TextView nomprof;
    private String ajoutermoduleurl = Globalurls.ajoutermoduleenseignant;//"https://gestionviescolaire.000webhostapp.com/ajoutermoduleenseignant.php";
    SwipeRefreshLayout refresh;
    ArrayList<Integer> module_id;
    ArrayList<Integer> modulesdejaexist;
    ArrayList<String> module_name;
    String module_name1;
    String jsonURL = Globalurls.selectmodule;// "https://gestionviescolaire.000webhostapp.com/selectmodule.php";
    ListView mListView;
String enseignantuser_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulesenseignant2);
        refresh = (SwipeRefreshLayout) findViewById(R.id.swiperefreshmodules2) ;
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                module_name = new ArrayList<String>();
                module_id = new ArrayList<Integer>();
                  new Dowloader(modulesenseignant2.this, jsonURL, mListView).execute();
                  refresh.setRefreshing(false);
            }
        });
        nomprof = (TextView) findViewById(R.id.moduleenseignantnomprof);
   Intent intent =this.getIntent();
        final String name = intent.getExtras().getString("username");
       enseignantuser_id = intent.getExtras().getString("enseignantuser_id");
        nomprof.setText(name);
        spinner = (Spinner) findViewById(R.id.choisir_module);
        btnajoutermodule = (Button) findViewById(R.id.btnajoutermodule);
        btnsyncmodules = (Button) findViewById(R.id.btnsyncmodules);
        mListView = (ListView) findViewById(R.id.list_modules);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.modules, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
modulesdejaexist = new ArrayList<>();

        btnsyncmodules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), pos + " " + par + enseignantuser_id+ ""+name, Toast.LENGTH_LONG).show();

                module_name = new ArrayList<String>();
                module_id = new ArrayList<Integer>();
                  new Dowloader(modulesenseignant2.this, jsonURL, mListView).execute();
            refresh.setRefreshing(false);

            }
        });
        btnajoutermodule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean a =true ;
               new ajoutermodule(modulesenseignant2.this, ajoutermoduleurl, enseignantuser_id, pos+1).execute();
                new Dowloader(modulesenseignant2.this, jsonURL, mListView).execute();

                for (int i =0; i<modulesdejaexist.size();i++){
                  if(pos+1 == modulesdejaexist.get(i)){
                      a=false;
                  }

              }
              if(a){
                  new ajoutermodule(modulesenseignant2.this, ajoutermoduleurl, enseignantuser_id, pos+1).execute();
              }else{
                  Toast.makeText(getApplicationContext(),"cette module déja exist pour cet enseignant !! ",Toast.LENGTH_LONG).show();
              }
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

        final String name = getIntent().getExtras().getString("name");
        final String enseignantuser_id = getIntent().getExtras().getString("enseignantuser_id");

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

        ArrayList <modulesmodel> modules = new ArrayList<>();

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
                modulesdejaexist.clear();
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
                    modulesdejaexist.add(module_id);
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
            final modulesmodel module =  (modulesmodel)  this.getItem(position);

            final int module_id =  module.getModule_id();

            final String module_name = module.getModule_name();

            TextView txtusername = (TextView) convertView.findViewById(R.id.moduletextenseignant);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.moduleimageenseignant);
            Glide.with(getApplicationContext()).load(Globalurls.modulesimages+module_id+".jpg").into(imageView);




            txtusername.setText(module_name+module_id);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
            opendeletemodule(module_name , module_id+" " ,enseignantuser_id );
                }
                });
            return convertView;

        }


}


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
        par = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), position + " " + par + "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public class ajoutermodule extends AsyncTask<Void, Void, String> {
        Context c;
        String urlAddress;
        String enseignantuser_id;
        int module_id;

        ProgressDialog pd;

        public ajoutermodule(Context c, String urlAddress, String enseignantuser_id, int module_id) {
            this.c = c;
            this.urlAddress = urlAddress;


            this.enseignantuser_id = enseignantuser_id;
            this.module_id = module_id;
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
            return this.send();

        }

        private String send() {
            Object mconnect = connect.connect(urlAddress);
            if (mconnect.toString().startsWith("Error")) {
                return mconnect.toString();
            }
            try {
                HttpURLConnection connection = (HttpURLConnection) mconnect;

                OutputStream os = new BufferedOutputStream(connection.getOutputStream());
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                String dataurl = "enseignantuser_id=" + enseignantuser_id + "&module_id=" + module_id;
                bw.write(dataurl);
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
                    //
                    return response.toString();


                } else {
                    return "erreurs" + String.valueOf(responsecode);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            //Get response


            return "go go go !!! ";
        }


    }



 private void opendeletemodule(String ... details) {
        Intent intent = new Intent(getApplicationContext(),deletemodulesenseignant.class);
        intent.putExtra("module_name",details[0]);
        intent.putExtra("module_id",details[1]);
        intent.putExtra("enseignantuser_id",details[2]);
        startActivity(intent);
    }
}
