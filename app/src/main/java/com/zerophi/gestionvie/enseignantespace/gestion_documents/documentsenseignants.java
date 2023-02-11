package com.zerophi.gestionvie.enseignantespace.gestion_documents;

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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.etudiantespace.document.documentgrid;
import com.zerophi.gestionvie.etudiantespace.document.pdfdoc;
import com.zerophi.gestionvie.models.pdfdoccl;
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

public class documentsenseignants extends AppCompatActivity {
private String jsonURL = Globalurls.selectmodule;//"https://gestionviescolaire.000webhostapp.com/selectmodule.php";


    Button btn;
    SwipeRefreshLayout refresh;
    ProgressBar myProgressBar;
    GridViewAdapter adapter;
    GridView myGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentsenseignants);
            final int enseignantuser_id = sharedpref.readSharedSettingint(getApplicationContext(), "id", 0);
            final int module_id = getIntent().getIntExtra("module_id",0);
            final String module_name = getIntent().getStringExtra("module_name");
            final String semestre_id = getIntent().getStringExtra("semestre_id");
            final String departemnt_id= getIntent().getStringExtra("departement_id");
  refresh = (SwipeRefreshLayout) findViewById(R.id.swiperefreshenseignantdocument);
myGridView  = findViewById(R.id.documentenseignantgridview);
 btn  = (Button) findViewById(R.id.documentenseignantdownloadbtn);

myProgressBar = findViewById(R.id.documentenseignantprogressbar);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
           //     new JsonDownloader(documentsenseignants.this,enseignantuser_id,module_id).retreive(myGridView,myProgressBar);
                  new Dowloader(documentsenseignants.this,Globalurls.selectdocument,myGridView,module_id).execute();
                refresh.setRefreshing(false);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), insertdocument.class);
            intent.putExtra("module_name", module_name);
            intent.putExtra("module_id", module_id);
            intent.putExtra("semestre_id", semestre_id);
            intent.putExtra("departement_id",departemnt_id);
            startActivity(intent);
            }
        });
    }

