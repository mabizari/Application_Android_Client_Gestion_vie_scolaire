package com.zerophi.gestionvie.connection;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class Dowloader extends AsyncTask<Void,Integer, String> {

        Context mContext;
        String jurl;
        ListView mListView;
        Boolean isTrue ;
        ProgressDialog pd;

        public Dowloader(Context c, String jurl, ListView listView) {
            this.mContext = c;
            this.jurl = jurl;
            this.mListView = listView;
        }




    @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd=new ProgressDialog(mContext);
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

            pd.dismiss();;

            if(s.startsWith("Error"))
            {
                Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();

            }else
            {
                Parser p=new Parser(mContext,s,mListView);
                p.execute();

            }
        }

        private String downloadData()  {

         Object connection = connect.connectget(jurl);
         HttpURLConnection connection1 = (HttpURLConnection) connection;
            try {
                if (connection1.getResponseCode()==connection1.HTTP_OK){

                     InputStream is=new BufferedInputStream(connection1.getInputStream());
                     BufferedReader br=new BufferedReader(new InputStreamReader(is));

                        String line;
                       StringBuffer jsonData=new StringBuffer();
                         while ((line=br.readLine()) != null)
                       {
                           jsonData.append(line+"\n");
                       }
                          br.close();
                       is.close();
                   return jsonData.toString();
                }else
                   {
                       return "Error "+connection1.getResponseMessage();
                   }
            } catch (IOException e) {
                e.printStackTrace();
                return "Error"+e.getMessage();
            }
        }

}

