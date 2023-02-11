package com.zerophi.gestionvie.etudiantespace;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.zerophi.gestionvie.Main2Activity;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.enseignantespace.gestion_documents.documentmodules;
import com.zerophi.gestionvie.etudiantespace.absence.absenceetudiant;
import com.zerophi.gestionvie.etudiantespace.document.documentgrid;
import com.zerophi.gestionvie.etudiantespace.document.uploadpdf3;
import com.zerophi.gestionvie.etudiantespace.documents.enseignantsetudiant;
import com.zerophi.gestionvie.etudiantespace.emplois.EmploiNew;
import com.zerophi.gestionvie.etudiantespace.emplois.emplois;
import com.zerophi.gestionvie.etudiantespace.nocification.nocification;
import com.zerophi.gestionvie.etudiantespace.notificationenseignant.twonotification;
import com.zerophi.gestionvie.etudiantespace.resultas.notes;
import com.zerophi.gestionvie.sharedpref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class drawerDashEtudiant extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
  int etudiant_id;
    String name,section;
    int departement_id ;
    int semestre_id;
    int section_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_dash_etudiant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



      etudiant_id =    sharedpref.readSharedSettingint(getApplicationContext(),"id",0);
        name=  sharedpref.readSharedSetting(getApplicationContext(),"name"," ");
         departement_id = sharedpref.readSharedSettingint(getApplicationContext(),"departement_id",0);
       semestre_id =  sharedpref.readSharedSettingint(getApplicationContext(),"semestre_id",0);
        section =  sharedpref.readSharedSetting(getApplicationContext(),"section"," ");
        if (section.equals("A")){
            section_id=1;

        }else if(section.equals("B")){
            section_id=2;
        }


      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().add(R.id.main_contetudiant, new acceuiletudiant()).commit();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

          View h = navigationView.getHeaderView(0);

        ImageView img = h.findViewById(R.id.profile_image_etudiant);
        TextView us = h.findViewById(R.id.etudiantnamedrawer);
        TextView emailtxt = h.findViewById(R.id.etudiantemaildrawer);
        Glide.with(getApplicationContext()).load(sharedpref.readSharedSetting(getApplicationContext(),"profile_image"," ")).into(img);
            us.setText(sharedpref.readSharedSetting(getApplicationContext(),"name","EST Meknes"));
        emailtxt.setText(sharedpref.readSharedSetting(getApplicationContext(),"email","Ecole superieur de technologie meknes"));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Boolean Checkadmin = Boolean.valueOf(sharedpref.readSharedSetting(drawerDashEtudiant.this, "etudiant", "true"));
            if (Checkadmin) {
                finish();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("est ce que tu est sure que vous voullez quitez lapp?")
                        .setCancelable(false)
                        .setPositiveButton("oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("non", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                //  super.onBackPressed();
            }
        }
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.drawer_dash_etudiant, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings_etudiant) {
              /*  AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/selectetudiantbyid.php")
                        .addBodyParameter("etudiantuser_id","1")
                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{


                            JSONObject jo;



                            jo = response.getJSONObject(0);


                            String id_unique = jo.getString("id_unique");
                            int etudiantuser_id = jo.getInt("etudiantuser_id");
                            int annee_id = jo.getInt("annee_id");
                            int departement_id = jo.getInt("departement_id");
                            //    String password = jo.getString("password");
                            String address = jo.getString("address");
                            String telephone = jo.getString("telephone");
                            String email = jo.getString("email");
                            String first_name = jo.getString("first_name");
                            String last_name = jo.getString("last_name");
                            int semestre_id = jo.getInt("semestre_id");
                            String section = jo.getString("section");
                            String profile_image = jo.getString("profile_image");

                            Toast.makeText(getApplicationContext(),etudiantuser_id+id_unique+annee_id+departement_id+address+telephone+email+first_name+last_name+semestre_id+section+profile_image+"",Toast.LENGTH_LONG).show();

                            openDetailActivity(etudiantuser_id+"",id_unique+"",annee_id+"",
                                    departement_id+"",address,telephone,email,first_name,last_name,semestre_id+""/*,password*///,section,profile_image);

/*

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        //  mListView.setAdapter(new CustomAdaptermodifieretudiant(getApplicationContext(),etudiant));


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });*/
                startActivity(new Intent(getApplicationContext(),settingetudiant.class));
     // startActivity(new Intent(getApplicationContext(),settingetudiant.class));
            } else if (id == R.id.action_logout_etudiant) {
                sharedpref.saveSharedSetting(drawerDashEtudiant.this, "etudiant", "true");
                Intent logout = new Intent(drawerDashEtudiant.this, Main2Activity.class);
                startActivity(logout);
                finish();
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected (MenuItem item){
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_absence_etudiant) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_contetudiant, new absenceetudiant()).commit();
            } else if (id == R.id.nav_noticification_etudiant) {

                getSupportFragmentManager().beginTransaction().replace(R.id.main_contetudiant, new twonotification()).commit();
                   } else if (id == R.id.nav_acceuil_information_etudiant) {

    getSupportFragmentManager().beginTransaction().replace(R.id.main_contetudiant, new acceuiletudiant()).commit();
                // startActivity(new Intent(getApplicationContext(),nocification.class));
            } else if (id == R.id.nav_resultas_etudiant) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_contetudiant, new notes()).commit();
            } else if (id == R.id.nav_main_etudiant) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_contetudiant, new dash_etudiant()).commit();
            } else if (id == R.id.nav_emplois_etudiant) {
               getSupportFragmentManager().beginTransaction().replace(R.id.main_contetudiant, new emplois()).commit();
 
/*
                                                AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/selectemplois.php")
                                                        .addBodyParameter("semestre_id",semestre_id+"")
                                                        .addBodyParameter("departement_id",departement_id+"")
                                                        .addBodyParameter("section",section_id+"")

                                                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                                                    @Override
                                                    public void onResponse(JSONArray response) {

                                                        JSONObject jo1;

                                                        try {
                                                            jo1= response.getJSONObject(0);
                                                           String imageurl1 =jo1.getString("imageurl");
                                                       String     title1 = jo1.getString("title");
                                                          int  section1 = jo1.getInt("section_id");
                                                   openEmploiActivity(imageurl1,title1,section);
                                                            Toast.makeText(getApplicationContext(),imageurl1+title1+section1+"",Toast.LENGTH_LONG).show();


                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }


                                                    }

                                                    @Override
                                                    public void onError(ANError anError) {

                                                    }
                                                });*/

            } else if (id == R.id.nav_document_etudiant) {
startActivity(new Intent(getApplicationContext(),enseignantsetudiant.class));
                
            } else if (id == R.id.nav_email_etudiant) {
              Intent mailIntent = new Intent();
                mailIntent.setAction(Intent.ACTION_SEND);
                mailIntent.setType("message/rfc822");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"saad19551945@gmail.com"});
                startActivity(mailIntent);
               // startActivity(new Intent(getApplicationContext(),documentgrid.class));


            } else if (id == R.id.nav_logout_etudiant) {
               sharedpref.saveSharedSetting(getApplicationContext(),"etudiant","true");
                Intent logout = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(logout);
               finish();
          //   startActivity(new Intent(getApplicationContext(),uploadpdf3.class));

            }
                 else if (id == R.id.nav_document_enseignant) {
                startActivity(new Intent(getApplicationContext(),documentmodules.class));
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

    private void openDetailActivity(String ... details) {
        Intent intent = new Intent(getApplicationContext(),settingetudiant2.class);
        intent.putExtra("etudiantuser_id",details[0]+"");
        intent.putExtra("id_unique",details[1]+"");
        intent.putExtra("annee_id",details[2]+"");
        intent.putExtra("departement_id",details[3]+"");

        intent.putExtra("address",details[4]+"");
        intent.putExtra("telephone",details[5]+"");
        intent.putExtra("email",details[6]+"");
        intent.putExtra("first_name",details[7]+"");
        intent.putExtra("last_name",details[8]+"");
        intent.putExtra("semestre_id",details[9]+"");
        intent.putExtra("semestre_id",details[10]+"");
        //   intent.putExtra("password",details[10]);

      intent.putExtra("profile_image",details[11]+"");


        startActivity(intent);
    }
    private void openEmploiActivity(String ... details) {
        Intent intent = new Intent(getApplicationContext(),EmploiNew.class);

        intent.putExtra("imageurl",details[0]+"");
        intent.putExtra("title1",details[1]+"");
        intent.putExtra("section",details[2]+"");



        startActivity(intent);
    }
    }
