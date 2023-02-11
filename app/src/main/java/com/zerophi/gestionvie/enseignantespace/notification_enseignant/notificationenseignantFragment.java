package com.zerophi.gestionvie.enseignantespace.notification_enseignant;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.sharedpref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

import static com.zerophi.gestionvie.enseignantespace.gestion_notes.modules.imagemodule;

/**
 * A simple {@link Fragment} subclass.
 */
public class notificationenseignantFragment extends Fragment {
    public static String[] modules = {
            "module 1",
            "module 2",
            "module 3",
            "module 4",
            "module 5",
            "module 6",

    };

    final static String url = Globalurls.ajouternotificationenseignant;
    Button btn;
    Context c;
    Spinner mSpinner;

    public notificationenseignantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notificationenseignant, container, false);
        final ArrayList<Integer> module_id = new ArrayList<Integer>();

        int enseignantuser_id = sharedpref.readSharedSettingint(getActivity().getApplicationContext(), "id", 0);
        mSpinner = (Spinner) view.findViewById(R.id.modulenotificationenseignant);
        AndroidNetworking.post(Globalurls.selectmodule)
                .addBodyParameter("enseignantuser_id", enseignantuser_id + "")
                .build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jo;
                    for (int i = 0; i < response.length(); i++) {
                        jo = response.getJSONObject(i);

                        int module_id2 = jo.getInt("module_id");
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


        mSpinner.setAdapter(new arraymodule(getActivity().getApplicationContext(), modules));


        return view;
    }


    private class arraymodule extends ArrayAdapter {

        private Context context;
        private LayoutInflater inflater;

        private String[] modules;

        public arraymodule(Context context,String[] modules) {
            super(context, R.layout.imagemodules, modules);

            this.context = context;
            this.modules = modules;

            inflater = LayoutInflater.from(context);
        }
    }
}