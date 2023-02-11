package com.zerophi.gestionvie.enseignantespace.gestion_absence;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import com.zerophi.gestionvie.connection.connect;
import com.zerophi.gestionvie.enseignantespace.gestion_notes.CustomAdapteretudiant2;
import com.zerophi.gestionvie.enseignantespace.gestion_notes.ajouternote;
import com.zerophi.gestionvie.models.adminmodel;
import com.zerophi.gestionvie.models.enseignantmodel;
import com.zerophi.gestionvie.models.etudiantsmodel;
import com.zerophi.gestionvie.models.nocificationmodel;
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
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class abcensecl extends AppCompatActivity {

    public static String time, period;
    ListView listView;
    ListAdapter2 adapter;

    ArrayList<String> names;
    ArrayList<Integer> metudiantuser_id;
    Activity thisActivity = this;
    Spinner spinner;
    ArrayList<Integer> module_id;
    ArrayList<Boolean> attendanceList;
    ArrayList<String> module_name;
    int enseignantuser_id;
ArrayList<String> mn;
    int module_id2;
    private String module_name1;
String par;
    int posi;
    String moddi;
    int modi;
Button load;
    int semestre_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abcensecl);
        listView = (ListView) findViewById(R.id.attendanceListViwe);
        enseignantuser_id = sharedpref.readSharedSettingint(getApplicationContext(), "id", 0);
        module_name = new ArrayList<String>();
        module_id = new ArrayList<Integer>();

        load = (Button) findViewById(R.id.loadButton);

        new Dowloadermodule(abcensecl.this, Globalurls.selectmodule).execute();
        time = getIntent().getStringExtra("DATE");
        period = getIntent().getStringExtra("PERIOD");


        names = new ArrayList<String>();
        metudiantuser_id = new ArrayList<Integer>();
        attendanceList = new ArrayList<Boolean>();


        Button btn = (Button) findViewById(R.id.loadButton);

        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  loadListView(v);
                // Toast.makeText(getApplicationContext(),spinner.getSelectedItem()+"",Toast.LENGTH_SHORT).show();


            }
        });

        Button btnx = (Button) findViewById(R.id.buttonSaveAttendance);
        assert btnx != null;
        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Saving", Toast.LENGTH_LONG).show();
                adapter.saveAll();
            }
        });


        spinner = (Spinner) findViewById(R.id.spinnermodule);
