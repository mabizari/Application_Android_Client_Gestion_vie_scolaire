package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.sharedpref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class enseignantgestion extends AppCompatActivity {
Button btnajouter, btnsupprimer,btnmodifier;
ArrayList <module_model> modules;
Button btn_modules;
int enseignantuser_id;
ArrayList<String> module_name;
    private String module_name1;
ArrayList<Integer> module_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enseignantgestion);

  enseignantuser_id = sharedpref.readSharedSettingint(getApplicationContext(),"id",0);

        modules = new ArrayList<>();
        modules.clear();
            btnajouter = (Button) findViewById(R.id.ajouteruserenseignant);
            btnsupprimer = (Button) findViewById(R.id.supprimeruserenseignant);
            btnmodifier = (Button) findViewById(R.id.modifieruserenseignant);
            btn_modules = (Button) findViewById(R.id.choisirmodulesenseignant);
            btnajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),ajouterenseignant.class);
            startActivity(intent);
            }
        });

        btnsupprimer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),deleteenseignant.class));
            }
        });
        btnmodifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           startActivity(new Intent(getApplicationContext(),modifierenseignant.class));
            }
        });
        btn_modules.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                  enseignantuser_id = sharedpref.readSharedSettingint(getApplicationContext(),"id",0);
        module_name = new ArrayList<String>();
        module_id =new ArrayList<Integer>();
                module_id =new ArrayList<Integer>();
                AndroidNetworking.post(Globalurls.selectmodule)
                .addBodyParameter("enseignantuser_id",enseignantuser_id+"")
                .build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                try
                {
                    JSONObject jo;
                    for (int i=0;i<response.length();i++)
                    {
                        jo=response.getJSONObject(i);

                        int module_id2= jo.getInt("module_id");
                        module_id.add(module_id2);


                    }

                    module_name.clear();
                    for (int i = 0; i<module_id.size();i++){
                        if(module_id.get(i) ==1){
                            module_name1= "mathematiques generales";
                        }else if (module_id.get(i)==2){
                            module_name1= "algorithmique et bases de programmation c";
                        }else if(module_id.get(i)==3){
                            module_name1= "langages et technique d'expression et de communication";
                        }else if(module_id.get(i)==4){
                            module_name1= "architecture des ordinateurs";
                        }else if(module_id.get(i)==5){
                            module_name1= "system d'information et bases de donnees";
                        }else if(module_id.get(i) ==6){
                            module_name1= "algorithmique et structures des donnes";
                        }else if(module_id.get(i)==7){
                            module_name1= "systeme d'exploitation & reseaux";
                        }else if(module_id.get(i)==8){
                            module_name1= "environnement economique et juridique de l'entreprise";
                        }else if(module_id.get(i)==9){
                            module_name1= "interconnexion des reseaux";
                        }else if(module_id.get(i)==10){
                            module_name1= "base de donnnes avancees";
                        }else if(module_id.get(i)==11){
                            module_name1= "programmation orientees object java";
                        }else if(module_id.get(i)==12){
                            module_name1= "outils d'aide a la decision";
                        }else if(module_id.get(i)==13){
                            module_name1= "atelier genie logiciel";
                        }else if(module_id.get(i) ==14){
                            module_name1= "claud computing securite";
                        }else if(module_id.get(i)==15){
                            module_name1= "administration services reseau";
                        }else if (module_id.get(i)==16){
                            module_name1= "projet de fin d'etud";
                        }else{
                            module_name1= "stage";
                        }
                        module_name.add(module_name1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onError(ANError anError) {

            }});
                startActivity(new Intent (getApplicationContext(),modulesenseignant.class));
            }
        });
      /*  AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/selectallmodules.php")
                .build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                try {


                    JSONObject jo;

                    modules.clear();


                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jo = response.getJSONObject(i);
                            module_model md = new module_model();

                            String module_id = jo.getString("module_id");
                            String departement_id = jo.getString("departement_id");
                            String module_name = jo.getString("module_name");
                            String semestre_id = jo.getString("semestre_id");
                            StringBuffer sbmodule_id = new StringBuffer(module_id);
                            int module_idint = Integer.parseInt(sbmodule_id.substring(0, 1).toString());

                            StringBuffer sbdepartement_id = new StringBuffer(departement_id);
                            int departemnt_idint = Integer.parseInt(sbdepartement_id.substring(0, 1).toString());

                            StringBuffer sbsemestre_id = new StringBuffer(semestre_id);
                            int semestre_idint = Integer.parseInt(sbsemestre_id.substring(0, 1));

                            md.setSemestre_id(semestre_idint);
                            md.setDepartement_id(departemnt_idint);
                            md.setModule_id(module_idint);
                            md.setModule_name(module_name);

                            modules.add(md);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });*/
/*

        btn_modules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getApplicationContext(),modulesenseignant.class);
                intent.putExtra("listallmodules",modules);
              startActivity(myintent);

            }
        });  */
    }
}
