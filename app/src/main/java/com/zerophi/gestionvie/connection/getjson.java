package com.zerophi.gestionvie.connection;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class getjson extends AsyncTask <String,Void,String> {
    String url;
    Context c;
    String json_string;


    public getjson(Context c, String url){
this.url= url ;
this.c=c;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(c,"hh22",Toast.LENGTH_SHORT).show();
    }




    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url2= new URL (url);
            HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while ((json_string=bufferedReader.readLine())!=null)

            {
                sb.append(json_string+"\n");
            }
            bufferedReader.close();
            inputStream.close();
            connection.disconnect();
            return sb.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(c,s,Toast.LENGTH_SHORT).show();
    }}