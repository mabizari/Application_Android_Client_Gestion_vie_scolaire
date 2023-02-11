package com.zerophi.gestionvie.enseignantespace.gestion_notes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.squareup.picasso.Picasso;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.models.etudiantsmodel;
import com.zerophi.gestionvie.sharedpref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class modules extends AppCompatActivity {
    public static String[] imagemodule = {
            Globalurls.imagesmodule+"1.jpg",
            Globalurls.imagesmodule+"2.jpg",
           Globalurls.imagesmodule+"3.jpg",
            Globalurls.imagesmodule+"4.jpg",
           Globalurls.imagesmodule+"5.jpg",
           Globalurls.imagesmodule+"6.jpg",
            Globalurls.imagesmodule+"7.jpg",
           Globalurls.imagesmodule+"8.jpg",
          Globalurls.imagesmodule+"9.jpg",
           Globalurls.imagesmodule+"10.jpg",
           Globalurls.imagesmodule+"11.jpg",
           Globalurls.imagesmodule+"12.jpg",
            Globalurls.imagesmodule+"13.jpg",
          Globalurls.imagesmodule+"14.jpg",
          Globalurls.imagesmodule+"15.jpg",
            Globalurls.imagesmodule+"16.jpg",
         Globalurls.imagesmodule+"17.jpg",


    };
    GridView mGridView;
    ArrayList<Integer> module_id =new ArrayList<Integer>();
    int enseignantuser_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
         enseignantuser_id = sharedpref.readSharedSettingint(getApplicationContext(),"id",0);
        mGridView =(GridView ) findViewById(R.id.gridviewimagemodule); 
        mGridView.setAdapter(new ImageListAdapter(getApplicationContext(), imagemodule));

        AndroidNetworking.post(Globalurls.selectmodule)
                .addBodyParameter("enseignantuser_id",enseignantuser_id+"")
                .build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                try
                {
                    JSONObject jo;
                    for (int i=0;i<response.length();i++)
                    {
                        jo=response.getJSONObject(i);

                         int module_id2= jo.getInt("module_id");
                         module_id.add(module_id2);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }

    private class ImageListAdapter extends ArrayAdapter {


        private Context context;
        private LayoutInflater inflater;

        private String[] imageUrls;

        public ImageListAdapter(Context context, String[] imageUrls) {
            super(context, R.layout.imagemodules, imageUrls);

            this.context = context;
            this.imageUrls = imageUrls;

            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = inflater.inflate(R.layout.imagemodules, parent, false);
            }


            Picasso
                    .get()
                    .load(imageUrls[position])
                    .fit() // will explain later
                    .into((ImageView) convertView);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                int pos = position +1;
             //       Toast.makeText(getApplicationContext(),position+"dd"+enseignantuser_id+module_id.get(0),Toast.LENGTH_LONG).show();

                    for (int i=0;i<module_id.size();i++){
                        if(pos == module_id.get(i)){
                          //  Toast.makeText(getApplicationContext(),position+"dd"+enseignantuser_id+module_id.get(i),Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), ajouternote.class);
                            int mod = module_id.get(i);
                            intent.putExtra("module_id",mod);
      //  Toast.makeText(getApplicationContext(),mod+"",Toast.LENGTH_LONG).show();
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"tu n'as pas l'acces a ce module !! dzl  choisir un autre!!",Toast.LENGTH_LONG).show();

                        }

                    }
                }
            });
            return convertView;
        }
    }
}