/*String [] test = new String []{"sss","ddd"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,test);// module_name);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapterSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
           //     posi = position + 1;
           //     names.clear();

*/

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String it = spinner.getSelectedItem().toString();
                int r = spinner.getSelectedItemPosition();
       int module = module_id.get(r);
                if (module<5){
                    semestre_id = 1;
                }
                if(  module>= 5 && module<9){
                    semestre_id =2;
                }
                if (module>=9 && module<13){
                    semestre_id=3;

                }
                if(module<18 && module>=13){
                    semestre_id =4;

                }

                Toast.makeText(getApplicationContext(),r+" "+semestre_id,Toast.LENGTH_SHORT).show();
                new Dowloader(getApplicationContext(), Globalurls.selectetudiant, listView).execute();
            }
        });
    }




    private class Dowloader extends AsyncTask<Void, Integer, String> {

        Context mContext;
        String jurl;
        ListView mListView;
        Boolean isTrue;
        ProgressDialog pd;

        public Dowloader(Context c, String jurl, ListView listView) {
            this.mContext = c;
            this.jurl = jurl;
            this.mListView = listView;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(Void... params) {

            return downloadData();

        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            ;

            if (s.startsWith("Error")) {
                Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();

            } else {
                Parser p = new Parser(mContext, s, mListView);
                p.execute();

            }
        }

        private String downloadData() {
            try {
              //  String sa = sharedpref.readSharedSetting(mContext,"moddi","1");
                int mo = Integer.parseInt(moddi);
              //  int mo = sharedpref.readSharedSettingint(mContext,"modi",0);

      if (modi<5){
                    semestre_id = 1;
                }
                if(  modi>= 5 && modi<9){
                    semestre_id =2;
                }
                if (modi>=9 && modi<13){
                    semestre_id=3;

                }
                if(modi<18 && modi>=13){
                    semestre_id =4;

                }
         //       Toast.makeText(mContext," module "+modi + "semestre " +semestre_id,Toast.LENGTH_SHORT).show();
            } catch(NumberFormatException nfe) {
          //   Toast.makeText(mContext,"error parse number",Toast.LENGTH_SHORT).show();
            }

            Object connection = connect.connect(Globalurls.selectetudiantwithsemestre);
            HttpURLConnection connection1 = (HttpURLConnection) connection;
            try {

                OutputStream os = new BufferedOutputStream(connection1.getOutputStream());
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

                String LoginData = "semestre_id="+semestre_id;

                bw.write(LoginData);
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

    private class Dowloadermodule extends AsyncTask<Void, Integer, String> {

        Context mContext;
        String jurl;

        Boolean isTrue;
        ProgressDialog pd;

        public Dowloadermodule(Context c, String jurl) {
            this.mContext = c;
            this.jurl = jurl;

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(Void... params) {

            return downloadData();

        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            ;

            if (s.startsWith("Error")) {
                Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();

            } else {
                Parsermodule p = new Parsermodule(mContext, s);
                p.execute();


            }
        }

        private String downloadData() {

            Object connection = connect.connect(jurl);
            HttpURLConnection connection1 = (HttpURLConnection) connection;
            try {


                OutputStream os = new BufferedOutputStream(connection1.getOutputStream());
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

                String LoginData = "enseignantuser_id="+enseignantuser_id;

                bw.write(LoginData);
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

    public class Parser extends AsyncTask<Void, Void, Boolean> {
        Context mContext;
        ListView mListView;
        String jdata;
        int jj;

        ArrayList<etudiantsmodel> etudiant = new ArrayList<>();

        ProgressDialog pd;

        public Parser(Context context, String jdata, ListView listView) {
            this.mContext = context;
            this.jdata = jdata;
            this.mListView = listView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(Void... params) {


            return this.parseetudiant();


        }

        private Boolean parseetudiant() {
            try {

                JSONArray ja = new JSONArray(jdata);
                JSONObject jo;


                names.clear();
                metudiantuser_id.clear();
                attendanceList.clear();
                for (int i = 0; i < ja.length(); i++) {
                    jo = ja.getJSONObject(i);
                    int etudiantid = jo.getInt("etudiantuser_id");
                    //    String id_unique = jo.getString("id_unique");

                    String first_name = jo.getString("first_name");
                    String last_name = jo.getString("last_name");

                    //         int annee_id = jo.getInt("annee_id");
                    //       int departement_id = jo.getInt("departement_id");
                    //       int semestre_id = jo.getInt("semestre_id");

                    names.add(first_name + " " + last_name);
                    metudiantuser_id.add(etudiantid);
                    attendanceList.add(new Boolean(true));


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
//Toast.makeText(getApplicationContext(),""+moddi,Toast.LENGTH_SHORT).show();
            if (isParsed) {
               // Toast.makeText(mContext,names.get(1).toString(),Toast.LENGTH_LONG).show();
                  adapter = new ListAdapter2(abcensecl.this, names, metudiantuser_id, attendanceList, moddi);
        listView.setAdapter(adapter);
            } else {
                Toast.makeText(mContext, jdata, Toast.LENGTH_SHORT).show();
            }


        }
    }
    public class Parsermodule extends AsyncTask<Void, Void, Boolean> {
        Context mContext;

        String jdata;


   //     ArrayList<etudiantsmodel> etudiant = new ArrayList<>();

        ProgressDialog pd;

        public Parsermodule(Context context, String jdata) {
            this.mContext = context;
            this.jdata = jdata;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Boolean doInBackground(Void... params) {


            return this.parsemod();


        }

        private Boolean parsemod() {
            try {


                JSONArray ja = new JSONArray(jdata);
                   JSONObject jo;
                   module_id.clear();
                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);

                        int module_id2 = jo.getInt("module_id");
                        module_id.add(module_id2);


                    }
mn = new ArrayList<String>();
                    mn.clear();
                    for (int i = 0; i < module_id.size(); i++) {
                        if (module_id.get(i) == 1) {
                            module_name1 = module_id.get(i)+" mathematiques generales";
                        } else if (module_id.get(i) == 2) {
                            module_name1 = module_id.get(i)+" algorithmique et bases de programmation c";
                        } else if (module_id.get(i) == 3) {
                            module_name1 = module_id.get(i)+" langages et technique d'expression et de communication";
                        } else if (module_id.get(i) == 4) {
                            module_name1 = module_id.get(i)+" architecture des ordinateurs";
                        } else if (module_id.get(i) == 5) {
                            module_name1 = module_id.get(i)+" system d'information et bases de donnees";
                        } else if (module_id.get(i) == 6) {
                            module_name1 = module_id.get(i)+" algorithmique et structures des donnes";
                        } else if (module_id.get(i) == 7) {
                            module_name1 = module_id.get(i)+" systeme d'exploitation & reseaux";
                        } else if (module_id.get(i) == 8) {
                            module_name1 = module_id.get(i)+" environnement economique et juridique de l'entreprise";
                        } else if (module_id.get(i) == 9) {
                            module_name1 = module_id.get(i)+" interconnexion des reseaux";
                        } else if (module_id.get(i) == 10) {
                            module_name1 = module_id.get(i)+" base de donnnes avancees";
                        } else if (module_id.get(i) == 11) {
                            module_name1 = module_id.get(i)+" programmation orientees object java";
                        } else if (module_id.get(i) == 12) {
                            module_name1 = module_id.get(i)+" outils d'aide a la decision";
                        } else if (module_id.get(i) == 13) {
                            module_name1 = module_id.get(i)+" atelier genie logiciel";
                        } else if (module_id.get(i) == 14) {
                            module_name1 = module_id.get(i)+" claud computing securite";
                        } else if (module_id.get(i) == 15) {
                            module_name1 = module_id.get(i)+" administration services reseau";
                        } else if (module_id.get(i) == 16) {
                            module_name1 = module_id.get(i)+" projet de fin d'etude";
                        } else {
                            module_name1 = module_id.get(i)+" stage";
                        }
                        mn.add(module_name1);
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

            if (isParsed) {
               ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,mn);// module_name);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapterSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                par = parent.getItemAtPosition(position).toString();

                StringBuffer bww = new StringBuffer(par);
                moddi= bww.substring(0,2);


            modi = module_id.get(position);

       //       sharedpref.saveSharedSetting(mContext,"moddi",moddi);
                posi = position + 1;
               names.clear();

                  Toast.makeText(mContext,"salam " + module_id.get(position),Toast.LENGTH_SHORT).show();
         //   new Dowloader(mContext, Globalurls.selectetudiant, listView).execute();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
  //Toast.makeText(getApplicationContext(),"sl",Toast.LENGTH_SHORT).show();
            }
        });
            } else {
                Toast.makeText(mContext, jdata, Toast.LENGTH_SHORT).show();
            }


        }
    }
}