/*

    public class JsonDownloader  {
        private final Context c;

      //  private  final static String pdf_site= Globalurls.selectdocument;//"https://gestionviescolaire.000webhostapp.com/selectdocument.php";
       // private GridViewAdapter adapter;
        int enseignantuser_id, module_id;
        public JsonDownloader(Context c,int enseignantuser_id,int module_id) {
            this.c = c;
            this.enseignantuser_id = enseignantuser_id;
            this.module_id = module_id;
        }

        public void retreive(final GridView gv , final ProgressBar myProgressBar) {

            final ArrayList<pdfdoccl> pdfdocuments = new ArrayList<>();
         //   myProgressBar.setIndeterminate(true);
        //    myProgressBar.setVisibility(View.VISIBLE);

      /*      AndroidNetworking.post(Globalurls.selectdocument)
                    .setPriority(Priority.MEDIUM)
                    .addBodyParameter("enseignantuser_id",enseignantuser_id+"")
                    .addBodyParameter("module_id",module_id+"")
                    .build().getAsJSONArray(new JSONArrayRequestListener() {
                @Override
                public void onResponse(JSONArray response) {
                    JSONObject jo = null;
                    pdfdoccl p ;
                try {
                    for (int i = 0; i < response.length(); i++) {
                        jo = response.getJSONObject(i);
                    int id = jo.getInt("document_id");
                    String name =jo.getString("name");
                    int module_id = jo.getInt("module_id");
                    String pdfurl= jo.getString("pdfurl");
                    String pdfurlicon = jo.getString ("pdfurlicon");
                    String enseignantname = jo.getString ("enseignant_name");
                    int enseignantuser_id = jo.getInt("enseignantuser_id");


                    p = new pdfdoccl();
                    p.setDocument_id(id);
                    p.setNameautheur(enseignantname);
                    p.setModule_id(module_id);
                    p.setPdfurl(pdfurl);
                    p.setPdfurlicon(pdfurlicon);
                    p.setEnseignantuser_id(enseignantuser_id);
                    p.setTitre(name);

                        pdfdocuments.add(p);
                    }
                 adapter = new GridViewAdapter(c,pdfdocuments);
                 gv.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(),pdfdocuments.get(0).getNameautheur()+"",Toast.LENGTH_LONG).show();
                    myProgressBar.setVisibility(View.GONE);
                }catch (JSONException e){
                    myProgressBar.setVisibility(View.GONE);
                    Toast.makeText(c,"java cant parse  json ",Toast.LENGTH_LONG).show();

                }

                }


                @Override
                public void onError(ANError anError) {
                    anError.printStackTrace();
                    myProgressBar.setVisibility(View.GONE);
                    Toast.makeText(c,"unsucess"+anError.getErrorDetail(),Toast.LENGTH_LONG).show();
                }
            });
*/
/*
      new Dowloader(c,Globalurls.selectdocument,gv,module_id);
        }
    }*/
       public class Dowloader extends AsyncTask<Void, Integer, String> {

        Context mContext;
        String jurl;
        GridView mListView;
        Boolean isTrue;
        ProgressDialog pd;
        int module_id;
              //   final int module_id = getIntent().getIntExtra("module_id",0);
        public Dowloader(Context c, String jurl, GridView listView,int module_id) {
            this.mContext = c;
            this.jurl = jurl;
            this.mListView = listView;
            this.module_id = module_id;
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
        final int utilisateur_id = sharedpref.readSharedSettingint(documentsenseignants.this, "id", 0);
        Object connection = connect.connect(jurl);
        HttpURLConnection connection1 = (HttpURLConnection) connection;
        try {
            OutputStream os = new BufferedOutputStream(connection1.getOutputStream());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

           String dataurl = "enseignantuser_id=" + utilisateur_id+"&module_id="+module_id;

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
           GridView mListView;
           String jdata;

           final ArrayList<pdfdoccl> pdfdocuments = new ArrayList<>();

           ProgressDialog pd;

           public Parser(Context context, String jdata, GridView listView) {
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

               return this.parsedocument();

           }


           private Boolean parsedocument() {
               try {

                   JSONArray ja = new JSONArray(jdata);
                   JSONObject jo2 = ja.getJSONObject(0);
                   int error = jo2.getInt("error");
                   if(error==0) {
                       pdfdocuments.clear();

                       JSONObject jo = null;


                       pdfdoccl p;

                       for (int i = 0; i < ja.length(); i++) {
                           jo = ja.getJSONObject(i);
                           int id = jo.getInt("document_id");
                           String name = jo.getString("name");
                           int module_id = jo.getInt("module_id");
                           String pdfurl = jo.getString("pdfurl");
                           String pdfurlicon = jo.getString("pdfurlicon");
                           String enseignantname = jo.getString("enseignant_name");
                           int enseignantuser_id = jo.getInt("enseignantuser_id");


                           p = new pdfdoccl();
                           p.setDocument_id(id);
                           p.setNameautheur(enseignantname);
                           p.setModule_id(module_id);
                           p.setPdfurl(pdfurl);
                           p.setPdfurlicon(pdfurlicon);
                           p.setEnseignantuser_id(enseignantuser_id);
                           p.setTitre(name);

                           pdfdocuments.add(p);
                       }


                       //   myProgressBar.setVisibility(View.GONE);
                       return true;
                   }
                   else{
                       return  false;
                   }
               } catch (Exception e) {
             //  myProgressBar.setVisibility(View.GONE);
               Toast.makeText(mContext, "java cant parse  json ", Toast.LENGTH_LONG).show();
               return false;
               }

           }
            @Override
        protected void onPostExecute(Boolean isParsed) {
            super.onPostExecute(isParsed);

             //   Toast.makeText(getApplicationContext(), pdfdocuments.get(0).getNameautheur() + "", Toast.LENGTH_LONG).show();
            pd.dismiss();
            if (isParsed) {


                adapter = new GridViewAdapter(mContext, pdfdocuments);
                mListView.setAdapter(adapter);

            } else {
                Toast.makeText(mContext, "no cours yet", Toast.LENGTH_SHORT).show();
            }


        }

    }

    public class GridViewAdapter extends BaseAdapter {
        Context c ;
        ArrayList<pdfdoccl> pdfdocument;

        public GridViewAdapter(Context c, ArrayList<pdfdoccl> pdfdocument) {
            this.c = c;
            this.pdfdocument = pdfdocument;
        }

        @Override
        public int getCount() {
            return pdfdocument.size();
        }

        @Override
        public Object getItem(int position) {
            return pdfdocument.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(c).inflate(R.layout.document_row_model,parent,false);

            }
            TextView txtname = (TextView) convertView.findViewById(R.id.documentpdfnametxt);
            TextView txtautheur = (TextView) convertView.findViewById(R.id.documentauthortxt);
            ImageView pdficon =(ImageView) convertView.findViewById(R.id.documentimageview);

            final pdfdoccl pdf =(pdfdoccl) this.getItem(position);
            txtname.setText(pdf.getTitre());
            txtautheur.setText(pdf.getNameautheur());

            // if (pdf.getPdfurl() != null && pdf.getPdfurl().length()>0){
            //  Picasso.get().load(R.drawable.biology).into(pdficon);
            //     pdficon.setImageDrawable(R.drawable.ic_picture_as_pdf_black_24dp);
            //       Picasso.get().load(pdf.getPdfurlicon()).placeholder(R.drawable.book).into(pdficon);
            //    }else
            // {
           // Toast.makeText(getApplicationContext(),"empty "+pdf.getPdfurl(),Toast.LENGTH_SHORT).show();
            //    Picasso.get().load(R.drawable.biology).into(pdficon);
            //     }
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c,pdf.getTitre(),Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(c,pdfdoc.class);
                    intent.putExtra("path",pdf.getPdfurl());
                    c.startActivity(intent);
                }
            });
            return convertView;
        }
    }

}
