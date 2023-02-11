package com.zerophi.gestionvie.adminespace.gestion_utilisateur;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.zerophi.gestionvie.models.enseignantmodel;

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
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class ajouterenseignant extends AppCompatActivity {
    private TextView mTextViewDate,mTextViewModule;
    private TextInputEditText nom, prenom, address, telephone, email,id_unique;
    private String urlajouter = Globalurls.ajouterenseignant;// "https://gestionviescolaire.000webhostapp.com/ajouterenseignant.php";
    private MaterialButton btnajouter;
    RadioGroup mRadioGroup;
    TextView passwdgen;
    RadioButton mRadioButton;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    int module_id;
   // Button module ;
    String [] modules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouterenseignant);

       // module = (Button) findViewById(R.id.modulejouterenseignant);
        module_id=0;

        btnajouter = (MaterialButton) findViewById(R.id.ajouteruserenseignant);
        mTextViewDate = (TextView) findViewById(R.id.dateajouterenseignatn);

        nom = (TextInputEditText) findViewById(R.id.nomajouterenseignant);
        prenom = (TextInputEditText) findViewById(R.id.prenomajouterenseignant);
        address = (TextInputEditText) findViewById(R.id.addressajouterenseignant);
        telephone = (TextInputEditText) findViewById(R.id.telephoneajouterenseignant);
        email = (TextInputEditText) findViewById(R.id.emailajouterenseignant);
passwdgen = (TextView)findViewById(R.id.passwdgenenseign);
        id_unique = (TextInputEditText) findViewById(R.id.id_uniqueenseignant);

        mRadioGroup = (RadioGroup)findViewById(R.id.geder);

       /* module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modules = new String[]{"mathematiques generales","algorithmique et bases de programmation c",
               "langages et technique d'expression et de communication",
                "architecture des ordinateurs",
                        "system d'information et bases de donnees",
                        "algorithmique et structures des donnes",
                        "systeme d'exploitation & reseaux",
                        "environnement economique et juridique de l'entreprise",
                        "interconnexion des reseaux",
                        "base de donnnes avancees",
                        "programmation orientees object java",
                        "outils d'aide a la decision"

                };
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ajouterenseignant.this);
                mBuilder.setTitle("choisir un module");
                mBuilder.setIcon(R.drawable.ic_gps_fixed_black_24dp);
                mBuilder.setSingleChoiceItems(modules, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        module_id = ++which;
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
        });*/

        mTextViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int annee = cal.get(Calendar.YEAR);
                int mounth = cal.get(Calendar.MONTH);
                int jour = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(ajouterenseignant.this, android.R.style.Theme_DeviceDefault_Light_DarkActionBar, mOnDateSetListener,
                        annee, mounth, jour);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }

        });
        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String Date = year + "-" + month + "-" + dayOfMonth;
                mTextViewDate.setText(Date);
            }
        };

      btnajouter.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                          boolean noErrors = true;
                    String noms = nom.getText().toString();
                    String prenoms = prenom.getText().toString();
                    String id_uniques = id_unique.getText().toString();
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
                    id_unique.setError("remplire ce champ obligatoire");
                    noErrors = false;
                } else {
                    id_unique.setError(null);
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
                if (mTextViewDate.getText().toString()=="clicker ici pour choisir date rejoine") {
                   mTextViewDate.setError("clicker ici ");
                    noErrors = false;
                } else {
                    mTextViewDate.setError(null);
                }
                 if (noErrors) {
                     int id = mRadioGroup.getCheckedRadioButtonId();
                  mRadioButton = (RadioButton)findViewById(id);
                 String gender=  mRadioButton.getText().toString();

                    new enseignantajoutsend(getApplicationContext(), id_unique,  nom,  prenom,gender,  address,
                                telephone,  email, urlajouter, mTextViewDate,passwdgen).execute();
                   // ajouterenseignant.super.onBackPressed();
           }


          }
      });
    }


    public class enseignantajoutsend extends AsyncTask<Void,Void,String> {
        private Context c;
        private TextView mTextViewDate;
        private TextInputEditText nom, prenom, address, telephone, email,id_unique;

        private String urlajouter ;
        private Button btnajouter;
        String gender;

        enseignantmodel mEnseignantmodel;
TextView passwdgen;
        int module_id;

        public enseignantajoutsend(Context c,TextInputEditText id_unique,  TextInputEditText nom, TextInputEditText prenom,String gender,  TextInputEditText address,
                                   TextInputEditText telephone, TextInputEditText email,  String urlajouter,
                                  TextView textViewDate,TextView passwdgen) {
            this.c = c;
            mTextViewDate = textViewDate;
            this.nom = nom;
            this.prenom = prenom;
            this.address = address;
            this.telephone = telephone;
            this.email = email;

            this.urlajouter = urlajouter;
            this.gender = gender;

            this.id_unique  = id_unique;

this.passwdgen= passwdgen;
            mEnseignantmodel = new enseignantmodel();
            mEnseignantmodel.setLast_name(prenom.getText().toString());
            mEnseignantmodel.setFirst_name(nom.getText().toString());
            mEnseignantmodel.setGender(gender);

            mEnseignantmodel.setAddress(address.getText().toString());
            mEnseignantmodel.setTelephone(telephone.getText().toString());
            mEnseignantmodel.setEmail(email.getText().toString());

            mEnseignantmodel.setJoin_date(mTextViewDate.getText().toString());
            mEnseignantmodel.setId_unique(id_unique.getText().toString());
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(c,mEnseignantmodel.getGender()+mEnseignantmodel.getJoin_date()+"",Toast.LENGTH_LONG).show();
            Toast.makeText(c, "envoyer encore !!! ",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
          //  Toast.makeText(c,s+" :)", Toast.LENGTH_LONG).show();
            passwdgen.setText(s);
        }

        @Override
        protected String doInBackground(Void... voids) {

            return this.ajouterenseignant();
        }

        private String ajouterenseignant() {
            Object mconnect = connect.connect(urlajouter);
            if (mconnect.toString().startsWith("Error")) {
                return mconnect.toString();
            }
            try {
                HttpURLConnection connection = (HttpURLConnection) mconnect;

                OutputStream os = new BufferedOutputStream(connection.getOutputStream());
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

                String LoginData = new packdataenseignant(mEnseignantmodel).packenseignant();

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
    public class packdataenseignant {

        enseignantmodel mEnseignantmodel;
        private String mFormBody;

        public packdataenseignant(enseignantmodel enseignantmodel) {
            mEnseignantmodel = enseignantmodel;
        }

        public String packenseignant (){
            List<NameValuePair> formData = new ArrayList<NameValuePair>();
            formData.add(new BasicNameValuePair("id_unique",mEnseignantmodel.getId_unique()));
            formData.add(new BasicNameValuePair("last_name",mEnseignantmodel.getLast_name()));
            formData.add(new BasicNameValuePair("first_name",mEnseignantmodel.getFirst_name()));
            formData.add(new BasicNameValuePair("gender",mEnseignantmodel.getGender()));
            formData.add(new BasicNameValuePair("email",mEnseignantmodel.getEmail()));
            formData.add(new BasicNameValuePair("address",mEnseignantmodel.getAddress()));
            formData.add(new BasicNameValuePair("telephone",mEnseignantmodel.getTelephone()));
            formData.add(new BasicNameValuePair("join_date",mEnseignantmodel.getJoin_date()));






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

            return mFormBody;
        }
    }

}