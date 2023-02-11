package com.zerophi.gestionvie.enseignantespace;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.zerophi.gestionvie.Main2Activity;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.adminespace.gestion_noticification.noticificationgestion;
import com.zerophi.gestionvie.enseignantespace.gestion_documents.documentmodules;
import com.zerophi.gestionvie.enseignantespace.gestion_nocification.notification_enseignant;
import com.zerophi.gestionvie.enseignantespace.gestion_nocification.notification_enseignant_modules;
import com.zerophi.gestionvie.enseignantespace.gestion_notes.gestion_notes;
import com.zerophi.gestionvie.enseignantespace.notification_enseignant.notificationenseignantFragment;
import com.zerophi.gestionvie.sharedpref;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

public class drawDashEnseignant extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_dash_enseignant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
toolbar.setTitle("Enseignant");
        getSupportFragmentManager().beginTransaction().add(R.id.main_contenseignant, new gestion_notes()).commit();
     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

  View h = navigationView.getHeaderView(0);

        ImageView img = h.findViewById(R.id.profile_image_enseignant);
        TextView us = h.findViewById(R.id.enseignantnamedrawer);
        TextView emailtxt = h.findViewById(R.id.enseignantemaildrawer);
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
            Boolean Checkadmin = Boolean.valueOf(sharedpref.readSharedSetting(drawDashEnseignant.this, "enseignant", "true"));
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
                // super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.draw_dash_enseignant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting_enseignant) {
            startActivity(new Intent(getApplicationContext(),settingenseignant.class));
        }
        if (id == R.id.action_logout_enseignant) {
            sharedpref.saveSharedSetting(getApplicationContext(),"enseignant","true");
            Intent logout = new Intent(getApplicationContext(),Main2Activity.class);
            startActivity(logout);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_absence_enseignant) {
            FragmentManager fm= getSupportFragmentManager();
            dash_enseignant.create request = new dash_enseignant.create();
            request.show(fm,"selecting");

        } else if (id == R.id.nav_notification_enseignant) {
  // getSupportFragmentManager().beginTransaction().replace(R.id.main_contenseignant,new noticificationgestion()).commit();
            startActivity(new Intent(getApplicationContext(),notification_enseignant_modules.class));
     //       getSupportFragmentManager().beginTransaction().replace(R.id.main_contenseignant,new notificationenseignantFragment()).commit();
        } else if (id == R.id.nav_resultas_enseignant) {
  getSupportFragmentManager().beginTransaction().replace(R.id.main_contenseignant,new gestion_notes()).commit();
        } else if (id == R.id.nav_document_enseignant) {
 startActivity(new Intent(getApplicationContext(),documentmodules.class));

        } else if (id == R.id.nav_setting_enseignant) {
         //   getSupportFragmentManager().beginTransaction().add(R.id.main_contenseignant, new settingenseignant()).commit();
startActivity(new Intent(getApplicationContext(),settingenseignant.class));

        } else if (id == R.id.nav_email_enseignant) {
            Intent mailIntent = new Intent();
            mailIntent.setAction(Intent.ACTION_SEND);
            mailIntent.setType("message/rfc822");
            mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"saad19551945@gmail.com"});
            startActivity(mailIntent);

        } else if (id == R.id.nav_logout_enseignant) {
            sharedpref.saveSharedSetting(getApplicationContext(),"enseignant","true");
            Intent logout = new Intent(getApplicationContext(),Main2Activity.class);
            startActivity(logout);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
