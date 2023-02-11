package com.zerophi.gestionvie.connection;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.adminespace.gestion_utilisateur.deleteadmin2;
import com.zerophi.gestionvie.adminespace.gestion_utilisateur.deleteenseignant2;
import com.zerophi.gestionvie.adminespace.gestion_utilisateur.deleteetudiant2;
import com.zerophi.gestionvie.adminespace.gestion_utilisateur.modifieradmin2;
import com.zerophi.gestionvie.adminespace.gestion_utilisateur.modifierenseignant2;
import com.zerophi.gestionvie.adminespace.gestion_utilisateur.modifieretudiant2;
import com.zerophi.gestionvie.etudiantespace.nocification.CustomAdapternotic;

import com.zerophi.gestionvie.models.adminmodel;
import com.zerophi.gestionvie.models.enseignantmodel;
import com.zerophi.gestionvie.models.etudiantsmodel;
import com.zerophi.gestionvie.models.nocificationmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser extends AsyncTask<Void,Void,Boolean> {
    Context mContext;
    ListView mListView;
    String jdata;
    int jj;
    ArrayList<adminmodel> admin = new ArrayList<>();
    ArrayList<enseignantmodel> enseignant = new ArrayList<>();
    ArrayList<etudiantsmodel> etudiant = new ArrayList<>();

    ArrayList<nocificationmodel> nocifications = new ArrayList<>();
    ProgressDialog pd;

    public Parser(Context context, String jdata, ListView listView) {
        this.mContext = context;
        this.jdata = jdata;
        this.mListView = listView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(mContext);
        pd.setTitle("atteient");
        pd.setMessage("atteint un moment !!! ");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            JSONArray ja = new JSONArray(jdata);
            JSONObject jo;
            jo = ja.getJSONObject(1);
            int parsing = jo.getInt("parsing");
            jj = parsing;
            if (parsing == 1) {
                return this.parseadmin();
            } else if (parsing == 2) {
                return this.parseenseignant();
            } else if (parsing == 3) {
                return this.parseetudiant();
            }
            else if (parsing == 5) {
                return this.parsenocification();
            } else {
                Log.i("info","ddddddddddddd");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    private Boolean parseadmin()  {
        try{

        JSONArray ja = new JSONArray(jdata);
        JSONObject jo;

        admin.clear();
        adminmodel madminmodel;
Log.i("info","dddddddddddelzkdozdkopzkdd dijzeidjz");
        for (int i = 0; i < ja.length(); i++) {
            jo = ja.getJSONObject(i);

            int adminuser_id = jo.getInt("adminuser_id");
            String admin_name = jo.getString("admin_name");
            String email = jo.getString("email");
            String first_name = jo.getString("first_name");
            String last_name = jo.getString("last_name");
            int role_id = jo.getInt("role_id");

            madminmodel = new adminmodel();

            madminmodel.setAdminuser_id(adminuser_id);
            madminmodel.setAdmin_name(admin_name);
            madminmodel.setEmail(email);
            madminmodel.setFirst_name(first_name);
            madminmodel.setLast_name(last_name);
            madminmodel.setRole_id(role_id);
            admin.add(madminmodel);
        }
        return true;

    } catch (JSONException e) {
        e.printStackTrace();
        return false;
    }



}

    private Boolean parseenseignant() {
          try{
        JSONArray ja = new JSONArray(jdata);
        JSONObject jo;

        enseignant.clear();
        enseignantmodel menseignantmodel ;

        for (int i = 0; i < ja.length(); i++) {
            jo = ja.getJSONObject(i);

            int enseignatuser_id = jo.getInt("enseignantuser_id");
            String name = jo.getString("name");
            String email = jo.getString("email");
            String first_name = jo.getString("first_name");
            String last_name = jo.getString("last_name");
            int role_id = jo.getInt("role_id");
            String password_generer = jo.getString("passwordgenerer");
            String join_date  =jo.getString("join_date");
            String telephone = jo.getString("telephone");
            String address = jo.getString("address");
           // int module_it = jo.getInt("module_id");
            String gender = jo.getString("gender");
            String id_unique = jo.getString("id_unique");

            menseignantmodel = new enseignantmodel();

            menseignantmodel.setEnseignantuser_id(enseignatuser_id);
            menseignantmodel.setName(name);
            menseignantmodel.setEmail(email);
            menseignantmodel.setFirst_name(first_name);
            menseignantmodel.setLast_name(last_name);
            menseignantmodel.setRole_id(role_id);
            menseignantmodel.setPassword(password_generer);
            menseignantmodel.setJoin_date(join_date);
            menseignantmodel.setTelephone(telephone);
            menseignantmodel.setAddress(address);
      //      menseignantmodel.setModule_id(module_it);
            menseignantmodel.setGender(gender);
            menseignantmodel.setId_unique(id_unique);


            enseignant.add(menseignantmodel);
        }
        return true;

    } catch (JSONException e) {
        e.printStackTrace();
        return false;
    }

    }

    private Boolean parseetudiant() {
 try
        {

            JSONArray ja=new JSONArray(jdata);
            JSONObject jo;

            etudiant.clear();
            etudiantsmodel metudiantsmodel ;

            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

                int etudiantuser_id = jo.getInt("etudiantuser_id");
                String id_unique = jo.getString("id_unique");

            String name = jo.getString("name");
            String email = jo.getString("email");
            String first_name = jo.getString("first_name");
            String last_name = jo.getString("last_name");
            int role_id = jo.getInt("role_id");
            String password_generer = jo.getString("passwordgenerer");
            String telephone = jo.getString("telephone");
            String address = jo.getString("address");
            String gender = jo.getString("gender");
            int annee_id = jo.getInt("annee_id");
            int departement_id = jo.getInt("departement_id");
            int semestre_id = jo.getInt("semestre_id");

                metudiantsmodel = new etudiantsmodel();

            metudiantsmodel.setEtudiantuser_id(etudiantuser_id);
            metudiantsmodel.setName(name);
            metudiantsmodel.setEmail(email);
            metudiantsmodel.setFirst_name(first_name);
            metudiantsmodel.setLast_name(last_name);
            metudiantsmodel.setRole_id(role_id);
            metudiantsmodel.setPassword(password_generer);
            metudiantsmodel.setTelephone(telephone);
            metudiantsmodel.setAddress(address);
            metudiantsmodel.setGender(gender);
            metudiantsmodel.setId_unique(id_unique);
            metudiantsmodel.setDepartement_id(departement_id);
            metudiantsmodel.setSemestre_id(semestre_id);
            metudiantsmodel.setAnnee_id(annee_id);




                etudiant.add(metudiantsmodel);
            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

    }

    private Boolean parsenocification() {
        try
        {

            JSONArray ja=new JSONArray(jdata);
            JSONObject jo;

            nocifications.clear();
            nocificationmodel noci;

            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
/*[{"notic_id":"1","notic_titre":"annonce1","notic_description":
                "pour les etudiant tsi on ","publish_date":"2019-02-18 14:55:32","departement_id":"1","parsing":"5"}*/
                int notice_id=jo.getInt("notic_id");
                String notic_ids = " "+notice_id;
                String notic_titre=jo.getString("notic_titre");
                String notic_description = jo.getString("notic_description");
                String publish_date = jo.getString("publish_date");
                int dept = jo.getInt("departement_id");

                noci=new nocificationmodel();

                noci.setNotic_id(notice_id);
                noci.setNotic_titre(notic_titre);
                noci.setNotic_description(notic_description);
                noci.setPublish_date(publish_date);
                noci.setDept(dept);

                nocifications.add(noci);
            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

      pd.dismiss();
      if(isParsed){

if(jj==5) {
    mListView.setAdapter(new CustomAdapternotic(mContext, nocifications));
}
else if(jj==1){
    mListView.setAdapter(new CustomAdapteradmin(mContext, admin));
}else if(jj==2){
    mListView.setAdapter(new CustomAdapterenseignant(mContext,enseignant));

}else {
    mListView.setAdapter(new CustomAdapteretudiant(mContext,etudiant));

}

    }
    else {
           Toast.makeText(mContext, jdata, Toast.LENGTH_SHORT).show();
      }



}

    public class CustomAdapteradmin extends BaseAdapter {
        private Context mContext;
        private ArrayList<adminmodel> mAdminmodels;

        public CustomAdapteradmin(Context context, ArrayList <adminmodel>adminmodels) {
            mContext = context;
            this.mAdminmodels = adminmodels;
        }

        @Override
        public int getCount() {
            return mAdminmodels.size();
        }

        @Override
        public Object getItem(int position) {
            return mAdminmodels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null)
            {
                convertView=LayoutInflater.from(mContext).inflate(R.layout.rowadminuser,parent,false);
                Toast.makeText(mContext,"ddddddddddddddkjsikjdiosdjfiojsfiojsfi",Toast.LENGTH_LONG).show();

            }
            TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);
            TextView txtmail = (TextView) convertView.findViewById(R.id.emailuser) ;
            ImageView trashadmin = (ImageView) convertView.findViewById(R.id.trashadmin);
            adminmodel adminmodel =  (adminmodel)  this.getItem(position);


            final int adminuser_id = adminmodel.getAdminuser_id();
            //    final String admin_name = adminmodel.getAdmin_name();
            final    String email = adminmodel.getEmail();
            final String first_name = adminmodel.getFirst_name();
            final  String last_name = adminmodel.getLast_name();
            //   final  int role_id = adminmodel.getRole_id();


            txtnom.setText(first_name+" "+last_name);
            txtmail.setText(email);
            trashadmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                       deleteDetailActivity(first_name,adminuser_id+" ",last_name,email);
                }
            });
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //  openDetailActivity(adminuser_id+"",admin_name,email,first_name,last_name,role_id+"");
                    openDetailActivity(first_name,adminuser_id+" ",last_name,email);

                }
            });
            return convertView;

        }

   private void deleteDetailActivity(String ... details) {
            Intent intent = new Intent(mContext,deleteadmin2.class);
            intent.putExtra("first_name",details[0]);
            intent.putExtra("adminuser_id",details[1]);
            intent.putExtra("last_name",details[2]);
            intent.putExtra("email",details[3]);


            //   intent.putExtra("role_id",details[5]);


            mContext.startActivity(intent);
        }

        private void openDetailActivity(String ... details) {
            Intent intent = new Intent(mContext,modifieradmin2.class);
            intent.putExtra("first_name",details[0]);
            intent.putExtra("adminuser_id",details[1]);
            intent.putExtra("last_name",details[2]);
            intent.putExtra("email",details[3]);


            //   intent.putExtra("role_id",details[5]);


            mContext.startActivity(intent);
        }
    }
    public class CustomAdapterenseignant extends BaseAdapter {
        private Context mContext;
        private ArrayList<enseignantmodel> mEnseignantmodelArrayList;

        public CustomAdapterenseignant(Context context, ArrayList<enseignantmodel> enseignantmodelArrayList) {
            mContext = context;
            this.mEnseignantmodelArrayList = enseignantmodelArrayList;
        }

        @Override
        public int getCount() {
            return mEnseignantmodelArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return mEnseignantmodelArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.rowadminuser, parent, false);
            }
            TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);
            TextView txtemail = (TextView) convertView.findViewById(R.id.emailuser) ;
            ImageView trashenseignnt = (ImageView) convertView.findViewById(R.id.trashadmin);
            enseignantmodel menseignantmodel = (enseignantmodel) this.getItem(position);

            final int enseignantuser_id = menseignantmodel.getEnseignantuser_id();
            final String name = menseignantmodel.getName();
            final String email = menseignantmodel.getEmail();
            final String first_name = menseignantmodel.getFirst_name();
            final String last_name = menseignantmodel.getLast_name();
            final int role_id = menseignantmodel.getRole_id();
            final String passwordgenerer = menseignantmodel.getPassword();
            final String join_date = menseignantmodel.getJoin_date();
            final String telephone = menseignantmodel.getTelephone();
            final String address = menseignantmodel.getAddress();
            final int module_id = menseignantmodel.getModule_id();
            final String gender = menseignantmodel.getGender();
            final String id_unique = menseignantmodel.getId_unique();


            txtnom.setText(email);
            txtemail.setText(email);
            trashenseignnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
      deleteenseignantDetailActivity(first_name,enseignantuser_id+" ",last_name,email);
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    openDetailActivity(enseignantuser_id + "", name, email, first_name, last_name, role_id + "", passwordgenerer, join_date, telephone,
                            address, module_id + "", gender, id_unique);

                }
            });
            return convertView;

        }


        private void openDetailActivity(String... details) {
            Intent intent = new Intent(mContext, modifierenseignant2.class);

            intent.putExtra("enseignantuser_id", details[0]);
            intent.putExtra("name", details[1]);
            intent.putExtra("email", details[2]);
            intent.putExtra("first_name", details[3]);
            intent.putExtra("last_name", details[4]);
            intent.putExtra("role_id", details[5]);
            intent.putExtra("passwordgenerer", details[6]);
            intent.putExtra("join_date", details[7]);
            intent.putExtra("telephone", details[8]);
            intent.putExtra("address", details[9]);
            intent.putExtra("module_id", details[10]);
            intent.putExtra("gender", details[11]);
            intent.putExtra("id_unique", details[12]);


            mContext.startActivity(intent);
        }
                private void deleteenseignantDetailActivity(String ... details) {
            Intent intent = new Intent(mContext,deleteenseignant2.class);
            intent.putExtra("first_name",details[0]);
            intent.putExtra("enseignantuser_id",details[1]);
            intent.putExtra("last_name",details[2]);
            intent.putExtra("email",details[3]);





            mContext.startActivity(intent);
        }
    }
    public class CustomAdapteretudiant extends BaseAdapter {

        private Context mContext;
        private ArrayList<etudiantsmodel> mEtudiantsmodelArrayList;

        public CustomAdapteretudiant(Context context, ArrayList<etudiantsmodel> etudiantsmodelArrayList) {
            mContext = context;
            this.mEtudiantsmodelArrayList = etudiantsmodelArrayList;
        }

        @Override
        public int getCount() {
            return mEtudiantsmodelArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return mEtudiantsmodelArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.rowusers, parent, false);
            }
            TextView txtnom = (TextView) convertView.findViewById(R.id.nomuser);
            TextView txtemail = (TextView) convertView.findViewById(R.id.emailuser);
            ImageView trashetudiant = (ImageView) convertView.findViewById(R.id.trashadmin);
            etudiantsmodel metudinatmodel = (etudiantsmodel) this.getItem(position);

            final int etudiantuser_id = metudinatmodel.getEtudiantuser_id();
            final String name = metudinatmodel.getName();
            final String email = metudinatmodel.getEmail();
            final String first_name = metudinatmodel.getFirst_name();
            final String last_name = metudinatmodel.getLast_name();
            final int role_id = metudinatmodel.getRole_id();
           final String passwordgenerer = metudinatmodel.getPassword();
            final String telephone = metudinatmodel.getTelephone();
            final String address = metudinatmodel.getAddress();
            final String gender = metudinatmodel.getGender();
            final String id_unique = metudinatmodel.getId_unique();
            final int departement_id = metudinatmodel.getDepartement_id();
            final int semestre_id = metudinatmodel.getSemestre_id();
            final int annee_id = metudinatmodel.getAnnee_id();

            txtnom.setText(name);
            txtemail.setText(email);
            trashetudiant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
 openDetailActivity(first_name,etudiantuser_id+" ",last_name,email);
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    openDetailActivity(etudiantuser_id + "", name, email, first_name, last_name, role_id + "", passwordgenerer, telephone,
                            address, gender, id_unique, departement_id + "", semestre_id + "", annee_id + "");

                }
            });
            return convertView;

        }


        private void openDetailActivity(String... details) {
            Intent intent = new Intent(mContext, modifieretudiant2.class);

            intent.putExtra("etudiantuser_id", details[0]);
            intent.putExtra("name", details[1]);
            intent.putExtra("email", details[2]);
            intent.putExtra("first_name", details[3]);
            intent.putExtra("last_name", details[4]);
            intent.putExtra("role_id", details[5]);
            intent.putExtra("passwordgenerer", details[6]);
            intent.putExtra("telephone", details[7]);
            intent.putExtra("address", details[8]);
            intent.putExtra("gender", details[9]);
            intent.putExtra("id_unique", details[10]);
            intent.putExtra("departement_id", details[11]);
            intent.putExtra("semestre_id", details[12]);
            intent.putExtra("annee_id", details[13]);


            mContext.startActivity(intent);
        }
            private void deleteetudiantDetailActivity(String ... details) {
            Intent intent = new Intent(mContext,deleteetudiant2.class);
            intent.putExtra("first_name",details[0]);
            intent.putExtra("etudiantuser_id",details[1]);
            intent.putExtra("last_name",details[2]);
            intent.putExtra("email",details[3]);





            mContext.startActivity(intent);
        }
    }
}
