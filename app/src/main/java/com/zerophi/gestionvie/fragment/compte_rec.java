package com.zerophi.gestionvie.fragment;


import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.Main2Activity;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.interfaces.BaseBackPressedListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class compte_rec extends Fragment {
    LinearLayout mcardetudiant , mcardenseignant ;
    LinearLayout admin;
    CardView cardadmin,cardenseignat;

      Button btn1enseignant , btn2enseignant ;
    EditText mEditTextenseignant;

    Button btn1etudiant , btn2etudiant ;
    EditText mEditTextetudiant;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compte_rec,container,false);

        mcardenseignant= (LinearLayout) view.findViewById(R.id.recuperer_enseignant_compte);
        mcardetudiant = (LinearLayout) view.findViewById(R.id.recuperer_etudiant_compte);
        admin = (LinearLayout) view.findViewById(R.id.contacteradministrateur);
        cardadmin = (CardView) view.findViewById(R.id.cardadmin);
        cardenseignat = (CardView) view.findViewById(R.id.cardenseignant);
         btn1enseignant = (Button)view.findViewById(R.id.geererunmotdepassenseignant);
        btn2enseignant = (Button)view.findViewById(R.id.recupereremailelectroniqueenseignant);
         mEditTextenseignant = (EditText) view.findViewById(R.id.emailenseigantrec);

        btn1enseignant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post(Globalurls.recuperer_enseignant)
                        .addBodyParameter("email",mEditTextenseignant.getText().toString())
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
        btn2enseignant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(),siteenseignant.class));
            }
        });

         btn1etudiant = (Button) view.findViewById(R.id.geererunmotdepassetudiant);
        btn2etudiant = (Button) view.findViewById(R.id.recupereremailelectroniqueetudiant);
        mEditTextetudiant = (EditText) view.findViewById(R.id.emailetudiantrec);


        btn1etudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidNetworking.post(Globalurls.recuperer_etudiant)//"https://gestionviescolaire.000webhostapp.com/recuperer_enseignant.php")
                        .addBodyParameter("email",mEditTextenseignant.getText().toString())
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
        btn2etudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getActivity().getApplicationContext(),siteetudiant.class));
            }
        });

      //  macaCardViewadmin = (CardView) view.findViewById(R.id.cardadmin);

      //  getActivity().onBackPressed();

       ((Main2Activity)getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()));
        mcardenseignant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   startActivity(new Intent(getActivity().getApplicationContext(),recenseignant.class));
                   if(cardenseignat.getVisibility()==View.VISIBLE) {
                    cardenseignat.setVisibility(View.GONE);
                }else
                {
                    cardenseignat.setVisibility(View.VISIBLE);
                }
            }
        });
        mcardetudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(getActivity().getApplicationContext(),recenseignant.class));
              //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_cont,new recetudiant()).commit();
                if(cardadmin.getVisibility()==View.VISIBLE) {
                    cardadmin.setVisibility(View.GONE);
                }else
                {
                    cardadmin.setVisibility(View.VISIBLE);
                }
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent();
                mailIntent.setAction(Intent.ACTION_SEND);
                mailIntent.setType("message/rfc822");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"saad19551945@gmail.com"});
                startActivity(mailIntent);
            }
        });
        return  view;
    }

    }

