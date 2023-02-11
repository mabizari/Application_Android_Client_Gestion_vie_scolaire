package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.connection.connect;
import com.zerophi.gestionvie.connection.errors;
import com.zerophi.gestionvie.models.etudiantsmodel;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ajouteretudiant extends AppCompatActivity {
    MaterialButton btnajouter;
    TextInputEditText codemassar, nom, prenom, telephone, address, email;
    private String urlajouter = Globalurls.ajouteretudiant;//"https://gestionviescolaire.000webhostapp.com/ajouteretudiant.php";

    RadioGroup mRadioGroup;
    RadioButton mRadioButton;
    MaterialButton btnannee, btndepartment, btnsemestre;
    int annee = 0, departement = 0, semestre = 0;
    String[] stannee, stsemestre, stdepartement;

    TextView passwdgen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouteretudiant);
        mRadioGroup = (RadioGroup) findViewById(R.id.gender);
        codemassar = (TextInputEditText) findViewById(R.id.id_uniqueetudiant);
        nom = (TextInputEditText) findViewById(R.id.nomajouteretudiant);
        prenom = (TextInputEditText) findViewById(R.id.prenomajouteretudiant);

        btnajouter = (MaterialButton) findViewById(R.id.ajouteretudiant);
        telephone = (TextInputEditText) findViewById(R.id.telephoneajouteretudiant);
        address = (TextInputEditText) findViewById(R.id.addressajouteretudiant);
        email = (TextInputEditText) findViewById(R.id.emailajouteretudiant);
        btnannee = (MaterialButton) findViewById(R.id.anneeajouteretudiant);
        btndepartment = (MaterialButton) findViewById(R.id.departementajouteretudiant);
        btnsemestre = (MaterialButton) findViewById(R.id.semestreajouteretudiant);
