package com.zerophi.gestionvie.etudiantespace.resultas;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.google.gson.JsonObject;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.sharedpref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class notes extends Fragment {

    String jsonurl = Globalurls.resultasetudiant;//"https://gestionviescolaire.000webhostapp.com/resultasetudiant.php";
TextView updnote;
    TextView note1,note2,note3,note4,note5,note6
            ,note7,note8,note9,note10,note11,note12,
            note13,note14,note15,note16,note17;
    LinearLayout linear1note, linear2note, linear3note, linear4note;
    TextView sem1,sem2,sem3,sem4;
    SwipeRefreshLayout refreshnote;
    int id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notes,container,false);

        id = sharedpref.readSharedSettingint(getActivity().getApplicationContext(),"id",0);
        note1 =(TextView)   view.findViewById(R.id.notenote1);
        note2 =(TextView)   view.findViewById(R.id.notenote2);
        note3 =(TextView)   view.findViewById(R.id.notenote3);
        note4 =(TextView)   view.findViewById(R.id.notenote4);
        note5 =(TextView)   view.findViewById(R.id.notenote5);
        note6 =(TextView)   view.findViewById(R.id.notenote6);
        note7 =(TextView)   view.findViewById(R.id.notenote7);
        note8 =(TextView)   view.findViewById(R.id.notenote8);
        note9 =(TextView)   view.findViewById(R.id.notenote9);
        note10 =(TextView)  view.findViewById(R.id.notenote10);
        note11 =(TextView)  view.findViewById(R.id.notenote11);
        note12 =(TextView)  view.findViewById(R.id.notenote12);
        note13 =(TextView)  view.findViewById(R.id.notenote13);
        note14 =(TextView)  view.findViewById(R.id.notenote14);
        note15 =(TextView)  view.findViewById(R.id.notenote15);
        note16 =(TextView) view.findViewById(R.id.notenote16);
        note17 =(TextView)  view.findViewById(R.id.notenote17);


        updnote = (TextView) view.findViewById(R.id.updnote);
        linear1note = (LinearLayout) view.findViewById(R.id.linear1note);
        linear2note = (LinearLayout) view.findViewById(R.id.linear2note);
        linear3note = (LinearLayout) view.findViewById(R.id.linear3note);
        linear4note = (LinearLayout) view.findViewById(R.id.linear4note);
        sem1 = (TextView) view.findViewById(R.id.sem1);
        sem2 = (TextView) view.findViewById(R.id.sem2);
        sem3 = (TextView) view.findViewById(R.id.sem3);
        sem4 = (TextView) view.findViewById(R.id.sem4);


        sem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (linear1note.getVisibility() == View.VISIBLE){
                  linear1note.setVisibility(View.GONE);
              }else{
                  linear1note.setVisibility(View.VISIBLE);
              }
            }
        });
        sem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linear2note.getVisibility() == View.VISIBLE){
                    linear2note.setVisibility(View.GONE);
                }else{
                    linear2note.setVisibility(View.VISIBLE);
                }
            }
        });
        sem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linear3note.getVisibility() == View.VISIBLE){
                    linear3note.setVisibility(View.GONE);
                }else{
                    linear3note.setVisibility(View.VISIBLE);
                }
            }
        });
        sem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linear4note.getVisibility() == View.VISIBLE){
                    linear4note.setVisibility(View.GONE);
                }else{
                    linear4note.setVisibility(View.VISIBLE);
                }
            }
        });


updnote.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AndroidNetworking.post(jsonurl)
                .addBodyParameter("etudiantuser_id",id+"")
                .build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jo;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jo = response.getJSONObject(i);
                        if (jo.getInt("module_id") == 1) {
                            note1.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 2) {
                            note2.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 3) {
                            note3.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 4) {
                            note4.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 5) {
                            note5.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 6) {
                            note6.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 7) {
                            note7.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 8) {
                            note8.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 9) {
                            note9.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 10) {
                            note10.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 11) {
                            note11.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 12) {
                            note12.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 13) {
                            note13.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 14) {
                            note14.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 15) {
                            note15.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 16) {
                            note16.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 17) {
                            note17.setText(jo.getString("note"));
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "iiiiiiiiii", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
            @Override
            public void onError(ANError anError) {

            }
        });

    }
});

        AndroidNetworking.post(jsonurl)
                .addBodyParameter("etudiantuser_id",id+"")
                .build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jo;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jo = response.getJSONObject(i);
                        if (jo.getInt("module_id") == 1) {
                            note1.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 2) {
                            note2.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 3) {
                            note3.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 4) {
                            note4.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 5) {
                            note5.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 6) {
                            note6.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 7) {
                            note7.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 8) {
                            note8.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 9) {
                            note9.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 10) {
                            note10.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 11) {
                            note11.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 12) {
                            note12.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 13) {
                            note13.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 14) {
                            note14.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 15) {
                            note15.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 16) {
                            note16.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 17) {
                            note17.setText(jo.getString("note"));
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "iiiiiiiiii", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
            @Override
            public void onError(ANError anError) {

            }
        });


        return view;
    }
}
/*
public class notes extends AppCompatActivity {
String jsonurl ="https://gestionviescolaire.000webhostapp.com/resultasetudiant.php";

TextView note1,note2,note3,note4,note5,note6
        ,note7,note8,note9,note10,note11,note12,
    note13,note14,note15,note16,note17;
int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
         id = sharedpref.readSharedSettingint(getApplicationContext(),"id",0);
        note1 =(TextView)findViewById(R.id.notenote1);
        note2 =(TextView)findViewById(R.id.notenote2);
        note3 =(TextView)findViewById(R.id.notenote3);
        note4 =(TextView)findViewById(R.id.notenote4);
        note5 =(TextView)findViewById(R.id.notenote5);
        note6 =(TextView)findViewById(R.id.notenote6);
        note7 =(TextView)findViewById(R.id.notenote7);
        note8 =(TextView)findViewById(R.id.notenote8);
        note9 =(TextView)findViewById(R.id.notenote9);
        note10 =(TextView)findViewById(R.id.notenote10);
        note11 =(TextView)findViewById(R.id.notenote11);
        note12 =(TextView)findViewById(R.id.notenote12);
        note13 =(TextView)findViewById(R.id.notenote13);
        note14 =(TextView)findViewById(R.id.notenote14);
        note15 =(TextView)findViewById(R.id.notenote15);
        note16 =(TextView)findViewById(R.id.notenote16);
        note17 =(TextView)findViewById(R.id.notenote17);





        AndroidNetworking.post(jsonurl)
                .addBodyParameter("etudiantuser_id",id+"")
                .build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jo;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jo = response.getJSONObject(i);
                        if (jo.getInt("module_id") == 1) {
                            note1.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 2) {
                            note2.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 3) {
                            note3.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 4) {
                            note4.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 5) {
                            note5.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 6) {
                            note6.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 7) {
                            note7.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 8) {
                            note8.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 9) {
                            note9.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 10) {
                            note10.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 11) {
                            note11.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 12) {
                            note12.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 13) {
                            note13.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 14) {
                            note14.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 15) {
                            note15.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 16) {
                            note16.setText(jo.getString("note"));
                        } else if (jo.getInt("module_id") == 17) {
                            note17.setText(jo.getString("note"));
                        } else {
                            Toast.makeText(getApplicationContext(), "iiiiiiiiii", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
            @Override
            public void onError(ANError anError) {

            }
        });

        }
    }
    */
