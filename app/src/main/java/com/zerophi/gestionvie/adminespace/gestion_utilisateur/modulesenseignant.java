package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

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

import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.models.enseignantmodel;
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

public class modulesenseignant extends AppCompatActivity {
    String jsonURL = Globalurls.selectenseignant;//"https://gestionviescolaire.000webhostapp.com/selectenseignant.php";
    ListView mListView;
    Button btn;
SwipeRefreshLayout refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulesenseignant);
        mListView = (ListView) findViewById(R.id.list_enseignant);
        btn = (Button) findViewById(R.id.btnsyncenseignantmodule);
        new Dowloader(modulesenseignant.this, jsonURL, mListView).execute();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Dowloader(modulesenseignant.this, jsonURL, mListView).execute();
            }
        });

        refresh = (SwipeRefreshLayout) findViewById(R.id.swiperefreshmodule);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                  new Dowloader(modulesenseignant.this, jsonURL, mListView).execute();
                  refresh.setRefreshing(false);
            }
        });
    }

    private class Dowloader extends AsyncTask<Void, Integer, String> {
        enseignantmodel c;

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
            pd.setMessage("searching les enseignants :) !!! ");
            pd.show();
        }

        @Override
        protected String doInBackground(Void... params) {

            return downloadEnseignant();

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



    private String downloadEnseignant() {
        final int utilisateur_id = sharedpref.readSharedSettingint(modulesenseignant.this, "id", 0);
        Object connection = connect.connect(jurl);
        HttpURLConnection connection1 = (HttpURLConnection) connection;
        try {
            OutputStream os = new BufferedOutputStream(connection1.getOutputStream());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

      //      String dataurl = "utilisateur_id=" + utilisateur_id;

          //  bw.write(dataurl);
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

        ArrayList <enseignantmodel> enseignants = new ArrayList<>();

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

            enseignants.clear();
            enseignantmodel con;

            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                String username = jo.getString("name");
                int enseignantuser_id = jo.getInt("enseignantuser_id");
                String email = jo.getString("email");
                String first_name = jo.getString("first_name");
                String last_name = jo.getString("last_name");

               /* StringBuffer sb = new StringBuffer(contact_id);
                int enseignantuser_id =    Integer.parseInt(sb.substring(0,1));*/


                con = new enseignantmodel();

           con.setName(username);
           con.setEnseignantuser_id(enseignantuser_id);
           con.setFirst_name(first_name);
           con.setLast_name(last_name);
           con.setEmail(email);

                enseignants.add(con);
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
            mListView.setAdapter(new CustomAdapternotic(mContext, enseignants));

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
    public static Object connectget(String jurl)
    {
        try
        {
            URL url=new URL(jurl);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();

            //CON PROPS
            con.setRequestMethod("GET");
            con.setConnectTimeout(15000);
            con.setReadTimeout(15000);
            con.setDoInput(true);

            return con;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();

        } catch (IOException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();

        }
    }

    }

    private class CustomAdapternotic extends BaseAdapter {

    private Context mContext;
    private ArrayList<enseignantmodel> arraycontact;

    public CustomAdapternotic(Context context, ArrayList <enseignantmodel> arraycontact) {
        mContext = context;
        this.arraycontact = arraycontact;
    }

    @Override
    public int getCount() {
        return arraycontact.size();
    }

    @Override
    public Object getItem(int position) {
        return arraycontact.get(position);
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

        TextView txtusername = (TextView) convertView.findViewById(R.id.nomuser);
        TextView txtmail = (TextView) convertView.findViewById(R.id.emailuser) ;
        ImageView trashenseignant = (ImageView) convertView.findViewById(R.id.trashadmin);

        final enseignantmodel enseignant =  (enseignantmodel)  this.getItem(position);

        final String username =  enseignant.getName();
        final int enseignantuser_id = enseignant.getEnseignantuser_id();


        txtusername.setText(enseignant.getFirst_name()+" "+enseignant.getLast_name());
        txtmail.setText(enseignant.getEmail());
        trashenseignant.setVisibility(View.GONE);



        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Toast.makeText(getApplicationContext(),enseignantuser_id+"haha",Toast.LENGTH_LONG).show();
                openSendMessage(username+"",enseignantuser_id+"");

            }
        });
        return convertView;

    }



    private void openSendMessage(String ... details) {
        Intent intent = new Intent(mContext,modulesenseignant2.class);
        intent.putExtra("username",details[0]);
        intent.putExtra("enseignantuser_id",details[1]);
        mContext.startActivity(intent);
    }}

}