passwdgen = (TextView) findViewById(R.id.passwdgen);

        btnannee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stannee = new String[]{
                        "2018/2019", "2019/2020", "2020/2021"
                };
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ajouteretudiant.this);
                mBuilder.setTitle("choisir un ANNEE");
                mBuilder.setIcon(R.drawable.ic_gps_fixed_black_24dp);
                mBuilder.setSingleChoiceItems(stannee, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        annee = ++which;
                        dialog.dismiss();
                        btnannee.setText("selected");
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mAlertDialog = mBuilder.create();
                mAlertDialog.show();
            }

        });
        btndepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stdepartement = new String[]{
                        "GI"
                };
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ajouteretudiant.this);
                mBuilder.setTitle("choisir un departement");
                mBuilder.setIcon(R.drawable.ic_gps_fixed_black_24dp);
                mBuilder.setSingleChoiceItems(stdepartement, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        departement = ++which;
                        btndepartment.setText("GI");
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mAlertDialog = mBuilder.create();
                mAlertDialog.show();
            }

        });
        btnsemestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stsemestre = new String[]{
                        "s1", "s2", "s3", "s4", "s5", "s6"
                };
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ajouteretudiant.this);
                mBuilder.setTitle("choisir un semestre");
                mBuilder.setIcon(R.drawable.ic_gps_fixed_black_24dp);
                mBuilder.setSingleChoiceItems(stsemestre, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        semestre = ++which;
                        btnsemestre.setText("selected");
                        dialog.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mAlertDialog = mBuilder.create();
                mAlertDialog.show();


            }
        });
        btnajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        boolean noErrors = true;
                    String noms = nom.getText().toString();
                    String prenoms = prenom.getText().toString();
                    String id_uniques = codemassar.getText().toString();
                    String telephones = telephone.getText().toString();
                    String addresses = address.getText().toString();
                    String emailedtst = email.getText().toString() ;



                    if (noms.isEmpty()) {
                   nom.setError("remplire ce champ obligatoire");
                   noErrors = false;
               } else {
                   nom.setError(null);
               }
                if (prenoms.isEmpty()) {
                    prenom.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    prenom.setError(null);
                }
                 if (id_uniques.isEmpty()) {
                    codemassar.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    codemassar.setError(null);
                }
                  if (telephones.isEmpty()) {
                    telephone.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    telephone.setError(null);
                }
                  if (addresses.isEmpty()) {
                    address.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    address.setError(null);
                }
                if (emailedtst.isEmpty()) {
                    email.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    email.setError(null);
                }
                if (annee ==0) {
                   btnannee.setError("clicker ici ");
                    noErrors = false;
                } else {
                    btnannee.setError(null);
                }
                  if (semestre ==0) {
                   btnsemestre.setError("clicker ici ");
                    noErrors = false;
                } else {
                    btnsemestre.setError(null);
                }
                  if (departement ==0) {
                   btndepartment.setError("clicker ici ");
                    noErrors = false;
                } else {
                    btndepartment.setError(null);
                }
                 if (noErrors) {
                      int id = mRadioGroup.getCheckedRadioButtonId();
                    mRadioButton = (RadioButton) findViewById(id);
                  String   gender = mRadioButton.getText().toString();

                    new etudiantajoutersend( ajouteretudiant.this,codemassar,  nom,  prenom,  telephone,  address,
                                email, annee,  departement,  semestre,  urlajouter,  gender,passwdgen).execute();
                //    ajouteretudiant.super.onBackPressed();
           }


       /*         if (
                        (nom.length() <= 0 || nom == null) || (prenom.length() <= 0 || prenom == null)
                                || (email.length() <= 0 || email == null)
                                || (address.length() <= 0 || address == null) || (telephone.length() <= 0 || telephone == null) ||
                                (codemassar.length() <= 0 || codemassar == null)
                                || (annee == 0) || (semestre == 0) || (departement == 0)
                        ){
                     Toast.makeText(getApplicationContext(), "slv remplire tous les champs !!!", Toast.LENGTH_SHORT).show();
                }else{
                    int id = mRadioGroup.getCheckedRadioButtonId();
                    mRadioButton = (RadioButton) findViewById(id);
                  String   gender = mRadioButton.getText().toString();

                    new etudiantajoutersend( ajouteretudiant.this,codemassar,  nom,  prenom,  telephone,  address,
                                email, annee,  departement,  semestre,  urlajouter,  gender).execute();

                }
*/


            }
        });
    }

    public class etudiantajoutersend extends AsyncTask<Void,Void,String> {

        TextInputEditText codemassar, nom, prenom, telephone, address, email;
        int annee , departement , semestre ;
        String urlajouter;
        String gender;
        Context c;
        etudiantsmodel mEtudiantsmodel;
TextView passwdgen;
        public etudiantajoutersend(Context c,TextInputEditText codemassar, TextInputEditText nom, TextInputEditText prenom, TextInputEditText telephone, TextInputEditText address,
                                   TextInputEditText email, int annee, int departement, int semestre, String urlajouter, String gender,TextView passwdgen) {
            this.c= c;
            this.codemassar = codemassar;
            this.nom = nom;
            this.prenom = prenom;
            this.telephone = telephone;
            this.address = address;
            this.email = email;
            this.annee = annee;
            this.departement = departement;
            this.semestre = semestre;
            this.urlajouter = urlajouter;
            this.gender = gender;
this.passwdgen = passwdgen;


            mEtudiantsmodel = new etudiantsmodel();

            mEtudiantsmodel.setId_unique(codemassar.getText().toString());
            mEtudiantsmodel.setFirst_name(nom.getText().toString());
            mEtudiantsmodel.setLast_name(prenom.getText().toString());

            mEtudiantsmodel.setTelephone(telephone.getText().toString());
            mEtudiantsmodel.setAddress(address.getText().toString());
            mEtudiantsmodel.setEmail(email.getText().toString());
            mEtudiantsmodel.setAnnee_id(annee);
            mEtudiantsmodel.setDepartement_id(departement);
            mEtudiantsmodel.setSemestre_id(semestre);
            mEtudiantsmodel.setGender(gender);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(c, "envoyer encore !!! ",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           // Toast.makeText(c,s+" :) ",Toast.LENGTH_LONG).show();
            passwdgen.setText(s);
        }

        @Override
        protected String doInBackground(Void... voids) {
            return this.ajouteretudiant();
        }

        private String ajouteretudiant() {
            Object mconnect = connect.connect(urlajouter);
            if (mconnect.toString().startsWith("Error")) {
                return mconnect.toString();
            }
            try {
                HttpURLConnection connection = (HttpURLConnection) mconnect;

                OutputStream os = new BufferedOutputStream(connection.getOutputStream());
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

                String LoginData = new packdataetudiants(mEtudiantsmodel).packetudiants();

                bw.write(LoginData);
                bw.flush();
                bw.close();
                os.close();
                int responsecode = connection.getResponseCode();
                if(responsecode==connection.HTTP_OK){

                    InputStream is = new BufferedInputStream(connection.getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuffer response = new StringBuffer();
                    while((line=br.readLine())!=null)
                    {
                        response.append(line+"\n");
                    }
                    br.close();
                    is.close();
                    return response.toString();

                }else {
                    return errors.RESPONSE_ERROR + String.valueOf(responsecode);
                }



            } catch (IOException e) {
                e.printStackTrace();
            }



            return "go go go !!! ";
        }
    }

    public class packdataetudiants {

        etudiantsmodel mEtudiantsmodel;
        private String mFormBody;

        public packdataetudiants(etudiantsmodel etudiantsmodel) {
            mEtudiantsmodel = etudiantsmodel;
        }

        public String packetudiants(){

            List<NameValuePair> formData = new ArrayList<NameValuePair>();
            formData.add(new BasicNameValuePair("id_unique",mEtudiantsmodel.getId_unique()));
            formData.add(new BasicNameValuePair("first_name",mEtudiantsmodel.getFirst_name()));
            formData.add(new BasicNameValuePair("last_name",mEtudiantsmodel.getLast_name()));
            formData.add(new BasicNameValuePair("gender",mEtudiantsmodel.getGender()));
            formData.add(new BasicNameValuePair("departement_id",mEtudiantsmodel.getDepartement_id()+""));
            formData.add(new BasicNameValuePair("address",mEtudiantsmodel.getAddress()));
            formData.add(new BasicNameValuePair("telephone",mEtudiantsmodel.getTelephone()));
            formData.add(new BasicNameValuePair("semestre_id",mEtudiantsmodel.getSemestre_id()+""));
            formData.add(new BasicNameValuePair("annee_id",mEtudiantsmodel.getAnnee_id()+""));
            formData.add(new BasicNameValuePair("email",mEtudiantsmodel.getEmail()));



            if (formData == null ){
                mFormBody = null;
            }
            StringBuilder sb = new StringBuilder();
            for(int i =0; i<formData.size();i++) {
                NameValuePair item = formData.get(i);

                sb.append(URLEncoder.encode(item.getName()));
                sb.append("=");
                sb.append(URLEncoder.encode(item.getValue()));
                if (i != (formData.size() - 1)) {
                    sb.append("&");
                }
            }
            mFormBody=sb.toString();
            return  mFormBody;
        }
    }

}