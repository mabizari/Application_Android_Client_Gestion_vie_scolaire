package com.zerophi.gestionvie.fragment;

import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
public class recetudiant extends AppCompatActivity {
Button btn1 , btn2 ;
EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetudiant);
        btn1 = (Button)findViewById(R.id.geererunmotdepassetudiant);
        btn2 = (Button)findViewById(R.id.recupereremailelectroniqueetudiant);
        mEditText = (EditText) findViewById(R.id.emailetudiantrec);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/recuperer_enseignant.php")
                        .addBodyParameter("email",mEditText.getText().toString())
                        .build().getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response+"",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });



            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),siteetudiant.class));
            }
        });
    }
}
*/
public class recetudiant extends Fragment {
    Button btn1 , btn2 ;
    EditText mEditText;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.activity_recetudiant,container,false);
      btn1 = (Button) view.findViewById(R.id.geererunmotdepassetudiant);
        btn2 = (Button) view.findViewById(R.id.recupereremailelectroniqueetudiant);
        mEditText = (EditText) view.findViewById(R.id.emailetudiantrec);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post(Globalurls.recuperer_enseignant)
                        .addBodyParameter("email",mEditText.getText().toString())
                        .build().getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity().getApplicationContext(),response+"",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });



            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getActivity().getApplicationContext(),siteetudiant.class));
            }
        });

        return view;
    }
}