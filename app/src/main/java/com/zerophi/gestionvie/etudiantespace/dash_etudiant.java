package com.zerophi.gestionvie.etudiantespace;
import com.zerophi.gestionvie.MainActivity;

import com.zerophi.gestionvie.*;
import com.zerophi.gestionvie.adminespace.dashAdmin;
import com.zerophi.gestionvie.etudiantespace.absence.absenceetudiant;
import com.zerophi.gestionvie.etudiantespace.document.documentgrid;
import com.zerophi.gestionvie.etudiantespace.emploi.emploi;
import com.zerophi.gestionvie.etudiantespace.nocification.nocification;
import com.zerophi.gestionvie.etudiantespace.emplois.emplois;
import com.zerophi.gestionvie.etudiantespace.resultas.notes;

import android.content.DialogInterface;
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

public class dash_etudiant extends Fragment {
    Button btn;
    CardView mCardViewlogin,mCardViewAbsence,mCardViewnocification,mCardViewresultas,mCardViewemploi,mCardViewemail,mCardViewsetting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dash_etudiant,container,false);

        mCardViewnocification = (CardView) view.findViewById(R.id.etudiantnocification);
        mCardViewlogin = (CardView) view.findViewById(R.id.logoutetudiant);
        //   mTextView = (TextView) findViewById(R.id.txtvnameetudiant);
        mCardViewemploi= (CardView) view.findViewById(R.id.etudiantemploi);
        //l    mTextView.setText(sharedpref.readSharedSetting(dash_etudiant.this,"name",":) "));
        mCardViewresultas = (CardView) view.findViewById(R.id.etudiantresult);
        mCardViewemail = (CardView) view.findViewById(R.id.etudiantemail) ;
        mCardViewsetting = (CardView) view.findViewById(R.id.settingetudiant);
        mCardViewAbsence = (CardView) view.findViewById(R.id.etudiantabsence);



        mCardViewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpref.saveSharedSetting(getActivity().getApplicationContext(),"etudiant","true");
                Intent logout = new Intent(getActivity().getApplicationContext(),Main2Activity.class);
                startActivity(logout);
                getActivity().finish();
            }
        });

        mCardViewnocification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent noci = new Intent(getActivity().getApplicationContext(),nocification.class);
                startActivity(noci);


            }
        });

        mCardViewemploi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent emplois = new Intent (getActivity().getApplicationContext(),emplois.class);
                startActivity(emplois);*/
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_contetudiant, new emplois()).commit();
            }
        });

        mCardViewresultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    startActivity(new Intent(getActivity().getApplicationContext(),notes.class));
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_contetudiant, new notes()).commit();
            }
        });

        mCardViewsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   startActivity(new Intent(getApplicationContext(),emploik.class));
                startActivity(new Intent(getActivity().getApplicationContext(),settingetudiant.class));
            }
        });
        mCardViewemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent();
                mailIntent.setAction(Intent.ACTION_SEND);
                mailIntent.setType("message/rfc822");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"saad19551945@gmail.com"});
                startActivity(mailIntent);
            }
        });

        mCardViewAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(getActivity().getApplicationContext(),absenceetudiant.class));
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_contetudiant, new absenceetudiant()).commit();
            }
        });

        return view;
    }
}
/*
public class dash_etudiant extends AppCompatActivity {
Button btn;
CardView mCardViewlogin,mCardViewAbsence,mCardViewnocification,mCardViewresultas,mCardViewemploi,mCardViewemail,mCardViewsetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_etudiant);

        mCardViewnocification = (CardView) findViewById(R.id.etudiantnocification);
        mCardViewlogin = (CardView) findViewById(R.id.logoutetudiant);
     //   mTextView = (TextView) findViewById(R.id.txtvnameetudiant);
       mCardViewemploi= (CardView) findViewById(R.id.etudiantemploi);
    //l    mTextView.setText(sharedpref.readSharedSetting(dash_etudiant.this,"name",":) "));
        mCardViewresultas = (CardView) findViewById(R.id.etudiantresult);
        mCardViewemail = (CardView) findViewById(R.id.etudiantemail) ;
        mCardViewsetting = (CardView) findViewById(R.id.settingetudiant);
        mCardViewAbsence = (CardView) findViewById(R.id.etudiantabsence);



        mCardViewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpref.saveSharedSetting(dash_etudiant.this,"etudiant","true");
                Intent logout = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(logout);
                finish();
            }
        });

        mCardViewnocification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent noci = new Intent(getApplicationContext(),nocification.class);
                startActivity(noci);

            }
        });

      mCardViewemploi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emplois = new Intent (getApplicationContext(),emplois.class);
                startActivity(emplois);
            }
        });

       mCardViewresultas.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getApplicationContext(),notes.class));
           }
       });

       mCardViewsetting.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            //   startActivity(new Intent(getApplicationContext(),emploik.class));
        startActivity(new Intent(getApplicationContext(),settingetudiant.class));
           }
       });
       mCardViewemail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent mailIntent = new Intent();
               mailIntent.setAction(Intent.ACTION_SEND);
               mailIntent.setType("message/rfc822");
               mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"saad19551945@gmail.com"});
               startActivity(mailIntent);
           }
       });

    mCardViewAbsence.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),absenceetudiant.class));
        }
    });

    }




    public void onBackPressed(){
        Boolean Checketudiant = Boolean.valueOf(sharedpref.readSharedSetting(dash_etudiant.this,"etudiant","true"));
        if(Checketudiant){
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
}}
*/