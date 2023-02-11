package com.zerophi.gestionvie.etudiantespace.emplois;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.widget.ANImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.enseignantespace.gestion_notes.ajoternote2;
import com.zerophi.gestionvie.sharedpref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import uk.co.senab.photoview.PhotoView;

public class emplois extends Fragment {
    ImageView mImageView1;
    PhotoView mImageView2;
    TextView mTextViewtitle1 ,mTextViewsection1;
    int semestre_id, departement_id,section_id;
String section;
    String title1, imageurl1;
           int section1;
    int etudiantuser_id;
           SwipeRefreshLayout refresh ;
    String title2, imageurl2, section2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_emplois, container, false);



        mImageView1 = (PhotoView) view.findViewById(R.id.emploi1);
        mTextViewtitle1 = (TextView) view.findViewById(R.id.titreemploi1);
        mTextViewsection1 = (TextView) view.findViewById(R.id.sectionemploi1);
        etudiantuser_id = sharedpref.readSharedSettingint(getActivity().getApplicationContext(), "id", 0);
       mTextViewsection1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AndroidNetworking.post(Globalurls.select1etudiant)
                       .addBodyParameter("etudiantuser_id",etudiantuser_id+"")

                       .build().getAsJSONArray(new JSONArrayRequestListener() {
                   @Override
                   public void onResponse(JSONArray response) {
                       JSONObject jo ;

                       try {
                           jo = response.getJSONObject(0);
                           section = jo.getString("section");
                           semestre_id = jo.getInt("semestre_id");
                           departement_id= jo.getInt("departement_id");

                           if(section.equals("A")) {
                               section_id = 1;
                           }else{
                               section_id =2;
                           }
                           //          Toast.makeText(getActivity().getApplicationContext(),section+section_id+departement_id+"",Toast.LENGTH_LONG).show();

                       } catch (JSONException e) {
                           e.printStackTrace();
                       }



                       AndroidNetworking.post(Globalurls.selectemplois)
                               .addBodyParameter("semestre_id",semestre_id+"")
                               .addBodyParameter("departement_id",departement_id+"")
                               .addBodyParameter("section",section_id+"")

                               .build().getAsJSONArray(new JSONArrayRequestListener() {
                           @Override
                           public void onResponse(JSONArray response) {

                               JSONObject jo1;

                               try {
                                   jo1= response.getJSONObject(0);
                                   imageurl1 =jo1.getString("imageurl");
                                   title1 = jo1.getString("title");
                                   section1 = jo1.getInt("section_id");
                                   StringBuffer sb = new StringBuffer(title1);
                                   String Titre1 = sb.substring(0,3);
                                   String titre2 = sb.substring(3,5);
                                   String titre3 = sb.substring(5,7);
                                   String titre4 = sb.substring(7);
                                   //           Toast.makeText(getActivity().getApplicationContext(),imageurl1+title1+section1+"",Toast.LENGTH_LONG).show();
                                   Glide.with(getActivity().getApplicationContext()).load("https://gestionviescolaire.000webhostapp.com/uploadEmploiNew/"+imageurl1).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(mImageView1);

                                   //     mImageView1.setImageResource("https://gestionviescolaire.000webhostapp.com/uploadEmploiNew/images/"+imageurl1);
                                   mTextViewtitle1.setText(Titre1+"loi "+ titre2 + " "+ titre3+ " "+ titre4);
                                   if(section1==1) {
                                       mTextViewsection1.setText("section :  " + "A" + " ");
                                   }else {
                                       mTextViewsection1.setText("section :  " + "B" + " ");
                                   }
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }


                           }

                           @Override
                           public void onError(ANError anError) {

                           }
                       });





                       mImageView1.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               Intent intent = new Intent(getActivity().getApplicationContext(), emplois2.class);

                               intent.putExtra("title1",imageurl1);
                               intent.putExtra("section1",section1);
                               intent.putExtra("imageurl",imageurl1);
                               Toast.makeText(getActivity().getApplicationContext(),imageurl1 + " ",Toast.LENGTH_LONG).show();
                               startActivity(intent);
                           }
                       });


                   }

                   @Override
                   public void onError(ANError anError) {

                   }
               });
           }
       });


        AndroidNetworking.post(Globalurls.select1etudiant)
                .addBodyParameter("etudiantuser_id",etudiantuser_id+"")

                .build().getAsJSONArray(new JSONArrayRequestListener() {
                                            @Override
                                            public void onResponse(JSONArray response) {
                                                JSONObject jo ;

                                                try {
                                                    jo = response.getJSONObject(0);
                                                    section = jo.getString("section");
                                                    semestre_id = jo.getInt("semestre_id");
                                                    departement_id= jo.getInt("departement_id");

                                                    if(section.equals("A")) {
                                                        section_id = 1;
                                                    }else{
                                                        section_id =2;
                                                    }
                                          //          Toast.makeText(getActivity().getApplicationContext(),section+section_id+departement_id+"",Toast.LENGTH_LONG).show();

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }



                                                AndroidNetworking.post(Globalurls.selectemplois)
                                                        .addBodyParameter("semestre_id",semestre_id+"")
                                                        .addBodyParameter("departement_id",departement_id+"")
                                                        .addBodyParameter("section",section_id+"")

                                                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                                                    @Override
                                                    public void onResponse(JSONArray response) {

                                                        JSONObject jo1;

                                                        try {
                                                            jo1= response.getJSONObject(0);
                                                            imageurl1 =jo1.getString("imageurl");
                                                            title1 = jo1.getString("title");
                                                            section1 = jo1.getInt("section_id");
StringBuffer sb = new StringBuffer(title1);
String Titre1 = sb.substring(0,3);
String titre2 = sb.substring(3,5);
String titre3 = sb.substring(5,7);
String titre4 = sb.substring(7);
                                                 //           Toast.makeText(getActivity().getApplicationContext(),imageurl1+title1+section1+"",Toast.LENGTH_LONG).show();
                                                           Glide.with(getActivity().getApplicationContext()).load("https://gestionviescolaire.000webhostapp.com/uploadEmploiNew/"+imageurl1).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(mImageView1);

                                                       //     mImageView1.setImageResource("https://gestionviescolaire.000webhostapp.com/uploadEmploiNew/images/"+imageurl1);
                                                            mTextViewtitle1.setText(Titre1+"loi "+ titre2 + " "+ titre3+ " "+ titre4);
                                                            if(section1==1) {
                                                                mTextViewsection1.setText("section :  " + "A" + " ");
                                                            }else {
                                                                mTextViewsection1.setText("section :  " + "B" + " ");
                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }


                                                    }

                                                    @Override
                                                    public void onError(ANError anError) {

                                                    }
                                                });





//AndroidNetworking.initialize(getActivity().getApplicationContext());
                                           //     Glide.with(getActivity().getApplicationContext()).load("https://gestionviescolaire.000webhostapp.com/uploadEmploiNew/"+imageurl2).into(mImageView2);
                                              //  Glide.with(getActivity().getApplicationContext()).load("https://gestionviescolaire.000webhostapp.com/uploadEmploiNew/"+imageurl1).into(mImageView1);
       //mImageView1.setImageUrl("https://gestionviescolaire.000webhostapp.com/uploadEmploiNew/"+imageurl1);
   //  mImageView2.setImageUrl("https://gestionviescolaire.000webhostapp.com/uploadEmploiNew/"+imageurl2);


                mImageView1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity().getApplicationContext(), emplois2.class);

        intent.putExtra("title1",imageurl1);
        intent.putExtra("section1",section1);
        intent.putExtra("imageurl",imageurl1);
        Toast.makeText(getActivity().getApplicationContext(),imageurl1 + " ",Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
});


            }

            @Override
            public void onError(ANError anError) {

            }
        });

        return view;
    }


                                            }


