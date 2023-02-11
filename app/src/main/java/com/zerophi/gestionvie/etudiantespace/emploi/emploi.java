package com.zerophi.gestionvie.etudiantespace.emploi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.zerophi.gestionvie.MainActivity;
import com.zerophi.gestionvie.R;

public class emploi extends Activity  {

    private ListView list;
    Button btn;
    private LazyListAdapter listAdapter;
    private String serverFileBase;
    private String serverPicBase ;
    private String fileName ;
    private String config;
    private static JSONArray configFileJson;

    private boolean masterAvail = false;

    private static ArrayList<String> fetchURL;
    private static ArrayList<String> imageTitle ;
    private static ArrayList<String> imageDesc ;



    private int listPicHeight;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploi);
        list = (ListView) findViewById(R.id.list);


        serverFileBase = "https://gestionviescolaire.000webhostapp.com/";
        serverPicBase = "https://gestionviescolaire.000webhostapp.com/pics";
        fileName = "config.txt";

        config = "";
        JSONArray configFileJson = null;

        masterAvail = false;

        fetchURL = new ArrayList<String>();
        imageTitle = new ArrayList<String>();
        imageDesc = new ArrayList<String>();
        listPicHeight = utils.getListPicHeight(emploi.this);

        // Clear the ArrayLists
        fetchURL.clear();
        imageTitle.clear();
        imageDesc.clear();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new sync();
            }

        });
    }




    public boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public class sync extends AsyncTask<Void,Void,String> {
        final ProgressDialog pd = ProgressDialog.show(emploi.this,"Loading","Loading file list and images...",true, false);


        @Override
        protected String doInBackground(Void... voids) {
            return this.down();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"before",Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"after"+s,Toast.LENGTH_LONG).show();

        }

        private String down() {

            //File masterFil = new File(configFile);
            if (checkInternetConnection()) {
                config = utils.DownloadText(serverFileBase + fileName);
                //save the configFile to the local storage so we can lazyload the images offline
                masterAvail = true;
            } else {
                noConnection();
            }

            if (masterAvail) {
                // setup the fetchURL ArrayLists
                try {
                    configFileJson = new JSONArray(config);
                    for (int i=0; i<configFileJson.length(); i++){
                        String title = jsonGetter2(configFileJson.getJSONArray(i),"title").toString();
                        imageTitle.add(title);
                        String fname = jsonGetter2(configFileJson.getJSONArray(i),"filename").toString();
                        fetchURL.add(fname);
                        String desc = jsonGetter2(configFileJson.getJSONArray(i),"desc").toString();
                        imageDesc.add(desc);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listAdapter = new LazyListAdapter(emploi.this, fetchURL, emploi.this);
                list.setAdapter(listAdapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView parent, View v, final int position, long id) {
                        Toast.makeText(emploi.this, "List item selected: " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                jsonConnection();
            }pd.dismiss();
            return null;
        }

    }










    private class LazyListAdapter extends BaseAdapter {
        private Activity activity;
        private ArrayList<String> data;
        private LayoutInflater inflater=null;
        public imageload imageLoader;
        LinearLayout.LayoutParams params;

        public LazyListAdapter(Context context, ArrayList<String> d, Activity a) {
            super();
            imageLoader=new imageload(context);
            this.data = d;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            activity = a;
            data = d;
        }

        public int getCount() {
            return data.size();
        }
        public Object getItem(int position) {
            return position;
        }
        public long getItemId(int position) {
            return position;
        }
        class ViewHolder {
            public ImageView image;
            public TextView title;
            public TextView desc;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            // Inflate the view
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.liste_item, null);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.list_image);
                holder.title = (TextView) convertView.findViewById(R.id.list_title);
                holder.desc = (TextView) convertView.findViewById(R.id.list_desc);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String imageurl = data.get(position);
            holder.image.setTag(imageurl);

            int picHeight = listPicHeight;
            int picLength = (int) ((float) picHeight / 1.5);

            params = new LinearLayout.LayoutParams(picLength,picHeight);
            params.gravity=Gravity.CENTER;
            params.height=picHeight;
            params.width=picLength;

            holder.image.setLayoutParams(params);
            holder.title.setText(imageTitle.get(position));
            holder.desc.setText(imageDesc.get(position));

            imageLoader.DisplayImage(serverPicBase + imageurl, holder.image);

            return convertView;
        }
    }

    public void noConnection() {
        AlertDialog alertDialog = new AlertDialog.Builder(emploi.this).create();
        alertDialog.setTitle("Connection");
        alertDialog.setIcon(android.R.drawable.stat_sys_warning);
        alertDialog.setMessage("Server not reachable.");
        alertDialog.setCancelable(false);
        alertDialog.setButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            } });
        alertDialog.show();
    }

    public void exConnection() {
        AlertDialog alertDialog = new AlertDialog.Builder(emploi.this).create();
        alertDialog.setTitle("Exception");
        alertDialog.setIcon(android.R.drawable.stat_sys_warning);
        alertDialog.setMessage("Exception.");
        alertDialog.setCancelable(false);
        alertDialog.setButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            } });
        alertDialog.show();
    }

    public void jsonConnection() {
        AlertDialog alertDialog = new AlertDialog.Builder(emploi.this).create();
        alertDialog.setTitle("JSON Error");
        alertDialog.setIcon(android.R.drawable.stat_sys_warning);
        alertDialog.setMessage("Problem parsing JSON file.");
        alertDialog.setCancelable(false);
        alertDialog.setButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            } });
        alertDialog.show();
    }


    private Object jsonGetter2(JSONArray json, String key) {
        Object value = null;
        for (int i=0; i<json.length(); i++) {
            try {
                JSONObject obj = json.getJSONObject(i);
                if (obj.has(key)) {
                    value = obj.get(key);
                }
            } catch (JSONException e) {
                Log.v("jsonGetter2 Exception",e.toString());
            }
        }
        return value;
    }

    @Override
    protected void onDestroy() {
        list.setOnItemClickListener(null);
        list.setAdapter(null);
        super.onDestroy();
    }

}

