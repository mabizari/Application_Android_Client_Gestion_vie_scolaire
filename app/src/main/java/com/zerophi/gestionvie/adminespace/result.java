package com.zerophi.gestionvie.adminespace;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.listeviewadapter;
import com.zerophi.gestionvie.login.login;
import com.zerophi.gestionvie.model;
import com.zerophi.gestionvie.sharedpref;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class result extends AppCompatActivity {


    ListView mListView;
    listeviewadapter adapter ;
    String []title;
    String []desc;
    int[] icon;
    ArrayList<model> mArrayList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);




    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    adapter.filtrer("");
                    mListView.clearTextFilter();
                }
                else {
                    adapter.filtrer(newText);
                }
                return true;
            }
        });


        return true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_logout){
            sharedpref.saveSharedSetting(result.this,"admin","true");
            Intent logout = new Intent(getApplicationContext(),login.class);
            startActivity(logout);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
