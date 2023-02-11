package com.zerophi.gestionvie.adminespace;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.Main2Activity;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.adminespace.gestion_noticification.noticificationgestion;
import com.zerophi.gestionvie.adminespace.gestion_utilisateur.useradmin;
import com.zerophi.gestionvie.adminespace.uploadx_x.UploadFragment;
import com.zerophi.gestionvie.sharedpref;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class drawerDashAdmin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
ImageView profile_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_dash_admin);

        Toast.makeText(getApplicationContext(),sharedpref.readSharedSetting(getApplicationContext(),"profile_image"," "),Toast.LENGTH_LONG).show();
      //Glide.with(getApplicationContext()).load(sharedpref.readSharedSetting(getApplicationContext(),"profile_image"," ")).into(profile_image);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Administrateur");
      //  getSupportFragmentManager().beginTransaction().add(R.id.main_contadmin,new useradmin()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_contadmin,new dashAdmin()).commit();

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Drawer administrateur", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
  View h = navigationView.getHeaderView(0);

        ImageView img = h.findViewById(R.id.profile_image_admin);
        TextView us = h.findViewById(R.id.admin_namedrawer);
        TextView emailtxt = h.findViewById(R.id.admin_email_drawer);
        Glide.with(getApplicationContext()).load(sharedpref.readSharedSetting(getApplicationContext(),"profile_image"," ")).into(img);
        us.setText(sharedpref.readSharedSetting(getApplicationContext(),"name","EST MEKNES"));
      //  profile_image = (ImageView) findViewById(R.id.profile_image_admin);
       // Glide.with(getApplicationContext()).load(sharedpref.readSharedSetting(getApplicationContext(),"profile_image"," ")).into(profile_image);
        emailtxt.setText(sharedpref.readSharedSetting(getApplicationContext(),"email","Ecole Superieur de Technologie GI"));
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Boolean Checkadmin = Boolean.valueOf(sharedpref.readSharedSetting(drawerDashAdmin.this,"admin","true"));
            if(Checkadmin){
                finish();}
            else{
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
         //   super.onBackPressed();
        }
    }}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_dash_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting_admin) {
            startActivity(new Intent(getApplicationContext(),settingadmin.class));
        }
        else if (id == R.id.action_logout_admin){
            sharedpref.saveSharedSetting(drawerDashAdmin.this,"admin","true");
            Intent logout = new Intent(drawerDashAdmin.this,Main2Activity.class);
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

        if (id == R.id.nav_gestion_users) {

            getSupportFragmentManager().beginTransaction().replace(R.id.main_contadmin,new useradmin()).commit();
        } else if (id == R.id.nav_gestion_noticification) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_contadmin,new noticificationgestion()).commit();
        } else if (id == R.id.nav_gestion_emploi) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_contadmin,new UploadFragment()).commit();
        } else if (id == R.id.nav_logout_admin) {
             sharedpref.saveSharedSetting(drawerDashAdmin.this,"admin","true");
            Intent logout = new Intent(drawerDashAdmin.this,Main2Activity.class);
            startActivity(logout);
            finish();
         } else if (id == R.id.nav_page_principale_admin) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_contadmin,new dashAdmin()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
