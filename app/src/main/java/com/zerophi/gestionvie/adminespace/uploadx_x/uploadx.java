package com.zerophi.gestionvie.adminespace.uploadx_x;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.adminespace.drawerDashAdmin;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class uploadx extends AppCompatActivity implements View.OnClickListener {
Button btn1,btn2;
EditText name;
TextView textemploi;

ImageView mImageView;
private final int img_request =1;
private  Bitmap mBitmap;
//private  String uploadurl = "https://gestionviescolaire.000webhostapp.com/uploadx2.php";
    private String uploadurl = Globalurls.connection;//"https://gestionviescolaire.000webhostapp.com/uploadEmploiNew/connection.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadx);
        textemploi = (TextView) findViewById(R.id.textemploi);
        btn1 = (Button) findViewById(R.id.chooseImage);
        btn2 = (Button) findViewById(R.id.uploadimage);
        final String par = getIntent().getStringExtra("par");
        final int pos = getIntent().getIntExtra("pos",0);
        textemploi.setText(" " +par);
        mImageView = (ImageView) findViewById(R.id.previewcetimage);
        btn1.setOnClickListener(this);

if (pos==0){
    Toast.makeText(getApplicationContext(),"tu n'as pas choisir une emploi pour changer ",Toast.LENGTH_LONG).show();
}
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"attent !!! ",Toast.LENGTH_SHORT).show();
                AndroidNetworking.post(uploadurl)
                        .addBodyParameter("image_name",pos+".jpg")
                        .addBodyParameter("emploi_id",pos+"")
                        .addBodyParameter("encoded_string",imagetostring(mBitmap))
                        .build().getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response+" ",Toast.LENGTH_SHORT).show();
                        generateNotification(getApplicationContext(), response,par);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chooseImage:
            selectimage();
                break;
       //     case R.id.uploadimage:
             //   uploadimage();
            //    break;

        }
    }
    private void selectimage(){
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,img_request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==img_request && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();
            try {
                mBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                mImageView.setImageBitmap(mBitmap);
                mImageView.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    /*
    private void uploadimage(){


     /*   StringRequest stringRequest = new StringRequest(Request.Method.POST, uploadurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response+"",Toast.LENGTH_SHORT).show();
                    mImageView.setImageResource(0);
                    mImageView.setVisibility(View.GONE);

                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name","1");
                params.put("image",imagetostring(mBitmap));

              return params;
            }
        };
     //   myupload.getmInstence(uploadx.this).addToRequestQue(stringRequest);







    }
    */

    private String imagetostring(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);

    }

    private static void generateNotification(Context context, String message ,String par) {
        int icon = R.drawable.ic_student;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, drawerDashAdmin.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        Notification.Builder builder = new Notification.Builder(context);

        builder.setAutoCancel(false);
        builder.setContentTitle("image est uploadé " + par);
        builder.setContentText("success");
        builder.setSmallIcon(icon);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        builder.setNumber(100);
        builder.build();

        Notification notification = builder.getNotification();
        notificationManager.notify(0, notification);
    }

}
