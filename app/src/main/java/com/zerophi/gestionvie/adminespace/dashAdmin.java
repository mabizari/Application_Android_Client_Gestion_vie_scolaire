package com.zerophi.gestionvie.adminespace;
import com.zerophi.gestionvie.*;
import com.zerophi.gestionvie.adminespace.gestion_noticification.noticificationgestion;
import com.zerophi.gestionvie.adminespace.gestion_utilisateur.useradmin;

import com.zerophi.gestionvie.adminespace.uploadx_x.UploadFragment;
import com.zerophi.gestionvie.adminespace.uploadx_x.uploadx;
import com.zerophi.gestionvie.etudiantespace.emploi.emploi;
import com.zerophi.gestionvie.sharedpref;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class dashAdmin extends Fragment {
    Button btn ;
    CardView mCardViewlogin,mCardViewResultat,mCardViewAbsence,mCardViewnoticification,mCardViewusers,mCardViewemplois;
    TextView txtview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dash_admin,container,false);

        mCardViewlogin = (CardView) view.findViewById(R.id.logoutadmin);
        mCardViewResultat =(CardView) view.findViewById(R.id.adminresult) ;
        mCardViewAbsence = (CardView) view.findViewById(R.id.adminabsence);
        mCardViewnoticification = (CardView) view.findViewById(R.id.adminnocification);
        mCardViewusers = (CardView) view.findViewById(R.id.adminusers);
        mCardViewemplois =(CardView) view.findViewById(R.id.adminemplois);
        txtview = (TextView)view.findViewById(R.id.nname);
        txtview.setText(sharedpref.readSharedSetting(getActivity().getApplicationContext(),"first_name",":) ")+ " "+ sharedpref.readSharedSetting(getActivity().getApplicationContext(),"last_name",":) "));


        mCardViewnoticification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent (getActivity().getApplicationContext(),noticificationgestion.class);
                startActivity(intent);*/
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_contadmin,new noticificationgestion()).commit();
            }
        });


        mCardViewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpref.saveSharedSetting(getActivity().getApplicationContext(),"admin","true");
                Intent logout = new Intent(getActivity().getApplicationContext(),MainActivity.class);
                startActivity(logout);
                getActivity().finish();

            }
        });

        mCardViewResultat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent resultat = new Intent(getActivity().getApplicationContext(),resultas.class);
                startActivity(resultat);*/
            }
        });

        mCardViewAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent absence = new Intent(getActivity().getApplicationContext(),emploi.class);
                startActivity(absence);*/
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_contadmin,new UploadFragment()).commit();
            }
        });

        mCardViewusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Inten        <android.support.v7.widget.CardView
            android:id="@+id/adminemplois"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_columnWeight="1"
            android:layout_rowWeight="1"
            android:layout_marginBottom="16dp"
            android:layout_marginLeft="16dp"
            android:layout_marginRight="16dp"
            app:cardElevation="8dp"
            app:cardCornerRadius="8dp">
            <LinearLayout
                android:layout_gravity="center_horizontal|center_vertical"
                android:layout_margin="16dp"
                android:orientation="vertical"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content">

                <ImageView
                    android:layout_width="68dp"
                    android:layout_height="90dp"
                    android:layout_gravity="center_horizontal"
                    android:background="@drawable/biology" />
                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:textSize="14dp"
                    android:text="emplois"
                    android:textAlignment="center"/>
            </LinearLayout>
        </android.support.v7.widget.CardView>t users = new Intent (getActivity().getApplicationContext(),useradmin.class);
                startActivity(users);*/
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_contadmin,new useradmin()).commit();
            }
        });


        mCardViewemplois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         //       startActivity(new Intent(getActivity().getApplicationContext(),uploadx.class));
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_contadmin,new UploadFragment()).commit();
            }
        });
        return view;
    }

}
/*
public class dashAdmin extends AppCompatActivity {
Button btn ;
CardView mCardViewlogin,mCardViewResultat,mCardViewAbsence,mCardViewnoticification,mCardViewusers,mCardViewemplois;
TextView txtview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_admin);
        mCardViewlogin = (CardView) findViewById(R.id.logoutadmin);
        mCardViewResultat =(CardView) findViewById(R.id.adminresult) ;
        mCardViewAbsence = (CardView) findViewById(R.id.adminabsence);
        mCardViewnoticification = (CardView) findViewById(R.id.adminnocification);
        mCardViewusers = (CardView) findViewById(R.id.adminusers);
        mCardViewemplois =(CardView)findViewById(R.id.adminemplois);
        txtview = (TextView)findViewById(R.id.nname);
        txtview.setText(sharedpref.readSharedSetting(dashAdmin.this,"name",":) "));

        mCardViewnoticification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),noticificationgestion.class);
                startActivity(intent);
            }
        });


        mCardViewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpref.saveSharedSetting(dashAdmin.this,"admin","true");
                Intent logout = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(logout);
                finish();

            }
        });

        mCardViewResultat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent resultat = new Intent(getApplicationContext(),resultas.class);
            startActivity(resultat);
            }
        });

        mCardViewAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent absence = new Intent(getApplicationContext(),emploi.class);
            startActivity(absence);
            }
        });

        mCardViewusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent users = new Intent (getApplicationContext(),useradmin.class);
                startActivity(users);
            }
        });


        mCardViewemplois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
startActivity(new Intent(getApplicationContext(),uploadx.class));

            }
        });

    }
    public void onBackPressed(){
        Boolean Checkadmin = Boolean.valueOf(sharedpref.readSharedSetting(dashAdmin.this,"admin","true"));
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
        }
    }
}
*/