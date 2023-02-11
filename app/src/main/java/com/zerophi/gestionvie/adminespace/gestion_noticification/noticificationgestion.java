package com.zerophi.gestionvie.adminespace.gestion_noticification;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.adminespace.gestion_noticification.noticificationsend;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;


import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class noticificationgestion extends Fragment {
    final static String url = Globalurls.ajouternotic;  //"https://gestionviescolaire.000webhostapp.com/ajouternotic.php";
    Button btn;
    Context c;
    Button voirnotificationadmin;
    TextInputEditText notic_titre,dept;
    EditText notic_description;
    RadioGroup rgdept ;

//	Need handler for callbacks to the UI thread


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_noticificationgestion,container,false);
        btn= (Button) view.findViewById(R.id.btnajouter);
        voirnotificationadmin = (Button) view.findViewById(R.id.voirnotificationadmin);
        voirnotificationadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity().getApplicationContext(),notification_admin.class));
            }
        });
        notic_titre =(TextInputEditText) view.findViewById(R.id.notic_titre) ;
        notic_description =(EditText) view.findViewById(R.id.notic_description);

        rgdept = (RadioGroup) view.findViewById(R.id.gr);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new noticificationsend(getActivity().getApplicationContext(), url, notic_titre, notic_description, rgdept).execute();



            }
        });

        return view;


    }



    public boolean checkInternetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    }

/*
public class noticificationgestion extends AppCompatActivity {
    final static String url = "https://gestionviescolaire.000webhostapp.com/ajouternotic.php";
    Button btn;
    Context c;

    EditText notic_titre,notic_description,dept;
    RadioGroup rgdept ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticificationgestion);
        btn= (Button) findViewById(R.id.btnajouter);

        notic_titre =(EditText) findViewById(R.id.notic_titre) ;
        notic_description =(EditText)findViewById(R.id.notic_description);

         rgdept = (RadioGroup) findViewById(R.id.gr);
         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 new noticificationsend(noticificationgestion.this, url, notic_titre, notic_description,rgdept).execute();
             }
         });


    }
}*/
