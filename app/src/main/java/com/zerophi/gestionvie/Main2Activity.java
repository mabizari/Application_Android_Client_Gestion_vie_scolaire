package com.zerophi.gestionvie;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.zerophi.gestionvie.fragment.actualite;
import com.zerophi.gestionvie.fragment.compte_rec;
import com.zerophi.gestionvie.interfaces.OnBackPressedListener;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    protected OnBackPressedListener onBackPressedListener;
    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar= (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().add(R.id.main_cont,new info()).addToBackStack(null).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {

        if (onBackPressedListener != null){
            onBackPressedListener.doBack();}
        else{
            super.onBackPressed();}
/*
         int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 0) {

                this.finish();
                //additional code
            } else {
                getSupportFragmentManager().popBackStack();
            }*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
                 getSupportFragmentManager().beginTransaction().replace(R.id.main_cont,new MainActivity()).commit();
            //  return true;
          //  startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_compte) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_cont,new compte_rec()).commit();
            toolbar.setTitle("recuperer compte");
          //  startActivity(new Intent(getApplicationContext(),compte_rec.class));

        } else if (id == R.id.nav_actualite) {
        //    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new actualites()).commit();
      /*      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }*/  toolbar.setTitle("Site principal");
      getSupportFragmentManager().beginTransaction().replace(R.id.main_cont,new actualite()).commit();

        } else if (id == R.id.nav_informations) {
       //   startActivity(new Intent(getApplicationContext(),info.class));
               getSupportFragmentManager().beginTransaction().replace(R.id.main_cont,new info()).commit();
               toolbar.setTitle("Acceuil");
        } else if (id == R.id.nav_contact) {
            //   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragment_contacte()).commit();
            Intent mailIntent = new Intent();
            mailIntent.setAction(Intent.ACTION_SEND);
            mailIntent.setType("message/rfc822");
            mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"saad19551945@gmail.com"});
            startActivity(mailIntent);
        } else if (id == R.id.nav_login) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_cont,new MainActivity()).commit();
            toolbar.setTitle("Login");
        //    startActivity(new Intent (getApplicationContext(),MainActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }


}
