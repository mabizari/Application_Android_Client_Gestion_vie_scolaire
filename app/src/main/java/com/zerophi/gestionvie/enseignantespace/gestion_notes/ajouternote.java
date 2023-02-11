package com.zerophi.gestionvie.enseignantespace.gestion_notes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.models.etudiantsmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ajouternote extends AppCompatActivity {
    String jsonURL=Globalurls.selectetudiantwithsemestre;

    ListView mListView;
    Button btn,btn2;
    CustomAdapteretudiantnote2 adapter;
    ArrayList<etudiantsmodel> etudiant = new ArrayList<>();
    int semestre_id ;
    int module_id;
    String note="note";
    String module_name ;
SwipeRefreshLayout refreshajouternote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouternote);
        Intent intent =this.getIntent();
         module_id = intent.getExtras().getInt("module_id") ;


        if(module_id==1){
            module_name= "mathematiques generales";
        }else if (module_id==2){
            module_name= "algorithmique et bases de programmation c";
        }else if(module_id==3){
            module_name= "langages et technique d'expression et de communication";
        }else if(module_id==4){
            module_name= "architecture des ordinateurs";
        }else if(module_id==5){
            module_name= "system d'information et bases de donnees";
        }else if(module_id ==6){
            module_name= "algorithmique et structures des donnes";
        }else if(module_id==7){
            module_name= "systeme d'exploitation & reseaux";
        }else if(module_id==8){
            module_name= "environnement economique et juridique de l'entreprise";
        }else if(module_id==9){
            module_name= "interconnexion des reseaux";
        }else if(module_id==10){
            module_name= "base de donnnes avancees";
        }else if(module_id==11){
            module_name= "programmation orientees object java";
        }else if(module_id==12){
            module_name= "outils d'aide a la decision";
        }else if(module_id==13){
            module_name= "atelier genie logiciel";
        }else if(module_id ==14){
            module_name= "claud computing securite";
        }else if(module_id==15){
            module_name= "administration services reseau";
        }else if (module_id==16){
            module_name= "projet de fin d'etud";
        }else{
            module_name= "stage";
        }
refreshajouternote = (SwipeRefreshLayout) findViewById(R.id.refreshajouternote);
       if (module_id<5){
            semestre_id = 1;
        }
        if(  module_id>= 5 && module_id<9){
            semestre_id =2;
        }
        if (module_id>=9 && module_id<13){
            semestre_id=3;

        }
         if(module_id<18 && module_id>=13){
            semestre_id =4;

        }

        mListView = (ListView) findViewById(R.id.notelistview);
        btn = (Button) findViewById(R.id.btnsyncnote);

