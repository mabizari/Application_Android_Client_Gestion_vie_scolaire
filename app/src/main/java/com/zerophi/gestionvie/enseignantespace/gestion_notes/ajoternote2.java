package com.zerophi.gestionvie.enseignantespace.gestion_notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.sharedpref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ajoternote2 extends AppCompatActivity {
TextView mTextViewnom;
EditText mEditText;
TextView mTextViewmodules;
Button btnajouter;
String note;
TextView noteajou;
int semestre_id ,module_id;
    String etudiantuser_id;
    String module_name ;

    int mod_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoternote2);
        mTextViewnom =(TextView)findViewById(R.id.nomajouternote);
        btnajouter = (Button)findViewById(R.id.ajouternote);
        mEditText = (EditText)findViewById(R.id.edit_text) ;
        mTextViewmodules =(TextView) findViewById(R.id.moduleajouternote);
        noteajou = (TextView) findViewById(R.id.noteajo2);

        Intent intent =this.getIntent();
        String name = intent.getExtras().getString("first_name") + " "+intent.getExtras().getString("last_name");
          note = intent.getExtras().getString("note");
        module_id =intent.getExtras().getInt("module_id");
        etudiantuser_id = intent.getExtras().getString("etudiantuser_id");

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

        semestre_id=intent.getExtras().getInt("semestre_id");
        mTextViewnom.setText(name);
        mTextViewmodules.setText(module_name);

    //    etudiantuser_id = sharedpref.readSharedSettingint(getApplicationContext(),"id",0);


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
                                  noteajou.setText(note);
                                }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


      btnajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
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
                                    //Toast.makeText(getApplicationContext(),"tu as deja ajouter cet note",Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ajoternote2.this);
                                    builder.setMessage("note deja ajouter est ce que tu est sure voulu continue?")
                                            .setCancelable(false)
                                            .setPositiveButton("oui", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    AndroidNetworking.post(Globalurls.modifiernote)
                                                            .addBodyParameter("etudiantuser_id",etudiantuser_id+"")
                                                            .addBodyParameter("module_id",module_id+"")
                                                            .addBodyParameter("semestre_id",semestre_id+"")
                                                            .addBodyParameter("note", mEditText.getText().toString())
                                                            .build().getAsString(new StringRequestListener() {
                                                        @Override
                                                        public void onResponse(String response) {
                                                            Toast.makeText(getApplicationContext(),response+" ",Toast.LENGTH_LONG).show();

                                                        }

                                                        @Override
                                                        public void onError(ANError anError) {

                                                        }
                                                    });


                                                }
                                            })
                                            .setNegativeButton("non", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();

                                }else {
                                    AndroidNetworking.post(Globalurls.ajouternote)
                                            .addBodyParameter("etudiantuser_id",etudiantuser_id+"")
                                            .addBodyParameter("module_id",module_id+"")
                                            .addBodyParameter("semestre_id",semestre_id+"")
                                            .addBodyParameter("note", mEditText.getText().toString())
                                            .build().getAsString(new StringRequestListener() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(getApplicationContext(),response+" ",Toast.LENGTH_LONG).show();

                                        }

                                        @Override
                                        public void onError(ANError anError) {

                                        }
                                    });
                                }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });



            }
        });



    }
}
