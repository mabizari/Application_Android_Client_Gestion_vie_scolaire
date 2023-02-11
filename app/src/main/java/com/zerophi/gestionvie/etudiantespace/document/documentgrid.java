package com.zerophi.gestionvie.etudiantespace.document;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.squareup.picasso.Picasso;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.models.pdfdoccl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class documentgrid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentgrid);
final GridView myGridView  = findViewById(R.id.documentgridview);
Button btnretreive  = findViewById(R.id.documentdownloadbtn);
final ProgressBar myProgressBar = findViewById(R.id.documentprogressbar);
btnretreive.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        new JsonDownloader(documentgrid.this).retreive(myGridView,myProgressBar);
    }
});


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
                Toast.makeText(getApplicationContext(),"empty "+pdf.getPdfurl(),Toast.LENGTH_SHORT).show();
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


    public class JsonDownloader  {
        private final Context c;

        private static final String pdf_site= "https://gestionviescolaire.000webhostapp.com/selectdocument.php";
        private GridViewAdapter adapter;

        public JsonDownloader(Context c) {
            this.c = c;
        }

        public void retreive(final GridView gv , final ProgressBar myProgressBar) {

            final ArrayList<pdfdoccl> pdfdocuments = new ArrayList<>();
            myProgressBar.setIndeterminate(true);
            myProgressBar.setVisibility(View.VISIBLE);

            AndroidNetworking.post(Globalurls.selectdocument)
                    .setPriority(Priority.MEDIUM)
                    .addBodyParameter("enseignantuser_id",1+"")
                    .addBodyParameter("module_id",1+"")
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

        }
    }
}
