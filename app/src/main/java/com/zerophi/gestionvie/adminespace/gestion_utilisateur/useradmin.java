package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.widget.ANImageView;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.sharedpref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class useradmin extends Fragment {
TextView btnadmin,btnenseignant,btnetudiant;
LinearLayout admingestion, enseignantgestion , etudiantgestion ;
Button btnajouteradmin, btnsupprimeradmin, btnmodifieradmin;
Button btnajouterenseignant, btnsupprimerenseignant,btnmodifierenseignant;
Button btnajouteretudiant, btnsupprimeretudiant,btnmodifieretudiant;
ArrayList<module_model> modules;
Button btn_modules;
int enseignantuser_id;
ArrayList<String> module_name;
    private String module_name1;
ArrayList<Integer> module_id;
  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useradmin);*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_useradmin,container,false);
        btnadmin = (TextView) view.findViewById(R.id.admingestion);
        btnenseignant = (TextView) view.findViewById(R.id.enseignantuser);
        btnetudiant = (TextView) view.findViewById(R.id.etudiantuser);

        admingestion = (LinearLayout) view.findViewById(R.id.admingestionlinear);
        btnajouteradmin = (Button) view.findViewById(R.id.ajouteruseradmin);
        btnsupprimeradmin = (Button) view.findViewById(R.id.supprimeruseradmin);
        btnmodifieradmin = (Button) view.findViewById(R.id.modifieruseradmin);
        btnajouteradmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),ajouteradmin.class);
                startActivity(intent);
            }
        });

        btnsupprimeradmin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(),deleteadmin.class));
            }
        });
        btnmodifieradmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(),modifieradmin.class));
            }
        });

        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent (getActivity().getApplicationContext(),admingestion.class);
                startActivity(intent);*/
              if (admingestion.getVisibility() == View.VISIBLE){
                   admingestion.setVisibility(View.GONE);
              }else{
                  admingestion.setVisibility(View.VISIBLE);
              }
            }
        });

        // ici pour l'enseignant
        enseignantgestion = (LinearLayout) view.findViewById(R.id.enseignantgestion);
        btnenseignant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent intent = new Intent (getActivity().getApplicationContext(), enseignantgestion.class);
                startActivity(intent);*/
             if (enseignantgestion.getVisibility() == View.VISIBLE){
                 enseignantgestion.setVisibility(View.GONE);
             }else{
                 enseignantgestion.setVisibility(View.VISIBLE);
             }
            }
        });

  enseignantuser_id = sharedpref.readSharedSettingint(getActivity().getApplicationContext(),"id",0);

        modules = new ArrayList<>();
        modules.clear();
            btnajouterenseignant = (Button) view.findViewById(R.id.ajouteruserenseignant);
            btnsupprimerenseignant = (Button) view.findViewById(R.id.supprimeruserenseignant);
            btnmodifierenseignant = (Button) view.findViewById(R.id.modifieruserenseignant);
            btn_modules = (Button) view.findViewById(R.id.choisirmodulesenseignant);
            btnajouterenseignant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(getActivity().getApplicationContext(),ajouterenseignant.class);
            startActivity(intent);
            }
        });

        btnsupprimerenseignant.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
        startActivity(new Intent(getActivity().getApplicationContext(),deleteenseignant.class));
            }
        });
        btnmodifierenseignant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           startActivity(new Intent(getActivity().getApplicationContext(),modifierenseignant.class));
            }
        });
        btn_modules.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                  enseignantuser_id = sharedpref.readSharedSettingint(getActivity().getApplicationContext(),"id",0);
        module_name = new ArrayList<String>();
        module_id =new ArrayList<Integer>();
                module_id =new ArrayList<Integer>();
                AndroidNetworking.post(Globalurls.selectmodule/*"https://gestionviescolaire.000webhostapp.com/selectmodule.php"*/)
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
                startActivity(new Intent (getActivity().getApplicationContext(),modulesenseignant.class));
            }
        });
//pour etudiant
        etudiantgestion = (LinearLayout) view.findViewById(R.id.etudiantgestion);
           btnajouteretudiant = (Button) view.findViewById(R.id.ajouteruseretudiant);
            btnsupprimeretudiant = (Button) view.findViewById(R.id.supprimeruseretudiant);
            btnmodifieretudiant = (Button) view.findViewById(R.id.modifieruseretudiant);
        btnajouteretudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent (getActivity().getApplicationContext(),ajouteretudiant.class);
            startActivity(intent);
            }
        });

        btnsupprimeretudiant.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
startActivity(new Intent(getActivity().getApplicationContext(),deleteetudiant.class));
            }
        });
        btnmodifieretudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 startActivity(new Intent(getActivity().getApplicationContext(),modifieretudiant.class));
            }
        });
        btnetudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent (getActivity().getApplicationContext(), etudiantgestion.class);
                startActivity(intent);*/
                if (etudiantgestion.getVisibility() == View.VISIBLE) {
                    etudiantgestion.setVisibility(View.GONE);

                } else {
                    etudiantgestion.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }


   /* ANImageView imageView = (ANImageView) findViewById(R.id.adimage);
        AndroidNetworking.initialize(getApplicationContext());

          imageView.setDefaultImageResId(R.drawable.book);
      imageView.setErrorImageResId(R.drawable.desktop);
      imageView.setImageUrl("https://gestionviescolaire.000webhostapp.com/pic/1.jpg");*/

    }


