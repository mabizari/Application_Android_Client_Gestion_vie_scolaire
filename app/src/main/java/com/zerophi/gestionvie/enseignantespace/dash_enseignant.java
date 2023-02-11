package com.zerophi.gestionvie.enseignantespace;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.zerophi.gestionvie.*;
import com.zerophi.gestionvie.adminespace.gestion_noticification.noticificationgestion;
import com.zerophi.gestionvie.enseignantespace.gestion_absence.abcensecl;
import com.zerophi.gestionvie.enseignantespace.gestion_notes.gestion_notes;
import com.zerophi.gestionvie.etudiantespace.document.documentgrid;

import android.app.Dialog;



import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class dash_enseignant extends Fragment {
    Button btn;
    CardView mCardViewlogin, mCardViewresult, mCardViewnoticification, mCardViewabsence, mCardViewsetting;
    TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_dash_enseignant, container, false);
        mCardViewlogin = (CardView) view.findViewById(R.id.logoutenseignant);
        mCardViewresult = (CardView) view.findViewById(R.id.enseignantresult);
        mCardViewnoticification = (CardView) view.findViewById(R.id.enseignantnocification);
        mCardViewabsence = (CardView) view.findViewById(R.id.enseignantabsence);
        mCardViewsetting = (CardView) view.findViewById(R.id.settingenseignant);
        // mTextView = (TextView) findViewById(R.id.txtvnname) ;
        //  mTextView.setText(sharedpref.readSharedSetting(dash_enseignant.this,"name",":) "));


        mCardViewsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), settingenseignant.class));
            }
        });
        mCardViewabsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   startActivity(new Intent (getApplicationContext(),gestion_absence.class));
                FragmentManager fm = getActivity().getSupportFragmentManager();
                create request = new create();
                request.show(fm, "selecting");

                //  request.show(fm, "Select");

            }
        });
        mCardViewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpref.saveSharedSetting(getActivity().getApplicationContext(), "enseignant", "true");
                Intent logout = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(logout);
                getActivity().finish();
            }
        });
        mCardViewresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(getActivity().getApplicationContext(), gestion_notes.class));
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_contenseignant,new noticificationgestion()).commit();
            }
        });
        mCardViewnoticification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //  startActivity(new Intent(getActivity().getApplicationContext(), noticificationgestion.class));
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_contenseignant,new noticificationgestion()).commit();
                //getSupportFragmentManager().beginTransaction().replace(R.id.main_contadmin,new noticificationgestion()).commit();


            }
        });
        return view;
    }

    /*
    public class dash_enseignant extends AppCompatActivity {
        Button btn;
    CardView mCardViewlogin,mCardViewresult,mCardViewnoticification,mCardViewabsence,mCardViewsetting;
    TextView mTextView;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dash_enseignant);
            mCardViewlogin = (CardView) findViewById(R.id.logoutenseignant);
            mCardViewresult = (CardView) findViewById(R.id.enseignantresult);
            mCardViewnoticification = (CardView) findViewById (R.id.enseignantnocification);
        mCardViewabsence = (CardView) findViewById(R.id.enseignantabsence) ;
        mCardViewsetting = (CardView) findViewById(R.id.settingenseignant);
           // mTextView = (TextView) findViewById(R.id.txtvnname) ;
          //  mTextView.setText(sharedpref.readSharedSetting(dash_enseignant.this,"name",":) "));


            mCardViewsetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
    startActivity(new Intent(getApplicationContext(),settingenseignant.class));
                }
            });
            mCardViewabsence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 //   startActivity(new Intent (getApplicationContext(),gestion_absence.class));
                    FragmentManager fm= getSupportFragmentManager();
                    create request = new create();
                    request.show(fm,"selecting");

                   //  request.show(fm, "Select");

                }
            });
            mCardViewlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedpref.saveSharedSetting(dash_enseignant.this, "enseignant", "true");
                    Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(logout);
                    finish();
                }
            });
            mCardViewresult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   startActivity(new Intent (getApplicationContext(),gestion_notes.class));
                }
            });
            mCardViewnoticification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),noticificationgestion.class));
                }
            });

        }

        public void onBackPressed() {
            Boolean Checkenseignant = Boolean.valueOf(sharedpref.readSharedSetting(dash_enseignant.this, "enseignant", "true"));
            if (Checkenseignant) {
                finish();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("est ce que tu est sure que vous voullez quitez lapp ?")
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

    */
    public static class create extends DialogFragment {

        int exist;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();
            final View v = inflater.inflate(R.layout.pick_per, null);
            final DatePicker datePicker = (DatePicker) v.findViewById(R.id.datepick);
            final EditText hour = (EditText) v.findViewById(R.id.perID);
            //      final Spinner spn = (Spinner) v.findViewById(R.id.spinnerModule);

            //  ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, module_name);

          /*  assert spn != null;
            spn.setAdapter(adapterSpinner);*/


            builder.setView(v)
                    // Add action buttons
                    .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            int day = datePicker.getDayOfMonth();
                            int month = datePicker.getMonth() + 1;
                            int year = datePicker.getYear();
                            String date = year + "-" + month + "-" + day;
                            //  String subject = spn.getSelectedItem().toString();


                            AndroidNetworking.post(Globalurls.selectabsence)//"https://gestionviescolaire.000webhostapp.com/selectabsence.php")
                                    .addBodyParameter("absence_date", date)
                                    .addBodyParameter("heur", hour.getText().toString())
                                    .build().getAsJSONArray(new JSONArrayRequestListener() {
                                @Override
                                public void onResponse(JSONArray response) {

                                    try {
                                        JSONObject jo;
                                        jo = response.getJSONObject(0);

                                        exist = jo.getInt("exist");


                                    } catch (JSONException e) {
                                        e.printStackTrace();

                                    }

                                }

                                @Override
                                public void onError(ANError anError) {

                                }
                            });

                            if (exist == 0) {
                                Intent lintent = new Intent(getActivity(), abcensecl.class);
                                lintent.putExtra("DATE", date);
                                lintent.putExtra("PERIOD", hour.getText().toString());
                                getActivity().startActivity(lintent);
                            } else {
                                Toast.makeText(getActivity(), "ajouter deja !!!", Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            return builder.create();
        }


    }
}

