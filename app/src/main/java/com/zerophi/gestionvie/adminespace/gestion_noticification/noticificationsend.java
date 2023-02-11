package com.zerophi.gestionvie.adminespace.gestion_noticification;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.zerophi.gestionvie.connection.connect;
import com.zerophi.gestionvie.connection.errors;
import com.zerophi.gestionvie.models.nocificationmodel;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class noticificationsend extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddress;
    TextInputEditText notic_titre;
    EditText notic_description;
    RadioGroup rgdept;

    nocificationmodel mNocificationmodel;

    ProgressDialog pd;

    public noticificationsend(Context c, String urlAddress, TextInputEditText notic_titre, EditText notic_description, RadioGroup rgdept) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.notic_titre = notic_titre;
        this.notic_description = notic_description;
        this.rgdept = rgdept;

    mNocificationmodel = new nocificationmodel();
    mNocificationmodel.setNotic_titre(notic_titre.getText().toString());
    mNocificationmodel.setNotic_description(notic_description.getText().toString());
  //  mNocificationmodel.setDept(rgdept.getCheckedRadioButtonId());
        mNocificationmodel.setDept(1);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(c, "atteint !!! ", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(c,s,Toast.LENGTH_LONG).show();
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
            String LoginData = new packdatanocification(mNocificationmodel).packnocification();

            bw.write(LoginData);
            bw.flush();
            bw.close();
            os.close();
            int responsecode = connection.getResponseCode();
            if(responsecode==connection.HTTP_OK){

                InputStream is = new BufferedInputStream(connection.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while((line=br.readLine())!=null)
                {
                    response.append(line+"\n");
                }
                br.close();
                is.close();
                return response.toString();


            }else {
                return errors.RESPONSE_ERROR + String.valueOf(responsecode);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
          //Get response


        return "go go go !!! ";
    }
    public class packdatanocification {

        nocificationmodel mNocificationmodel;
        private String mFormBody;

        public packdatanocification(nocificationmodel nocificationmodel) {
            mNocificationmodel = nocificationmodel;
        }
        public String packnocification(){
            ;
            List<NameValuePair> formData =new ArrayList<NameValuePair>();
            formData.add(new BasicNameValuePair("notic_titre",mNocificationmodel.getNotic_titre()));
            formData.add(new BasicNameValuePair("notic_description",mNocificationmodel.getNotic_description()));
            formData.add(new BasicNameValuePair("dept",mNocificationmodel.getDept()+""));

            if(formData == null){
                mFormBody =null;

            }
            StringBuilder sb = new StringBuilder();
            for(int i =0; i<formData.size();i++) {
                NameValuePair item = formData.get(i);

                sb.append(URLEncoder.encode(item.getName()));
                sb.append("=");
                sb.append(URLEncoder.encode(item.getValue()));
                if (i != (formData.size() - 1)) {
                    sb.append("&");
                }
            }
            mFormBody=sb.toString();

            return mFormBody;
        }

    }

}