refreshajouternote.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        AndroidNetworking.post(Globalurls.selectetudiantwithsemestre)
                .addBodyParameter("semestre_id",semestre_id+"")
                .build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                //              Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                try
                {

                    //JSONArray ja=new JSONArray(response);
                    JSONObject jo;

                    etudiant.clear();
                    etudiantsmodel metudiantsmodel ;

                    for (int i=0;i<response.length();i++)
                    {
                        jo=response.getJSONObject(i);

                        int etudiantuser_id = jo.getInt("etudiantuser_id");
                        String id_unique = jo.getString("id_unique");

                        String first_name = jo.getString("first_name");
                        String last_name = jo.getString("last_name");

                        //      int annee_id = jo.getInt("annee_id");
                        //       int departement_id = jo.getInt("departement_id");
                        //       int semestre_id = jo.getInt("semestre_id");

                        metudiantsmodel = new etudiantsmodel();

                        metudiantsmodel.setEtudiantuser_id(etudiantuser_id);

                        metudiantsmodel.setFirst_name(first_name);
                        metudiantsmodel.setLast_name(last_name);
                        metudiantsmodel.setId_unique(id_unique);
                        //       metudiantsmodel.setDepartement_id(departement_id);
                        //    metudiantsmodel.setSemestre_id(semestre_id);
                        //       metudiantsmodel.setAnnee_id(annee_id);

                        etudiant.add(metudiantsmodel);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }
                adapter = new CustomAdapteretudiantnote2(ajouternote.this , etudiant,semestre_id,module_id);
                mListView.setAdapter( adapter);
                refreshajouternote.setRefreshing(false);
            }

            @Override
            public void onError(ANError anError) {

            }
        });

    }
});
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post(Globalurls.selectetudiantwithsemestre)
                        .addBodyParameter("semestre_id",semestre_id+"")
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
          //              Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                        try
                        {

                            //JSONArray ja=new JSONArray(response);
                            JSONObject jo;

                            etudiant.clear();
                            etudiantsmodel metudiantsmodel ;

                            for (int i=0;i<response.length();i++)
                            {
                                jo=response.getJSONObject(i);

                                int etudiantuser_id = jo.getInt("etudiantuser_id");
                             String id_unique = jo.getString("id_unique");

                                String first_name = jo.getString("first_name");
                                String last_name = jo.getString("last_name");

                         //      int annee_id = jo.getInt("annee_id");
                         //       int departement_id = jo.getInt("departement_id");
                         //       int semestre_id = jo.getInt("semestre_id");

                                metudiantsmodel = new etudiantsmodel();

                                metudiantsmodel.setEtudiantuser_id(etudiantuser_id);

                                metudiantsmodel.setFirst_name(first_name);
                                metudiantsmodel.setLast_name(last_name);
                                metudiantsmodel.setId_unique(id_unique);
                         //       metudiantsmodel.setDepartement_id(departement_id);
                            //    metudiantsmodel.setSemestre_id(semestre_id);
                         //       metudiantsmodel.setAnnee_id(annee_id);
                                                        
                                etudiant.add(metudiantsmodel);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        adapter = new CustomAdapteretudiantnote2(ajouternote.this , etudiant,semestre_id,module_id);
                      mListView.setAdapter( adapter);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


            }
        });
    }

    private class CustomAdapteretudiantnote2 extends BaseAdapter {
        int semestre_id,module_id;

        private Context mContext;
        private ArrayList<etudiantsmodel> mEtudiantsmodelArrayList;

        public CustomAdapteretudiantnote2(Context context, ArrayList<etudiantsmodel> etudiantsmodelArrayList,int semestre_id,int module_id) {
            this.mContext = context;
            this.mEtudiantsmodelArrayList = etudiantsmodelArrayList;
            this.semestre_id = semestre_id;
            this.module_id = module_id;
        }

        @Override
        public int getCount() {
            return mEtudiantsmodelArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return mEtudiantsmodelArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.row_etud_note, parent, false);
            }
            TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);
            TextView modtxt = (TextView) convertView.findViewById(R.id.emailuser);
            final TextView notetxt = (TextView) convertView.findViewById(R.id.note);

            etudiantsmodel metudinatmodel = (etudiantsmodel) this.getItem(position);

            final int etudiantuser_id = metudinatmodel.getEtudiantuser_id();


            final String first_name = metudinatmodel.getFirst_name();
            final String last_name = metudinatmodel.getLast_name();

            final String id_unique = metudinatmodel.getId_unique();
            // final int departement_id = metudinatmodel.getDepartement_id();
            //  final int semestre_id = metudinatmodel.getSemestre_id();
            //   final int annee_id = metudinatmodel.getAnnee_id();

            txtnom.setText(first_name+" " + last_name);
            modtxt.setText(module_name);
         AndroidNetworking.post(Globalurls.selectnoteens)
                        .addBodyParameter("etudiantuser_id",etudiantuser_id+"")
                        .addBodyParameter("module_id",module_id+"")
                        .addBodyParameter("semestre_id",semestre_id+"")
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
try{
                        JSONObject jo;

                                jo = response.getJSONObject(0);
                                if (jo.getInt("exist") == 1) {
                                   // Toast.makeText(getApplicationContext(), "tu as deja ajouter cet note", Toast.LENGTH_SHORT).show();
                                    note = jo.getString("note");
                                  notetxt.setText(note);

                                }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });



            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    openDetailActivity(etudiantuser_id + "", first_name, last_name,
                            id_unique + "");

                }
            });
            return convertView;

        }
        private void openDetailActivity(String... details) {
            Intent intent = new Intent(mContext, ajoternote2.class);

            intent.putExtra("etudiantuser_id", details[0]);
            intent.putExtra("first_name", details[1]+"");
            intent.putExtra("last_name", details[2]+"");
            intent.putExtra("id_unique", details[3]+"");
            intent.putExtra("semestre_id",semestre_id);
            intent.putExtra("module_id",module_id);
            intent.putExtra("note",note);

            mContext.startActivity(intent);
        }
    }
}