/*
public class emplois extends AppCompatActivity {
    ANImageView mImageView1,mImageView2;
    TextView mTextViewtitle1 , mTextViewtitle2, mTextViewsection1,mTextViewsection2;
int semestre_id , departement_id ;
String title1 , imageurl1 , section1;
    String title2 , imageurl2 , section2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emplois);

        mImageView1 = (ANImageView) findViewById(R.id.emploi1);
        mImageView2 = (ANImageView) findViewById(R.id.emploi2);

        mTextViewtitle1 =(TextView)findViewById(R.id.titreemploi1);
        mTextViewtitle2 =(TextView)findViewById(R.id.titreemploi2);
        mTextViewsection1 =(TextView)findViewById(R.id.sectionemploi1);
        mTextViewsection2 =(TextView)findViewById(R.id.sectionemploi2);
        int etudiantuser_id = sharedpref.readSharedSettingint(getApplicationContext(),"id",0);
        AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/select1etudiant.php")
                .addBodyParameter("etudiantuser_id",etudiantuser_id+"")
                .build().getAsJSONArray(new JSONArrayRequestListener() {
                                            @Override
                                            public void onResponse(JSONArray response) {
                                                JSONObject jo;

                                                try {
                                                    jo = response.getJSONObject(0);

                                                    semestre_id = jo.getInt("semestre_id");
                                                    departement_id = jo.getInt("departement_id");
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                            }

                                            @Override
                                            public void onError(ANError anError) {

                                            }
                                        });

                AndroidNetworking.post("https://gestionviescolaire.000webhostapp.com/selectemplois.php")
                        .addBodyParameter("semestre_id",semestre_id+"")
                        .addBodyParameter("departement_id",departement_id+"")

                        .build().getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSONObject jo1;
                        JSONObject jo2;
                            try {
                                jo1= response.getJSONObject(0);
                                imageurl1 =jo1.getString("imageurl");
                                title1 = jo1.getString("title");
                                section1 = jo1.getString("section");

                                jo2 = response.getJSONObject(1);
                                imageurl2 =jo2.getString("imageurl");
                                title2 = jo2.getString("title");
                                section2 = jo2.getString("section");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


                mTextViewtitle1.setText(title1);
                mTextViewtitle2.setText(title2);
                mTextViewsection1.setText(section1);
                mTextViewsection2.setText(section2);
            /*    Picasso
                        .get()
                        .load("https://gestionviescolaire.000webhostapp.com/pic/"+imageurl1)
                        .fit() // will explain later
                        .into((ImageView) mImageView1);
                Picasso
                        .get()
                        .load("https://gestionviescolaire.000webhostapp.com/pic/"+imageurl2)
                        .fit() // will explain later
                        .into((ImageView) mImageView2);

*/
/*AndroidNetworking.initialize(getApplicationContext());

                mImageView1.setImageUrl("https://gestionviescolaire.000webhostapp.com/pic/"+"1.jpg");
                mImageView2.setImageUrl("https://gestionviescolaire.000webhostapp.com/pic/"+imageurl2);


                mImageView1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), emplois2.class);

        intent.putExtra("title1",imageurl1);
        intent.putExtra("section1",section1);
        intent.putExtra("imageurl",imageurl1);

        startActivity(intent);
    }
});


mImageView2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), emplois2.class);

        intent.putExtra("title2",title2);
        intent.putExtra("section2",section2);
        intent.putExtra("imageurl",imageurl2);
        startActivity(intent);
    }
});
            }

            @Override
            public void onError(ANError anError) {

            }
        });

    }


}
*/